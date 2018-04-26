
package com.boco.eoms.exelawtipstaffexam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.cache.ICacheDAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.BocoConnection;
import com.boco.eoms.db.util.DBStaticMethod;
import com.boco.eoms.exelawtipstaffexam.model.BestSqdqAndKdId;
import com.boco.eoms.exelawtipstaffexam.model.Kcfpqk;
import com.boco.eoms.exelawtipstaffexam.model.Kcfpzwqk;
import com.boco.eoms.exelawtipstaffexam.model.StringValue;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksXh;

public class ZfksJDBCDAO extends ICacheDAO {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
	
	public ZfksJDBCDAO() {
	}
	
	public void deleteZW(String year, String provinceId) {
		PreparedStatement pstmt = null;
		BocoConnection conn = null;
		String sql = "delete from zfryks_zw where kc_id in (select id from zfryks_kc where kd_id in (select id from zfryks_kcgl where kaoqu like '" + provinceId.substring(0, 2) + "%'))";
		System.out.println("deleteZW: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			DBStaticMethod.commit(conn);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_deleteZW_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
	}
	
	public void deleteKC(String year, String provinceId) {
		PreparedStatement pstmt = null;
		BocoConnection conn = null;
		String sql = "delete from zfryks_kc where kd_id in (select id from zfryks_kcgl where kaoqu like '" + provinceId.substring(0, 2) + "%')";
		System.out.println("deleteKC: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			DBStaticMethod.commit(conn);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_deleteKC_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
	}
	
	public void resetAllApplyKchAndZwh(String year, String provinceId) {
		PreparedStatement pstmt = null;
		BocoConnection conn = null;
		String sql = "update zfryks_apply set kcid = null, kch = null, zwh = null where nd = '" + year 
				+ "' and sqdu like '" + provinceId.substring(0, 2) + "%'";
		System.out.println("resetAllApplyKchAndZwh: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			DBStaticMethod.commit(conn);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_resetAllApplyKchAndZwh_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
	}
	
	/**
	 * 执行sql
	 * 
	 * @param list
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午10:15:05
	 */
	public void executeJDBCSqlList(List list) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		try {
			conn = this.ds.getConnection();
			conn.setAutoCommit(false);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println("sqlList:" + list.get(i));
					pstmt = conn.prepareStatement(StaticMethod.nullObject2String(list.get(i)));
					pstmt.executeUpdate();
				}
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.commit(conn);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
	}
	
	public List getDistrictIdMaxXh(String year, String provinceId) {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = this.getSQLForDistrictIdMaxXh(year, provinceId);
		System.out.println("getDistrictIdMaxXh: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ZfryksXh zfryksXh = new ZfryksXh();
				zfryksXh.setDistrictId(rs.getString("districtId"));
				zfryksXh.setMaxXh(Integer.valueOf(rs.getInt("maxXh")));
				list.add(zfryksXh);
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		
		return list;
	}
	
	private String getSQLForDistrictIdMaxXh(String year, String provinceId) {
		if (provinceId.length() == 6)
			provinceId = provinceId.substring(0, 2);
		String sql = "select districtId, max(xh) as maxXh from (select SUBSTRING(zkzh, 3, 6) as districtId, cast(SUBSTRING(zkzh, 9,11) as int) xh " 
			+ "from zfryks_apply " 
			+ "where zkzh is not null and nd = '" + year 
			+ "' and sqdu like '" + provinceId + "%') as temp " 
			+ "group by districtId";
		System.out.println("getSQLForDistrictIdMaxXh: " + sql);
		return sql;
	}
	
	public int getMaxKch(String year, String provinceId) {
		int maxKch = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = "select max(kch) as maxKch from zfryks_kc t1, zfryks_kcgl t2 where t1.kd_id = t2.id and t2.nd = '" 
				+ year + "' and t2.kaoqu like '" 
				+ provinceId.substring(0, 2) + "%'";
		System.out.println("getMaxKch: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				maxKch = rs.getInt("maxKch");
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		
		return maxKch;
	}
	
	public void resetApplyKcHZwh(String year, String provinceId) {
		PreparedStatement pstmt = null;
		BocoConnection conn = null;
		String sql = "update zfryks_apply set kch = null, zwh = null where not exists (select 1 from zfryks_zw t2 where zfryks_apply.kch = t2.kch and zfryks_apply.zwh = t2.zwh) "
				+ " and kch is not null "
				+ " and zwh is not null "
				+ " and nd = '" + year 
				+ "' and sqdu like '" + provinceId.substring(0, 2) + "%'";
		System.out.println("resetApplyKcHZwh: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			DBStaticMethod.commit(conn);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
	}
	
	public BestSqdqAndKdId getBestSqdqAndKdId(String year, String provinceId, String jjdqId, String kdId) {
		BestSqdqAndKdId bestSqdqAndKdId = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = this.getSqlForBestSqdqAndKdId(year, provinceId, jjdqId, kdId);
		System.out.println("getBestSqdqAndKdId: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bestSqdqAndKdId = new BestSqdqAndKdId();
				bestSqdqAndKdId.setSqdq(rs.getString("sqdu"));
				bestSqdqAndKdId.setSqdqRs(rs.getInt("rs"));
				bestSqdqAndKdId.setKdId(rs.getString("kd_id"));
				bestSqdqAndKdId.setKdIdZws(rs.getInt("zws"));
				bestSqdqAndKdId.setCz(rs.getInt("cz"));
				bestSqdqAndKdId.setCzAbs(rs.getInt("czabs"));
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		
		return bestSqdqAndKdId;
	}
	
	public int[] getKqRsAndZws(String year, String provinceId, String jjdqId, String kdId) {
		int[] intArray = new int[2];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = this.getSqlForKqRsAndZws(year, provinceId, jjdqId, kdId);
		System.out.println("getKqRsAndZws: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				intArray[0] = rs.getInt("kqrs");
				intArray[1] = rs.getInt("kqzws");
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		
		return intArray;
	}
	
	/**
	 * 按地区统计没有分配考区的数据
	 * 
	 * @param year
	 * @param provinceId
	 * @param jjdqId
	 * @return
	 */
	public List getApplyNoKq(String year, String provinceId, String jjdqId) {
		List stringValueList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = this.getSqlForApplyNoKq(year, provinceId, jjdqId);
		System.out.println("getApplyNoKq: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StringValue stringValue = new StringValue();
				stringValue.setKey(rs.getString("sqdu"));
				stringValue.setName(rs.getString("districtName"));
				stringValue.setValue(rs.getInt("nokqSum"));
				stringValueList.add(stringValue);
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		return stringValueList;
	}
	
	public int getMaxBatchNo(String year, String provinceId) {
		int maxBachtNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = this.getSqlForMaxBatch(year, provinceId);
		System.out.println("getMaxBatchNo: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				maxBachtNo = rs.getInt("max_sbpc");
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		
		return maxBachtNo;
	}
	/**
	 * 获取本省本年度考场分配情况    
	 * @param year
	 * @param provinceId
	 * @return List
	 * @author wjk 
	 * @Create Date 2017 下午5:18:49
	 */
	public List getKcfpqk(String year, String provinceId) {
		List kcfpqkList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		String sql = this.getSqlForKcfpqk(year, provinceId);
		System.out.println("getKcfpqk: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Kcfpqk kcfpqk = new Kcfpqk();
				kcfpqk.setKaoquName(rs.getString("kaoquName"));
				kcfpqk.setKaodian(rs.getString("kaodian"));
				kcfpqk.setKddz(rs.getString("kddz"));
				kcfpqk.setKch(rs.getInt("kch"));
				kcfpqk.setKczws(rs.getInt("kczws"));
				kcfpqk.setFpzws(rs.getInt("fpzws"));
				kcfpqk.setKcid(rs.getString("kcid"));
				kcfpqkList.add(kcfpqk);
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		return kcfpqkList;
	}
	
	private String getSqlForBestSqdqAndKdId(String year, String provinceId, String jjdqId, String kdId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("    top 1 sqdu, rs, kd_id, zws, zws - rs as cz, ABS(zws - rs) AS czabs ");
		sb.append("FROM");
		sb.append("    (SELECT ");
		sb.append("        temp.sqdu AS sqdu, COUNT(*) AS rs");
		sb.append("    FROM");
		sb.append("        (SELECT DISTINCT");
		sb.append("        t1.*");
		sb.append("    FROM");
		sb.append("        dbo.zfryks_apply t1");
		sb.append("    WHERE");
		sb.append("        SUBSTRING(t1.sqdu, 0, 5) + '00' IN (").append(jjdqId).append(")");
		sb.append("            AND (zwh IS NULL OR zwh = '')) AS temp");
		sb.append("    GROUP BY temp.sqdu) tt1,");
		sb.append("    (SELECT ");
		sb.append("        kd_id, COUNT(*) AS zws");
		sb.append("    FROM");
		sb.append("        dbo.zfryks_zw t1, dbo.zfryks_kc t2, dbo.zfryks_kcgl t3");
		sb.append("    WHERE");
		sb.append("        t1.kc_id = t2.id AND t2.kd_id = t3.id");
		sb.append("            AND t3.nd = '").append(year).append("'");
		sb.append("            AND t3.kaoqu LIKE '").append(provinceId.substring(0, 2)).append("%'");
		sb.append("            AND t3.kqid = '").append(kdId).append("'");
		sb.append("    GROUP BY kd_id) tt2 ");
		sb.append("ORDER BY czabs");
		
		return sb.toString();
	}
	
	private String getSqlForKqRsAndZws(String year, String provinceId, String jjdqId, String kdId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("    kqrs, kqzws ");
		sb.append("FROM");
		sb.append("    (SELECT ");
		sb.append("        COUNT(*) AS kqrs");
		sb.append("    FROM");
		sb.append("        (SELECT DISTINCT");
		sb.append("        t1.*");
		sb.append("    FROM");
		sb.append("        dbo.zfryks_apply t1 ");
		sb.append("    WHERE");
		sb.append("        SUBSTRING(t1.sqdu, 0, 5) + '00' IN (").append(jjdqId).append(")");
		sb.append("            AND (zwh IS NULL OR zwh = '')) AS temp) AS tt1,");
		sb.append("    (SELECT ");
		sb.append("        COUNT(*) AS kqzws");
		sb.append("    FROM");
		sb.append("        dbo.zfryks_zw t1, dbo.zfryks_kc t2, dbo.zfryks_kcgl t3");
		sb.append("    WHERE");
		sb.append("        t1.kc_id = t2.id AND t2.kd_id = t3.id");
		sb.append("            AND t3.nd = '").append(year).append("'");
		sb.append("            AND t3.kaoqu LIKE '").append(provinceId.substring(0, 2)).append("%'");
		sb.append("            AND t3.kqid = '").append(kdId).append("') AS tt2");
		
		return sb.toString();
	}
	
	private String getSqlForApplyNoKq(String year, String provinceId, String jjdqId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("    sqdu, t2.districtName as districtName, COUNT(*) as nokqSum ");
		sb.append("FROM");
		sb.append("    (SELECT DISTINCT");
		sb.append("        t1.*");
		sb.append("    FROM");
		sb.append("        dbo.zfryks_apply t1");
		sb.append("    WHERE");
		sb.append("        t1.sqdu LIKE '").append(provinceId.substring(0, 2)).append("%' AND t1.nd = '").append(year).append("'");
		sb.append("            AND SUBSTRING(t1.sqdu, 0, 5) + '00' NOT IN (").append(jjdqId).append(") ");
		sb.append("            AND t1.sqdu NOT IN (").append(jjdqId).append(")) AS temp, ");
		sb.append("    taw_district t2 ");
		sb.append("WHERE");
		sb.append("    temp.sqdu = t2.districtId ");
		sb.append("GROUP BY sqdu, t2.districtName ");
		sb.append("ORDER BY sqdu ");
		
		return sb.toString();
	}
	
	private String getSqlForMaxBatch(String year, String provinceId) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT ");
		sb.append("    MAX(sbpc) as max_sbpc ");
		sb.append("FROM");
		sb.append("    zfryks_apply t1 ");
		sb.append("WHERE ");
		sb.append("    t1.nd = '").append(year).append("' ");
		sb.append("        AND t1.sqdu LIKE '").append(provinceId.substring(0, 2)).append("%' ");
		sb.append("        AND t1.kch IS NOT NULL");
		sb.append("        AND t1.zwh IS NOT NULL");
		
		return sb.toString();
	}
	
	private String getSqlForKcfpqk(String year, String provinceId){
		StringBuffer sb = new StringBuffer();
		
		sb.append("    SELECT t2.kaoquName, t2.kaodian, t2.kddz, t1.kch, t1.kczws, t3.fpzws, t1.id as kcid");
		sb.append("      FROM zfryks_kc t1 ");
		sb.append(" LEFT JOIN (SELECT kc_id, COUNT(*) AS fpzws FROM zfryks_zw WHERE zkzh IS NOT NULL GROUP BY kc_id) t3 ON t1.id = t3.kc_id, ");
		sb.append("	          zfryks_kcgl t2");
		sb.append("     WHERE t1.kd_id = t2.id");
		sb.append("	      AND t2.nd = '").append(year).append("'");
		sb.append("	      AND t2.kaoqu LIKE '").append(provinceId.substring(0, 2)).append("%'");
		sb.append("  ORDER BY t1.kch");
		
		return sb.toString();
	}
	
	/**
	 * 获取考场内的座位分配情况   
	 * @param sql
	 * @return List
	 * @author wjk 
	 * @Create Date 2017 下午2:03:35
	 */
	public List getzwListBySql(String sql) {
		List kcfpzwqkList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BocoConnection conn = null;
		System.out.println("getzwListBySql: " + sql);
		try {
			conn = this.ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Kcfpzwqk kcfpzwqk = new Kcfpzwqk();
				kcfpzwqk.setZwh(rs.getString("zwh"));
				kcfpzwqk.setZkzh(rs.getString("zkzh"));
				kcfpzwqk.setXm(rs.getString("xm"));
				kcfpzwqkList.add(kcfpzwqk);
			}
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
		} catch (SQLException e) {
			DBStaticMethod.close(rs);
			DBStaticMethod.close(pstmt);
			DBStaticMethod.rollback(conn);
			System.out.println("JDBCDAA_checkMoreYes_Exception:" + e.toString());
			e.printStackTrace();
		} finally {
			DBStaticMethod.close(conn);
		}
		return kcfpzwqkList;
	}
}
