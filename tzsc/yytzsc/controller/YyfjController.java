
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
import com.sky.tzsc.yytzsc.persistence.manager.YyfjManager;
import com.sky.tzsc.yytzsc.persistence.model.Yyfj;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查附件表]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 14:47:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/yytzsc/yyfj")
public class YyfjController extends BaseController {
	
	@Resource
	YyfjManager yyfjManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@RequestMapping("yyfjListJson")
	@ResponseBody
	public Object yyfjListJson() throws Exception {
		PageList<Yyfj> yyfjList = (PageList<Yyfj>) yyfjManager.yyfjListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(yyfjList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[远洋图纸审查附件表]信息 
	 * @return Yyfj
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@RequestMapping("getYyfjJson")
	public @ResponseBody Yyfj getYyfjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Yyfj yyfj = yyfjManager.getYyfj(id);
		return yyfj;
	}
	
	/**
	 * 新增/修改[远洋图纸审查附件表]信息，跳转新增/修改[远洋图纸审查附件表]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@RequestMapping("yyfjEdit")
	public ModelAndView yyfjEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[远洋图纸审查附件表]信息
	 * @param yyfj
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@RequestMapping("saveYyfj")
	@ResponseBody
	public AjaxJson saveYyfj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Yyfj yyfj = (Yyfj) getRequestObject(Yyfj .class);
			yyfj = yyfjManager.saveYyfj(yyfj);
			ajaxJson.setMsg("远洋图纸审查附件表操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查附件表操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[远洋图纸审查附件表]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 14:47:11
	 */
	@RequestMapping("doYyfjRemove")
	@ResponseBody
	public AjaxJson doYyfjRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> yyfj = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(yyfj.get("id"));
			yyfjManager.doRemove(aryIds);
			ajaxJson.setMsg("远洋图纸审查附件表删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查附件表删除失败！");
		}
		return ajaxJson;
	}
}
