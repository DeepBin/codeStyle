
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
import com.sky.common.controller.BaseController;
import com.sky.tzsc.yytzsc.persistence.manager.ZgxxYyManager;
import com.sky.tzsc.yytzsc.persistence.model.ZgxxYy;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查整改信息]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-11 09:38:35
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/yytzsc/zgxxYy")
public class ZgxxYyController extends BaseController {
	
	@Resource
	ZgxxYyManager zgxxYyManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@RequestMapping("zgxxYyListJson")
	@ResponseBody
	public Object zgxxYyListJson() throws Exception {
		PageList<ZgxxYy> zgxxYyList = (PageList<ZgxxYy>) zgxxYyManager.zgxxYyListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(zgxxYyList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[远洋图纸审查整改信息]信息 
	 * @return ZgxxYy
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@RequestMapping("getZgxxYyJson")
	public @ResponseBody ZgxxYy getZgxxYyJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		ZgxxYy zgxxYy = zgxxYyManager.getZgxxYy(id);
		return zgxxYy;
	}
	
	/**
	 * 新增/修改[远洋图纸审查整改信息]信息，跳转新增/修改[远洋图纸审查整改信息]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@RequestMapping("zgxxYyEdit")
	public ModelAndView zgxxYyEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[远洋图纸审查整改信息]信息
	 * @param zgxxYy
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@RequestMapping("saveZgxxYy")
	@ResponseBody
	public AjaxJson saveZgxxYy() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			ZgxxYy zgxxYy = (ZgxxYy) getRequestObject(ZgxxYy .class);
			zgxxYy = zgxxYyManager.saveZgxxYy(zgxxYy);
			ajaxJson.setMsg("远洋图纸审查整改信息操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查整改信息操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[远洋图纸审查整改信息]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-11 09:38:35
	 */
	@RequestMapping("doZgxxYyRemove")
	@ResponseBody
	public AjaxJson doZgxxYyRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> zgxxYy = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(zgxxYy.get("id"));
			zgxxYyManager.doRemove(aryIds);
			ajaxJson.setMsg("远洋图纸审查整改信息删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查整改信息删除失败！");
		}
		return ajaxJson;
	}
}
