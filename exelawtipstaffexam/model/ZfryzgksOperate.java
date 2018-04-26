package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;

public class ZfryzgksOperate {

	public ZfryzgksOperate() {
	}

	/** 工单主键 */
	private String id;

	/** 删除标记 */
	private Integer delFlag;

	private String districtId;

	/** 操作项 */
	private String content;
	/** 操作时间 */
	private Timestamp time;

	/** 备注 */
	private String remark;

	/** 申请书id */
	private String applyId;

	/** 操作用户id */
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