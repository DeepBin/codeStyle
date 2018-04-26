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
import com.sky.tzsc.gntzsc.persistence.manager.FlManager;
import com.sky.tzsc.gntzsc.persistence.model.Fl;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fl]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-26 16:08:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/gntzsc/Fl")
public class FlController extends BaseController {
	
	@Resource
	FlManager flManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@RequestMapping("flListJson")
	@ResponseBody
	public Object flListJson() throws Exception {
		PageList<Fl> flList = (PageList<Fl>) flManager.flListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(flList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[tzsc_fl]信息 
	 * @return Fl
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@RequestMapping("getFlJson")
	public @ResponseBody Fl getFlJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Fl fl = flManager.getFl(id);
		return fl;
	}
	
	/**
	 * 新增/修改[tzsc_fl]信息，跳转新增/修改[tzsc_fl]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@RequestMapping("flEdit")
	public ModelAndView flEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_fl]信息
	 * @param fl
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@RequestMapping("saveFl")
	@ResponseBody
	public AjaxJson saveFl() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Fl fl = (Fl) getRequestObject(Fl .class);
			fl = flManager.saveFl(fl);
			ajaxJson.setMsg("tzsc_fl操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_fl操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_fl]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-26 16:08:22
	 */
	@RequestMapping("doFlRemove")
	@ResponseBody
	public AjaxJson doFlRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> fl = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(fl.get("id"));
			flManager.doRemove(aryIds);
			ajaxJson.setMsg("tzsc_fl删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_fl删除失败！");
		}
		return ajaxJson;
	}
}
