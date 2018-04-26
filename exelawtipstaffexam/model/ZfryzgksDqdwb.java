
package com.boco.eoms.exelawtipstaffexam.model;




public class ZfryzgksDqdwb implements java.io.Serializable {
	

	
	private String id;
	
	private String districtId;
	
	private String dwxz;
	
	private String gzdwmc;
	
	private Integer dwjb;
	
	private String operater;
	
	private java.sql.Timestamp operateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDwxz() {
		return dwxz;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public Integer getDwjb() {
		return dwjb;
	}

	public void setDwjb(Integer dwjb) {
		this.dwjb = dwjb;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public java.sql.Timestamp getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(java.sql.Timestamp operateTime) {
		this.operateTime = operateTime;
	}
	
	
	public String getGzdwmc() {
		return gzdwmc;
	}

	public void setGzdwmc(String gzdwmc) {
		this.gzdwmc = gzdwmc;
	}

	public String toString() {
		return "ZfryzgksDqdwb [id=" + id + ", districtId=" + districtId + ", dwxz=" + dwxz + ", gzdwmc=" + gzdwmc
				+ ", dwjb=" + dwjb + ", operater=" + operater + ", operateTime=" + operateTime + "]";
	}

}