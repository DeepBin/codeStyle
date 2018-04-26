package com.sky.tzsc.util;

import java.util.List;

public class PrintConfig {
	// 打印页面的URL
	String printUrl;
	
	// 是否可以进行打印的权限控制
	Boolean isCanPrint;
	
	// 打印后，要回调的URL；如：计次等
	String printedCallBackUrl;
	
	// 系统的模块名称
	String module;
	
	// 文件的名称 如：捕捞申请书fishingApply
	String fileName;
	
	// 条形码密文的字符串，按照证书的页顺序进行填写，无的为NULL，有的填写
	String[] barcodeKeys;
	
	// 贴花
	List fishingAppliques;
	
	// 打印份数
	int printCopies;
	
	public String getPrintUrl() {
		return printUrl;
	}
	
	public void setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
	}
	
	public Boolean getIsCanPrint() {
		return isCanPrint;
	}
	
	public void setIsCanPrint(Boolean isCanPrint) {
		this.isCanPrint = isCanPrint;
	}
	
	public String getPrintedCallBackUrl() {
		return printedCallBackUrl;
	}
	
	public void setPrintedCallBackUrl(String printedCallBackUrl) {
		this.printedCallBackUrl = printedCallBackUrl;
	}
	
	public String getModule() {
		return module;
	}
	
	public void setModule(String module) {
		this.module = module;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String[] getBarcodeKeys() {
		return barcodeKeys;
	}
	
	public void setBarcodeKeys(String[] barcodeKeys) {
		this.barcodeKeys = barcodeKeys;
	}
	
	public List getFishingAppliques() {
		return fishingAppliques;
	}
	
	public void setFishingAppliques(List fishingAppliques) {
		this.fishingAppliques = fishingAppliques;
	}
	
	public int getPrintCopies() {
		return printCopies;
	}
	
	public void setPrintCopies(int printCopies) {
		this.printCopies = printCopies;
	}
	
}
