
package com.sky.tzsc.gntzsc.controller;

import javax.annotation.Resource;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.core.web.RequestUtil;
import com.hotent.base.db.mybatis.domain.PageJson;
import com.hotent.base.db.mybatis.domain.PageList;
import com.sky.common.util.AjaxJson;
import com.sky.common.util.StaticMethod;
import com.sky.tzsc.gntzsc.persistence.manager.WlwjManager;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[tzsc_wlwj]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 16:34:38
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/wlwj")
public class WlwjController extends BaseController {
	
	@Resource
	WlwjManager wlwjManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@RequestMapping("wlwjListJson")
	@ResponseBody
	public Object wlwjListJson() throws Exception {
		PageList<Wlwj> wlwjList = (PageList<Wlwj>) wlwjManager.wlwjListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(wlwjList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[tzsc_wlwj]信息 
	 * @return Wlwj
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@RequestMapping("getWlwjJson")
	public @ResponseBody Wlwj getWlwjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Wlwj wlwj = wlwjManager.getWlwj(id);
		return wlwj;
	}
	
	/**
	 * 新增/修改[tzsc_wlwj]信息，跳转新增/修改[tzsc_wlwj]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@RequestMapping("wlwjEdit")
	public ModelAndView wlwjEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_wlwj]信息
	 * @param wlwj
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@RequestMapping("saveWlwj")
	@ResponseBody
	public AjaxJson saveWlwj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Wlwj wlwj = (Wlwj) getRequestObject(Wlwj .class);
			wlwj = wlwjManager.saveWlwj(wlwj);
			ajaxJson.setMsg("tzsc_wlwj操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_wlwj操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_wlwj]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 16:34:38
	 */
	@RequestMapping("doWlwjRemove")
	@ResponseBody
	public AjaxJson doWlwjRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> wlwj = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(wlwj.get("id"));
			wlwjManager.doRemove(aryIds);
			ajaxJson.setMsg("tzsc_wlwj删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_wlwj删除失败！");
		}
		return ajaxJson;
	}
}
