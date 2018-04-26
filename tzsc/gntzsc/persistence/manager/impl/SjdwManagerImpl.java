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
import com.sky.tzsc.gntzsc.persistence.dao.SjdwDao;
import com.sky.tzsc.gntzsc.persistence.manager.SjdwManager;
import com.sky.tzsc.gntzsc.persistence.model.Sjdw;

/**
 * 
 * <pre> 
 * 描述：[图纸审查设计单位信息表]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-04-08 14:32:58
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("sjdwManager")
public class SjdwManagerImpl extends AbstractManagerImpl<String, Sjdw> implements SjdwManager{
	@Resource
	SjdwDao sjdwDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Sjdw> getDao() {
		return sjdwDao;
	}
	/**
	 * 根据主键得到[图纸审查设计单位信息表]信息
	 * @param id
	 * @return Sjdw
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@Override
	public Sjdw getSjdw(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Sjdw sjdw = this.get(id);
		return sjdw;
	}
	
	/**
	 * 保存[图纸审查设计单位信息表]对象
	 * @param sjdw
	 * @return Sjdw
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@Override
	public Sjdw saveSjdw(Sjdw sjdw) {
		//sjdw.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getSjdw(sjdw.getId()))) {
			this.create(sjdw);
			sysLogService.addSysLog("新增图纸审查设计单位信息表成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,sjdw.getId());
		} else {
			this.update(sjdw);
			sysLogService.addSysLog("更新图纸审查设计单位信息表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,sjdw.getId());
		}
		return sjdw;
	}
	
	/**
	 * 执行删除[图纸审查设计单位信息表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Sjdw
	 * 			<li>如果是删除一条[图纸审查设计单位信息表]数据，会返回删除的[图纸审查设计单位信息表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@Override
	public Sjdw doRemove(String[] aryIds) {
		for(String id:aryIds){
			Sjdw sjdw = this.getSjdw(id);
			//sjdw.setIsDeleted(ConstSystem.YES);
			update(sjdw);
			sysLogService.addSysLog("删除图纸审查设计单位信息表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,sjdw.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getSjdw(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Sjdw>
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	public PageList<Sjdw>  sjdwListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Sjdw> sjdwList = (PageList<Sjdw>) this.query(queryFilter);// 执行查询
		return sjdwList;
	}
}
