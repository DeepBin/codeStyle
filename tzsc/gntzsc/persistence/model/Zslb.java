package com.sky.tzsc.gntzsc.persistence.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 
 * <pre> 
 * 描述：[tzsc_zslb]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-27 15:36:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Zslb implements Serializable{
	
	/**
	* id
	*/
	protected String id; 
	
	/**
	* file_id
	*/
	protected Integer fileId; 
	
	/**
	* cert_version
	*/
	protected String certVersion; 
	
	/**
	* name
	*/
	protected String name; 
	
	/**
	* cert_type
	*/
	protected Integer certType; 
	
	/**
	* sheet_type
	*/
	protected Integer sheetType; 
	
	/**
	* type
	*/
	protected Integer type; 
	
	/**
	* is_in_ship
	*/
	protected Integer isInShip; 
	
	/**
	* is_special
	*/
	protected Integer isSpecial; 
	
	/**
	* page_num
	*/
	protected Integer pageNum; 
	
	/**
	* deleted
	*/
	protected String deleted; 
	
	/**
	* page_type
	*/
	protected Integer pageType; 
	
	/**
	* remark
	*/
	protected String remark; 
	
	/**
	* order_id
	*/
	protected Integer orderId; 
	
	/**
	* has_cfr_logo
	*/
	protected Integer hasCfrLogo; 
	
	/**
	* issue_level
	*/
	protected String issueLevel; 
	
	/**
	* overdue
	*/
	protected Integer overdue; 
	/**
	 * systemName 模块名称 
	 */
	protected String systemName;
	
	/**
	* page_name
	*/
	protected String pageName; 
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @param id void
	 * @author wjk 
	 * @Create Date 2018 上午8:55:16
	 */
	protected String lclx;
	
	
	public String getLclx() {
		return lclx;
	}

	public void setLclx(String lclx) {
		this.lclx = lclx;
	}

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
	
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * 返回 file_id
	 * @return
	 */
	public Integer getFileId() {
		return this.fileId;
	}
	
	public void setCertVersion(String certVersion) {
		this.certVersion = certVersion;
	}
	
	/**
	 * 返回 cert_version
	 * @return
	 */
	public String getCertVersion() {
		return this.certVersion;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 返回 name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	public void setCertType(Integer certType) {
		this.certType = certType;
	}
	
	/**
	 * 返回 cert_type
	 * @return
	 */
	public Integer getCertType() {
		return this.certType;
	}
	
	public void setSheetType(Integer sheetType) {
		this.sheetType = sheetType;
	}
	
	/**
	 * 返回 sheet_type
	 * @return
	 */
	public Integer getSheetType() {
		return this.sheetType;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 返回 type
	 * @return
	 */
	public Integer getType() {
		return this.type;
	}
	
	public void setIsInShip(Integer isInShip) {
		this.isInShip = isInShip;
	}
	
	/**
	 * 返回 is_in_ship
	 * @return
	 */
	public Integer getIsInShip() {
		return this.isInShip;
	}
	
	public void setIsSpecial(Integer isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	/**
	 * 返回 is_special
	 * @return
	 */
	public Integer getIsSpecial() {
		return this.isSpecial;
	}
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * 返回 page_num
	 * @return
	 */
	public Integer getPageNum() {
		return this.pageNum;
	}
	
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * 返回 deleted
	 * @return
	 */
	public String getDeleted() {
		return this.deleted;
	}
	
	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}
	
	/**
	 * 返回 page_type
	 * @return
	 */
	public Integer getPageType() {
		return this.pageType;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 返回 remark
	 * @return
	 */
	public String getRemark() {
		return this.remark;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 返回 order_id
	 * @return
	 */
	public Integer getOrderId() {
		return this.orderId;
	}
	
	public void setHasCfrLogo(Integer hasCfrLogo) {
		this.hasCfrLogo = hasCfrLogo;
	}
	
	/**
	 * 返回 has_cfr_logo
	 * @return
	 */
	public Integer getHasCfrLogo() {
		return this.hasCfrLogo;
	}
	
	public void setIssueLevel(String issueLevel) {
		this.issueLevel = issueLevel;
	}
	
	/**
	 * 返回 issue_level
	 * @return
	 */
	public String getIssueLevel() {
		return this.issueLevel;
	}
	
	public void setOverdue(Integer overdue) {
		this.overdue = overdue;
	}
	
	/**
	 * 返回 overdue
	 * @return
	 */
	public Integer getOverdue() {
		return this.overdue;
	}
	
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	/**
	 * 返回 page_name
	 * @return
	 */
	public String getPageName() {
		return this.pageName;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("fileId", this.fileId) 
		.append("certVersion", this.certVersion) 
		.append("name", this.name) 
		.append("certType", this.certType) 
		.append("sheetType", this.sheetType) 
		.append("type", this.type) 
		.append("isInShip", this.isInShip) 
		.append("isSpecial", this.isSpecial) 
		.append("pageNum", this.pageNum) 
		.append("deleted", this.deleted) 
		.append("pageType", this.pageType) 
		.append("remark", this.remark) 
		.append("orderId", this.orderId) 
		.append("hasCfrLogo", this.hasCfrLogo) 
		.append("issueLevel", this.issueLevel) 
		.append("overdue", this.overdue) 
		.append("pageName", this.pageName) 
		.toString();
	}
}