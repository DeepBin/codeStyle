package com.sky.tzsc.yytzsc.persistence.manager.impl;

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
import com.sky.tzsc.yytzsc.persistence.dao.ZgxxYyDao;
import com.sky.tzsc.yytzsc.persistence.manager.ZgxxYyManager;
import com.sky.tzsc.yytzsc.persistence.model.ZgxxYy;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查整改信息]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-11 09:38:35
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("zgxxYyManager")
public class ZgxxYyManagerImpl extends AbstractManagerImpl<String, ZgxxYy> implements ZgxxYyManager{
	@Resource
	ZgxxYyDao zgxxYyDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, ZgxxYy> getDao() {
		return zgxxYyDao;
	}
	/**
	 * 根据主键得到[远洋图纸审查整改信息]信息
	 * @param id
	 * @return ZgxxYy
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@Override
	public ZgxxYy getZgxxYy(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		ZgxxYy zgxxYy = this.get(id);
		return zgxxYy;
	}
	
	/**
	 * 保存[远洋图纸审查整改信息]对象
	 * @param zgxxYy
	 * @return ZgxxYy
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@Override
	public ZgxxYy saveZgxxYy(ZgxxYy zgxxYy) {
		//zgxxYy.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getZgxxYy(zgxxYy.getId()))) {
			this.create(zgxxYy);
			sysLogService.addSysLog("新增远洋图纸审查整改信息成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,zgxxYy.getId());
		} else {
			this.update(zgxxYy);
			sysLogService.addSysLog("更新远洋图纸审查整改信息成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,zgxxYy.getId());
		}
		return zgxxYy;
	}
	
	/**
	 * 执行删除[远洋图纸审查整改信息]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return ZgxxYy
	 * 			<li>如果是删除一条[远洋图纸审查整改信息]数据，会返回删除的[远洋图纸审查整改信息]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@Override
	public ZgxxYy doRemove(String[] aryIds) {
		for(String id:aryIds){
			ZgxxYy zgxxYy = this.getZgxxYy(id);
			//zgxxYy.setIsDeleted(ConstSystem.YES);
			update(zgxxYy);
			sysLogService.addSysLog("删除远洋图纸审查整改信息成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,zgxxYy.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getZgxxYy(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<ZgxxYy>
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	public PageList<ZgxxYy>  zgxxYyListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<ZgxxYy> zgxxYyList = (PageList<ZgxxYy>) this.query(queryFilter);// 执行查询
		return zgxxYyList;
	}
}
