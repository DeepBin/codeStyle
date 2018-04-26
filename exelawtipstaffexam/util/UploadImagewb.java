package com.boco.eoms.exelawtipstaffexam.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.RequestUtils;

public class UploadImagewb extends BodyTagSupport {
	private String idList = "";
	
	private String idField = "";
	
	private String viewFlag = "";
	
	private String appCode = "";
	
	private String ids = "";
	
	protected String name;
	
	protected String property;
	
	protected String scope;
	
	protected String path;
	
	protected int width;
	
	protected int high;
	
	protected String jspUse;
	
	public String getJspUse() {
		return this.jspUse;
	}
	
	public void setJspUse(String jspUse) {
		this.jspUse = jspUse;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProperty() {
		return this.property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getScope() {
		return this.scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public int doStartTag() {
		ServletRequest request = this.pageContext.getRequest();
		
		this.path = ((HttpServletRequest) request).getContextPath();
		if (request.getParameter(this.idList) != null) {
			this.ids = request.getParameter(this.idList);
		} else {
			this.ids = "";
		}
		try {
			if ((this.name != null) && (this.property != null) && (this.scope != null)) {
				this.ids = ((String) RequestUtils.lookup(this.pageContext, this.name, this.property, this.scope));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public int doEndTag() throws JspTagException {
		try {
			String html = "";
			if (!this.viewFlag.equals("Y")) {
				html =

				"<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=\"" + (getHigh() + 100)
						+ "\" SCROLLING=yes SRC='" + this.path + "/zfryks/kaoshenggl/upload.do?app=" + this.appCode
						+ "&idfile=" + this.idField + "&width=" + getWidth() + "&high=" + getHigh() + "&filelist="
						+ this.ids + "'></IFRAME><input type='hidden' " + "name='" + this.idField + "'>";
				if ((this.jspUse != null) && (!this.jspUse.equals(""))) {
					html = "<IFRAME ID=" + this.idField + "IFrame  FRAMEBORDER=0 width='100%' height=\"" + (getHigh() + 150)
							+ "\" SCROLLING=yes SRC='" + this.path + "/zfryks/ksuploadImage/" + this.jspUse + ".jsp?jspUse="
							+ this.jspUse + "&app=" + this.appCode + "&idfile=" + this.idField + "&width=" + getWidth()
							+ "&high=" + getHigh() + "&filelist=" + this.ids + "'></IFRAME><input type='hidden' "
							+ "name='" + this.idField + "'>";
				}
			} else {
				html = "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=\"" + getHigh() + "\" SCROLLING=yes SRC='"
						+ this.path + "/viewImage.do?app=" + this.appCode + "&idfile=" + this.idField
						+ "&filelist=" + this.ids + "'></IFRAME>";
				if ((this.jspUse != null) && (!this.jspUse.equals(""))) {
					html = "<IFRAME ID=" + this.idField + "IFrame  FRAMEBORDER=0 width='100%' height=\"" + getHigh()
							+ "\" SCROLLING=yes SRC='" + this.path + "/zfryks/ksuploadImage/" + this.jspUse + ".jsp?app="
							+ this.appCode + "&idfile=" + this.idField + "&width=" + getWidth() + "&high=" + getHigh()
							+ "&filelist=" + this.ids + "'></IFRAME>";
				}
			}
			this.pageContext.getOut().write(html);
		} catch (Exception localException) {
		}
		return 6;
	}
	
	public String getIdList() {
		return this.idList;
	}
	
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	public String getViewFlag() {
		return this.viewFlag;
	}
	
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	
	public String getIdField() {
		return this.idField;
	}
	
	public void setIdField(String idField) {
		this.idField = idField;
	}
	
	public String getAppCode() {
		return this.appCode;
	}
	
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	
	public int getHigh() {
		if (this.high == 0) {
			this.high = 86;
		}
		return this.high;
	}
	
	public void setHigh(int high) {
		this.high = high;
	}
	
	public int getWidth() {
		if (this.width == 0) {
			this.width = 70;
		}
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}
