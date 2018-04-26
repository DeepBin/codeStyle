
package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;


/**
 * ZfryksBmsj entity. @author MyEclipse Persistence Tools
 */

public class ZfryksBmsj implements java.io.Serializable {
	
	// Fields
	
	private String id;
	
	private String niandu;
	
	private String pici;
	
	private String districtId;
	
	private Timestamp applyBegdate;
	
	private Timestamp applyEnddate;
	
	private Timestamp insertTime;
	
	private String insertUserId;
	
	private Timestamp kssjKs;
	
	private Timestamp kssjJs;
	
	private Timestamp shjssj;
	
	private Timestamp sbjssj;
	
	private Timestamp gxsjKs;// 可以更新报名信息的开始时间
	
	private Timestamp gxsjJs;// 可以更新报名信息的截止时间

	public Timestamp getSbjssj() {
		return sbjssj;
	}

	public void setSbjssj(Timestamp sbjssj) {
		this.sbjssj = sbjssj;
	}

	public Timestamp getShjssj() {
		return shjssj;
	}

	public void setShjssj(Timestamp shjssj) {
		this.shjssj = shjssj;
	}

	public Timestamp getKssjKs() {
		return kssjKs;
	}

	public void setKssjKs(Timestamp kssjKs) {
		this.kssjKs = kssjKs;
	}

	public Timestamp getKssjJs() {
		return kssjJs;
	}

	public void setKssjJs(Timestamp kssjJs) {
		this.kssjJs = kssjJs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNiandu() {
		return niandu;
	}

	public void setNiandu(String niandu) {
		this.niandu = niandu;
	}

	public String getPici() {
		return pici;
	}

	public void setPici(String pici) {
		this.pici = pici;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public Timestamp getApplyBegdate() {
		return applyBegdate;
	}

	public void setApplyBegdate(Timestamp applyBegdate) {
		this.applyBegdate = applyBegdate;
	}

	public Timestamp getApplyEnddate() {
		return applyEnddate;
	}

	public void setApplyEnddate(Timestamp applyEnddate) {
		this.applyEnddate = applyEnddate;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}
	
	public Timestamp getGxsjKs() {
		return gxsjKs;
	}

	public void setGxsjKs(Timestamp gxsjKs) {
		this.gxsjKs = gxsjKs;
	}

	public Timestamp getGxsjJs() {
		return gxsjJs;
	}

	public void setGxsjJs(Timestamp gxsjJs) {
		this.gxsjJs = gxsjJs;
	}

	public String toString() {
		return "ZfryksBmsj [id=" + id + ", niandu=" + niandu + ", pici=" + pici + ", districtId=" + districtId + ", applyBegdate=" + applyBegdate + ", applyEnddate=" + applyEnddate + ", insertTime=" + insertTime + ", insertUserId=" + insertUserId + "]";
	}
}