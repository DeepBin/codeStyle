
package com.sky.tzsc.gntzsc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.sky.tzsc.gntzsc.persistence.manager.ClshyjManager;
import com.sky.tzsc.gntzsc.persistence.manager.CwgjzbpzsManager;
import com.sky.tzsc.gntzsc.persistence.manager.FjManager;
import com.sky.tzsc.gntzsc.persistence.manager.TzsqManager;
import com.sky.tzsc.gntzsc.persistence.manager.WlwjManager;
import com.sky.tzsc.gntzsc.persistence.manager.YjManager;
import com.sky.tzsc.gntzsc.persistence.manager.ZslbManager;
import com.sky.tzsc.gntzsc.persistence.model.Clshyj;
import com.sky.tzsc.gntzsc.persistence.model.Cwgjzbpzs;
import com.sky.tzsc.gntzsc.persistence.model.Fj;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;
import com.sky.tzsc.gntzsc.persistence.model.Yj;
import com.sky.tzsc.gntzsc.persistence.model.Zslb;
import com.sky.tzsc.gntzsc.util.TzscZsXmlHelper;


/**
 * 
 * <pre>
 *  
 * 描述：[tzsc_sq]控制器类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-22 14:04:09
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Controller
@RequestMapping("/tzsc/gntzsc/tzsq")
public class TzsqController extends BaseController {

	@Resource
	TzsqManager tzsqManager;
	@Resource
	WlwjManager wlwjManager;
	@Resource
	CwgjzbpzsManager cwgjzbpzsManager;
	@Resource
	FjManager fjManager;
	@Resource
	YjManager yjManager;
	@Resource
	ClshyjManager clshyjManager;
	@Resource
	FlowTaskManager flowTaskManager;
	@Resource
	ZslbManager zslbManager;

	/**
	 * 执行查询 ,系统自动封装查询条件
	 * 
	 * @return ModelAndView
	 * @throws Exception
	 * @author administrator
	 * @Create 2017-11-22 14:04:09
	 */
	@RequestMapping("tzsqListJson")
	@ResponseBody
	public Object tzsqListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		paramMap.put("Q^tjzt^S", 1);
		PageList<Tzsq> tzsqList = (PageList<Tzsq>) tzsqManager.tzsqListJson(paramMap);// 执行查询
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}

	/**
	 * 通过ajax方式查询[tzsc_sq]信息
	 * 
	 * @return Tzsq
	 * @throws Exception
	 * @author administrator
	 * @Create 2017-11-22 14:04:09
	 */
	@RequestMapping("getTzsqJson")
	public @ResponseBody Tzsq getTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			//fjManager.dealFjBySqId(tzsq, queryFilter);
		}

		return tzsq;
	}

	/**
	 * 新增/修改图纸审查申请信息，跳转新增/修改图纸审查申请页面
	 * 
	 * @return ModelAndView
	 * @throws Exception
	 * @author administrator
	 * @Create 2017-11-22 14:04:09
	 */
	@RequestMapping("tzsqEdit")
	public ModelAndView tzsqEdit(String id) throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		if (!StaticMethod.isNotEmpty(id)) {
			modelAndView.addObject("id", idGenerator.getSuid());
		}
		return modelAndView;
	}

	/**
	 * 执行新增或者更新[tzsc_sq]信息
	 * 
	 * @param tzsq
	 * @return AjaxJson
	 * @throws Exception
	 * @author administrator
	 * @Create 2017-11-22 14:04:09
	 */
	@RequestMapping("saveTzsq")
	@ResponseBody
	public AjaxJson saveTzsq() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Tzsq tzsq = (Tzsq) getRequestObject(Tzsq.class);
			String  draftFlag  = RequestUtil.getString(this.getRequest(), "draft");
			tzsq.setTjzt(Integer.valueOf(draftFlag));
			tzsq = tzsqManager.saveTzsq(tzsq);
			//QueryFilter queryFilter = getQueryFilter(getRequest());
			//fjManager.saveFjByTzsq(tzsq, queryFilter);
			ajaxJson.setMsg("国内图纸申请操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("国内图纸申请操作失败");
		}
		return ajaxJson;
	}

	/**
	 * 执行删除[tzsc_sq]对象
	 * 
	 * @return AjaxJson
	 * @throws Exception
	 * @author administrator
	 * @Create 2017-11-22 14:04:09
	 */
	@RequestMapping("doTzsqRemove")
	@ResponseBody
	public AjaxJson doTzsqRemove() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Map<String, String> tzsq = (Map<String, String>) this.getRequestMap();
			String[] aryIds = RequestUtil.getStringAryByStr(tzsq.get("id"));
			tzsqManager.doRemove(aryIds);
			ajaxJson.setMsg("图纸申请删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("图纸申请删除失败！");
		}
		return ajaxJson;
	}

	/**
	 * 根据申请id 查询 未删除的往来文件列表
	 * 
	 * @return ModelAndView
	 * @author wjk
	 * @Create Date 2017 下午4:39:43
	 */
	@RequestMapping("getWlwjByTzSqId")
	@ResponseBody
	public Object getWlwjByTzSqId() {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		queryFilter.addFilter("scbj", 1, QueryOP.NOT_EQUAL);
		List<Wlwj> wlwjList = wlwjManager.query(queryFilter);
		PageJson pageJson = new PageJson(wlwjList);
		return pageJson;
	}

	/**
	 * 根据 id删除往来文件
	 * 
	 * @return ModelAndView
	 * @author wjk
	 * @Create Date 2017 下午2:33:02
	 */
	@RequestMapping("doWlwjRemove")
	public ModelAndView doWlwjRemove() {
		String id = RequestUtil.getString(this.getRequest(), "id");
		Wlwj wlwj = wlwjManager.getWlwj(id);
		String tzsqId = wlwj.getTzsqId();
		wlwj.setScbj(1);
		wlwjManager.update(wlwj);
		return new ModelAndView("redirect:/tzsc/tzsq/getWlwj?id=" + tzsqId);
	}

	/**
	 * 根据输入的船网工具指标批准书编号 查询
	 * 
	 * @return pageJson
	 * @author wjk
	 * @Create Date 2017 下午5:05:09
	 */
	@RequestMapping("getCwpzsbhByInIputWordJson")
	@ResponseBody
	public Object getCwpzsbhByInIputWord() {
		PageList<Cwgjzbpzs> tzsqList = (PageList<Cwgjzbpzs>) cwgjzbpzsManager.cwgjzbpzsListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}

	/**
	 * 查询带整改 申请 列表
	 * 
	 * @return Object
	 * @author wjk
	 * @Create Date 2017 下午2:22:07
	 */
	@RequestMapping("tzdzgListJson")
	@ResponseBody
	public Object tzdzgListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		queryFilter.addFilter("tjzt", 2, QueryOP.EQUAL); // 带整改条件
		List<Tzsq> TzsqList = tzsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(TzsqList);
		return pageJson;
	}

	/**
	 * 根据申请表id 查询带整改的 附件 列表
	 * 
	 * @return Object pageJson
	 * @author wjk
	 * @Create Date 2017 下午6:00:43
	 */
	@RequestMapping("zgListJson")
	@ResponseBody
	public Object zgListJson() {
		String id = RequestUtil.getString(this.getRequest(), "id");
		QueryFilter queryFilter = getQueryFilter(getRequest());
		queryFilter.addFilter("zt", 2, QueryOP.EQUAL);
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		queryFilter.addFilter("scbj", 1, QueryOP.NOT_EQUAL);
		List<Fj> FjList = fjManager.query(queryFilter);
		PageJson pageJson = new PageJson(FjList);
		return pageJson;
	}

	/**
	 * 根据附件 id 查询 具体信息
	 * 
	 * @param id
	 * @return ModelAndView
	 * @author wjk
	 * @throws Exception
	 * @Create Date 2017 上午9:33:24
	 */
	@RequestMapping("getFjzgJson")
	public @ResponseBody Fj fjzgEdit() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Fj fj = fjManager.getFj(id);
		return fj;
	}

	/**
	 * 保存整改记录
	 * 
	 * @return AjaxJson
	 * @author wjk
	 * @Create Date 2017 上午11:18:56
	 */
	@RequestMapping("saveFj")
	@ResponseBody
	public AjaxJson saveFj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Fj fj = (Fj) getRequestObject(Fj.class);
			// 更新整改 后的 状态
			fj = fjManager.saveFj(fj);
			ajaxJson.setMsg("附件整改操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("附件整改操作失败");
		}
		return ajaxJson;
	}

	/**
	 * 根据 附件 id 查询 为删除 的 附件意见列表
	 * 
	 * @return Object pageJson
	 * @author wjk
	 * @Create Date 2017 下午1:53:55
	 */
	@RequestMapping("yjListJson")
	@ResponseBody
	public Object yjListJson() {
		String id = RequestUtil.getString(this.getRequest(), "id");
		QueryFilter queryFilter = getQueryFilter(getRequest());
		queryFilter.addFilter("tzfj_id", id, QueryOP.EQUAL);
		queryFilter.addFilter("scbj", 1, QueryOP.NOT_EQUAL);
		List<Yj> YjList = yjManager.query(queryFilter);
		PageJson pageJson = new PageJson(YjList);
		return pageJson;
	}
	
	/**
	 * 申请受理列表     
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
		List<Tzsq> tzsqList = tzsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	
	/**
	 * 保存材料审核意见    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 上午10:47:03
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
			//后期需要更新 标记 
			clshyj = clshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("材料审核操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("材料审核操作失败");
		}
		return ajaxJson;
	}

	/**
	 * 查询图纸申请 委托申请列表   
	 * @return Object
	 * @author wjk 
	 * @Create Date 2017 上午11:51:13
	 */
	@RequestMapping("wtstListJson")
	@ResponseBody
	public  Object wtstListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		List<Tzsq> tzsqList = tzsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	
	/**
	 * 保存领导审批意见    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午2:21:25
	 */
	@RequestMapping("wtst/saveldshyj")
	@ResponseBody
	public AjaxJson saveldshyj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("领导审批");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = clshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("领导审批操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("领导审批操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 申请 查看 
	 * @return
	 * @throws Exception Tzsq
	 * @author wjk 
	 * @Create Date 2017 下午2:37:23
	 */
	@RequestMapping("wtst/getTzsqJson")
	public @ResponseBody Tzsq getWtstTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			fjManager.dealFjBySqId(tzsq, queryFilter);
		}
		return tzsq;
	}
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @return
	 * @throws Exception Tzsq
	 * @author wjk 
	 * @Create Date 2017 上午9:17:38
	 */
	@RequestMapping("wtst/getWlwjJson")
	public @ResponseBody Wlwj getWlwjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 申请书主键
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		//queryFilter.addFilter("wjlb_yw", "wth", QueryOP.EQUAL);
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		Wlwj wlwj = wlwjManager.dealWlwjBySqId(queryFilter);
		return wlwj;
	}
	
	@RequestMapping("tzsc/getWlwjJson")
	public @ResponseBody Wlwj getTzscWlwjJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 申请书主键
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		queryFilter.addFilter("tzsq_id", id, QueryOP.EQUAL);
		Wlwj wlwj = wlwjManager.dealWlwjBySqId(queryFilter);
		return wlwj;
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
		List<Tzsq> tzsqList = tzsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	
	/**
	 * 人员指派 申请 查看 
	 * @return
	 * @throws Exception Tzsq
	 * @author wjk 
	 * @Create Date 2017 下午2:37:23
	 */
	@RequestMapping("ryzp/getTzsqJson")
	public @ResponseBody Tzsq getRyzpTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			//fjManager.dealFjBySqId(tzsq, queryFilter);
		}
		return tzsq;
	}
	
	/**
	 * 国内 渔船 图纸审查  图纸审查  查看界面    
	 * @return Tzsq
	 * @author wjk 
	 * @Create Date 2017 上午9:38:12
	 */
	@RequestMapping("tzsc/getTzsqJson")
	public @ResponseBody Tzsq getTzscTzsqJson() {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			//fjManager.dealFjBySqId(tzsq, queryFilter);
		}
		return tzsq;
	}
	
	/**
	 * 保存领导 批复 意见 ，此处 并未 对申请 表进行 处理 后续 可能 对申请表进行处理   
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午1:58:27
	 */
	@RequestMapping("tzsc/saveldpf")
	@ResponseBody
	public AjaxJson saveldpf() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("领导批复");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = clshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("领导批复操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("领导批复操作失败");
		}
		return ajaxJson;
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
			Wlwj wlwj = (Wlwj) getRequestObject(Wlwj.class);
			wlwj.setTzsqId(id);
			QueryFilter queryFilter = getQueryFilter(getRequest());
			wlwjManager.saveWlwjByTzsq(wlwj, queryFilter);
			ajaxJson.setMsg("图纸申请上传材料操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("图纸申请上传材料操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 委托审图 上传 委托函    
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午2:13:14
	 */
	@RequestMapping("wtst/saveScwth")
	@ResponseBody
	public AjaxJson saveScwth() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			String id = RequestUtil.getString(this.getRequest(), "id");
			Wlwj wlwj = (Wlwj) getRequestObject(Wlwj.class);
			wlwj.setTzsqId(id);
			QueryFilter queryFilter = getQueryFilter(getRequest());
			wlwjManager.saveWlwjByTzsq(wlwj, queryFilter);
			ajaxJson.setMsg("委托函上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("委托函上传失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 部局办结 保存 办结意见   
	 * @return AjaxJson
	 * @author wjk 
	 * @Create Date 2017 下午2:51:01
	 */
	@RequestMapping("stbj/saveBj")
	@ResponseBody
	public AjaxJson saveBj() {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			Clshyj clshyj = (Clshyj) getRequestObject(Clshyj.class);
			clshyj.setBz("部局办结");
			String id = RequestUtil.getString(this.getRequest(), "id");
			clshyj.setTzsqId(id);
			//后期需要更新 申请表的审批 标记 
			clshyj = clshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("部局办结操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("部局办结操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 审图办结  查看   此处 应该 合并为一个 方法   
	 * @return
	 * @throws Exception Tzsq
	 * @author wjk 
	 * @Create Date 2017 下午2:59:55
	 */
	@RequestMapping("stbj/getTzsqJson")
	public @ResponseBody Tzsq getStbjTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			//fjManager.dealFjBySqId(tzsq, queryFilter);
		}
		return tzsq;
	}
	
	
	 /**
	  * 材料归档  申请 查看     
	  * @return Tzsq
	  * @author wjk 
	  * @Create Date 2017 下午5:10:15
	  */
	@RequestMapping("clgd/getTzsqJson")
	public @ResponseBody Tzsq getTzscClgdJson() {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			//fjManager.dealFjBySqId(tzsq, queryFilter);
		}
		return tzsq;
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
			//后期需要更新 申请表的审批 标记 
			clshyj = clshyjManager.saveClshyj(clshyj);
			ajaxJson.setMsg("材料归档操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("材料归档操作失败");
		}
		return ajaxJson;
	}
	
	/**
	 * 得到审图办结列表   
	 * @return Object
	 * @author wjk 
	 * @Create Date 2017 下午3:14:06
	 */
	@RequestMapping("stbj/stbjListJson")
	@ResponseBody
	public Object stbjListJson() {
		QueryFilter queryFilter = getQueryFilter(getRequest());
		//后续 需要在这里添加  流程到 人员指派 的条件
		List<Tzsq> tzsqList = tzsqManager.query(queryFilter);
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	
	/**
	 * 草稿查询    
	 * @return
	 * @throws Exception Object
	 * @author wjk 
	 * @Create Date 2017 下午3:54:37
	 */
	@RequestMapping("tzsqcgListJson")
	@ResponseBody
	public Object tzsqcgListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		paramMap.put("Q^tjzt^S", 0);
		PageList<Tzsq> tzsqList = (PageList<Tzsq>) tzsqManager.tzsqListJson(paramMap);// 执行查询
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	/**
	 * 查看全部列表   
	 * @return
	 * @throws Exception Object
	 * @author wjk 
	 * @Create Date 2017 下午6:21:48
	 */
	@RequestMapping("gnstgzjdListJson")
	@ResponseBody
	public Object gnstgzjdListJson() throws Exception {
		Map<String ,Object> paramMap = StaticMethod.changeRequestParamToMap(getRequest());
		PageList<Tzsq> tzsqList = (PageList<Tzsq>) tzsqManager.tzsqListJson(paramMap);// 执行查询
		PageJson pageJson = new PageJson(tzsqList);
		return pageJson;
	}
	
	@RequestMapping("stgzjd/getTzsqJson")
	public @ResponseBody Tzsq getStgzjdTzsqJson() throws Exception {
		String id = RequestUtil.getString(this.getRequest(), "id");// 主键
		Tzsq tzsq = tzsqManager.getTzsq(id);
		QueryFilter queryFilter = getQueryFilter(getRequest());// 封装查询条件
		if (null != tzsq) {
			//fjManager.dealFjBySqId(tzsq, queryFilter);
		}

		return tzsq;
	}
	//gntzscYbList
	/**
	 * 已处理列表
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午3:26:49
	 */
	@RequestMapping("gntzscYbList")
	public ModelAndView gntzscYbList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		List<Map<String, Object>> list = (List<Map<String, Object>>) flowTaskManager.getDoneList(StaticMethod.changeRequestParamToMap(getRequest()),"tzsc_sq");
		PageJson pageJson = new PageJson(list);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	/**
	 * 国内图纸审查查询列表   
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午4:12:17
	 */
	@RequestMapping("gntzscCxList")
	public ModelAndView gntzscCxList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		PageList<Tzsq> tzscsqList = (PageList<Tzsq>) tzsqManager.tzsqListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(tzscsqList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	
	/**
	 * 国内图纸审查打印查询   
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午4:12:17
	 */
	@RequestMapping("gntzscDyList")
	public ModelAndView gntzscDyList() throws Exception {
		ModelAndView modelAndView = this.getAutoView();
		PageList<Tzsq> tzscsqList = (PageList<Tzsq>) tzsqManager.tzsqListJson(StaticMethod.changeRequestParamToMap(getRequest()));// 执行查询
		PageJson pageJson = new PageJson(tzscsqList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	/**
	 * 更具流程 类型 查询 需要制作的 材料列表   
	 * parms  lcbz lcbz
	 * @return Object
	 * @author wjk 
	 * @Create Date 2018 下午2:28:41
	 */
	@RequestMapping("getGntzscZslb")
	@ResponseBody
	public Object getGntzscZslb(String lcbz,String sheetKey,String systemName)  {
		AjaxJson ajaxJson = this.getAjaxJson();
		//String lclx = RequestUtil.getString(this.getRequest(), "lcbz");
		try {
			List<Zslb> list = zslbManager.getZslbByLclx(lcbz,sheetKey,systemName);
			ajaxJson.setObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("证书查询失败，请联系管理员");
		}
		return ajaxJson;
	}
	
	/**
	 * 添加证书     
	 * @return Object
	 * @author wjk 
	 * @Create Date 2018 上午9:30:57
	 */
	@RequestMapping("addTzscZs")
	@ResponseBody
	public Object addTzscZs(String sheetKey,String fileId,String systemName) {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			zslbManager.addTzscZs(sheetKey,fileId,systemName);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("选择证书失败，请联系管理员");
		}
		return ajaxJson;
	}
	
	/**
	 * 删除 图纸审查申请 证书表    
	 * @param sheetKey
	 * @param fileId
	 * @return Object
	 * @author wjk 
	 * @Create Date 2018 上午10:13:55
	 */
	@RequestMapping("deleteTzscZs")
	@ResponseBody
	public Object deleteTzscZs(String sheetKey,String fileId,String systemName) {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			zslbManager.deleteTzscZs(sheetKey,fileId,systemName);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("选择证书失败，请联系管理员");
		}
		return ajaxJson;
	}
	
	/**
	 * 图纸审查 证书 编辑    
	 * @param sheetKey 图纸审查 申请表主键 
	 * @param fileId 图纸证书 表主键
	 * @return ModelAndView 
	 * @author wjk 
	 * @throws Exception 
	 * @Create Date 2018 上午10:26:53
	 */
	@RequestMapping("tzscZsEdit")
	public ModelAndView tzscZsEdit(String sheetKey,String fileId) throws Exception {
		String forword = "/tzsc/tzscZs/commonEidt"; 
		ModelAndView modelAndView = this.getZsxx(sheetKey, fileId);
		modelAndView.setViewName(forword);
		return modelAndView;
	}

	/**
	 * 得到证书信息   
	 * @param sheetKey
	 * @param fileId
	 * @return ModelAndView
	 * @author wjk 
	 * @throws Exception 
	 * @Create Date 2018 上午10:30:04
	 */
	private ModelAndView getZsxx(String sheetKey, String fileId) throws Exception {
		if(!StaticMethod.isNotEmpty(sheetKey) || !StaticMethod.isNotEmpty(fileId)) {
			throw new Exception("证书查看错误，请与管理员联系 ");
		}
		Zslb zslb = zslbManager.getZslb(fileId);
		String forword = "/tzsc/tzscZs/commonZstx"; 
		ModelAndView modelAndView = new ModelAndView(forword);
		List<Map<String, Object>> listElement = TzscZsXmlHelper.getElementMap(zslb.getPageName()+".xml");
		Map<String ,Object> dataMap = zslbManager.getSelectResult(listElement, sheetKey, "sheet_key");
		Tzsq tzsq = tzsqManager.getTzsq(sheetKey);
		modelAndView.addObject(tzsq);
		modelAndView.addObject("sheetKey",sheetKey);
		modelAndView.addObject("zslb",zslb);
		modelAndView.addObject("dataMap",dataMap);
		String url = "zs/"+zslb.getPageName()+".jsp"; 
		modelAndView.addObject("url",url);
		return modelAndView;
	}
	
	/**
	 * 得到已选的证书    
	 * @param sheetKey 图纸申请表主键
	 * @return Object
	 * @author wjk 
	 * @Create Date 2018 下午1:46:44
	 */
	@RequestMapping("getYxzs")
	@ResponseBody
	public Object getYxzs(String sheetKey,String systemName) {
		AjaxJson ajaxJson = this.getAjaxJson();
		try {
			List<Zslb> list = zslbManager.getYxzs(sheetKey,systemName);
			ajaxJson.setObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("选择证书失败，请联系管理员");
		}
		return ajaxJson;
	}
	
	/**
	 * 查看信息    
	 * @param sheetKey
	 * @param fileId
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午3:22:11
	 */
	@RequestMapping("viewZsxx")
	public ModelAndView viewZsxx(String sheetKey,String fileId) throws Exception  {
		String forword = "/tzsc/tzscZs/commonView"; 
		ModelAndView modelAndView = this.getZsxx(sheetKey, fileId);
		modelAndView.setViewName(forword);
		return modelAndView;
	}
	
	/**
	 * 修改证书信息   
	 * @param sheetKey
	 * @param fileId
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午3:24:08
	 */
	@RequestMapping("editZsxx")
	public ModelAndView editZsxx(String sheetKey,String fileId) throws Exception  {
		String forword = "/tzsc/tzscZs/commonEidt"; 
		ModelAndView modelAndView = this.getZsxx(sheetKey, fileId);
		modelAndView.setViewName(forword);
		return modelAndView;
	}
	
	/**
	 * 打印证书通用页面
	 * @param sheetKey
	 * @param fileId
	 * @return ModelAndView
	 * @author wjg 
	 * @throws Exception 
	 * @Create Date 2017 下午3:54:18
	 */
	@RequestMapping("printZsxx")
	public ModelAndView printZsxx(String sheetKey,String fileId) throws Exception  {
		String forword = "/tzsc/tzscZs/commonPrint"; 
		ModelAndView modelAndView = this.getZsxx(sheetKey, fileId);
		modelAndView.setViewName(forword);
		return modelAndView;
	}
	
	/**
	 * 保存证书信息    
	 * @param sheetKey
	 * @param fileId
	 * @param saveFlag
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午3:25:48
	 */
	@RequestMapping("updateZs")
	public ModelAndView updateZs (String sheetKey,Integer fileId,String saveFlag) throws Exception  {
		if(!StaticMethod.isNotEmpty(sheetKey) || !StaticMethod.isNotEmpty(fileId)) {
			throw new Exception("保存证书错误，请与管理员联系 ");
		}
		String forword = "/tzsc/gntzsc/tzsq/result?sheetKey="+sheetKey+"&fileId="+fileId;
		ModelAndView modelAndView = new ModelAndView("redirect:/"+forword);
		Map<String ,Object> map = StaticMethod.changeRequestParamToMap(this.getRequest());
		zslbManager.updateZs(saveFlag,map, sheetKey, "sheet_key",fileId);
		return modelAndView;
	}
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @return ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午4:06:46
	 */
	@RequestMapping("saveTzml")
	public Object saveTzml() {
		HttpServletRequest request = this.getRequest();
		String flowKey = request.getParameter("flowKey");//申请书id
		return null;
	}
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @return
	 * @throws Exception ModelAndView
	 * @author wjk 
	 * @Create Date 2018 下午2:07:34
	 */
	@RequestMapping("viewTzfl")
	public ModelAndView viewTzfl() throws Exception {
		String  flowKey = this.getRequest().getParameter("flowKey");
		ModelAndView modelAndView = this.getAutoView();
		List<Fj> fjList = fjManager.getFjListByFlowKey(flowKey);// 执行查询
		PageJson pageJson = new PageJson(fjList);
		modelAndView.addObject("pageJson", pageJson);
		return modelAndView;
	}
	
	/**
	 * 打印申请书 界面  根据申请表 主键查询申请信息    
	 * @return ModelAndView 
	 * @author wjk 
	 * @throws Exception 
	 * @Create Date 2018 下午4:51:55
	 */
	@RequestMapping("printSq")
	public ModelAndView printSq() throws Exception {
		String  id = this.getRequest().getParameter("id");
		ModelAndView modelAndView = this.getAutoView();
		Tzsq tzsq = tzsqManager.getTzsq(id);
		modelAndView.addObject(tzsq);
		return  modelAndView;
	}
	
}
