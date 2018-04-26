package com.sky.tzsc.gntzsc.persistence.model;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[tzsc_wlwj]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-12 14:26:49
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Wlwj extends AbstractModel<String>{
	
	/**
	* 图纸审查往来文件表主键
	*/
	protected String id; 
	
	/**
	* 图纸审查申请表主键
	*/
	protected String tzsqId; 
	
	/**
	* 附件表主键
	*/
	protected String fjId; 
	
	/**
	* 文件类别
	*/
	protected String wjlb; 
	
	/**
	* 文件类别英文
	*/
	protected String wjlbYw; 
	
	/**
	* 文件名称
	*/
	protected String wjmc; 
	
	/**
	* 上传操作人
	*/
	protected String scczr; 
	
	/**
	* 上传时间
	*/
	protected java.util.Date scsj; 
	
	/**
	* 上传说明
	*/
	protected String scsm; 
	
	/**
	* 收件单位
	*/
	protected String sjdw; 
	
	/**
	* 创建时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 创建用户
	*/
	protected String insertUser; 
	
	/**
	* 创建部门
	*/
	protected String insertDept; 
	
	/**
	* 创建用户loginid
	*/
	protected String insertLogin; 
	
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
	
	/**
	 * 委托函
	 */
	protected String scwth;
	
	/**
	 * 委托函 接受单位
	 */
	protected String scwthSjdw;
	
	protected String scwthfwgj;
	
	protected String scwthfwgjSjdw;
	
	/**
	 * 审图意见书
	 */
	protected String styjs;
	/**
	 * 审图意见书 收件单位
	 */
	protected String  styjSjdw;
	/**
	 * 等效免除 批文
	 */
	protected  String dxmcpw;
	/**
	 *等效免除批文   收件单位
	 */
	protected String dxmcpwSjdw;
	/**
	 * 图纸审查报告
	 */
	protected String tzscbg;
	/**
	 * 图纸审查报告    收件单位
	 */
	protected String tzscbgSjdw;
	/**
	 * 图纸批准函
	 */
	protected String tzpzh;
	/**
	 * 图纸批准函    收件单位
	 */
	protected String tzpzhSjdw;
	
	
	
	public String getScwth() {
		return scwth;
	}

	public void setScwth(String scwth) {
		this.scwth = scwth;
	}

	public String getScwthSjdw() {
		return scwthSjdw;
	}

	public void setScwthSjdw(String scwthSjdw) {
		this.scwthSjdw = scwthSjdw;
	}

	public String getScwthfwgj() {
		return scwthfwgj;
	}

	public void setScwthfwgj(String scwthfwgj) {
		this.scwthfwgj = scwthfwgj;
	}

	public String getScwthfwgjSjdw() {
		return scwthfwgjSjdw;
	}

	public void setScwthfwgjSjdw(String scwthfwgjSjdw) {
		this.scwthfwgjSjdw = scwthfwgjSjdw;
	}

	public String getStyjs() {
		return styjs;
	}

	public void setStyjs(String styjs) {
		this.styjs = styjs;
	}

	public String getStyjSjdw() {
		return styjSjdw;
	}

	public void setStyjSjdw(String styjSjdw) {
		this.styjSjdw = styjSjdw;
	}

	public String getDxmcpw() {
		return dxmcpw;
	}

	public void setDxmcpw(String dxmcpw) {
		this.dxmcpw = dxmcpw;
	}

	public String getDxmcpwSjdw() {
		return dxmcpwSjdw;
	}

	public void setDxmcpwSjdw(String dxmcpwSjdw) {
		this.dxmcpwSjdw = dxmcpwSjdw;
	}

	public String getTzscbg() {
		return tzscbg;
	}

	public void setTzscbg(String tzscbg) {
		this.tzscbg = tzscbg;
	}

	public String getTzscbgSjdw() {
		return tzscbgSjdw;
	}

	public void setTzscbgSjdw(String tzscbgSjdw) {
		this.tzscbgSjdw = tzscbgSjdw;
	}

	public String getTzpzh() {
		return tzpzh;
	}

	public void setTzpzh(String tzpzh) {
		this.tzpzh = tzpzh;
	}

	public String getTzpzhSjdw() {
		return tzpzhSjdw;
	}

	public void setTzpzhSjdw(String tzpzhSjdw) {
		this.tzpzhSjdw = tzpzhSjdw;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 图纸审查往来文件表主键
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
	
	public void setWjlb(String wjlb) {
		this.wjlb = wjlb;
	}
	
	/**
	 * 返回 文件类别
	 * @return
	 */
	public String getWjlb() {
		return this.wjlb;
	}
	
	public void setWjlbYw(String wjlbYw) {
		this.wjlbYw = wjlbYw;
	}
	
	/**
	 * 返回 文件类别英文
	 * @return
	 */
	public String getWjlbYw() {
		return this.wjlbYw;
	}
	
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	
	/**
	 * 返回 文件名称
	 * @return
	 */
	public String getWjmc() {
		return this.wjmc;
	}
	
	public void setScczr(String scczr) {
		this.scczr = scczr;
	}
	
	/**
	 * 返回 上传操作人
	 * @return
	 */
	public String getScczr() {
		return this.scczr;
	}
	
	public void setScsj(java.util.Date scsj) {
		this.scsj = scsj;
	}
	
	/**
	 * 返回 上传时间
	 * @return
	 */
	public java.util.Date getScsj() {
		return this.scsj;
	}
	
	public void setScsm(String scsm) {
		this.scsm = scsm;
	}
	
	/**
	 * 返回 上传说明
	 * @return
	 */
	public String getScsm() {
		return this.scsm;
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
		.append("wjlb", this.wjlb) 
		.append("wjlbYw", this.wjlbYw) 
		.append("wjmc", this.wjmc) 
		.append("scczr", this.scczr) 
		.append("scsj", this.scsj) 
		.append("scsm", this.scsm) 
		.append("sjdw", this.sjdw) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("insertDept", this.insertDept) 
		.append("insertLogin", this.insertLogin) 
		.append("updateTime", this.updateTime) 
		.append("updateUser", this.updateUser) 
		.append("scbj", this.scbj) 
		.toString();
	}
}