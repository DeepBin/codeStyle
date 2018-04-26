package com.sky.tzsc.yytzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查附件表]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 14:47:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Yyfj extends AbstractModel<String>{
	
	/**
	* 远洋图纸审查附件表主键
	*/
	protected String id; 
	
	/**
	* 远洋图纸审查申请表主键
	*/
	protected String tzsqId; 
	
	/**
	* 附件表主键
	*/
	protected String fjId; 
	
	/**
	* 附件目录分类
	*/
	protected String fjmlfl; 
	
	/**
	* 附件目录分类拼音
	*/
	protected String fjmlflYw; 
	
	/**
	* 附件名称
	*/
	protected String fjmc; 
	
	/**
	* 附件说明
	*/
	protected String fjsm; 
	
	/**
	* 收件单位
	*/
	protected String sjdw; 
	
	/**
	* 整改结果
	*/
	protected String zgjg; 
	
	/**
	* 状态
	*/
	protected Integer zt; 
	
	/**
	* 插入时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 插入用户
	*/
	protected String insertUser; 
	
	/**
	* 插入用户登录id
	*/
	protected String insertLogin; 
	
	/**
	* 插入用户所属部门
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
	 * 返回 远洋图纸审查附件表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setTzsqId(String tzsqId) {
		this.tzsqId = tzsqId;
	}
	
	/**
	 * 返回 远洋图纸审查申请表主键
	 * @return
	 */
	public String getTzsqId() {
		return this.tzsqId;
	}
	
	public void setFjId(String fjId) {
		this.fjId = fjId;
	}
	
	/**
	 * 返回 附件表主键
	 * @return
	 */
	public String getFjId() {
		return this.fjId;
	}
	
	public void setFjmlfl(String fjmlfl) {
		this.fjmlfl = fjmlfl;
	}
	
	/**
	 * 返回 附件目录分类
	 * @return
	 */
	public String getFjmlfl() {
		return this.fjmlfl;
	}
	
	public void setFjmlflYw(String fjmlflYw) {
		this.fjmlflYw = fjmlflYw;
	}
	
	/**
	 * 返回 附件目录分类拼音
	 * @return
	 */
	public String getFjmlflYw() {
		return this.fjmlflYw;
	}
	
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	
	/**
	 * 返回 附件名称
	 * @return
	 */
	public String getFjmc() {
		return this.fjmc;
	}
	
	public void setFjsm(String fjsm) {
		this.fjsm = fjsm;
	}
	
	/**
	 * 返回 附件说明
	 * @return
	 */
	public String getFjsm() {
		return this.fjsm;
	}
	
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	
	/**
	 * 返回 收件单位
	 * @return
	 */
	public String getSjdw() {
		return this.sjdw;
	}
	
	public void setZgjg(String zgjg) {
		this.zgjg = zgjg;
	}
	
	/**
	 * 返回 整改结果
	 * @return
	 */
	public String getZgjg() {
		return this.zgjg;
	}
	
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
	/**
	 * 返回 状态
	 * @return
	 */
	public Integer getZt() {
		return this.zt;
	}
	
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	/**
	 * 返回 插入时间
	 * @return
	 */
	public java.util.Date getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	/**
	 * 返回 插入用户
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
	}
	
	public void setInsertLogin(String insertLogin) {
		this.insertLogin = insertLogin;
	}
	
	/**
	 * 返回 插入用户登录id
	 * @return
	 */
	public String getInsertLogin() {
		return this.insertLogin;
	}
	
	public void setInsertDept(String insertDept) {
		this.insertDept = insertDept;
	}
	
	/**
	 * 返回 插入用户所属部门
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
		.append("tzsqId", this.tzsqId) 
		.append("fjId", this.fjId) 
		.append("fjmlfl", this.fjmlfl) 
		.append("fjmlflYw", this.fjmlflYw) 
		.append("fjmc", this.fjmc) 
		.append("fjsm", this.fjsm) 
		.append("sjdw", this.sjdw) 
		.append("zgjg", this.zgjg) 
		.append("zt", this.zt) 
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