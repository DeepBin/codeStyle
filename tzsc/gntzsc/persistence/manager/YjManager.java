
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Yj;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[tzsc_yj]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-29 13:41:43
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface YjManager extends Manager<String, Yj> {
	/**
	 * 根据主键得到[tzsc_yj]信息
	 * @param id
	 * @return Yj
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	public Yj getYj(String id);
	
	/**
	 * 保存[tzsc_yj]对象
	 * @param yj
	 * @return Yj
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	public Yj saveYj(Yj yj);
	
	/**
	 * 执行删除[tzsc_yj]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Yj
	 * 			<li>如果是删除一条[tzsc_yj]数据，会返回删除的[tzsc_yj]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	public Yj doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Yj>
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	public PageList<Yj>  yjListJson(Map<String ,Object> paramMap);
}
