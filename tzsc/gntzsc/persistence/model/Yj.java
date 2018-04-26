package com.sky.tzsc.gntzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[tzsc_yj]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-29 13:41:43
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Yj extends AbstractModel<String>{
	
	/**
	* 图纸审查意见表主键
	*/
	protected String id; 
	
	/**
	* 图纸审查申请表主键
	*/
	protected String tzsqId; 
	
	/**
	* 图纸审查附件表主键
	*/
	protected String tzfjId; 
	
	/**
	* 审图人员名称
	*/
	protected String strymc; 
	
	/**
	* 审图人员id
	*/
	protected String stryid; 
	
	/**
	* 审图意见
	*/
	protected String styj; 
	
	/**
	* 填写意见时间
	*/
	protected java.util.Date txyjsj; 
	
	/**
	* 创建时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 创建用户
	*/
	protected String insertUser; 
	
	/**
	* 创建用户loginid
	*/
	protected String insertLogin; 
	
	/**
	* 创建部门
	*/
	protected String insertDept; 
	
	/**
	* 修改时间
	*/
	protected java.util.Date updateTime; 
	
	/**
	* 修改用户
	*/
	protected String updateUser; 
	
	/**
	* 删除标记
	*/
	protected Integer scbj; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 图纸审查意见表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setTzsqId(String tzsqId) {
		this.tzsqId = tzsqId;
	}
	
	/**
	 * 返回 图纸审查申请表主键
	 * @return
	 */
	public String getTzsqId() {
		return this.tzsqId;
	}
	
	public void setTzfjId(String tzfjId) {
		this.tzfjId = tzfjId;
	}
	
	/**
	 * 返回 图纸审查附件表主键
	 * @return
	 */
	public String getTzfjId() {
		return this.tzfjId;
	}
	
	public void setStrymc(String strymc) {
		this.strymc = strymc;
	}
	
	/**
	 * 返回 审图人员名称
	 * @return
	 */
	public String getStrymc() {
		return this.strymc;
	}
	
	public void setStryid(String stryid) {
		this.stryid = stryid;
	}
	
	/**
	 * 返回 审图人员id
	 * @return
	 */
	public String getStryid() {
		return this.stryid;
	}
	
	public void setStyj(String styj) {
		this.styj = styj;
	}
	
	/**
	 * 返回 审图意见
	 * @return
	 */
	public String getStyj() {
		return this.styj;
	}
	
	public void setTxyjsj(java.util.Date txyjsj) {
		this.txyjsj = txyjsj;
	}
	
	/**
	 * 返回 填写意见时间
	 * @return
	 */
	public java.util.Date getTxyjsj() {
		return this.txyjsj;
	}
	
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	/**
	 * 返回 创建用户
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
	}
	
	public void setInsertLogin(String insertLogin) {
		this.insertLogin = insertLogin;
	}
	
	/**
	 * 返回 创建用户loginid
	 * @return
	 */
	public String getInsertLogin() {
		return this.insertLogin;
	}
	
	public void setInsertDept(String insertDept) {
		this.insertDept = insertDept;
	}
	
	/**
	 * 返回 创建部门
	 * @return
	 */
	public String getInsertDept() {
		return this.insertDept;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 返回 修改时间
	 * @return
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * 返回 修改用户
	 * @return
	 */
	public String getUpdateUser() {
		return this.updateUser;
	}
	
	public void setScbj(Integer scbj) {
		this.scbj = scbj;
	}
	
	/**
	 * 返回 删除标记
	 * @return
	 */
	public Integer getScbj() {
		return this.scbj;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("tzsqId", this.tzsqId) 
		.append("tzfjId", this.tzfjId) 
		.append("strymc", this.strymc) 
		.append("stryid", this.stryid) 
		.append("styj", this.styj) 
		.append("txyjsj", this.txyjsj) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("insertLogin", this.insertLogin) 
		.append("insertDept", this.insertDept) 
		.append("updateTime", this.updateTime) 
		.append("updateUser", this.updateUser) 
		.append("scbj", this.scbj) 
		.toString();
	}
}