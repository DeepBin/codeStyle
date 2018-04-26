
package com.sky.tzsc.yytzsc.controller;

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
import com.sky.tzsc.yytzsc.persistence.manager.YyWlwjManager;
import com.sky.tzsc.yytzsc.persistence.model.YyWlwj;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查往来文件表]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-08 10:08:08
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/yytzsc/yyWlwj")
public class YyWlwjController extends BaseController {
	
	@Resource
	YyWlwjManager yyWlwjManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	@RequestMapping("yyWlwjListJson")
	@ResponseBody
	public Object yyWlwjListJson() throws Exception {
		PageList<YyWlwj> yyWlwjList = (PageList<YyWlwj>) yyWlwjManager.yyWlwjListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(yyWlwjList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[远洋图纸审查往来文件表]信息 
	 * @return YyWlwj
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	@RequestMapping("getYyWlwjJson")
	public @ResponseBody YyWlwj getYyWlwjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		YyWlwj yyWlwj = yyWlwjManager.getYyWlwj(id);
		return yyWlwj;
	}
	
	/**
	 * 新增/修改[远洋图纸审查往来文件表]信息，跳转新增/修改[远洋图纸审查往来文件表]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	@RequestMapping("yyWlwjEdit")
	public ModelAndView yyWlwjEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[远洋图纸审查往来文件表]信息
	 * @param yyWlwj
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	@RequestMapping("saveYyWlwj")
	@ResponseBody
	public AjaxJson saveYyWlwj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			YyWlwj yyWlwj = (YyWlwj) getRequestObject(YyWlwj .class);
			yyWlwj = yyWlwjManager.saveYyWlwj(yyWlwj);
			ajaxJson.setMsg("远洋图纸审查往来文件表操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查往来文件表操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[远洋图纸审查往来文件表]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-08 10:08:08
	 */
	@RequestMapping("doYyWlwjRemove")
	@ResponseBody
	public AjaxJson doYyWlwjRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> yyWlwj = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(yyWlwj.get("id"));
			yyWlwjManager.doRemove(aryIds);
			ajaxJson.setMsg("远洋图纸审查往来文件表删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查往来文件表删除失败！");
		}
		return ajaxJson;
	}
}
