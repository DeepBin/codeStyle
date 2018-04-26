
package com.boco.eoms.exelawtipstaffexam.bo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.hibernate.HibernateException;

import com.boco.eoms.common.util.UUIDHexGenerator;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.exelawtipstaffexam.dao.ZfksJDBCDAO;
import com.boco.eoms.exelawtipstaffexam.dao.ZfryKcfpDAO;
import com.boco.eoms.exelawtipstaffexam.model.BestSqdqAndKdId;
import com.boco.eoms.exelawtipstaffexam.model.ResultMessage;
import com.boco.eoms.exelawtipstaffexam.model.StringValue;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksKc;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksXh;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksZw;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksApply;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksKcgl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksKd;
/**
 * 
 * 项目名称：CNFM    
 * 类名称：ZfryKcglBO    
 * 类描述：   执法人员考试考场管理 
 * @version   1.0
 * @author wjk
 *
 */
public class ZfryKcglBO {
	private ZfksJDBCDAO zfksJDBCDAO = new ZfksJDBCDAO();
	
	private ZfryKcfpDAO zfryKcfpDAO = new ZfryKcfpDAO();
	
	public ResultMessage resetKC(String year, String provinceId) throws HibernateException, SQLException {
		ResultMessage resultMessage = new ResultMessage(ResultMessage.STATUS_SUCC, "重置考场成功.");
		
		long timeStart = System.currentTimeMillis();
		
		try {
			this.zfksJDBCDAO.deleteZW(year, provinceId);
			this.zfksJDBCDAO.deleteKC(year, provinceId);
			this.zfksJDBCDAO.resetAllApplyKchAndZwh(year, provinceId);
		} catch (Exception e) {
			new ResultMessage(ResultMessage.STATUS_FAIL, "重置考场失败.");
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("provinceId = " + provinceId + " resetKC cost: " + ((timeEnd - timeStart)) + "ms.");
		
		return resultMessage;
	}
	
	/**
	 * 给全省考生分配准考证号、分配考场座位
	 * 
	 * @param year
	 * @param provinceId
	 * @throws HibernateException
	 * @throws SQLException
	 * @author wjk
	 */
	public ResultMessage distribute(String year, String provinceId) throws HibernateException, SQLException {
		ResultMessage resultMessage = null;
		
		long timeStart = System.currentTimeMillis();
		// 获取当前最大批次，加1作为下一批次号
		resultMessage = this.checkApplyNoKq(year, provinceId);
		if (null != resultMessage)
			return resultMessage;
		
		int batchNo = getNextMaxBatchNo(year, provinceId);
		this.makeZkzhForApply(year, provinceId, batchNo);
		this.updateKc(year, provinceId);
		this.updateZw(year, provinceId);
		this.initApplyZw(year, provinceId);
		
		resultMessage = this.distributeZwForApply(year, provinceId);// 本地区考生尽量在同一考点, 先按照顺序排位, 导致：同一地区的考生在同一考场
		if (null != resultMessage)
			return resultMessage;
		
		resultMessage = this.distributeZwForApplyAgain(year, provinceId, batchNo);// 上述基础上, 将考点已有考生打乱重排，实现：同一地区考生尽量不同考场
		if (null != resultMessage)
			return resultMessage;
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("provinceId = " + provinceId + " distribute cost: " + ((timeEnd - timeStart)) + "ms.");
		
		return new ResultMessage(ResultMessage.STATUS_SUCC, "分配准考证号、分配考场成功.");
	}
	
	/**
	 * 未分配考区的考生数据    
	 * @param year 考试年度
	 * @param provinceId 省份代码
	 * @return
	 * @throws HibernateException ResultMessage
	 * @author wjk 
	 */
	private ResultMessage checkApplyNoKq(String year, String provinceId) throws HibernateException {
		List list = this.getApplyNoKq(year, provinceId);
		if (null == list || list.isEmpty())
			return null;
		
		StringBuffer sb = new StringBuffer();
		for (int index = 0; index < list.size(); index++) {
			StringValue stringValue = (StringValue) list.get(index);
			String message = stringValue.getKey() + ":" + stringValue.getName() + ":" + stringValue.getValue() + "条";
			// System.out.println(message);
			sb.append("[").append(message).append("]");
		}
		return new ResultMessage(ResultMessage.STATUS_FAIL, "本省存在未分配考区的考生数据, " + sb.toString() + ", 分配考场失败.");
	}
	
	/**
	 * 判断本省待上报数据是否存在未分配考区的数据
	 * 
	 * @param year 考试年度
	 * @param provinceId 省份代码
	 * @return List
	 * @throws HibernateException
	 * @author wjk
	 */
	private List getApplyNoKq(String year, String provinceId) throws HibernateException {
		List applyNoKqList = new ArrayList();
		List kqList = this.getAllKq(year, provinceId);
		
		StringBuffer jjdqAllKaoqu = new StringBuffer();
		List dqList = new ArrayList();
		for (int index = 0; index < kqList.size(); index++) {
			ZfryzgksKd zfryzgksKd = (ZfryzgksKd) kqList.get(index);
			String[] jjdqArray = zfryzgksKd.getJjkqDistrictId().split(",");// 数据库存储格式 450100,450000,450300,450200,
			for (int i = 0; i < jjdqArray.length; i++) {
				dqList.add(jjdqArray[i]);
			}
		}
		Collections.sort(dqList);
		for (int i = 0; i < dqList.size(); i++) {
			jjdqAllKaoqu.append("'").append(dqList.get(i)).append("',");
		}
		if (dqList.isEmpty()) {
			jjdqAllKaoqu.append("'',");
		}
		applyNoKqList = this.zfksJDBCDAO.getApplyNoKq(year, provinceId, jjdqAllKaoqu.substring(0, jjdqAllKaoqu.length() - 1));
		
		return applyNoKqList;
	}
	
	/**
	 * 得到 做最大的申报批次   
	 * @param year
	 * @param provinceId
	 * @return int
	 * @author wjk 
	 */
	private int getNextMaxBatchNo(String year, String provinceId) {
		int maxBatchNo = this.zfksJDBCDAO.getMaxBatchNo(year, provinceId);
		return maxBatchNo + 1;
	}
	
	/**
	 * 为所有没有准考证号的考生分配准考证号
	 * @param year 年度
	 * @param provinceId 省份编码
	 * @throws HibernateException
	 * @author wjk
	 */
	private void makeZkzhForApply(String year, String provinceId, int batchNo) throws HibernateException {
		String yearPrefix = getCurrentYear();
		if (null != year && year.length() == 4)
			yearPrefix = year.substring(2, 4);
		
		List zfryzgksApplyList = this.getApplyWaitSubmitNoZkzh(year, provinceId);
		if (null == zfryzgksApplyList || zfryzgksApplyList.isEmpty())
			return;
		
		Map districtIdMaxXhMap = new HashMap();
		List zfryksMaxXhList = this.getDistrictIdMaxXh(year, provinceId);
		for (int index = 0; index < zfryksMaxXhList.size(); index++) {
			ZfryksXh zfryksXh = (ZfryksXh) zfryksMaxXhList.get(index);
			districtIdMaxXhMap.put(zfryksXh.getDistrictId(), zfryksXh.getMaxXh());
		}
		// 准考证号码按11位数字编排，1-2位是年度代码，3-4位是省别代码，5-6位是市别代码，7-8位是区县代码，9-11位为随机生成的考试序号
		for (int index = 0; index < zfryzgksApplyList.size(); index++) {
			ZfryzgksApply zfryzgksApply = (ZfryzgksApply) zfryzgksApplyList.get(index);
			if (null != districtIdMaxXhMap.get(zfryzgksApply.getSqdu())) {
				Integer maxXh = (Integer) districtIdMaxXhMap.get(zfryzgksApply.getSqdu());
				int nextXh = maxXh.intValue() + 1;
				zfryzgksApply.setZkzh(yearPrefix + zfryzgksApply.getSqdu() + this.getIntString(Integer.valueOf(nextXh)));
				districtIdMaxXhMap.put(zfryzgksApply.getSqdu(), Integer.valueOf(nextXh));
			} else {
				zfryzgksApply.setZkzh(yearPrefix + zfryzgksApply.getSqdu() + this.getIntString(Integer.valueOf(1)));
				districtIdMaxXhMap.put(zfryzgksApply.getSqdu(), Integer.valueOf(1));
			}
			zfryzgksApply.setSbpc(Integer.valueOf(batchNo));
			// System.out.println(zfryzgksApply.getId() + ":" + zfryzgksApply.getZkzh() + ":" + zfryzgksApply.getSbpc());
			zfryKcfpDAO.update(zfryzgksApply);
		}
		HibernateUtil.commitTransaction();
	}
	
	/**
	 * 根据设置的考点信息, 更新考场数据
	 * @param year 年度
	 * @param provinceId 省份代码
	 * @throws HibernateException
	 * @throws SQLException
	 * @author wjk
	 */
	private void updateKc(String year, String provinceId) throws HibernateException, SQLException {
		int maxKchExist = this.zfksJDBCDAO.getMaxKch(year, provinceId);
		List kcByKdList = new ArrayList();
		
		int maxKchForKd = 0;
		List kdList = this.getAllKd(year, provinceId);
		List kcList = this.getAllKc(year, provinceId);
		if (null != kdList) {
			for (int index = 0; index < kdList.size(); index++) {
				ZfryzgksKcgl zfryzgksKcgl = (ZfryzgksKcgl) kdList.get(index);
				// System.out.println(zfryzgksKcgl.getId() + ":" + zfryzgksKcgl.getKaoqu() + ":" + zfryzgksKcgl.getKaodian() + ":" + zfryzgksKcgl.getKcs() + ":" + zfryzgksKcgl.getKczw());
				maxKchForKd = maxKchForKd + zfryzgksKcgl.getKcs().intValue();
			}
		}
		
		for (int index = 0; index < kdList.size(); index++) {
			ZfryzgksKcgl zfryzgksKcgl = (ZfryzgksKcgl) kdList.get(index);
			int rangeNo = zfryzgksKcgl.getKcs().intValue();
			for (int no = 0; no < rangeNo; no++) {
				ZfryksKc zfryksKc = new ZfryksKc();
				zfryksKc.setKdId(zfryzgksKcgl.getId());
				zfryksKc.setKdxh(Integer.valueOf(no + 1));
				zfryksKc.setKczws(zfryzgksKcgl.getKczw());
				kcByKdList.add(zfryksKc);
			}
		}
		
		List kcForCreateList = new ArrayList();
		List kcForUpdateList = new ArrayList();
		List kcForDeleteList = new ArrayList();
		// 考场编号 全省不重复
		// 但为了记录某个考点的考场 是进行新增 or 删除, 特意记录了考点下的所有考场编号, 通过考点的考场数据、已有考场的考点编号 判断该考场是否进行删除 or 新增 or 更新(座位数量)
		for (int indexKcByKd = 0; indexKcByKd < kcByKdList.size(); indexKcByKd++) {
			boolean existDB = false;
			ZfryksKc zfryksKcByKd = (ZfryksKc) kcByKdList.get(indexKcByKd);
			ZfryksKc zfryksKcTemp = null;
			for (int indexKc = 0; indexKc < kcList.size(); indexKc++) {
				ZfryksKc zfryksKcDB = (ZfryksKc) kcList.get(indexKc);
				if ((zfryksKcByKd.getKdId().equals(zfryksKcDB.getKdId())) && (zfryksKcByKd.getKdxh().intValue() == zfryksKcDB.getKdxh().intValue())) {
					existDB = true;
					zfryksKcTemp = zfryksKcDB;
				}
			}
			if (existDB) {
				if (zfryksKcByKd.getKczws().intValue() != zfryksKcTemp.getKczws().intValue()) {
					zfryksKcTemp.setKczws(zfryksKcByKd.getKczws());
					kcForUpdateList.add(zfryksKcTemp);
				}
			} else {
				kcForCreateList.add(zfryksKcByKd);
			}
		}
		
		for (int indexKc = 0; indexKc < kcList.size(); indexKc++) {
			boolean existKcByKd = true;
			ZfryksKc zfryksKcDB = (ZfryksKc) kcList.get(indexKc);
			for (int indexKcByKd = 0; indexKcByKd < kcByKdList.size(); indexKcByKd++) {
				ZfryksKc zfryksKcByKd = (ZfryksKc) kcByKdList.get(indexKcByKd);
				if ((zfryksKcByKd.getKdId().equals(zfryksKcDB.getKdId())) && (zfryksKcByKd.getKdxh().intValue() == zfryksKcDB.getKdxh().intValue())) {
					existKcByKd = false;
				}
			}
			if (existKcByKd) {
				kcForDeleteList.add(zfryksKcDB);
			}
		}
		// 保存修改考点对应考场数量后的新增考场(考点的考场数量增加)
		for (int index = 0; index < kcForCreateList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcForCreateList.get(index);
			zfryksKc.setId(UUIDHexGenerator.getInstance().getID());
			zfryksKc.setKch(Integer.valueOf(maxKchExist + 1));
			maxKchExist++;
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " create");
			zfryKcfpDAO.save(zfryksKc);
		}
		// 更新修改考点对应考场的座位数量(考点的考场座位数变化)
		for (int index = 0; index < kcForUpdateList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcForUpdateList.get(index);
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " update");
			zfryKcfpDAO.update(zfryksKc);
		}
		// 删除已有考点的考场(考点的考场数量变小)
		for (int index = 0; index < kcForDeleteList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcForDeleteList.get(index);
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " delete");
			zfryKcfpDAO.delete(zfryksKc);
		}
		HibernateUtil.commitTransaction();
	}
	
	/**
	 * 更新座位情况
	 * @param year
	 * @param provinceId
	 * @throws HibernateException
	 * @throws SQLException
	 * @author wjk
	 */
	private void updateZw(String year, String provinceId) throws HibernateException, SQLException {
		List zwForCreateList = new ArrayList();
		List zwForDeleteList = new ArrayList();
		
		List kcList = this.getAllKc(year, provinceId);
		for (int index = 0; index < kcList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcList.get(index);
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " will be check zw");
			int kczwsQuery = zfryksKc.getKczws().intValue();
			int kczwsExist = 0;
			List zwList = this.zfryKcfpDAO.getZwByKcId(zfryksKc.getId());
			if (null != zwList && zwList.size() > 0) {
				kczwsExist = zwList.size();
			}
			if (kczwsQuery < kczwsExist) {
				for (int indexZw = kczwsQuery; indexZw < kczwsExist; indexZw++) {
					zwForDeleteList.add(zwList.get(indexZw));
				}
			} else if (kczwsQuery > kczwsExist) {
				for (int zwh = kczwsExist + 1; zwh <= kczwsQuery; zwh++) {
					ZfryksZw zfryksZw = new ZfryksZw();
					zfryksZw.setKcId(zfryksKc.getId());
					zfryksZw.setKch(zfryksKc.getKch());
					zfryksZw.setZwh(Integer.valueOf(zwh));
					zwForCreateList.add(zfryksZw);
				}
			}
		}
		
		List zwNoKcList = this.zfryKcfpDAO.getAllZwByBeletedKc(year, provinceId);
		zwForDeleteList.addAll(zwNoKcList);
		// 考场的座位数增加
		for (int index = 0; index < zwForCreateList.size(); index++) {
			ZfryksZw zfryksZw = (ZfryksZw) zwForCreateList.get(index);
			zfryksZw.setId(UUIDHexGenerator.getInstance().getID());
			// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getZwh() + " create");
			zfryKcfpDAO.save(zfryksZw);
		}
		// 考场的座位数减少
		for (int index = 0; index < zwForDeleteList.size(); index++) {
			ZfryksZw zfryksZw = (ZfryksZw) zwForDeleteList.get(index);
			// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getZwh() + " delete");
			zfryKcfpDAO.delete(zfryksZw);
		}
		HibernateUtil.commitTransaction();
	}
	
	/**
	 * 由于考场变化, 主要是考场座位数减少后, 导致部分已经分配座位的考生需要将已经分配的考场、座位号置空, 以便重新分配考场, 判断条件：考生对应的考场号、座位号 在座位表中没有(即已经被删除)
	 * 
	 * @param year
	 * @param provinceId
	 * @author wjk
	 */
	private void initApplyZw(String year, String provinceId) {
		this.zfksJDBCDAO.resetApplyKcHZwh(year, provinceId);
	}
	
	/**
	 * 为没有分配座位的考生进行座位分配
	 * 
	 * @param year
	 * @param provinceId
	 * @throws HibernateException
	 * @author wjk
	 */
	private ResultMessage distributeZwForApply(String year, String provinceId) throws HibernateException {
		List applyList = this.zfryKcfpDAO.getApplyNoZwh(year, provinceId);
		List zwhKcList = this.zfryKcfpDAO.getZwNoZkzh(year, provinceId);
		if (null == applyList || applyList.isEmpty())
			return null;
		
		if (null == zwhKcList || zwhKcList.isEmpty())
			return new ResultMessage(ResultMessage.STATUS_FAIL, "全省待分配考场人数[" + applyList.size() + "], 没有可以分配的考场座位.分配考场失败.");
		
		if (zwhKcList.size() < applyList.size())
			return new ResultMessage(ResultMessage.STATUS_FAIL, "全省待分配考场人数[" + applyList.size() + "], 可以分配的考场座位数[" + zwhKcList.size() + "].分配考场失败.");
		
		// System.out.println(year + ":" + provinceId + ", 全省待分配考场人数[" + applyList.size() + "], 可以分配的考场座位数[" + zwhKcList.size() + "].分配考场失败.");
		List kqList = this.getAllKq(year, provinceId);
		
		for (int index = 0; index < kqList.size(); index++) {
			ZfryzgksKd zfryzgksKd = (ZfryzgksKd) kqList.get(index);
			
			StringBuffer jjdqs = new StringBuffer();
			String[] jjdqArray = zfryzgksKd.getJjkqDistrictId().split(",");// 数据库存储格式 450100,450000,450300,450200,
			for (int i = 0; i < jjdqArray.length; i++) {
				jjdqs.append("'").append(jjdqArray[i]).append("',");
			}
			if (null == jjdqArray || jjdqArray.length == 0)
				jjdqs.append("'',");
			
			// 查询本考区待分配座位考生人员、未分配座位数
			int[] array = this.zfksJDBCDAO.getKqRsAndZws(year, provinceId, jjdqs.substring(0, jjdqs.length() - 1), zfryzgksKd.getId());
			int kqrs = array[0];
			int kqzws = array[1];
			
			if (kqzws < kqrs)
				return new ResultMessage(ResultMessage.STATUS_FAIL, zfryzgksKd.getKqMc() + "待分配考场人数[" + kqrs + "], 可以分配的考场座位数[" + kqzws + "].分配考场失败.");
			
			distributeZwForApplyEveryKq(year, provinceId, zfryzgksKd);
		}
		
		return null;
	}
	
	/**
	 * 以考区为单位, 分配本考区的考生, 按照 |考点座位数 - 报考地区|的最小值进行最优操作, 每次分配后提交数据库, 然后重新查询, 直到本考区的考生分配完毕; 否则递归
	 * 
	 * @param year
	 * @param provinceId
	 * @param zfryzgksKd
	 * @throws HibernateException
	 * @author wjk
	 */
	private void distributeZwForApplyEveryKq(String year, String provinceId, ZfryzgksKd zfryzgksKd) throws HibernateException {
		// 当全省没有未分配座位考生时, 也可以结束迭代
		List applyNoZwList = this.zfryKcfpDAO.getApplyNoZwh(year, provinceId);
		if (null == applyNoZwList || applyNoZwList.isEmpty())
			return;
		
		StringBuffer jjdqs = new StringBuffer();
		String[] jjdqArray = zfryzgksKd.getJjkqDistrictId().split(",");
		for (int i = 0; i < jjdqArray.length; i++) {
			jjdqs.append("'").append(jjdqArray[i]).append("',");
		}
		BestSqdqAndKdId bestSqdqAndKdId = this.zfksJDBCDAO.getBestSqdqAndKdId(year, provinceId, jjdqs.substring(0, jjdqs.length() - 1), zfryzgksKd.getId());
		// 所有本考区按照地区为单位进行考生座位分配后, 此查询结果为null, 则递归结束
		if (null == bestSqdqAndKdId)
			return;
		
		List zwNoZkzhKdIdList = this.zfryKcfpDAO.getZwByKdId(bestSqdqAndKdId.getKdId());
		List applyNoZwhList = this.zfryKcfpDAO.getApplyNoZwhBySqdq(year, provinceId, bestSqdqAndKdId.getSqdq());
		
		// 按照 |考点座位数 - 报考地区|最小进行计算, 可能会存在考点人数不能满足一个报考地区的情况, 此情况下, 以考点座位为主进行遍历, 否则以本地区考生列表进行遍历
		if (bestSqdqAndKdId.getCz() > 0) {
			for (int indexApply = 0; indexApply < applyNoZwhList.size(); indexApply++) {
				ZfryzgksApply zfryzgksApply = (ZfryzgksApply) applyNoZwhList.get(indexApply);
				ZfryksZw zfryksZw = (ZfryksZw) zwNoZkzhKdIdList.get(indexApply);
				zfryzgksApply.setKch(zfryksZw.getKch());
				zfryzgksApply.setZwh(zfryksZw.getZwh());
				ZfryksKc zfryksKc = this.getkdIdBykcId(zfryksZw.getKcId());
				zfryzgksApply.setKcid(zfryksKc.getKdId());
				zfryksZw.setZkzh(zfryzgksApply.getZkzh());
				// System.out.println(zfryzgksApply.getId() + ":" + zfryzgksApply.getXm() + ":" + zfryzgksApply.getSqdu() + ":" + zfryzgksApply.getZkzh() + ":" + zfryzgksApply.getKch() + ":" + zfryzgksApply.getZwh());
				// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getZwh() + ":" + zfryksZw.getZkzh());
				this.zfryKcfpDAO.update(zfryksZw);
				this.zfryKcfpDAO.update(zfryzgksApply);
			}
		} else if (bestSqdqAndKdId.getCz() < 0) {
			for (int indexZw = 0; indexZw < zwNoZkzhKdIdList.size(); indexZw++) {
				ZfryzgksApply zfryzgksApply = (ZfryzgksApply) applyNoZwhList.get(indexZw);
				ZfryksZw zfryksZw = (ZfryksZw) zwNoZkzhKdIdList.get(indexZw);
				zfryzgksApply.setKch(zfryksZw.getKch());
				zfryzgksApply.setZwh(zfryksZw.getZwh());
				ZfryksKc zfryksKc = this.getkdIdBykcId(zfryksZw.getKcId());
				zfryzgksApply.setKcid(zfryksKc.getKdId());
				zfryksZw.setZkzh(zfryzgksApply.getZkzh());
				// System.out.println(zfryzgksApply.getId() + ":" + zfryzgksApply.getXm() + ":" + zfryzgksApply.getSqdu() + ":" + zfryzgksApply.getZkzh() + ":" + zfryzgksApply.getKch() + ":" + zfryzgksApply.getZwh());
				// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getZwh() + ":" + zfryksZw.getZkzh());
				this.zfryKcfpDAO.update(zfryksZw);
				this.zfryKcfpDAO.update(zfryzgksApply);
			}
		}
		HibernateUtil.commitTransaction();
		distributeZwForApplyEveryKq(year, provinceId, zfryzgksKd);
	}
	
	/**
	 * 循环考点，打乱已经分配在本考点的考生，进行座位重排。该逻辑将导致已经安排考场的考生座位重拍，如果仅现本次上报的数据，需对上报批次进行序列号编号（明确上报批次）
	 * 
	 * @param year
	 * @param provinceId
	 * @param batchNo
	 * @throws HibernateException
	 * @author wjk
	 */
	private ResultMessage distributeZwForApplyAgain(String year, String provinceId, int batchNo) throws HibernateException {
		List kdList = this.getAllKd(year, provinceId);
		for (int indexKd = 0; indexKd < kdList.size(); indexKd++) {
			ZfryzgksKcgl zfryzgksKcgl = (ZfryzgksKcgl) kdList.get(indexKd);
			ResultMessage resultMessage = null;
			resultMessage = distributeZwForApplyEveryKd(zfryzgksKcgl.getId(), zfryzgksKcgl.getKaodian(), batchNo);
			if (null != resultMessage)
				return resultMessage;
		}
		
		return null;
	}
	
	/**
	 * 针对每个考点的考生，打乱已经分配在本考点的考生，进行座位重排，实现需求：统一地区的人尽量同一考点，但同一个考点的考生尽量不同的考场
	 * 
	 * @param kdId
	 * @throws HibernateException
	 * @author wjk
	 */
	private ResultMessage distributeZwForApplyEveryKd(String kdId, String kaodian, int batchNo) throws HibernateException {
		List zwHaveZkzhList = this.getZwHaveZkzhInKd(kdId, batchNo);
		List applyHaveZwInKdList = this.getApplyHaveZwInKd(kdId, batchNo);
		if (null == zwHaveZkzhList || null == applyHaveZwInKdList)
			return null;
		
		// zwHaveZkzhList和applyHaveZwInKdList的长度应该是一致的，如果不一致，抛出异常
		if (zwHaveZkzhList.size() != applyHaveZwInKdList.size())
			return new ResultMessage(ResultMessage.STATUS_FAIL, kaodian + "分配考场错误，请联系系统管理员！");
		
		for (int indexZw = 0; indexZw < zwHaveZkzhList.size(); indexZw++) {
			ZfryksZw zfryksZw = (ZfryksZw) zwHaveZkzhList.get(indexZw);
			
			int size = applyHaveZwInKdList.size();
			int randomIndex = (int) (Math.random() * size);// 经测试，基数、偶数情况下，都通过随机索引可以获取完所有的考生
			ZfryzgksApply zfryzgksApply = (ZfryzgksApply) applyHaveZwInKdList.get(randomIndex);
			
			zfryzgksApply.setKch(zfryksZw.getKch());
			zfryzgksApply.setZwh(zfryksZw.getZwh());
			ZfryksKc zfryksKc = this.getkdIdBykcId(zfryksZw.getKcId());
			zfryzgksApply.setKcid(zfryksKc.getKdId());
			
			zfryksZw.setZkzh(zfryzgksApply.getZkzh());
			// System.out.println(zfryzgksApply.getId() + ":" + zfryzgksApply.getXm() + ":" + zfryzgksApply.getSqdu() + ":" + zfryzgksApply.getZkzh() + ":" + zfryzgksApply.getKch() + ":" + zfryzgksApply.getZwh());
			// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getZwh() + ":" + zfryksZw.getZkzh());
			
			this.zfryKcfpDAO.update(zfryksZw);
			this.zfryKcfpDAO.update(zfryzgksApply);
			
			applyHaveZwInKdList.remove(zfryzgksApply);// 被分配的考生从队列中移除，下次随机获取将不再获取到该考生
		}
		HibernateUtil.commitTransaction();
		
		return null;
	}
	
	/**
	 * 当年度为空时, 默认设置为当前年度
	 * 
	 * @return String
	 * @author wjk
	 */
	private String getCurrentYear() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
		return simpleDateFormat.format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 *  根据 考场id 查询 考点 id
	 * @param kcid
	 * @return
	 * @throws HibernateException ZfryksKc
	 * @author wjk 
	 */
	private ZfryksKc getkdIdBykcId(String kcid) throws HibernateException {
		return zfryKcfpDAO.getkdIdBykcId(kcid);
	}
	
	/**
	 * 查询每个地区的当前最大准考证号
	 * 
	 * @param year
	 * @param provinceId
	 * @return maxXhList
	 * @author wjk
	 */
	private List getDistrictIdMaxXh(String year, String provinceId) {
		List maxXhList = this.zfksJDBCDAO.getDistrictIdMaxXh(year, provinceId);
		return maxXhList;
	}
	
	/**
	 * 查询所有待省级上报且未分配准考证号的考生
	 * @param year
	 * @param provinceId
	 * @return applyList
	 * @throws HibernateException
	 * @author wjk
	 */
	private List getApplyWaitSubmitNoZkzh(String year, String provinceId) throws HibernateException {
		List applyList = zfryKcfpDAO.getApplyNoZkzh(year, provinceId);
		return applyList;
	}
	
	/**
	 * 对准考证后三位进行补零
	 * @param no
	 * @return
	 * @author wjk
	 */
	private String getIntString(Integer no) {
		String str_m = String.valueOf(no);
		String str = "000";
		return str.substring(0, 3 - str_m.length()) + str_m;
	}
	
	/**
	 * 根据 年度 和 省份代码得到全部的考区     
	 * @param year
	 * @param provinceId
	 * @throws HibernateException List
	 * @author wjk 
	 */
	private List getAllKq(String year, String provinceId) throws HibernateException {
		List kqList = zfryKcfpDAO.getAllKq(year, provinceId);
		return kqList;
	}
	
	/**
	 * 得到全部的考点   
	 * @param year
	 * @param provinceId
	 * @return
	 * @throws HibernateException List
	 * @author wjk 
	 */
	private List getAllKd(String year, String provinceId) throws HibernateException {
		List kdList = zfryKcfpDAO.getAllKd(year, provinceId);
		return kdList;
	}
	
	/**得到 全部的 考场
	 * @param year
	 * @param provinceId
	 * @return
	 * @throws HibernateException List
	 * @author wjk 
	 */
	private List getAllKc(String year, String provinceId) throws HibernateException {
		List kdList = zfryKcfpDAO.getAllKc(year, provinceId);
		return kdList;
	}
	
 
	private List getZwHaveZkzhInKd(String kdId, int batchNo) throws HibernateException {
		List zwList = zfryKcfpDAO.getZwHaveZkzhInKd(kdId, batchNo);
		return zwList;
	}
	
	private List getApplyHaveZwInKd(String kdId, int batchNo) throws HibernateException {
		List applyList = zfryKcfpDAO.getApplyHaveZwInKd(kdId, batchNo);
		return applyList;
	}
}
