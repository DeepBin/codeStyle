package com.boco.eoms.exelawtipstaffexam.model;

public class ZfryzgksCheckConfig {

	public ZfryzgksCheckConfig() {
	}

	/** �������� */
	private String id;
	/** ɾ����� */
	private Integer delFlag;
	/** ������� */
	private String districtId;
	/** ��ǰ��˵��� */
	private String nowDistrictId;
	/** �´���˵��� */
	private String nextDistrictId;
	/** ������������ */
	private String finalDistrictId;
	/** ������id */
	private Integer groupId;
	/** ���/�������� */
	private Integer checkType;
	/** ��˼��� */
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