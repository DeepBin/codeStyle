
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
import com.sky.common.controller.BaseController;
import com.sky.tzsc.gntzsc.persistence.manager.ZgxxManager;
import com.sky.tzsc.gntzsc.persistence.model.Zgxx;

/**
 * 
 * <pre> 
 * 描述：[国内图纸审查整改信息]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-08 10:10:23
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/gntzsc/Zgxx")
public class zgxxController extends BaseController {
	
	@Resource
	ZgxxManager zgxxManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@RequestMapping("zgxxListJson")
	@ResponseBody
	public Object zgxxListJson() throws Exception {
		PageList<Zgxx> zgxxList = (PageList<Zgxx>) zgxxManager.zgxxListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(zgxxList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[国内图纸审查整改信息]信息 
	 * @return zgxx
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@RequestMapping("getzgxxJson")
	public @ResponseBody Zgxx getzgxxJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Zgxx zgxx = zgxxManager.getzgxx(id);
		return zgxx;
	}
	
	/**
	 * 新增/修改[国内图纸审查整改信息]信息，跳转新增/修改[国内图纸审查整改信息]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@RequestMapping("zgxxEdit")
	public ModelAndView zgxxEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[国内图纸审查整改信息]信息
	 * @param Zgxx
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@RequestMapping("savezgxx")
	@ResponseBody
	public AjaxJson savezgxx() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Zgxx zgxx = (Zgxx) getRequestObject(Zgxx .class);
			zgxx = zgxxManager.savezgxx(zgxx);
			ajaxJson.setMsg("国内图纸审查整改信息操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("国内图纸审查整改信息操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[国内图纸审查整改信息]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-08 10:10:23
	 */
	@RequestMapping("dozgxxRemove")
	@ResponseBody
	public AjaxJson dozgxxRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> zgxx = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(zgxx.get("id"));
			zgxxManager.doRemove(aryIds);
			ajaxJson.setMsg("国内图纸审查整改信息删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("国内图纸审查整改信息删除失败！");
		}
		return ajaxJson;
	}
}
