package com.sky.tzsc.gntzsc.persistence.manager.impl;

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
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.dao.TzsqDao;
import com.sky.tzsc.gntzsc.persistence.manager.TzsqManager;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.util.ConstTzsc;

/**
 * 
 * <pre> 
 * 描述：[tzsc_sq]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-22 14:04:09
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("tzsqManager")
public class TzsqManagerImpl extends AbstractManagerImpl<String, Tzsq> implements TzsqManager{
	@Resource
	TzsqDao tzsqDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Resource
	IBaseFlowService<?, Tzsq> baseFlowService;
	
	@Override
	protected Dao<String, Tzsq> getDao() {
		return tzsqDao;
	}
	/**
	 * 根据主键得到图纸申请信息
	 * @param id
	 * @return Tzsq
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	@Override
	public Tzsq getTzsq(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Tzsq tzsq = this.get(id);
		return tzsq;
	}
	
	/**
	 * 保存[tzsc_sq]对象
	 * @param tzsq
	 * @return Tzsq
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	@Override
	public Tzsq saveTzsq(Tzsq tzsq) {
		//tzsq.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getTzsq(tzsq.getId()))) {
			this.create(tzsq);
			if(tzsq.getTjzt() ==1) {
				// 启动流程
				baseFlowService.startFlow(ConstTzsc.FLOW_GNTZSC, tzsq);
			}
			sysLogService.addSysLog("新增图纸申请成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,tzsq.getId());
		} else {
			this.update(tzsq);
			sysLogService.addSysLog("更新图纸申请成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,tzsq.getId());
		}
		return tzsq;
	}
	
	/**
	 * 执行删除[tzsc_sq]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Tzsq
	 * 			<li>如果是删除一条[tzsc_sq]数据，会返回删除的[tzsc_sq]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	@Override
	public Tzsq doRemove(String[] aryIds) {
		for(String id:aryIds){
			Tzsq tzsq = this.getTzsq(id);
			//tzsq.setIsDeleted(ConstSystem.YES);
			update(tzsq);
			sysLogService.addSysLog("删除图纸申请成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,tzsq.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getTzsq(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Tzsq>
	 * @author administrator 
	 * @Create 2017-11-22 14:04:09
	 */
	public PageList<Tzsq>  tzsqListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Tzsq> tzsqList = (PageList<Tzsq>) this.query(queryFilter);// 执行查询
		return tzsqList;
	}
	
	public List  getTzdzgList(Map<String ,Object> paramMap) {
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		paramMap = queryFilter.getParams();
		List dzgList = tzsqDao.getDzgList(paramMap);
		return dzgList;
	}
	
	
}
