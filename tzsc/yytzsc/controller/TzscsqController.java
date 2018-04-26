
package com.sky.tzsc.yytzsc.controller;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.core.web.RequestUtil;
import com.hotent.base.db.mybatis.domain.PageJson;
import com.hotent.base.db.mybatis.domain.PageList;
import com.sky.common.util.AjaxJson;
import com.sky.common.util.StaticMethod;
import com.sky.flow.task.persistence.manager.FlowTaskManager;
import com.sky.common.controller.BaseController;
import com.sky.tzsc.gntzsc.persistence.manager.CwgjzbpzsManager;
import com.sky.tzsc.gntzsc.persistence.model.Cwgjzbpzs;
import com.sky.tzsc.util.PrintConfig;
import com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager;
import com.sky.tzsc.yytzsc.persistence.manager.YyClshyjManager;
import com.sky.tzsc.yytzsc.persistence.manager.YyWlwjManager;
import com.sky.tzsc.yytzsc.persistence.manager.YyfjManager;
import com.sky.tzsc.yytzsc.persistence.model.Clshyj;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;
import com.sky.tzsc.yytzsc.persistence.model.YyWlwj;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查申请表]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:28:56
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/yytzsc/tzsq")
public class TzscsqController extends BaseController {
	
	@Resource
	TzscsqManager tzscsqManager;
	@Resource
	CwgjzbpzsManager cwgjzbpzsManager;
	@Resource
	YyfjManager yyfjManager;
	
	@Resource
	YyWlwjManager yyWlwjManager;
	@Resource
	YyClshyjManager yyClshyjManager;
	@Resource
	FlowTaskManager flowTaskManager;
	
	/**
	 * 执行查询 ,系统自动封装查询条件 
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@RequestMapping("tzscsqListJson")
	@ResponseBody
	public Object tzscsqListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		paramMap.put("Q^tjzt^S", 1);
		PageList<Tzscsq> tzscsqList = (PageList<Tzscsq>) tzscsqManager.tzscsqListJson(paramMap);// 执行查询
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	
	/**
	 * 通过ajax方式查询[远洋图纸审查申请表]信息 
	 * @return Tzscsq
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@RequestMapping("getTzscsqJson")
	public @ResponseBody Tzscsq getTzscsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 新增/修改[远洋图纸审查申请表]信息，跳转新增/修改[远洋图纸审查申请表]页面  
	 * @return ModelAndView
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@RequestMapping("tzscsqEdit")
	public ModelAndView tzscsqEdit(String id) throws Exception {		
		ModelAndView modelAndView = this.getAutoView();
		if(!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}
	
	/**
	 * 执行新增或者更新[远洋图纸审查申请表]信息
	 * @param tzscsq
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@RequestMapping("saveTzscsq")
	@ResponseBody
	public AjaxJson saveTzscsq() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Tzscsq tzscsq = (Tzscsq) getRequestObject(Tzscsq .class);
			String draftFlag = RequestUtil.getString(this.getRequest(), "draft");
			tzscsq.setTjzt(Integer.valueOf(draftFlag));
			tzscsq = tzscsqManager.saveTzscsq(tzscsq);
			QueryFilter queryFilter = getQueryFilter(getRequest());
			yyfjManager.saveFjByTzsq(tzscsq, queryFilter);
			ajaxJson.setMsg("远洋图纸审查申请表操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查申请表操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 执行删除[远洋图纸审查申请表]对象
	 * @return AjaxJson
	 * @throws Exception 
	 * @author administrator 
	 * @Create 2017-12-07 10:28:56
	 */
	@RequestMapping("doTzscsqRemove")
	@ResponseBody
	public AjaxJson doTzscsqRemove(){
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> tzscsq = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(tzscsq.get("id"));
			tzscsqManager.doRemove(aryIds);
			ajaxJson.setMsg("远洋图纸审查申请表删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("远洋图纸审查申请表删除失败！");
		}
		return ajaxJson;
	}
	
	/**
	 * 根据输入的船网工具指标批准书编号 查询
	 * 
	 * @return pageJson
	 * @author wjk
	 * @Create 2017-12-7 14:35:21
	 */
	@RequestMapping("getCwpzsbhByInIputWordJson")
	@ResponseBody
	public Object getCwpzsbhByInIputWord() {
		PageList<Cwgjzbpzs> tzsqList = (PageList<Cwgjzbpzs>) cwgjzbpzsManager.cwgjzbpzsListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	

	/**
	 * 国内 渔船 图纸审查  图纸审查  查看界面    
	 * @return Tzsq
	 * @author wjk 
	 * @Create Date 2017 上午9:38:12
	 */
	@RequestMapping("getTzsqJson")
	public @ResponseBody Tzscsq getTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 根据 申请 表id   查询 往来文件     
	 * @return Object
	 * @author wjk 
	 * @Create Date 2017 上午9:59:32
	 */
	@RequestMapping("getWlwjByTzSqId")
	@ResponseBody
	public Object getWlwjByTzSqId() {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		queryFilter.addFilter("scbj", 1, QueryOP.NOT_EQUAL);
		List<YyWlwj> yyWlwjList = yyWlwjManager.query(queryFilter);
		PageJson pageJson = new PageJson(yyWlwjList);
		return pageJson;
	}
	
	
	/**
	 * 根据id  删除 往来文件     
	 * @return ModelAndView
	 * @author wjk 
	 * @Create Date 2017 上午10:27:14
	 */
	@RequestMapping("tzsc/doWlwjRemove")
	public ModelAndView doWlwjRemove() {
		String id = RequestUtil.getString(this.getRequest(), "id");
		YyWlwj yyWlwj = yyWlwjManager.getYyWlwj(id);
		String tzsqId = yyWlwj.getTzsqId();
		yyWlwj.setScbj(1);
		yyWlwjManager.update(yyWlwj);
		return new ModelAndView("redirect:/tzsc/yytzsc/tzsq/getWlwj?id=" + tzsqId);
	}

	
	/**
	 * 查询带整改 申请 列表
	 * @return Object
	 * @author wjk
	 * @Create Date 2017 下午2:22:07
	 */
	@RequestMapping("tzdzgListJson")
	@ResponseBody
	public Object tzdzgListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		//queryFilter.addFilter("tjzt", 2, QueryOP.EQUAL); // 带整改条件
		List<Tzscsq> tzscsqList = tzscsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	
	/**
	 * 远洋图纸审查申请受理列表     
	 * @return
	 * @throws Exception Tzsq
	 * @author wjk 
	 * @Create Date 2017 下午3:32:04
	 */
	@RequestMapping("sqslListJson")
	@ResponseBody
	public Object sqslListJson() throws Exception {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		//后期 在此处添加 待受理列表 查询条件
		List<Tzscsq> tzscsqList = tzscsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	
	/**
	 * 远洋图纸审查 材料审核    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午2:34:37
	 */
	@RequestMapping("saveShyj")
	@ResponseBody
	public AjaxJson saveShyj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		String id = RequestUtil.getString(this.getRequest(), "id");
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("材料审核");
			clshyj.setTzsqId(id);
			clshyj = yyClshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("材料审核操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("材料审核操作失败");
		}
		return ajaxJson;
	}
	/**
	 * 部局受理 列表    
	 * @return
	 * @throws Exception Object
	 * @author wjk 
	 * @Create Date 2017 下午2:57:06
	 */
	@RequestMapping("bjsl/bjslListJson")
	@ResponseBody
	public Object bjslListJson() throws Exception {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		//后期 在此处添加 待受理列表 查询条件
		List<Tzscsq> tzscsqList = tzscsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	/**
	 * 远洋渔船 图纸审查  部级受理 查看
	 * @return
	 * @throws Exception Tzscsq
	 * @author wjk 
	 * @Create Date 2017 下午3:07:40
	 */
	@RequestMapping("bjsl/getTzsqJson")
	public @ResponseBody Tzscsq getTzscsqBjslJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 部局受理  审核意见    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午3:21:51
	 */
	@RequestMapping("bjsl/savebjslyj")
	@ResponseBody
	public AjaxJson saveldshyj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("部局受理");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = yyClshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("部局受理操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("部局受理操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 部局办理 查看    
	 * @return
	 * @throws Exception Tzscsq
	 * @author wjk 
	 * @Create Date 2017 下午3:40:33
	 */
	@RequestMapping("bjbl/getTzsqJson")
	public @ResponseBody Tzscsq getTzscsqBjblJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 部局办理意见 保存    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午3:48:42
	 */
	@RequestMapping("bjbl/savebjblyj")
	@ResponseBody
	public AjaxJson savebjblyj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("部局办理");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = yyClshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("部局办理操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("部局办理操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 保存往来文件    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午4:26:42
	 */
	@RequestMapping("bjbl/saveScwth")
	@ResponseBody
	public AjaxJson saveScwth() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			String id = RequestUtil.getString(this.getRequest(), "id");
			YyWlwj yyWlwj = (YyWlwj) getRequestObject(YyWlwj.class);
			yyWlwj.setTzsqId(id);
			QueryFilter queryFilter = getQueryFilter(getRequest());
			yyWlwjManager.saveWlwjByTzsq(yyWlwj, queryFilter);
			ajaxJson.setMsg("委托函上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("委托函上传失败");
		}
		return ajaxJson;
	}
	/**
	 * 查看  委托函   
	 * @return
	 * @throws Exception Tzsq
	 * @author wjk 
	 * @Create Date 2017 上午9:17:38
	 */
	@RequestMapping("bjbl/getWlwjJson")
	public @ResponseBody YyWlwj getWlwjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 申请书主键
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		YyWlwj yyWlwj = yyWlwjManager.dealWlwjBySqId(queryFilter);
		return yyWlwj;
	}
	/**
	 * 得到 人员指派 列表   
	 * @return Object
	 * @author wjk 
	 * @Create Date 2017 下午3:14:06
	 */
	@RequestMapping("ryzp/ryzpListJson")
	@ResponseBody
	public Object ryzpListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		//后续 需要在这里添加  流程到 人员指派 的条件
		List<Tzscsq> tzscsqList = tzscsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	
	/**
	 * 人员指派 申请 查看 
	 * @return
	 * @throws Exception Tzscsq
	 * @author wjk 
	 * @Create Date 2017 下午2:37:23
	 */
	@RequestMapping("ryzp/getTzsqJson")
	public @ResponseBody Tzscsq getRyzpTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	

	/**
	 * 国内 渔船 图纸审查  图纸审查  查看界面    
	 * @return Tzsq
	 * @author wjk 
	 * @Create Date 2017 上午9:38:12
	 */
	@RequestMapping("tzsc/getTzsqJson")
	public @ResponseBody Tzscsq getTzscTzsqJson() {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 材料初审   
	 * @return Tzscsq
	 * @author wjk 
	 * @Create Date 2018 下午3:21:24
	 */
	@RequestMapping("clcs/getTzsqJson")
	public @ResponseBody Tzscsq getTzscClcsJson() {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 图纸审查 申请 保存材料    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 上午10:31:38
	 */
	@RequestMapping("tzsc/saveClsc")
	@ResponseBody
	public AjaxJson saveClsc() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			String id = RequestUtil.getString(this.getRequest(), "id");
			YyWlwj yyWlwj = (YyWlwj) getRequestObject(YyWlwj.class);
			yyWlwj.setTzsqId(id);
			QueryFilter queryFilter = getQueryFilter(getRequest());
			yyWlwjManager.saveWlwjByTzsq(yyWlwj, queryFilter);
			ajaxJson.setMsg("图纸申请上传材料操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("图纸申请上传材料操作失败");
		}
		return ajaxJson;
	}
	/**
	 * 图纸审查 上传材料    
	 * @return
	 * @throws Exception Wlwj
	 * @author wjk 
	 * @Create Date 2017 上午9:59:28
	 */
	@RequestMapping("tzsc/getWlwjJson")
	public @ResponseBody YyWlwj getTzscWlwjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 申请书主键
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		YyWlwj yyWlwj = yyWlwjManager.dealWlwjBySqId(queryFilter);
		return yyWlwj;
	}
	/**
	 * 保存领导 批复 意见 ，此处 并未 对申请 表进行 处理 后续 可能 对申请表进行处理   
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午1:58:27
	 */
	@RequestMapping("tzsc/saveLdpf")
	@ResponseBody
	public AjaxJson saveldpf() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("领导批复");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = yyClshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("领导批复操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("领导批复操作失败");
		}
		return ajaxJson;
	}
	/**
	 * 得到部级办结列表  
	 * @return Object
	 * @author wjk 
	 * @Create Date 2017 下午3:14:06
	 */
	@RequestMapping("bjbj/bjbjListJson")
	@ResponseBody
	public Object bjbjListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		List<Tzscsq> tzscsqList = tzscsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	
	/**
	 * 部局办结  查看申请    
	 * @return
	 * @throws Exception Tzscsq
	 * @author wjk 
	 * @Create Date 2017 上午11:17:56
	 */
	@RequestMapping("bjbj/getTzsqJson")
	public @ResponseBody Tzscsq getTzscsqBjbjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	
	/**
	 * 部局办结 保存 办结意见   
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午2:51:01
	 */
	@RequestMapping("bjbj/saveBj")
	@ResponseBody
	public AjaxJson saveBj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("部局办结");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = yyClshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("部局办结操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("部局办结操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 得到材料归档列表
	 * @return Object
	 * @author wjk 
	 * @Create Date 2017 下午3:14:06
	 */
	@RequestMapping("clgd/clgdListJson")
	@ResponseBody
	public Object clgdListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		List<Tzscsq> tzscsqList = tzscsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	/**
	 * 材料归档查看    
	 * @return
	 * @throws Exception Tzscsq
	 * @author wjk 
	 * @Create Date 2017 下午1:51:44
	 */
	@RequestMapping("clgd/getTzsqJson")
	public @ResponseBody Tzscsq getTzscsqClgdJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");//主键
		Tzscsq tzscsq = tzscsqManager.getTzscsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzscsq) {
			yyfjManager.dealFjBySqId(tzscsq, queryFilter);
		}
		return tzscsq;
	}
	/**
	 * 保存材料归档 意见   后续 可以 更新 申请 表 状态    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午5:19:08
	 */
	@RequestMapping("clgd/saveGdyj")
	@ResponseBody
	public AjaxJson saveGdyj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("材料归档");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			clshyj = yyClshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("材料归档操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("材料归档操作失败");
		}
		return ajaxJson;
	}
	/**
	 * 草稿查询    
	 * @return
	 * @throws Exception Object
	 * @author wjk 
	 * @Create Date 2017 下午4:24:39
	 */
	@RequestMapping("tzscsqcgListJson")
	@ResponseBody
	public Object tzscsqcgListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		paramMap.put("Q^tjzt^S", 0);
		PageList<Tzscsq> tzscsqList = (PageList<Tzscsq>) tzscsqManager.tzscsqListJson(paramMap);
		PageJson pageJson = new PageJson(tzscsqList);
		return pageJson;
	}
	/**
	 * 已处理列表
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午3:26:49
	 */
	@RequestMapping("yytzscYbList")
	public ModelAndView yytzscYbList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		List<Map<String, Object>> list = (List<Map<String, Object>>) flowTaskManager.getDoneList(StaticMethod.changeRequestParamToMap(getRequest()),"tzsc_sq_yy");
		PageJson pageJson = new PageJson(list);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	/**
	 * 远洋图纸审查查询列表   
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午4:12:17
	 */
	@RequestMapping("yytzscCxList")
	public ModelAndView yytzscCxList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		PageList<Tzscsq> tzscsqList = (PageList<Tzscsq>) tzscsqManager.tzscsqListJson(StaticMethod.changeRequestParamToMap(getRequest()));
		PageJson pageJson = new PageJson(tzscsqList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	/**
	 * 远洋图纸审查打印查询
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午4:12:17
	 */
	@RequestMapping("yytzscDyList")
	public ModelAndView yytzscDyList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		PageList<Tzscsq> tzscsqList = (PageList<Tzscsq>) tzscsqManager.tzscsqListJson(StaticMethod.changeRequestParamToMap(getRequest()));
		PageJson pageJson = new PageJson(tzscsqList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
}
