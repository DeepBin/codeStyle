package com.sky.tzsc.gntzsc.handler;

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
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.manager.ClshyjManager;
import com.sky.tzsc.gntzsc.persistence.manager.TzsqManager;
import com.sky.tzsc.gntzsc.persistence.manager.WlwjManager;
import com.sky.tzsc.gntzsc.persistence.manager.ZgxxManager;
import com.sky.tzsc.gntzsc.persistence.model.Clshyj;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;
import com.sky.tzsc.gntzsc.persistence.model.Zgxx;

@Service
public class GntzscHandler {
	
	@Resource
	TzsqManager tzsqManager;
	@Resource 
	ClshyjManager clshyjManager;
	@Resource
	WlwjManager wlwjManager;
	@Resource
	ZgxxManager zgxxManager;
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
		Tzsq tzsq = JSONObject.parseObject(data, Tzsq.class);
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		tzsqManager.saveTzsq(tzsq);
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
		clshyjManager.saveClshyj(clshyj);
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
		Tzsq tzsq = JSONObject.parseObject(data, Tzsq.class);
		Wlwj wlwj = JSONObject.parseObject(data, Wlwj.class);
		wlwj.setTzsqId(tzsq.getId());
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		wlwjManager.saveWlwjByTzsq(wlwj, queryFilter);
	}
	
	/**
	 * 保存往来文件   
	 * @param processCmd void
	 * @author wjk 
	 * @Create Date 2017 下午2:18:33
	 */
	public void saveClsc(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data))
			return;
		//Clshyj clshyj = JSONObject.parseObject(data, Clshyj.class);
		Tzsq tzsq = JSONObject.parseObject(data, Tzsq.class);
		Wlwj wlwj = JSONObject.parseObject(data, Wlwj.class);
		wlwj.setTzsqId(tzsq.getId());
		if (processCmd.getActionName().equals("backToStart") || processCmd.getActionName().equals("reject")) {
			return;
		}
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		wlwjManager.saveWlwjByTzsq(wlwj, queryFilter);
		tzsqManager.update(tzsq);
		//clshyjManager.saveClshyj(clshyj);
	}
	/**
	 * 保存整改结论和申请信息。
	 * @param processCmd void
	 * @author wjk 
	 * @Create Date 2018 上午9:52:31
	 */
	public void saveZgxx(ActionCmd processCmd) {
		String data = processCmd.getBusData();
		if (StringUtil.isEmpty(data)) return;
		Tzsq tzsq = JSONObject.parseObject(data, Tzsq.class);
		Zgxx zgxx = JSONObject.parseObject(data, Zgxx.class);
		Wlwj wlwj = JSONObject.parseObject(data, Wlwj.class);
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		QueryFilter queryFilter = tzsqManager.getQueryFilter(StaticMethod.changeRequestParamToMap(request));
		wlwjManager.saveWlwjByTzsq(wlwj, queryFilter);
		tzsqManager.update(tzsq);
		zgxx.setId(null);
		zgxx.setSqId(tzsq.getId());
		zgxx.setInsertUser(ContextUtil.getCurrentSysUser().getUserName());
		zgxx.setInsertUserId(ContextUtil.getCurrentSysUser().getUserId());
		zgxx.setInsertLoginId(ContextUtil.getCurrentLoginId());
		zgxx.setInsertDeptId(ContextUtil.getCurrentDeptId());
		zgxx.setScbj(0);
		zgxxManager.create(zgxx);
	}
	/**
	 * 保存办结意见    
	 * @param processCmd void
	 * @author wjk 
	 * @Create Date 2018 上午11:17:59
	 */
	public  void saveBjxx(ActionCmd processCmd) {
		
	}
}
