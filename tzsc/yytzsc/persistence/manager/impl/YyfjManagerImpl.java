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
import com.sky.common.util.StaticMethod;
import com.sky.sys.file.persistence.manager.SysFileManager;
import com.sky.sys.file.persistence.model.SysFile;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.yytzsc.persistence.dao.YyfjDao;
import com.sky.tzsc.yytzsc.persistence.manager.YyfjManager;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;
import com.sky.tzsc.yytzsc.persistence.model.Yyfj;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查附件表]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 14:47:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("yyfjManager")
public class YyfjManagerImpl extends AbstractManagerImpl<String, Yyfj> implements YyfjManager{
	@Resource
	YyfjDao yyfjDao;
	
	@Resource
	ISysLogService sysLogService;
	@Resource
	SysFileManager sysFileManager;
	@Override
	protected Dao<String, Yyfj> getDao() {
		return yyfjDao;
	}
	/**
	 * 根据主键得到[远洋图纸审查附件表]信息
	 * @param id
	 * @return Yyfj
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@Override
	public Yyfj getYyfj(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Yyfj yyfj = this.get(id);
		return yyfj;
	}
	
	/**
	 * 保存[远洋图纸审查附件表]对象
	 * @param yyfj
	 * @return Yyfj
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@Override
	public Yyfj saveYyfj(Yyfj yyfj) {
		//yyfj.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getYyfj(yyfj.getId()))) {
			this.create(yyfj);
			sysLogService.addSysLog("新增远洋图纸审查附件表成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO,yyfj.getId());
		} else {
			this.update(yyfj);
			sysLogService.addSysLog("更新远洋图纸审查附件表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,yyfj.getId());
		}
		return yyfj;
	}
	
	/**
	 * 执行删除[远洋图纸审查附件表]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Yyfj
	 * 			<li>如果是删除一条[远洋图纸审查附件表]数据，会返回删除的[远洋图纸审查附件表]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@Override
	public Yyfj doRemove(String[] aryIds) {
		for(String id:aryIds){
			Yyfj yyfj = this.getYyfj(id);
			//yyfj.setIsDeleted(ConstSystem.YES);
			update(yyfj);
			sysLogService.addSysLog("删除远洋图纸审查附件表成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO,yyfj.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) { return this.getYyfj(aryIds[0]); }
		return null;
	}
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Yyfj>
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	public PageList<Yyfj>  yyfjListJson(Map<String ,Object> paramMap) {
		/******************paramMap中可以追加条件,例如：paramMap.put(key,value)*************/
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Yyfj> yyfjList = (PageList<Yyfj>) this.query(queryFilter);// 执行查询
		return yyfjList;
	}
	
	/**
	 * 执行列表查询
	 * @param queryFilter tzscsq
	 * @return void
	 * @author  wjk
	 * @Create 2017-12-7 14:55:12
	 */
	@Override
	public void saveFjByTzsq(Tzscsq tzscsq, QueryFilter queryFilter) {
		/*if (!StaticMethod.nullObject2String(tzscsq.getCtbf()).equals("")) {
			this.dealCommonObject("ctbf", tzscsq, "船体部分", tzscsq.getCtbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getXzbf()).equals("")) {
			this.dealCommonObject("xzbf", tzscsq, "舾装部分", tzscsq.getXzbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getFhbf()).equals("")) {
			this.dealCommonObject("fhbf", tzscsq, "防火、探火和灭火部分", tzscsq.getFhbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getLjbf()).equals("")) {
			this.dealCommonObject("ljbf", tzscsq, "轮机部分", tzscsq.getLjbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getDqbf()).equals("")) {
			this.dealCommonObject("dqbf", tzscsq, "电气部分", tzscsq.getDqbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getDqwrzsbf()).equals("")) {
			this.dealCommonObject("dqwrzsbf", tzscsq, "定期无人值班机器处所部分", tzscsq.getDqwrzsbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getWxdtxjdhbf()).equals("")) {
			this.dealCommonObject("wxdtxjdhbf", tzscsq, "无线电通信设备和航行设备部分", tzscsq.getWxdtxjdhbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzscsq.getLcjldbf()).equals("")) {
			this.dealCommonObject("lcjldbf", tzscsq, "冷藏及冷冻装置部分", tzscsq.getLcjldbf(), queryFilter,"");
		}*/
	}
	
	/**
	 * 附件 处理   
	 * @param item
	 * @param tzscsq
	 * @param fjmlflString
	 * @param sysFileId
	 * @param queryFilter
	 * @param sjdw void
	 * @author wjk 
	 * @Create Date 2017 下午2:59:22
	 */
	private void dealCommonObject(String item, Tzscsq tzscsq, String fjmlflString, String sysFileId,QueryFilter queryFilter,String sjdw) {
		Yyfj yyfj = new Yyfj();
		String fjmc = "";
		this.removeFj(sysFileId, queryFilter);
		String[] sysFileIdArr = sysFileId.split(",");
		if (sysFileIdArr.length == 1) {
			SysFile sysFile = sysFileManager.getJson(sysFileId);
			fjmc = sysFile.getName();
		}
		if (sysFileIdArr.length > 1) {
			for (int i = 0; i < sysFileIdArr.length; i++) {
				SysFile sysFile = sysFileManager.getJson(sysFileIdArr[i]);
				fjmc = fjmc + ";" + sysFile.getName();
				this.removeFj(sysFile.getId(), queryFilter);
			}
		}
		yyfj.setFjmc(fjmc);
		yyfj.setFjmlflYw(item);
		yyfj.setFjmlfl(fjmlflString);
		yyfj.setFjId(sysFileId);
		yyfj.setTzsqId(tzscsq.getId());
		yyfj.setInsertTime(StaticMethod.getTimestamp());
		yyfj.setSjdw(sjdw);
		this.saveYyfj(yyfj);
		
	}
	
	/**
	 * 每次进行 附件上传时 进行 附件的删除 后重新 插入    
	 * @param sysFileId
	 * @param queryFilter void
	 * @author wjk 
	 * @Create Date 2017 下午2:59:27
	 */
	private void removeFj(String sysFileId, QueryFilter queryFilter) {
		queryFilter.addFilter("fj_id", "%" + sysFileId + "%", QueryOP.LIKE);
		List<Yyfj> fjList = this.query(queryFilter);
		if (null != fjList && fjList.size() > 0) {
			for (int i = 0; i < fjList.size(); i++) {
				Yyfj removeFj = fjList.get(i);
				this.remove(removeFj.getId());
			}
		}
		
	}
	/**
	 * 单独 处理每个 附件     
	 * @param tzscsq queryFilter
	 * @param  void
	 * @author wjk 
	 * @Create Date 2017 下午2:59:27
	 */
	@Override
	public void dealFjBySqId(Tzscsq tzscsq, QueryFilter queryFilter) {
		queryFilter.addFilter("tzsq_id", tzscsq.getId(), QueryOP.EQUAL);
		this.dealCtbf(queryFilter, tzscsq);
		this.dealXzbf(queryFilter, tzscsq);
		this.dealFhdq(queryFilter, tzscsq);
		this.dealLjbf(queryFilter, tzscsq);
		this.dealDqbf(queryFilter, tzscsq);
		this.dealDqwrzsbf(queryFilter, tzscsq);
		this.dealWxdtxjdhbf(queryFilter, tzscsq);
		this.dealLcjldbf(queryFilter, tzscsq);
	}

	/**
	 * 处理冷藏 冷冻 部分 附件
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:23:01
	 */
	private void dealLcjldbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "lcjldbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setLcjldbf(yyfj.getFjId());
		}
		
	}
	/**
	 * 处理 无线电 通信设备部分   
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:21:48
	 */
	private void dealWxdtxjdhbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "wxdtxjdhbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setWxdtxjdhbf(yyfj.getFjId());
		}
		
	}
	/**
	 * 处理定期无人值班机器处所部分    
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:20:26
	 */
	private void dealDqwrzsbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "dqwrzsbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setDqwrzsbf(yyfj.getFjId());
		}
		
	}
	/**
	 * 处理电气 部分附件    
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:18:22
	 */
	private void dealDqbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "dqbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setDqbf(yyfj.getFjId());
		}
		
	}
	/**
	 * 处理轮机 部分附件    
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:16:43
	 */
	private void dealLjbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "ljbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setLjbf(yyfj.getFjId());
		}
		
	}
	/**
	 * 处理  防火 部分 附件    
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:15:24
	 */
	private void dealFhdq(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "fhbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setFhbf(yyfj.getFjId());
		}
		
	}
	/**
	 * 处理舾装 部分 附件    
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:11:50
	 */
	private void dealXzbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "xzbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setXzbf(yyfj.getFjId());
		}
	}
	/**
	 *处理船体部分 附件   
	 * @param queryFilter
	 * @param tzscsq void
	 * @author wjk 
	 * @Create Date 2017 下午3:09:39
	 */
	private void dealCtbf(QueryFilter queryFilter, Tzscsq tzscsq) {
		queryFilter.addFilter("fjmlfl_yw", "ctbf", QueryOP.EQUAL);
		List<Yyfj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Yyfj yyfj = list.get(0);
			//tzscsq.setCtbf(yyfj.getFjId());
		}
	}
	
}
