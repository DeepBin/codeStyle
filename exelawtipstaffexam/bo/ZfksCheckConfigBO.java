
package com.boco.eoms.exelawtipstaffexam.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.hibernate.HibernateException;

import com.boco.eoms.common.exception.CnfmMessageException;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.exelawtipstaffexam.dao.ZfksCheckConfigDAO;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksCheckLevelConfig;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksCheckConfig;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksCheckControl;

public class ZfksCheckConfigBO {
	ZfksCheckConfigDAO zfksCheckConfigDAO = new ZfksCheckConfigDAO();
	
	Map actionFormMap;
	
	public void setActionFormMap(Map map) {
		this.actionFormMap = map;
	}
	
	/**
	 * 获取申报流程
	 * @param districtId
	 * @return
	 * @throws HibernateException
	 * @throws CnfmMessageException String
	 * @author wjk 
	 * @Create Date 2017 下午3:22:13
	 */
	private String getGroupId(String districtId) throws HibernateException, CnfmMessageException {
		String groupId = null;
		if (StaticMethod.null2String(districtId).equals("")) { throw new CnfmMessageException("ERROE:无法获取当前操作人地区！"); }
		String sql = "from ZfryksCheckLevelConfig zfryksCheckLevelConfig where zfryksCheckLevelConfig.districtId='" + districtId + "'";
		List list = zfksCheckConfigDAO.query(sql);
		if (list == null || list.size() == 0) {
			String shengjiDistrictId = districtId.substring(0, 2) + "0000";
			if (districtId.equals(shengjiDistrictId)) {
				throw new CnfmMessageException("本省（直辖市）未设置申报流程，请与省（直辖市）级操作员联系！");
			} else {
				sql = "from ZfryksCheckLevelConfig zfryksCheckLevelConfig where zfryksCheckLevelConfig.districtId='" + shengjiDistrictId + "'";
				List shengjiList = zfksCheckConfigDAO.query(sql);
				if (shengjiList == null || shengjiList.size() == 0) {// 未设置
					throw new CnfmMessageException("本省（直辖市）未设置申报流程，请与省（直辖市）级操作员联系！");
				} else if (shengjiList.size() > 1) {// 系统存在多条记录
					throw new CnfmMessageException("本省（直辖市）设置申报流程错误，请与省（直辖市）级操作员联系！");
				} else {// 系统只存在一条记录
					ZfryksCheckLevelConfig zfryksCheckLevelConfig = (ZfryksCheckLevelConfig) shengjiList.get(0);
					groupId = zfryksCheckLevelConfig.getGroupId();
				}
			}
		} else if (list.size() > 1) {// 系统存在多条记录
			throw new CnfmMessageException("本省（直辖市）设置申报流程错误，请与省（直辖市）级操作员联系！");
		} else {// 系统只存在一条记录
			ZfryksCheckLevelConfig zfryksCheckLevelConfig = (ZfryksCheckLevelConfig) list.get(0);
			groupId = zfryksCheckLevelConfig.getGroupId();
		}
		return groupId;
	}
	
	/**
	 * 申报的流程控制
	 * 
	 * @param applyId
	 * @param districtId
	 * @param firstFlag
	 * @return
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 *             boolean
	 * @author wjk
	 * @Create Date 2015年12月30日 下午3:44:11
	 */
	public boolean checkConfigSave(String applyId, String districtId) throws HibernateException, CnfmMessageException {
		return checkConfigSave(applyId, districtId, getGroupId(districtId), true, "1");
	}
	
	/**
	 * 流程控制   
	 * @param applyId
	 * @param districtId
	 * @param groupId
	 * @param firstFlag
	 * @param stepNum
	 * @return
	 * @throws HibernateException
	 * @throws CnfmMessageException boolean
	 * @author wjk 
	 * @Create Date 2017 下午5:45:20
	 */
	public boolean checkConfigSave(String applyId, String districtId, String groupId, boolean firstFlag, String stepNum) throws HibernateException, CnfmMessageException {
		List gccList = new ArrayList();
		ZfksCheckConfigDAO zfksCheckConfigDAO = new ZfksCheckConfigDAO();
		String hSql = "";
		if (firstFlag) {
			hSql = new String(" from ZfryzgksCheckConfig zfryzgksCheckConfig where zfryzgksCheckConfig.groupId=" + groupId + " and zfryzgksCheckConfig.districtId = '" + districtId + "'");
		} else {
			hSql = new String(" from ZfryzgksCheckConfig zfryzgksCheckConfig where zfryzgksCheckConfig.groupId=" + groupId + " and zfryzgksCheckConfig.nowDistrictId = '" + districtId + "'");
		}
		gccList = zfksCheckConfigDAO.query(hSql);
		if ((gccList == null || gccList.size() == 0) && firstFlag) { throw new CnfmMessageException("未获得此地区的流程配置信息，请与管理员联系！"); }
		if (gccList.size() <= 0 && !firstFlag) {
			return true;
		} else {
			if (gccList.size() <= 0 && firstFlag) {
				// 如果该地区该种类型申请的审批流程不是单独配置的，那么查看是否是以省为单位统一配置。
				hSql = new String("from ZfryzgksCheckConfig zfryzgksCheckConfig where zfryzgksCheckConfig.groupId=" + groupId + " and zfryzgksCheckConfig.districtId = '" + districtId.substring(0, 2) + "'");
				gccList = zfksCheckConfigDAO.query(hSql);
			}
			if (gccList.size() > 0) {
				ZfryzgksCheckControl gccontrol = new ZfryzgksCheckControl();
				ZfryzgksCheckConfig gcconfig = (ZfryzgksCheckConfig) gccList.get(0);
				gccontrol.setApplyId(applyId);
				gccontrol.setDistrictId(gcconfig.getNowDistrictId());
				gccontrol.setNowStep(stepNum);
				gccontrol.setDelFlag(new Integer(0));
				if (firstFlag) {
					// 如果是第一次进入该方法，那么需要设置一个“已提交”的状态
					ZfryzgksCheckControl gcc = new ZfryzgksCheckControl();
					gcc.setNowStep("0");
					gcc.setNextStep(new Integer(1));
					gcc.setStepFlag(new Integer(1));
					gcc.setState("1");
					gcc.setDistrictId(districtId);
					gcc.setApplyId(applyId);
					zfksCheckConfigDAO.save(gcc);
					// 第一次进入，要设置即将审核的地区，故而要将即将审核的地区的步骤标志设置为0
					gccontrol.setStepFlag(new Integer(0));
				} else {
					gccontrol.setStepFlag(new Integer(1));
				}
				String checkState = "";
				if (gcconfig.getNowDistrictId().equals(gcconfig.getNextDistrictId())) {
					// 说明是审批
					checkState = "2";
					gccontrol.setState(checkState);
					gccontrol.setNextStep(new Integer(-1));
					zfksCheckConfigDAO.save(gccontrol);
					return true;
				} else {
					checkState = "2";
					gccontrol.setState(checkState);
					districtId = gcconfig.getNextDistrictId();
					int stepNumInt = new Integer(stepNum).intValue();
					gccontrol.setNextStep(new Integer(++stepNumInt));
					zfksCheckConfigDAO.save(gccontrol);
					return checkConfigSave(applyId, districtId, groupId, false, stepNumInt + "");
				}
			} else {
				return false;
			}
		}
	}
	
}
