package com.sky.tzsc.yytzsc.persistence.manager.impl;

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
import com.sky.tzsc.yytzsc.persistence.dao.YyWlwjDao;
import com.sky.tzsc.yytzsc.persistence.manager.YyWlwjManager;
import com.sky.tzsc.yytzsc.persistence.model.YyWlwj;

/**
 * 
 * <pre>
 *  
 * 描述：[远洋图纸审查往来文件表]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-08 10:08:08
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("yyWlwjManager")
public class YyWlwjManagerImpl extends AbstractManagerImpl<String, YyWlwj> implements YyWlwjManager {
	@Resource
	YyWlwjDao yyWlwjDao;
	@Resource
	SysFileManager sysFileManager;
	@Resource
	ISysLogService sysLogService;

	@Override
	protected Dao<String, YyWlwj> getDao() {
		return yyWlwjDao;
	}

	/**
	 * 根据主键得到[远洋图纸审查往来文件表]信息
	 * 
	 * @param id
	 * @return YyWlwj
	 * @author administrator
	 * @Create 2017-12-08 10:08:08
	 */
	@Override
	public YyWlwj getYyWlwj(String id) {
		if (!StaticMethod.isNotEmpty(id)) {
			return null;
		}
		YyWlwj yyWlwj = this.get(id);
		return yyWlwj;
	}

	/**
	 * 保存[远洋图纸审查往来文件表]对象
	 * 
	 * @param yyWlwj
	 * @return YyWlwj
	 * @author administrator
	 * @Create 2017-12-08 10:08:08
	 */
	@Override
	public YyWlwj saveYyWlwj(YyWlwj yyWlwj) {
		// yyWlwj.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getYyWlwj(yyWlwj.getId()))) {
			this.create(yyWlwj);
			sysLogService.addSysLog("新增远洋图纸审查往来文件表成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,
					yyWlwj.getId());
		} else {
			this.update(yyWlwj);
			sysLogService.addSysLog("更新远洋图纸审查往来文件表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,
					yyWlwj.getId());
		}
		return yyWlwj;
	}

	/**
	 * 执行删除[远洋图纸审查往来文件表]对象
	 * 
	 * @param aryIds
	 *            <li>主键值，以逗号隔开</li>
	 * @return YyWlwj
	 *         <li>如果是删除一条[远洋图纸审查往来文件表]数据，会返回删除的[远洋图纸审查往来文件表]信息。批量删除，会返回null</li>
	 * @author administrator
	 * @Create 2017-12-08 10:08:08
	 */
	@Override
	public YyWlwj doRemove(String[] aryIds) {
		for (String id : aryIds) {
			YyWlwj yyWlwj = this.getYyWlwj(id);
			// yyWlwj.setIsDeleted(ConstSystem.YES);
			update(yyWlwj);
			sysLogService.addSysLog("删除远洋图纸审查往来文件表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,
					yyWlwj.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) {
			return this.getYyWlwj(aryIds[0]);
		}
		return null;
	}

	/**
	 * 执行列表查询
	 * 
	 * @param paramMaps
	 * @return PageList<YyWlwj>
	 * @author administrator
	 * @Create 2017-12-08 10:08:08
	 */
	public PageList<YyWlwj> yyWlwjListJson(Map<String, Object> paramMap) {
		/****************** paramMap中可以追加条件,例如：paramMap.put(key,value) *************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<YyWlwj> yyWlwjList = (PageList<YyWlwj>) this.query(queryFilter);// 执行查询
		return yyWlwjList;
	}

	/**
	 * 执行列表查询
	 * 
	 * @param paramMaps
	 * @return PageList<YyWlwj>
	 * @author administrator
	 * @Create 2017-12-8 16:34:49
	 */
	@Override
	public void saveWlwjByTzsq(YyWlwj yyWlwj, QueryFilter queryFilter) {
		if (!StaticMethod.nullObject2String(yyWlwj.getScwth()).equals("")) {
			this.dealCommonObject("wth", yyWlwj.getScwth(), "委托函", yyWlwj.getScwth(), queryFilter,yyWlwj.getScwthSjdw(), yyWlwj.getTzsqId());
		}
		if (!StaticMethod.nullObject2String(yyWlwj.getScwthfwgj()).equals("")) {
			this.dealCommonObject("scwthfwgj", yyWlwj.getScwthfwgj(), "委托函发文稿件", yyWlwj.getScwthfwgj(), queryFilter,yyWlwj.getScwthfwgjSjdw(), yyWlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(yyWlwj.getStyjs()).equals("")) {
			this.dealCommonObject("styjs", yyWlwj.getStyjs(), "审图意见书", yyWlwj.getStyjs(), queryFilter,yyWlwj.getStyjSjdw(),yyWlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(yyWlwj.getDxmcpw()).equals("")) {
			this.dealCommonObject("dxmcpw", yyWlwj.getDxmcpw(), "等效免除批文", yyWlwj.getDxmcpw(), queryFilter,yyWlwj.getDxmcpwSjdw(),yyWlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(yyWlwj.getTzscbg()).equals("")) {
			this.dealCommonObject("tzscbg", yyWlwj.getTzscbg(), "图纸审查报告", yyWlwj.getTzscbg(), queryFilter,yyWlwj.getTzscbgSjdw(),yyWlwj.getTzsqId());
		}
		if(!StaticMethod.nullObject2String(yyWlwj.getTzpzh()).equals("")) {
			this.dealCommonObject("tzpzh", yyWlwj.getTzpzh(), "图纸批准函", yyWlwj.getTzpzh(), queryFilter,yyWlwj.getTzpzhSjdw(),yyWlwj.getTzsqId());
		}
	}

	/**
	 * 往来文件 处理
	 * 
	 * @param wjlbYw
	 * @param sysFileId
	 * @param wjlb
	 * @param scwth
	 * @param queryFilter
	 * @param scwthSjdw
	 * @param tzscSqId
	 *            void
	 * @author wjk
	 * @Create Date 2017 下午4:38:11
	 */
	private void dealCommonObject(String wjlbYw, String sysFileId, String wjlb, String scwth, QueryFilter queryFilter,String scwthSjdw, String tzscSqId) {
		YyWlwj wlwjNew = new YyWlwj();
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
		wlwjNew.setInsertTime(StaticMethod.getTimestamp());
		wlwjNew.setScbj(0);
		wlwjNew.setScczr(ContextUtil.getCurrentSysUser().getUserName());
		wlwjNew.setScsj(StaticMethod.getTimestamp());
		wlwjNew.setInsertDept(ContextUtil.getCurrentSysDept().getDeptName());
		wlwjNew.setInsertDeptId(ContextUtil.getCurrentDeptId());
		wlwjNew.setInsertLogin(ContextUtil.getCurrentLogin().getLoginUserId());
		wlwjNew.setInsertUser(ContextUtil.getCurrentSysUser().getUserName());
		wlwjNew.setInsertUserId(ContextUtil.getCurrentSysUser().getUserId());
		this.saveYyWlwj(wlwjNew);

	}

	/**
	 * 删除 往来文件
	 * 
	 * @param sysFileId
	 * @param queryFilter
	 *            void
	 * @author wjk
	 * @Create Date 2017 下午4:39:59
	 */
	private void removeWlwj(String sysFileId, QueryFilter queryFilter) {
		queryFilter.addFilter("fj_id", "%" + sysFileId + "%", QueryOP.LIKE);
		List<YyWlwj> wlwjList = this.query(queryFilter);
		if (null != wlwjList && wlwjList.size() > 0) {
			for (int i = 0; i < wlwjList.size(); i++) {
				YyWlwj removeWlwj = wlwjList.get(i);
				this.remove(removeWlwj.getId());
			}
		}
	}
	@Override
	public YyWlwj dealWlwjBySqId(QueryFilter queryFilter) {
		PageList<YyWlwj> wlwjList = (PageList<YyWlwj>) this.query(queryFilter);
		YyWlwj wlwj = new YyWlwj();
		YyWlwj wlwjView = new YyWlwj();
		if(!wlwjList.isEmpty()) {
			for(int i=0;i<wlwjList.size();i++) {
				wlwj = wlwjList.get(i);
				this.dealWlwj(wlwj,wlwjView);
			}
			
		}
			return wlwjView;
	}
	/**
	 * 处理往来文件   
	 * @param wlwj
	 * @param wlwjView void
	 * @author wjk 
	 * @Create Date 2017 下午5:07:24
	 */
	private void dealWlwj(YyWlwj wlwj, YyWlwj wlwjView) {
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("wth")) {
			wlwjView.setScwth(wlwj.getFjId());
			wlwjView.setScwthSjdw(wlwj.getSjdw());
		}
		if(wlwj != null && StaticMethod.nullObject2String(wlwj.getWjlbYw()).equals("scwthfwgj")) {
			wlwjView.setScwthfwgj(wlwj.getFjId());
			wlwjView.setScwthfwgjSjdw(wlwj.getSjdw());
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
		
	}
}
