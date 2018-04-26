
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
import com.sky.common.context.ContextUtil;
import com.sky.common.controller.BaseController;
import com.sky.tzsc.gntzsc.persistence.manager.BzcxWjglManager;
import com.sky.tzsc.gntzsc.persistence.model.BzcxWjgl;

/**
 * 
 * <pre> 
 * 描述：[标准船型文件管理]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-25 09:17:50
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/bzcx/bzcxWjgl")
public class BzcxWjglController extends BaseController {
	
	@Resource
	BzcxWjglManager bzcxWjglManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@RequestMapping("bzcxWjglListJson")
	@ResponseBody
	public Object bzcxWjglListJson() throws Exception {
		PageList<BzcxWjgl> bzcxWjglList = (PageList<BzcxWjgl>) bzcxWjglManager.bzcxWjglListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(bzcxWjglList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[标准船型文件管理]信息 
	 * @return BzcxWjgl
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@RequestMapping("getBzcxWjglJson")
	public @ResponseBody BzcxWjgl getBzcxWjglJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		BzcxWjgl bzcxWjgl = bzcxWjglManager.getBzcxWjgl(id);
		return bzcxWjgl;
	}
	
	/**
	 * 新增/修改[标准船型文件管理]信息，跳转新增/修改[标准船型文件管理]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@RequestMapping("bzcxWjglEdit")
	public ModelAndView bzcxWjglEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[标准船型文件管理]信息
	 * @param bzcxWjgl
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@RequestMapping("saveBzcxWjgl")
	@ResponseBody
	public AjaxJson saveBzcxWjgl() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			BzcxWjgl bzcxWjgl = (BzcxWjgl) getRequestObject(BzcxWjgl .class);
			bzcxWjgl.setInsertTime(StaticMethod.getTimestamp());
			bzcxWjgl.setInsertDeptId(ContextUtil.getCurrentDeptId());
			bzcxWjgl.setInsertLoginId(ContextUtil.getCurrentLoginId());
			bzcxWjgl.setInsertUser(ContextUtil.getCurrentLogin().getLoginUserId());
			bzcxWjgl = bzcxWjglManager.saveBzcxWjgl(bzcxWjgl);
			ajaxJson.setMsg("标准船型文件管理操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("标准船型文件管理操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[标准船型文件管理]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-01-25 09:17:50
	 */
	@RequestMapping("doBzcxWjglRemove")
	@ResponseBody
	public AjaxJson doBzcxWjglRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> bzcxWjgl = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(bzcxWjgl.get("id"));
			bzcxWjglManager.doRemove(aryIds);
			ajaxJson.setMsg("标准船型文件管理删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("标准船型文件管理删除失败！");
		}
		return ajaxJson;
	}
}
