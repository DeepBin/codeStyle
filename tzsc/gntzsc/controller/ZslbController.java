package com.sky.tzsc.gntzsc.controller;



import javax.annotation.Resource;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.base.core.web.RequestUtil;
import com.hotent.base.db.mybatis.domain.PageJson;
import com.hotent.base.db.mybatis.domain.PageList;
import com.sky.common.util.AjaxJson;
import com.sky.common.util.StaticMethod;
import com.sky.common.controller.BaseController;
import com.sky.tzsc.gntzsc.persistence.manager.ZslbManager;
import com.sky.tzsc.gntzsc.persistence.model.Zslb;

/**
 * 
 * <pre> 
 * 描述：[tzsc_zslb]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-27 15:36:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/gntzsc/Zslb")
public class ZslbController extends BaseController {
	
	@Resource
	ZslbManager zslbManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	@RequestMapping("zslbListJson")
	@ResponseBody
	public Object zslbListJson() throws Exception {
		PageList<Zslb> zslbList = (PageList<Zslb>) zslbManager.zslbListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(zslbList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[tzsc_zslb]信息 
	 * @return Zslb
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	@RequestMapping("getZslbJson")
	public @ResponseBody Zslb getZslbJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Zslb zslb = zslbManager.getZslb(id);
		return zslb;
	}
	
	/**
	 * 新增/修改[tzsc_zslb]信息，跳转新增/修改[tzsc_zslb]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	@RequestMapping("zslbEdit")
	public ModelAndView zslbEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_zslb]信息
	 * @param zslb
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	@RequestMapping("saveZslb")
	@ResponseBody
	public AjaxJson saveZslb() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Zslb zslb = (Zslb) getRequestObject(Zslb .class);
			zslb = zslbManager.saveZslb(zslb);
			ajaxJson.setMsg("tzsc_zslb操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_zslb操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_zslb]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	@RequestMapping("doZslbRemove")
	@ResponseBody
	public AjaxJson doZslbRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> zslb = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(zslb.get("id"));
			zslbManager.doRemove(aryIds);
			ajaxJson.setMsg("tzsc_zslb删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("tzsc_zslb删除失败！");
		}
		return ajaxJson;
	}
}
