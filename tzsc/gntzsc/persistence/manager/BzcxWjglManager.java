
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.BzcxWjgl;


import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[标准船型文件管理]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-25 09:17:50
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface BzcxWjglManager extends Manager<String, BzcxWjgl> {
	/**
	 * 根据主键得到[标准船型文件管理]信息
	 * @param id
	 * @return BzcxWjgl
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	public BzcxWjgl getBzcxWjgl(String id);
	
	/**
	 * 保存[标准船型文件管理]对象
	 * @param bzcxWjgl
	 * @return BzcxWjgl
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	public BzcxWjgl saveBzcxWjgl(BzcxWjgl bzcxWjgl);
	
	/**
	 * 执行删除[标准船型文件管理]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return BzcxWjgl
	 * 			<li>如果是删除一条[标准船型文件管理]数据，会返回删除的[标准船型文件管理]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	public BzcxWjgl doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<BzcxWjgl>
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	public PageList<BzcxWjgl>  bzcxWjglListJson(Map<String ,Object> paramMap);
}
