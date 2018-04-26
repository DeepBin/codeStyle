
package com.boco.eoms.exelawtipstaffexam.dao;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import com.boco.eoms.common.dao.HibernateDAO;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksKc;

public class ZfryKcfpDAO extends HibernateDAO {
	
	public ZfryksKc getkdIdBykcId(String kcid) throws HibernateException {
		ZfryksKc zfryksKc = new ZfryksKc();
		String hql = "from ZfryksKc zfryksKc where zfryksKc.id='" + kcid + "'";
		List zfryksKcList = query(hql);
		if (zfryksKcList.size() > 0) {
			zfryksKc = (ZfryksKc) zfryksKcList.get(0);
		}
		
		return zfryksKc;
	}
	
	public List getZwHaveZkzhInKd(String kdId, int batchNo) throws HibernateException{
		String hql = "select zfryksZw from ZfryzgksApply as zfryzgksApply, ZfryksZw as zfryksZw, ZfryksKc as zfryksKc, ZfryzgksKcgl as zfryzgksKcgl "
				+ "where zfryzgksApply.zkzh = zfryksZw.zkzh "
				+ "and zfryksZw.kcId = zfryksKc.id "
				+ "and zfryksKc.kdId = zfryzgksKcgl.id " 
				+ "and zfryzgksKcgl.id = '" + kdId + "' "
				+ "and zfryzgksApply.sbpc = " + batchNo + " "
				+ "and zfryksZw.zkzh is not null " 
				+ "order by zfryksZw.kch, zfryksZw.zwh"; 
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getApplyHaveZwInKd(String kdId, int batchNo) throws HibernateException{
		String hql = "select zfryzgksApply from ZfryzgksApply as zfryzgksApply, ZfryksZw as zfryksZw, ZfryksKc as zfryksKc, ZfryzgksKcgl as zfryzgksKcgl "
				+ "where zfryzgksApply.zkzh = zfryksZw.zkzh "
				+ "and zfryksZw.kcId = zfryksKc.id "
				+ "and zfryksKc.kdId = zfryzgksKcgl.id " 
				+ "and zfryzgksKcgl.id = '" + kdId + "' " 
				+ "and zfryzgksApply.sbpc = " + batchNo + " "
				+ "and zfryksZw.zkzh is not null " 
				+ "order by zfryzgksApply.kch, zfryzgksApply.zwh"; 
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getApplyNoZwh(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "select zfryzgksApply from ZfryzgksApply as zfryzgksApply " 
				+ "where zfryzgksApply.nd = '" + year + "' " 
				+ "and zfryzgksApply.sqdu like '" + districtProvince + "%' "
				+ "and (zfryzgksApply.zwh is null or zfryzgksApply.zwh = '') " 
				+ "order by zfryzgksApply.sqdu";
		
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getApplyNoZwhBySqdq(String year, String provinceId, String sqdq) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "select zfryzgksApply from ZfryzgksApply as zfryzgksApply " 
				+ "where zfryzgksApply.sqdu = '" + sqdq + "' " 
				+ "and zfryzgksApply.sqdu like '" + districtProvince + "%' "
				+ "and zfryzgksApply.nd = '" + year + "' " 
				+ "and (zfryzgksApply.zwh is null or zfryzgksApply.zwh = '') " 
				+ "order by zfryzgksApply.sqdu";
		
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getApplyNoZkzh(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "select zfryzgksApply from ZfryzgksApply as zfryzgksApply " 
				+ "where zfryzgksApply.nd = '" + year + "' " 
				+ "and zfryzgksApply.sqdu like '" + districtProvince + "%' "
				+ "and (zfryzgksApply.zkzh is null or zfryzgksApply.zkzh = '') " 
				+ "order by zfryzgksApply.sqdu";
		
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getAllKq(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "from ZfryzgksKd as zfryzgksKd "
				+ "where zfryzgksKd.id in (select zfryzgksKcgl.kqid from ZfryzgksKcgl as zfryzgksKcgl where zfryzgksKcgl.nd = '" + year + "') and zfryzgksKd.kqId like '" + districtProvince + "%'";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		
		return query.list();
	}
	
	public List getAllKd(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "from ZfryzgksKcgl as zfryzgksKcgl "
				+ "where zfryzgksKcgl.nd = '" + year + "' "
				+ "and zfryzgksKcgl.kaoqu like '" + districtProvince + "%' order by zfryzgksKcgl.kaoqu";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		
		return query.list();
	}
	
	public List getAllKc(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "select zfryksKc from ZfryksKc as zfryksKc, ZfryzgksKcgl as zfryzgksKcgl " 
				+ "where zfryksKc.kdId = zfryzgksKcgl.id " 
				+ "and zfryzgksKcgl.nd = '" + year 
				+ "' and zfryzgksKcgl.kaoqu like '" + districtProvince + "%'";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getZwNoZkzh(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		String hql = "select zfryksZw from ZfryksZw as zfryksZw, ZfryksKc as zfryksKc, ZfryzgksKcgl as zfryzgksKcgl " 
				+ "where zfryksZw.kcId = zfryksKc.id " 
				+ "and zfryksKc.kdId = zfryzgksKcgl.id " 
				+ "and zfryzgksKcgl.nd = '" + year + "' and zfryzgksKcgl.kaoqu like '" + districtProvince + "%'";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	/**
	 * ���������޸Ŀ������������ٿ���ʱ����������ĳЩ�����ᱻɾ������ʱ��ɾ���Ŀ�����Ӧ����λҲӦ�ñ�ɾ����<br>
	 * ���ڿ���������������ȷ���ĸ�����Ŀ�����ɾ��������ȫ�������жϣ���������ʡ�ݵ��жϡ�<br>
	 * �˴�bug�������������ʡ�ݣ������³���ʡ�����п�����λ��Ϣ��ɾ����
	 * @param year
	 * @param provinceId
	 * @return
	 * @throws HibernateException
	 */
	public List getAllZwByBeletedKc(String year, String provinceId) throws HibernateException {
		String districtProvince = provinceId.substring(0, 2);
		System.out.println("getAllZwByBeletedKc: districtProvince = " + districtProvince);
		String hql = "from ZfryksZw as zfryksZw " 
				+ "where zfryksZw.kcId not in "
				+ "(select zfryksKc.id from ZfryksKc as zfryksKc, ZfryzgksKcgl as zfryzgksKcgl where zfryksKc.kdId = zfryzgksKcgl.id and zfryzgksKcgl.nd = '" + year + "') ";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getZwByKcId(String kcId) throws HibernateException {
		String hql = "from ZfryksZw as zfryksZw where zfryksZw.kcId = '" + kcId + "' order by zfryksZw.zwh";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List getZwByKdId(String kdId) throws HibernateException {
		String hql = "select zfryksZw from ZfryksZw as zfryksZw, ZfryksKc as zfryksKc where (zfryksZw.zkzh is null or zfryksZw.zkzh = '') and zfryksZw.kcId = zfryksKc.id and zfryksKc.kdId = '" + kdId + "'";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
}
