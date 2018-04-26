
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Cwgjzbpzs;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[tzsc_cwgjzbpzs]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 14:39:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface CwgjzbpzsManager extends Manager<String, Cwgjzbpzs> {
	/**
	 * 根据主键得到[tzsc_cwgjzbpzs]信息
	 * @param id
	 * @return Cwgjzbpzs
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	public Cwgjzbpzs getCwgjzbpzs(String id);
	
	/**
	 * 保存[tzsc_cwgjzbpzs]对象
	 * @param cwgjzbpzs
	 * @return Cwgjzbpzs
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	public Cwgjzbpzs saveCwgjzbpzs(Cwgjzbpzs cwgjzbpzs);
	
	/**
	 * 执行删除[tzsc_cwgjzbpzs]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Cwgjzbpzs
	 * 			<li>如果是删除一条[tzsc_cwgjzbpzs]数据，会返回删除的[tzsc_cwgjzbpzs]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	public Cwgjzbpzs doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Cwgjzbpzs>
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	public PageList<Cwgjzbpzs>  cwgjzbpzsListJson(Map<String ,Object> paramMap);
}
