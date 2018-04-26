package com.sky.tzsc.gntzsc.persistence.manager.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.mybatis.domain.PageList;

import com.sky.api.sys.log.constant.Globals;
import com.sky.api.sys.log.service.ISysLogService;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.sky.common.constant.ConstSystem;
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.dao.YjDao;
import com.sky.tzsc.gntzsc.persistence.manager.YjManager;
import com.sky.tzsc.gntzsc.persistence.model.Yj;

/**
 * 
 * <pre> 
 * 描述：[tzsc_yj]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-29 13:41:43
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("yjManager")
public class YjManagerImpl extends AbstractManagerImpl<String, Yj> implements YjManager{
	@Resource
	YjDao yjDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Yj> getDao() {
		return yjDao;
	}
	/**
	 * 根据主键得到[tzsc_yj]信息
	 * @param id
	 * @return Yj
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@Override
	public Yj getYj(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Yj yj = this.get(id);
		return yj;
	}
	
	/**
	 * 保存[tzsc_yj]对象
	 * @param yj
	 * @return Yj
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@Override
	public Yj saveYj(Yj yj) {
		//yj.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getYj(yj.getId()))) {
			this.create(yj);
			sysLogService.addSysLog("新增tzsc_yj成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,yj.getId());
		} else {
			this.update(yj);
			sysLogService.addSysLog("更新tzsc_yj成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,yj.getId());
		}
		return yj;
	}
	
	/**
	 * 执行删除[tzsc_yj]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Yj
	 * 			<li>如果是删除一条[tzsc_yj]数据，会返回删除的[tzsc_yj]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@Override
	public Yj doRemove(String[] aryIds) {
		for(String id:aryIds){
			Yj yj = this.getYj(id);
			//yj.setIsDeleted(ConstSystem.YES);
			update(yj);
			sysLogService.addSysLog("删除tzsc_yj成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,yj.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getYj(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Yj>
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	public PageList<Yj>  yjListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Yj> yjList = (PageList<Yj>) this.query(queryFilter);// 执行查询
		return yjList;
	}
}
