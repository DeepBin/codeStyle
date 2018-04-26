
package com.boco.eoms.exelawtipstaffexam.qo;

import com.boco.eoms.common.oo.QueryDataObject;
import com.boco.eoms.jbzl.dao.impl.DistrictDAO;

public class ZfryzgksApplyQO extends QueryDataObject {
	
	public ZfryzgksApplyQO() {
	}
	
	private String poName = "zfryzgksApply";
	
	private String clauseSql = " ";
	
	private String shipDistricSql ="";
	
	
	/** �������� */
	private String sqdu;
	
	private String gzdw;
	
	private String  whcd;
	
	private String  xb;
	
	private String zj;
	
	private String xm;
	
	private String zkzh;
	
	private String zjslfs;
	
	private String gzdwmc;
	
	private String xrzw;
	
	private String kscj;
	
	private String sfzzxz;
	
	
	/** ��������*/
	public void setSqdu(String sqdu) {
		String includeDownDis ="1";
		if (sqdu != null && !sqdu.equals("")) {
			DistrictDAO districtDAO = new DistrictDAO();
			if (includeDownDis != null && includeDownDis.equals("1")) {
				shipDistricSql = districtDAO.getDistrictSql(sqdu, "zfryzgksApply.sqdu");
			} else if (includeDownDis != null && includeDownDis.equals("2")) {
				shipDistricSql = " and zfryzgksApply.sqdu='" + sqdu + "'";
			} else {
				shipDistricSql = districtDAO.getDistrictSql(sqdu, "zfryzgksApply.sqdu");
			}
		}
		this.sqdu = sqdu;
	}

	/**ְ�� */
	public void setZj(String zj) {
		this.clauseSql += addEqualClause(poName, "zj", zj);
		this.zj = zj;
	}
	
	/**������λ���� */
	public void setGzdwmc(String gzdwmc) {
		this.clauseSql += addLikeClause(poName, "gzdwmc", gzdwmc);
		this.gzdwmc = gzdwmc;
	}
	
	/**����ְ�� */
	public void setXrzw(String xrzw) {
		this.clauseSql += addEqualClause(poName, "xrzw", xrzw);
		this.xrzw = xrzw;
	}
	
	/**���Գɼ� */
	public void setKscj(String kscj) {
		this.clauseSql += addEqualClause(poName, "kscj", kscj);
		this.kscj = kscj;
	}

	/**�Ա� */
	public void setXb(String xb) {
		this.clauseSql += addEqualClause(poName, "xb", xb);
		this.xb = xb;
	}
	
	public void setZjslfs(String zjslfs){
		this.clauseSql += addEqualClause(poName, "zjslfs", zjslfs);
		this.zjslfs = zjslfs;
	}
	/**
	 * �Ļ��̶�   
	 */
	public void setWhcd(String whcd) {
		this.clauseSql += addEqualClause(poName, "whcd", whcd);
		this.whcd = whcd;
	}
	/**
	 * ������λ   
	 */
	public void setGzdw(String gzdw) {
		if (gzdw != null && !gzdw.equals("")) {
			clauseSql += " and zfryzgksApply.gzdw like '%" + gzdw + "%'";
		}
		this.gzdw = gzdw;
	}

	public void setXm(String xm) {
		if (xm != null && !xm.equals("")) {
			clauseSql += " and zfryzgksApply.xm like '%" + xm + "%'";
		}
		this.xm = xm;
	}

	public void setZkzh(String zkzh) {
		if (zkzh != null && !zkzh.equals("")) {
			clauseSql += " and zfryzgksApply.zkzh like '%" + zkzh + "%'";
		}
		this.zkzh = zkzh;
	}
	
	/** ��ѯʱѡ���Ƿ��ѯ����ֻ����֤��Ա */
	public void setSfzzxz(String sfzzxz) {
		if (sfzzxz != null && !sfzzxz.equals("")) {
			clauseSql += " and ((zfryzgksApply.nsyzfzh is null or zfryzgksApply.nsyzfzh ='') or (zfryzgksApply.xrzw is null or zfryzgksApply.xrzw ='') or (zfryzgksApply.gzdwmc is null or zfryzgksApply.gzdwmc =''))";
		} else {
			// clauseSql += " and ((zfryzgksApply.nsyzfzh is not null and zfryzgksApply.nsyzfzh !='') and (zfryzgksApply.xrzw is not null and zfryzgksApply.xrzw !='') and (zfryzgksApply.gzdwmc is not null and zfryzgksApply.gzdwmc !=''))";
		}
		this.sfzzxz = sfzzxz;
	}

	/**
	 * ��ѯ    
	 * @param districtId
	 * @return String
	 * @author wjk 
	 * @Create Date 2017 ����1:54:13
	 */
	public String getQueryHql(String sqdu,String districtId) {
		String districtIds = "";
		DistrictDAO districtDAO = new DistrictDAO();
		if(sqdu.equals("")){
			districtIds = districtId;
		}else{
			districtIds = sqdu;
		}
		shipDistricSql = districtDAO.getDistrictSql(districtIds, "zfryzgksApply.sqdu");
		String hql ="from  ZfryzgksApply zfryzgksApply where 1=1"+clauseSql+shipDistricSql + " order by zfryzgksApply.sqdu,zfryzgksApply.gzdwmc,zfryzgksApply.zkzh";
		return hql;
	}
	/**
	 * �ϱ���ѯ�б�sql   
	 * @param string
	 * @param districtId
	 * @return String
	 * @author glw 
	 * @Create Date 2017 ����11:04:12
	 */
	public String getshangbaoQueryHql(String string, String districtId) {
		String districtIds = "";
		DistrictDAO districtDAO = new DistrictDAO();
		if(sqdu.equals("")){
			districtIds = districtId;
		}else{
			districtIds = sqdu;
		}
		shipDistricSql = districtDAO.getDistrictSql(districtIds, "zfryzgksApply.sqdu");
		String hql = "from ZfryzgksApply zfryzgksApply ,ZfryzgksCheckControl zcc where 1=1 and zcc.applyId=zfryzgksApply.id and zcc.districtId='" + districtId + "' and zcc.state in(3,4) and zcc.stepFlag=0 and zcc.delFlag=0 "+clauseSql+""+shipDistricSql+" order by zfryzgksApply.sqdu,zfryzgksApply.gzdwmc,zfryzgksApply.sfzh";
		//String hql ="from  ZfryzgksApply zfryzgksApply where 1=1"+clauseSql+shipDistricSql;
		return hql;
	}
	/**
	 * ���ֲ�ѯ��֤���ݴ����б�sql
	 * @return
	 */
	public String getQueryZzSjcwHql() {
		String hql ="from  ZfryzgksApply zfryzgksApply where 1=1"+clauseSql+" and zfryzgksApply.sqdu like '%0000' order by zfryzgksApply.sqdu,zfryzgksApply.gzdwmc,zfryzgksApply.zkzh";
		return hql;
	}
	
	
}
