package com.sky.tzsc.tzscStzjgl.persistence.model;
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
 * 描述：[审图专家管理]实体类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-16 17:07:42
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public class TzscStzjgl extends AbstractModel<String>{
	SysDeptManager sysDeptManager = (SysDeptManager) AppUtil.getBean("sysDeptManager");
	/**
	* id
	*/
	protected String id; 
	
	/**
	* 插入时间
	*/
	protected java.util.Date insertTime; 
	
	/**
	* 创建用户
	*/
	protected String insertUserId; 
	
	/**
	* 创建用户loginid
	*/
	protected String insertLoginId; 
	
	/**
	* 创建部门
	*/
	protected String insertDeptId; 
	
	/**
	* 专家名称
	*/
	protected String zjName; 
	
	/**
	* 性别
	*/
	protected String zjSex; 
	
	/**
	* 所属单位
	*/
	protected String zjSsdw; 
	
	/**
	* 联系电话
	*/
	protected String zjLxdh; 
	
	/**
	* 专家资质
	*/
	protected String zjZjzz; 
	
	
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
	
	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}
	
	/**
	 * 返回 创建用户
	 * @return
	 */
	public String getInsertUserId() {
		return this.insertUserId;
	}
	
	public void setInsertLoginId(String insertLoginId) {
		this.insertLoginId = insertLoginId;
	}
	
	/**
	 * 返回 创建用户loginid
	 * @return
	 */
	public String getInsertLoginId() {
		return this.insertLoginId;
	}
	
	public void setInsertDeptId(String insertDeptId) {
		this.insertDeptId = insertDeptId;
	}
	
	/**
	 * 返回 创建部门
	 * @return
	 */
	public String getInsertDeptId() {
		return this.insertDeptId;
	}
	
	public void setZjName(String zjName) {
		this.zjName = zjName;
	}
	
	/**
	 * 返回 专家名称
	 * @return
	 */
	public String getZjName() {
		return this.zjName;
	}
	
	public void setZjSex(String zjSex) {
		this.zjSex = zjSex;
	}
	
	/**
	 * 返回 性别
	 * @return
	 */
	public String getZjSex() {
		return this.zjSex;
	}
	
	
	/**
	 * 返回 所属单位
	 * @return
	 */
	public String getZjSsdw() {
		return zjSsdw;
	}

	public void setZjSsdw(String zjSsdw) {
		this.zjSsdw = zjSsdw;
	}

	public void setZjLxdh(String zjLxdh) {
		this.zjLxdh = zjLxdh;
	}
	
	/**
	 * 返回 联系电话
	 * @return
	 */
	public String getZjLxdh() {
		return this.zjLxdh;
	}
	
	public void setZjZjzz(String zjZjzz) {
		this.zjZjzz = zjZjzz;
	}
	
	/**
	 * 返回 专家资质
	 * @return
	 */
	public String getZjZjzz() {
		return this.zjZjzz;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("insertTime", this.insertTime) 
		.append("insertUserId", this.insertUserId) 
		.append("insertLoginId", this.insertLoginId) 
		.append("insertDeptId", this.insertDeptId) 
		.append("zjName", this.zjName) 
		.append("zjSex", this.zjSex) 
		.append("zjDeptId", this.zjSsdw) 
		.append("zjLxdh", this.zjLxdh) 
		.append("zjZjzz", this.zjZjzz) 
		.toString();
	}
	private String deptName;

	public String getDeptName() {
		SysDept sysDept = sysDeptManager.getSysDeptByDeptId(String.valueOf(this.getZjSsdw()));
		if(StaticMethod.isNotEmpty(sysDept)) {
			return sysDept.getDeptName();
		}
		return "";
	}
}