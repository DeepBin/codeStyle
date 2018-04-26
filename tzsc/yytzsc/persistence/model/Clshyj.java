package com.sky.tzsc.yytzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查材料审核意见]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:34:07
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Clshyj extends AbstractModel<String>{
	
	/**
	* 材料审核意见表主键
	*/
	protected String id; 
	
	/**
	* 图纸申请表主键
	*/
	protected String tzsqId; 
	
	/**
	* 收到材料日期
	*/
	protected java.util.Date sdclrq; 
	
	/**
	* 审核人
	*/
	protected String shr; 
	
	/**
	* 审核结论
	*/
	protected String shjl; 
	
	/**
	* shrq
	*/
	protected java.util.Date shrq; 
	
	/**
	* 审核日期
	*/
	protected String shyj; 
	
	/**
	* 新增时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 新增用户
	*/
	protected String insertUser; 
	
	/**
	* 插入记录部门
	*/
	protected String insertDept; 
	
	/**
	* 更新记录时间
	*/
	protected java.util.Date updateTime; 
	
	/**
	* 更新记录用户
	*/
	protected String updateUser; 
	
	/**
	* 删除标记
	*/
	protected Integer scbj; 
	
	/**
	* 备注
	*/
	protected String bz; 
	

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 材料审核意见表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setTzsqId(String tzsqId) {
		this.tzsqId = tzsqId;
	}
	
	/**
	 * 返回 图纸申请表主键
	 * @return
	 */
	public String getTzsqId() {
		return this.tzsqId;
	}
	
	public void setSdclrq(java.util.Date sdclrq) {
		this.sdclrq = sdclrq;
	}
	
	/**
	 * 返回 收到材料日期
	 * @return
	 */
	public java.util.Date getSdclrq() {
		return this.sdclrq;
	}
	
	public void setShr(String shr) {
		this.shr = shr;
	}
	
	/**
	 * 返回 审核人
	 * @return
	 */
	public String getShr() {
		return this.shr;
	}
	
	public void setShjl(String shjl) {
		this.shjl = shjl;
	}
	
	/**
	 * 返回 审核结论
	 * @return
	 */
	public String getShjl() {
		return this.shjl;
	}
	
	public void setShrq(java.util.Date shrq) {
		this.shrq = shrq;
	}
	
	/**
	 * 返回 shrq
	 * @return
	 */
	public java.util.Date getShrq() {
		return this.shrq;
	}
	
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	
	/**
	 * 返回 审核日期
	 * @return
	 */
	public String getShyj() {
		return this.shyj;
	}
	
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	/**
	 * 返回 新增时间
	 * @return
	 */
	public java.util.Date getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	/**
	 * 返回 新增用户
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
	}
	
	public void setInsertDept(String insertDept) {
		this.insertDept = insertDept;
	}
	
	/**
	 * 返回 插入记录部门
	 * @return
	 */
	public String getInsertDept() {
		return this.insertDept;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 返回 更新记录时间
	 * @return
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * 返回 更新记录用户
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
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getBz() {
		return this.bz;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("tzsqId", this.tzsqId) 
		.append("sdclrq", this.sdclrq) 
		.append("shr", this.shr) 
		.append("shjl", this.shjl) 
		.append("shrq", this.shrq) 
		.append("shyj", this.shyj) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("insertDept", this.insertDept) 
		.append("updateTime", this.updateTime) 
		.append("updateUser", this.updateUser) 
		.append("scbj", this.scbj) 
		.append("bz", this.bz) 
		.toString();
	}
}