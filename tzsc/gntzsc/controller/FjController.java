
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
import com.sky.tzsc.gntzsc.persistence.manager.FjManager;
import com.sky.tzsc.gntzsc.persistence.model.Fj;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fj]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-28 16:18:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/fj")
public class FjController extends BaseController {
	
	@Resource
	FjManager fjManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	@RequestMapping("fjListJson")
	@ResponseBody
	public Object fjListJson() throws Exception {
		PageList<Fj> fjList = (PageList<Fj>) fjManager.fjListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(fjList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[tzsc_fj]信息 
	 * @return Fj
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	@RequestMapping("getFjJson")
	public @ResponseBody Fj getFjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Fj fj = fjManager.getFj(id);
		return fj;
	}
	
	/**
	 * 新增/修改[tzsc_fj]信息，跳转新增/修改[tzsc_fj]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	@RequestMapping("fjEdit")
	public ModelAndView fjEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_fj]信息
	 * @param fj
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	@RequestMapping("saveFj")
	@ResponseBody
	public AjaxJson saveFj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Fj fj = (Fj) getRequestObject(Fj .class);
			fj = fjManager.saveFj(fj);
			ajaxJson.setMsg("tzsc_fj操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_fj操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_fj]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-28 16:18:18
	 */
	@RequestMapping("doFjRemove")
	@ResponseBody
	public AjaxJson doFjRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> fj = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(fj.get("id"));
			fjManager.doRemove(aryIds);
			ajaxJson.setMsg("tzsc_fj删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_fj删除失败！");
		}
		return ajaxJson;
	}
}
