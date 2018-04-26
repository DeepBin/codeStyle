
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[tzsc_wlwj]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 16:34:38
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface WlwjManager extends Manager<String, Wlwj> {
	/**
	 * 根据主键得到[tzsc_wlwj]信息
	 * @param id
	 * @return Wlwj
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	public Wlwj getWlwj(String id);
	
	/**
	 * 保存[tzsc_wlwj]对象
	 * @param wlwj
	 * @return Wlwj
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	public Wlwj saveWlwj(Wlwj wlwj);
	
	/**
	 * 执行删除[tzsc_wlwj]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Wlwj
	 * 			<li>如果是删除一条[tzsc_wlwj]数据，会返回删除的[tzsc_wlwj]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	public Wlwj doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Wlwj>
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	public PageList<Wlwj>  wlwjListJson(Map<String ,Object> paramMap);

	/**
	 * 得到往来文件    
	 * @param queryFilter
	 * @return wlwj
	 * @author wjk 
	 * @Create Date 2017 上午9:39:37
	 */
	public Wlwj dealWlwjBySqId(QueryFilter queryFilter);

	public void saveWlwjByTzsq(Wlwj wlwj, QueryFilter queryFilter);
}
