
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
import com.sky.tzsc.gntzsc.persistence.manager.CwgjzbpzsManager;
import com.sky.tzsc.gntzsc.persistence.model.Cwgjzbpzs;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[tzsc_cwgjzbpzs]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 14:39:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/cwgjzbpzs")
public class CwgjzbpzsController extends BaseController {
	
	@Resource
	CwgjzbpzsManager cwgjzbpzsManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@RequestMapping("cwgjzbpzsListJson")
	@ResponseBody
	public Object cwgjzbpzsListJson() throws Exception {
		PageList<Cwgjzbpzs> cwgjzbpzsList = (PageList<Cwgjzbpzs>) cwgjzbpzsManager.cwgjzbpzsListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(cwgjzbpzsList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[tzsc_cwgjzbpzs]信息 
	 * @return Cwgjzbpzs
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@RequestMapping("getCwgjzbpzsJson")
	public @ResponseBody Cwgjzbpzs getCwgjzbpzsJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Cwgjzbpzs cwgjzbpzs = cwgjzbpzsManager.getCwgjzbpzs(id);
		return cwgjzbpzs;
	}
	
	/**
	 * 新增/修改[tzsc_cwgjzbpzs]信息，跳转新增/修改[tzsc_cwgjzbpzs]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@RequestMapping("cwgjzbpzsEdit")
	public ModelAndView cwgjzbpzsEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_cwgjzbpzs]信息
	 * @param cwgjzbpzs
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@RequestMapping("saveCwgjzbpzs")
	@ResponseBody
	public AjaxJson saveCwgjzbpzs() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Cwgjzbpzs cwgjzbpzs = (Cwgjzbpzs) getRequestObject(Cwgjzbpzs .class);
			cwgjzbpzs = cwgjzbpzsManager.saveCwgjzbpzs(cwgjzbpzs);
			ajaxJson.setMsg("tzsc_cwgjzbpzs操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_cwgjzbpzs操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_cwgjzbpzs]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-24 14:39:22
	 */
	@RequestMapping("doCwgjzbpzsRemove")
	@ResponseBody
	public AjaxJson doCwgjzbpzsRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> cwgjzbpzs = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(cwgjzbpzs.get("id"));
			cwgjzbpzsManager.doRemove(aryIds);
			ajaxJson.setMsg("tzsc_cwgjzbpzs删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_cwgjzbpzs删除失败！");
		}
		return ajaxJson;
	}
}
