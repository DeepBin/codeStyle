package com.sky.tzsc.gntzsc.persistence.model;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.util.AppUtil;
import com.sky.common.model.AbstractModel;
import com.sky.common.util.StaticMethod;
import com.sky.sys.dict.persistence.manager.SysDictManager;
import com.sky.sys.dict.persistence.model.SysDict;
import com.sky.tzsc.gntzsc.util.DictConfigUtil;


/**
 * 
 * <pre> 
 * 描述：[图纸审查设计单位信息表]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-04-08 14:32:58
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class Sjdw extends AbstractModel<String>{
	
	private SysDictManager sysDictManager = (SysDictManager) AppUtil.getBean("sysDictManager");
	/**
	* 图纸审查设计单位主键
	*/
	protected String id; 
	
	/**
	* 注册资金（万元）
	*/
	protected Integer zczj; 
	
	/**
	* 质量保证
	*/
	protected String zlbz; 
	
	/**
	* 技术装备
	*/
	protected String jszb; 
	
	/**
	* 技术总负责人姓名
	*/
	protected String jszfzrxm; 
	
	/**
	* 技术总负责人专业
	*/
	protected String jszfzrzy; 
	
	/**
	* 技术总负责人职称
	*/
	protected String jszfzrzc; 
	
	/**
	* 技术总负责人船舶设计经历
	*/
	protected String jszfzrcbsjjl; 
	
	/**
	* 质量保证材料
	*/
	protected String zlbzcl; 
	
	/**
	* 技术装备证明材料
	*/
	protected String jszbcl; 
	
	/**
	* 质量总负责人姓名
	*/
	protected String zlzfzrxm; 
	
	/**
	* 质量总负责人职称
	*/
	protected String zlzfzrzc; 
	
	/**
	* 质量负责人管理经验
	*/
	protected Integer zlfzrgljy; 
	
	/**
	* 专业技术人员人数
	*/
	protected Integer zyjsryrs; 
	
	/**
	* zyjsryzy
	*/
	protected String zyjsryzy; 
	
	/**
	* 专业技术人员职称
	*/
	protected String zyjsryzc; 
	
	/**
	* 设计单位名称
	*/
	protected String sjdwmc; 
	
	/**
	* 设计单位地址
	*/
	protected String sjdwdz; 
	
	/**
	* 设计单位联系电话
	*/
	protected String sjdwlxdh; 
	
	/**
	* 新增用户
	*/
	protected String insertUser; 
	
	/**
	* 新增时间
	*/
	protected java.util.Date insertTime; 
	
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
	 * 返回 图纸审查设计单位主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setZczj(Integer zczj) {
		this.zczj = zczj;
	}
	
	/**
	 * 返回 注册资金（万元）
	 * @return
	 */
	public Integer getZczj() {
		return this.zczj;
	}
	
	public void setZlbz(String zlbz) {
		this.zlbz = zlbz;
	}
	
	/**
	 * 返回 质量保证
	 * @return
	 */
	public String getZlbz() {
		return this.zlbz;
	}
	
	public void setJszb(String jszb) {
		this.jszb = jszb;
	}
	
	/**
	 * 返回 技术装备
	 * @return
	 */
	public String getJszb() {
		return this.jszb;
	}
	
	public void setJszfzrxm(String jszfzrxm) {
		this.jszfzrxm = jszfzrxm;
	}
	
	/**
	 * 返回 技术总负责人姓名
	 * @return
	 */
	public String getJszfzrxm() {
		return this.jszfzrxm;
	}
	
	public void setJszfzrzy(String jszfzrzy) {
		this.jszfzrzy = jszfzrzy;
	}
	
	/**
	 * 返回 技术总负责人专业
	 * @return
	 */
	public String getJszfzrzy() {
		return this.jszfzrzy;
	}
	
	public void setJszfzrzc(String jszfzrzc) {
		this.jszfzrzc = jszfzrzc;
	}
	
	/**
	 * 返回 技术总负责人职称
	 * @return
	 */
	public String getJszfzrzc() {
		return this.jszfzrzc;
	}
	
	public void setJszfzrcbsjjl(String jszfzrcbsjjl) {
		this.jszfzrcbsjjl = jszfzrcbsjjl;
	}
	
	/**
	 * 返回 技术总负责人船舶设计经历
	 * @return
	 */
	public String getJszfzrcbsjjl() {
		return this.jszfzrcbsjjl;
	}
	
	public void setZlbzcl(String zlbzcl) {
		this.zlbzcl = zlbzcl;
	}
	
	/**
	 * 返回 质量保证材料
	 * @return
	 */
	public String getZlbzcl() {
		return this.zlbzcl;
	}
	
	public void setJszbcl(String jszbcl) {
		this.jszbcl = jszbcl;
	}
	
	/**
	 * 返回 技术装备证明材料
	 * @return
	 */
	public String getJszbcl() {
		return this.jszbcl;
	}
	
	public void setZlzfzrxm(String zlzfzrxm) {
		this.zlzfzrxm = zlzfzrxm;
	}
	
	/**
	 * 返回 质量总负责人姓名
	 * @return
	 */
	public String getZlzfzrxm() {
		return this.zlzfzrxm;
	}
	
	public void setZlzfzrzc(String zlzfzrzc) {
		this.zlzfzrzc = zlzfzrzc;
	}
	
	/**
	 * 返回 质量总负责人职称
	 * @return
	 */
	public String getZlzfzrzc() {
		return this.zlzfzrzc;
	}
	
	public void setZlfzrgljy(Integer zlfzrgljy) {
		this.zlfzrgljy = zlfzrgljy;
	}
	
	/**
	 * 返回 质量负责人管理经验
	 * @return
	 */
	public Integer getZlfzrgljy() {
		return this.zlfzrgljy;
	}
	
	public void setZyjsryrs(Integer zyjsryrs) {
		this.zyjsryrs = zyjsryrs;
	}
	
	/**
	 * 返回 专业技术人员人数
	 * @return
	 */
	public Integer getZyjsryrs() {
		return this.zyjsryrs;
	}
	
	public void setZyjsryzy(String zyjsryzy) {
		this.zyjsryzy = zyjsryzy;
	}
	
	/**
	 * 返回 zyjsryzy
	 * @return
	 */
	public String getZyjsryzy() {
		return this.zyjsryzy;
	}
	
	public void setZyjsryzc(String zyjsryzc) {
		this.zyjsryzc = zyjsryzc;
	}
	
	/**
	 * 返回 专业技术人员职称
	 * @return
	 */
	public String getZyjsryzc() {
		return this.zyjsryzc;
	}
	
	public void setSjdwmc(String sjdwmc) {
		this.sjdwmc = sjdwmc;
	}
	
	/**
	 * 返回 设计单位名称
	 * @return
	 */
	public String getSjdwmc() {
		return this.sjdwmc;
	}
	
	public void setSjdwdz(String sjdwdz) {
		this.sjdwdz = sjdwdz;
	}
	
	/**
	 * 返回 设计单位地址
	 * @return
	 */
	public String getSjdwdz() {
		return this.sjdwdz;
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
		.append("zczj", this.zczj) 
		.append("zlbz", this.zlbz) 
		.append("jszb", this.jszb) 
		.append("jszfzrxm", this.jszfzrxm) 
		.append("jszfzrzy", this.jszfzrzy) 
		.append("jszfzrzc", this.jszfzrzc) 
		.append("jszfzrcbsjjl", this.jszfzrcbsjjl) 
		.append("zlbzcl", this.zlbzcl) 
		.append("jszbcl", this.jszbcl) 
		.append("zlzfzrxm", this.zlzfzrxm) 
		.append("zlzfzrzc", this.zlzfzrzc) 
		.append("zlfzrgljy", this.zlfzrgljy) 
		.append("zyjsryrs", this.zyjsryrs) 
		.append("zyjsryzy", this.zyjsryzy) 
		.append("zyjsryzc", this.zyjsryzc) 
		.append("sjdwmc", this.sjdwmc) 
		.append("sjdwdz", this.sjdwdz) 
		.append("sjdwlxdh", this.sjdwlxdh) 
		.append("insertUser", this.insertUser) 
		.append("insertTime", this.insertTime) 
		.append("updateTime", this.updateTime) 
		.append("updateUser", this.updateUser) 
		.append("scbj", this.scbj) 
		.toString();
	}
	//得到 技术总负责人 专业的 名称
	protected  String jszfzrzyName;

	public String getJszfzrzyName() {
		if(StaticMethod.isNotEmpty(jszfzrzyName)) {
			return  jszfzrzyName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.ZY, jszfzrzy);
		if(StaticMethod.isNotEmpty(sysDict)) {
			jszfzrzyName = sysDict.getDictName();
		}
		return jszfzrzyName;
	}
	//得到 技术总负责人职称的名称
	protected String jszfzrzcName;

	public String getJszfzrzcName() {
		if(StaticMethod.isNotEmpty(jszfzrzcName)) {
			return  jszfzrzcName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.ZC, jszfzrzc);
		if(StaticMethod.isNotEmpty(sysDict)) {
			jszfzrzcName = sysDict.getDictName();
		}
		return jszfzrzcName;
	}
	//得到质量总负责人职称的名称
	protected  String zlzfzrzcName;

	public String getZlzfzrzcName() {
		if(StaticMethod.isNotEmpty(zlzfzrzcName)) {
			return  zlzfzrzcName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.ZC, zlzfzrzc);
		if(StaticMethod.isNotEmpty(sysDict)) {
			zlzfzrzcName = sysDict.getDictName();
		}
		return zlzfzrzcName;
	}
	//得到专业技术人员的 专业名称
	protected String zyjsryzyName;

	public String getZyjsryzyName() {
		if(StaticMethod.isNotEmpty(zyjsryzyName)) {
			return  zyjsryzyName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.ZY, zyjsryzy);
		if(StaticMethod.isNotEmpty(sysDict)) {
			zyjsryzyName = sysDict.getDictName();
		}
		return zyjsryzyName;
	}
	//得到 专业技术人员职称名称
	protected String zyjsryzcName;

	public String getZyjsryzcName() {
		if(StaticMethod.isNotEmpty(zyjsryzcName)) {
			return zyjsryzcName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.ZC, zyjsryzc);
		if(StaticMethod.isNotEmpty(sysDict)) {
			zyjsryzcName = sysDict.getDictName();
		}
		return zyjsryzcName;
	}
	//得到质量保证字典值
	protected String zlbzName;


	public String getZlbzName() {
		if(StaticMethod.isNotEmpty(zlbzName)) {
			return zlbzName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.ZLBZ, zlbz);
		if(StaticMethod.isNotEmpty(sysDict)) {
			zlbzName = sysDict.getDictName();
		}
		return zlbzName;
	}
	//得到技术装备 名称
	protected String jszbName;

	public String getJszbName() {
		if(StaticMethod.isNotEmpty(jszbName)) {
			return  jszbName;
		}
		SysDict sysDict = sysDictManager.getSysDictByDictCodeAndDictId(DictConfigUtil.JSZB, jszb);
		if(StaticMethod.isNotEmpty(sysDict)) {
			jszbName = sysDict.getDictName();
		}
		return jszbName;
	}
	
	
	
	
}