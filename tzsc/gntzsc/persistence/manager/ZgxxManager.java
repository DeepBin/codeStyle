
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Zgxx;


import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[国内图纸审查整改信息]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-08 10:10:23
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface ZgxxManager extends Manager<String, Zgxx> {
	/**
	 * 根据主键得到[国内图纸审查整改信息]信息
	 * @param id
	 * @return zgxx
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	public Zgxx getzgxx(String id);
	
	/**
	 * 保存[国内图纸审查整改信息]对象
	 * @param zgxx
	 * @return zgxx
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	public Zgxx savezgxx(Zgxx zgxx);
	
	/**
	 * 执行删除[国内图纸审查整改信息]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return zgxx
	 * 			<li>如果是删除一条[国内图纸审查整改信息]数据，会返回删除的[国内图纸审查整改信息]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	public Zgxx doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<zgxx>
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	public PageList<Zgxx>  zgxxListJson(Map<String ,Object> paramMap);
}
