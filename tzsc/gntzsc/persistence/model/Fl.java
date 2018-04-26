package com.sky.tzsc.gntzsc.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[tzsc_fl]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-26 15:57:33
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Fl extends AbstractModel<String>{
	
	/**
	* 图纸审查附件表主键
	*/
	protected String id; 
	
	/**
	* 图纸分类
	*/
	protected String fl; 
	
	/**
	* 图纸申请表主键
	*/
	protected String flowKey; 
	
	/**
	* 创建时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 创建用户
	*/
	protected String insertUser; 
	
	/**
	* 创建用户登录id
	*/
	protected String insertLogin; 
	
	/**
	* 创建部门
	*/
	protected String insertDept; 
	
	/**
	* 更新时间
	*/
	protected java.util.Date updateTime; 
	
	/**
	* 更新用户
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
	 * 返回 图纸审查附件表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setFl(String fl) {
		this.fl = fl;
	}
	
	/**
	 * 返回 图纸分类
	 * @return
	 */
	public String getFl() {
		return this.fl;
	}
	
	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}
	
	/**
	 * 返回 图纸申请表主键
	 * @return
	 */
	public String getFlowKey() {
		return this.flowKey;
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
	 * 返回 创建用户登录id
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
	 * 返回 更新时间
	 * @return
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * 返回 更新用户
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
		.append("fl", this.fl) 
		.append("flowKey", this.flowKey) 
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