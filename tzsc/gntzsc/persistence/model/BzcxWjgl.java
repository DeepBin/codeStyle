package com.sky.tzsc.gntzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[标准船型文件管理]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-25 09:17:50
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class BzcxWjgl extends AbstractModel<String>{
	
	/**
	* 标准船型文件管理表主键
	*/
	protected String id; 
	
	/**
	* 公布文件号
	*/
	protected String gbwjh; 
	
	/**
	* 公布文件单位
	*/
	protected String gbwjdw; 
	
	/**
	* 公布时间
	*/
	protected java.util.Date gbsj; 
	
	/**
	* 附件
	*/
	protected String fj; 
	
	/**
	* 标题
	*/
	protected String bt; 
	
	/**
	* 上传地区id
	*/
	protected String dq; 
	
	/**
	* 上传部门
	*/
	protected String sendDept; 
	
	/**
	* 上传时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 上传用户
	*/
	protected String insertUser; 
	
	/**
	* 更新用户
	*/
	protected String updateUser; 
	
	/**
	* 更新时间
	*/
	protected java.util.Date updateTime; 
	
	/**
	* 删除标记
	*/
	protected Integer scbj; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 标准船型文件管理表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setGbwjh(String gbwjh) {
		this.gbwjh = gbwjh;
	}
	
	/**
	 * 返回 公布文件号
	 * @return
	 */
	public String getGbwjh() {
		return this.gbwjh;
	}
	
	public void setGbwjdw(String gbwjdw) {
		this.gbwjdw = gbwjdw;
	}
	
	/**
	 * 返回 公布文件单位
	 * @return
	 */
	public String getGbwjdw() {
		return this.gbwjdw;
	}
	
	public void setGbsj(java.util.Date gbsj) {
		this.gbsj = gbsj;
	}
	
	/**
	 * 返回 公布时间
	 * @return
	 */
	public java.util.Date getGbsj() {
		return this.gbsj;
	}
	
	public void setFj(String fj) {
		this.fj = fj;
	}
	
	/**
	 * 返回 附件
	 * @return
	 */
	public String getFj() {
		return this.fj;
	}
	
	public void setBt(String bt) {
		this.bt = bt;
	}
	
	/**
	 * 返回 标题
	 * @return
	 */
	public String getBt() {
		return this.bt;
	}
	
	public void setDq(String dq) {
		this.dq = dq;
	}
	
	/**
	 * 返回 上传地区id
	 * @return
	 */
	public String getDq() {
		return this.dq;
	}
	
	public void setSendDept(String sendDept) {
		this.sendDept = sendDept;
	}
	
	/**
	 * 返回 上传部门
	 * @return
	 */
	public String getSendDept() {
		return this.sendDept;
	}
	
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	/**
	 * 返回 上传时间
	 * @return
	 */
	public java.util.Date getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	/**
	 * 返回 上传用户
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
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
		.append("gbwjh", this.gbwjh) 
		.append("gbwjdw", this.gbwjdw) 
		.append("gbsj", this.gbsj) 
		.append("fj", this.fj) 
		.append("bt", this.bt) 
		.append("dq", this.dq) 
		.append("sendDept", this.sendDept) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("updateUser", this.updateUser) 
		.append("updateTime", this.updateTime) 
		.append("scbj", this.scbj) 
		.toString();
	}
}