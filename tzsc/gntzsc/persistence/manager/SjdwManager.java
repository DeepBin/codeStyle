
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Sjdw;


import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[图纸审查设计单位信息表]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-04-08 14:32:58
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface SjdwManager extends Manager<String, Sjdw> {
	/**
	 * 根据主键得到[图纸审查设计单位信息表]信息
	 * @param id
	 * @return Sjdw
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	public Sjdw getSjdw(String id);
	
	/**
	 * 保存[图纸审查设计单位信息表]对象
	 * @param sjdw
	 * @return Sjdw
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	public Sjdw saveSjdw(Sjdw sjdw);
	
	/**
	 * 执行删除[图纸审查设计单位信息表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Sjdw
	 * 			<li>如果是删除一条[图纸审查设计单位信息表]数据，会返回删除的[图纸审查设计单位信息表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	public Sjdw doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Sjdw>
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	public PageList<Sjdw>  sjdwListJson(Map<String ,Object> paramMap);
}
