
package com.sky.tzsc.yytzsc.persistence.manager;

import java.util.Map;

import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.tzsc.yytzsc.persistence.model.YyWlwj;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查往来文件表]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-08 10:08:08
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface YyWlwjManager extends Manager<String, YyWlwj> {
	/**
	 * 根据主键得到[远洋图纸审查往来文件表]信息
	 * @param id
	 * @return YyWlwj
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	public YyWlwj getYyWlwj(String id);
	
	/**
	 * 保存[远洋图纸审查往来文件表]对象
	 * @param yyWlwj
	 * @return YyWlwj
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	public YyWlwj saveYyWlwj(YyWlwj yyWlwj);
	
	/**
	 * 执行删除[远洋图纸审查往来文件表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return YyWlwj
	 * 			<li>如果是删除一条[远洋图纸审查往来文件表]数据，会返回删除的[远洋图纸审查往来文件表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	public YyWlwj doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<YyWlwj>
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	public PageList<YyWlwj>  yyWlwjListJson(Map<String ,Object> paramMap);

	/**
	 * 保存往来文件  -- 上传图纸委托函   
	 * @param yyWlwj
	 * @param queryFilter void
	 * @author wjk 
	 * @Create Date 2017 下午4:28:00
	 */
	public void saveWlwjByTzsq(YyWlwj yyWlwj, QueryFilter queryFilter);

	/**
	 * 往来文件    
	 * @param queryFilter
	 * @return YyWlwj
	 * @author wjk 
	 * @Create Date 2017 下午5:02:25
	 */
	public YyWlwj dealWlwjBySqId(QueryFilter queryFilter);
}
