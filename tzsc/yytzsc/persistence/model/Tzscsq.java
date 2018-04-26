package com.sky.tzsc.yytzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.util.AppUtil;
import com.sky.common.model.AbstractModel;
import com.sky.common.util.StaticMethod;
import com.sky.sys.dept.persistence.manager.SysDeptManager;
import com.sky.sys.dept.persistence.model.SysDept;


/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查申请表]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:28:56
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Tzscsq extends AbstractModel<String>{
	
	
	private SysDeptManager sysDeptManager = (SysDeptManager) AppUtil.getBean("sysDeptManager");
	/**
	* id
	*/
	protected String id; 
	
	/**
	* 图纸批准号
	*/
	protected String sqTzpzh; 
	
	/**
	* 图号
	*/
	protected String th; 
	
	/**
	* 申请审查单位名称
	*/
	protected String sqscdwmc; 
	
	/**
	* 申请审查单位地址
	*/
	protected String sqscdwdz; 
	
	/**
	* 申请审查单位联系人
	*/
	protected String sqscdwlxr; 
	
	/**
	* 申请审查单位电话
	*/
	protected String sqscdwdh; 
	
	/**
	* 申请审查单位邮箱
	*/
	protected String sqscdwyx; 
	
	/**
	* 等效免除
	*/
	protected String dxmc; 
	
	/**
	* 分批送审
	*/
	protected String fpss; 
	
	/**
	* 本船设计满足下述规则、规范和特
	*/
	protected String mzyq; 
	
	/**
	* 其他规范、法规和主管部门的特殊
	*/
	protected String qtgfyq; 
	
	/**
	* 申请资料核查意见
	*/
	protected String sqzlhcyj; 
	
	/**
	* 申请资料核查人
	*/
	protected String sqzlhcr; 
	
	/**
	* 申请材料核查时间
	*/
	protected java.util.Date sqzlhcsj; 
	
	/**
	* 受理人
	*/
	protected String slr; 
	
	/**
	* 受理时间
	*/
	protected java.util.Date slsj; 
	
	/**
	* 轮机审图人员
	*/
	protected String ljstry; 
	
	/**
	* 电气审图人员
	*/
	protected String dqstry; 
	
	/**
	* 船体审图人员
	*/
	protected String ctstry; 
	
	/**
	* 技术评审完成时间
	*/
	protected java.util.Date jspswcsj; 
	
	/**
	* csyjwcsj
	*/
	protected java.util.Date csyjwcsj; 
	
	/**
	* 批准函有效期
	*/
	protected java.util.Date pzhyxq; 
	
	/**
	* 批准函编号
	*/
	protected String pzhbh; 
	
	/**
	* 批准函批准人
	*/
	protected String pzhpzr; 
	
	/**
	* 准造（艘）
	*/
	protected String zzsl; 
	
	/**
	* 制冷审图人员
	*/
	protected String zltry; 
	
	/**
	* 受理意见
	*/
	protected String slyj; 
	
	/**
	* 图纸名称
	*/
	protected String tzmc; 
	
	/**
	* 船舶总长
	*/
	protected Double cbzc; 
	
	/**
	* 垂线间长
	*/
	protected Double cxjc; 
	
	/**
	* 型宽
	*/
	protected Double xk; 
	
	/**
	* 型深
	*/
	protected Double xs; 
	
	/**
	* 吃水
	*/
	protected Double cs; 
	
	/**
	* 总吨位
	*/
	protected Double zdw; 
	
	/**
	* 登记船长
	*/
	protected Double djcc; 
	
	/**
	* 船舶种类
	*/
	protected String cbzl; 
	
	/**
	* 船型编号
	*/
	protected String cxbh; 
	
	/**
	* 送审单位
	*/
	protected String ssdw; 
	
	/**
	* 图纸审查单位
	*/
	protected String scdw; 
	
	/**
	* 航区(类)
	*/
	protected String hq; 
	
	/**
	* 拟建造艘数
	*/
	protected Integer njzss; 
	
	/**
	* 主机功率(kw)
	*/
	protected Double zjgl; 
	
	/**
	* 设计航速
	*/
	protected Integer sjhs; 
	
	/**
	* 设计排水量
	*/
	protected Integer sjpsl; 
	
	/**
	* 定员
	*/
	protected Integer dy; 
	
	/**
	* 船体材料
	*/
	protected String ctcl; 
	
	/**
	* 辅机总功率
	*/
	protected Double fjzgl; 
	
	/**
	* 发电机总功率
	*/
	protected Double fdjzgl; 
	
	/**
	* 冷藏舱容积
	*/
	protected String lccrj; 
	
	/**
	* 自动化功率
	*/
	protected Double zdhgl; 
	
	/**
	* 自动化机舱类别
	*/
	protected String zdhjclb; 
	
	/**
	* 有无CMDSS
	*/
	protected String ywcmdss; 
	
	/**
	* 可变螺距
	*/
	protected String kblj; 
	
	/**
	* 变速齿轮
	*/
	protected String bscl; 
	
	/**
	* 推进器数
	*/
	protected Integer tjqs; 
	
	/**
	* 船网工具指标批准书id
	*/
	protected String cwgjzbpzsId; 
	
	/**
	* 船网批准书编号
	*/
	protected String cwpzsbh; 
	
	/**
	* 提交状态
	*/
	protected Integer tjzt; 
	
	/**
	* 渔船图纸类型
	*/
	protected String yctzlx; 
	
	/**
	* 备注
	*/
	protected String bz; 
	
	/**
	* 是否结束整改
	*/
	protected String sfjszg; 
	
	/**
	* 设计单位地址
	*/
	protected String sjdwybjdz; 
	
	/**
	* 设计单位联系电话
	*/
	protected String sjdwlxdh; 
	
	/**
	* 设计单位
	*/
	protected String sjdw; 
	
	/**
	* 主要尺寸
	*/
	protected String zycc; 
	
	/**
	* 是否整改
	*/
	protected String sfzg; 
	
	/**
	* 插入时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 创建用户
	*/
	protected String insertUser; 
	
	/**
	* 创建用户登录账号
	*/
	protected String insertLogin; 
	
	/**
	* 创建部门
	*/
	protected String insertDept; 
	
	/**
	* 修改时间
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
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setSqTzpzh(String sqTzpzh) {
		this.sqTzpzh = sqTzpzh;
	}
	
	/**
	 * 返回 图纸批准号
	 * @return
	 */
	public String getSqTzpzh() {
		return this.sqTzpzh;
	}
	
	public void setTh(String th) {
		this.th = th;
	}
	
	/**
	 * 返回 图号
	 * @return
	 */
	public String getTh() {
		return this.th;
	}
	
	public void setSqscdwmc(String sqscdwmc) {
		this.sqscdwmc = sqscdwmc;
	}
	
	/**
	 * 返回 申请审查单位名称
	 * @return
	 */
	public String getSqscdwmc() {
		return this.sqscdwmc;
	}
	
	public void setSqscdwdz(String sqscdwdz) {
		this.sqscdwdz = sqscdwdz;
	}
	
	/**
	 * 返回 申请审查单位地址
	 * @return
	 */
	public String getSqscdwdz() {
		return this.sqscdwdz;
	}
	
	public void setSqscdwlxr(String sqscdwlxr) {
		this.sqscdwlxr = sqscdwlxr;
	}
	
	/**
	 * 返回 申请审查单位联系人
	 * @return
	 */
	public String getSqscdwlxr() {
		return this.sqscdwlxr;
	}
	
	public void setSqscdwdh(String sqscdwdh) {
		this.sqscdwdh = sqscdwdh;
	}
	
	/**
	 * 返回 申请审查单位电话
	 * @return
	 */
	public String getSqscdwdh() {
		return this.sqscdwdh;
	}
	
	public void setSqscdwyx(String sqscdwyx) {
		this.sqscdwyx = sqscdwyx;
	}
	
	/**
	 * 返回 申请审查单位邮箱
	 * @return
	 */
	public String getSqscdwyx() {
		return this.sqscdwyx;
	}
	
	public void setDxmc(String dxmc) {
		this.dxmc = dxmc;
	}
	
	/**
	 * 返回 等效免除
	 * @return
	 */
	public String getDxmc() {
		return this.dxmc;
	}
	
	public void setFpss(String fpss) {
		this.fpss = fpss;
	}
	
	/**
	 * 返回 分批送审
	 * @return
	 */
	public String getFpss() {
		return this.fpss;
	}
	
	public void setMzyq(String mzyq) {
		this.mzyq = mzyq;
	}
	
	/**
	 * 返回 本船设计满足下述规则、规范和特
	 * @return
	 */
	public String getMzyq() {
		return this.mzyq;
	}
	
	public void setQtgfyq(String qtgfyq) {
		this.qtgfyq = qtgfyq;
	}
	
	/**
	 * 返回 其他规范、法规和主管部门的特殊
	 * @return
	 */
	public String getQtgfyq() {
		return this.qtgfyq;
	}
	
	public void setSqzlhcyj(String sqzlhcyj) {
		this.sqzlhcyj = sqzlhcyj;
	}
	
	/**
	 * 返回 申请资料核查意见
	 * @return
	 */
	public String getSqzlhcyj() {
		return this.sqzlhcyj;
	}
	
	public void setSqzlhcr(String sqzlhcr) {
		this.sqzlhcr = sqzlhcr;
	}
	
	/**
	 * 返回 申请资料核查人
	 * @return
	 */
	public String getSqzlhcr() {
		return this.sqzlhcr;
	}
	
	public void setSqzlhcsj(java.util.Date sqzlhcsj) {
		this.sqzlhcsj = sqzlhcsj;
	}
	
	/**
	 * 返回 申请材料核查时间
	 * @return
	 */
	public java.util.Date getSqzlhcsj() {
		return this.sqzlhcsj;
	}
	
	public void setSlr(String slr) {
		this.slr = slr;
	}
	
	/**
	 * 返回 受理人
	 * @return
	 */
	public String getSlr() {
		return this.slr;
	}
	
	public void setSlsj(java.util.Date slsj) {
		this.slsj = slsj;
	}
	
	/**
	 * 返回 受理时间
	 * @return
	 */
	public java.util.Date getSlsj() {
		return this.slsj;
	}
	
	public void setLjstry(String ljstry) {
		this.ljstry = ljstry;
	}
	
	/**
	 * 返回 轮机审图人员
	 * @return
	 */
	public String getLjstry() {
		return this.ljstry;
	}
	
	public void setDqstry(String dqstry) {
		this.dqstry = dqstry;
	}
	
	/**
	 * 返回 电气审图人员
	 * @return
	 */
	public String getDqstry() {
		return this.dqstry;
	}
	
	public void setCtstry(String ctstry) {
		this.ctstry = ctstry;
	}
	
	/**
	 * 返回 船体审图人员
	 * @return
	 */
	public String getCtstry() {
		return this.ctstry;
	}
	
	public void setJspswcsj(java.util.Date jspswcsj) {
		this.jspswcsj = jspswcsj;
	}
	
	/**
	 * 返回 技术评审完成时间
	 * @return
	 */
	public java.util.Date getJspswcsj() {
		return this.jspswcsj;
	}
	
	public void setCsyjwcsj(java.util.Date csyjwcsj) {
		this.csyjwcsj = csyjwcsj;
	}
	
	/**
	 * 返回 csyjwcsj
	 * @return
	 */
	public java.util.Date getCsyjwcsj() {
		return this.csyjwcsj;
	}
	
	public void setPzhyxq(java.util.Date pzhyxq) {
		this.pzhyxq = pzhyxq;
	}
	
	/**
	 * 返回 批准函有效期
	 * @return
	 */
	public java.util.Date getPzhyxq() {
		return this.pzhyxq;
	}
	
	public void setPzhbh(String pzhbh) {
		this.pzhbh = pzhbh;
	}
	
	/**
	 * 返回 批准函编号
	 * @return
	 */
	public String getPzhbh() {
		return this.pzhbh;
	}
	
	public void setPzhpzr(String pzhpzr) {
		this.pzhpzr = pzhpzr;
	}
	
	/**
	 * 返回 批准函批准人
	 * @return
	 */
	public String getPzhpzr() {
		return this.pzhpzr;
	}
	
	public void setZzsl(String zzsl) {
		this.zzsl = zzsl;
	}
	
	/**
	 * 返回 准造（艘）
	 * @return
	 */
	public String getZzsl() {
		return this.zzsl;
	}
	
	public void setZltry(String zltry) {
		this.zltry = zltry;
	}
	
	/**
	 * 返回 制冷审图人员
	 * @return
	 */
	public String getZltry() {
		return this.zltry;
	}
	
	public void setSlyj(String slyj) {
		this.slyj = slyj;
	}
	
	/**
	 * 返回 受理意见
	 * @return
	 */
	public String getSlyj() {
		return this.slyj;
	}
	
	public void setTzmc(String tzmc) {
		this.tzmc = tzmc;
	}
	
	/**
	 * 返回 图纸名称
	 * @return
	 */
	public String getTzmc() {
		return this.tzmc;
	}
	
	public void setCbzc(Double cbzc) {
		this.cbzc = cbzc;
	}
	
	/**
	 * 返回 船舶总长
	 * @return
	 */
	public Double getCbzc() {
		return this.cbzc;
	}
	
	public void setCxjc(Double cxjc) {
		this.cxjc = cxjc;
	}
	
	/**
	 * 返回 垂线间长
	 * @return
	 */
	public Double getCxjc() {
		return this.cxjc;
	}
	
	public void setXk(Double xk) {
		this.xk = xk;
	}
	
	/**
	 * 返回 型宽
	 * @return
	 */
	public Double getXk() {
		return this.xk;
	}
	
	public void setXs(Double xs) {
		this.xs = xs;
	}
	
	/**
	 * 返回 型深
	 * @return
	 */
	public Double getXs() {
		return this.xs;
	}
	
	public void setCs(Double cs) {
		this.cs = cs;
	}
	
	/**
	 * 返回 吃水
	 * @return
	 */
	public Double getCs() {
		return this.cs;
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
	
	public void setDjcc(Double djcc) {
		this.djcc = djcc;
	}
	
	/**
	 * 返回 登记船长
	 * @return
	 */
	public Double getDjcc() {
		return this.djcc;
	}
	
	public void setCbzl(String cbzl) {
		this.cbzl = cbzl;
	}
	
	/**
	 * 返回 船舶种类
	 * @return
	 */
	public String getCbzl() {
		return this.cbzl;
	}
	
	public void setCxbh(String cxbh) {
		this.cxbh = cxbh;
	}
	
	/**
	 * 返回 船型编号
	 * @return
	 */
	public String getCxbh() {
		return this.cxbh;
	}
	
	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}
	
	/**
	 * 返回 送审单位
	 * @return
	 */
	public String getSsdw() {
		return this.ssdw;
	}
	
	public void setScdw(String scdw) {
		this.scdw = scdw;
	}
	
	/**
	 * 返回 图纸审查单位
	 * @return
	 */
	public String getScdw() {
		return this.scdw;
	}
	
	public void setHq(String hq) {
		this.hq = hq;
	}
	
	/**
	 * 返回 航区(类)
	 * @return
	 */
	public String getHq() {
		return this.hq;
	}
	
	public void setNjzss(Integer njzss) {
		this.njzss = njzss;
	}
	
	/**
	 * 返回 拟建造艘数
	 * @return
	 */
	public Integer getNjzss() {
		return this.njzss;
	}
	
	public void setZjgl(Double zjgl) {
		this.zjgl = zjgl;
	}
	
	/**
	 * 返回 主机功率(kw)
	 * @return
	 */
	public Double getZjgl() {
		return this.zjgl;
	}
	
	public void setSjhs(Integer sjhs) {
		this.sjhs = sjhs;
	}
	
	/**
	 * 返回 设计航速
	 * @return
	 */
	public Integer getSjhs() {
		return this.sjhs;
	}
	
	public void setSjpsl(Integer sjpsl) {
		this.sjpsl = sjpsl;
	}
	
	/**
	 * 返回 设计排水量
	 * @return
	 */
	public Integer getSjpsl() {
		return this.sjpsl;
	}
	
	public void setDy(Integer dy) {
		this.dy = dy;
	}
	
	/**
	 * 返回 定员
	 * @return
	 */
	public Integer getDy() {
		return this.dy;
	}
	
	public void setCtcl(String ctcl) {
		this.ctcl = ctcl;
	}
	
	/**
	 * 返回 船体材料
	 * @return
	 */
	public String getCtcl() {
		return this.ctcl;
	}
	
	public void setFjzgl(Double fjzgl) {
		this.fjzgl = fjzgl;
	}
	
	/**
	 * 返回 辅机总功率
	 * @return
	 */
	public Double getFjzgl() {
		return this.fjzgl;
	}
	
	public void setFdjzgl(Double fdjzgl) {
		this.fdjzgl = fdjzgl;
	}
	
	/**
	 * 返回 发电机总功率
	 * @return
	 */
	public Double getFdjzgl() {
		return this.fdjzgl;
	}
	
	public void setLccrj(String lccrj) {
		this.lccrj = lccrj;
	}
	
	/**
	 * 返回 冷藏舱容积
	 * @return
	 */
	public String getLccrj() {
		return this.lccrj;
	}
	
	public void setZdhgl(Double zdhgl) {
		this.zdhgl = zdhgl;
	}
	
	/**
	 * 返回 自动化功率
	 * @return
	 */
	public Double getZdhgl() {
		return this.zdhgl;
	}
	
	public void setZdhjclb(String zdhjclb) {
		this.zdhjclb = zdhjclb;
	}
	
	/**
	 * 返回 自动化机舱类别
	 * @return
	 */
	public String getZdhjclb() {
		return this.zdhjclb;
	}
	
	public void setYwcmdss(String ywcmdss) {
		this.ywcmdss = ywcmdss;
	}
	
	/**
	 * 返回 有无CMDSS
	 * @return
	 */
	public String getYwcmdss() {
		return this.ywcmdss;
	}
	
	public void setKblj(String kblj) {
		this.kblj = kblj;
	}
	
	/**
	 * 返回 可变螺距
	 * @return
	 */
	public String getKblj() {
		return this.kblj;
	}
	
	public void setBscl(String bscl) {
		this.bscl = bscl;
	}
	
	/**
	 * 返回 变速齿轮
	 * @return
	 */
	public String getBscl() {
		return this.bscl;
	}
	
	public void setTjqs(Integer tjqs) {
		this.tjqs = tjqs;
	}
	
	/**
	 * 返回 推进器数
	 * @return
	 */
	public Integer getTjqs() {
		return this.tjqs;
	}
	
	public void setCwgjzbpzsId(String cwgjzbpzsId) {
		this.cwgjzbpzsId = cwgjzbpzsId;
	}
	
	/**
	 * 返回 船网工具指标批准书id
	 * @return
	 */
	public String getCwgjzbpzsId() {
		return this.cwgjzbpzsId;
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
	
	public void setTjzt(Integer tjzt) {
		this.tjzt = tjzt;
	}
	
	/**
	 * 返回 提交状态
	 * @return
	 */
	public Integer getTjzt() {
		return this.tjzt;
	}
	
	public void setYctzlx(String yctzlx) {
		this.yctzlx = yctzlx;
	}
	
	/**
	 * 返回 渔船图纸类型
	 * @return
	 */
	public String getYctzlx() {
		return this.yctzlx;
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
	
	public void setSfjszg(String sfjszg) {
		this.sfjszg = sfjszg;
	}
	
	/**
	 * 返回 是否结束整改
	 * @return
	 */
	public String getSfjszg() {
		return this.sfjszg;
	}
	
	public void setSjdwybjdz(String sjdwybjdz) {
		this.sjdwybjdz = sjdwybjdz;
	}
	
	/**
	 * 返回 设计单位地址
	 * @return
	 */
	public String getSjdwybjdz() {
		return this.sjdwybjdz;
	}
	
	public void setSjdwlxdh(String sjdwlxdh) {
		this.sjdwlxdh = sjdwlxdh;
	}
	
	/**
	 * 返回 设计单位联系电话
	 * @return
	 */
	public String getSjdwlxdh() {
		return this.sjdwlxdh;
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
	
	public void setZycc(String zycc) {
		this.zycc = zycc;
	}
	
	/**
	 * 返回 主要尺寸
	 * @return
	 */
	public String getZycc() {
		return this.zycc;
	}
	
	public void setSfzg(String sfzg) {
		this.sfzg = sfzg;
	}
	
	/**
	 * 返回 是否整改
	 * @return
	 */
	public String getSfzg() {
		return this.sfzg;
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
	 * 返回 创建用户
	 * @return
	 */
	public String getInsertUser() {
		return this.insertUser;
	}
	
	public void setInsertLogin(String insertLogin) {
		this.insertLogin = insertLogin;
	}
	
	/**
	 * 返回 创建用户登录账号
	 * @return
	 */
	public String getInsertLogin() {
		return this.insertLogin;
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
		.append("sqTzpzh", this.sqTzpzh) 
		.append("th", this.th) 
		.append("sqscdwmc", this.sqscdwmc) 
		.append("sqscdwdz", this.sqscdwdz) 
		.append("sqscdwlxr", this.sqscdwlxr) 
		.append("sqscdwdh", this.sqscdwdh) 
		.append("sqscdwyx", this.sqscdwyx) 
		.append("dxmc", this.dxmc) 
		.append("fpss", this.fpss) 
		.append("mzyq", this.mzyq) 
		.append("qtgfyq", this.qtgfyq) 
		.append("sqzlhcyj", this.sqzlhcyj) 
		.append("sqzlhcr", this.sqzlhcr) 
		.append("sqzlhcsj", this.sqzlhcsj) 
		.append("slr", this.slr) 
		.append("slsj", this.slsj) 
		.append("ljstry", this.ljstry) 
		.append("dqstry", this.dqstry) 
		.append("ctstry", this.ctstry) 
		.append("jspswcsj", this.jspswcsj) 
		.append("csyjwcsj", this.csyjwcsj) 
		.append("pzhyxq", this.pzhyxq) 
		.append("pzhbh", this.pzhbh) 
		.append("pzhpzr", this.pzhpzr) 
		.append("zzsl", this.zzsl) 
		.append("zltry", this.zltry) 
		.append("slyj", this.slyj) 
		.append("tzmc", this.tzmc) 
		.append("cbzc", this.cbzc) 
		.append("cxjc", this.cxjc) 
		.append("xk", this.xk) 
		.append("xs", this.xs) 
		.append("cs", this.cs) 
		.append("zdw", this.zdw) 
		.append("djcc", this.djcc) 
		.append("cbzl", this.cbzl) 
		.append("cxbh", this.cxbh) 
		.append("ssdw", this.ssdw) 
		.append("scdw", this.scdw) 
		.append("hq", this.hq) 
		.append("njzss", this.njzss) 
		.append("zjgl", this.zjgl) 
		.append("sjhs", this.sjhs) 
		.append("sjpsl", this.sjpsl) 
		.append("dy", this.dy) 
		.append("ctcl", this.ctcl) 
		.append("fjzgl", this.fjzgl) 
		.append("fdjzgl", this.fdjzgl) 
		.append("lccrj", this.lccrj) 
		.append("zdhgl", this.zdhgl) 
		.append("zdhjclb", this.zdhjclb) 
		.append("ywcmdss", this.ywcmdss) 
		.append("kblj", this.kblj) 
		.append("bscl", this.bscl) 
		.append("tjqs", this.tjqs) 
		.append("cwgjzbpzsId", this.cwgjzbpzsId) 
		.append("cwpzsbh", this.cwpzsbh) 
		.append("tjzt", this.tjzt) 
		.append("yctzlx", this.yctzlx) 
		.append("bz", this.bz) 
		.append("sfjszg", this.sfjszg) 
		.append("sjdwybjdz", this.sjdwybjdz) 
		.append("sjdwlxdh", this.sjdwlxdh) 
		.append("sjdw", this.sjdw) 
		.append("zycc", this.zycc) 
		.append("sfzg", this.sfzg) 
		.append("insertTime", this.insertTime) 
		.append("insertUser", this.insertUser) 
		.append("insertLogin", this.insertLogin) 
		.append("insertDept", this.insertDept) 
		.append("updateTime", this.updateTime) 
		.append("updateUser", this.updateUser) 
		.append("scbj", this.scbj) 
		.toString();
	}
	
	private String scdwName = ""; 

	public String getScdwName() {
		if(StaticMethod.isNotEmpty(scdwName)) {
			return scdwName;
		}
		SysDept sysDept = sysDeptManager.getSysDeptByDeptId(String.valueOf(this.getScdw()));
		if(StaticMethod.isNotEmpty(sysDept)) {
			scdwName = sysDept.getDeptName();
		}
		return scdwName;
	}
}