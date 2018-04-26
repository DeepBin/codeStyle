
package com.sky.tzsc.tzscStzjgl.controller;

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
import com.sky.tzsc.tzscStzjgl.persistence.manager.TzscStzjglManager;
import com.sky.tzsc.tzscStzjgl.persistence.model.TzscStzjgl;

/**
 * 
 * <pre> 
 * 描述：[审图专家管理]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-16 17:07:42
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/tzscStzjgl")
public class TzscStzjglController extends BaseController {
	
	@Resource
	TzscStzjglManager tzscStzjglManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@RequestMapping("tzscStzjglListJson")
	@ResponseBody
	public Object tzscStzjglListJson() throws Exception {
		PageList<TzscStzjgl> tzscStzjglList = (PageList<TzscStzjgl>) tzscStzjglManager.tzscStzjglListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(tzscStzjglList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[审图专家管理]信息 
	 * @return TzscStzjgl
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@RequestMapping("getTzscStzjglJson")
	public @ResponseBody TzscStzjgl getTzscStzjglJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		TzscStzjgl tzscStzjgl = tzscStzjglManager.getTzscStzjgl(id);
		return tzscStzjgl;
	}
	
	/**
	 * 新增/修改[审图专家管理]信息，跳转新增/修改[审图专家管理]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@RequestMapping("tzscStzjglEdit")
	public ModelAndView tzscStzjglEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[审图专家管理]信息
	 * @param tzscStzjgl
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@RequestMapping("saveTzscStzjgl")
	@ResponseBody
	public AjaxJson saveTzscStzjgl() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			TzscStzjgl tzscStzjgl = (TzscStzjgl) getRequestObject(TzscStzjgl .class);
			tzscStzjgl = tzscStzjglManager.saveTzscStzjgl(tzscStzjgl);
			ajaxJson.setMsg("审图专家管理操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("审图专家管理操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[审图专家管理]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-16 17:07:42
	 */
	@RequestMapping("doTzscStzjglRemove")
	@ResponseBody
	public AjaxJson doTzscStzjglRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> tzscStzjgl = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(tzscStzjgl.get("id"));
			tzscStzjglManager.doRemove(aryIds);
			ajaxJson.setMsg("审图专家管理删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("审图专家管理删除失败！");
		}
		return ajaxJson;
	}
}
