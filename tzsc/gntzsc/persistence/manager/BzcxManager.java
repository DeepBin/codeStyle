
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Bzcx;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-20 15:37:20
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface BzcxManager extends Manager<String, Bzcx> {
	/**
	 * 根据主键得到[tzsc_bzcx]信息
	 * @param id
	 * @return Bzcx
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	public Bzcx getBzcx(String id);
	
	/**
	 * 保存[tzsc_bzcx]对象
	 * @param bzcx
	 * @return Bzcx
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	public Bzcx saveBzcx(Bzcx bzcx);
	
	/**
	 * 执行删除[tzsc_bzcx]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Bzcx
	 * 			<li>如果是删除一条[tzsc_bzcx]数据，会返回删除的[tzsc_bzcx]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	public Bzcx doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Bzcx>
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	public PageList<Bzcx>  bzcxListJson(Map<String ,Object> paramMap);
}
