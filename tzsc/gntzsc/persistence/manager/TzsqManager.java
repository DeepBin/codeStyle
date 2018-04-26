
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[tzsc_sq]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-22 14:04:09
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface TzsqManager extends Manager<String, Tzsq> {
	/**
	 * 根据主键得到[tzsc_sq]信息
	 * @param id
	 * @return Tzsq
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	public Tzsq getTzsq(String id);
	
	/**
	 * 保存[tzsc_sq]对象
	 * @param tzsq
	 * @return Tzsq
	 * @author administrator 
	 * @param queryFilter 
	 * @Create 2017-11-22 14:04:09
	 */
	public Tzsq saveTzsq(Tzsq tzsq);
	
	/**
	 * 执行删除[tzsc_sq]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Tzsq
	 * 			<li>如果是删除一条[tzsc_sq]数据，会返回删除的[tzsc_sq]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	public Tzsq doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Tzsq>
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	public PageList<Tzsq>  tzsqListJson(Map<String ,Object> paramMap);

	public List getTzdzgList(Map<String, Object> changeRequestParamToMap);
}
