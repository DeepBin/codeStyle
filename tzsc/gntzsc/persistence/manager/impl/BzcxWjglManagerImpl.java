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
import com.sky.tzsc.gntzsc.persistence.dao.BzcxWjglDao;
import com.sky.tzsc.gntzsc.persistence.manager.BzcxWjglManager;
import com.sky.tzsc.gntzsc.persistence.model.BzcxWjgl;

/**
 * 
 * <pre> 
 * 描述：[标准船型文件管理]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-25 09:17:50
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("bzcxWjglManager")
public class BzcxWjglManagerImpl extends AbstractManagerImpl<String, BzcxWjgl> implements BzcxWjglManager{
	@Resource
	BzcxWjglDao bzcxWjglDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, BzcxWjgl> getDao() {
		return bzcxWjglDao;
	}
	/**
	 * 根据主键得到[标准船型文件管理]信息
	 * @param id
	 * @return BzcxWjgl
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@Override
	public BzcxWjgl getBzcxWjgl(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		BzcxWjgl bzcxWjgl = this.get(id);
		return bzcxWjgl;
	}
	
	/**
	 * 保存[标准船型文件管理]对象
	 * @param bzcxWjgl
	 * @return BzcxWjgl
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@Override
	public BzcxWjgl saveBzcxWjgl(BzcxWjgl bzcxWjgl) {
		//bzcxWjgl.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getBzcxWjgl(bzcxWjgl.getId()))) {
			this.create(bzcxWjgl);
			sysLogService.addSysLog("新增标准船型文件管理成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,bzcxWjgl.getId());
		} else {
			this.update(bzcxWjgl);
			sysLogService.addSysLog("更新标准船型文件管理成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,bzcxWjgl.getId());
		}
		return bzcxWjgl;
	}
	
	/**
	 * 执行删除[标准船型文件管理]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return BzcxWjgl
	 * 			<li>如果是删除一条[标准船型文件管理]数据，会返回删除的[标准船型文件管理]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@Override
	public BzcxWjgl doRemove(String[] aryIds) {
		for(String id:aryIds){
			BzcxWjgl bzcxWjgl = this.getBzcxWjgl(id);
			//bzcxWjgl.setIsDeleted(ConstSystem.YES);
			update(bzcxWjgl);
			sysLogService.addSysLog("删除标准船型文件管理成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,bzcxWjgl.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getBzcxWjgl(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<BzcxWjgl>
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	public PageList<BzcxWjgl>  bzcxWjglListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<BzcxWjgl> bzcxWjglList = (PageList<BzcxWjgl>) this.query(queryFilter);// 执行查询
		return bzcxWjglList;
	}
}
