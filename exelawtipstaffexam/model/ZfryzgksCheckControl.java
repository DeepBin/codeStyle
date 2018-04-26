package com.boco.eoms.exelawtipstaffexam.model;

public class ZfryzgksCheckControl {

	public ZfryzgksCheckControl() {
	}

	/** 工单主键 */
	private String id;
	/** 删除标记 */
	private Integer delFlag;
	/** 申请书id */
	private String applyId;
	/** 审核状态 */
	private String state;
	/** 当前审核地区 */
	private String districtId;
	/** 当前步骤 */
	private String nowStep;
	/** 下一步骤 */
	private Integer nextStep;
	/** 步骤标志 */
	private Integer stepFlag;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getNowStep() {
		return nowStep;
	}

	public void setNowStep(String nowStep) {
		this.nowStep = nowStep;
	}

	public Integer getNextStep() {
		return nextStep;
	}

	public void setNextStep(Integer nextStep) {
		this.nextStep = nextStep;
	}

	public Integer getStepFlag() {
		return stepFlag;
	}

	public void setStepFlag(Integer stepFlag) {
		this.stepFlag = stepFlag;
	}

}