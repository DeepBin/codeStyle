package com.sky.tzsc.tzscStzjgl.persistence.manager.impl;

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
import com.sky.tzsc.tzscStzjgl.persistence.dao.TzscStzjglDao;
import com.sky.tzsc.tzscStzjgl.persistence.manager.TzscStzjglManager;
import com.sky.tzsc.tzscStzjgl.persistence.model.TzscStzjgl;

/**
 * 
 * <pre> 
 * 描述：[审图专家管理]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-16 17:07:42
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("tzscStzjglManager")
public class TzscStzjglManagerImpl extends AbstractManagerImpl<String, TzscStzjgl> implements TzscStzjglManager{
	@Resource
	TzscStzjglDao tzscStzjglDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, TzscStzjgl> getDao() {
		return tzscStzjglDao;
	}
	/**
	 * 根据主键得到[审图专家管理]信息
	 * @param id
	 * @return TzscStzjgl
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@Override
	public TzscStzjgl getTzscStzjgl(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		TzscStzjgl tzscStzjgl = this.get(id);
		return tzscStzjgl;
	}
	
	/**
	 * 保存[审图专家管理]对象
	 * @param tzscStzjgl
	 * @return TzscStzjgl
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@Override
	public TzscStzjgl saveTzscStzjgl(TzscStzjgl tzscStzjgl) {
		//tzscStzjgl.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getTzscStzjgl(tzscStzjgl.getId()))) {
			this.create(tzscStzjgl);
			sysLogService.addSysLog("新增审图专家管理成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,tzscStzjgl.getId());
		} else {
			this.update(tzscStzjgl);
			sysLogService.addSysLog("更新审图专家管理成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,tzscStzjgl.getId());
		}
		return tzscStzjgl;
	}
	
	/**
	 * 执行删除[审图专家管理]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return TzscStzjgl
	 * 			<li>如果是删除一条[审图专家管理]数据，会返回删除的[审图专家管理]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@Override
	public TzscStzjgl doRemove(String[] aryIds) {
		for(String id:aryIds){
			TzscStzjgl tzscStzjgl = this.getTzscStzjgl(id);
			//tzscStzjgl.setIsDeleted(ConstSystem.YES);
			update(tzscStzjgl);
			sysLogService.addSysLog("删除审图专家管理成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,tzscStzjgl.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getTzscStzjgl(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<TzscStzjgl>
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	public PageList<TzscStzjgl>  tzscStzjglListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<TzscStzjgl> tzscStzjglList = (PageList<TzscStzjgl>) this.query(queryFilter);// 执行查询
		return tzscStzjglList;
	}
}
