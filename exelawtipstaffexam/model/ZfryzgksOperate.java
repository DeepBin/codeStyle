package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;

public class ZfryzgksOperate {

	public ZfryzgksOperate() {
	}

	/** �������� */
	private String id;

	/** ɾ����� */
	private Integer delFlag;

	private String districtId;

	/** ������ */
	private String content;
	/** ����ʱ�� */
	private Timestamp time;

	/** ��ע */
	private String remark;

	/** ������id */
	private String applyId;

	/** �����û�id */
	private String userid;

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getApplyId() {
		return this.applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}