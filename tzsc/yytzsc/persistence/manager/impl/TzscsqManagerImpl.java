package com.sky.tzsc.yytzsc.persistence.manager.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.mybatis.domain.PageList;
import com.sky.api.base.flow.service.IBaseFlowService;
import com.sky.api.sys.log.constant.Globals;
import com.sky.api.sys.log.service.ISysLogService;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.sky.common.constant.ConstSystem;
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.util.ConstTzsc;
import com.sky.tzsc.yytzsc.persistence.dao.TzscsqDao;
import com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查申请表]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:28:56
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("tzscsqManager")
public class TzscsqManagerImpl extends AbstractManagerImpl<String, Tzscsq> implements TzscsqManager{
	@Resource
	TzscsqDao tzscsqDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Resource
	IBaseFlowService<?, Tzscsq> baseFlowService;
	
	
	@Override
	protected Dao<String, Tzscsq> getDao() {
		return tzscsqDao;
	}
	/**
	 * 根据主键得到[远洋图纸审查申请表]信息
	 * @param id
	 * @return Tzscsq
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@Override
	public Tzscsq getTzscsq(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Tzscsq tzscsq = this.get(id);
		return tzscsq;
	}
	
	/**
	 * 保存[远洋图纸审查申请表]对象
	 * @param tzscsq
	 * @return Tzscsq
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@Override
	public Tzscsq saveTzscsq(Tzscsq tzscsq) {
		//tzscsq.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getTzscsq(tzscsq.getId()))) {
			this.create(tzscsq);
			if(tzscsq.getTjzt() == 1) {
				//启动流程
				baseFlowService.startFlow(ConstTzsc.FLOW_YYTZSC, tzscsq);
			}
			sysLogService.addSysLog("新增远洋图纸审查申请表成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,tzscsq.getId());
		} else {
			this.update(tzscsq);
			sysLogService.addSysLog("更新远洋图纸审查申请表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,tzscsq.getId());
		}
		return tzscsq;
	}
	
	/**
	 * 执行删除[远洋图纸审查申请表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Tzscsq
	 * 			<li>如果是删除一条[远洋图纸审查申请表]数据，会返回删除的[远洋图纸审查申请表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@Override
	public Tzscsq doRemove(String[] aryIds) {
		for(String id:aryIds){
			Tzscsq tzscsq = this.getTzscsq(id);
			//tzscsq.setIsDeleted(ConstSystem.YES);
			update(tzscsq);
			sysLogService.addSysLog("删除远洋图纸审查申请表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,tzscsq.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getTzscsq(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Tzscsq>
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	public PageList<Tzscsq>  tzscsqListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Tzscsq> tzscsqList = (PageList<Tzscsq>) this.query(queryFilter);// 执行查询
		return tzscsqList;
	}
}
