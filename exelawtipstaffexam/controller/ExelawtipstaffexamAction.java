
package com.boco.eoms.exelawtipstaffexam.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.hibernate.HibernateException;
import net.sf.json.JSONObject;

import org.apache.jcs.access.exception.CacheException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.boco.eoms.attachment.dao.TawAttachmentDAO;
import com.boco.eoms.attachment.model.TawAttachment;
import com.boco.eoms.attachment.util.ReadAttconfig;
import com.boco.eoms.common.controller.CnfmAction;
import com.boco.eoms.common.controller.SaveSessionBeanForm;
import com.boco.eoms.common.exception.CnfmMessageException;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.print.util.PrintConfig;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.excelmanagenew.PoiExcel;
import com.boco.eoms.exelawtipstaffexam.bo.ExelawtipstaffexamBO;
import com.boco.eoms.exelawtipstaffexam.bo.ZfryKcglBO;
import com.boco.eoms.exelawtipstaffexam.model.ResultMessage;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksBmsj;
import com.boco.eoms.exelawtipstaffexam.model.ZfryksKc;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksApply;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksCheckControl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksDqdwb;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksDwgl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksJgfs;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksKcgl;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksKd;
import com.boco.eoms.exelawtipstaffexam.vo.ZfryzgksApplyVO;
import com.boco.eoms.exelawtipstaffexam.vo.ZfryzgksCheckControlVO;
import com.boco.eoms.jbzl.bo.TawDeptBO;
import com.boco.eoms.jbzl.bo.TawDistrictBO;
import com.boco.eoms.jbzl.model.TawDept;
import com.boco.eoms.jbzl.model.TawDistrict;
import com.boco.eoms.ui.xtree.vo.XtreeNodeVO;

/**
 * ��Ŀ���ƣ�ִ������ �����ƣ�ExelawtipstaffexamAction �������� ����ʱ�䣺2017��7��17�� ����2:25:54
 * 
 * @version 1.0
 * @author wjk
 */
public class ExelawtipstaffexamAction extends CnfmAction {
	private static int PAGE_LENGTH = 15;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();
		// -----------------------------------------------
		if (isCancelled(request)) { return mapping.findForward("cancel"); }
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("shangbao_init".equalsIgnoreCase(myaction)) {
			myforward = shangbaoInit(mapping, form, request, response);
		} else if ("baomingInit".equalsIgnoreCase(myaction)) {
			myforward = baomingInit(mapping, form, request, response);
		} else if ("shenhe_init".equalsIgnoreCase(myaction)) {
			myforward = shenhe_init(mapping, form, request, response);
		} else if ("check_more_yes".equalsIgnoreCase(myaction)) {
			myforward = check_more_yes(mapping, form, request, response);
		} else if ("check_more_no".equalsIgnoreCase(myaction)) {
			myforward = check_more_no(mapping, form, request, response);
		} else if ("check_up_result".equalsIgnoreCase(myaction)) {
			myforward = check_up_result(mapping, form, request, response);
		} else if ("check_up_more_no".equalsIgnoreCase(myaction)) {
			myforward = check_up_more_no(mapping, form, request, response);
		} else if ("kscsszInit".equalsIgnoreCase(myaction)) {
			myforward = kscsszInit(mapping, form, request, response);
		} else if ("saveKssj".equalsIgnoreCase(myaction)) {
			myforward = saveKssj(mapping, form, request, response);
		} else if ("djxxtx".equalsIgnoreCase(myaction)) {
			myforward = djxxtx(mapping, form, request, response);
		} else if ("baokao".equalsIgnoreCase(myaction)) {
			myforward = baokao(mapping, form, request, response);
		} else if ("baokaoUpdate".equalsIgnoreCase(myaction)) {
			myforward = baokaoUpdate(mapping, form, request, response);
		} else if ("chakanbaomingxixi".equalsIgnoreCase(myaction)) {
			myforward = chakanbaomingxixi(mapping, form, request, response);
		} else if ("kaochangguanli".equalsIgnoreCase(myaction)) {
			myforward = kaochangguanli(mapping, form, request, response);
		} else if ("addckInit".equalsIgnoreCase(myaction)) {
			myforward = addckInit(mapping, form, request, response);
		} else if ("bckcxx".equalsIgnoreCase(myaction)) {
			myforward = bckcxx(mapping, form, request, response);
		} else if ("xiugaickInit".equalsIgnoreCase(myaction)) {
			myforward = xiugaickInit(mapping, form, request, response);
		} else if ("shanchuck".equalsIgnoreCase(myaction)) {
			myforward = shanchuck(mapping, form, request, response);
		} else if ("modify".equalsIgnoreCase(myaction)) {
			myforward = modify(mapping, form, request, response);
		} else if ("delete".equalsIgnoreCase(myaction)) {
			myforward = delete(mapping, form, request, response);
		} else if ("modifySave".equalsIgnoreCase(myaction)) {
			myforward = modifySave(mapping, form, request, response);
		} else if ("modifyUpdate".equalsIgnoreCase(myaction)) {
			myforward = modifyUpdate(mapping, form, request, response);
		} else if ("view".equalsIgnoreCase(myaction)) {
			myforward = view(mapping, form, request, response);
		} else if ("dayinzhunkaozheng_init".equalsIgnoreCase(myaction)) {
			myforward = dayinzhunkaozhengInit(mapping, form, request, response);
		} else if ("dayingzhunkaozheng".equalsIgnoreCase(myaction)) {
			myforward = dayingzhunkaozheng(mapping, form, request, response);
		} else if ("wbdayingzhunkaozheng".equalsIgnoreCase(myaction)) {
			myforward = wbdayingzhunkaozheng(mapping, form, request, response);
		} else if ("dayingkaoshengxinxi".equalsIgnoreCase(myaction)) {
			myforward = dayingkaoshengxinxi(mapping, form, request, response);
		} else if ("chengjiguanl_init".equalsIgnoreCase(myaction)) {
			myforward = chengjiguanlInit(mapping, form, request, response);
		} else if ("kaoshengchengjiluru".equalsIgnoreCase(myaction)) {
			myforward = kaoshengchengjiluru(mapping, form, request, response);
		} else if ("kaoshengchengjilurubaocun".equalsIgnoreCase(myaction)) {
			myforward = kaoshengchengjilurubaocun(mapping, form, request, response);
		} else if ("dayinhuizongbiao_init".equalsIgnoreCase(myaction)) {
			myforward = dayinhuizongbiao_init(mapping, form, request, response);
		} else if ("cx_init".equalsIgnoreCase(myaction)) {
			myforward = cx_init(mapping, form, request, response);
		} else if ("jjkqxz".equalsIgnoreCase(myaction)) {
			myforward = jjkqxz(mapping, form, request, response);
		} else if ("kaxz".equalsIgnoreCase(myaction)) {
			myforward = kaxz(mapping, form, request, response);
		} else if ("checkjjkq".equalsIgnoreCase(myaction)) {
			myforward = checkjjkq(mapping, form, request, response);
		} else if ("ckkdry".equalsIgnoreCase(myaction)) {
			myforward = ckkdry(mapping, form, request, response);
		} else if ("cx_query".equalsIgnoreCase(myaction)) {
			myforward = cx_query(mapping, form, request, response);
		} else if ("hgfssz".equalsIgnoreCase(myaction)) {
			myforward = hgfssz(mapping, form, request, response);
		} else if ("saveKjg".equalsIgnoreCase(myaction)) {
			myforward = saveKjg(mapping, form, request, response);
		} else if ("tj_init".equalsIgnoreCase(myaction)) {
			myforward = tj_init(mapping, form, request, response);
		} else if ("district".equalsIgnoreCase(myaction)) {
			myforward = districtTree(mapping, form, request, response);
		} else if ("dept".equalsIgnoreCase(myaction)) {
			myforward = deptTree(mapping, form, request, response);
		} else if ("ckanbaomingxixi".equalsIgnoreCase(myaction)) {
			myforward = ckanbaomingxixi(mapping, form, request, response);
		} else if ("UPLOAD".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = upload(mapping, form, request, response);
		} else if ("check_init".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = check_init(mapping, form, request, response);
		} else if ("checkOne".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = checkOne(mapping, form, request, response);
		}else if ("danweishezhi_init".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = danweishezhiInit(mapping, form, request, response);
		}else if ("baocundanwei".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = baocundanwei(mapping, form, request, response);
		}else if ("check_init_check".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = check_init_check(mapping, form, request, response);
		}else if ("fpkc".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = fpkc(mapping, form, request, response);
		}else if ("resetKC".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = resetKC(mapping, form, request, response);
		}else if ("ckkcfp".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = ckkcfp(mapping, form, request, response);
		}else if ("kczwxq".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = kczwxq(mapping, form, request, response);
		}else if ("checkdwxz".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = checkdwxz(mapping, form, request, response);
		}else if ("zpclgj".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = zpclgj(mapping, form, request, response);
		}else if ("getdata".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = getdata(mapping, form, request, response);
		}else if ("getDwxzAndDwjb".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = getDwxzAndDwjb(mapping, form, request, response);
		}else if ("baokaodanweiguanli".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = baokaodanweiguanli(mapping, form, request, response);
		}else if ("modifyDanWei".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = modifyDanWei(mapping, form, request, response);
		}else if ("bcdwglxx".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = bcdwglxx(mapping, form, request, response);
		}else if ("checksfhygdw".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = checksfhygdw(mapping, form, request, response);
		}else if ("addDanWei".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = addDanWei(mapping, form, request, response);
		}else if ("chaxundw".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = chaxundw(mapping, form, request, response);
		}else if ("chaxundwdo".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = chaxundwdo(mapping, form, request, response);
		}else if ("shangchuankuang".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = shangchuankuang(mapping, form, request, response);
		}else if ("shangbaoQuery".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = shangbaoQuery(mapping, form, request, response);
		}else if ("shnagbaocx_query".equalsIgnoreCase(myaction)) {// ��ʾ
			myforward = shnagbaocx_query(mapping, form, request, response);
		}else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}
	/**
	 * �ϱ���ѯ    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����11:00:43
	 */
	private ActionForward shnagbaocx_query(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		try {
			String operatePoint = "success";
			Map actionFormMap = ((DynaActionForm) form).getMap();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
			String districtId = saveSessionBeanForm.getDistrictId();
			int length = PAGE_LENGTH;
			int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
			int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
			String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
			String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
			if (exportAllFlag.equals("true")) {
				length = size;
			}
			int[] pagePra = { offset, length, size };
			String year = exelawtipstaffexamBO.getYear(districtId);
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.getBmsj(year, districtId);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			request.setAttribute("year", year);
			String operate = " �ύ�ϱ� ";
			int checkupFlag = 0;
			boolean isHedingDistrict = exelawtipstaffexamBO.isHedingDistrict(districtId);
			if (isHedingDistrict == true) {
				operate = " �˶� ";
				operatePoint = "view";
			}
			List gagccList = new ArrayList();
			checkupFlag = exelawtipstaffexamBO.isCanCheckUp(districtId);
			int shengFlag = exelawtipstaffexamBO.isSheng(districtId);
			gagccList = exelawtipstaffexamBO.performCheckUpqueryInit(pagePra, districtId);
			List gaList = new ArrayList();
			List gccList = new ArrayList();
			for (int i = 0; i < gagccList.size(); i++) {
				Object[] o = (Object[]) gagccList.get(i);
				ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
				zfryzgksApply = (ZfryzgksApply) o[0];
				ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
				MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				gaList.add(zfryzgksApplyVO);
				ZfryzgksCheckControl zfryzgksCheckControl = new ZfryzgksCheckControl();
				MyBeanUtils.copyPropertiesFromDBToPage(zfryzgksCheckControl);
				gccList.add(zfryzgksCheckControl);
			}
			request.setAttribute("shengFlag", Integer.valueOf(shengFlag));
			request.setAttribute("isHedingDistrict", new Boolean(isHedingDistrict));
			request.setAttribute("checkupFlag", new Integer(checkupFlag));
			request.setAttribute("operate", operate);
			request.setAttribute("BASEINFOLIST", gaList);
			request.setAttribute("gccList", gccList);
			request.setAttribute("isQueryResult", "false");
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", gaList);
				String moban = "DaiShangbao";
				if (isHedingDistrict) {
					moban = "BujiDaiShangbao";
				}
				exportPath = PoiExcel.getPoiExcel(moban, map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			return mapping.findForward(operatePoint);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}
	/**
	 * ��ת�ϱ���ѯҳ��  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����10:33:50
	 */
	private ActionForward shangbaoQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		String  districtId = saveSessionBeanForm.getDistrictId();
		String  districtName = saveSessionBeanForm.getDistrictName();
		request.setAttribute("districtId", districtId);
		request.setAttribute("districtName", districtName);
		return mapping.findForward("success");
	}
	/**
	 * �ϴ�������    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����5:59:54
	 */
	private ActionForward shangchuankuang(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("success");
	}
	private ActionForward chaxundwdo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		int length = 20;
		int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
		String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
		String districtId= StaticMethod.nullObject2String(request.getParameter("districtId"));
		String dwmc= StaticMethod.nullObject2String(request.getParameter("dwmc"));
		String operatePoint = "success";
		if (exportAllFlag.equals("true")) {
			length = size;
		}
		int[] pagePra = { offset, length, size };
		try {
			List list = exelawtipstaffexamBO.getqueryBaokaoDanWeiList(pagePra,districtId,dwmc);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", list);
				exportPath = PoiExcel.getPoiExcel("chaxunmoban", map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("list", list);
		}  catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (RowsExceededException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (WriteException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (BiffException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward(operatePoint);
	}
	/**
	 * ������λ��ѯ    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����11:31:03
	 */
	private ActionForward chaxundw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		String districtId = StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictId());
		String districtName = StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictName());
		String shenfenId = districtId.substring(0, 2)+"0000";
		TawDistrictBO tawDistrictBO = new TawDistrictBO();
		String shengfenName = tawDistrictBO.getDistrictNameById(shenfenId);
		request.setAttribute("shengfenName", shengfenName);
		request.setAttribute("districtName", districtName);
		request.setAttribute("shenfenId", shenfenId);
		return mapping.findForward("success");
	}
	/**
	 * ��ת������λ   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����4:34:36
	 */
	private ActionForward addDanWei(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		String districtId = StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictId());
		String districtName = StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictName());
		String shenfenId = districtId.substring(0, 2)+"0000";
		request.setAttribute("districtId", districtId);
		request.setAttribute("districtName", districtName);
		request.setAttribute("shenfenId", shenfenId);
		return mapping.findForward("success");
	}
	/**
	 * ��֤��д�ĵ�λ�����Ƿ�����ϵͳ��    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����4:03:48
	 */
	private ActionForward checksfhygdw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		String message = "";
		JSONObject resultObj = JSONObject.fromObject("{}");
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			String districtId =StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictId());
			String dwmc = StaticMethod.nullObject2String(request.getParameter("dwmc"));
			String shenfenId = districtId.substring(0, 2)+"0000";
			TawDistrictBO tawDistrictBO = new TawDistrictBO();
			String shenfenName = tawDistrictBO.getDistrictNameById(shenfenId);
			String districtName = tawDistrictBO.getDistrictNameById(districtId);
			ZfryzgksDwgl zfryzgksDwgl = exelawtipstaffexamBO.checkdwsfyy(districtId,dwmc);
			if(!StaticMethod.nullObject2String(zfryzgksDwgl.getId()).equals("")){
					message="�õ�λ��'"+dwmc+"'���Ѿ��ڡ�'"+shenfenName+"'�������д��ڣ�������������¸õ�λΪ��'"+districtName+"'�������¡���ȷ����д�ĵ�λ�����Ƿ���ȷ��";
			}else{
					message="�õ�λ��'"+dwmc+"'���ڡ�'"+shenfenName+"'�������в����ڣ������������棡";
			}
			resultObj.put("id", zfryzgksDwgl.getId());
			resultObj.put("message", message);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	/**
	 * ���浥λ������Ϣ   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����3:10:13
	 */
	private ActionForward bcdwglxx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			 exelawtipstaffexamBO.saveOrUpdateZfryzgksDwgl(actionFormMap);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("success");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}
	/**
	 * �޸�/������λ    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����10:36:33
	 */
	private ActionForward modifyDanWei(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		String districtId = StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictId());
		String shenfenId = districtId.substring(0, 2)+"0000";
		try {
			String danweiId = StaticMethod.nullObject2String(request.getParameter("id"));
			ZfryzgksDwgl zfryzgksDwgl = new ZfryzgksDwgl();
			String districtName="";
			if(!danweiId.equals("")){
				zfryzgksDwgl = exelawtipstaffexamBO.getDanWeiById(danweiId);
				TawDistrictBO tawDistrictBO = new TawDistrictBO();
				districtName = tawDistrictBO.getDistrictNameById(zfryzgksDwgl.getDistrictId());
			}
			request.setAttribute("districtId", districtId);
			request.setAttribute("districtName", districtName);
			request.setAttribute("shenfenId", shenfenId);
			request.setAttribute("zfryzgksDwgl", zfryzgksDwgl);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} 
		return mapping.findForward("success");
	}
	/**
	 * ������λ����   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����9:46:22
	 */
	private ActionForward baokaodanweiguanli(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		int length = 20;
		int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
		String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
		String districtId= StaticMethod.nullObject2String(saveSessionBeanForm.getDistrictId());
		String operatePoint = "success";
		if (exportAllFlag.equals("true")) {
			length = size;
		}
		int[] pagePra = { offset, length, size };
		try {
			List list = exelawtipstaffexamBO.getBaokaoDanWeiList(pagePra,districtId);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", list);
				exportPath = PoiExcel.getPoiExcel("chaxunmoban", map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("list", list);
		}  catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (RowsExceededException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (WriteException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (BiffException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward(operatePoint);
	}
	/**
	 * ��λ��Ϣ����    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����9:08:54
	 */
	private ActionForward getdata(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String sqdu = StaticMethod.nullObject2String(request.getParameter("sqdu"));
        try {  
        	ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
            String jsonValue=exelawtipstaffexamBO.findSqdwmc(sqdu);  
            response.getWriter().write(jsonValue);  
        } catch (Exception e) {  
        	this.generalMessage(request, e);
			return mapping.findForward("failure");
        }  
        return null;  
	}
	/**
	 * ��λ��Ϣ����    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����9:08:54
	 */
	private ActionForward getDwxzAndDwjb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		JSONObject resultObj = JSONObject.fromObject("{}");
		String message = "";
        try {  
        	String dwmc = java.net.URLDecoder.decode(StaticMethod.nullObject2String(request.getParameter("dwmc")), "utf-8");
        	String sqdu = StaticMethod.nullObject2String(request.getParameter("sqdu"));
        	request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Map map = exelawtipstaffexamBO.getDwxzAndDwjb(dwmc,sqdu);
			resultObj.put("map", map);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "�޸ĵ�λ���ƴ���");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "�޸ĵ�λ���ƴ���");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	/**
	 * ��ת��Ƭ�����߽���    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����3:18:38
	 */
	private ActionForward zpclgj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("success");
	}
	/**
	 * ��鵥λ�Ƿ�����   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����11:24:18
	 */
	private ActionForward checkdwxz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		String message = "true";
		JSONObject resultObj = JSONObject.fromObject("{}");
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			String districtId = saveSessionBeanForm.getDistrictId();
			ZfryzgksDqdwb zfryzgksDqdwb = exelawtipstaffexamBO.getzfryzgksDqdwb(districtId);
			if(StaticMethod.nullObject2String(zfryzgksDqdwb.getId()).equals("")){
				message="false";
			}
			resultObj.put("message", message);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	/***
	 * ������λ����    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����5:37:37
	 */
	private ActionForward kczwxq(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		try {
			String kcId = StaticMethod.nullObject2String(request.getParameter("kcid"));
			ZfryksKc zfryksKc = exelawtipstaffexamBO.getckById(kcId);
			String kch = zfryksKc.getKch().toString();
			List kcfbList = exelawtipstaffexamBO.getkcfbList(kcId);
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.getZfryzgksKcglByKcId(kcId);
			if (null != zfryzgksKcgl) {
				request.setAttribute("kaoquName", zfryzgksKcgl.getKaoquName());
				request.setAttribute("kaodian", zfryzgksKcgl.getKaodian());
				request.setAttribute("kddz", zfryzgksKcgl.getKddz());
			}
			request.setAttribute("kch", kch);
			request.setAttribute("kcfbList", kcfbList);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} 
		return mapping.findForward("success");
	}
	/***
	 * �鿴�����ֲ����   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����2:13:20
	 */
	private ActionForward ckkcfp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		try {
			String year = exelawtipstaffexamBO.getYear(saveSessionBeanForm.getDistrictId());
			String districtIds = saveSessionBeanForm.getDistrictId().substring(0, 2);
			Map kcfpMapList = exelawtipstaffexamBO.getKcfpList(year,districtIds + "0000");
			request.setAttribute("kcfpMapList", kcfpMapList);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * ��ѯ�Ƿ���ʹ�õ���ʹ��ִ��֤��   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk 
	 * @Create Date 2017 ����2:32:23
	 */
	private ActionForward check_init_check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if(saveSessionBeanForm == null ){return  mapping.findForward("timeout");}
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		String nsyzfzh = request.getParameter("nsyzfzh");
		String xm = request.getParameter("xm");
		try {
			boolean  isCanUse =  exelawtipstaffexamBO.checkNsyzfzh(nsyzfzh,xm);
			request.setAttribute("isCanUse", Boolean.valueOf(isCanUse));
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}


	/**
	 * ���俼��    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����3:55:38
	 */
	private ActionForward fpkc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		String message = "";
		JSONObject resultObj = JSONObject.fromObject("{}");
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			String year = exelawtipstaffexamBO.getYear(saveSessionBeanForm.getDistrictId());
			String districtIds = saveSessionBeanForm.getDistrictId().substring(0, 2);
			ZfryKcglBO zfryKcglBO = new ZfryKcglBO();
			ResultMessage resultMessage = zfryKcglBO.distribute(year, districtIds + "0000");
			if(resultMessage.getStatus()!=0){
				message = resultMessage.getMessage();
			}else{
				message = resultMessage.getMessage();
			}
			resultObj.put("message", message);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ���ÿ���    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����3:55:38
	 */
	private ActionForward resetKC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		String message = "";
		JSONObject resultObj = JSONObject.fromObject("{}");
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			String year = exelawtipstaffexamBO.getYear(saveSessionBeanForm.getDistrictId());
			String districtId = saveSessionBeanForm.getDistrictId().substring(0, 2);
			ZfryKcglBO zfryKcglBO = new ZfryKcglBO();
			ResultMessage resultMessage = zfryKcglBO.resetKC(year, districtId + "0000");
			message = resultMessage.getMessage();
			resultObj.put("message", message);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "���ÿ�������");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "���ÿ�������");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}


	/**
	 * ������˷���    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk 
	 * @Create Date 2017 ����6:09:59
	 */
	private ActionForward checkOne(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) actionForm).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.checkOne();
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * ��˳�ʼ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����5:23:21
	 */
	private ActionForward check_init(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) actionForm).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		String id  = request.getParameter("id");
		try {
			ZfryzgksApply zfryzgksApply = exelawtipstaffexamBO.viewById(id);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("success");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}
	

	/**
	 * ���浥λ   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����5:35:56
	 */
	private ActionForward baocundanwei(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			String url = request.getContextPath() + "/exelawtipstaffexam/kaoshenggl/danweishezhi_init.do";
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Map actionFormMap = ((DynaActionForm) form).getMap();
			ZfryzgksDqdwb zfryzgksDqdwb = new ZfryzgksDqdwb();
			exelawtipstaffexamBO.savezfryzgksDqdwb(zfryzgksDqdwb,actionFormMap);
			response.sendRedirect(url);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	/**
	 * ��λ����    
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw 
	 * @Create Date 2017 ����5:04:51
	 */
	private ActionForward danweishezhiInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			String districtId = saveSessionBeanForm.getDistrictId();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			ZfryzgksDqdwb zfryzgksDqdwb = new ZfryzgksDqdwb();
			zfryzgksDqdwb = exelawtipstaffexamBO.getzfryzgksDqdwb(districtId);
			String userName = saveSessionBeanForm.getWrf_UserID();
			request.setAttribute("districtId", districtId);
			request.setAttribute("userName", userName);
			request.setAttribute("zfryzgksDqdwb", zfryzgksDqdwb);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/***
	 * �鿴����������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����3:43:59
	 */
	private ActionForward ckanbaomingxixi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			String flag = "true";
			String applyId = StaticMethod.nullObject2String(request.getParameter("applyId"));
			zfryzgksApply = exelawtipstaffexamBO.chakanZfryzgksApply(applyId);
			// ��ѯ�������ݵ�����״̬��
			String districtId = zfryzgksApply.getSqdu();
			ZfryzgksCheckControlVO zfryzgksCheckControlVO = exelawtipstaffexamBO.getZfryzgksCheckControlByApplyId(applyId);
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.chakanzfryzgksKcgl(zfryzgksApply.getKcid());
			ZfryzgksKd zfryzgksKd = exelawtipstaffexamBO.chakanzfryzgksKd(zfryzgksKcgl.getKqid());
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.chakankaoshishijian(nd, districtId);
			request.setAttribute("zfryzgksCheckControlVO", zfryzgksCheckControlVO);
			request.setAttribute("zfryzgksKd", zfryzgksKd);
			request.setAttribute("zfryzgksKcgl", zfryzgksKcgl);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
			flag = exelawtipstaffexamBO.getdaishenhediqu(zfryzgksApply.getId(), flag);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
			request.setAttribute("dsdq", zfryzgksApply.getSqdumc());
			request.setAttribute("flag", flag);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
		} catch (HibernateException e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * �ⲿ��ӡ׼��֤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����6:23:41
	 */
	private ActionForward wbdayingzhunkaozheng(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			String sqId = StaticMethod.nullObject2String(request.getParameter("sqid"));
			ZfryzgksApply zfryzgksApply = exelawtipstaffexamBO.chakanZfryzgksApply(sqId);
			String districtId = zfryzgksApply.getSqdu();
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.chakanzfryzgksKcgl(zfryzgksApply.getKcid());
			ZfryzgksKd zfryzgksKd = exelawtipstaffexamBO.chakanzfryzgksKd(zfryzgksKcgl.getKqid());
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.chakankaoshishijian(nd, districtId);
			request.setAttribute("zfryzgksKd", zfryzgksKd);
			request.setAttribute("zfryzgksKcgl", zfryzgksKcgl);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
		} catch (HibernateException e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ��ӡ׼��֤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����4:12:26
	 */
	private ActionForward dayingzhunkaozheng(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		try {
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String districtId = saveSessionBeanForm.getDistrictId();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			String sqId = StaticMethod.nullObject2String(request.getParameter("sqId"));
			String kqId = StaticMethod.nullObject2String(request.getParameter("kqId"));
			String kdId = StaticMethod.nullObject2String(request.getParameter("kdId"));
			ZfryzgksApply zfryzgksApply = exelawtipstaffexamBO.chakanZfryzgksApply(sqId);
			ZfryzgksKd zfryzgksKd = exelawtipstaffexamBO.chakanzfryzgksKd(kqId);
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.chakanzfryzgksKcgl(kdId);
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.chakankaoshishijian(nd, districtId);
			PrintConfig printConfig = exelawtipstaffexamBO.setPrintConfigForzfryzgksApply(request.getContextPath(), zfryzgksApply);
			request.setAttribute("printConfig", printConfig);
			request.setAttribute("zfryzgksKd", zfryzgksKd);
			request.setAttribute("zfryzgksKcgl", zfryzgksKcgl);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
		} catch (HibernateException e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ��ӡ׼��֤ �б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����3:43:00
	 */
	private ActionForward dayinzhunkaozhengInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String districtId = saveSessionBeanForm.getDistrictId();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			request.setAttribute("nd", nd);
			List kdryList = exelawtipstaffexamBO.dayinzhunkazhengList(districtId, nd);
			request.setAttribute("kdryList", kdryList);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * �鿴������Ա
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����2:39:18
	 */
	private ActionForward ckkdry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			request.setAttribute("nd", nd);
			String kcId = StaticMethod.nullObject2String(request.getParameter("kcId"));
			List kdryList = exelawtipstaffexamBO.ckkdryList(kcId);
			request.setAttribute("kdryList", kdryList);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ѡ����ʱ�鿴�ͽ������Ĵ�ѡ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����8:52:05
	 */
	private ActionForward checkjjkq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		JSONObject resultObj = JSONObject.fromObject("{}");
		String message = "";
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String kqid = StaticMethod.nullObject2String(request.getParameter("kqid"));
			Map map = exelawtipstaffexamBO.checkjjkq(kqid);
			resultObj.put("map", map);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "ɾ����������");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "ɾ����������");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ѡ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����8:40:03
	 */
	private ActionForward kaxz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			String districtId = saveSessionBeanForm.getDistrictId();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			List kqList = exelawtipstaffexamBO.choosekq(districtId);
			request.setAttribute("kqList", kqList);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ѡ��ͽ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����8:23:22
	 */
	private ActionForward jjkqxz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			String districtId = saveSessionBeanForm.getDistrictId();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String kaoqu = StaticMethod.nullObject2String(request.getParameter("kaoqu"));
			List jjkqList = exelawtipstaffexamBO.choosejjkq(districtId, kaoqu);
			request.setAttribute("jjkqList", jjkqList);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author dwl
	 * @Create Date 2017 ����4:42:53
	 */
	private ActionForward tj_init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.getTjList();
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����7:48:09
	 */
	private ActionForward saveKjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.saveJgfs();
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ���Ժϸ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����6:07:51
	 */
	private ActionForward hgfssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		ZfryzgksJgfs zfryzgksJgfs = new ZfryzgksJgfs();
		String forward = "view";
		if (saveSessionBeanForm.getDistrictId().equals("000000")) {
			forward = "success";
		}
		try {
			zfryzgksJgfs = exelawtipstaffexamBO.getJgfs();
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		request.setAttribute("zfryzgksJgfs", zfryzgksJgfs);
		return mapping.findForward(forward);
	}
	
	private ActionForward cx_query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, "000000");
			// ��ѯ��ǰ��� ���û�У���ѯ��һ���
			if(null == zfryksBmsj.getNiandu()){
				nd = String.valueOf(a.get(Calendar.YEAR) - 1);
				zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, "000000");
			}
			boolean canUpdate = exelawtipstaffexamBO.checkBmsjForUpdate(zfryksBmsj);
			request.setAttribute("canUpdate", Boolean.valueOf(canUpdate));
		} catch (HibernateException e1) {
			e1.printStackTrace();
		}
		
		String districtId = saveSessionBeanForm.getDistrictId();
		
		int length = 20;
		int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		String sfZz = StaticMethod.nullObject2String(request.getParameter("zzType"));// ����ȫ����ʾ��
		String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
		String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
		String operatePoint = "success";
		if (exportAllFlag.equals("true")) {
			length = size;
		}
		int[] pagePra = { offset, length, size };
		try {
			List list = exelawtipstaffexamBO.getQueryList(pagePra);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String attPath = servlet.getServletContext().getRealPath("/attachmentfiles")+  File.separator + "zfryks" + File.separator;
				String exportPath = "";
				//������Ƭ�ļ�
				if (districtId.equalsIgnoreCase("000000")) {
					if("Zz".equals(sfZz)){
						list = exelawtipstaffexamBO.exportFileByQueryList(list,attPath,districtId);
						map.put("list0", list);
						exportPath = exelawtipstaffexamBO.getPoiExcel("chaxunmobanForBu", map, path,list,attPath);
					}else{
						map.put("list0", list);
						exportPath = PoiExcel.getPoiExcel("chaxunmobanForBu", map, path);
					}
					
				}else{
					if("Zz".equals(sfZz)){
						list = exelawtipstaffexamBO.exportFileByQueryList(list,attPath,districtId);
						map.put("list0", list);
						exportPath = exelawtipstaffexamBO.getPoiExcel("chaxunmoban", map, path,list,attPath);
					}else{
						map.put("list0", list);
						exportPath = PoiExcel.getPoiExcel("chaxunmoban", map, path);
					}
					
				}
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("list", list);
			if (districtId.equalsIgnoreCase("000000")) {
				request.setAttribute("showChengji", new Boolean(true));
			} 
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (RowsExceededException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (WriteException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (BiffException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}catch (SQLException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}catch (IOException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward(operatePoint);
	}
	
	/**
	 * ��ѯ��ʼ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����4:04:26
	 */
	private ActionForward cx_init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		String  districtId = saveSessionBeanForm.getDistrictId();
		String  districtName = saveSessionBeanForm.getDistrictName();
		int length = 20;
		int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
		String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
		String operatePoint = "success";
		if (exportAllFlag.equals("true")) {
			length = size;
		}
		int[] pagePra = { offset, length, size };
		try {
			List list = exelawtipstaffexamBO.getQueryList(pagePra);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", list);
				exportPath = PoiExcel.getPoiExcel("chaxunmoban", map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("districtId", districtId);
			request.setAttribute("districtName", districtName);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("list", list);
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (RowsExceededException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (WriteException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (BiffException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward(operatePoint);
	}
	
	/***
	 * �����ɼ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����4:50:47
	 */
	private ActionForward kaoshengchengjilurubaocun(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			String url = request.getContextPath() + "/exelawtipstaffexam/kaoshenggl/chengjiguanl_init.do";
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			String kscj = StaticMethod.nullObject2String(request.getParameter("kscj"));
			exelawtipstaffexamBO.saveKaoshengchengji(id, kscj);
			response.sendRedirect(url);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * �����ɼ�¼��ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����3:45:05
	 */
	private ActionForward kaoshengchengjiluru(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		try {
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			String cz = StaticMethod.nullObject2String(request.getParameter("cz"));
			ZfryzgksApply zfryzgksApply = exelawtipstaffexamBO.chakanZfryzgksApply(id);
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.getzfryzgksKcgl(zfryzgksApply.getKcid());
			request.setAttribute("zfryzgksApply", zfryzgksApply);
			request.setAttribute("zfryzgksKcgl", zfryzgksKcgl);
			request.setAttribute("cz", cz);
		} catch (HibernateException e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		String districtName = saveSessionBeanForm.getDistrictName();
		request.setAttribute("districtName", districtName);
		return mapping.findForward("success");
	}
	
	/***
	 * �����ɼ��б�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @throws CnfmMessageException
	 * @throws SQLException
	 * @throws CacheException
	 * @Create Date 2017 ����3:01:04
	 */
	private ActionForward chengjiguanlInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CnfmMessageException {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
		int length = 20;
		int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
		String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
		String operatePoint = "success";
		if (exportAllFlag.equals("true")) {
			length = size;
		}
		int[] pagePra = { offset, length, size };
		try {
			String districtId = StaticMethod.null2String(saveSessionBeanForm.getDistrictId());
			String remark = StaticMethod.null2String(saveSessionBeanForm.getDistrictRemark());
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			List baokaoshenqingList = exelawtipstaffexamBO.getBaokaoshenqingList(districtId, nd, pagePra, remark);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", baokaoshenqingList);
				exportPath = PoiExcel.getPoiExcel("chengjichaxun", map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("baokaoshenqingList", baokaoshenqingList);
			request.setAttribute("nd", nd);
			return mapping.findForward(operatePoint);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CacheException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (SQLException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (RowsExceededException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (WriteException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (BiffException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
	}
	
	/***
	 * ��ӡ������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����9:57:39
	 */
	private ActionForward dayingkaoshengxinxi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			ZfryzgksApply zfryzgksApply = exelawtipstaffexamBO.chakanZfryzgksApply(id);
			PrintConfig printConfig = exelawtipstaffexamBO.setPrintConfigForzfryzgksApply(request.getContextPath(), zfryzgksApply);
			request.setAttribute("printConfig", printConfig);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
		} catch (HibernateException e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ɾ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����11:08:56
	 */
	private ActionForward shanchuck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		JSONObject resultObj = JSONObject.fromObject("{}");
		String message = "ɾ���ɹ���";
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			Map map = exelawtipstaffexamBO.deletezfryzgksKcgl(id, message);
			resultObj.put("map", map);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "ɾ����������");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "ɾ����������");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	
	/***
	 * �޸Ŀ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����11:02:05
	 */
	private ActionForward xiugaickInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		String message = "";
		JSONObject resultObj = JSONObject.fromObject("{}");
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.getzfryzgksKcgl(id);
			ZfryzgksKd zfryzgksKd = exelawtipstaffexamBO.getzfryzgksKd(zfryzgksKcgl.getKqid());
			Map map = exelawtipstaffexamBO.getsfxgxx(id, message);
			resultObj.put("map", map);
			resultObj.put("tbsj", zfryzgksKcgl.getTbsj().toString());
			resultObj.put("zfryzgksKcgl", zfryzgksKcgl);
			resultObj.put("zfryzgksKd", zfryzgksKd);
			out.print(resultObj);
		} catch (IOException e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} catch (Exception e) {
			BocoLog.error(this, 0, "NormConfigAction.performSaveOrUpdata", e);
			generalError(request, e);
			resultObj.put("error", "��ѯ���ñ����");
			out.print(resultObj);
			return mapping.findForward("failure");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ���濼����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����9:56:54
	 */
	private ActionForward bckcxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			String url = request.getContextPath() + "/exelawtipstaffexam/kaoshenggl/kaochangguanli.do";
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Map actionFormMap = ((DynaActionForm) form).getMap();
			String districtId = saveSessionBeanForm.getDistrictId();
			ZfryzgksKcgl zfryzgksKcgl = new ZfryzgksKcgl();
			exelawtipstaffexamBO.savezfryzgksKcgl(districtId, zfryzgksKcgl, actionFormMap);
			response.sendRedirect(url);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ��ת��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����9:33:40
	 */
	private ActionForward addckInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			// ��õ�ǰ�����û��Ļ�����Ϣ������
			String districtId = StaticMethod.null2String(saveSessionBeanForm.getDistrictId());
			String districtName = StaticMethod.null2String(saveSessionBeanForm.getDistrictName());
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			request.setAttribute("nd", nd);
			request.setAttribute("districtId", districtId);
			request.setAttribute("districtName", districtName);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ��ӡ���ܱ�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����10:01:41
	 */
	private ActionForward dayinhuizongbiao_init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		Calendar now = Calendar.getInstance();
		//ʹ��Date
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		String nd = StaticMethod.nullObject2String(request.getParameter("nd"));
		String districtName = saveSessionBeanForm.getDistrictName();
		request.setAttribute("districtName", districtName);
		String forward = "view";
		if (saveSessionBeanForm.getDistrictId().equals(saveSessionBeanForm.getDistrictId().substring(0, 2) + "0000")) {
			forward = "success";
		}
		String exportFlag = StaticMethod.nullObject2String(request.getParameter("exportFlag"));
		if (exportFlag.equals("true")) {
			if(saveSessionBeanForm.getDistrictId().equals(saveSessionBeanForm.getDistrictId().substring(0, 2) + "0000")){
				forward = "excel";
			}else{
				forward = "excel2";
			}
		}
		try {
			Calendar a = Calendar.getInstance();
			if("".equals(nd)){
				nd = String.valueOf(a.get(Calendar.YEAR));
			}
			List list = exelawtipstaffexamBO.getHuiZongBiao(nd);
			request.setAttribute("list", list);
			request.setAttribute("nd", nd);
			request.setAttribute("now", sdf.format(d));
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward(forward);
	}
	
	/**
	 * ��˲鿴����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����4:19:20
	 */
	private ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		String id = request.getParameter("id");
		
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		try {
			zfryzgksApply = exelawtipstaffexamBO.viewById(id);
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		request.setAttribute("zfryzgksApply", zfryzgksApply);
		String districtId = saveSessionBeanForm.getDistrictId();
		if (districtId.equalsIgnoreCase("000000")) {
			request.setAttribute("showChengji", new Boolean(true));
		} 
		
		return mapping.findForward("success");
	}
	
	/**
	 * ����޸ı���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����3:07:20
	 */
	private ActionForward modifySave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.updateApply();
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	private ActionForward modifyUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			String nsyzfzh = StaticMethod.nullObject2String(actionFormMap.get("nsyzfzh"));
			String xm = StaticMethod.nullObject2String(actionFormMap.get("xm"));
			if (exelawtipstaffexamBO.checkNsyzfzh(nsyzfzh, xm)) {
				exelawtipstaffexamBO.updateApplyByManager();
				String id = StaticMethod.nullObject2String(actionFormMap.get("id"));
				String url = request.getContextPath() + "/exelawtipstaffexam/shenhe/view.do?id=" + id;
				response.sendRedirect(url);
			} else {
				throw new CnfmMessageException("��ʹ��ִ����Ա֤��" + nsyzfzh + "�ظ�, ����е�����");
			}
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	
	
	/**
	 * ���ɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����2:31:57
	 */
	private ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		String id = request.getParameter("id");
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.deleteApplyById(id);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ����޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����1:40:50
	 */
	private ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		String id = request.getParameter("id");
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
		try {
			TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO();
			zfryzgksApply = exelawtipstaffexamBO.getModifyById(id);
			List list = tawAttachmentDAO.list(zfryzgksApply.getZp());
			TawAttachment tawAttachment = new TawAttachment();
			if(list.size()>0){
				tawAttachment = (TawAttachment) list.get(0);
			}
			request.setAttribute("tawAttachment", tawAttachment);
			
			ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, "000000");
			// ��ѯ��ǰ��� ���û�У���ѯ��һ���
			if(null == zfryksBmsj.getNiandu()){
				nd = String.valueOf(a.get(Calendar.YEAR) - 1);
				zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, "000000");
			}
			boolean canUpdate = exelawtipstaffexamBO.checkBmsjForUpdate(zfryksBmsj);
			request.setAttribute("canUpdate", Boolean.valueOf(canUpdate));
		} catch (SQLException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		request.setAttribute("zfryzgksApply", zfryzgksApply);
		return mapping.findForward("success");
	}
	
	/**
	 * ���������б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����4:46:33
	 */
	private ActionForward kaochangguanli(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
		int length = 20;
		int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
		String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
		String operatePoint = "success";
		if (exportAllFlag.equals("true")) {
			length = size;
		}
		int[] pagePra = { offset, length, size };
		try {
			String districtName = StaticMethod.null2String(saveSessionBeanForm.getDistrictName());
			String districtId = StaticMethod.null2String(saveSessionBeanForm.getDistrictId());
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			List kcList = exelawtipstaffexamBO.getkcxxListByDq(districtId, nd, pagePra);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", kcList);
				exportPath = PoiExcel.getPoiExcel("kaoqu", map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("nd", nd);
			request.setAttribute("districtId", districtId);
			request.setAttribute("districtName", districtName);
			request.setAttribute("kcList", kcList);
			return mapping.findForward(operatePoint);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
	}
	
	/***
	 * �Ѿ���˺�ı�����Ϣֻ�ܲ鿴
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����3:55:15
	 */
	private ActionForward chakanbaomingxixi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String id = request.getParameter("id");
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			zfryzgksApply = exelawtipstaffexamBO.chakanZfryzgksApply(id);
			ZfryzgksCheckControlVO zfryzgksCheckControlVO = exelawtipstaffexamBO.getZfryzgksCheckControlByApplyId(zfryzgksApply.getId());
			String districtId = zfryzgksApply.getSqdu();
			ZfryzgksKcgl zfryzgksKcgl = exelawtipstaffexamBO.chakanzfryzgksKcgl(zfryzgksApply.getKcid());
			ZfryzgksKd zfryzgksKd = exelawtipstaffexamBO.chakanzfryzgksKd(zfryzgksKcgl.getKqid());
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.chakankaoshishijian(nd, districtId);
			request.setAttribute("zfryzgksCheckControlVO", zfryzgksCheckControlVO);
			request.setAttribute("zfryzgksKd", zfryzgksKd);
			request.setAttribute("zfryzgksKcgl", zfryzgksKcgl);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			String flag = "true";
			flag = exelawtipstaffexamBO.getdaishenhediqu(zfryzgksApply.getId(), flag);
			request.setAttribute("zfryzgksApply", zfryzgksApply);
			request.setAttribute("dsdq", zfryzgksApply.getSqdumc());
			request.setAttribute("flag", flag);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ����������Ϣ¼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����9:51:59
	 */
	private ActionForward baokao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			/*
			 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm"); if (saveSessionBeanForm == null) { return
			 * mapping.findForward("timeout"); }
			 */
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Map actionFormMap = ((DynaActionForm) form).getMap();
			ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
			String dsdq = StaticMethod.nullObject2String(actionFormMap.get("sqdumc"));
			zfryzgksApply = exelawtipstaffexamBO.saveZfryzgksApply(zfryzgksApply, actionFormMap);
			String applyId = zfryzgksApply.getId();
			String url = request.getContextPath() + "/zfryks/kaoshenggl/ckanbaomingxixi.do?applyId=" + applyId;
			response.sendRedirect(url);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
		
	}
	
	/***
	 * ����������Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����9:51:59
	 */
	private ActionForward baokaoUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			Map actionFormMap = ((DynaActionForm) form).getMap();
			ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
			zfryzgksApply = exelawtipstaffexamBO.updateZfryzgksApply(zfryzgksApply, actionFormMap);
			String applyId = zfryzgksApply.getId();
			String url = request.getContextPath() + "/zfryks/kaoshenggl/ckanbaomingxixi.do?applyId=" + applyId;
			response.sendRedirect(url);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
		
	}
	
	/***
	 * �����Ǽ���Ϣ��д
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����3:24:39
	 */
	private ActionForward djxxtx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO();
			String ksxm = java.net.URLDecoder.decode(StaticMethod.nullObject2String(request.getParameter("ksxm")), "utf-8");
			String kssfzhm = StaticMethod.nullObject2String(request.getParameter("kssfzhm"));
			ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
			zfryzgksApply = exelawtipstaffexamBO.getzfryzgksApplyBysfzh(kssfzhm);
			ZfryzgksCheckControlVO zfryzgksCheckControlVO = exelawtipstaffexamBO.getZfryzgksCheckControlByApplyId(zfryzgksApply.getId());
			List list = tawAttachmentDAO.list(zfryzgksApply.getZp());
			TawAttachment tawAttachment = new TawAttachment();
			if(list.size()>0){
				tawAttachment = (TawAttachment) list.get(0);
			}
			request.setAttribute("zfryzgksApply", zfryzgksApply);
			request.setAttribute("tawAttachment", tawAttachment);
			ZfryksBmsj zfryksBmsj = new ZfryksBmsj();
			Calendar a = Calendar.getInstance();
			String nd = String.valueOf(a.get(Calendar.YEAR));
			zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, "000000");
			// ��ѯ��ǰ��� ���û�У���ѯ��һ���
			if(null == zfryksBmsj.getNiandu()){
				nd = String.valueOf(a.get(Calendar.YEAR) - 1);
				zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, "000000");
			}
			boolean  canApply = exelawtipstaffexamBO.checkBmsj(zfryksBmsj);
			boolean canUpdate = exelawtipstaffexamBO.checkBmsjForUpdate(zfryksBmsj);
			request.setAttribute("canApply", Boolean.valueOf(canApply));
			request.setAttribute("canUpdate", Boolean.valueOf(canUpdate));
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			String birth = kssfzhm.substring(6, 12);
			String sex = kssfzhm.substring(16, 17);
			if (Integer.parseInt(sex) % 2 == 0) {
				sex = "Ů";
			} else {
				sex = "��";
			}
			request.setAttribute("zfryzgksCheckControlVO", zfryzgksCheckControlVO);
			request.setAttribute("nd", nd);
			request.setAttribute("birth", birth);
			request.setAttribute("sex", sex);
			request.setAttribute("ksxm", ksxm);
			request.setAttribute("kssfzhm", kssfzhm);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ���濼��ʱ�䡢��ȡ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����4:27:17
	 */
	private ActionForward saveKssj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.saveKssj();
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ���Բ������ó�ʼ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����4:08:46
	 */
	private ActionForward kscsszInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		Calendar a = Calendar.getInstance();
		String nd = String.valueOf(a.get(Calendar.YEAR));
		String districtId = saveSessionBeanForm.getDistrictId();
		String forword = "success";
		if (!districtId.equals("000000")) {
			forword = "view";
		}
		try {
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.getBmsj(nd, districtId);
			String districtName = new com.boco.eoms.jbzl.bo.TawDistrictBO().getDistrictNameById(zfryksBmsj.getDistrictId());
			request.setAttribute("districtName", districtName);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		String districtIdName = saveSessionBeanForm.getDistrictName();
		request.setAttribute("districtIdName", districtIdName);
		request.setAttribute("districtId", districtId);
		return mapping.findForward(forword);
	}
	
	/**
	 * �ϱ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����3:41:48
	 */
	private ActionForward check_up_more_no(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.checkUpMoreNo();
		} catch (IllegalAccessException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (InvocationTargetException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * �ϱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����2:48:49
	 */
	private ActionForward check_up_result(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) form).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.checkUpMore();
		} catch (SQLException e) {
			e.printStackTrace();
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (HibernateException e) {
			e.printStackTrace();
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			e.printStackTrace();
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ������˲�ͨ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����10:36:02
	 */
	private ActionForward check_more_no(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) actionForm).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		try {
			exelawtipstaffexamBO.checkMoreNo();
		} catch (IllegalAccessException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (InvocationTargetException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ���ͨ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����9:44:15
	 */
	private ActionForward check_more_yes(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		Map actionFormMap = ((DynaActionForm) actionForm).getMap();
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
		// exelawtipstaffexamBO.setActionFormMap(((DynaActionForm) actionForm).getMap());
		try {
			exelawtipstaffexamBO.checkMoreYes();// ���
		} catch (IllegalAccessException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (InvocationTargetException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (CnfmMessageException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		} catch (HibernateException e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ��˳�ʼ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjk
	 * @Create Date 2017 ����8:45:03
	 */
	private ActionForward shenhe_init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		try {
			String districtId = saveSessionBeanForm.getDistrictId();
			int length = PAGE_LENGTH;
			int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
			int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
			String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
			String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
			if (exportAllFlag.equals("true")) {
				length = size;
			}
			int[] pagePra = { offset, length, size };
			Map actionFormMap = ((DynaActionForm) form).getMap();
			ExelawtipstaffexamBO bo = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
			Calendar c = Calendar.getInstance();
			String nd = String.valueOf(c.get(Calendar.YEAR));
			ZfryksBmsj zfryksBmsj = bo.getBmsj(nd, districtId);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			List gaList = new ArrayList();
			// �������б�
			List gagccList = bo.performCheckInit(pagePra, districtId);
			for (int i = 0; i < gagccList.size(); i++) {
				ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
				ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
				zfryzgksApply = (ZfryzgksApply) gagccList.get(i);
				MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				gaList.add(zfryzgksApplyVO);
			}
			request.setAttribute("BASEINFOLIST", gaList);
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			String operatePoint = "success";
			request.setAttribute("pagerHeader", pagerHeader);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", gaList);
				String moban = "shenhe";
				exportPath = PoiExcel.getPoiExcel(moban, map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			return mapping.findForward(operatePoint);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}
	
	/***
	 * ��������ϵͳ������ʼ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����9:15:50
	 */
	private ActionForward baomingInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			// SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
			// if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
			// // ��õ�ǰ�����û��Ļ�����Ϣ������
			// String districtId = StaticMethod.null2String(saveSessionBeanForm.getDistrictId());
			// String districtName = StaticMethod.null2String(saveSessionBeanForm.getDistrictName());
			// request.setAttribute("districtId", districtId);
			// request.setAttribute("districtName", districtName);
		} catch (Exception e) {
			this.generalMessage(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	/***
	 * ����ϵͳ�ϱ���ʼ��ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author glw
	 * @Create Date 2017 ����5:22:53
	 */
	private ActionForward shangbaoInit(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) { return mapping.findForward("timeout"); }
		try {
			String operatePoint = "success";
			Map actionFormMap = ((DynaActionForm) actionForm).getMap();
			ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO(saveSessionBeanForm, actionFormMap);
			String districtId = saveSessionBeanForm.getDistrictId();
			int length = PAGE_LENGTH;
			int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
			int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
			String exportAllFlag = StaticMethod.nullObject2String(request.getParameter("exportAllFlag"));// ����ȫ����ʾ��
			String exportNowPageFlag = StaticMethod.nullObject2String(request.getParameter("exportNowPageFlag"));// ������ҳ��ʾ��
			if (exportAllFlag.equals("true")) {
				length = size;
			}
			int[] pagePra = { offset, length, size };
			String year = exelawtipstaffexamBO.getYear(districtId);
			ZfryksBmsj zfryksBmsj = exelawtipstaffexamBO.getBmsj(year, districtId);
			request.setAttribute("zfryksBmsj", zfryksBmsj);
			request.setAttribute("year", year);
			String operate = " �ύ�ϱ� ";
			int checkupFlag = 0;
			boolean isHedingDistrict = exelawtipstaffexamBO.isHedingDistrict(districtId);
			if (isHedingDistrict == true) {
				operate = " �˶� ";
				operatePoint = "view";
			}
			List gagccList = new ArrayList();
			checkupFlag = exelawtipstaffexamBO.isCanCheckUp(districtId);
			int shengFlag = exelawtipstaffexamBO.isSheng(districtId);
			gagccList = exelawtipstaffexamBO.performCheckUpInit(pagePra, districtId);
			List gaList = new ArrayList();
			List gccList = new ArrayList();
			for (int i = 0; i < gagccList.size(); i++) {
				Object[] o = (Object[]) gagccList.get(i);
				ZfryzgksApply zfryzgksApply = new ZfryzgksApply();
				zfryzgksApply = (ZfryzgksApply) o[0];
				ZfryzgksApplyVO zfryzgksApplyVO = new ZfryzgksApplyVO();
				MyBeanUtils.copyProperties(zfryzgksApplyVO, zfryzgksApply, null);
				gaList.add(zfryzgksApplyVO);
				ZfryzgksCheckControl zfryzgksCheckControl = new ZfryzgksCheckControl();
				MyBeanUtils.copyPropertiesFromDBToPage(zfryzgksCheckControl);
				gccList.add(zfryzgksCheckControl);
			}
			request.setAttribute("shengFlag", Integer.valueOf(shengFlag));
			request.setAttribute("isHedingDistrict", new Boolean(isHedingDistrict));
			request.setAttribute("checkupFlag", new Integer(checkupFlag));
			request.setAttribute("operate", operate);
			request.setAttribute("BASEINFOLIST", gaList);
			request.setAttribute("gccList", gccList);
			request.setAttribute("isQueryResult", "false");
			String url = request.getContextPath() + "/exelawtipstaffexam" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			if (exportAllFlag.equals("true") || exportNowPageFlag.equals("true")) {
				// �����������
				HashMap map = new HashMap();
				String path = servlet.getServletContext().getRealPath("/WEB-INF") + File.separator + "configfiles" + File.separator + "export" + File.separator + "model" + File.separator + "exelawtipstaffexam";
				String exportPath = "";
				map.put("list0", gaList);
				String moban = "DaiShangbao";
				if (isHedingDistrict) {
					moban = "BujiDaiShangbao";
				}
				exportPath = PoiExcel.getPoiExcel(moban, map, path);
				operatePoint = "downexcel";
				request.setAttribute("excelfile", exportPath);
				request.setAttribute("excelfilename", exportPath.substring(exportPath.lastIndexOf(File.separator) + 1, exportPath.length()));
			}
			return mapping.findForward(operatePoint);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}
	
	/***
	 * ����������
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 *             ActionForward
	 * @author glw
	 * @Create Date 2017 ����10:42:40
	 */
	private ActionForward districtTree(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExelawtipstaffexamBO exelawtipstaffexamBO = new ExelawtipstaffexamBO();
		String id = StaticMethod.null2String(request.getParameter("id"), "-1");
		String ids = request.getParameter("ids");
		String template = StaticMethod.null2String(request.getParameter("template"), "deptLinkTemplate");
		ArrayList list = new ArrayList();
		ArrayList nodeList = new ArrayList();
		
		try {
			TawDistrictBO tawDistrictBO = new TawDistrictBO();
			
			TawDistrict tawDistrict = new TawDistrict();
			//if (!id.substring(0, 2).equals("66")) {
				tawDistrict = tawDistrictBO.getDistrict(id);
			//}
			String districtID = "";
			String districtName = "";
			if (tawDistrict != null) {
				districtID = tawDistrict.getDistrictId();
				districtName = tawDistrict.getDistrictName();
			}
			if (districtID.equals(ids)) {
				XtreeNodeVO thisNode = new XtreeNodeVO();
				thisNode.setId(districtID + "");
				thisNode.setText(districtName);
				thisNode.setAction(districtID + "");
				List deptChilds = tawDistrictBO.getSecondDistricts(districtID);
				if (0 < deptChilds.size()) {
					thisNode.setLeaf(0);
					thisNode.setSrc(districtID + "");
				} else {
					thisNode.setLeaf(1);
					thisNode.setSrc("");
				}
				nodeList.add(thisNode);
			} else {
				list = (ArrayList) tawDistrictBO.getSecondDistricts(id);
				
				for (int i = 0; i < list.size(); i++) {
					String subDistrictID = ((TawDistrict) list.get(i)).getDistrictId();
					String subDistrictName = ((TawDistrict) list.get(i)).getDistrictName();
					XtreeNodeVO node = new XtreeNodeVO();
					node.setId(subDistrictID + "");
					node.setText(subDistrictName);
					node.setAction(subDistrictID + "");
					List deptChilds = tawDistrictBO.getSecondDistricts(subDistrictID);
					if (0 < deptChilds.size()) {
						node.setLeaf(0);
						node.setSrc(subDistrictID + "");
					} else {
						// node.setLeaf(StaticVariable.LEAF);
						node.setLeaf(1);
						node.setSrc("");
					}
					//if (!subDistrictID.substring(0, 2).equals("66")) {
						nodeList.add(node);
					//}
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, 101, "����������ͼ����", ex);
		}
		request.setAttribute("list", nodeList);
		return new ActionForward("/baoming/districtCheckboxTemplate.jsp");
	}
	
	/**
	 * @see ��������ͼ��������template��������Ҫת���ģ���ļ���
	 */
	private ActionForward deptTree(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = StaticMethod.null2int(request.getParameter("id"), StaticVariable.ProvinceID);
		// ��idsΪnullʱ����ֵΪһ��������ȡ����ֵ-21
		int ids = StaticMethod.null2int(request.getParameter("ids"), -21);
		String template = StaticMethod.null2String(request.getParameter("template"), "deptLinkTemplate");
		ArrayList list = new ArrayList();
		ArrayList nodeList = new ArrayList();
		
		try {
			TawDeptBO tawDeptBO = new TawDeptBO();
			
			TawDept tawDept = new TawDept();
			tawDept = tawDeptBO.retrieveDept(id);
			int deptID = -21;
			String deptName = "";
			if (tawDept != null) {
				deptID = tawDept.getDeptId();
				deptName = tawDept.getDeptName();
			}
			
			if (deptID == ids) {
				XtreeNodeVO thisNode = new XtreeNodeVO();
				thisNode.setId(deptID + "");
				thisNode.setText(deptName);
				thisNode.setAction(deptID + "");
				List deptChilds = tawDeptBO.getSecondDepts(deptID);
				if (0 < deptChilds.size()) {
					thisNode.setLeaf(0);
					thisNode.setSrc(deptID + "");
				} else {
					thisNode.setLeaf(1);
					thisNode.setSrc("");
				}
				nodeList.add(thisNode);
			} else {
				
				list = (ArrayList) tawDeptBO.getSecondDepts(id);
				
				for (int i = 0; i < list.size(); i++) {
					int subDeptID = ((TawDept) list.get(i)).getDeptId();
					String subDeptName = ((TawDept) list.get(i)).getDeptName();
					
					XtreeNodeVO node = new XtreeNodeVO();
					node.setId(subDeptID + "");
					node.setText(subDeptName);
					node.setAction(subDeptID + "");
					
					List deptChilds = tawDeptBO.getSecondDepts(subDeptID);
					if (0 < deptChilds.size()) {
						node.setLeaf(0);
						node.setSrc(subDeptID + "");
					} else {
						node.setLeaf(StaticVariable.LEAF);
						node.setSrc("");
					}
					nodeList.add(node);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, 101, "����������ͼ����", ex);
		}
		request.setAttribute("list", nodeList);
		return new ActionForward("/baoming/deptCheckboxTemplate.jsp");
	}
	
	/**
	 * TODO(������һ�仰�����������������)
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author wjg
	 * @Create Date 2016 ����2:50:31
	 */
	private ActionForward upload(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		try {
			TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO();
			String fileIdList = StaticMethod.nullObject2String(request.getParameter("filelist"));
			String app = StaticMethod.nullObject2String(request.getParameter("app"));
			if (!fileIdList.equals("")) {
				List list = tawAttachmentDAO.list(fileIdList);
				request.setAttribute("LIST", list);
				String attachmentfilesPath = request.getRealPath("attachmentfiles").replace("attachmentfiles", "");;
				String filePath = attachmentfilesPath + new ReadAttconfig(attachmentfilesPath).getPath(app);
				filePath = filePath.replaceAll("\\\\","\\\\\\\\");
				request.setAttribute("filePath", filePath);
			}
		} catch (Exception e) {
			BocoLog.error(this, 0, "ShipinfoRollbackAction.performInit", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
}
