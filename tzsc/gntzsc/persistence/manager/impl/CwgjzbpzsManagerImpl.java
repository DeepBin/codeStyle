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
import com.sky.tzsc.gntzsc.persistence.dao.CwgjzbpzsDao;
import com.sky.tzsc.gntzsc.persistence.manager.CwgjzbpzsManager;
import com.sky.tzsc.gntzsc.persistence.model.Cwgjzbpzs;

/**
 * 
 * <pre> 
 * 描述：[tzsc_cwgjzbpzs]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 14:39:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("cwgjzbpzsManager")
public class CwgjzbpzsManagerImpl extends AbstractManagerImpl<String, Cwgjzbpzs> implements CwgjzbpzsManager{
	@Resource
	CwgjzbpzsDao cwgjzbpzsDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Cwgjzbpzs> getDao() {
		return cwgjzbpzsDao;
	}
	/**
	 * 根据主键得到[tzsc_cwgjzbpzs]信息
	 * @param id
	 * @return Cwgjzbpzs
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@Override
	public Cwgjzbpzs getCwgjzbpzs(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Cwgjzbpzs cwgjzbpzs = this.get(id);
		return cwgjzbpzs;
	}
	
	/**
	 * 保存[tzsc_cwgjzbpzs]对象
	 * @param cwgjzbpzs
	 * @return Cwgjzbpzs
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@Override
	public Cwgjzbpzs saveCwgjzbpzs(Cwgjzbpzs cwgjzbpzs) {
		//cwgjzbpzs.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getCwgjzbpzs(cwgjzbpzs.getId()))) {
			this.create(cwgjzbpzs);
			sysLogService.addSysLog("新增tzsc_cwgjzbpzs成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,cwgjzbpzs.getId());
		} else {
			this.update(cwgjzbpzs);
			sysLogService.addSysLog("更新tzsc_cwgjzbpzs成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,cwgjzbpzs.getId());
		}
		return cwgjzbpzs;
	}
	
	/**
	 * 执行删除[tzsc_cwgjzbpzs]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Cwgjzbpzs
	 * 			<li>如果是删除一条[tzsc_cwgjzbpzs]数据，会返回删除的[tzsc_cwgjzbpzs]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@Override
	public Cwgjzbpzs doRemove(String[] aryIds) {
		for(String id:aryIds){
			Cwgjzbpzs cwgjzbpzs = this.getCwgjzbpzs(id);
			//cwgjzbpzs.setIsDeleted(ConstSystem.YES);
			update(cwgjzbpzs);
			sysLogService.addSysLog("删除tzsc_cwgjzbpzs成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,cwgjzbpzs.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getCwgjzbpzs(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Cwgjzbpzs>
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	public PageList<Cwgjzbpzs>  cwgjzbpzsListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Cwgjzbpzs> cwgjzbpzsList = (PageList<Cwgjzbpzs>) this.query(queryFilter);// 执行查询
		return cwgjzbpzsList;
	}
}
