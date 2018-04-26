package com.sky.tzsc.yytzsc.handler;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.core.web.RequestContext;
import com.hotent.bpmx.api.cmd.ActionCmd;
import com.sky.common.context.ContextUtil;
import com.sky.common.controller.BaseController;
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;
import com.sky.tzsc.gntzsc.persistence.model.Zgxx;
import com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager;
import com.sky.tzsc.yytzsc.persistence.manager.YyClshyjManager;
import com.sky.tzsc.yytzsc.persistence.manager.YyWlwjManager;
import com.sky.tzsc.yytzsc.persistence.manager.ZgxxYyManager;
import com.sky.tzsc.yytzsc.persistence.model.Clshyj;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;
import com.sky.tzsc.yytzsc.persistence.model.YyWlwj;
import com.sky.tzsc.yytzsc.persistence.model.ZgxxYy;

@Service
public class YytzscHandler {
	
	@Resource
	TzscsqManager tzscsqManager;
	@Resource
	YyWlwjManager yyWlwjManager;
	@Resource
	YyClshyjManager yyClshyjManager;
	@Resource
	ZgxxYyManager zgxxYyManager;
	/**
	 * 示例  不进行调用    
	 * @param processCmd
	 * @throws ParseException void
	 * @author wjk 
	 * @Create Date 2017 下午3:48:57
	 */
	public void saveScxx(ActionCmd processCmd) throws ParseException {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data))
			return;
		Tzscsq tzsq = JSONObject.parseObject(data, Tzscsq.class);
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		tzscsqManager.saveTzscsq(tzsq);
	}
	/**
	 * 保存材料审核意见  
	 * @param processCmd void
	 * @author wjk 
	 * @Create Date 2017 下午3:48:50
	 */
	public void saveClshyj(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data))
			return;
		Clshyj clshyj = JSONObject.parseObject(data, Clshyj.class);
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		yyClshyjManager.saveClshyj(clshyj);
	}
	/**
	 * 上传 委托审图 书    
	 * @param processCmd void
	 * @author wjk 
	 * @Create Date 2017 上午9:17:31
	 */
	public void saveLdspWtst(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data))
			return;
		//Clshyj clshyj = JSONObject.parseObject(data, Clshyj.class);
		Tzscsq tzsq = JSONObject.parseObject(data, Tzscsq.class);
		YyWlwj yyWlwj = JSONObject.parseObject(data, YyWlwj.class);
		yyWlwj.setTzsqId(tzsq.getId());
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzscsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		yyWlwjManager.saveWlwjByTzsq(yyWlwj, queryFilter);
		//clshyjManager.saveClshyj(clshyj);
	}
	
	/**
	 * saveWlwjByTzsq
	 * 保存往来文件   
	 * @param processCmd void
	 * @author wjk 
	 * @Create Date 2017 下午2:18:33
	 */
	public void saveBjblWlwj(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data))
			return;
		Tzscsq tzsq = JSONObject.parseObject(data, Tzscsq.class);
		YyWlwj yyWlwj = JSONObject.parseObject(data, YyWlwj.class);
		yyWlwj.setTzsqId(tzsq.getId());
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzscsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		yyWlwjManager.saveWlwjByTzsq(yyWlwj, queryFilter);
		//clshyjManager.saveClshyj(clshyj);
	}
	/**
	 * 图纸审查中 材料上传 处理    
	 *  void
	 * @author wjk 
	 * @Create Date 2017 下午2:43:23
	 */
	public void saveClsc(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data))
			return;
		Tzscsq tzsq = JSONObject.parseObject(data, Tzscsq.class);
		YyWlwj yyWlwj = JSONObject.parseObject(data, YyWlwj.class);
		yyWlwj.setTzsqId(tzsq.getId());
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzscsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		yyWlwjManager.saveWlwjByTzsq(yyWlwj, queryFilter);
		tzscsqManager.update(tzsq);
	}
	
	public void savaZgxx(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data)) return;
		Tzscsq tzscsq = JSONObject.parseObject(data, Tzscsq.class);
		ZgxxYy zgxxYy = JSONObject.parseObject(data, ZgxxYy.class);
		YyWlwj yyWlwj = JSONObject.parseObject(data, YyWlwj.class);
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzscsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		yyWlwjManager.saveWlwjByTzsq(yyWlwj, queryFilter);
		tzscsqManager.update(tzscsq);
		zgxxYy.setSqId(tzscsq.getId());
		zgxxYy.setId(null);
		zgxxYy.setInsertUser(ContextUtil.getCurrentSysUser().getUserName());
		zgxxYy.setScbj(0);
		zgxxYyManager.create(zgxxYy);
	}
}
