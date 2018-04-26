
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
import com.sky.tzsc.gntzsc.persistence.manager.SjdwManager;
import com.sky.tzsc.gntzsc.persistence.model.Sjdw;

/**
 * 
 * <pre> 
 * 描述：[图纸审查设计单位信息表]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-04-08 14:32:58
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/sjdw")
public class SjdwController extends BaseController {
	
	@Resource
	SjdwManager sjdwManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@RequestMapping("sjdwListJson")
	@ResponseBody
	public Object sjdwListJson() throws Exception {
		PageList<Sjdw> sjdwList = (PageList<Sjdw>) sjdwManager.sjdwListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(sjdwList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[图纸审查设计单位信息表]信息 
	 * @return Sjdw
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@RequestMapping("getSjdwJson")
	public @ResponseBody Sjdw getSjdwJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Sjdw sjdw = sjdwManager.getSjdw(id);
		return sjdw;
	}
	
	/**
	 * 新增/修改[图纸审查设计单位信息表]信息，跳转新增/修改[图纸审查设计单位信息表]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@RequestMapping("sjdwEdit")
	public ModelAndView sjdwEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[图纸审查设计单位信息表]信息
	 * @param sjdw
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@RequestMapping("saveSjdw")
	@ResponseBody
	public AjaxJson saveSjdw() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Sjdw sjdw = (Sjdw) getRequestObject(Sjdw .class);
			sjdw = sjdwManager.saveSjdw(sjdw);
			ajaxJson.setMsg("图纸审查设计单位信息表操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("图纸审查设计单位信息表操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[图纸审查设计单位信息表]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-04-08 14:32:58
	 */
	@RequestMapping("doSjdwRemove")
	@ResponseBody
	public AjaxJson doSjdwRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> sjdw = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(sjdw.get("id"));
			sjdwManager.doRemove(aryIds);
			ajaxJson.setMsg("图纸审查设计单位信息表删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("图纸审查设计单位信息表删除失败！");
		}
		return ajaxJson;
	}
}
