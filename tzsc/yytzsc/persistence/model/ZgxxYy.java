package com.sky.tzsc.yytzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查整改信息]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-11 09:38:35
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class ZgxxYy extends AbstractModel<String>{
	
	/**
	* id
	*/
	protected String id; 
	
	/**
	* 远洋图纸申请表主键
	*/
	protected String sqId; 
	
	/**
	* 整改人
	*/
	protected String zgr; 
	
	/**
	* 整改日期
	*/
	protected java.util.Date zgrq; 
	
	/**
	* 整改情况
	*/
	protected String zgqk; 
	
	/**
	* 整改结论
	*/
	protected String zgjl; 
	
	/**
	* 是否结束整改
	*/
	protected Integer sfjszg; 
	
	/**
	* 插入时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 新增用户
	*/
	protected String insertUser; 
	
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
	
	public void setSqId(String sqId) {
		this.sqId = sqId;
	}
	
	/**
	 * 返回 远洋图纸申请表主键
	 * @return
	 */
	public String getSqId() {
		return this.sqId;
	}
	
	public void setZgr(String zgr) {
		this.zgr = zgr;
	}
	
	/**
	 * 返回 整改人
	 * @return
	 */
	public String getZgr() {
		return this.zgr;
	}
	
	public void setZgrq(java.util.Date zgrq) {
		this.zgrq = zgrq;
	}
	
	/**
	 * 返回 整改日期
	 * @return
	 */
	public java.util.Date getZgrq() {
		return this.zgrq;
	}
	
	public void setZgqk(String zgqk) {
		this.zgqk = zgqk;
	}
	
	/**
	 * 返回 整改情况
	 * @return
	 */
	public String getZgqk() {
		return this.zgqk;
	}
	
	public void setZgjl(String zgjl) {
		this.zgjl = zgjl;
	}
	
	/**
	 * 返回 整改结论
	 * @return
	 */
	public String getZgjl() {
		return this.zgjl;
	}
	
	public void setSfjszg(Integer sfjszg) {
		this.sfjszg = sfjszg;
	}
	
	/**
	 * 返回 是否结束整改
	 * @return
	 */
	public Integer getSfjszg() {
		return this.sfjszg;
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
	 * 返回 新增用户
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
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
		.append("sqId", this.sqId) 
		.append("zgr", this.zgr) 
		.append("zgrq", this.zgrq) 
		.append("zgqk", this.zgqk) 
		.append("zgjl", this.zgjl) 
		.append("sfjszg", this.sfjszg) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("scbj", this.scbj) 
		.toString();
	}
}