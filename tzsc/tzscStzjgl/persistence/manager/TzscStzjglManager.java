
package com.sky.tzsc.tzscStzjgl.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.tzscStzjgl.persistence.model.TzscStzjgl;


import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[审图专家管理]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-16 17:07:42
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface TzscStzjglManager extends Manager<String, TzscStzjgl> {
	/**
	 * 根据主键得到[审图专家管理]信息
	 * @param id
	 * @return TzscStzjgl
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	public TzscStzjgl getTzscStzjgl(String id);
	
	/**
	 * 保存[审图专家管理]对象
	 * @param tzscStzjgl
	 * @return TzscStzjgl
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	public TzscStzjgl saveTzscStzjgl(TzscStzjgl tzscStzjgl);
	
	/**
	 * 执行删除[审图专家管理]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return TzscStzjgl
	 * 			<li>如果是删除一条[审图专家管理]数据，会返回删除的[审图专家管理]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	public TzscStzjgl doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<TzscStzjgl>
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	public PageList<TzscStzjgl>  tzscStzjglListJson(Map<String ,Object> paramMap);
}
