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
import com.sky.tzsc.gntzsc.persistence.dao.ClshyjDao;
import com.sky.tzsc.gntzsc.persistence.manager.ClshyjManager;
import com.sky.tzsc.gntzsc.persistence.model.Clshyj;

/**
 * 
 * <pre> 
 * 描述：[材料审核意见]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-01 10:33:48
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("clshyjManager")
public class ClshyjManagerImpl extends AbstractManagerImpl<String, Clshyj> implements ClshyjManager{
	@Resource
	ClshyjDao clshyjDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Clshyj> getDao() {
		return clshyjDao;
	}
	/**
	 * 根据主键得到[材料审核意见]信息
	 * @param id
	 * @return Clshyj
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@Override
	public Clshyj getClshyj(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Clshyj clshyj = this.get(id);
		return clshyj;
	}
	
	/**
	 * 保存[材料审核意见]对象
	 * @param clshyj
	 * @return Clshyj
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@Override
	public Clshyj saveClshyj(Clshyj clshyj) {
		//clshyj.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getClshyj(clshyj.getId()))) {
			this.create(clshyj);
			sysLogService.addSysLog("新增材料审核意见成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,clshyj.getId());
		} else {
			this.update(clshyj);
			sysLogService.addSysLog("更新材料审核意见成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,clshyj.getId());
		}
		return clshyj;
	}
	
	/**
	 * 执行删除[材料审核意见]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Clshyj
	 * 			<li>如果是删除一条[材料审核意见]数据，会返回删除的[材料审核意见]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@Override
	public Clshyj doRemove(String[] aryIds) {
		for(String id:aryIds){
			Clshyj clshyj = this.getClshyj(id);
			//clshyj.setIsDeleted(ConstSystem.YES);
			update(clshyj);
			sysLogService.addSysLog("删除材料审核意见成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,clshyj.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getClshyj(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Clshyj>
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	public PageList<Clshyj>  clshyjListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Clshyj> clshyjList = (PageList<Clshyj>) this.query(queryFilter);// 执行查询
		return clshyjList;
	}
}
