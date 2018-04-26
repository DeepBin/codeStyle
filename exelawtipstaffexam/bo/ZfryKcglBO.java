
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
 * ��Ŀ���ƣ�CNFM    
 * �����ƣ�ZfryKcglBO    
 * ��������   ִ����Ա���Կ������� 
 * @version   1.0
 * @author wjk
 *
 */
public class ZfryKcglBO {
	private ZfksJDBCDAO zfksJDBCDAO = new ZfksJDBCDAO();
	
	private ZfryKcfpDAO zfryKcfpDAO = new ZfryKcfpDAO();
	
	public ResultMessage resetKC(String year, String provinceId) throws HibernateException, SQLException {
		ResultMessage resultMessage = new ResultMessage(ResultMessage.STATUS_SUCC, "���ÿ����ɹ�.");
		
		long timeStart = System.currentTimeMillis();
		
		try {
			this.zfksJDBCDAO.deleteZW(year, provinceId);
			this.zfksJDBCDAO.deleteKC(year, provinceId);
			this.zfksJDBCDAO.resetAllApplyKchAndZwh(year, provinceId);
		} catch (Exception e) {
			new ResultMessage(ResultMessage.STATUS_FAIL, "���ÿ���ʧ��.");
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("provinceId = " + provinceId + " resetKC cost: " + ((timeEnd - timeStart)) + "ms.");
		
		return resultMessage;
	}
	
	/**
	 * ��ȫʡ��������׼��֤�š����俼����λ
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
		// ��ȡ��ǰ������Σ���1��Ϊ��һ���κ�
		resultMessage = this.checkApplyNoKq(year, provinceId);
		if (null != resultMessage)
			return resultMessage;
		
		int batchNo = getNextMaxBatchNo(year, provinceId);
		this.makeZkzhForApply(year, provinceId, batchNo);
		this.updateKc(year, provinceId);
		this.updateZw(year, provinceId);
		this.initApplyZw(year, provinceId);
		
		resultMessage = this.distributeZwForApply(year, provinceId);// ����������������ͬһ����, �Ȱ���˳����λ, ���£�ͬһ�����Ŀ�����ͬһ����
		if (null != resultMessage)
			return resultMessage;
		
		resultMessage = this.distributeZwForApplyAgain(year, provinceId, batchNo);// ����������, ���������п����������ţ�ʵ�֣�ͬһ��������������ͬ����
		if (null != resultMessage)
			return resultMessage;
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("provinceId = " + provinceId + " distribute cost: " + ((timeEnd - timeStart)) + "ms.");
		
		return new ResultMessage(ResultMessage.STATUS_SUCC, "����׼��֤�š����俼���ɹ�.");
	}
	
	/**
	 * δ���俼���Ŀ�������    
	 * @param year �������
	 * @param provinceId ʡ�ݴ���
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
			String message = stringValue.getKey() + ":" + stringValue.getName() + ":" + stringValue.getValue() + "��";
			// System.out.println(message);
			sb.append("[").append(message).append("]");
		}
		return new ResultMessage(ResultMessage.STATUS_FAIL, "��ʡ����δ���俼���Ŀ�������, " + sb.toString() + ", ���俼��ʧ��.");
	}
	
	/**
	 * �жϱ�ʡ���ϱ������Ƿ����δ���俼��������
	 * 
	 * @param year �������
	 * @param provinceId ʡ�ݴ���
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
			String[] jjdqArray = zfryzgksKd.getJjkqDistrictId().split(",");// ���ݿ�洢��ʽ 450100,450000,450300,450200,
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
	 * �õ� �������걨����   
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
	 * Ϊ����û��׼��֤�ŵĿ�������׼��֤��
	 * @param year ���
	 * @param provinceId ʡ�ݱ���
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
		// ׼��֤���밴11λ���ֱ��ţ�1-2λ����ȴ��룬3-4λ��ʡ����룬5-6λ���б���룬7-8λ�����ش��룬9-11λΪ������ɵĿ������
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
	 * �������õĿ�����Ϣ, ���¿�������
	 * @param year ���
	 * @param provinceId ʡ�ݴ���
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
		// ������� ȫʡ���ظ�
		// ��Ϊ�˼�¼ĳ������Ŀ��� �ǽ������� or ɾ��, �����¼�˿����µ����п������, ͨ������Ŀ������ݡ����п����Ŀ����� �жϸÿ����Ƿ����ɾ�� or ���� or ����(��λ����)
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
		// �����޸Ŀ����Ӧ�������������������(����Ŀ�����������)
		for (int index = 0; index < kcForCreateList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcForCreateList.get(index);
			zfryksKc.setId(UUIDHexGenerator.getInstance().getID());
			zfryksKc.setKch(Integer.valueOf(maxKchExist + 1));
			maxKchExist++;
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " create");
			zfryKcfpDAO.save(zfryksKc);
		}
		// �����޸Ŀ����Ӧ��������λ����(����Ŀ�����λ���仯)
		for (int index = 0; index < kcForUpdateList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcForUpdateList.get(index);
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " update");
			zfryKcfpDAO.update(zfryksKc);
		}
		// ɾ�����п���Ŀ���(����Ŀ���������С)
		for (int index = 0; index < kcForDeleteList.size(); index++) {
			ZfryksKc zfryksKc = (ZfryksKc) kcForDeleteList.get(index);
			// System.out.println(zfryksKc.getId() + ":" + zfryksKc.getKch() + ":" + zfryksKc.getKdId() + ":" + zfryksKc.getKdxh() + ":" + zfryksKc.getKczws() + " delete");
			zfryKcfpDAO.delete(zfryksKc);
		}
		HibernateUtil.commitTransaction();
	}
	
	/**
	 * ������λ���
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
		// ��������λ������
		for (int index = 0; index < zwForCreateList.size(); index++) {
			ZfryksZw zfryksZw = (ZfryksZw) zwForCreateList.get(index);
			zfryksZw.setId(UUIDHexGenerator.getInstance().getID());
			// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getZwh() + " create");
			zfryKcfpDAO.save(zfryksZw);
		}
		// ��������λ������
		for (int index = 0; index < zwForDeleteList.size(); index++) {
			ZfryksZw zfryksZw = (ZfryksZw) zwForDeleteList.get(index);
			// System.out.println(zfryksZw.getId() + ":" + zfryksZw.getKch() + ":" + zfryksZw.getKcId() + ":" + zfryksZw.getZwh() + " delete");
			zfryKcfpDAO.delete(zfryksZw);
		}
		HibernateUtil.commitTransaction();
	}
	
	/**
	 * ���ڿ����仯, ��Ҫ�ǿ�����λ�����ٺ�, ���²����Ѿ�������λ�Ŀ�����Ҫ���Ѿ�����Ŀ�������λ���ÿ�, �Ա����·��俼��, �ж�������������Ӧ�Ŀ����š���λ�� ����λ����û��(���Ѿ���ɾ��)
	 * 
	 * @param year
	 * @param provinceId
	 * @author wjk
	 */
	private void initApplyZw(String year, String provinceId) {
		this.zfksJDBCDAO.resetApplyKcHZwh(year, provinceId);
	}
	
	/**
	 * Ϊû�з�����λ�Ŀ���������λ����
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
			return new ResultMessage(ResultMessage.STATUS_FAIL, "ȫʡ�����俼������[" + applyList.size() + "], û�п��Է���Ŀ�����λ.���俼��ʧ��.");
		
		if (zwhKcList.size() < applyList.size())
			return new ResultMessage(ResultMessage.STATUS_FAIL, "ȫʡ�����俼������[" + applyList.size() + "], ���Է���Ŀ�����λ��[" + zwhKcList.size() + "].���俼��ʧ��.");
		
		// System.out.println(year + ":" + provinceId + ", ȫʡ�����俼������[" + applyList.size() + "], ���Է���Ŀ�����λ��[" + zwhKcList.size() + "].���俼��ʧ��.");
		List kqList = this.getAllKq(year, provinceId);
		
		for (int index = 0; index < kqList.size(); index++) {
			ZfryzgksKd zfryzgksKd = (ZfryzgksKd) kqList.get(index);
			
			StringBuffer jjdqs = new StringBuffer();
			String[] jjdqArray = zfryzgksKd.getJjkqDistrictId().split(",");// ���ݿ�洢��ʽ 450100,450000,450300,450200,
			for (int i = 0; i < jjdqArray.length; i++) {
				jjdqs.append("'").append(jjdqArray[i]).append("',");
			}
			if (null == jjdqArray || jjdqArray.length == 0)
				jjdqs.append("'',");
			
			// ��ѯ��������������λ������Ա��δ������λ��
			int[] array = this.zfksJDBCDAO.getKqRsAndZws(year, provinceId, jjdqs.substring(0, jjdqs.length() - 1), zfryzgksKd.getId());
			int kqrs = array[0];
			int kqzws = array[1];
			
			if (kqzws < kqrs)
				return new ResultMessage(ResultMessage.STATUS_FAIL, zfryzgksKd.getKqMc() + "�����俼������[" + kqrs + "], ���Է���Ŀ�����λ��[" + kqzws + "].���俼��ʧ��.");
			
			distributeZwForApplyEveryKq(year, provinceId, zfryzgksKd);
		}
		
		return null;
	}
	
	/**
	 * �Կ���Ϊ��λ, ���䱾�����Ŀ���, ���� |������λ�� - ��������|����Сֵ�������Ų���, ÿ�η�����ύ���ݿ�, Ȼ�����²�ѯ, ֱ���������Ŀ����������; ����ݹ�
	 * 
	 * @param year
	 * @param provinceId
	 * @param zfryzgksKd
	 * @throws HibernateException
	 * @author wjk
	 */
	private void distributeZwForApplyEveryKq(String year, String provinceId, ZfryzgksKd zfryzgksKd) throws HibernateException {
		// ��ȫʡû��δ������λ����ʱ, Ҳ���Խ�������
		List applyNoZwList = this.zfryKcfpDAO.getApplyNoZwh(year, provinceId);
		if (null == applyNoZwList || applyNoZwList.isEmpty())
			return;
		
		StringBuffer jjdqs = new StringBuffer();
		String[] jjdqArray = zfryzgksKd.getJjkqDistrictId().split(",");
		for (int i = 0; i < jjdqArray.length; i++) {
			jjdqs.append("'").append(jjdqArray[i]).append("',");
		}
		BestSqdqAndKdId bestSqdqAndKdId = this.zfksJDBCDAO.getBestSqdqAndKdId(year, provinceId, jjdqs.substring(0, jjdqs.length() - 1), zfryzgksKd.getId());
		// ���б��������յ���Ϊ��λ���п�����λ�����, �˲�ѯ���Ϊnull, ��ݹ����
		if (null == bestSqdqAndKdId)
			return;
		
		List zwNoZkzhKdIdList = this.zfryKcfpDAO.getZwByKdId(bestSqdqAndKdId.getKdId());
		List applyNoZwhList = this.zfryKcfpDAO.getApplyNoZwhBySqdq(year, provinceId, bestSqdqAndKdId.getSqdq());
		
		// ���� |������λ�� - ��������|��С���м���, ���ܻ���ڿ���������������һ���������������, �������, �Կ�����λΪ�����б���, �����Ա����������б���б���
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
	 * ѭ�����㣬�����Ѿ������ڱ�����Ŀ�����������λ���š����߼��������Ѿ����ſ����Ŀ�����λ���ģ�������ֱ����ϱ������ݣ�����ϱ����ν������кű�ţ���ȷ�ϱ����Σ�
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
	 * ���ÿ������Ŀ����������Ѿ������ڱ�����Ŀ�����������λ���ţ�ʵ������ͳһ�������˾���ͬһ���㣬��ͬһ������Ŀ���������ͬ�Ŀ���
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
		
		// zwHaveZkzhList��applyHaveZwInKdList�ĳ���Ӧ����һ�µģ������һ�£��׳��쳣
		if (zwHaveZkzhList.size() != applyHaveZwInKdList.size())
			return new ResultMessage(ResultMessage.STATUS_FAIL, kaodian + "���俼����������ϵϵͳ����Ա��");
		
		for (int indexZw = 0; indexZw < zwHaveZkzhList.size(); indexZw++) {
			ZfryksZw zfryksZw = (ZfryksZw) zwHaveZkzhList.get(indexZw);
			
			int size = applyHaveZwInKdList.size();
			int randomIndex = (int) (Math.random() * size);// �����ԣ�������ż������£���ͨ������������Ի�ȡ�����еĿ���
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
			
			applyHaveZwInKdList.remove(zfryzgksApply);// ������Ŀ����Ӷ������Ƴ����´������ȡ�����ٻ�ȡ���ÿ���
		}
		HibernateUtil.commitTransaction();
		
		return null;
	}
	
	/**
	 * �����Ϊ��ʱ, Ĭ������Ϊ��ǰ���
	 * 
	 * @return String
	 * @author wjk
	 */
	private String getCurrentYear() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
		return simpleDateFormat.format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 *  ���� ����id ��ѯ ���� id
	 * @param kcid
	 * @return
	 * @throws HibernateException ZfryksKc
	 * @author wjk 
	 */
	private ZfryksKc getkdIdBykcId(String kcid) throws HibernateException {
		return zfryKcfpDAO.getkdIdBykcId(kcid);
	}
	
	/**
	 * ��ѯÿ�������ĵ�ǰ���׼��֤��
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
	 * ��ѯ���д�ʡ���ϱ���δ����׼��֤�ŵĿ���
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
	 * ��׼��֤����λ���в���
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
	 * ���� ��� �� ʡ�ݴ���õ�ȫ���Ŀ���     
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
	 * �õ�ȫ���Ŀ���   
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
	
	/**�õ� ȫ���� ����
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
