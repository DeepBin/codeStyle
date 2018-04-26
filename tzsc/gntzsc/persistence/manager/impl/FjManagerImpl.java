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
import com.sky.common.util.StaticMethod;
import com.sky.sys.file.persistence.manager.SysFileManager;
import com.sky.sys.file.persistence.model.SysFile;
import com.sky.tzsc.gntzsc.persistence.dao.FjDao;
import com.sky.tzsc.gntzsc.persistence.manager.FjManager;
import com.sky.tzsc.gntzsc.persistence.model.Fj;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;

/**
 * 
 * <pre>
 *  
 * 描述：[tzsc_fj]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-28 16:18:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("fjManager")
public class FjManagerImpl extends AbstractManagerImpl<String, Fj> implements FjManager {
	@Resource
	FjDao fjDao;

	@Resource
	ISysLogService sysLogService;
	@Resource
	SysFileManager sysFileManager;

	@Override
	protected Dao<String, Fj> getDao() {
		return fjDao;
	}

	/**
	 * 根据主键得到[tzsc_fj]信息
	 * 
	 * @param id
	 * @return Fj
	 * @author administrator
	 * @Create 2017-11-28 16:18:18
	 */
	@Override
	public Fj getFj(String id) {
		if (!StaticMethod.isNotEmpty(id)) {
			return null;
		}
		Fj fj = this.get(id);
		return fj;
	}

	/**
	 * 保存[tzsc_fj]对象
	 * 
	 * @param fj
	 * @return Fj
	 * @author administrator
	 * @Create 2017-11-28 16:18:18
	 */
	@Override
	public Fj saveFj(Fj fj) {
		// fj.setIsDeleted(ConstSystem.NO);
		if (!StaticMethod.isNotEmpty(this.getFj(fj.getId()))) {
			this.create(fj);
			sysLogService.addSysLog("新增tzsc_fj成功", Globals.Log_Type_CREATE, Globals.Log_Leavel_INFO, fj.getId());
		} else {
			this.update(fj);
			sysLogService.addSysLog("更新tzsc_fj成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO, fj.getId());
		}
		return fj;
	}

	/**
	 * 执行删除[tzsc_fj]对象
	 * 
	 * @param aryIds
	 *            <li>主键值，以逗号隔开</li>
	 * @return Fj
	 *         <li>如果是删除一条[tzsc_fj]数据，会返回删除的[tzsc_fj]信息。批量删除，会返回null</li>
	 * @author administrator
	 * @Create 2017-11-28 16:18:18
	 */
	@Override
	public Fj doRemove(String[] aryIds) {
		for (String id : aryIds) {
			Fj fj = this.getFj(id);
			// fj.setIsDeleted(ConstSystem.YES);
			update(fj);
			sysLogService.addSysLog("删除tzsc_fj成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO, fj.getId());
		}
		if (StaticMethod.isNotEmpty(aryIds) && aryIds.length == 1) {
			return this.getFj(aryIds[0]);
		}
		return null;
	}

	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Fj>
	 * @author administrator
	 * @Create 2017-11-28 16:18:18
	 */
	public PageList<Fj> fjListJson(Map<String, Object> paramMap) {
		QueryFilter queryFilter = getQueryFilter(paramMap);// 封装查询条件
		PageList<Fj> fjList = (PageList<Fj>) this.query(queryFilter);// 执行查询
		return fjList;
	}

	/**
	 * 附件保存
	 * @param Tzsq
	 * @return void
	 * @author wjk
	 * @Create 2017-11-29 15:10:55
	 */
	@Override
	public void saveFjByTzsq(Tzsq tzsq, QueryFilter queryFilter) {
		/*if (!StaticMethod.nullObject2String(tzsq.getCtbf()).equals("")) {
			this.dealCommonObject("ctbf", tzsq, "船体部分", tzsq.getCtbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getXzbf()).equals("")) {
			this.dealCommonObject("xzbf", tzsq, "舾装部分", tzsq.getXzbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getFhbf()).equals("")) {
			this.dealCommonObject("fhbf", tzsq, "防火、探火和灭火部分", tzsq.getFhbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getLjbf()).equals("")) {
			this.dealCommonObject("ljbf", tzsq, "轮机部分", tzsq.getLjbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getDqbf()).equals("")) {
			this.dealCommonObject("dqbf", tzsq, "电气部分", tzsq.getDqbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getDqwrzsbf()).equals("")) {
			this.dealCommonObject("dqwrzsbf", tzsq, "定期无人值班机器处所部分", tzsq.getDqwrzsbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getWxdtxjdhbf()).equals("")) {
			this.dealCommonObject("wxdtxjdhbf", tzsq, "无线电通信设备和航行设备部分", tzsq.getWxdtxjdhbf(), queryFilter,"");
		}
		if (!StaticMethod.nullObject2String(tzsq.getLcjldbf()).equals("")) {
			this.dealCommonObject("lcjldbf", tzsq, "冷藏及冷冻装置部分", tzsq.getLcjldbf(), queryFilter,"");
		}
		if(!StaticMethod.nullObject2String(tzsq.getStyjs()).equals("")) {
			this.dealCommonObject("styjs", tzsq, "审图意见书", tzsq.getStyjs(), queryFilter,tzsq.getStyjSjdw());
		}
		if(!StaticMethod.nullObject2String(tzsq.getDxmcpw()).equals("")) {
			this.dealCommonObject("dxmcpw", tzsq, "等效免除批文", tzsq.getDxmcpw(), queryFilter,tzsq.getDxmcpwSjdw());
		}
		if(!StaticMethod.nullObject2String(tzsq.getTzscbg()).equals("")) {
			this.dealCommonObject("tzscbg", tzsq, "图纸审查报告", tzsq.getTzscbg(), queryFilter,tzsq.getTzscbgSjdw());
		}
		if(!StaticMethod.nullObject2String(tzsq.getTzpzh()).equals("")) {
			this.dealCommonObject("tzpzh", tzsq, "图纸批准函", tzsq.getTzpzh(), queryFilter,tzsq.getTzpzhSjdw());
		}
		if(!StaticMethod.nullObject2String(tzsq.getScwth()).equals("")) {
			this.dealCommonObject("scwth", tzsq, "委托函", tzsq.getScwth(), queryFilter, tzsq.getScwthSjdw());
		}*/
			
	}

	/**
	 * 保存 图纸审查附件表 需要 考虑 修正 时 上传 多个 附件 sysFileId 为一个 字符串的问题
	 * 附件 保存 前 会将 存在的附件先进行删除  
	 * @param item
	 * @param tzsq
	 * @param fjmlflString
	 * @author wjk
	 * @Create Date 2017 下午6:12:33
	 */
	private void dealCommonObject(String item, Tzsq tzsq, String fjmlflString, String sysFileId,QueryFilter queryFilter,String sjdw) {
		Fj fj = new Fj();
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
	/*	fj.setFjmc(fjmc);
		fj.setFjmlflYw(item);
		fj.setFjmlfl(fjmlflString);*/
		fj.setFjId(sysFileId);
		//fj.setTzsqId(tzsq.getId());
		fj.setInsertTime(StaticMethod.getTimestamp());
		//fj.setSjdw(sjdw);
		this.saveFj(fj);
	}

	/**
	 * 插入之前 删除 附件表 数据
	 * 
	 * @param sysFileId
	 * @param queryFilter
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:45:39
	 */
	private void removeFj(String sysFileId, QueryFilter queryFilter) {
		queryFilter.addFilter("fj_id", "%" + sysFileId + "%", QueryOP.LIKE);
		List<Fj> fjList = this.query(queryFilter);
		if (null != fjList && fjList.size() > 0) {
			for (int i = 0; i < fjList.size(); i++) {
				Fj removeFj = fjList.get(i);
				this.remove(removeFj.getId());
			}
		}

	}

	/**
	 * 把附件的id放到图纸申请里
	 * 
	 * @param queryFilter
	 * @param tzsq
	 * @author wjk
	 * @Create Date 2017年11月30日08:48:50
	 */
	@Override
	public void dealFjBySqId(Tzsq tzsq, QueryFilter queryFilter) {
		queryFilter.addFilter("flow_key", tzsq.getId(), QueryOP.EQUAL);
		/*this.dealCtbf(queryFilter, tzsq);
		this.dealXzbf(queryFilter, tzsq);
		this.dealFhdq(queryFilter, tzsq);
		this.dealLjbf(queryFilter, tzsq);
		this.dealDqbf(queryFilter, tzsq);
		this.dealDqwrzsbf(queryFilter, tzsq);
		this.dealWxdtxjdhbf(queryFilter, tzsq);
		this.dealLcjldbf(queryFilter, tzsq);
		this.dealStyjs(queryFilter, tzsq);
		this.dealDxmcpw(queryFilter, tzsq);
		this.dealTzscbg(queryFilter, tzsq);
		this.dealTzpzh(queryFilter, tzsq);
		this.dealScwth(queryFilter, tzsq);*/
	}

	/**
	 * 委托函 附件 处理   
	 * @param queryFilter
	 * @param tzsq void
	 * @author wjk 
	 * @Create Date 2017 下午2:18:32
	 *//*
	private void dealScwth(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "scwth", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setScwth(fj.getFjId());
			//tzsq.setScwthSjdw(fj.getSjdw());
		}
		
	}

	*//**
	 * 图纸批准函    
	 * @param queryFilter
	 * @param tzsq void
	 * @author wjk 
	 * @Create Date 2017 上午11:32:33
	 *//*
	private void dealTzpzh(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "tzpzh", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setTzpzh(fj.getFjId());
			//tzsq.setTzpzhSjdw(fj.getSjdw());
		}
	}

	*//**
	 * 图纸审查报告    
	 * @param queryFilter
	 * @param tzsq void
	 * @author wjk 
	 * @Create Date 2017 上午11:29:24
	 *//*
	private void dealTzscbg(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "tzscbg", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setTzscbg(fj.getFjId());
			//tzsq.setTzscbgSjdw(fj.getSjdw());
		}
		
	}

	*//**
	 * 等效免除批文   
	 * @param queryFilter
	 * @param tzsq void
	 * @author wjk 
	 * @Create Date 2017 上午11:26:29
	 *//*
	private void dealDxmcpw(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "dxmcpw", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setDxmcpw(fj.getFjId());
			//tzsq.setDxmcpwSjdw(fj.getSjdw());
		}
	}

	*//**
	 * 审图意见书   
	 * @param queryFilter
	 * @param tzsq void
	 * @author wjk 
	 * @Create Date 2017 上午11:25:25
	 *//*
	private void dealStyjs(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "styjs", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setStyjs(fj.getFjId());
			//tzsq.setStyjSjdw(fj.getSjdw());
		}
		
	}

	*//**
	 * 冷藏冷冻设备
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:13:58
	 *//*
	private void dealLcjldbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "lcjldbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setLcjldbf(fj.getFjId());
		}

	}

	*//**
	 * 无线电通信设备和航行设备部分
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:13:02
	 *//*
	private void dealWxdtxjdhbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "wxdtxjdhbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setWxdtxjdhbf(fj.getFjId());
		}
	}

	*//**
	 * 定期无人值班机器处所部分
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:11:00
	 *//*
	private void dealDqwrzsbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "dqwrzsbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setDqwrzsbf(fj.getFjId());
		}

	}

	*//**
	 * 电气部分
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:09:00
	 *//*
	private void dealDqbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "dqbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setDqbf(fj.getFjId());
		}

	}

	*//**
	 * 轮机部分
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:07:47
	 *//*
	private void dealLjbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "ljbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setLjbf(fj.getFjId());
		}
	}

	*//**
	 * 防火部分 附件
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:06:18
	 *//*
	private void dealFhdq(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "fhbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setFhbf(fj.getFjId());
		}
	}

	*//**
	 * 舾装部分 附件
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:06:05
	 *//*
	private void dealXzbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "xzbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setXzbf(fj.getFjId());
		}
	}

	*//**
	 * 船体部分 附件
	 * 
	 * @param queryFilter
	 * @param tzsq
	 *            void
	 * @author wjk
	 * @Create Date 2017 上午9:05:49
	 *//*
	private void dealCtbf(QueryFilter queryFilter, Tzsq tzsq) {
		queryFilter.addFilter("fjmlfl_yw", "ctbf", QueryOP.EQUAL);
		List<Fj> list = this.query(queryFilter);
		if (null != list && list.size() == 1) {
			Fj fj = list.get(0);
			tzsq.setCtbf(fj.getFjId());
		}

	}*/
	/**
	 * 查看 附件
	 */
	@Override
	public List<Fj> getFjListByFlowKey(String flowkey){
		List fjList = fjDao.getFjListByFlowKey(flowkey);
		return  fjList;
	}
}
