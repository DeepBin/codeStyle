package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;

public class ZfryzgksCheckInfo {

	public ZfryzgksCheckInfo() {
	}

	/** 工单主键 */
	private String id;
	/** 删除标记 */
	private Integer delFlag;
	/** 申请表id */
	private String applyId;
	/** 审核地区编码 */
	private String checkDistrict;
	/** 审核结果 */
	private String checkResult;
	/** 审核意见 */
	private String checkIdea;
	/** 特殊核增时间 */
	private Float incraeceTime;
	/** 特殊核减时间 */
	private Float reduceTime;
	/** 备注 */
	private String remark;
	/** 审核部门 */
	private String checkDept;
	/** 审核时间 */
	private Timestamp checkTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	
	
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCheckDistrict() {
		return checkDistrict;
	}

	public void setCheckDistrict(String checkDistrict) {
		this.checkDistrict = checkDistrict;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckIdea() {
		return checkIdea;
	}

	public void setCheckIdea(String checkIdea) {
		this.checkIdea = checkIdea;
	}

	public Float getIncraeceTime() {
		return incraeceTime;
	}

	public void setIncraeceTime(Float incraeceTime) {
		this.incraeceTime = incraeceTime;
	}

	public Float getReduceTime() {
		return reduceTime;
	}

	public void setReduceTime(Float reduceTime) {
		this.reduceTime = reduceTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckDept() {
		return checkDept;
	}

	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}

	public Timestamp getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}

}