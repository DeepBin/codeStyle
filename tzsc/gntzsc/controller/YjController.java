
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
import com.sky.tzsc.gntzsc.persistence.manager.YjManager;
import com.sky.tzsc.gntzsc.persistence.model.Yj;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[tzsc_yj]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-29 13:41:43
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/yj")
public class YjController extends BaseController {
	
	@Resource
	YjManager yjManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@RequestMapping("yjListJson")
	@ResponseBody
	public Object yjListJson() throws Exception {
		PageList<Yj> yjList = (PageList<Yj>) yjManager.yjListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(yjList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[tzsc_yj]信息 
	 * @return Yj
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@RequestMapping("getYjJson")
	public @ResponseBody Yj getYjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Yj yj = yjManager.getYj(id);
		return yj;
	}
	
	/**
	 * 新增/修改[tzsc_yj]信息，跳转新增/修改[tzsc_yj]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@RequestMapping("yjEdit")
	public ModelAndView yjEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_yj]信息
	 * @param yj
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@RequestMapping("saveYj")
	@ResponseBody
	public AjaxJson saveYj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Yj yj = (Yj) getRequestObject(Yj .class);
			yj = yjManager.saveYj(yj);
			ajaxJson.setMsg("tzsc_yj操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_yj操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_yj]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-29 13:41:43
	 */
	@RequestMapping("doYjRemove")
	@ResponseBody
	public AjaxJson doYjRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> yj = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(yj.get("id"));
			yjManager.doRemove(aryIds);
			ajaxJson.setMsg("tzsc_yj删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_yj删除失败！");
		}
		return ajaxJson;
	}
}
