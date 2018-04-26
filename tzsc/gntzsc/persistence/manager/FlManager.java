package com.sky.tzsc.gntzsc.persistence.manager;


import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Fl;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fl]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-26 15:57:33
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface FlManager extends Manager<String, Fl> {
	/**
	 * 根据主键得到[tzsc_fl]信息
	 * @param id
	 * @return TzscFl
	 * @author administrator 
	 * @Create 2018-03-26 15:57:33
	 */
	public Fl getFl(String id);
	
	/**
	 * 保存[tzsc_fl]对象
	 * @param tzscFl
	 * @return TzscFl
	 * @author administrator 
	 * @Create 2018-03-26 15:57:33
	 */
	public Fl saveFl(Fl tzscFl);
	
	/**
	 * 执行删除[tzsc_fl]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return TzscFl
	 * 			<li>如果是删除一条[tzsc_fl]数据，会返回删除的[tzsc_fl]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-03-26 15:57:33
	 */
	public Fl doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<TzscFl>
	 * @author administrator 
	 * @Create 2018-03-26 15:57:33
	 */
	public PageList<Fl>  flListJson(Map<String ,Object> paramMap);
}
