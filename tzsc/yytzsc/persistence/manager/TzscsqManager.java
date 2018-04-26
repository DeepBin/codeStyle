
package com.sky.tzsc.yytzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;


import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查申请表]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:28:56
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface TzscsqManager extends Manager<String, Tzscsq> {
	/**
	 * 根据主键得到[远洋图纸审查申请表]信息
	 * @param id
	 * @return Tzscsq
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	public Tzscsq getTzscsq(String id);
	
	/**
	 * 保存[远洋图纸审查申请表]对象
	 * @param tzscsq
	 * @return Tzscsq
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	public Tzscsq saveTzscsq(Tzscsq tzscsq);
	
	/**
	 * 执行删除[远洋图纸审查申请表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Tzscsq
	 * 			<li>如果是删除一条[远洋图纸审查申请表]数据，会返回删除的[远洋图纸审查申请表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	public Tzscsq doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Tzscsq>
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	public PageList<Tzscsq>  tzscsqListJson(Map<String ,Object> paramMap);
}
