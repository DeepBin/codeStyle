package com.sky.tzsc.gntzsc.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.util.AppUtil;
import com.sky.api.sys.dict.service.ISysDictService;
import com.sky.common.model.AbstractModel;
import com.sky.common.util.StaticMethod;
import com.sky.sys.dict.persistence.model.SysDict;
import com.sky.sys.district.persistence.manager.SysDistrictManager;
import com.sky.sys.login.persistence.manager.SysLoginManager;


/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-20 15:37:20
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Bzcx extends AbstractModel<String>{
	ISysDictService sysDictService = (ISysDictService) AppUtil.getBean("sysDictService");
	SysLoginManager sysLoginManager = (SysLoginManager) AppUtil.getBean("sysLoginManager");
	SysDistrictManager sysDistrictManager = (SysDistrictManager) AppUtil.getBean("sysDistrictManager");
	
	/**
	* 标准船型表主键
	*/
	protected String id; 
	
	/**
	* 船型代号
	*/
	protected String cxdh; 
	
	/**
	* 图纸批准文号
	*/
	protected String tzpzwh; 
	
	/**
	* 船名
	*/
	protected String cm; 
	
	/**
	* 建造完工日期
	*/
	protected java.util.Date jzwgrq; 
	
	/**
	* 渔船编码
	*/
	protected String ycbm; 
	
	/**
	* 检验登记号
	*/
	protected String jydjh; 
	
	/**
	* 船舶种类
	*/
	protected String cbzl; 
	
	/**
	 * 船舶种类名称
	 */
	protected String cbzlName;
	
	/**
	* 同型船建造数量
	*/
	protected Integer txcjzsl; 
	
	/**
	* 船体材质
	*/
	protected String ctcz; 
	
	/**
	 * 船体材质名称
	 */
	protected String ctczName;
	/**
	* 保鲜方式
	*/
	protected String bxfs; 
	
	/**
	 * 保险方式名称
	 */
	protected String bxfsName;
	
	/**
	* 航区
	*/
	protected String hq; 
	
	/**
	* 甲板层数
	*/
	protected Integer jbcs; 
	
	/**
	* 总长(m)
	*/
	protected Double zc; 
	
	/**
	* 公约船长(m)
	*/
	protected Double gycc; 
	
	/**
	* 型宽(m)
	*/
	protected Double xk; 
	
	/**
	* 型深(m)
	*/
	protected Double xs; 
	
	/**
	* 设计吃水(m)
	*/
	protected Integer sjcs; 
	
	/**
	* 设计排水量(t)
	*/
	protected Integer sjpsl; 
	
	/**
	* 设计航速(km)
	*/
	protected Integer sjhs; 
	
	/**
	* 船员定额
	*/
	protected Integer cyde; 
	
	/**
	* 干舷（m）
	*/
	protected String gx; 
	
	/**
	* 鱼舱容积（m3）
	*/
	protected Integer rjYucang; 
	
	/**
	* 淡水舱容积（m3）
	*/
	protected Integer dscrj; 
	
	/**
	* 油舱容积（m3）
	*/
	protected Integer rjYoucang; 
	
	/**
	* 空船重量（t）
	*/
	protected Integer kczl; 
	
	/**
	* 固定压载（t）
	*/
	protected Integer gdyz; 
	
	/**
	* 系柱拖力（kN）
	*/
	protected Integer xztl; 
	
	/**
	* 螺旋桨敞水效率
	*/
	protected String lxjcsxl; 
	
	/**
	* 总吨
	*/
	protected Double zd; 
	
	/**
	* 净吨
	*/
	protected Double jd; 
	
	/**
	* 主机功率（KW）
	*/
	protected Double zjgl; 
	
	/**
	* 推进功率（KW）
	*/
	protected Double tjgl; 
	
	/**
	* 满载出港K
	*/
	protected String mzcg; 
	
	/**
	* 满载离渔场K
	*/
	protected String mzlyc; 
	
	/**
	* 空载到港K
	*/
	protected String kzdg; 
	
	/**
	* 是否为可调桨
	*/
	protected String sfwktj; 
	
	/**
	* 设计单位
	*/
	protected String sjdw; 
	
	/**
	* 渔船建造厂
	*/
	protected String ycjzc; 
	
	/**
	* 备注
	*/
	protected String bz; 
	
	/**
	 * 所属地区
	 */
	protected String ssdq;
	
	/**
	 * 所属地区id
	 */
	protected String ssdqId;
	/**
	 * 版权单位
	 */
	protected String bqdw;
	/**
	 * 船型评价总得分
	 */
	protected String  cxpjzdf;
	/**
	 * 三级评价得分
	 */
	protected String  sjpjdf;
	/**
	 * 二级评价得分
	 */
	protected String ejpjdf;
	/**
	 * 提交标记  0 保存 1 保存
	 */
	protected String tjbj;
	
	/**
	* 全船说明书
	*/
	protected String qcsms; 
	
	public String getQcsms() {
		return qcsms;
	}

	public void setQcsms(String qcsms) {
		this.qcsms = qcsms;
	}

	public String getZbzt() {
		return zbzt;
	}

	public void setZbzt(String zbzt) {
		this.zbzt = zbzt;
	}

	public String getWgwdxjss() {
		return wgwdxjss;
	}

	public void setWgwdxjss(String wgwdxjss) {
		this.wgwdxjss = wgwdxjss;
	}

	public String getDxhpmt() {
		return dxhpmt;
	}

	public void setDxhpmt(String dxhpmt) {
		this.dxhpmt = dxhpmt;
	}

	public String getJcbzt() {
		return jcbzt;
	}

	public void setJcbzt(String jcbzt) {
		this.jcbzt = jcbzt;
	}

	public String getDqsbbzt() {
		return dqsbbzt;
	}

	public void setDqsbbzt(String dqsbbzt) {
		this.dqsbbzt = dqsbbzt;
	}

	public String getYlsbbzt() {
		return ylsbbzt;
	}

	public void setYlsbbzt(String ylsbbzt) {
		this.ylsbbzt = ylsbbzt;
	}

	public String getYcsczp() {
		return ycsczp;
	}

	public void setYcsczp(String ycsczp) {
		this.ycsczp = ycsczp;
	}

	public String getYcshbg() {
		return ycshbg;
	}

	public void setYcshbg(String ycshbg) {
		this.ycshbg = ycshbg;
	}

	public String getLxjjss() {
		return lxjjss;
	}

	public void setLxjjss(String lxjjss) {
		this.lxjjss = lxjjss;
	}

	public String getGdyzbzt() {
		return gdyzbzt;
	}

	public void setGdyzbzt(String gdyzbzt) {
		this.gdyzbzt = gdyzbzt;
	}

	public String getXztlsybg() {
		return xztlsybg;
	}

	public void setXztlsybg(String xztlsybg) {
		this.xztlsybg = xztlsybg;
	}
	/**
	* 总布置图
	*/
	protected String zbzt; 
	
	/**
	* 完工稳性说明书
	*/
	protected String wgwdxjss; 
	
	/**
	* 典型横剖面图
	*/
	protected String dxhpmt; 
	
	/**
	* 机舱布置图
	*/
	protected String jcbzt; 
	
	/**
	* 电气设备布置图
	*/
	protected String dqsbbzt; 
	
	/**
	* 渔捞设备布置图
	*/
	protected String ylsbbzt; 
	
	/**
	* 样船实船照片
	*/
	protected String ycsczp; 
	
	/**
	* 样船试航报告
	*/
	protected String ycshbg; 
	
	/**
	* 螺旋桨计算书
	*/
	protected String lxjjss; 
	
	/**
	* 固定压载布置图
	*/
	protected String gdyzbzt; 
	
	/**
	* xztlsybg
	*/
	protected String xztlsybg; 
	
	 

	public String getTjbj() {
		return tjbj;
	}

	public void setTjbj(String tjbj) {
		this.tjbj = tjbj;
	}

	public String getSsdq() {
		return ssdq;
	}

	public void setSsdq(String ssdq) {
		this.ssdq = ssdq;
	}

	public String getSsdqId() {
		return ssdqId;
	}

	public void setSsdqId(String ssdqId) {
		this.ssdqId = ssdqId;
	}

	public String getBqdw() {
		return bqdw;
	}

	public void setBqdw(String bqdw) {
		this.bqdw = bqdw;
	}

	public String getCxpjzdf() {
		return cxpjzdf;
	}

	public void setCxpjzdf(String cxpjzdf) {
		this.cxpjzdf = cxpjzdf;
	}

	public String getSjpjdf() {
		return sjpjdf;
	}

	public void setSjpjdf(String sjpjdf) {
		this.sjpjdf = sjpjdf;
	}

	public String getEjpjdf() {
		return ejpjdf;
	}

	public void setEjpjdf(String ejpjdf) {
		this.ejpjdf = ejpjdf;
	}
	/**
	* 修改时间
	*/
	protected java.util.Date xgsj; 
	
	/**
	* 修改用户
	*/
	protected String xgyh; 
	
	
	/**
	* 录入时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 录入人
	*/
	protected String insertUser; 
	
	/**
	* 录入人登录账号
	*/
	protected String insertLogin; 
	
	/**
	* 录入人部门
	*/
	protected Integer insertDept; 
	
	/**
	* 删除标记
	*/
	protected Integer scbj; 
	
	protected String ssdqName;
	
	
	public String getSsdqName() {
		return  sysDistrictManager.getDistrict(this.ssdqId).getDistrictName();
	}

	public void setSsdqName(String ssdqName) {
		this.ssdqName = ssdqName;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 标准船型表主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setCxdh(String cxdh) {
		this.cxdh = cxdh;
	}
	
	/**
	 * 返回 船型代号
	 * @return
	 */
	public String getCxdh() {
		return this.cxdh;
	}
	
	public void setTzpzwh(String tzpzwh) {
		this.tzpzwh = tzpzwh;
	}
	
	/**
	 * 返回 图纸批准文号
	 * @return
	 */
	public String getTzpzwh() {
		return this.tzpzwh;
	}
	
	public void setCm(String cm) {
		this.cm = cm;
	}
	
	/**
	 * 返回 船名
	 * @return
	 */
	public String getCm() {
		return this.cm;
	}
	
	public void setJzwgrq(java.util.Date jzwgrq) {
		this.jzwgrq = jzwgrq;
	}
	
	/**
	 * 返回 建造完工日期
	 * @return
	 */
	public java.util.Date getJzwgrq() {
		return this.jzwgrq;
	}
	
	public void setYcbm(String ycbm) {
		this.ycbm = ycbm;
	}
	
	/**
	 * 返回 渔船编码
	 * @return
	 */
	public String getYcbm() {
		return this.ycbm;
	}
	
	public void setJydjh(String jydjh) {
		this.jydjh = jydjh;
	}
	
	/**
	 * 返回 检验登记号
	 * @return
	 */
	public String getJydjh() {
		return this.jydjh;
	}
	
	public void setCbzl(String cbzl) {
		this.cbzl = cbzl;
	}
	
	
	
	public java.util.Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getInsertLogin() {
		return insertLogin;
	}

	public void setInsertLogin(String insertLogin) {
		this.insertLogin = insertLogin;
	}

	public Integer getInsertDept() {
		return insertDept;
	}

	public void setInsertDept(Integer insertDept) {
		this.insertDept = insertDept;
	}

	/**
	 * 返回 船舶种类
	 * @return
	 */
	
	public void setTxcjzsl(Integer txcjzsl) {
		this.txcjzsl = txcjzsl;
	}
	
	public String getCbzlName() {
		SysDict sysDict = sysDictService.getSysDictByDictCodeAndDictId("CBLX",this.getCbzl());
		if(StaticMethod.isNotEmpty(sysDict)) {
			return sysDict.getDictName();
		}
		return cbzlName;
	}

	public String getCbzl() {
		return cbzl;
	}

	public String getCtczName() {
		SysDict sysDict = sysDictService.getSysDictByDictCodeAndDictId("CTCZ",this.getCtcz());
		if(StaticMethod.isNotEmpty(sysDict)) {
			return sysDict.getDictName();
		}
		return ctczName;
	}

	/**
	 * 返回 同型船建造数量
	 * @return
	 */
	public Integer getTxcjzsl() {
		return this.txcjzsl;
	}
	
	public void setCtcz(String ctcz) {
		this.ctcz = ctcz;
	}
	
	/**
	 * 返回 船体材质
	 * @return
	 */
	public String getCtcz() {
		return this.ctcz;
	}
	
	public void setBxfs(String bxfs) {
		this.bxfs = bxfs;
	}
	
	/**
	 * 返回 保鲜方式
	 * @return
	 */
	public String getBxfs() {
		return this.bxfs;
	}
	
	
	public String getBxfsName() {
		SysDict sysDict = sysDictService.getSysDictByDictCodeAndDictId("BXFS",this.getBxfs());
		if(StaticMethod.isNotEmpty(sysDict)) {
			return sysDict.getDictName();
		}
		return bxfsName;
	}

	public void setHq(String hq) {
		this.hq = hq;
	}
	
	/**
	 * 返回 航区
	 * @return
	 */
	public String getHq() {
		return this.hq;
	}
	
	public void setJbcs(Integer jbcs) {
		this.jbcs = jbcs;
	}
	
	/**
	 * 返回 甲板层数
	 * @return
	 */
	public Integer getJbcs() {
		return this.jbcs;
	}
	
	public void setZc(Double zc) {
		this.zc = zc;
	}
	
	/**
	 * 返回 总长(m)
	 * @return
	 */
	public Double getZc() {
		return this.zc;
	}
	
	public void setGycc(Double gycc) {
		this.gycc = gycc;
	}
	
	/**
	 * 返回 公约船长(m)
	 * @return
	 */
	public Double getGycc() {
		return this.gycc;
	}
	
	public void setXk(Double xk) {
		this.xk = xk;
	}
	
	/**
	 * 返回 型宽(m)
	 * @return
	 */
	public Double getXk() {
		return this.xk;
	}
	
	public void setXs(Double xs) {
		this.xs = xs;
	}
	
	/**
	 * 返回 型深(m)
	 * @return
	 */
	public Double getXs() {
		return this.xs;
	}
	
	public void setSjcs(Integer sjcs) {
		this.sjcs = sjcs;
	}
	
	/**
	 * 返回 设计吃水(m)
	 * @return
	 */
	public Integer getSjcs() {
		return this.sjcs;
	}
	
	public void setSjpsl(Integer sjpsl) {
		this.sjpsl = sjpsl;
	}
	
	/**
	 * 返回 设计排水量(t)
	 * @return
	 */
	public Integer getSjpsl() {
		return this.sjpsl;
	}
	
	public void setSjhs(Integer sjhs) {
		this.sjhs = sjhs;
	}
	
	/**
	 * 返回 设计航速(km)
	 * @return
	 */
	public Integer getSjhs() {
		return this.sjhs;
	}
	
	public void setCyde(Integer cyde) {
		this.cyde = cyde;
	}
	
	/**
	 * 返回 船员定额
	 * @return
	 */
	public Integer getCyde() {
		return this.cyde;
	}
	
	public void setGx(String gx) {
		this.gx = gx;
	}
	
	/**
	 * 返回 干舷（m）
	 * @return
	 */
	public String getGx() {
		return this.gx;
	}
	
	public void setRjYucang(Integer rjYucang) {
		this.rjYucang = rjYucang;
	}
	
	/**
	 * 返回 鱼舱容积（m3）
	 * @return
	 */
	public Integer getRjYucang() {
		return this.rjYucang;
	}
	
	public void setDscrj(Integer dscrj) {
		this.dscrj = dscrj;
	}
	
	/**
	 * 返回 淡水舱容积（m3）
	 * @return
	 */
	public Integer getDscrj() {
		return this.dscrj;
	}
	
	public void setRjYoucang(Integer rjYoucang) {
		this.rjYoucang = rjYoucang;
	}
	
	/**
	 * 返回 油舱容积（m3）
	 * @return
	 */
	public Integer getRjYoucang() {
		return this.rjYoucang;
	}
	
	public void setKczl(Integer kczl) {
		this.kczl = kczl;
	}
	
	/**
	 * 返回 空船重量（t）
	 * @return
	 */
	public Integer getKczl() {
		return this.kczl;
	}
	
	public void setGdyz(Integer gdyz) {
		this.gdyz = gdyz;
	}
	
	/**
	 * 返回 固定压载（t）
	 * @return
	 */
	public Integer getGdyz() {
		return this.gdyz;
	}
	
	public void setXztl(Integer xztl) {
		this.xztl = xztl;
	}
	
	/**
	 * 返回 系柱拖力（kN）
	 * @return
	 */
	public Integer getXztl() {
		return this.xztl;
	}
	
	public void setLxjcsxl(String lxjcsxl) {
		this.lxjcsxl = lxjcsxl;
	}
	
	/**
	 * 返回 螺旋桨敞水效率
	 * @return
	 */
	public String getLxjcsxl() {
		return this.lxjcsxl;
	}
	
	public void setZd(Double zd) {
		this.zd = zd;
	}
	
	/**
	 * 返回 总吨
	 * @return
	 */
	public Double getZd() {
		return this.zd;
	}
	
	public void setJd(Double jd) {
		this.jd = jd;
	}
	
	/**
	 * 返回 净吨
	 * @return
	 */
	public Double getJd() {
		return this.jd;
	}
	
	public void setZjgl(Double zjgl) {
		this.zjgl = zjgl;
	}
	
	/**
	 * 返回 主机功率（KW）
	 * @return
	 */
	public Double getZjgl() {
		return this.zjgl;
	}
	
	public void setTjgl(Double tjgl) {
		this.tjgl = tjgl;
	}
	
	/**
	 * 返回 推进功率（KW）
	 * @return
	 */
	public Double getTjgl() {
		return this.tjgl;
	}
	
	public void setMzcg(String mzcg) {
		this.mzcg = mzcg;
	}
	
	/**
	 * 返回 满载出港K
	 * @return
	 */
	public String getMzcg() {
		return this.mzcg;
	}
	
	public void setMzlyc(String mzlyc) {
		this.mzlyc = mzlyc;
	}
	
	/**
	 * 返回 满载离渔场K
	 * @return
	 */
	public String getMzlyc() {
		return this.mzlyc;
	}
	
	public void setKzdg(String kzdg) {
		this.kzdg = kzdg;
	}
	
	/**
	 * 返回 空载到港K
	 * @return
	 */
	public String getKzdg() {
		return this.kzdg;
	}
	
	public void setSfwktj(String sfwktj) {
		this.sfwktj = sfwktj;
	}
	
	/**
	 * 返回 是否为可调桨
	 * @return
	 */
	public String getSfwktj() {
		return this.sfwktj;
	}
	
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	
	/**
	 * 返回 设计单位
	 * @return
	 */
	public String getSjdw() {
		return this.sjdw;
	}
	
	public void setYcjzc(String ycjzc) {
		this.ycjzc = ycjzc;
	}
	
	/**
	 * 返回 渔船建造厂
	 * @return
	 */
	public String getYcjzc() {
		return this.ycjzc;
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
	 * 返回 修改时间
	 * @return
	 */
	public java.util.Date getXgsj() {
		return this.xgsj;
	}
	
	public void setXgyh(String xgyh) {
		this.xgyh = xgyh;
	}
	
	/**
	 * 返回 修改用户
	 * @return
	 */
	public String getXgyh() {
		return this.xgyh;
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
		.append("cxdh", this.cxdh) 
		.append("tzpzwh", this.tzpzwh) 
		.append("cm", this.cm) 
		.append("jzwgrq", this.jzwgrq) 
		.append("ycbm", this.ycbm) 
		.append("jydjh", this.jydjh) 
		.append("cbzl", this.cbzl) 
		.append("txcjzsl", this.txcjzsl) 
		.append("ctcz", this.ctcz) 
		.append("bxfs", this.bxfs) 
		.append("hq", this.hq) 
		.append("jbcs", this.jbcs) 
		.append("zc", this.zc) 
		.append("gycc", this.gycc) 
		.append("xk", this.xk) 
		.append("xs", this.xs) 
		.append("sjcs", this.sjcs) 
		.append("sjpsl", this.sjpsl) 
		.append("sjhs", this.sjhs) 
		.append("cyde", this.cyde) 
		.append("gx", this.gx) 
		.append("rjYucang", this.rjYucang) 
		.append("dscrj", this.dscrj) 
		.append("rjYoucang", this.rjYoucang) 
		.append("kczl", this.kczl) 
		.append("gdyz", this.gdyz) 
		.append("xztl", this.xztl) 
		.append("lxjcsxl", this.lxjcsxl) 
		.append("zd", this.zd) 
		.append("jd", this.jd) 
		.append("zjgl", this.zjgl) 
		.append("tjgl", this.tjgl) 
		.append("mzcg", this.mzcg) 
		.append("mzlyc", this.mzlyc) 
		.append("kzdg", this.kzdg) 
		.append("sfwktj", this.sfwktj) 
		.append("sjdw", this.sjdw) 
		.append("ycjzc", this.ycjzc) 
		.append("bz", this.bz) 
		.append("xgsj", this.xgsj) 
		.append("xgyh", this.xgyh) 
		.append("scbj", this.scbj) 
		.toString();
	}
}