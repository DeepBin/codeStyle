package com.sky.tzsc.gntzsc.persistence.manager.impl;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.mybatis.domain.PageList;

import com.sky.api.sys.log.constant.Globals;
import com.sky.api.sys.log.service.ISysLogService;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.dao.FlDao;
import com.sky.tzsc.gntzsc.persistence.manager.FlManager;
import com.sky.tzsc.gntzsc.persistence.model.Fl;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fl]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-26 16:08:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("flManager")
public class FlManagerImpl extends AbstractManagerImpl<String, Fl> implements FlManager{
	@Resource
	FlDao flDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Fl> getDao() {
		return flDao;
	}
	/**
	 * 根据主键得到[tzsc_fl]信息
	 * @param id
	 * @return Fl
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@Override
	public Fl getFl(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Fl fl = this.get(id);
		return fl;
	}
	
	/**
	 * 保存[tzsc_fl]对象
	 * @param fl
	 * @return Fl
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@Override
	public Fl saveFl(Fl fl) {
		//fl.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getFl(fl.getId()))) {
			this.create(fl);
			sysLogService.addSysLog("新增tzsc_fl成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,fl.getId());
		} else {
			this.update(fl);
			sysLogService.addSysLog("更新tzsc_fl成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,fl.getId());
		}
		return fl;
	}
	
	/**
	 * 执行删除[tzsc_fl]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Fl
	 * 			<li>如果是删除一条[tzsc_fl]数据，会返回删除的[tzsc_fl]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@Override
	public Fl doRemove(String[] aryIds) {
		for(String id:aryIds){
			Fl fl = this.getFl(id);
			//fl.setIsDeleted(ConstSystem.YES);
			update(fl);
			sysLogService.addSysLog("删除tzsc_fl成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,fl.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getFl(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Fl>
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	public PageList<Fl>  flListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Fl> flList = (PageList<Fl>) this.query(queryFilter);// 执行查询
		return flList;
	}
}
