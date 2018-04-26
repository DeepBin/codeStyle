package com.sky.tzsc.gntzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[tzsc_fj]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-04-02 16:58:41
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Fj extends AbstractModel<String>{
	
	/**
	* id
	*/
	protected String id; 
	
	/**
	* 图纸分类表主键
	*/
	protected String flId; 
	
	/**
	* 图纸附件表主键
	*/
	protected String fjId; 
	
	/**
	* 附件辅助材料主键
	*/
	protected String fjfzscId; 
	
	/**
	* 图纸申请表主键
	*/
	protected String flowKey; 
	
	/**
	* 图纸负责人姓名
	*/
	protected String tzfzrxm; 
	
	/**
	* 图纸负责人联系电话
	*/
	protected String tzfzrlxdh; 
	
	/**
	* insert_time
	*/
	protected java.util.Date insertTime; 
	
	/**
	* insert_user
	*/
	protected String insertUser; 
	
	/**
	* insert_login
	*/
	protected String insertLogin; 
	
	/**
	* insert_dept
	*/
	protected String insertDept; 
	
	/**
	* update_time
	*/
	protected java.util.Date updateTime; 
	
	/**
	* update_user
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
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setFlId(String flId) {
		this.flId = flId;
	}
	
	/**
	 * 返回 图纸分类表主键
	 * @return
	 */
	public String getFlId() {
		return this.flId;
	}
	
	public void setFjId(String fjId) {
		this.fjId = fjId;
	}
	
	/**
	 * 返回 图纸附件表主键
	 * @return
	 */
	public String getFjId() {
		return this.fjId;
	}
	
	public void setFjfzscId(String fjfzscId) {
		this.fjfzscId = fjfzscId;
	}
	
	/**
	 * 返回 附件辅助材料主键
	 * @return
	 */
	public String getFjfzscId() {
		return this.fjfzscId;
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
	
	public void setTzfzrxm(String tzfzrxm) {
		this.tzfzrxm = tzfzrxm;
	}
	
	/**
	 * 返回 图纸负责人姓名
	 * @return
	 */
	public String getTzfzrxm() {
		return this.tzfzrxm;
	}
	
	public void setTzfzrlxdh(String tzfzrlxdh) {
		this.tzfzrlxdh = tzfzrlxdh;
	}
	
	/**
	 * 返回 图纸负责人联系电话
	 * @return
	 */
	public String getTzfzrlxdh() {
		return this.tzfzrlxdh;
	}
	
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	/**
	 * 返回 insert_time
	 * @return
	 */
	public java.util.Date getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	/**
	 * 返回 insert_user
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
	}
	
	public void setInsertLogin(String insertLogin) {
		this.insertLogin = insertLogin;
	}
	
	/**
	 * 返回 insert_login
	 * @return
	 */
	public String getInsertLogin() {
		return this.insertLogin;
	}
	
	public void setInsertDept(String insertDept) {
		this.insertDept = insertDept;
	}
	
	/**
	 * 返回 insert_dept
	 * @return
	 */
	public String getInsertDept() {
		return this.insertDept;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 返回 update_time
	 * @return
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * 返回 update_user
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
		.append("flId", this.flId) 
		.append("fjId", this.fjId) 
		.append("fjfzscId", this.fjfzscId) 
		.append("flowKey", this.flowKey) 
		.append("tzfzrxm", this.tzfzrxm) 
		.append("tzfzrlxdh", this.tzfzrlxdh) 
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