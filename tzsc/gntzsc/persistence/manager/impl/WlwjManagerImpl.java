package com.sky.tzsc.gntzsc.persistence.manager.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.db.mybatis.domain.PageList;

import com.sky.api.sys.log.constant.Globals;
import com.sky.api.sys.log.service.ISysLogService;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.sky.common.constant.ConstSystem;
import com.sky.common.context.ContextUtil;
import com.sky.common.util.StaticMethod;
import com.sky.sys.file.persistence.manager.SysFileManager;
import com.sky.sys.file.persistence.model.SysFile;
import com.sky.tzsc.gntzsc.persistence.dao.WlwjDao;
import com.sky.tzsc.gntzsc.persistence.manager.WlwjManager;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;

/**
 * 
 * <pre> 
 * 描述：[tzsc_wlwj]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 16:34:38
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("wlwjManager")
public class WlwjManagerImpl extends AbstractManagerImpl<String, Wlwj> implements WlwjManager{
	@Resource
	WlwjDao wlwjDao;
	
	@Resource
	ISysLogService sysLogService;
	@Resource
	SysFileManager sysFileManager;
	@Override
	protected Dao<String, Wlwj> getDao() {
		return wlwjDao;
	}
	/**
	 * 根据主键得到[tzsc_wlwj]信息
	 * @param id
	 * @return Wlwj
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@Override
	public Wlwj getWlwj(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Wlwj wlwj = this.get(id);
		return wlwj;
	}
	
	/**
	 * 保存[tzsc_wlwj]对象
	 * @param wlwj
	 * @return Wlwj
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@Override
	public Wlwj saveWlwj(Wlwj wlwj) {
		//wlwj.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getWlwj(wlwj.getId()))) {
			this.create(wlwj);
			sysLogService.addSysLog("新增往来文件成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,wlwj.getId());
		} else {
			this.update(wlwj);
			sysLogService.addSysLog("更新往来文件成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,wlwj.getId());
		}
		return wlwj;
	}
	
	/**
	 * 执行删除[tzsc_wlwj]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Wlwj
	 * 			<li>如果是删除一条[tzsc_wlwj]数据，会返回删除的[tzsc_wlwj]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@Override
	public Wlwj doRemove(String[] aryIds) {
		for(String id:aryIds){
			Wlwj wlwj = this.getWlwj(id);
			//wlwj.setIsDeleted(ConstSystem.YES);
			update(wlwj);
			sysLogService.addSysLog("删除往来文件成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,wlwj.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getWlwj(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Wlwj>
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	public PageList<Wlwj>  wlwjListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Wlwj> wlwjList = (PageList<Wlwj>) this.query(queryFilter);// 执行查询
		return wlwjList;
	}
	/**
	 * 往来文件 查询 
	 * @param paramMaps
	 * @return PageList<Wlwj>
	 * @author administrator 
	 * @Create 2017-12-6 09:44:48
	 */
	@Override
	public Wlwj dealWlwjBySqId(QueryFilter queryFilter) {
	PageList<Wlwj> wlwjList = (PageList<Wlwj>) this.query(queryFilter);
	Wlwj wlwj = new Wlwj();
	Wlwj wlwjView = new Wlwj();
	if(!wlwjList.isEmpty()) {
		for(int i=0;i<wlwjList.size();i++) {
			wlwj = wlwjList.get(i);
			this.dealWlwj(wlwj,wlwjView);
		}
		
	}
		return wlwjView;
	}
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @return Wlwj
	 * @author wjk 
	 * @Create Date 2017 上午9:45:58
	 */
	private Wlwj dealWlwj(Wlwj wlwj,Wlwj wlwjView) {
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("wth")) {
			wlwjView.setScwth(wlwj.getFjId());
			wlwjView.setScwthSjdw(wlwj.getSjdw());
		}
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("styjs")) {
			wlwjView.setStyjs(wlwj.getFjId());
			wlwjView.setStyjSjdw(wlwj.getSjdw());
		}
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("dxmcpw")) {
			wlwjView.setDxmcpw(wlwj.getFjId());
			wlwjView.setDxmcpwSjdw(wlwj.getSjdw());
		}
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("tzscbg")) {
			wlwjView.setTzscbg(wlwj.getFjId());
			wlwjView.setTzscbgSjdw(wlwj.getSjdw());
		}
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("tzpzh")) {
			wlwjView.setTzpzh(wlwj.getFjId());
			wlwjView.setTzpzhSjdw(wlwj.getSjdw());
		}
		return wlwj;
	}
	/**
	 * 
	 */
	@Override
	public void saveWlwjByTzsq(Wlwj wlwj, QueryFilter queryFilter) {
		if (!StaticMethod.nullObject2String(wlwj.getScwth()).equals("")) {
			this.dealCommonObject("wth", wlwj.getScwth(), "委托函", wlwj.getScwth(), queryFilter,wlwj.getScwthSjdw(),wlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(wlwj.getStyjs()).equals("")) {
			this.dealCommonObject("styjs", wlwj.getStyjs(), "审图意见书", wlwj.getStyjs(), queryFilter,wlwj.getStyjSjdw(),wlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(wlwj.getDxmcpw()).equals("")) {
			this.dealCommonObject("dxmcpw", wlwj.getDxmcpw(), "等效免除批文", wlwj.getDxmcpw(), queryFilter,wlwj.getDxmcpwSjdw(),wlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(wlwj.getTzscbg()).equals("")) {
			this.dealCommonObject("tzscbg", wlwj.getTzscbg(), "图纸审查报告", wlwj.getTzscbg(), queryFilter,wlwj.getTzscbgSjdw(),wlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(wlwj.getTzpzh()).equals("")) {
			this.dealCommonObject("tzpzh", wlwj.getTzpzh(), "图纸批准函", wlwj.getTzpzh(), queryFilter,wlwj.getTzpzhSjdw(),wlwj.getTzsqId());
		}
	}
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @param string
	 * @param tzsq
	 * @param string2
	 * @param ctbf
	 * @param queryFilter
	 * @param string3 void
	 * @author wjk 
	 * @Create Date 2017 上午9:58:56
	 */
	private void dealCommonObject(String wjlbYw, String sysFileId, String wjlb, String scwth, QueryFilter queryFilter,String scwthSjdw,String tzscSqId) {
		Wlwj wlwjNew = new Wlwj();
		String fjmc = "";
		this.removeWlwj(sysFileId, queryFilter);
		String[] sysFileIdArr = sysFileId.split(",");
		if (sysFileIdArr.length == 1) {
			SysFile sysFile = sysFileManager.getJson(sysFileId);
			fjmc = sysFile.getName();
		}
		if (sysFileIdArr.length > 1) {
			for (int i = 0; i < sysFileIdArr.length; i++) {
				SysFile sysFile = sysFileManager.getJson(sysFileIdArr[i]);
				fjmc = fjmc + ";" + sysFile.getName();
				this.removeWlwj(sysFile.getId(), queryFilter);
			}
		}
		wlwjNew.setTzsqId(tzscSqId);
		wlwjNew.setWjlb(wjlb);
		wlwjNew.setFjId(sysFileId);
		wlwjNew.setWjlbYw(wjlbYw);
		wlwjNew.setWjmc(fjmc);
		wlwjNew.setSjdw(scwthSjdw);
		wlwjNew.setScczr(ContextUtil.getCurrentSysUser().getUserName());
		wlwjNew.setScsj(StaticMethod.getTimestamp());
		wlwjNew.setInsertTime(StaticMethod.getTimestamp());
		wlwjNew.setScbj(0);
		this.saveWlwj(wlwjNew);
	}
	
	private void removeWlwj(String  sysFileId, QueryFilter queryFilter) {
		queryFilter.addFilter("fj_id", "%" + sysFileId + "%", QueryOP.LIKE);
		List<Wlwj> wlwjList = this.query(queryFilter);
		if (null != wlwjList && wlwjList.size() > 0) {
			for (int i = 0; i < wlwjList.size(); i++) {
				Wlwj removeWlwj = wlwjList.get(i);
				this.remove(removeWlwj.getId());
			}
		}
		
	}
}
