
package com.sky.tzsc.yytzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.yytzsc.persistence.model.Clshyj;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查材料审核意见]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:34:07
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface YyClshyjManager extends Manager<String, Clshyj> {
	/**
	 * 根据主键得到[远洋图纸审查材料审核意见]信息
	 * @param id
	 * @return Clshyj
	 * @author administrator 
	 * @Create 2017-12-07 10:34:07
	 */
	public Clshyj getClshyj(String id);
	
	/**
	 * 保存[远洋图纸审查材料审核意见]对象
	 * @param clshyj
	 * @return Clshyj
	 * @author administrator 
	 * @Create 2017-12-07 10:34:07
	 */
	public Clshyj saveClshyj(Clshyj clshyj);
	
	/**
	 * 执行删除[远洋图纸审查材料审核意见]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Clshyj
	 * 			<li>如果是删除一条[远洋图纸审查材料审核意见]数据，会返回删除的[远洋图纸审查材料审核意见]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-07 10:34:07
	 */
	public Clshyj doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Clshyj>
	 * @author administrator 
	 * @Create 2017-12-07 10:34:07
	 */
	public PageList<Clshyj>  clshyjListJson(Map<String ,Object> paramMap);
}
