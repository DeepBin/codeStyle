package com.sky.tzsc.gntzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.sky.common.model.AbstractModel;


/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx_history]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-02-26 09:17:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class BzcxHistory extends AbstractModel<String>{
	
	/**
	* id
	*/
	protected String id; 
	
	/**
	* bzcx_id
	*/
	protected String bzcxId; 
	
	/**
	* cxdh
	*/
	protected String cxdh; 
	
	/**
	* tzpzwh
	*/
	protected String tzpzwh; 
	
	/**
	* cm
	*/
	protected String cm; 
	
	/**
	* jzwgrq
	*/
	protected java.util.Date jzwgrq; 
	
	/**
	* ycbm
	*/
	protected String ycbm; 
	
	/**
	* jydjh
	*/
	protected String jydjh; 
	
	/**
	* cbzl
	*/
	protected String cbzl; 
	
	/**
	* txcjzsl
	*/
	protected Integer txcjzsl; 
	
	/**
	* ctcz
	*/
	protected String ctcz; 
	
	/**
	* bxfs
	*/
	protected String bxfs; 
	
	/**
	* hq
	*/
	protected String hq; 
	
	/**
	* jbcs
	*/
	protected Integer jbcs; 
	
	/**
	* zc
	*/
	protected Double zc; 
	
	/**
	* gycc
	*/
	protected Double gycc; 
	
	/**
	* xk
	*/
	protected Double xk; 
	
	/**
	* xs
	*/
	protected Double xs; 
	
	/**
	* sjcs
	*/
	protected Integer sjcs; 
	
	/**
	* sjpsl
	*/
	protected Integer sjpsl; 
	
	/**
	* sjhs
	*/
	protected Integer sjhs; 
	
	/**
	* cyde
	*/
	protected Integer cyde; 
	
	/**
	* gx
	*/
	protected String gx; 
	
	/**
	* rj_yucang
	*/
	protected Integer rjYucang; 
	
	/**
	* dscrj
	*/
	protected Integer dscrj; 
	
	/**
	* rj_youcang
	*/
	protected Integer rjYoucang; 
	
	/**
	* kczl
	*/
	protected Integer kczl; 
	
	/**
	* gdyz
	*/
	protected Integer gdyz; 
	
	/**
	* xztl
	*/
	protected Integer xztl; 
	
	/**
	* lxjcsxl
	*/
	protected String lxjcsxl; 
	
	/**
	* zd
	*/
	protected Double zd; 
	
	/**
	* jd
	*/
	protected Double jd; 
	
	/**
	* zjgl
	*/
	protected Double zjgl; 
	
	/**
	* tjgl
	*/
	protected Double tjgl; 
	
	/**
	* mzcg
	*/
	protected String mzcg; 
	
	/**
	* mzlyc
	*/
	protected String mzlyc; 
	
	/**
	* kzdg
	*/
	protected String kzdg; 
	
	/**
	* sfwktj
	*/
	protected String sfwktj; 
	
	/**
	* sjdw
	*/
	protected String sjdw; 
	
	/**
	* ycjzc
	*/
	protected String ycjzc; 
	
	/**
	* bz
	*/
	protected String bz; 
	
	/**
	* ssdq
	*/
	protected String ssdq; 
	
	/**
	* ssdq_id
	*/
	protected String ssdqId; 
	
	/**
	* bqdw
	*/
	protected String bqdw; 
	
	/**
	* cxpjzdf
	*/
	protected String cxpjzdf; 
	
	/**
	* sjpjdf
	*/
	protected String sjpjdf; 
	
	/**
	* ejpjdf
	*/
	protected String ejpjdf; 
	
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
	* xgsj
	*/
	protected java.util.Date xgsj; 
	
	/**
	* xgyh
	*/
	protected String xgyh; 
	
	/**
	* scbj
	*/
	protected Integer scbj; 
	
	/**
	* tjbj
	*/
	protected Integer tjbj; 
	
	/**
	* qcsms
	*/
	protected String qcsms; 
	
	/**
	* zbzt
	*/
	protected String zbzt; 
	
	/**
	* wgwdxjss
	*/
	protected String wgwdxjss; 
	
	/**
	* dxhpmt
	*/
	protected String dxhpmt; 
	
	/**
	* jcbzt
	*/
	protected String jcbzt; 
	
	/**
	* dqsbbzt
	*/
	protected String dqsbbzt; 
	
	/**
	* ylsbbzt
	*/
	protected String ylsbbzt; 
	
	/**
	* ycsczp
	*/
	protected String ycsczp; 
	
	/**
	* ycshbg
	*/
	protected String ycshbg; 
	
	/**
	* lxjjss
	*/
	protected String lxjjss; 
	
	/**
	* gdyzbzt
	*/
	protected String gdyzbzt; 
	
	/**
	* xztlsybg
	*/
	protected String xztlsybg; 
	
	/**
	* 修改时间
	*/
	protected java.util.Date updateTime; 
	
	/**
	* update_user
	*/
	protected String updateUser; 
	
	/**
	* update_dept
	*/
	protected String updateDept; 
	
	
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
	
	public void setBzcxId(String bzcxId) {
		this.bzcxId = bzcxId;
	}
	
	/**
	 * 返回 bzcx_id
	 * @return
	 */
	public String getBzcxId() {
		return this.bzcxId;
	}
	
	public void setCxdh(String cxdh) {
		this.cxdh = cxdh;
	}
	
	/**
	 * 返回 cxdh
	 * @return
	 */
	public String getCxdh() {
		return this.cxdh;
	}
	
	public void setTzpzwh(String tzpzwh) {
		this.tzpzwh = tzpzwh;
	}
	
	/**
	 * 返回 tzpzwh
	 * @return
	 */
	public String getTzpzwh() {
		return this.tzpzwh;
	}
	
	public void setCm(String cm) {
		this.cm = cm;
	}
	
	/**
	 * 返回 cm
	 * @return
	 */
	public String getCm() {
		return this.cm;
	}
	
	public void setJzwgrq(java.util.Date jzwgrq) {
		this.jzwgrq = jzwgrq;
	}
	
	/**
	 * 返回 jzwgrq
	 * @return
	 */
	public java.util.Date getJzwgrq() {
		return this.jzwgrq;
	}
	
	public void setYcbm(String ycbm) {
		this.ycbm = ycbm;
	}
	
	/**
	 * 返回 ycbm
	 * @return
	 */
	public String getYcbm() {
		return this.ycbm;
	}
	
	public void setJydjh(String jydjh) {
		this.jydjh = jydjh;
	}
	
	/**
	 * 返回 jydjh
	 * @return
	 */
	public String getJydjh() {
		return this.jydjh;
	}
	
	public void setCbzl(String cbzl) {
		this.cbzl = cbzl;
	}
	
	/**
	 * 返回 cbzl
	 * @return
	 */
	public String getCbzl() {
		return this.cbzl;
	}
	
	public void setTxcjzsl(Integer txcjzsl) {
		this.txcjzsl = txcjzsl;
	}
	
	/**
	 * 返回 txcjzsl
	 * @return
	 */
	public Integer getTxcjzsl() {
		return this.txcjzsl;
	}
	
	public void setCtcz(String ctcz) {
		this.ctcz = ctcz;
	}
	
	/**
	 * 返回 ctcz
	 * @return
	 */
	public String getCtcz() {
		return this.ctcz;
	}
	
	public void setBxfs(String bxfs) {
		this.bxfs = bxfs;
	}
	
	/**
	 * 返回 bxfs
	 * @return
	 */
	public String getBxfs() {
		return this.bxfs;
	}
	
	public void setHq(String hq) {
		this.hq = hq;
	}
	
	/**
	 * 返回 hq
	 * @return
	 */
	public String getHq() {
		return this.hq;
	}
	
	public void setJbcs(Integer jbcs) {
		this.jbcs = jbcs;
	}
	
	/**
	 * 返回 jbcs
	 * @return
	 */
	public Integer getJbcs() {
		return this.jbcs;
	}
	
	public void setZc(Double zc) {
		this.zc = zc;
	}
	
	/**
	 * 返回 zc
	 * @return
	 */
	public Double getZc() {
		return this.zc;
	}
	
	public void setGycc(Double gycc) {
		this.gycc = gycc;
	}
	
	/**
	 * 返回 gycc
	 * @return
	 */
	public Double getGycc() {
		return this.gycc;
	}
	
	public void setXk(Double xk) {
		this.xk = xk;
	}
	
	/**
	 * 返回 xk
	 * @return
	 */
	public Double getXk() {
		return this.xk;
	}
	
	public void setXs(Double xs) {
		this.xs = xs;
	}
	
	/**
	 * 返回 xs
	 * @return
	 */
	public Double getXs() {
		return this.xs;
	}
	
	public void setSjcs(Integer sjcs) {
		this.sjcs = sjcs;
	}
	
	/**
	 * 返回 sjcs
	 * @return
	 */
	public Integer getSjcs() {
		return this.sjcs;
	}
	
	public void setSjpsl(Integer sjpsl) {
		this.sjpsl = sjpsl;
	}
	
	/**
	 * 返回 sjpsl
	 * @return
	 */
	public Integer getSjpsl() {
		return this.sjpsl;
	}
	
	public void setSjhs(Integer sjhs) {
		this.sjhs = sjhs;
	}
	
	/**
	 * 返回 sjhs
	 * @return
	 */
	public Integer getSjhs() {
		return this.sjhs;
	}
	
	public void setCyde(Integer cyde) {
		this.cyde = cyde;
	}
	
	/**
	 * 返回 cyde
	 * @return
	 */
	public Integer getCyde() {
		return this.cyde;
	}
	
	public void setGx(String gx) {
		this.gx = gx;
	}
	
	/**
	 * 返回 gx
	 * @return
	 */
	public String getGx() {
		return this.gx;
	}
	
	public void setRjYucang(Integer rjYucang) {
		this.rjYucang = rjYucang;
	}
	
	/**
	 * 返回 rj_yucang
	 * @return
	 */
	public Integer getRjYucang() {
		return this.rjYucang;
	}
	
	public void setDscrj(Integer dscrj) {
		this.dscrj = dscrj;
	}
	
	/**
	 * 返回 dscrj
	 * @return
	 */
	public Integer getDscrj() {
		return this.dscrj;
	}
	
	public void setRjYoucang(Integer rjYoucang) {
		this.rjYoucang = rjYoucang;
	}
	
	/**
	 * 返回 rj_youcang
	 * @return
	 */
	public Integer getRjYoucang() {
		return this.rjYoucang;
	}
	
	public void setKczl(Integer kczl) {
		this.kczl = kczl;
	}
	
	/**
	 * 返回 kczl
	 * @return
	 */
	public Integer getKczl() {
		return this.kczl;
	}
	
	public void setGdyz(Integer gdyz) {
		this.gdyz = gdyz;
	}
	
	/**
	 * 返回 gdyz
	 * @return
	 */
	public Integer getGdyz() {
		return this.gdyz;
	}
	
	public void setXztl(Integer xztl) {
		this.xztl = xztl;
	}
	
	/**
	 * 返回 xztl
	 * @return
	 */
	public Integer getXztl() {
		return this.xztl;
	}
	
	public void setLxjcsxl(String lxjcsxl) {
		this.lxjcsxl = lxjcsxl;
	}
	
	/**
	 * 返回 lxjcsxl
	 * @return
	 */
	public String getLxjcsxl() {
		return this.lxjcsxl;
	}
	
	public void setZd(Double zd) {
		this.zd = zd;
	}
	
	/**
	 * 返回 zd
	 * @return
	 */
	public Double getZd() {
		return this.zd;
	}
	
	public void setJd(Double jd) {
		this.jd = jd;
	}
	
	/**
	 * 返回 jd
	 * @return
	 */
	public Double getJd() {
		return this.jd;
	}
	
	public void setZjgl(Double zjgl) {
		this.zjgl = zjgl;
	}
	
	/**
	 * 返回 zjgl
	 * @return
	 */
	public Double getZjgl() {
		return this.zjgl;
	}
	
	public void setTjgl(Double tjgl) {
		this.tjgl = tjgl;
	}
	
	/**
	 * 返回 tjgl
	 * @return
	 */
	public Double getTjgl() {
		return this.tjgl;
	}
	
	public void setMzcg(String mzcg) {
		this.mzcg = mzcg;
	}
	
	/**
	 * 返回 mzcg
	 * @return
	 */
	public String getMzcg() {
		return this.mzcg;
	}
	
	public void setMzlyc(String mzlyc) {
		this.mzlyc = mzlyc;
	}
	
	/**
	 * 返回 mzlyc
	 * @return
	 */
	public String getMzlyc() {
		return this.mzlyc;
	}
	
	public void setKzdg(String kzdg) {
		this.kzdg = kzdg;
	}
	
	/**
	 * 返回 kzdg
	 * @return
	 */
	public String getKzdg() {
		return this.kzdg;
	}
	
	public void setSfwktj(String sfwktj) {
		this.sfwktj = sfwktj;
	}
	
	/**
	 * 返回 sfwktj
	 * @return
	 */
	public String getSfwktj() {
		return this.sfwktj;
	}
	
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	
	/**
	 * 返回 sjdw
	 * @return
	 */
	public String getSjdw() {
		return this.sjdw;
	}
	
	public void setYcjzc(String ycjzc) {
		this.ycjzc = ycjzc;
	}
	
	/**
	 * 返回 ycjzc
	 * @return
	 */
	public String getYcjzc() {
		return this.ycjzc;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	/**
	 * 返回 bz
	 * @return
	 */
	public String getBz() {
		return this.bz;
	}
	
	public void setSsdq(String ssdq) {
		this.ssdq = ssdq;
	}
	
	/**
	 * 返回 ssdq
	 * @return
	 */
	public String getSsdq() {
		return this.ssdq;
	}
	
	public void setSsdqId(String ssdqId) {
		this.ssdqId = ssdqId;
	}
	
	/**
	 * 返回 ssdq_id
	 * @return
	 */
	public String getSsdqId() {
		return this.ssdqId;
	}
	
	public void setBqdw(String bqdw) {
		this.bqdw = bqdw;
	}
	
	/**
	 * 返回 bqdw
	 * @return
	 */
	public String getBqdw() {
		return this.bqdw;
	}
	
	public void setCxpjzdf(String cxpjzdf) {
		this.cxpjzdf = cxpjzdf;
	}
	
	/**
	 * 返回 cxpjzdf
	 * @return
	 */
	public String getCxpjzdf() {
		return this.cxpjzdf;
	}
	
	public void setSjpjdf(String sjpjdf) {
		this.sjpjdf = sjpjdf;
	}
	
	/**
	 * 返回 sjpjdf
	 * @return
	 */
	public String getSjpjdf() {
		return this.sjpjdf;
	}
	
	public void setEjpjdf(String ejpjdf) {
		this.ejpjdf = ejpjdf;
	}
	
	/**
	 * 返回 ejpjdf
	 * @return
	 */
	public String getEjpjdf() {
		return this.ejpjdf;
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
	
	public void setXgsj(java.util.Date xgsj) {
		this.xgsj = xgsj;
	}
	
	/**
	 * 返回 xgsj
	 * @return
	 */
	public java.util.Date getXgsj() {
		return this.xgsj;
	}
	
	public void setXgyh(String xgyh) {
		this.xgyh = xgyh;
	}
	
	/**
	 * 返回 xgyh
	 * @return
	 */
	public String getXgyh() {
		return this.xgyh;
	}
	
	public void setScbj(Integer scbj) {
		this.scbj = scbj;
	}
	
	/**
	 * 返回 scbj
	 * @return
	 */
	public Integer getScbj() {
		return this.scbj;
	}
	
	public void setTjbj(Integer tjbj) {
		this.tjbj = tjbj;
	}
	
	/**
	 * 返回 tjbj
	 * @return
	 */
	public Integer getTjbj() {
		return this.tjbj;
	}
	
	public void setQcsms(String qcsms) {
		this.qcsms = qcsms;
	}
	
	/**
	 * 返回 qcsms
	 * @return
	 */
	public String getQcsms() {
		return this.qcsms;
	}
	
	public void setZbzt(String zbzt) {
		this.zbzt = zbzt;
	}
	
	/**
	 * 返回 zbzt
	 * @return
	 */
	public String getZbzt() {
		return this.zbzt;
	}
	
	public void setWgwdxjss(String wgwdxjss) {
		this.wgwdxjss = wgwdxjss;
	}
	
	/**
	 * 返回 wgwdxjss
	 * @return
	 */
	public String getWgwdxjss() {
		return this.wgwdxjss;
	}
	
	public void setDxhpmt(String dxhpmt) {
		this.dxhpmt = dxhpmt;
	}
	
	/**
	 * 返回 dxhpmt
	 * @return
	 */
	public String getDxhpmt() {
		return this.dxhpmt;
	}
	
	public void setJcbzt(String jcbzt) {
		this.jcbzt = jcbzt;
	}
	
	/**
	 * 返回 jcbzt
	 * @return
	 */
	public String getJcbzt() {
		return this.jcbzt;
	}
	
	public void setDqsbbzt(String dqsbbzt) {
		this.dqsbbzt = dqsbbzt;
	}
	
	/**
	 * 返回 dqsbbzt
	 * @return
	 */
	public String getDqsbbzt() {
		return this.dqsbbzt;
	}
	
	public void setYlsbbzt(String ylsbbzt) {
		this.ylsbbzt = ylsbbzt;
	}
	
	/**
	 * 返回 ylsbbzt
	 * @return
	 */
	public String getYlsbbzt() {
		return this.ylsbbzt;
	}
	
	public void setYcsczp(String ycsczp) {
		this.ycsczp = ycsczp;
	}
	
	/**
	 * 返回 ycsczp
	 * @return
	 */
	public String getYcsczp() {
		return this.ycsczp;
	}
	
	public void setYcshbg(String ycshbg) {
		this.ycshbg = ycshbg;
	}
	
	/**
	 * 返回 ycshbg
	 * @return
	 */
	public String getYcshbg() {
		return this.ycshbg;
	}
	
	public void setLxjjss(String lxjjss) {
		this.lxjjss = lxjjss;
	}
	
	/**
	 * 返回 lxjjss
	 * @return
	 */
	public String getLxjjss() {
		return this.lxjjss;
	}
	
	public void setGdyzbzt(String gdyzbzt) {
		this.gdyzbzt = gdyzbzt;
	}
	
	/**
	 * 返回 gdyzbzt
	 * @return
	 */
	public String getGdyzbzt() {
		return this.gdyzbzt;
	}
	
	public void setXztlsybg(String xztlsybg) {
		this.xztlsybg = xztlsybg;
	}
	
	/**
	 * 返回 xztlsybg
	 * @return
	 */
	public String getXztlsybg() {
		return this.xztlsybg;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 返回 修改时间
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
	
	public void setUpdateDept(String updateDept) {
		this.updateDept = updateDept;
	}
	
	/**
	 * 返回 update_dept
	 * @return
	 */
	public String getUpdateDept() {
		return this.updateDept;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("bzcxId", this.bzcxId) 
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
		.append("ssdq", this.ssdq) 
		.append("ssdqId", this.ssdqId) 
		.append("bqdw", this.bqdw) 
		.append("cxpjzdf", this.cxpjzdf) 
		.append("sjpjdf", this.sjpjdf) 
		.append("ejpjdf", this.ejpjdf) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("insertLogin", this.insertLogin) 
		.append("insertDept", this.insertDept) 
		.append("xgsj", this.xgsj) 
		.append("xgyh", this.xgyh) 
		.append("scbj", this.scbj) 
		.append("tjbj", this.tjbj) 
		.append("qcsms", this.qcsms) 
		.append("zbzt", this.zbzt) 
		.append("wgwdxjss", this.wgwdxjss) 
		.append("dxhpmt", this.dxhpmt) 
		.append("jcbzt", this.jcbzt) 
		.append("dqsbbzt", this.dqsbbzt) 
		.append("ylsbbzt", this.ylsbbzt) 
		.append("ycsczp", this.ycsczp) 
		.append("ycshbg", this.ycshbg) 
		.append("lxjjss", this.lxjjss) 
		.append("gdyzbzt", this.gdyzbzt) 
		.append("xztlsybg", this.xztlsybg) 
		.append("updateTime", this.updateTime) 
		.append("updateUser", this.updateUser) 
		.append("updateDept", this.updateDept) 
		.toString();
	}
}