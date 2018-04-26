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
import com.sky.tzsc.gntzsc.persistence.dao.BzcxHistoryDao;
import com.sky.tzsc.gntzsc.persistence.manager.BzcxHistoryManager;
import com.sky.tzsc.gntzsc.persistence.model.BzcxHistory;

/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx_history]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-02-26 09:17:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("bzcxHistoryManager")
public class BzcxHistoryManagerImpl extends AbstractManagerImpl<String, BzcxHistory> implements BzcxHistoryManager{
	@Resource
	BzcxHistoryDao bzcxHistoryDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, BzcxHistory> getDao() {
		return bzcxHistoryDao;
	}
	/**
	 * 根据主键得到[tzsc_bzcx_history]信息
	 * @param id
	 * @return BzcxHistory
	 * @author administrator 
	 * @Create 2018-02-26 09:17:18
	 */
	@Override
	public BzcxHistory getBzcxHistory(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		BzcxHistory bzcxHistory = this.get(id);
		return bzcxHistory;
	}
	
	/**
	 * 保存[tzsc_bzcx_history]对象
	 * @param bzcxHistory
	 * @return BzcxHistory
	 * @author administrator 
	 * @Create 2018-02-26 09:17:18
	 */
	@Override
	public BzcxHistory saveBzcxHistory(BzcxHistory bzcxHistory) {
		//bzcxHistory.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getBzcxHistory(bzcxHistory.getId()))) {
			this.create(bzcxHistory);
			sysLogService.addSysLog("新增tzsc_bzcx_history成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,bzcxHistory.getId());
		} else {
			this.update(bzcxHistory);
			sysLogService.addSysLog("更新tzsc_bzcx_history成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,bzcxHistory.getId());
		}
		return bzcxHistory;
	}
	
	/**
	 * 执行删除[tzsc_bzcx_history]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return BzcxHistory
	 * 			<li>如果是删除一条[tzsc_bzcx_history]数据，会返回删除的[tzsc_bzcx_history]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-02-26 09:17:18
	 */
	@Override
	public BzcxHistory doRemove(String[] aryIds) {
		for(String id:aryIds){
			BzcxHistory bzcxHistory = this.getBzcxHistory(id);
			//bzcxHistory.setIsDeleted(ConstSystem.YES);
			update(bzcxHistory);
			sysLogService.addSysLog("删除tzsc_bzcx_history成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,bzcxHistory.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getBzcxHistory(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<BzcxHistory>
	 * @author administrator 
	 * @Create 2018-02-26 09:17:18
	 */
	public PageList<BzcxHistory>  bzcxHistoryListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<BzcxHistory> bzcxHistoryList = (PageList<BzcxHistory>) this.query(queryFilter);// 执行查询
		return bzcxHistoryList;
	}
}
