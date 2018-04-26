
package com.sky.tzsc.yytzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.yytzsc.persistence.model.ZgxxYy;


import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查整改信息]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-11 09:38:35
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface ZgxxYyManager extends Manager<String, ZgxxYy> {
	/**
	 * 根据主键得到[远洋图纸审查整改信息]信息
	 * @param id
	 * @return ZgxxYy
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	public ZgxxYy getZgxxYy(String id);
	
	/**
	 * 保存[远洋图纸审查整改信息]对象
	 * @param zgxxYy
	 * @return ZgxxYy
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	public ZgxxYy saveZgxxYy(ZgxxYy zgxxYy);
	
	/**
	 * 执行删除[远洋图纸审查整改信息]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return ZgxxYy
	 * 			<li>如果是删除一条[远洋图纸审查整改信息]数据，会返回删除的[远洋图纸审查整改信息]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	public ZgxxYy doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<ZgxxYy>
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	public PageList<ZgxxYy>  zgxxYyListJson(Map<String ,Object> paramMap);
}
