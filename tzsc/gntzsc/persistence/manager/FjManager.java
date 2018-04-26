
package com.sky.tzsc.gntzsc.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.gntzsc.persistence.model.Fj;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fj]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-28 16:18:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface FjManager extends Manager<String, Fj> {
	/**
	 * 根据主键得到[tzsc_fj]信息
	 * @param id
	 * @return Fj
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	public Fj getFj(String id);
	
	/**
	 * 保存[tzsc_fj]对象
	 * @param fj
	 * @return Fj
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	public Fj saveFj(Fj fj);
	
	/**
	 * 执行删除[tzsc_fj]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Fj
	 * 			<li>如果是删除一条[tzsc_fj]数据，会返回删除的[tzsc_fj]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	public Fj doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Fj>
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	public PageList<Fj>  fjListJson(Map<String ,Object> paramMap);

	/**
	 * 根据图纸审查 申请 保存 附件表    
	 * @param tzsq void
	 * @author wjk 
	 * @param queryFilter 
	 * @param queryFilter 
	 * @Create Date 2017 下午3:08:28
	 */
	public void saveFjByTzsq(Tzsq tzsq, QueryFilter queryFilter);

	/**
	 * 封装附件 id 到申请表中    
	 * @param tzsq void
	 * @author wjk 
	 * @Create Date 2017 下午6:46:15
	 */
	public void dealFjBySqId(Tzsq tzsq,QueryFilter queryFilter);

	/**
	 * 根据申请表key 得到 图纸附件    
	 * @param flowKey
	 * @return PageList<Tzsq>
	 * @author wjk 
	 * @Create Date 2018 下午2:15:47
	 */
	public List<Fj> getFjListByFlowKey(String flowKey);
}
