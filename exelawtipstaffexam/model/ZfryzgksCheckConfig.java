package com.boco.eoms.exelawtipstaffexam.model;

public class ZfryzgksCheckConfig {

	public ZfryzgksCheckConfig() {
	}

	/** 工单主键 */
	private String id;
	/** 删除标记 */
	private Integer delFlag;
	/** 申请地区 */
	private String districtId;
	/** 当前审核地区 */
	private String nowDistrictId;
	/** 下次审核地区 */
	private String nextDistrictId;
	/** 最终审批地区 */
	private String finalDistrictId;
	/** 流程组id */
	private Integer groupId;
	/** 审核/审批类型 */
	private Integer checkType;
	/** 审核级别 */
	private Integer checkLevel;

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

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getNowDistrictId() {
		return nowDistrictId;
	}

	public void setNowDistrictId(String nowDistrictId) {
		this.nowDistrictId = nowDistrictId;
	}

	public String getNextDistrictId() {
		return nextDistrictId;
	}

	public void setNextDistrictId(String nextDistrictId) {
		this.nextDistrictId = nextDistrictId;
	}

	public String getFinalDistrictId() {
		return finalDistrictId;
	}

	public void setFinalDistrictId(String finalDistrictId) {
		this.finalDistrictId = finalDistrictId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getCheckLevel() {
		return checkLevel;
	}

	public void setCheckLevel(Integer checkLevel) {
		this.checkLevel = checkLevel;
	}

}