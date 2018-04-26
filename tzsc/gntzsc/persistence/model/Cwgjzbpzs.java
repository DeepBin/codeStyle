package com.sky.tzsc.gntzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[tzsc_cwgjzbpzs]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 14:39:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Cwgjzbpzs extends AbstractModel<String>{
	
	/**
	* 船网工具指标批准书表主键
	*/
	protected String id; 
	
	/**
	* 船网批准书编号
	*/
	protected String cwpzsbh; 
	
	/**
	* 申请船名
	*/
	protected String sqcm; 
	
	/**
	* 到期日期
	*/
	protected java.util.Date dqrq; 
	
	/**
	* 企业名称
	*/
	protected String qymc; 
	
	/**
	* 企业地址
	*/
	protected String qydz; 
	
	/**
	* 企业性质
	*/
	protected String qyxz; 
	
	/**
	* 企业邮政编码
	*/
	protected String qyyzbm; 
	
	/**
	* 企业联系电话
	*/
	protected String qylxdh; 
	
	/**
	* 企业法人
	*/
	protected String qyfr; 
	
	/**
	* 双控功率
	*/
	protected Double skgl; 
	
	/**
	* 船长
	*/
	protected Double cc; 
	
	/**
	* 总吨位
	*/
	protected Double zdw; 
	
	/**
	* 船舶检验证书编号
	*/
	protected String cbjyzsbh; 
	
	/**
	* 船舶登记证书编号
	*/
	protected String cbdjzsbh; 
	
	/**
	* 捕捞许可证编号
	*/
	protected String blxkzbh; 
	
	/**
	* 签发地区
	*/
	protected String qfdq; 
	
	/**
	* 签发人
	*/
	protected String qfr; 
	
	/**
	* 签发时间
	*/
	protected java.util.Date qfsj; 
	
	/**
	* 签发部门
	*/
	protected String qfbm; 
	
	/**
	* 使用标识
	*/
	protected Integer sybs; 
	
	/**
	* 删除标识
	*/
	protected Integer scbs; 
	
	/**
	* 插入时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* insert_user
	*/
	protected String insertUser; 
	
	/**
	* 创建用户loginId
	*/
	protected String insertLogin; 
	
	/**
	* insert_dept
	*/
	protected String insertDept; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 船网工具指标批准书表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setCwpzsbh(String cwpzsbh) {
		this.cwpzsbh = cwpzsbh;
	}
	
	/**
	 * 返回 船网批准书编号
	 * @return
	 */
	public String getCwpzsbh() {
		return this.cwpzsbh;
	}
	
	public void setSqcm(String sqcm) {
		this.sqcm = sqcm;
	}
	
	/**
	 * 返回 申请船名
	 * @return
	 */
	public String getSqcm() {
		return this.sqcm;
	}
	
	public void setDqrq(java.util.Date dqrq) {
		this.dqrq = dqrq;
	}
	
	/**
	 * 返回 到期日期
	 * @return
	 */
	public java.util.Date getDqrq() {
		return this.dqrq;
	}
	
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
	/**
	 * 返回 企业名称
	 * @return
	 */
	public String getQymc() {
		return this.qymc;
	}
	
	public void setQydz(String qydz) {
		this.qydz = qydz;
	}
	
	/**
	 * 返回 企业地址
	 * @return
	 */
	public String getQydz() {
		return this.qydz;
	}
	
	public void setQyxz(String qyxz) {
		this.qyxz = qyxz;
	}
	
	/**
	 * 返回 企业性质
	 * @return
	 */
	public String getQyxz() {
		return this.qyxz;
	}
	
	public void setQyyzbm(String qyyzbm) {
		this.qyyzbm = qyyzbm;
	}
	
	/**
	 * 返回 企业邮政编码
	 * @return
	 */
	public String getQyyzbm() {
		return this.qyyzbm;
	}
	
	public void setQylxdh(String qylxdh) {
		this.qylxdh = qylxdh;
	}
	
	/**
	 * 返回 企业联系电话
	 * @return
	 */
	public String getQylxdh() {
		return this.qylxdh;
	}
	
	public void setQyfr(String qyfr) {
		this.qyfr = qyfr;
	}
	
	/**
	 * 返回 企业法人
	 * @return
	 */
	public String getQyfr() {
		return this.qyfr;
	}
	
	public void setSkgl(Double skgl) {
		this.skgl = skgl;
	}
	
	/**
	 * 返回 双控功率
	 * @return
	 */
	public Double getSkgl() {
		return this.skgl;
	}
	
	public void setCc(Double cc) {
		this.cc = cc;
	}
	
	/**
	 * 返回 船长
	 * @return
	 */
	public Double getCc() {
		return this.cc;
	}
	
	public void setZdw(Double zdw) {
		this.zdw = zdw;
	}
	
	/**
	 * 返回 总吨位
	 * @return
	 */
	public Double getZdw() {
		return this.zdw;
	}
	
	public void setCbjyzsbh(String cbjyzsbh) {
		this.cbjyzsbh = cbjyzsbh;
	}
	
	/**
	 * 返回 船舶检验证书编号
	 * @return
	 */
	public String getCbjyzsbh() {
		return this.cbjyzsbh;
	}
	
	public void setCbdjzsbh(String cbdjzsbh) {
		this.cbdjzsbh = cbdjzsbh;
	}
	
	/**
	 * 返回 船舶登记证书编号
	 * @return
	 */
	public String getCbdjzsbh() {
		return this.cbdjzsbh;
	}
	
	public void setBlxkzbh(String blxkzbh) {
		this.blxkzbh = blxkzbh;
	}
	
	/**
	 * 返回 捕捞许可证编号
	 * @return
	 */
	public String getBlxkzbh() {
		return this.blxkzbh;
	}
	
	public void setQfdq(String qfdq) {
		this.qfdq = qfdq;
	}
	
	/**
	 * 返回 签发地区
	 * @return
	 */
	public String getQfdq() {
		return this.qfdq;
	}
	
	public void setQfr(String qfr) {
		this.qfr = qfr;
	}
	
	/**
	 * 返回 签发人
	 * @return
	 */
	public String getQfr() {
		return this.qfr;
	}
	
	public void setQfsj(java.util.Date qfsj) {
		this.qfsj = qfsj;
	}
	
	/**
	 * 返回 签发时间
	 * @return
	 */
	public java.util.Date getQfsj() {
		return this.qfsj;
	}
	
	public void setQfbm(String qfbm) {
		this.qfbm = qfbm;
	}
	
	/**
	 * 返回 签发部门
	 * @return
	 */
	public String getQfbm() {
		return this.qfbm;
	}
	
	public void setSybs(Integer sybs) {
		this.sybs = sybs;
	}
	
	/**
	 * 返回 使用标识
	 * @return
	 */
	public Integer getSybs() {
		return this.sybs;
	}
	
	public void setScbs(Integer scbs) {
		this.scbs = scbs;
	}
	
	/**
	 * 返回 删除标识
	 * @return
	 */
	public Integer getScbs() {
		return this.scbs;
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
	 * 返回 创建用户loginId
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("cwpzsbh", this.cwpzsbh) 
		.append("sqcm", this.sqcm) 
		.append("dqrq", this.dqrq) 
		.append("qymc", this.qymc) 
		.append("qydz", this.qydz) 
		.append("qyxz", this.qyxz) 
		.append("qyyzbm", this.qyyzbm) 
		.append("qylxdh", this.qylxdh) 
		.append("qyfr", this.qyfr) 
		.append("skgl", this.skgl) 
		.append("cc", this.cc) 
		.append("zdw", this.zdw) 
		.append("cbjyzsbh", this.cbjyzsbh) 
		.append("cbdjzsbh", this.cbdjzsbh) 
		.append("blxkzbh", this.blxkzbh) 
		.append("qfdq", this.qfdq) 
		.append("qfr", this.qfr) 
		.append("qfsj", this.qfsj) 
		.append("qfbm", this.qfbm) 
		.append("sybs", this.sybs) 
		.append("scbs", this.scbs) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("insertLogin", this.insertLogin) 
		.append("insertDept", this.insertDept) 
		.toString();
	}
}