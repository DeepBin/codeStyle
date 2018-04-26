
package com.sky.tzsc.yytzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;
import com.sky.tzsc.yytzsc.persistence.model.Yyfj;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查附件表]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 14:47:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface YyfjManager extends Manager<String, Yyfj> {
	/**
	 * 根据主键得到[远洋图纸审查附件表]信息
	 * @param id
	 * @return Yyfj
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	public Yyfj getYyfj(String id);
	
	/**
	 * 保存[远洋图纸审查附件表]对象
	 * @param yyfj
	 * @return Yyfj
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	public Yyfj saveYyfj(Yyfj yyfj);
	
	/**
	 * 执行删除[远洋图纸审查附件表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Yyfj
	 * 			<li>如果是删除一条[远洋图纸审查附件表]数据，会返回删除的[远洋图纸审查附件表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	public Yyfj doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Yyfj>
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	public PageList<Yyfj>  yyfjListJson(Map<String ,Object> paramMap);

	/**
	 * 保存 申请时填写的附件   
	 * @param tzscsq
	 * @param queryFilter void
	 * @author wjk 
	 * @Create Date 2017 下午2:54:00
	 */
	public void saveFjByTzsq(Tzscsq tzscsq, QueryFilter queryFilter);

	/**
	 * 将 附件表的 附件 信息 放到 申请 表进行展示    
	 * @param tzscsq
	 * @param queryFilter void
	 * @author wjk 
	 * @Create Date 2017 下午3:05:43
	 */
	public void dealFjBySqId(Tzscsq tzscsq, QueryFilter queryFilter);
}
