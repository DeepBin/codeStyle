
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
import com.sky.tzsc.gntzsc.persistence.manager.BzcxManager;
import com.sky.tzsc.gntzsc.persistence.model.Bzcx;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;
import com.sky.common.controller.BaseController;

/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-20 15:37:20
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/bzcx")
public class BzcxController extends BaseController {
	
	@Resource
	BzcxManager bzcxManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@RequestMapping("bzcxListJson")
	@ResponseBody
	public Object bzcxListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		paramMap.put("Q^tjbj^S", 0); //查询 还未提交的数据
		PageList<Bzcx> bzcxList = (PageList<Bzcx>) bzcxManager.bzcxListJson(paramMap);// 执行查询
		PageJson pageJson = new PageJson(bzcxList);
		return pageJson;
	}
	/**
	 * 通过ajax方式查询[tzsc_bzcx]信息 
	 * @return Bzcx
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@RequestMapping("getBzcxJson")
	public @ResponseBody Bzcx getBzcxJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Bzcx bzcx = bzcxManager.getBzcx(id);
		return bzcx;
	}
	
	/**
	 * 新增/修改标准船型信息，跳转新增/修改标准船型页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@RequestMapping("bzcxEdit")
	public ModelAndView bzcxEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[tzsc_bzcx]信息
	 * @param bzcx
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@RequestMapping("saveBzcx")
	@ResponseBody
	public AjaxJson saveBzcx() {
		AjaxJson ajaxJson = this.getAjaxJson();
		String  tjflag  = RequestUtil.getString(this.getRequest(), "tjflag"); //提交标记  0  为保存 1 位 提交
		try {
			Bzcx bzcx = (Bzcx) getRequestObject(Bzcx .class);
			bzcx.setTjbj(tjflag);
			bzcx = bzcxManager.saveBzcx(bzcx);
			ajaxJson.setMsg("标准船型操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("标准船型操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[tzsc_bzcx]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-11-20 15:37:20
	 */
	@RequestMapping("doBzcxRemove")
	@ResponseBody
	public AjaxJson doBzcxRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> bzcx = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(bzcx.get("id"));
			bzcxManager.doRemove(aryIds);
			ajaxJson.setMsg("标准船型删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("标准船型删除失败！");
		}
		return ajaxJson;
	}
	
	/**
	 * 标准船型查询    
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午4:12:17
	 */
	@RequestMapping("bzcxCxList")
	public ModelAndView bzcxCxList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest()); 
		paramMap.put("Q^tjbj^S", 1); //查询 已提交的数据  
		//后续 需要把申请地区加进来
		PageList<Bzcx> bzcxList = (PageList<Bzcx>) bzcxManager.bzcxListJson(paramMap);
		PageJson pageJson = new PageJson(bzcxList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	
	/**
	 * 查询标准船型    
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 上午11:05:49
	 */
	@RequestMapping("getBzcxListByDistrictId")
	public ModelAndView getBzcxListByDistrictId() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		String districtId = RequestUtil.getString(this.getRequest(), "districtId");  
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest()); 
		paramMap.put("Q^tjbj^S", 1); //查询 已提交的数据
		paramMap.put("Q^ssdq_id^S", districtId);
		PageList<Bzcx> bzcxList = (PageList<Bzcx>) bzcxManager.bzcxListJson(paramMap);
		PageJson pageJson = new PageJson(bzcxList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	
	/**
	 * 标准船型数据维护列表方法    
	 * @return
	 * @throws Exception Object
	 * @author wjk 
	 * @Create Date 2018 下午4:08:42
	 */
	@RequestMapping("bzcxWhListJson")
	@ResponseBody
	public Object bzcxWhListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		paramMap.put("Q^tjbj^S", 1); //查询 还未提交的数据
		PageList<Bzcx> bzcxList = (PageList<Bzcx>) bzcxManager.bzcxListJson(paramMap);// 执行查询
		PageJson pageJson = new PageJson(bzcxList);
		return pageJson;
	}
}
