package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;

/**
 * 
 * ��Ŀ���ƣ�CNFM �����ƣ�GranthyCheckLevelConfig 
 * �������������ȼ���; 
 * 
 * @version 1.0
 * @author wjk
 * 
 */
public class ZfryksCheckLevelConfig {
	public ZfryksCheckLevelConfig() {
	}

	/** ����id */
	private String id;
	/** ����id */
	private String districtId;
	/** �����ȼ� */
	private String groupId;
	/** ������ */
	private String operateUserId;
	/** ����ʱ�� */
	private Timestamp operateTime;

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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	public Timestamp getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

}
