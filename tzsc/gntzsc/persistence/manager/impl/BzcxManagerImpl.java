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
import com.sky.common.context.ContextUtil;
import com.sky.common.util.MyBeanUtils;
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.dao.BzcxDao;
import com.sky.tzsc.gntzsc.persistence.dao.BzcxHistoryDao;
import com.sky.tzsc.gntzsc.persistence.manager.BzcxManager;
import com.sky.tzsc.gntzsc.persistence.model.Bzcx;
import com.sky.tzsc.gntzsc.persistence.model.BzcxHistory;

/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-20 15:37:20
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("bzcxManager")
public class BzcxManagerImpl extends AbstractManagerImpl<String, Bzcx> implements BzcxManager{
	@Resource
	BzcxDao bzcxDao;
	
	@Resource
	BzcxHistoryDao bzcxHistoryDao;
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	protected Dao<String, Bzcx> getDao() {
		return bzcxDao;
	}
	/**
	 * 根据主键得到[tzsc_bzcx]信息
	 * @param id
	 * @return Bzcx
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@Override
	public Bzcx getBzcx(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Bzcx bzcx = this.get(id);
		return bzcx;
	}
	
	/**
	 * 保存图纸审查标准船型对象
	 * @param bzcx
	 * @return Bzcx
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@Override
	public Bzcx saveBzcx(Bzcx bzcx) {
		//bzcx.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getBzcx(bzcx.getId()))) {
			this.create(bzcx);
			sysLogService.addSysLog("新增标准船型成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,bzcx.getId());
		} else {
			try {
				this.update(bzcx);
				Bzcx newBzcx = this.get(bzcx.getId());
				BzcxHistory bzcxHistory = new BzcxHistory();
				MyBeanUtils.copyBeanToBean(bzcxHistory, newBzcx);
				bzcxHistory.setBzcxId(bzcx.getId());
				bzcxHistory.setId(null);
				bzcxHistory.setUpdateUser(ContextUtil.getCurrentSysUser().getUserName());
				bzcxHistory.setUpdateTime(StaticMethod.getTimestamp());
				bzcxHistory.setUpdateDept(ContextUtil.getCurrentDeptId());
				bzcxHistoryDao.create(bzcxHistory);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sysLogService.addSysLog("更新标准船型成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,bzcx.getId());
		}
		return bzcx;
	}
	
	/**
	 * 执行删除 图纸审查标准船型对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Bzcx
	 * 			<li>如果是删除一条[tzsc_bzcx]数据，会返回删除的[tzsc_bzcx]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@Override
	public Bzcx doRemove(String[] aryIds) {
		for(String id:aryIds){
			Bzcx bzcx = this.getBzcx(id);
			//bzcx.setIsDeleted(ConstSystem.YES);
			update(bzcx);
			sysLogService.addSysLog("删除tzsc_bzcx成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,bzcx.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getBzcx(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Bzcx>
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	public PageList<Bzcx>  bzcxListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Bzcx> bzcxList = (PageList<Bzcx>) this.query(queryFilter);// 执行查询
		return bzcxList;
	}
}
