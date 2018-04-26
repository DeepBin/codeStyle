
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
import com.sky.tzsc.gntzsc.persistence.manager.ClshyjManager;
import com.sky.tzsc.gntzsc.persistence.model.Clshyj;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[材料审核意见]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-01 10:33:48
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/clshyj")
public class ClshyjController extends BaseController {
	
	@Resource
	ClshyjManager clshyjManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@RequestMapping("clshyjListJson")
	@ResponseBody
	public Object clshyjListJson() throws Exception {
		PageList<Clshyj> clshyjList = (PageList<Clshyj>) clshyjManager.clshyjListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(clshyjList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[材料审核意见]信息 
	 * @return Clshyj
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@RequestMapping("getClshyjJson")
	public @ResponseBody Clshyj getClshyjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Clshyj clshyj = clshyjManager.getClshyj(id);
		return clshyj;
	}
	
	/**
	 * 新增/修改[材料审核意见]信息，跳转新增/修改[材料审核意见]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@RequestMapping("clshyjEdit")
	public ModelAndView clshyjEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[材料审核意见]信息
	 * @param clshyj
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@RequestMapping("saveClshyj")
	@ResponseBody
	public AjaxJson saveClshyj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj .class);
			clshyj = clshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("材料审核意见操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("材料审核意见操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[材料审核意见]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-01 10:33:48
	 */
	@RequestMapping("doClshyjRemove")
	@ResponseBody
	public AjaxJson doClshyjRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> clshyj = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(clshyj.get("id"));
			clshyjManager.doRemove(aryIds);
			ajaxJson.setMsg("材料审核意见删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("材料审核意见删除失败！");
		}
		return ajaxJson;
	}
}
