
package com.boco.eoms.exelawtipstaffexam.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import net.sf.hibernate.HibernateException;

import org.apache.jcs.access.exception.CacheException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.codehaus.jettison.json.JSONArray;

import com.boco.eoms.attachment.dao.TawAttachmentDAO;
import com.boco.eoms.attachment.model.TawAttachment;
import com.boco.eoms.common.controller.SaveSessionBeanForm;
import com.boco.eoms.common.exception.CnfmMessageException;
import com.boco.eoms.common.print.util.PrintConfig;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.datainterface.util.FileConst;
import com.boco.eoms.datainterface.util.FileUtil;
import com.boco.eoms.datainterface.util.ZipUtil;
import com.boco.eoms.excelmanagenew.CopyFile;
import com.boco.eoms.excelmanagenew.JxlReadExcel;
import com.boco.eoms.excelmanagenew.PublicMethod;
import com.boco.eoms.excelmanagenew.ReadExcel;
import com.boco.eoms.exelawtipstaffexam.dao.ExelawtipstaffexamApplyDAO;
import com.boco.eoms.exelawtipstaffexam.dao.ZfksJDBCDAO;
import com.boco.eoms.exelawtipstaffexam.model.Kcfpqk;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksBmsj;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksKc;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksApply;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksCheckControl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksDqdwb;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksDwgl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksJgfs;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksKcgl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksKd;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksOperate;
import com.boco.eoms.exelawtipstaffexam.qo.ZfryzgksApplyQO;
import com.boco.eoms.exelawtipstaffexam.vo.ZfryzgksApplyVO;
import com.boco.eoms.exelawtipstaffexam.vo.ZfryzgksCheckControlVO;
import com.boco.eoms.fishing.util.TimeUtil;
import com.boco.eoms.jbzl.bo.TawDistrictBO;
import com.boco.eoms.jbzl.model.TawDistrict;
import com.boco.eoms.resmanage.entity.SysOpt;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExelawtipstaffexamBO {
	
	private Map actionFormMap;
	
	public void setActionFormMap(Map map) {
		this.actionFormMap = map;
	}
	
	private SaveSessionBeanForm saveSessionBeanForm;
	
	public void setSaveSessionBeanForm(SaveSessionBeanForm saveSessionBeanForm) {
		this.saveSessionBeanForm = saveSessionBeanForm;
	}
	
	public ExelawtipstaffexamBO() {
		
	}
	
	public ExelawtipstaffexamBO( SaveSessionBeanForm saveSessionBeanForm ) {
		this.saveSessionBeanForm = saveSessionBeanForm;
	}
	
	public ExelawtipstaffexamBO( SaveSessionBeanForm saveSessionBeanForm, Map actionFormMap ) {
		this.actionFormMap = actionFormMap;
		this.saveSessionBeanForm = saveSessionBeanForm;
	}
	
	/***
	 * ���汨�� ��Ϣ
	 * 
	 * @param actionFormMap2
	 * @return ZfryzgksApply
	 * @author glw
	 * @param zfryzgksApply
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����9:56:00
	 */
	public ZfryzgksApply saveZfryzgksApply(ZfryzgksApply zfryzgksApply, Map actionFormMap2) throws CnfmMessageException, HibernateException {
		// ���鱨��ʱ��
		String districId = StaticMethod.nullObject2String(actionFormMap2.get("sqdu"));
		String nd = actionFormMap2.get("nd").toString();
		//ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
		//zfryksBmsj = this.checkApplyTime(districId, nd);
		ZfryzgksDqdwb zfryzgksDqdwb =new ZfryzgksDqdwb();
		zfryzgksDqdwb = this.getzfryzgksDqdwb(districId);
		/*if (StaticMethod.nullObject2String(zfryzgksDqdwb.getId()).equals("")) { throw new com.boco.eoms.common.exception.CnfmMessageException("���뿼�Ե��������ڵ�λ�ĵ�λ����û����д�����ܽ��б���"); }*/
		//if (zfryksBmsj == null) { throw new com.boco.eoms.common.exception.CnfmMessageException("���ڱ���ʱ���ڣ����ܽ��б���"); }
		//this.checkTime(zfryksBmsj);
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		if (!StaticMethod.nullObject2String(actionFormMap2.get("id")).equals("")) {
			zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamDAO.load(StaticMethod.nullObject2String(actionFormMap2.get("id")), ZfryzgksApply.class);
			MyBeanUtils.copyProperties(zfryzgksApply, actionFormMap2, null);
			zfryzgksApply.setUpdateTime(java.sql.Timestamp.valueOf(TimeUtil.getCurrentTime()));
			exelawtipstaffexamDAO.executeSql("delete from zfryks_check_control where apply_id ='"+zfryzgksApply.getId()+"'");
			new ZfksCheckConfigBO().checkConfigSave(zfryzgksApply.getId(), zfryzgksApply.getSqdu());
			exelawtipstaffexamDAO.update(zfryzgksApply);
		} else {
			MyBeanUtils.copyProperties(zfryzgksApply, actionFormMap2, null);
			zfryzgksApply.setInsertTime(java.sql.Timestamp.valueOf(TimeUtil.getCurrentTime()));
			exelawtipstaffexamDAO.save(zfryzgksApply);
			new ZfksCheckConfigBO().checkConfigSave(zfryzgksApply.getId(), zfryzgksApply.getSqdu());
		}
		return zfryzgksApply;
	}
	
	/***
	 * ���汨�� ��Ϣ
	 * 
	 * @param actionFormMap2
	 * @return ZfryzgksApply
	 * @author glw
	 * @param zfryzgksApply
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����9:56:00
	 */
	public ZfryzgksApply updateZfryzgksApply(ZfryzgksApply zfryzgksApply, Map actionFormMap2) throws CnfmMessageException, HibernateException {
		// ���鱨��ʱ��
		String districId = StaticMethod.nullObject2String(actionFormMap2.get("sqdu"));
		ZfryzgksDqdwb zfryzgksDqdwb =new ZfryzgksDqdwb();
		zfryzgksDqdwb = this.getzfryzgksDqdwb(districId);
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		if (!StaticMethod.nullObject2String(actionFormMap2.get("id")).equals("")) {
			zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamDAO.load(StaticMethod.nullObject2String(actionFormMap2.get("id")), ZfryzgksApply.class);
			if(null == actionFormMap2.get("nsyzfzh") || actionFormMap2.get("nsyzfzh").equals("")){
				actionFormMap2.remove("nsyzfzh");// �Ὣ�������ÿ�
			}
			MyBeanUtils.copyProperties(zfryzgksApply, actionFormMap2, null);
			zfryzgksApply.setUpdateTime(java.sql.Timestamp.valueOf(TimeUtil.getCurrentTime()));
			//zfryzgksApply.setDwjb(zfryzgksDqdwb.getDwjb());
			//zfryzgksApply.setGzdwxz(zfryzgksDqdwb.getDwxz());
			String bakSql = "insert into zfryks_apply_history select newid(), getdate(), * from zfryks_apply where id ='"+zfryzgksApply.getId()+"'";
			System.out.println(bakSql);
			exelawtipstaffexamDAO.executeSql(bakSql);
			exelawtipstaffexamDAO.update(zfryzgksApply);
		} 
		
		return zfryzgksApply;
	}
	
	/**
	 * T�жϸõ�λ�ĵ�λ�����Ƿ���д    
	 * @param districId
	 * @return ZfryzgksDqdwb
	 * @author glw 
	 * @Create Date 2017 ����6:18:09
	 */
	private ZfryzgksDqdwb checkdanwei(String districId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * �������֤�Ų�ѯ����������Ϣ
	 * 
	 * @param kssfzhm
	 * @return ZfryzgksApply
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����3:32:14
	 */
	public ZfryzgksApply getzfryzgksApplyBysfzh(String kssfzhm) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		String sql = "from ZfryzgksApply zfryzgksApply where sfzh = '" + kssfzhm + "'";
		List zfryzgksApplyList = exelawtipstaffexamDAO.query(sql);
		if (zfryzgksApplyList.size() > 0) {
			zfryzgksApply = (ZfryzgksApply) zfryzgksApplyList.get(0);
		}
		return zfryzgksApply;
	}
	
	/***
	 * �鿴������Ϣ
	 * 
	 * @param id
	 * @return ZfryzgksApply
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����3:57:47
	 */
	public ZfryzgksApply chakanZfryzgksApply(String id) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		if (!StaticMethod.nullObject2String(id).equals("")) {
			zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamDAO.load(StaticMethod.nullObject2String(id), ZfryzgksApply.class);
		}
		return zfryzgksApply;
	}
	
	/**
	 * �������� �������� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����3:20:12
	 */
	public void saveApply() throws CnfmMessageException, HibernateException {
		// ��ѯ���Ƿ��ڱ���ȵı���������
		String districId = saveSessionBeanForm.getDistrictId();
		String nd = actionFormMap.get("nd").toString();
		ZfryksBmsj zfryksBmsj = this.checkApplyTime(districId, nd);
		if (zfryksBmsj == null) { throw new com.boco.eoms.common.exception.CnfmMessageException("δ�ܻ�ȡ�����Բ���������ϵ����Ա���ÿ��Բ���"); }
		this.checkTime(zfryksBmsj);
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		MyBeanUtils.copyProperties(zfryzgksApply, actionFormMap, null);
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		exelawtipstaffexamDAO.save(zfryzgksApply);
		// ��������
		new ZfksCheckConfigBO().checkConfigSave(zfryzgksApply.getId(), zfryzgksApply.getSqdu());
	}
	
	/**
	 * ��ѯ�Ƿ��������˱�����ʼʱ����걨��ȡ� ��������� �ж��Ƿ��ڱ���ʱ����
	 * 
	 * @param districId
	 *            void
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����4:00:57
	 */
	private ZfryksBmsj checkApplyTime(String districId, String nd) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
		String nowDistrictIdhql = "from  ZfryksBmsj zfryksBmsj where zfryksBmsj.districtId ='" + districId + "' and zfryksBmsj.niandu='" + nd + "'";
		List list = exelawtipstaffexamApplyDAO.query(nowDistrictIdhql);
		if (list != null && list.size() > 0) {
			if (list.size() == 1) {
				MyBeanUtils.copyProperties(zfryksBmsj, list.get(0), null);
			} else {
				throw new CnfmMessageException("���ڱ���ʱ���ڣ����ܽ��б�����");
			}
		} else {
			String shengDistrictIdHql = "from  ZfryksBmsj zfryksBmsj where zfryksBmsj.districtId ='" + districId.substring(0, 2) + "0000' and zfryksBmsj.niandu='" + nd + "'";
			list = exelawtipstaffexamApplyDAO.query(shengDistrictIdHql);
			if (list != null && list.size() > 0) {
				if (list.size() == 1) {
					MyBeanUtils.copyProperties(zfryksBmsj, list.get(0), null);
				} else {
					throw new CnfmMessageException("���ڱ���ʱ���ڣ����ܽ��б�����");
				}
			} else {
				String shengDistrictIdHql1 = "from  ZfryksBmsj zfryksBmsj where zfryksBmsj.districtId ='000000' and zfryksBmsj.niandu='" + nd + "'";
				list = exelawtipstaffexamApplyDAO.query(shengDistrictIdHql1);
				if (list.size() == 1) {
					MyBeanUtils.copyProperties(zfryksBmsj, list.get(0), null);
				} else {
					throw new CnfmMessageException("���ڱ���ʱ���ڣ����ܽ��б�����");
				}
			}
		}
		return zfryksBmsj;
	}
	
	/**
	 * ��鵱ǰʱ���Ƿ��ڱ���ʱ����
	 * 
	 * @param zfryksBmsj
	 *            void
	 * @author wjk
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����4:25:20
	 */
	private void checkTime(ZfryksBmsj zfryksBmsj) throws CnfmMessageException {
		Timestamp nowTime = StaticMethod.getTimestamp();
		if (nowTime.after(zfryksBmsj.getApplyBegdate()) && nowTime.before(zfryksBmsj.getApplyEnddate())) { return; }
		throw new com.boco.eoms.common.exception.CnfmMessageException("���ڿ��Ա��������ڣ����ܽ��б�����");
	}
	
	/**
	 * ��ѯ����˵��б�
	 * 
	 * @param pagePra
	 * @param districtId
	 * @return List
	 * @author wjk
	 * @throws HibernateException
	 * @Create Date 2017 ����8:57:49
	 */
	public List performCheckInit(int[] pagePra, String districtId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String hSql = "from ZfryzgksApply zfryzgksApply where zfryzgksApply.id in( select zfryzgksCheckControl.applyId from ZfryzgksCheckControl zfryzgksCheckControl where  zfryzgksCheckControl.districtId='" + districtId + "' and zfryzgksCheckControl.state =2 and zfryzgksCheckControl.stepFlag=0 and zfryzgksCheckControl.delFlag=0) order by zfryzgksApply.sqdu, zfryzgksApply.gzdwmc, zfryzgksApply.sfzh ";
		List list = exelawtipstaffexamApplyDAO.query(hSql, pagePra);
		List returnList = new ArrayList();
		
		if (!returnList.isEmpty() || returnList.size() > 0) {
			ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
			for (int i = 0; i < returnList.size(); i++) {
				zfryzgksApply = (ZfryzgksApply) returnList.get(i);
				// MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				list.add(zfryzgksApply);
			}
		}
		return list;
	}
	
	/**
	 * ��˷��� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws HibernateException 
	 * @Create Date 2017 ����10:10:26
	 */
	public void checkMoreYes() throws IllegalAccessException, InvocationTargetException, CnfmMessageException, HibernateException {
		// ��õ�ǰ�����û��Ļ�����Ϣ������
		//�����Ƿ������ʱ���ڣ�
		Calendar a = Calendar.getInstance();
		String nd = String.valueOf(a.get(Calendar.YEAR));
		ZfryksBmsj zfryksBmsj = this.checkApplyTime(saveSessionBeanForm.getDistrictId(), nd);
		if(zfryksBmsj == null){ throw new com.boco.eoms.common.exception.CnfmMessageException("û�в�ѯ������ʱ����Ϣ��");}
		if(!saveSessionBeanForm.getDistrictRemark().equals("1-")){
		      this.checkCheckTime(zfryksBmsj);
		}
		List jdbcSqlList = new ArrayList();
		String deptId = new String(new Integer(saveSessionBeanForm.getWrf_DeptID()).toString());
		String currentTime = TimeUtil.getCurrentTime();
		// ���sqlId
		String sqlId = getSqlId("checkMore");
		// �������̿��Ʊ� ����ǰ��˵��� �Ŀ�����Ϣ ����Ϊ ���ͨ��
		String applyShbjJDBCSQL = " update zfryks_apply set shbj =1 where id in " + sqlId;
		String zfksCheckControlJDBCSQL = " update zfryks_check_control set state=3 where district_id='" + saveSessionBeanForm.getDistrictId() + "' and step_flag=0 and apply_id in " + sqlId;
		String zfksCheckInfoJDBCSQL = " insert into zfryks_check_info (id,apply_id,check_district,check_result,check_idea,incraece_time,reduce_time,remark,DEL_FLAG,check_dept,check_time) select newid(),id,'" + saveSessionBeanForm.getDistrictId() + "',0,'" + "�������ͨ��" + "',0,0,'',0,'" + deptId + "','" + currentTime + "'  from zfryks_apply where id in " + sqlId;
		String zfksOperateJDBCSQL = " insert into zfryks_operate  (id,DEL_FLAG,apply_id,userid,district_id,content,time,remark)  select newid(),0,id,'" + saveSessionBeanForm.getWrf_UserID() + "','" + saveSessionBeanForm.getDistrictId() + "','�������ͨ��','" + currentTime + "','' from zfryks_apply where id in " + sqlId;
		ZfksJDBCDAO jdbcDAO = new ZfksJDBCDAO();
		jdbcSqlList.add(zfksCheckControlJDBCSQL);
		jdbcSqlList.add(zfksCheckInfoJDBCSQL);
		jdbcSqlList.add(zfksOperateJDBCSQL);
		jdbcSqlList.add(applyShbjJDBCSQL);
		jdbcDAO.executeJDBCSqlList(jdbcSqlList);
		
	}
	
	/**
	 * ��ѯ�Ƿ������������    
	 * @param zfryksBmsj void
	 * @author wjk 
	 * @throws CnfmMessageException 
	 * @Create Date 2017 ����9:59:40
	 */
	private void checkCheckTime(ZfryksBmsj zfryksBmsj) throws CnfmMessageException {
		Timestamp nowTime = StaticMethod.getTimestamp();
		if(nowTime.after(zfryksBmsj.getShjssj())){
			throw new com.boco.eoms.common.exception.CnfmMessageException("���ڵ�ǰ��ȵ����ʱ���ڣ����ܽ�����ˡ���ǰ��ȵ���˽�ֹʱ����"+zfryksBmsj.getShjssj());
		}
	}
	
	/**
	 * ��ѯ�Ƿ����ϱ�������    
	 * @param zfryksBmsj void
	 * @author wjk 
	 * @throws CnfmMessageException 
	 * @Create Date 2017 ����9:59:40
	 */
	private void checkCheckTimeForShangbao(ZfryksBmsj zfryksBmsj) throws CnfmMessageException {
		Timestamp nowTime = StaticMethod.getTimestamp();
		if(nowTime.after(zfryksBmsj.getSbjssj())){
			throw new com.boco.eoms.common.exception.CnfmMessageException("���ڵ�ǰ��ȵ��ϱ�ʱ���ڣ����ܽ����ϱ�����ǰ��ȵ��ϱ���ֹʱ����"+zfryksBmsj.getSbjssj());
		}
	}

	/**
	 * ����id
	 * 
	 * @param string
	 * @return String
	 * @author dwl
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����10:12:22
	 */
	private String getSqlId(String operate) throws IllegalAccessException, InvocationTargetException, CnfmMessageException {
		StringTokenizer token = new StringTokenizer(actionFormMap.get("zipcode").toString(), "|");
		String sqlId = "(";
		String remarkInfo = "";
		while (token.hasMoreTokens()) {
			String getID = token.nextToken();
			sqlId += "'" + getID + "',";
			remarkInfo += "''" + getID + "'',";
		}
		if (sqlId.indexOf(",") > 0) {
			sqlId = sqlId.substring(0, sqlId.length() - 1);
		}
		if (remarkInfo.indexOf(",") > 0) {
			remarkInfo = remarkInfo.substring(0, remarkInfo.length() - 1);
		}
		sqlId += ")";
		return sqlId;
	}
	
	/**
	 * ������˲�ͨ�� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Create Date 2017 ����10:37:26
	 */
	public void checkMoreNo() throws IllegalAccessException, InvocationTargetException, CnfmMessageException {
		List jdbcSqlList = new ArrayList();
		String checkIdea = "��˲�ͨ��";
		if (!StaticMethod.nullObject2String(actionFormMap.get("checkIdea")).equals("")) {
			checkIdea += "��" + StaticMethod.nullObject2String(actionFormMap.get("checkIdea"));
		}
		String deptId = new String(new Integer(saveSessionBeanForm.getWrf_DeptID()).toString());
		String currentTime = TimeUtil.getCurrentTime();
		String sqlId = getSqlId("checkMore");
		// ������Ŀǰ��������Ϊ�ύ����
		String zfksCheckControlFirstCheckJdbcSql = " update zfryks_check_control set state=2,step_flag=0 where now_step=1 and apply_id in " + sqlId;
		// ��״̬����Ϊ����״̬
		String zfksCheckControlJdbcSql = " update zfryks_check_control set state=2,step_flag=1 where now_step >1 and apply_id in " + sqlId;
		// ���������Ϣ��
		String zfksCheckInfoJdbcsql = " insert into zfryks_check_info select newid(),id,'" + saveSessionBeanForm.getDistrictId() + "',0,'" + checkIdea + "',0,0,'',0,'" + deptId + "','" + currentTime + "',0,'',0  from zfryks_apply where id in " + sqlId + " ";
		// ���������
		String zfksOperateJdbcSql = " insert into zfryks_operate(id,del_flag,apply_id,userid,district_id,content,time,remark)  select newid(),0,id,'" + saveSessionBeanForm.getWrf_UserID() + "','" + saveSessionBeanForm.getDistrictId() + "','" + checkIdea + "','" + currentTime + "','' from zfryks_apply where id in " + sqlId;
		// �����˲�ͨ������
		ZfksJDBCDAO jdbcDAO = new ZfksJDBCDAO();
		jdbcSqlList.add(zfksCheckControlFirstCheckJdbcSql);
		jdbcSqlList.add(zfksCheckControlJdbcSql);
		jdbcSqlList.add(zfksCheckInfoJdbcsql);
		jdbcSqlList.add(zfksOperateJdbcSql);
		jdbcDAO.executeJDBCSqlList(jdbcSqlList);
		
	}
	
	/**
	 * ���ϱ��б�
	 * 
	 * @param pagePra
	 * @param districtId
	 * @return List
	 * @author wjk
	 * @throws HibernateException
	 * @Create Date 2017 ����1:38:06
	 */
	public List performCheckUpInit(int[] pagePra, String districtId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String hql = "from ZfryzgksApply za ,ZfryzgksCheckControl zcc where zcc.applyId=za.id and zcc.districtId='" + districtId + "' and zcc.state in(3,4) and zcc.stepFlag=0 and zcc.delFlag=0 order by za.sqdu,za.gzdwmc,za.sfzh";
		return exelawtipstaffexamApplyDAO.query(hql, pagePra);
	}
	/**
	 * ���ϱ��б�
	 * 
	 * @param pagePra
	 * @param districtId
	 * @return List
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException 
	 * @Create Date 2017 ����1:38:06
	 */
	public List performCheckUpqueryInit(int[] pagePra, String districtId) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksApplyQO zfryzgksApplyQO = new ZfryzgksApplyQO();
		MyBeanUtils.copyProperties(zfryzgksApplyQO, actionFormMap, null);
		String hql = zfryzgksApplyQO.getshangbaoQueryHql(actionFormMap.get("sqdu").toString(), saveSessionBeanForm.getDistrictId());
		return exelawtipstaffexamApplyDAO.query(hql, pagePra);
	}
	/**
	 * ��ѯ�Ƿ��Ǻ˶�����
	 * 
	 * @param districtId
	 * @return boolean
	 * @author wjk
	 * @throws HibernateException
	 * @Create Date 2017 ����1:40:54
	 */
	public boolean isHedingDistrict(String districtId) throws HibernateException {
		String hSql = " from ZfryzgksCheckConfig zfryzgksCheckConfig where zfryzgksCheckConfig.finalDistrictId ='" + districtId + "'";
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		List list = exelawtipstaffexamApplyDAO.query(hSql);
		if (list != null && list.size() > 0) { return true; }
		return false;
	}
	
	/**
	 * �ж��Ƿ���������е�����
	 * 
	 * @param districtId
	 * @return int
	 * @author wjk
	 * @throws HibernateException
	 * @Create Date 2017 ����1:42:06
	 */
	public int isCanCheckUp(String districtId) throws HibernateException {
		String hql = "select * from ZfryzgksApply za ,ZfryzgksCheckControl zcc where zcc.applyId=za.id and zcc.districtId='" + saveSessionBeanForm.getDistrictId() + "' and zcc.state in(1,2) and zcc.stepFlag=0 and zcc.delFlag=0";
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		int count = exelawtipstaffexamApplyDAO.count(hql);
		int flag = 0;
		if (count > 0) {
			flag = -1;
		}
		return flag;
	}
	
	/**
	 * �õ��������
	 * 
	 * @param districtId
	 * @return String
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException 
	 * @Create Date 2017 ����2:05:34
	 */
	public String getYear(String districtId) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String shengHql =" from ZfryksBmsj zfryksBmsj where  zfryksBmsj.districtId ='"+districtId.substring(0, 2)+"0000'";
		String hql = "from  ZfryksBmsj zfryksBmsj where zfryksBmsj.districtId ='000000' ";
		List shenglist = exelawtipstaffexamApplyDAO.query(shengHql);
		List list = exelawtipstaffexamApplyDAO.query(hql);
		ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
		if (shenglist.size() > 0 || !shenglist.isEmpty()) {
			zfryksBmsj = (ZfryksBmsj) shenglist.get(0);
		}else if(list.size() > 0 || !list.isEmpty()){
			zfryksBmsj = (ZfryksBmsj) list.get(0);
		}else{
			throw new com.boco.eoms.common.exception.CnfmMessageException("��ȡ�������ʧ�ܣ�");
		}
		return zfryksBmsj.getNiandu();
	}
	
	/**
	 * �ϱ����� ������һ������״̬Ϊ ����� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @throws SQLException 
	 * @Create Date 2017 ����2:56:50
	 */
	public void checkUpMore() throws HibernateException, CnfmMessageException, SQLException {
		List jdbcSqlList = new ArrayList();
		//У�鵱ǰʱ���Ƿ񳬹�������ϱ���ʱ�䣬�������������ϱ�
		if(!saveSessionBeanForm.getDistrictRemark().equals("1-")){
			String districtId = saveSessionBeanForm.getDistrictId();
			Calendar c = Calendar.getInstance();
			String nd = String.valueOf(c.get(Calendar.YEAR));
			ZfryksBmsj zfryksBmsj = this.getBmsj(nd, districtId);
			this.checkCheckTimeForShangbao(zfryksBmsj);
		}
		if (saveSessionBeanForm.getDistrictRemark().equals("3-1")) {// ʡ���ϱ�֮ǰ�����Ƿ���ڿ���
			String year = this.getYear(saveSessionBeanForm.getDistrictId());
			String districtIds = saveSessionBeanForm.getDistrictId().substring(0, 2);
			ZfryKcglBO zfryKcglBO = new ZfryKcglBO();
			zfryKcglBO.distribute(year, districtIds + "0000");
			this.checkKc();
		}
		String deptId = new String(new Integer(saveSessionBeanForm.getWrf_DeptID()).toString());
		String currentTime = TimeUtil.getCurrentTime();
		// ������һ����
		if(saveSessionBeanForm.getDistrictRemark().equals("1-1")){
			String applyUpdatesql =" update zfryks_apply set hdbj = 1 where id in(select apply_id from zfryks_check_control where district_id='" + saveSessionBeanForm.getDistrictId() + "' and state in('3','4'))";
			jdbcSqlList.add(applyUpdatesql);
		}
		String zfksCheckControlNextStepJdbcSql = "update zfryks_check_control set state=3 where id in(select a.id from zfryks_check_control as a where  exists (select 1 from (select * from zfryks_check_control where district_id='" + saveSessionBeanForm.getDistrictId() + "' and step_flag=0  and state in('3','4') ) as b where  a.now_step=b.next_step and a.apply_id=b.apply_id))";
		// ��������һ��Ϊ���ϱ�״̬
		String zfksCheckControlNextNextStepJdbcSql = "update zfryks_check_control set  step_flag=0 where id in(select a.id from zfryks_check_control as a where  exists (select 1 from (select * from zfryks_check_control where district_id='" + saveSessionBeanForm.getDistrictId() + "' and step_flag=0  and state in('3','4') ) as b where  a.now_step=b.next_step and a.apply_id=b.apply_id))";
		// ���µ�ǰ��¼
		String zfksCheckControlNowStepJdbcSql = " update zfryks_check_control set state=5,step_flag=1 where district_id='" + saveSessionBeanForm.getDistrictId() + "' and step_flag=0 and state in('3','4') ";
		// ��¼������¼��
		String zfksOperateJdbcSql = " insert into zfryks_operate(id,del_flag,apply_id,userid,district_id,content,time,remark) select newid(),0,apply_id,'" + saveSessionBeanForm.getWrf_UserID() + "','" + saveSessionBeanForm.getDistrictId() + "','" + "�ϱ�" + "','" + currentTime + "','" + "" + "' from zfryks_check_control where district_id='" + saveSessionBeanForm.getDistrictId() + "' and state in('3','4') ";
		// ��¼�����Ϣ��
		String zfksCheckInfoJdbcSql = " insert into zfryks_check_info (id,apply_id,check_district,check_result,check_idea,incraece_time,reduce_time,remark,DEL_FLAG,check_dept,check_time) select newid(),apply_id,'" + saveSessionBeanForm.getDistrictId() + "',0,'" + "�ϱ�" + "',0,0,'',0,'" + deptId + "','" + currentTime + "' from zfryks_check_control where district_id='" + saveSessionBeanForm.getDistrictId() + "' and state in('3','4') ";
		ZfksJDBCDAO zfksJDBCDAO = new ZfksJDBCDAO();
		jdbcSqlList.add(zfksCheckControlNextStepJdbcSql);
		jdbcSqlList.add(zfksCheckControlNextNextStepJdbcSql);
		jdbcSqlList.add(zfksCheckControlNowStepJdbcSql);
		jdbcSqlList.add(zfksOperateJdbcSql);
		jdbcSqlList.add(zfksCheckInfoJdbcSql);
		zfksJDBCDAO.executeJDBCSqlList(jdbcSqlList);
	}
	
	/**
	 * ʡ���ϱ�֮ǰУ��õ������Ƿ���ڿ��� void
	 * 
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����2:40:59
	 */
	private void checkKc() throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String districtId = saveSessionBeanForm.getDistrictId();
		String hql = " from ZfryzgksKcgl zfryzgksKcgl where zfryzgksKcgl.kaoqu like '" + districtId.substring(0, 2) + "%'";
		List list = exelawtipstaffexamApplyDAO.query(hql);
		if (list.isEmpty()) { throw new com.boco.eoms.common.exception.CnfmMessageException("�õ����»�û�п�����Ϣ�����ܽ����ϱ����벹�俼����Ϣ���ٽ����ϱ�������"); }
		String sql = " update zfryks_kcgl set sfsb=1 where kaoqu like '" + districtId.substring(0, 2) + "%'";
		exelawtipstaffexamApplyDAO.executeSql(sql);
		// ���俼�� ����׼��֤��
	}
	
	/**
	 * �ϱ����� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Create Date 2017 ����3:51:35
	 */
	public void checkUpMoreNo() throws IllegalAccessException, InvocationTargetException, CnfmMessageException {
		List jdbcSqlList = new ArrayList();
		String deptId = new String(new Integer(saveSessionBeanForm.getWrf_DeptID()).toString());
		String currentTime = TimeUtil.getCurrentTime();
		String sqlId = getSqlId("checkUpMore");
		// ������Ŀǰ��������Ϊ�ύ����
		String zfksCheckControlFirstCheckJdbcSql = " update zfryks_check_control set state=2,step_flag=0 where now_step=1 and apply_id in " + sqlId;
		// ��״̬����Ϊ����״̬
		String zfksCheckControlJdbcSql = " update zfryks_check_control set state=2,step_flag=1 where now_step >1 and apply_id in " + sqlId;
		// ���������Ϣ��
		String zfksCheckInfoJdbcsql = " insert into zfryks_check_info select newid(),id,'" + saveSessionBeanForm.getDistrictId() + "',0,'" + "���ϱ���Ϣ��������" + "',0,0,'',0,'" + deptId + "','" + currentTime + "' from zfryks_apply where id in " + sqlId + " ";
		// ���������
		String zfksOperateJdbcSql = " insert into zfryks_operate(id,del_flag,apply_id,userid,district_id,content,time,remark)  select newid(),0,id,'" + saveSessionBeanForm.getWrf_UserID() + "','" + saveSessionBeanForm.getDistrictId() + "','���ϱ���Ϣ��������','" + currentTime + "','' from zfryks_apply where id in " + sqlId;
		// �����˲�ͨ������
		//��˲�ͨ������shbjΪnull 
		String gxshbj = "update zfryks_apply set shbj=null where id in"+sqlId;
		ZfksJDBCDAO zfksJDBCDAO = new ZfksJDBCDAO();
		jdbcSqlList.add(zfksCheckControlFirstCheckJdbcSql);
		jdbcSqlList.add(zfksCheckControlJdbcSql);
		jdbcSqlList.add(zfksCheckInfoJdbcsql);
		jdbcSqlList.add(zfksOperateJdbcSql);
		jdbcSqlList.add(gxshbj);
		zfksJDBCDAO.executeJDBCSqlList(jdbcSqlList);
		
	}
	
	/**
	 * ���濼��ʱ�� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����4:32:01
	 */
	public void saveKssj() throws CnfmMessageException, HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
		MyBeanUtils.copyProperties(zfryksBmsj, actionFormMap, null);
		// zfryksBmsj.setInsertTime(StaticMethod.getTimestamp());
		zfryksBmsj.setInsertUserId(saveSessionBeanForm.getWrf_UserID());
		if (StaticMethod.nullObject2String(actionFormMap.get("id")).equals("")) {
			exelawtipstaffexamApplyDAO.save(zfryksBmsj);
		} else {
			exelawtipstaffexamApplyDAO.update(zfryksBmsj);
		}
	}
	
	/**
	 * ���Ҹõ��������п���
	 * 
	 * @param districtId
	 * @return List
	 * @author glw
	 * @param nd
	 * @param pagePra 
	 * @throws HibernateException
	 * @Create Date 2017 ����5:03:09
	 */
	public List getkcxxListByDq(String districtId, String nd, int[] pagePra) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "from ZfryzgksKcgl zfryzgksKcgl where zfryzgksKcgl.kaoqu like'" + districtId.substring(0, 2) + "%'and zfryzgksKcgl.nd='" + nd + "'";
		List kcList = exelawtipstaffexamApplyDAO.query(sql,pagePra);
		return kcList;
		
	}
	
	/***
	 * ���濼����Ϣ
	 * 
	 * @param zfryzgksKcgl
	 * @param actionFormMap2
	 *            void
	 * @author glw
	 * @param districtId
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @param actionFormMap2
	 *            void
	 * @author glw
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����9:58:28
	 */
	public void savezfryzgksKcgl(String districtId, ZfryzgksKcgl zfryzgksKcgl, Map actionFormMap2) throws CnfmMessageException, HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String id = (String) actionFormMap2.get("id");
		ZfryzgksKd zfryzgksKq = new ZfryzgksKd();
		MyBeanUtils.copyProperties(zfryzgksKcgl, actionFormMap2, null);
		zfryzgksKcgl.setSfsb("0");
		String kaoqu = StaticMethod.nullObject2String(actionFormMap2.get("kaoqu"));
		String sqlkq = "from ZfryzgksKd zfryzgksKq where zfryzgksKq.kqId='" + kaoqu + "'";
		List kaoquList = exelawtipstaffexamDAO.query(sqlkq);
		Map shma = this.getsh();
		zfryzgksKq = this.zxsjj(zfryzgksKq, kaoqu, districtId, shma, zfryzgksKcgl, kaoquList);
		zfryzgksKq = this.fzxsjj(zfryzgksKq, kaoqu, districtId, shma, zfryzgksKcgl, kaoquList);
		if (!StaticMethod.nullObject2String(id).equals("")) {
			zfryzgksKcgl.setKqid(zfryzgksKq.getId());
			zfryzgksKcgl.setJjksdq(zfryzgksKq.getJjkqDistrictId());
			zfryzgksKcgl.setJjksdqmc(zfryzgksKq.getJjkqmc());
			exelawtipstaffexamDAO.update(zfryzgksKcgl);
		} else {
			zfryzgksKcgl.setKqid(zfryzgksKq.getId());
			exelawtipstaffexamDAO.save(zfryzgksKcgl);
		}
	}
	
	/***
	 * ֱϽ�оͽ�����
	 */
	public ZfryzgksKd zxsjj(ZfryzgksKd zfryzgksKq, String kaoqu, String districtId, Map shma, ZfryzgksKcgl zfryzgksKcgl, List kaoquList) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		TawDistrictBO tawDistrictBO = new TawDistrictBO();
		String jjkqid = "";
		String jjkqmc = "";
		if (districtId.equals("110000") || districtId.equals("120000") || districtId.equals("310000") || districtId.equals("500000")) {
			if (kaoquList.size() == 0) {
				jjkqid = kaoqu + "," + districtId + "," + zfryzgksKcgl.getJjksdq();
				jjkqmc = tawDistrictBO.getDistrictNameById(kaoqu) + "," + tawDistrictBO.getDistrictNameById(districtId) + "," + zfryzgksKcgl.getJjksdqmc();
				zfryzgksKq.setJjkqDistrictId(jjkqid);
				zfryzgksKq.setJjkqmc(jjkqmc);
				zfryzgksKq.setKqId(zfryzgksKcgl.getKaoqu());
				zfryzgksKq.setKqMc(zfryzgksKcgl.getKaoquName());
				exelawtipstaffexamDAO.save(zfryzgksKq);
			} else {
				zfryzgksKq = (ZfryzgksKd) kaoquList.get(0);
				if (zfryzgksKcgl.getJjksdq().indexOf(kaoqu) > 0) {
					jjkqid = zfryzgksKcgl.getJjksdq();
					jjkqmc = zfryzgksKcgl.getJjksdqmc();
				} else {
					jjkqid = kaoqu + "," + districtId + "," + zfryzgksKcgl.getJjksdq();
					jjkqmc = tawDistrictBO.getDistrictNameById(kaoqu) + "," + tawDistrictBO.getDistrictNameById(districtId) + "," + zfryzgksKcgl.getJjksdqmc();
				}
				
				zfryzgksKq.setJjkqDistrictId(jjkqid);
				zfryzgksKq.setJjkqmc(jjkqmc);
				zfryzgksKq.setKqId(zfryzgksKcgl.getKaoqu());
				zfryzgksKq.setKqMc(zfryzgksKcgl.getKaoquName());
				exelawtipstaffexamDAO.update(zfryzgksKq);
			}
		}
		return zfryzgksKq;
	}
	
	/**
	 * ��ֱϽ�оͽ����㴦��
	 * 
	 * @throws HibernateException
	 */
	public ZfryzgksKd fzxsjj(ZfryzgksKd zfryzgksKq, String kaoqu, String districtId, Map shma, ZfryzgksKcgl zfryzgksKcgl, List kaoquList) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		TawDistrictBO tawDistrictBO = new TawDistrictBO();
		String jjkqid = "";
		String jjkqmc = "";
		if (!districtId.equals("110000") && !districtId.equals("120000") &&! districtId.equals("310000") &&! districtId.equals("500000")) {
		if (kaoqu.equals(shma.get(districtId))) {
			if (kaoquList.size() == 0) {
				jjkqid = kaoqu + "," + districtId + "," + zfryzgksKcgl.getJjksdq();
				jjkqmc = tawDistrictBO.getDistrictNameById(kaoqu) + "," + tawDistrictBO.getDistrictNameById(districtId) + "," + zfryzgksKcgl.getJjksdqmc();
				zfryzgksKq.setJjkqDistrictId(jjkqid);
				zfryzgksKq.setJjkqmc(jjkqmc);
				zfryzgksKq.setKqId(zfryzgksKcgl.getKaoqu());
				zfryzgksKq.setKqMc(zfryzgksKcgl.getKaoquName());
				exelawtipstaffexamDAO.save(zfryzgksKq);
			} else {
				zfryzgksKq = (ZfryzgksKd) kaoquList.get(0);
				if (zfryzgksKcgl.getJjksdq().indexOf(districtId) > 0) {
					jjkqid = zfryzgksKcgl.getJjksdq();
					jjkqmc = zfryzgksKcgl.getJjksdqmc();
				} else {
					jjkqid = kaoqu + "," + districtId + "," + zfryzgksKcgl.getJjksdq();
					jjkqmc = tawDistrictBO.getDistrictNameById(kaoqu) + "," + tawDistrictBO.getDistrictNameById(districtId) + "," + zfryzgksKcgl.getJjksdqmc();
				}
				zfryzgksKq.setJjkqDistrictId(jjkqid);
				zfryzgksKq.setJjkqmc(jjkqmc);
				zfryzgksKq.setKqId(zfryzgksKcgl.getKaoqu());
				zfryzgksKq.setKqMc(zfryzgksKcgl.getKaoquName());
				exelawtipstaffexamDAO.update(zfryzgksKq);
			}
		} else {
			if (kaoquList.size() == 0) {
				if(zfryzgksKcgl.getJjksdq().indexOf(shma.get(districtId).toString())>-1){
					jjkqid=districtId+",";
					jjkqmc=tawDistrictBO.getDistrictNameById(districtId)+",";
				}
				jjkqid += kaoqu + "," + zfryzgksKcgl.getJjksdq();
				jjkqmc += tawDistrictBO.getDistrictNameById(kaoqu) + "," + zfryzgksKcgl.getJjksdqmc();
				zfryzgksKq.setJjkqDistrictId(jjkqid);
				zfryzgksKq.setJjkqmc(jjkqmc);
				zfryzgksKq.setKqId(zfryzgksKcgl.getKaoqu());
				zfryzgksKq.setKqMc(zfryzgksKcgl.getKaoquName());
				exelawtipstaffexamDAO.save(zfryzgksKq);
			} else {
				zfryzgksKq = (ZfryzgksKd) kaoquList.get(0);
				if (zfryzgksKcgl.getJjksdq().indexOf(kaoqu) > 0) {
					jjkqid = zfryzgksKcgl.getJjksdq();
					jjkqmc = zfryzgksKcgl.getJjksdqmc();
				} else {
					if(zfryzgksKcgl.getJjksdq().indexOf(shma.get(districtId).toString())>-1){
						jjkqid=districtId+",";
						jjkqmc=tawDistrictBO.getDistrictNameById(districtId)+",";
					}
					jjkqid += kaoqu + "," + zfryzgksKcgl.getJjksdq();
					jjkqmc += tawDistrictBO.getDistrictNameById(kaoqu) + "," + zfryzgksKcgl.getJjksdqmc();
				}
				
				zfryzgksKq.setJjkqDistrictId(jjkqid);
				zfryzgksKq.setJjkqmc(jjkqmc);
				zfryzgksKq.setKqId(zfryzgksKcgl.getKaoqu());
				zfryzgksKq.setKqMc(zfryzgksKcgl.getKaoquName());
				exelawtipstaffexamDAO.update(zfryzgksKq);
			}
			}
		}
		return zfryzgksKq;
	}
	
	public Map getsh() {
		Map map = new HashMap();
		map.put("130000", "130100");
		map.put("140000", "140100");
		map.put("150000", "150100");
		map.put("210000", "210100");
		map.put("220000", "220100");
		map.put("230000", "230100");
		map.put("320000", "320100");
		map.put("330000", "330100");
		map.put("340000", "340100");
		map.put("350000", "350100");
		map.put("360000", "360100");
		map.put("370000", "370100");
		map.put("410000", "410100");
		map.put("420000", "420100");
		map.put("430000", "430100");
		map.put("440000", "440100");
		map.put("450000", "450100");
		map.put("460000", "460100");
		map.put("510000", "510100");
		map.put("520000", "520100");
		map.put("530000", "530100");
		map.put("540000", "540100");
		map.put("610000", "610100");
		map.put("620000", "620100");
		map.put("630000", "630100");
		map.put("640000", "640100");
		map.put("650000", "650100");
		map.put("660000", "660100");
		return map;
		
	}
	
	/***
	 * ɾ������
	 * 
	 * @param id
	 *            void
	 * @author glw
	 * @param message
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 *             ======= ɾ������
	 * @param id
	 *            void
	 * @author glw
	 * @param message
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 *             >>>>>>> .r7811
	 * @Create Date 2017 ����11:11:37
	 */
	public Map deletezfryzgksKcgl(String id, String message) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksKcgl zfryzgksKcgl = (ZfryzgksKcgl) exelawtipstaffexamDAO.load(id, ZfryzgksKcgl.class);
		String kqid = zfryzgksKcgl.getKqid();
		String sqlkq = "from ZfryzgksKd zfryzgksKq where zfryzgksKq.id='" + kqid + "'";
		List zfryzgksKqList = exelawtipstaffexamDAO.query(sqlkq);
		String sqlkd = "from ZfryzgksKcgl zfryzgksKcgl where zfryzgksKcgl.kqid='" + kqid + "'";
		List zfryzgksKcglList = exelawtipstaffexamDAO.query(sqlkd);
		ZfryzgksKd zfryzgksKq = (ZfryzgksKd) zfryzgksKqList.get(0);
		Integer kcrw = zfryzgksKcgl.getKczw();
		Map map = new HashMap();
		String flag = "true";
		map.put("flag", flag);
		map.put("message", message);
		String sql = "from ZfryzgksApply zfryzgksApply where zfryzgksApply.kcid='" + id + "'";
		List zfryzgksApplyList = exelawtipstaffexamDAO.query(sql);
		if (zfryzgksApplyList.size() > 0 && zfryzgksApplyList.size() == kcrw.intValue()) {
			message = "�ÿ����Ѿ������˿��������ҿ����Ѿ����ˣ����ܽ���ɾ�����޸ģ�";
			flag = "false";
			map.put("flag", flag);
			map.put("message", message);
			return map;
		}
		if (zfryzgksApplyList.size() > 0) {
			message = "�ÿ����Ѿ������˿������ܽ���ɾ��,���Խ����޸�";
			flag = "false";
			map.put("flag", flag);
			map.put("message", message);
			return map;
		}
		if (!(zfryzgksKcglList.size() > 1)) {
			exelawtipstaffexamDAO.delete(zfryzgksKq);
		}
		exelawtipstaffexamDAO.delete(zfryzgksKcgl);
		return map;
	}
	
	/***
	 * ����id��ȡ������Ϣ
	 * 
	 * @param id
	 *            void
	 * @author glw
	 * @param message
	 * @return
	 * @throws HibernateException
	 * @Create Date 2017 ����11:34:29
	 */
	public ZfryzgksKcgl getzfryzgksKcgl(String id) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksKcgl zfryzgksKcgl = (ZfryzgksKcgl) exelawtipstaffexamDAO.load(id, ZfryzgksKcgl.class);
		return zfryzgksKcgl;
	}
	
	/***
	 * ��ȡ�����ܷ��޸ĵ���Ϣ
	 * 
	 * @param id
	 * @param message
	 * @return
	 * @throws HibernateException
	 *             String
	 * @author glw
	 * @Create Date 2017 ����2:22:05
	 */
	public Map getsfxgxx(String id, String message) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksKcgl zfryzgksKcgl = (ZfryzgksKcgl) exelawtipstaffexamDAO.load(id, ZfryzgksKcgl.class);
		Map map = new HashMap();
		String flag = "true";
		map.put("flag", flag);
		map.put("message", message);
		if (!(StaticMethod.nullObject2String(zfryzgksKcgl.getSfsb()).equals("0") || StaticMethod.nullObject2String(zfryzgksKcgl.getSfsb()).equals(""))) {
			message = "�ÿ����Ѿ��ϱ������ܽ����޸ģ�����޸������벵�غ��޸�";
			flag = "true";
			map.put("flag", flag);
			map.put("message", message);
		}
		return map;
	}
	
	/**
	 * ��ѯҪ�޸ĵļ�¼
	 * 
	 * @param id
	 *            ����
	 * @return ZfryzgksApply
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����1:55:47
	 */
	public ZfryzgksApply getModifyById(String id) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		if (StaticMethod.nullObject2String(id).equals("")) { throw new com.boco.eoms.common.exception.CnfmMessageException("��ȡ��Ϣʧ��!"); }
		ZfryzgksApply zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamApplyDAO.load(id, ZfryzgksApply.class);
		return zfryzgksApply;
	}
	
	/**
	 * ɾ��������Ϣ
	 * 
	 * @param id
	 *            void
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����2:34:29
	 */
	public void deleteApplyById(String id) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		if (StaticMethod.nullObject2String(id).equals("")) { throw new com.boco.eoms.common.exception.CnfmMessageException("��ȡ��Ϣʧ��!"); }
		ZfryzgksApply zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamApplyDAO.load(id, ZfryzgksApply.class);
		// ��¼������Ϣ
		ZfryzgksOperate zfryzgksOperate = new ZfryzgksOperate();
		zfryzgksOperate.setApplyId(id);
		zfryzgksOperate.setContent("ɾ��������Ϣ");
		zfryzgksOperate.setDelFlag(Integer.valueOf(0));
		zfryzgksOperate.setDistrictId(saveSessionBeanForm.getDistrictId());
		zfryzgksOperate.setRemark(saveSessionBeanForm.getWrf_UserName() + "ɾ��" + zfryzgksApply.getXm() + "�ı�����Ϣ");
		zfryzgksOperate.setTime(StaticMethod.getTimestamp());
		zfryzgksOperate.setUserid(saveSessionBeanForm.getWrf_UserID());
		exelawtipstaffexamApplyDAO.delete(zfryzgksApply);
		exelawtipstaffexamApplyDAO.save(zfryzgksOperate);
		// ɾ��������Ϣ
		String sql = "delete from zfryks_check_control where apply_id ='" + id + "'";
		exelawtipstaffexamApplyDAO.executeSql(sql);
	}
	
	/**
	 * ��������޸ĵ���Ϣ void
	 * 
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����3:14:10
	 */
	public void updateApply() throws HibernateException, CnfmMessageException {
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		if (!StaticMethod.nullObject2String(actionFormMap.get("id")).equals("")) {
			zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamApplyDAO.load(StaticMethod.nullObject2String(actionFormMap.get("id")), ZfryzgksApply.class);
			MyBeanUtils.copyProperties(zfryzgksApply, actionFormMap, null);
			zfryzgksApply.setUpdateTime(java.sql.Timestamp.valueOf(TimeUtil.getCurrentTime()));
			zfryzgksApply.setZp(actionFormMap.get("zpp").toString());
			exelawtipstaffexamApplyDAO.update(zfryzgksApply);
		}
	}
	
	/**
	 * ��������޸ĵ���Ϣ void
	 * 
	 * @author wjk
	 * @throws HibernateException
	 * @throws CnfmMessageException
	 * @Create Date 2017 ����3:14:10
	 */
	public void updateApplyByManager() throws HibernateException, CnfmMessageException {
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		if (!StaticMethod.nullObject2String(actionFormMap.get("id")).equals("")) {
			zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamApplyDAO.load(StaticMethod.nullObject2String(actionFormMap.get("id")), ZfryzgksApply.class);
			MyBeanUtils.copyProperties(zfryzgksApply, actionFormMap, null);
			zfryzgksApply.setUpdateTime(java.sql.Timestamp.valueOf(TimeUtil.getCurrentTime()));
			String bakSql = "insert into zfryks_apply_history select newid(), getdate(), * from zfryks_apply where id ='" + zfryzgksApply.getId() + "'";
			System.out.println(bakSql);
			exelawtipstaffexamApplyDAO.executeSql(bakSql);
			exelawtipstaffexamApplyDAO.update(zfryzgksApply);
		}
	}
	
	/**
	 * @param id
	 * @return ZfryzgksApply
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����4:24:17
	 */
	public ZfryzgksApply viewById(String id) throws CnfmMessageException, HibernateException {
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		if (StaticMethod.nullObject2String(id).equals("")) { throw new com.boco.eoms.common.exception.CnfmMessageException("��ȡ��Ϣʧ�ܣ�"); }
		zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamApplyDAO.load(id, ZfryzgksApply.class);
		return zfryzgksApply;
	}
	
	/**
	 * ��ӡ���ܱ�
	 * 
	 * @return List
	 * @author wjk
	 * @param nd 
	 * @throws HibernateException
	 * @Create Date 2017 ����10:11:53
	 */
	public List getHuiZongBiao(String nd) throws HibernateException {
		String hql = "from ZfryzgksApply zfryzgksApply where zfryzgksApply.nd='"+nd+"' and zfryzgksApply.id in(select zfryzgksCheckControl.applyId from ZfryzgksCheckControl zfryzgksCheckControl where zfryzgksCheckControl.districtId ='" + saveSessionBeanForm.getDistrictId() + "' and zfryzgksCheckControl.stepFlag =1 and state=5) order by  zfryzgksApply.sqdu, zfryzgksApply.gzdwmc, zfryzgksApply.sfzh";
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		List list = exelawtipstaffexamApplyDAO.query(hql);
		List zfryzgksApplyList = new ArrayList();
		if (list.size() > 0 || !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
				ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
				zfryzgksApply = (ZfryzgksApply) list.get(i);
				MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				zfryzgksApplyList.add(zfryzgksApplyVO);
			}
		}
		return zfryzgksApplyList;
	}
	
	/**
	 * �Ƿ���ʡ������ -1 ���� 0 ��
	 * 
	 * @param districtId
	 * @return int
	 * @author wjk
	 * @Create Date 2017 ����11:05:27
	 */
	public int isSheng(String districtId) {
		int flag = -1;
		if (districtId.equals(districtId.substring(0, 2) + "0000")) {
			flag = 0;
		}
		return flag;
	}
	
	/**
	 * ���ҵ�ǰ��ȵı�������
	 * 
	 * @param nd
	 * @param zfryksBmsj
	 * @return ZfryksBmsj
	 * @author wjk
	 * @throws HibernateException
	 * @Create Date 2017 ����2:15:01
	 */
	public ZfryksBmsj getBmsj(String nd, String districtId) throws HibernateException {
		String hqlSheng = "from ZfryksBmsj zfryksBmsj where zfryksBmsj.niandu='" + nd + "' and zfryksBmsj.districtId ='" + districtId.substring(0, 2) + "0000'";
		String hql = "from ZfryksBmsj zfryksBmsj where zfryksBmsj.niandu='" + nd + "' and zfryksBmsj.districtId='000000'";
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
		List bmsjList = exelawtipstaffexamApplyDAO.query(hqlSheng);
		List bmsjListBu = exelawtipstaffexamApplyDAO.query(hql);
		if (bmsjList.size() > 0 || !bmsjList.isEmpty()) {
			zfryksBmsj = (ZfryksBmsj) bmsjList.get(0);
		} else if (bmsjListBu.size() > 0 || !bmsjListBu.isEmpty()) {
			zfryksBmsj = (ZfryksBmsj) bmsjListBu.get(0);
		}
		return zfryksBmsj;
	}
	
	/***
	 * ���������id��ѯ����˵ĵ���
	 * 
	 * @param id
	 * @return String
	 * @author glw
	 * @param flag
	 * @param dsdq
	 * @throws HibernateException
	 * @Create Date 2017 ����9:06:43
	 */
	public String getdaishenhediqu(String id, String flag) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "from ZfryzgksCheckControl zfryzgksCheckControl where zfryzgksCheckControl.stepFlag='1' and zfryzgksCheckControl.nowStep='5'and zfryzgksCheckControl.applyId='" + id + "'";
		List zfryzgksCheckControlList = exelawtipstaffexamApplyDAO.query(sql);
		if (zfryzgksCheckControlList.size() > 0) {
			flag = "false";
		}
		return flag;
	}
	
	/***
	 * ��ӡ������Ϣ
	 * 
	 * @param contextPath
	 * @param normCjQrb
	 * @return PrintConfig
	 * @author glw
	 * @Create Date 2017 ����10:01:01
	 */
	public PrintConfig setPrintConfigForzfryzgksApply(String contextPath, ZfryzgksApply zfryzgksApply) {
		PrintConfig printConfig = new PrintConfig();
		String[] s = new String[1];
		printConfig.setBarcodeKeys(s);// �����������
		printConfig.setPrintUrl("");// ��ӡURL
		boolean isCanPrint = true;
		printConfig.setIsCanPrint(new Boolean(isCanPrint));// ��ǰ�û��Ƿ�ɴ�ӡ
		printConfig.setFileName("zfryzgksApply");//
		printConfig.setModule("exelawtipstaffexam");
		printConfig.setPrintedCallBackUrl("");
		printConfig.setPrintCopies(2);
		return printConfig;
	}
	
	/***
	 * ���ݵ���id��ѯ�ĵ����µı�����Ա�б�
	 * @param districtId
	 * @return List
	 * @author glw
	 * @param nd
	 * @param pagePra 
	 * @param remark 
	 * @throws SQLException
	 * @throws CacheException
	 * @throws HibernateException
	 * @throws CnfmMessageException 
	 * @Create Date 2017 ����3:07:21
	 */
	public List getBaokaoshenqingList(String districtId, String nd, int[] pagePra, String remark) throws CacheException, SQLException, HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		TawDistrictBO tawDistrictBO = new TawDistrictBO();
		String xsdq = tawDistrictBO.getChildDistrictsNoSelf(districtId);
		if(!remark.equals("3-0")&&!remark.equals("3-1")&&!remark.equals("1-1")){throw new CnfmMessageException("���˺Ų���ʡ���˺Ż��߲����˺ţ�����¼��ɼ�!");}
		String sql = "from ZfryzgksApply zfryzgksApply,ZfryzgksKcgl zfryzgksKcgl where zfryzgksApply.sqdu in(" + xsdq + ") and zfryzgksApply.nd='" + nd + "' " + "and (zfryzgksApply.shbj!='0'or zfryzgksApply.shbj is not null) and (zfryzgksApply.kcid is not null or zfryzgksApply.zkzh is not null )" + "and zfryzgksKcgl.id=zfryzgksApply.kcid";
		List yikaorenyuanList = exelawtipstaffexamApplyDAO.query(sql,pagePra);
		return yikaorenyuanList;
	}
	
	/**
	 * ���濼���ɼ�
	 * 
	 * @param id
	 * @param kscj
	 * @return ZfryzgksApply
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����4:52:45
	 */
	public void saveKaoshengchengji(String id, String kscj) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		if (!StaticMethod.nullObject2String(id).equals("")) {
			zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamDAO.load(StaticMethod.nullObject2String(id), ZfryzgksApply.class);
		}
		zfryzgksApply.setKscj(kscj);
		exelawtipstaffexamDAO.update(zfryzgksApply);
	}
	
	/***
	 * ѡ��ͽ�����
	 * 
	 * @param districtId
	 * @param kaoqu
	 *            void
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����8:28:38
	 */
	public List choosejjkq(String districtId, String kaoqu) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String kq = kaoqu;
		String kqsql = "from ZfryzgksKd zfryzgksKq where zfryzgksKq.jjkqDistrictId like '" + districtId.substring(0, 2) + "%' and zfryzgksKq.kqId!='" + kaoqu + "'";
		List jjkqList = exelawtipstaffexamDAO.query(kqsql);
		String sql="";
		ZfryzgksKd zfryzgksKq = new ZfryzgksKd();
		String kaoqunew = "";
		if (jjkqList.size() > 0) {
			for (int i = 0; i < jjkqList.size(); i++) {
				zfryzgksKq = (ZfryzgksKd) jjkqList.get(i);
				kaoqu = zfryzgksKq.getJjkqDistrictId();
				if(!kaoqu.endsWith(",")){
					kaoqu+=",";
				}
				kaoqunew += this.fgjjksdq(kaoqu)+",";
			}
			kaoqunew = this.fgjjksdq(kaoqunew);
			 sql = "from TawDistrict tawDistrict where tawDistrict.parentDistrict='" + districtId + "' and tawDistrict.districtId not in(" + kaoqunew + ")and tawDistrict.districtId!='"+kq+"'";
		}else{
			 sql = "from TawDistrict tawDistrict where tawDistrict.parentDistrict='" + districtId + "'and tawDistrict.districtId!='"+kq+"'";
		}
		List tawDistrictList = exelawtipstaffexamDAO.query(sql);
		return tawDistrictList;
		
	}
	
	/***
	 * �ָ�ͽ����Ե���
	 * 
	 * @return String
	 * @author glw
	 * @Create Date 2017 ����9:34:14
	 */
	public String fgjjksdq(String kaoqu) {
		kaoqu = kaoqu.substring(0, kaoqu.length()-1);
		return kaoqu;
	}
	
	/**
	 * ѡ����
	 * 
	 * @param districtId
	 * @return List
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����8:40:47
	 * @author wjk
	 * @param pagePra
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����4:59:14
	 */
	public List choosekq(String districtId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		TawDistrict tawDistrict = new TawDistrict();
		String sql = "from TawDistrict tawDistrict where tawDistrict.parentDistrict='" + districtId + "'";
		List tawDistrictList = exelawtipstaffexamDAO.query(sql);
		return tawDistrictList;
	}
	
	public List getQueryList(int[] pagePra) throws CnfmMessageException, HibernateException {
		ZfryzgksApplyQO zfryzgksApplyQO = new ZfryzgksApplyQO();
		MyBeanUtils.copyProperties(zfryzgksApplyQO, actionFormMap, null);
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String hql = zfryzgksApplyQO.getQueryHql(actionFormMap.get("sqdu").toString(), saveSessionBeanForm.getDistrictId());
		List list = exelawtipstaffexamApplyDAO.query(hql, pagePra);
		List returnList = new ArrayList();
		if (list.size() > 0 || !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
				ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
				zfryzgksApply = (ZfryzgksApply) list.get(i);
				MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				returnList.add(zfryzgksApplyVO);
			}
		}
		return returnList;
	}
	
	/**
	 * ѡ������鿴�ͽ�����
	 * 
	 * @param kqid
	 * @return Map
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����8:54:11
	 */
	public Map checkjjkq(String kqid) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String sqlkq = "from ZfryzgksKd zfryzgksKq where zfryzgksKq.kqId='" + kqid + "'";
		List jjkqList = exelawtipstaffexamDAO.query(sqlkq);
		ZfryzgksKd zfryzgksKq = new ZfryzgksKd();
		if (jjkqList.size() > 0) {
			zfryzgksKq = (ZfryzgksKd) jjkqList.get(0);
		}
		Map map = new HashMap();
		map.put("zfryzgksKq", zfryzgksKq);
		return map;
	}
	
	/***
	 * ���ݿ���id�鿴�ÿ������Ա�б�
	 * 
	 * @param kcId
	 * @return List
	 * @author glw
	 * @param nd
	 * @throws HibernateException
	 * @Create Date 2017 ����2:48:23
	 */
	public List ckkdryList(String kcId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String sqlkq = "from ZfryzgksApply zfryzgksApply,ZfryzgksKd zfryzgksKq,ZfryzgksKcgl zfryzgksKcgl where zfryzgksApply.kcid='" + kcId + "'" + "and zfryzgksKcgl.id=zfryzgksApply.kcid and zfryzgksKq.id=zfryzgksKcgl.kqid order by zfryzgksApply.kch, zfryzgksApply.zwh";
		List kdryList = exelawtipstaffexamDAO.query(sqlkq);
		return kdryList;
	}
	
	/**
	 * ���漰����� void
	 * 
	 * @author wjk
	 * @throws CnfmMessageException
	 * @throws HibernateException
	 * @Create Date 2017 ����7:50:24
	 */
	public void saveJgfs() throws CnfmMessageException, HibernateException {
		ZfryzgksJgfs zfryzgksJgfs = new ZfryzgksJgfs();
		MyBeanUtils.copyProperties(zfryzgksJgfs, actionFormMap, null);
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		if (StaticMethod.nullObject2String(actionFormMap.get("id")).equals("")) {
			zfryzgksJgfs.setInsertTime(StaticMethod.getTimestamp());
			zfryzgksJgfs.setInsertUserId(saveSessionBeanForm.getWrf_UserID());
			exelawtipstaffexamApplyDAO.save(zfryzgksJgfs);
		} else {
			exelawtipstaffexamApplyDAO.update(zfryzgksJgfs);
		}
	}
	
	/**
	 * ��ѯ�������
	 * 
	 * @return ZfryzgksJgfs
	 * @author wjk
	 * @throws HibernateException
	 * @Create Date 2017 ����8:28:26
	 */
	public ZfryzgksJgfs getJgfs() throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		Calendar a = Calendar.getInstance();
		String nd = String.valueOf(a.get(Calendar.YEAR));
		String hql = " from  ZfryzgksJgfs zfryzgksJgfs where zfryzgksJgfs.niandu='" + nd + "'";
		List list = exelawtipstaffexamApplyDAO.query(hql);
		ZfryzgksJgfs zfryzgksJgfs = new ZfryzgksJgfs();
		if (list.size() > 0 || !list.isEmpty()) {
			zfryzgksJgfs = (ZfryzgksJgfs) list.get(0);
		}
		return zfryzgksJgfs;
	}
	
	/***
	 * ���ݵ�������Ȳ�ѯ�ܹ���ӡ׼��֤���б�
	 * 
	 * @param districtId
	 * @param nd
	 * @return List
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����3:46:38
	 */
	public List dayinzhunkazhengList(String districtId, String nd) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String sqlkq = "from ZfryzgksApply zfryzgksApply,ZfryzgksKd zfryzgksKq,ZfryzgksKcgl zfryzgksKcgl where zfryzgksApply.nd='" + nd + "'" + "and zfryzgksApply.sqdu='" + districtId + "' and zfryzgksApply.zkzh !='' and zfryzgksKcgl.id=zfryzgksApply.kcid and zfryzgksKq.id=zfryzgksKcgl.kqid";
		List kdryList = exelawtipstaffexamDAO.query(sqlkq);
		return kdryList;
	}
	
	/***
	 * �鿴������Ϣ
	 * 
	 * @param kdId
	 * @return ZfryzgksKd
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����4:16:17
	 */
	public ZfryzgksKd chakanzfryzgksKd(String kqId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksKd zfryzgksKd = new ZfryzgksKd();
		if (!StaticMethod.nullObject2String(kqId).equals("")) {
			zfryzgksKd = (ZfryzgksKd) exelawtipstaffexamDAO.load(StaticMethod.nullObject2String(kqId), ZfryzgksKd.class);
		}
		return zfryzgksKd;
	}
	
	public ZfryzgksKcgl chakanzfryzgksKcgl(String kdId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksKcgl zfryzgksKcgl = new ZfryzgksKcgl();
		if (!StaticMethod.nullObject2String(kdId).equals("")) {
			zfryzgksKcgl = (ZfryzgksKcgl) exelawtipstaffexamDAO.load(StaticMethod.nullObject2String(kdId), ZfryzgksKcgl.class);
		}
		return zfryzgksKcgl;
	}
	
	/**
	 * ���ݵ�����Ȳ��ҿ���ʱ��
	 * 
	 * @param nd
	 * @param districtId
	 * @return ZfryksBmsj
	 * @author glw
	 * @throws HibernateException
	 * @Create Date 2017 ����4:24:09
	 */
	public ZfryksBmsj chakankaoshishijian(String nd, String districtId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "from ZfryksBmsj zfryksBmsj where zfryksBmsj.niandu='" + nd + "'and zfryksBmsj.districtId ='" + districtId.substring(0, 2) + "+0000'";
		List zfryksBmsjList = exelawtipstaffexamDAO.query(sql);
		ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
		if (zfryksBmsjList.size() > 0) {
			zfryksBmsj = (ZfryksBmsj) zfryksBmsjList.get(0);
		} else {
			sql = "from ZfryksBmsj zfryksBmsj where zfryksBmsj.niandu='" + nd + "'and zfryksBmsj.districtId ='000000'";
			zfryksBmsjList = exelawtipstaffexamDAO.query(sql);
			if (zfryksBmsjList.size() > 0) {
				zfryksBmsj = (ZfryksBmsj) zfryksBmsjList.get(0);
			}
		}
		return zfryksBmsj;
	}
	
	/**
	 * ��ģ����ʱ����
	 * 
	 * @throws CnfmMessageException
	 *             void
	 * @author wjk
	 * @Create Date 2017 ����4:46:07
	 */
	public void getTjList() throws CnfmMessageException {
		throw new com.boco.eoms.common.exception.CnfmMessageException("��ģ�齨���У�");
		
	}

	public List getSecondDistricts(String id) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "FROM TawDistrict tawDistrict WHERE tawDistrict.parentDistrict='"+id+"' and tawDistrict.deleted=0 order by tawDistrict.districtId ";
		List disList = exelawtipstaffexamDAO.query(sql);
		return disList;
	}
	
	
	
	/**
	 * ��ѯ��ǰ����
	 * 
	 * @param applyId
	 * @return com.boco.eoms.exelawtipstaffexam.model.ZfryzgksCheckControl
	 * @author wjk
	 * @throws HibernateException 
	 * @Create Date 2017 ����2:10:37
	 */
	public ZfryzgksCheckControlVO getZfryzgksCheckControlByApplyId(String applyId) throws HibernateException {
		String hql =" from ZfryzgksCheckControl zfryzgksCheckControl where zfryzgksCheckControl.applyId='"+applyId+"' and zfryzgksCheckControl.stepFlag=0";
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		List list = exelawtipstaffexamDAO.query(hql);
		ZfryzgksCheckControl zfryzgksCheckControl = new ZfryzgksCheckControl();
		ZfryzgksCheckControlVO zfryzgksCheckControlVO = new ZfryzgksCheckControlVO(); 
		if(list.size()>0||!list.isEmpty()){
			zfryzgksCheckControl = (ZfryzgksCheckControl) list.get(0);
			MyBeanUtils.copyProperties(zfryzgksCheckControlVO, zfryzgksCheckControl, null);
		}
		return zfryzgksCheckControlVO;
	}

	public ZfryzgksKd getzfryzgksKd(String kqid) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksKd zfryzgksKd = (ZfryzgksKd) exelawtipstaffexamDAO.load(kqid, ZfryzgksKd.class);
		return zfryzgksKd;
	}

	/**
	 * ͨ��id�鿴��Ϣ
	 * @param id
	 * @return ZfryzgksApplyVO
	 * @author wjk 
	 * @throws HibernateException 
	 * @Create Date 2017 ����5:28:24
	 */
	public ZfryzgksApplyVO getZfryzgksApplyById(String id) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksApply zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamDAO.load(id, ZfryzgksApply.class);
		ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
		MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
		return zfryzgksApplyVO;
	}

	/**
	 * �������ͨ��    
	 *  void
	 * @author wjk 
	 * @throws CnfmMessageException 
	 * @throws HibernateException 
	 * @Create Date 2017 ����6:11:17
	 */
	public void checkOne() throws CnfmMessageException, HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamDAO = new ExelawtipstaffexamApplyDAO();
		String  id  = actionFormMap.get("id").toString();
		String  nsyzfzh = actionFormMap.get("nsyzfzh").toString();
		if(id.equals("")){throw new com.boco.eoms.common.exception.CnfmMessageException("��ȡ��Ϣʧ�ܣ�");}
		if(nsyzfzh.equals("")){throw new com.boco.eoms.common.exception.CnfmMessageException("��ʹ��ִ����Ա֤��Ϊ�ղ����ύ��");}
		ZfryzgksApply zfryzgksApply = (ZfryzgksApply) exelawtipstaffexamDAO.load(id, ZfryzgksApply.class);
		zfryzgksApply.setNsyzfzh(nsyzfzh);
		zfryzgksApply.setShbj("1");
		exelawtipstaffexamDAO.update(zfryzgksApply);
		Calendar a = Calendar.getInstance();
		String nd = String.valueOf(a.get(Calendar.YEAR));
		ZfryksBmsj zfryksBmsj = this.checkApplyTime(saveSessionBeanForm.getDistrictId(), nd);
		if(zfryksBmsj == null){ throw new com.boco.eoms.common.exception.CnfmMessageException("û�в�ѯ������ʱ����Ϣ��");}
		if(!saveSessionBeanForm.getDistrictRemark().equals("1-")){
		      this.checkCheckTime(zfryksBmsj);
		}
		List jdbcSqlList = new ArrayList();
		String deptId = new String(new Integer(saveSessionBeanForm.getWrf_DeptID()).toString());
		String currentTime = TimeUtil.getCurrentTime();
		// �������̿��Ʊ� ����ǰ��˵��� �Ŀ�����Ϣ ����Ϊ ���ͨ��
		//String applyShbjJDBCSQL = " update zfryks_apply set shbj =1 where id ='"+id+"' ";
		String zfksCheckControlJDBCSQL = " update zfryks_check_control set state=3 where district_id='" + saveSessionBeanForm.getDistrictId() + "' and step_flag=0 and apply_id ='"+id+"' ";
		String zfksCheckInfoJDBCSQL = " insert into zfryks_check_info (id,apply_id,check_district,check_result,check_idea,incraece_time,reduce_time,remark,DEL_FLAG,check_dept,check_time) select newid(),id,'" + saveSessionBeanForm.getDistrictId() + "',0,'" + "�������ͨ��" + "',0,0,'',0,'" + deptId + "','" + currentTime + "'  from zfryks_apply where id ='"+id+"'";
		String zfksOperateJDBCSQL = " insert into zfryks_operate  (id,DEL_FLAG,apply_id,userid,district_id,content,time,remark)  select newid(),0,id,'" + saveSessionBeanForm.getWrf_UserID() + "','" + saveSessionBeanForm.getDistrictId() + "','�������ͨ��','" + currentTime + "','' from zfryks_apply where id ='"+id+"'";
		ZfksJDBCDAO jdbcDAO = new ZfksJDBCDAO();
		jdbcSqlList.add(zfksCheckControlJDBCSQL);
		jdbcSqlList.add(zfksCheckInfoJDBCSQL);
		jdbcSqlList.add(zfksOperateJDBCSQL);
		//jdbcSqlList.add(applyShbjJDBCSQL);
		jdbcDAO.executeJDBCSqlList(jdbcSqlList);
	}

	/***
	 * ���浥λ��Ϣ    
	 * @param zfryzgksDqdwb
	 * @param actionFormMap2 void
	 * @author glw 
	 * @throws CnfmMessageException 
	 * @throws HibernateException 
	 * @Create Date 2017 ����5:39:25
	 */
	public void savezfryzgksDqdwb(ZfryzgksDqdwb zfryzgksDqdwb, Map actionFormMap2) throws CnfmMessageException, HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		MyBeanUtils.copyProperties(zfryzgksDqdwb, actionFormMap2, null);
		zfryzgksDqdwb.setOperateTime(java.sql.Timestamp.valueOf(TimeUtil.getCurrentTime()));
		if(StaticMethod.nullObject2String(zfryzgksDqdwb.getId()).equals("")){
			exelawtipstaffexamApplyDAO.save(zfryzgksDqdwb);
		}else{
			exelawtipstaffexamApplyDAO.update(zfryzgksDqdwb);
		}
		/*String sql = "update zfryks_apply set gzdwxz='"+zfryzgksDqdwb.getDwxz()+"', dwjb='"+zfryzgksDqdwb.getDwjb()+"'where sqdu='"+zfryzgksDqdwb.getDistrictId()+"'";
		exelawtipstaffexamApplyDAO.executeSql(sql);*/
		
	}
	/**
	 * ���ݵ�����ȡ��λ��Ϣ    
	 * @param districtId
	 * @return ZfryzgksDqdwb
	 * @author glw 
	 * @throws HibernateException 
	 * @Create Date 2017 ����5:44:42
	 */
	public ZfryzgksDqdwb getzfryzgksDqdwb(String districtId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "from ZfryzgksDqdwb zfryzgksDqdwb where districtId='"+districtId+"'";
		ZfryzgksDqdwb zfryzgksDqdwb = new ZfryzgksDqdwb();
		List zfryzgksDqdwbList = exelawtipstaffexamApplyDAO.query(sql);
		if(zfryzgksDqdwbList.size()>0){
			zfryzgksDqdwb = (ZfryzgksDqdwb) zfryzgksDqdwbList.get(0);
		}
		return zfryzgksDqdwb;
	}
	/**
	 * �ж��Ƿ���ʹ�ô�ִ��֤��    
	 * @param nsyzfzh
	 * @return boolean
	 * @author wjk 
	 * @throws HibernateException 
	 * @Create Date 2017 ����3:50:59
	 */
	public boolean checkNsyzfzh(String nsyzfzh,String xm) throws HibernateException {
		boolean isCanUse = true;
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String  hql = " from ZfryzgksApply zfryzgksApply where zfryzgksApply.nsyzfzh='"+nsyzfzh+"' and zfryzgksApply.xm!='"+xm+"'";
		int  count = exelawtipstaffexamApplyDAO.count(hql);
		if(count>0){
			isCanUse = false;	
		}
		return  isCanUse;
	}
	
	/***
	 * �����������
	 * 
	 * @param year
	 * @param string
	 * @return List
	 * @author glw
	 * @Create Date 2017 ����2:30:32
	 */
	public Map getKcfpList(String year, String provinceId) {
		ZfksJDBCDAO ZfksJDBCDAO = new ZfksJDBCDAO();
		List kcfpList = ZfksJDBCDAO.getKcfpqk(year, provinceId);
		Map map = new LinkedHashMap();
		for (int i = 0; i < kcfpList.size(); i++) {
			Kcfpqk kcfpqk = (Kcfpqk) kcfpList.get(i);
			String key = "����:" + kcfpqk.getKaoquName() + " | ����:" + kcfpqk.getKaodian();
			if (null == map.get(key)) {
				List list = new ArrayList();
				map.put(key, list);
			}
			List list = (List) map.get(key);
			list.add(kcfpList.get(i));
		}
		
		return map;
	}
	/**
	 * �����ֲ����    
	 * @param kcId
	 * @return List
	 * @author glw 
	 * @throws HibernateException 
	 * @Create Date 2017 ����6:26:30
	 */
	public List getkcfbList(String kcId) throws HibernateException {
		ZfksJDBCDAO zfksJDBCDAO = new ZfksJDBCDAO();
		String sql = " SELECT zfryksZw.zwh,zfryksZw.zkzh,zfryzgksApply.xm from zfryks_zw zfryksZw left join zfryks_apply zfryzgksApply on zfryksZw.zkzh=zfryzgksApply.zkzh where zfryksZw.kc_id='" + kcId + "' order by zfryksZw.zwh";
		List zfryzgksDqdwbList = zfksJDBCDAO.getzwListBySql(sql);
		return zfryzgksDqdwbList;
	}
	/**
	 * ���ݿ���id��ȡ������Ϣ   
	 * @param kcId
	 * @return ZfryksKc
	 * @author glw 
	 * @throws HibernateException 
	 * @Create Date 2017 ����6:46:03
	 */
	public ZfryksKc getckById(String kcId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryksKc zfryksKc = (ZfryksKc) exelawtipstaffexamApplyDAO.load(kcId, ZfryksKc.class);
		return zfryksKc;
	}
	
	public ZfryzgksKcgl getZfryzgksKcglByKcId(String kcId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String hql = "select zfryzgksKcgl from ZfryzgksKcgl as zfryzgksKcgl, ZfryksKc as zfryksKc where zfryksKc.kdId = zfryzgksKcgl.id and zfryksKc.id = '" + kcId + "'";
		List list = exelawtipstaffexamApplyDAO.query(hql);
		if(null != list && !list.isEmpty()){
			return (ZfryzgksKcgl)list.get(0);
		}
		
		return null;
	}
	
	/**
	 * ��ȡ��λ��������
	 * 
	 * @return String
	 * @author glw
	 * @param sqdu
	 * @throws HibernateException
	 * @Create Date 2017 ����9:10:46
	 */
	public String findSqdwmc(String sqdu) throws HibernateException {
		String jsonString = null;
		if(!sqdu.equals("")){
			sqdu = sqdu.substring(0, 2);
		}
		JSONArray jsonArray=null;  
		//�˴���ѯȫ���ĵ�λ��Ϣ��
        String hSql="select zfryzgksDwgl.dwmc from ZfryzgksDwgl as zfryzgksDwgl where districtId like '"+sqdu+"%'";  
        ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
        List objList=exelawtipstaffexamApplyDAO.query(hSql); 
        if (objList!=null&&objList.size()>0) {  
            jsonArray=new JSONArray();  
            for (int i = 0; i < objList.size(); i++) { 
            	//ZfryzgksDwgl zfryzgksDwgl =  (ZfryzgksDwgl) objList.get(i);
            	Map map= new HashMap();
                map.put("dwmc",objList.get(i));
                /*map.put("dwxz",zfryzgksDwgl.getDwxz()); 
                if("���ּ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","1"); 
                }
                if("�����ּ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","2"); 
                }
                if("����".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","3"); 
                }
                if("������".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","4"); 
                }
                if("�Ƽ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","5"); 
                }
                if("���Ƽ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","6"); 
                }
                if("�ɼ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","7"); 
                }
                if("���ɼ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","8"); 
                }
                if("���ɼ�����".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","8"); 
                }
                if("/".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","8"); 
                }*/
                jsonArray.put(map);  
            }  
            jsonString = jsonArray.toString();
			objList = null;
			jsonArray = null;
        }  
    return jsonString;  
	}
	/**
	 * ������λ�б�   
	 * @param pagePra
	 * @return List
	 * @author glw 
	 * @param districtId 
	 * @throws HibernateException 
	 * @Create Date 2017 ����9:48:26
	 */
	public List getBaokaoDanWeiList(int[] pagePra, String districtId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "from ZfryzgksDwgl zfryzgksDwgl where zfryzgksDwgl.districtId='"+districtId+"'";
		List baoKaoDanWeiList = exelawtipstaffexamApplyDAO.query(sql, pagePra);
		return baoKaoDanWeiList;
	}
	/**
	 * ����id��ȡ��λ����   
	 * @param danweiId
	 * @return ZfryzgksDwgl
	 * @author glw 
	 * @throws HibernateException 
	 * @Create Date 2017 ����10:40:30
	 */
	public ZfryzgksDwgl getDanWeiById(String danweiId) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksDwgl zfryzgksDwgl  =  (ZfryzgksDwgl) exelawtipstaffexamApplyDAO.load(danweiId, ZfryzgksDwgl.class);
		return zfryzgksDwgl;
	}
	/**
	 * �Ա�����λ�ı�������޸�   
	 * @param actionFormMap2
	 * @return ZfryzgksDwgl
	 * @author glw 
	 * @throws HibernateException 
	 * @throws CnfmMessageException 
	 * @Create Date 2017 ����3:13:04
	 */
	public void saveOrUpdateZfryzgksDwgl(Map actionFormMap2) throws HibernateException, CnfmMessageException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksDwgl zfryzgksDwgl = new ZfryzgksDwgl();
		String id = StaticMethod.nullObject2String(actionFormMap2.get("id"));
		MyBeanUtils.copyProperties(zfryzgksDwgl, actionFormMap2, null);
		String districtId = StaticMethod.nullObject2String(actionFormMap2.get("districtId"));
		String shenfenId = districtId.substring(0, 2)+"0000";
		zfryzgksDwgl .setShenfenId(shenfenId);
		if(id.equals("")){
			exelawtipstaffexamApplyDAO.save(zfryzgksDwgl);
		}else {
			exelawtipstaffexamApplyDAO.update(zfryzgksDwgl);
		}
		//CacheInstance.getInstance().addOrUpdateDwgl(zfryzgksDwgl);
	}
	/**
	 * ��֤��λ�����Ƿ���ڣ���������Ϣ   
	 * @param districtId
	 * @return String
	 * @author glw 
	 * @param dwmc 
	 * @throws HibernateException 
	 * @Create Date 2017 ����4:07:59
	 */
	public ZfryzgksDwgl checkdwsfyy(String districtId, String dwmc) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		ZfryzgksDwgl zfryzgksDwgl = new ZfryzgksDwgl();
		String shenfenId = districtId.substring(0, 2)+"0000";
		String sql = "from ZfryzgksDwgl zfryzgksDwgl where zfryzgksDwgl.shenfenId='"+shenfenId+"' and zfryzgksDwgl.dwmc='"+dwmc+"'";
		List bkdwglList = exelawtipstaffexamApplyDAO.query(sql);
		if(bkdwglList.size()>0){
			zfryzgksDwgl = (ZfryzgksDwgl) bkdwglList.get(0);
		}
		return zfryzgksDwgl;
	}
	public boolean checkBmsj(ZfryksBmsj zfryksBmsj) {
		boolean  returnValue = false;
		Timestamp nowTime = StaticMethod.getTimestamp();
		if (nowTime.after(zfryksBmsj.getApplyBegdate()) && nowTime.before(zfryksBmsj.getApplyEnddate())) { 
			returnValue =  true;
		}
		return returnValue;
	}
	
	public boolean checkBmsjForUpdate(ZfryksBmsj zfryksBmsj) {
		boolean  returnValue = false;
		Timestamp nowTime = StaticMethod.getTimestamp();
		if (nowTime.after(zfryksBmsj.getGxsjKs()) && nowTime.before(zfryksBmsj.getGxsjJs())) { 
			returnValue =  true;
		}
		return returnValue;
	}
	/**
	 * ��ѯ��ʡ��λ    
	 * @param pagePra
	 * @param districtId
	 * @param dwmc
	 * @return List
	 * @author glw 
	 * @throws HibernateException 
	 * @Create Date 2017 ����11:56:45
	 */
	public List getqueryBaokaoDanWeiList(int[] pagePra, String districtId, String dwmc) throws HibernateException {
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String sql = "from ZfryzgksDwgl zfryzgksDwgl where zfryzgksDwgl.shenfenId='"+districtId+"' and zfryzgksDwgl.dwmc like'%"+dwmc+"%'";
		List bkdwglList = exelawtipstaffexamApplyDAO.query(sql,pagePra);
		return bkdwglList;
	}
	/**
	 * ���ֲ�ѯ�л�ȡ�������ݴ������֤�б�
	 * @param pagePra
	 * @return
	 * @throws CnfmMessageException 
	 * @throws HibernateException 
	 */
	public List getZzSjcwList() throws CnfmMessageException, HibernateException {
		ZfryzgksApplyQO zfryzgksApplyQO = new ZfryzgksApplyQO();
		MyBeanUtils.copyProperties(zfryzgksApplyQO, actionFormMap, null);
		ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
		String hql = zfryzgksApplyQO.getQueryZzSjcwHql();
		List list = exelawtipstaffexamApplyDAO.query(hql);
		List returnList = new ArrayList();
		if (list.size() > 0 || !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
				ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
				zfryzgksApply = (ZfryzgksApply) list.get(i);
				MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				returnList.add(zfryzgksApplyVO);
			}
		}
		return returnList;
	}
	/***
	 * ���ݲ�ѯ�������ݵ��������е���Ƭ�ļ���ѹ�����ļ���
	 * @param list
	 * @param attPath 
	 * @param districtId 
	 * @param request 
	 * @throws SQLException 
	 */
	public List exportFileByQueryList(List list, String attPath, String districtId) throws SQLException {
		FileUtil fileUtil = new FileUtil();
		TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO();
		String todayString = this.getNowString();
		//���������ļ���
		List zpExcelList = new ArrayList(); 
		List zpList = new ArrayList(); 
		if(null != list && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				ZfryzgksApply zfryzgksApply = (ZfryzgksApply) list.get(i);
				TawAttachment tawAttachment = new TawAttachment();
				tawAttachment = tawAttachmentDAO.retrieve(zfryzgksApply.getZp());
				String tawAttachmentFileName = "";
				String tawAttachmentFileNameJw[] = null;
				String attTawAttachmentFileName = "";
				String tawAttachmentFileNameSrc = "";
				if(!StaticMethod.nullObject2String(tawAttachment).equals("")){
					attTawAttachmentFileName = tawAttachment.getAttachmentFilename();
					tawAttachmentFileNameJw = tawAttachment.getAttachmentFilename().split("\\.");
					if(!StaticMethod.nullObject2String(zfryzgksApply.getNsyzfzh()).equals("")){
						tawAttachmentFileName = zfryzgksApply.getNsyzfzh()+"."+tawAttachmentFileNameJw[1];
					}else{
						tawAttachmentFileName = "CWSJ"+i+"."+tawAttachmentFileNameJw[1];
					}
				}else{
						tawAttachmentFileName = "û����Ƭ";
				}
				//attTawAttachmentFileName = attPath+"/"+attTawAttachmentFileName;
				//tawAttachmentFileNameSrc = attPath+"/"+tawAttachmentFileName;
				zfryzgksApply.setZpsrc(attPath+""+attTawAttachmentFileName);
				zfryzgksApply.setZpmc(tawAttachmentFileName);
				if("000000".equals(districtId)){
					if(!StaticMethod.nullObject2String(zfryzgksApply.getNsyzfzh()).equals("")){
						if("00".equals(zfryzgksApply.getNsyzfzh().substring(2,4))&&"ͨ��".equals(zfryzgksApply.getKscj())){
							zpExcelList.add(zfryzgksApply); 
							continue;
						}
					}else{
						if("0000".equals(zfryzgksApply.getSqdu().substring(2,6))&&"ͨ��".equals(zfryzgksApply.getKscj())){
							zpExcelList.add(zfryzgksApply); 
							continue;
						}
					}
				}else if("0000".equals(districtId.substring(2,6))&&!"000000".equals(districtId)){
					if(!StaticMethod.nullObject2String(zfryzgksApply.getNsyzfzh()).equals("")){
						if(!"00".equals(zfryzgksApply.getNsyzfzh().substring(2,4))&&"ͨ��".equals(zfryzgksApply.getKscj())){
							zpExcelList.add(zfryzgksApply); 
							continue;
						}
					}else{
						if("ͨ��".equals(zfryzgksApply.getKscj())){
							zpExcelList.add(zfryzgksApply); 
							continue;
						}
					}
					
				}else{
					if("ͨ��".equals(zfryzgksApply.getKscj())){
						zpExcelList.add(zfryzgksApply); 
						continue;
					}
				}
				
			}
			//boolean result=true;
			//result=ZipUtil.zipForPasswordMatch(FileConst.WindowsPathRar, null, null, todayString, null,null);
		}
		return zpExcelList;
	}
	
	/**
	 * @param excelname
	 *            �ļ���
	 * @param map
	 *            ����
	 * @param excelpath
	 *            �ļ����·��
	 * @return author yutong
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static String getPoiExcel(String excelname, Map map, String excelpath,List list,String attPath) throws RowsExceededException, WriteException, BiffException, SQLException, IOException {
		TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO();
		System.out.println("ģ������ = " + excelname);
		String path = "";
		String fromFile = "";
		String toFile = "";
		String zptoFold = "";
		String zpxztoFold = "";
		FileUtil fileUtil = new FileUtil();
		Map Columelistmap = new HashMap();// �ֶ�������
		Map CellStylelistmap = new HashMap();// ��Ԫ���ʽ����
		Map ObjectNumlistmap = new HashMap();// �ֶ����ڵĶ���λ��
		Map CellStartPointlistmap = new HashMap();// ��ȡ�ֶ��б���ʼ������
		Map CellPointlistmap = new HashMap();// ��ȡ�ֶ��б����е�����
		fromFile = excelpath + File.separator + excelname + ".xls";
		toFile = excelpath + File.separator + "output" + File.separator + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".xls";
		String zipFile = excelpath + File.separator + "output" + File.separator + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".zip";
		
		String exportfiletemplate = excelpath.substring(0, excelpath.indexOf("WEB-INF")) + "exportfiletemplate" + File.separator;
		toFile = exportfiletemplate + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".xls";
		zpxztoFold = exportfiletemplate + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + "zfryks";
		fileUtil.newFolder(zpxztoFold);
		zptoFold = zpxztoFold + File.separator+ "zfksbmzp";
		zipFile = exportfiletemplate + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".zip";
		
		CopyFile.copyFile(fromFile, toFile);
		long d1 = System.currentTimeMillis();
		int dataMaxLenth = new PublicMethod().getDataMapMaxLength(map);// ��ȡ������ݼ�����
		if (dataMaxLenth > 3) {
			// ʹ��JXL������ʽ
			JxlReadExcel JxlReadExcel = new JxlReadExcel();
			Columelistmap = JxlReadExcel.readExcel(toFile, Columelistmap);
			// CellStylelistmap = JxlReadExcel.getCellStyle(toFile,
			// CellStylelistmap);
			ObjectNumlistmap = JxlReadExcel.getObjectNum(toFile, ObjectNumlistmap);
			CellStartPointlistmap = JxlReadExcel.getCellStartPoint(toFile, CellStartPointlistmap);
			CellPointlistmap = JxlReadExcel.getCellPointAll(toFile, CellPointlistmap);
			long d2 = System.currentTimeMillis();
			System.out.println("ʹ��JXL���ļ�������ʱ��:" + (d2 - d1));
			try {
				JxlReadExcel.setExcel(Columelistmap, CellStylelistmap, ObjectNumlistmap, CellStartPointlistmap, CellPointlistmap, toFile, map);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// ʹ��Poi������ʽ
			ReadExcel re = new ReadExcel();
			Columelistmap = re.readExcel(toFile, Columelistmap);
			CellStylelistmap = re.getCellStyle(toFile, CellStylelistmap);
			ObjectNumlistmap = re.getObjectNum(toFile, ObjectNumlistmap);
			CellStartPointlistmap = re.getCellStartPoint(toFile, CellStartPointlistmap);
			CellPointlistmap = re.getCellPointAll(toFile, CellPointlistmap);
			long d2 = System.currentTimeMillis();
			System.out.println("ʹ��POI���ļ�������ʱ��:" + (d2 - d1));
			try {
				re.setExcel(Columelistmap, CellStylelistmap, ObjectNumlistmap, CellStartPointlistmap, CellPointlistmap, toFile, map);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fileUtil.newFolder(zptoFold);
		for(int i=0;i<list.size();i++){
			ZfryzgksApply zfryzgksApply = (ZfryzgksApply) list.get(i);
			TawAttachment tawAttachment = new TawAttachment();
			tawAttachment = tawAttachmentDAO.retrieve(zfryzgksApply.getZp());
			String tawAttachmentFileName = "";
			String tawAttachmentFileNameJw[] = null;
			String attTawAttachmentFileName = "";
			String tawAttachmentFileNameSrc = "";
			if(!StaticMethod.nullObject2String(tawAttachment).equals("")){
				attTawAttachmentFileName = tawAttachment.getAttachmentFilename();
				tawAttachmentFileNameJw = tawAttachment.getAttachmentFilename().split("\\.");
				if(!StaticMethod.nullObject2String(zfryzgksApply.getNsyzfzh()).equals("")){
					tawAttachmentFileName = zfryzgksApply.getNsyzfzh()+"."+tawAttachmentFileNameJw[1];
				}else{
					tawAttachmentFileName = "CWSJ"+i+"."+tawAttachmentFileNameJw[1];
				}
				fileUtil.copyFile(attPath+""+attTawAttachmentFileName,zptoFold+File.separator+tawAttachmentFileName);
			}
		}
		fileUtil.copyFile(toFile,zpxztoFold+File.separator+StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".xls");
		appendZip(zpxztoFold, zipFile);
		path = zipFile;
		return path;
	}
	
	
	
	/**
	 * �����ļ����
	 * 
	 * @param srcPathName
	 * @param pathName
	 *            void
	 * @author yutong
	 * @Create Date 2011-2-11 ����03:58:27
	 */
	public static void appendZip(String srcPathName, String pathName) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists())
			throw new RuntimeException(srcPathName + "�����ڣ�");
		FileSet fileSet = new FileSet();
		if (!srcdir.isDirectory()) {
			fileSet.setIncludes(srcdir.getName());
			String parentPathName = srcdir.getParent();
			srcdir = new File(parentPathName);
		}
		fileSet.setDir(srcdir);
		File zipFile = new File(pathName);
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		fileSet.setProject(prj);
		zip.addFileset(fileSet);
		zip.execute();
	}
	
	
	public  String getNowString() {
		return getNowString("yyyy_MM_dd_kk_mm");
	}
	
	public  String getNowString(String format) {
		Calendar calendar = Calendar.getInstance();
		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}

	public Map getDwxzAndDwjb(String dwmc, String sqdu) throws HibernateException {
		String jsonString = null;
		if(!sqdu.equals("")){
			sqdu = sqdu.substring(0, 2);
		}
		//�˴���ѯȫ���ĵ�λ��Ϣ��
		Map map= new HashMap();
        String hSql="from ZfryzgksDwgl  where districtId like '"+sqdu+"%' and dwmc='"+dwmc+"'";  
        ExelawtipstaffexamApplyDAO exelawtipstaffexamApplyDAO = new ExelawtipstaffexamApplyDAO();
        List objList=exelawtipstaffexamApplyDAO.query(hSql); 
        if (objList!=null&&objList.size()>0) {  
            	ZfryzgksDwgl zfryzgksDwgl =  (ZfryzgksDwgl) objList.get(0);
                map.put("dwxz",zfryzgksDwgl.getDwxz());
                if("���ּ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","1"); 
                }
                if("�����ּ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","2"); 
                }
                if("����".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","3"); 
                }
                if("������".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","4"); 
                }
                if("�Ƽ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","5"); 
                }
                if("���Ƽ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","6"); 
                }
                if("�ɼ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","7"); 
                }
                if("���ɼ�".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","8"); 
                }
                if("���ɼ�����".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","8"); 
                }
                if("/".equals(zfryzgksDwgl.getDwjb())){
                	map.put("dwjb","8"); 
                }
            }  
    return map;  
	}
}
