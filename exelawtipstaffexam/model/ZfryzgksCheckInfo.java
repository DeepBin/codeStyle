package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;

public class ZfryzgksCheckInfo {

	public ZfryzgksCheckInfo() {
	}

	/** �������� */
	private String id;
	/** ɾ����� */
	private Integer delFlag;
	/** �����id */
	private String applyId;
	/** ��˵������� */
	private String checkDistrict;
	/** ��˽�� */
	private String checkResult;
	/** ������ */
	private String checkIdea;
	/** �������ʱ�� */
	private Float incraeceTime;
	/** ����˼�ʱ�� */
	private Float reduceTime;
	/** ��ע */
	private String remark;
	/** ��˲��� */
	private String checkDept;
	/** ���ʱ�� */
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