package com.boco.eoms.exelawtipstaffexam.model;

import java.sql.Timestamp;

/**
 * 
 * 项目名称：CNFM 类名称：GranthyCheckLevelConfig 
 * 类描述：审批等级类; 
 * 
 * @version 1.0
 * @author wjk
 * 
 */
public class ZfryksCheckLevelConfig {
	public ZfryksCheckLevelConfig() {
	}

	/** 主键id */
	private String id;
	/** 地区id */
	private String districtId;
	/** 审批等级 */
	private String groupId;
	/** 操作人 */
	private String operateUserId;
	/** 操作时间 */
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
