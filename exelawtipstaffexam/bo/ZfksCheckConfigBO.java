
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
	 * ��ȡ�걨����
	 * @param districtId
	 * @return
	 * @throws HibernateException
	 * @throws CnfmMessageException String
	 * @author wjk 
	 * @Create Date 2017 ����3:22:13
	 */
	private String getGroupId(String districtId) throws HibernateException, CnfmMessageException {
		String groupId = null;
		if (StaticMethod.null2String(districtId).equals("")) { throw new CnfmMessageException("ERROE:�޷���ȡ��ǰ�����˵�����"); }
		String sql = "from ZfryksCheckLevelConfig zfryksCheckLevelConfig where zfryksCheckLevelConfig.districtId='" + districtId + "'";
		List list = zfksCheckConfigDAO.query(sql);
		if (list == null || list.size() == 0) {
			String shengjiDistrictId = districtId.substring(0, 2) + "0000";
			if (districtId.equals(shengjiDistrictId)) {
				throw new CnfmMessageException("��ʡ��ֱϽ�У�δ�����걨���̣�����ʡ��ֱϽ�У�������Ա��ϵ��");
			} else {
				sql = "from ZfryksCheckLevelConfig zfryksCheckLevelConfig where zfryksCheckLevelConfig.districtId='" + shengjiDistrictId + "'";
				List shengjiList = zfksCheckConfigDAO.query(sql);
				if (shengjiList == null || shengjiList.size() == 0) {// δ����
					throw new CnfmMessageException("��ʡ��ֱϽ�У�δ�����걨���̣�����ʡ��ֱϽ�У�������Ա��ϵ��");
				} else if (shengjiList.size() > 1) {// ϵͳ���ڶ�����¼
					throw new CnfmMessageException("��ʡ��ֱϽ�У������걨���̴�������ʡ��ֱϽ�У�������Ա��ϵ��");
				} else {// ϵͳֻ����һ����¼
					ZfryksCheckLevelConfig zfryksCheckLevelConfig = (ZfryksCheckLevelConfig) shengjiList.get(0);
					groupId = zfryksCheckLevelConfig.getGroupId();
				}
			}
		} else if (list.size() > 1) {// ϵͳ���ڶ�����¼
			throw new CnfmMessageException("��ʡ��ֱϽ�У������걨���̴�������ʡ��ֱϽ�У�������Ա��ϵ��");
		} else {// ϵͳֻ����һ����¼
			ZfryksCheckLevelConfig zfryksCheckLevelConfig = (ZfryksCheckLevelConfig) list.get(0);
			groupId = zfryksCheckLevelConfig.getGroupId();
		}
		return groupId;
	}
	
	/**
	 * �걨�����̿���
	 * 
	 * @param applyId
	 * @param districtId
	 * @param firstFlag
	 * @return
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 *             boolean
	 * @author wjk
	 * @Create Date 2015��12��30�� ����3:44:11
	 */
	public boolean checkConfigSave(String applyId, String districtId) throws HibernateException, CnfmMessageException {
		return checkConfigSave(applyId, districtId, getGroupId(districtId), true, "1");
	}
	
	/**
	 * ���̿���   
	 * @param applyId
	 * @param districtId
	 * @param groupId
	 * @param firstFlag
	 * @param stepNum
	 * @return
	 * @throws HibernateException
	 * @throws CnfmMessageException boolean
	 * @author wjk 
	 * @Create Date 2017 ����5:45:20
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
		if ((gccList == null || gccList.size() == 0) && firstFlag) { throw new CnfmMessageException("δ��ô˵���������������Ϣ���������Ա��ϵ��"); }
		if (gccList.size() <= 0 && !firstFlag) {
			return true;
		} else {
			if (gccList.size() <= 0 && firstFlag) {
				// ����õ�����������������������̲��ǵ������õģ���ô�鿴�Ƿ�����ʡΪ��λͳһ���á�
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
					// ����ǵ�һ�ν���÷�������ô��Ҫ����һ�������ύ����״̬
					ZfryzgksCheckControl gcc = new ZfryzgksCheckControl();
					gcc.setNowStep("0");
					gcc.setNextStep(new Integer(1));
					gcc.setStepFlag(new Integer(1));
					gcc.setState("1");
					gcc.setDistrictId(districtId);
					gcc.setApplyId(applyId);
					zfksCheckConfigDAO.save(gcc);
					// ��һ�ν��룬Ҫ���ü�����˵ĵ������ʶ�Ҫ��������˵ĵ����Ĳ����־����Ϊ0
					gccontrol.setStepFlag(new Integer(0));
				} else {
					gccontrol.setStepFlag(new Integer(1));
				}
				String checkState = "";
				if (gcconfig.getNowDistrictId().equals(gcconfig.getNextDistrictId())) {
					// ˵��������
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
