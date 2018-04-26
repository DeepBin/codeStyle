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
import com.sky.tzsc.gntzsc.persistence.dao.ZgxxDao;
import com.sky.tzsc.gntzsc.persistence.manager.ZgxxManager;
import com.sky.tzsc.gntzsc.persistence.model.Zgxx;

/**
 * 
 * <pre> 
 * 描述：[国内图纸审查整改信息]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-08 10:10:23
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("zgxxManager")
public class ZgxxManagerImpl extends AbstractManagerImpl<String, Zgxx> implements ZgxxManager{
	@Resource
	ZgxxDao zgxxDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Zgxx> getDao() {
		return zgxxDao;
	}
	/**
	 * 根据主键得到[国内图纸审查整改信息]信息
	 * @param id
	 * @return zgxx
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@Override
	public Zgxx getzgxx(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Zgxx zgxx = this.get(id);
		return zgxx;
	}
	
	/**
	 * 保存[国内图纸审查整改信息]对象
	 * @param zgxx
	 * @return zgxx
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@Override
	public Zgxx savezgxx(Zgxx zgxx) {
		//zgxx.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getzgxx(zgxx.getId()))) {
			this.create(zgxx);
			sysLogService.addSysLog("新增国内图纸审查整改信息成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,zgxx.getId());
		} else {
			this.update(zgxx);
			sysLogService.addSysLog("更新国内图纸审查整改信息成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,zgxx.getId());
		}
		return zgxx;
	}
	
	/**
	 * 执行删除[国内图纸审查整改信息]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return zgxx
	 * 			<li>如果是删除一条[国内图纸审查整改信息]数据，会返回删除的[国内图纸审查整改信息]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@Override
	public Zgxx doRemove(String[] aryIds) {
		for(String id:aryIds){
			Zgxx zgxx = this.getzgxx(id);
			//zgxx.setIsDeleted(ConstSystem.YES);
			update(zgxx);
			sysLogService.addSysLog("删除国内图纸审查整改信息成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,zgxx.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getzgxx(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<zgxx>
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	public PageList<Zgxx>  zgxxListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Zgxx> zgxxList = (PageList<Zgxx>) this.query(queryFilter);// 执行查询
		return zgxxList;
	}
}
