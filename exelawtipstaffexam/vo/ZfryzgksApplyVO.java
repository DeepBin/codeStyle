
package com.boco.eoms.exelawtipstaffexam.vo;

import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksApply;



public class ZfryzgksApplyVO extends ZfryzgksApply{
	
	private String  zgqkName; //在岗情况 
	
	private String zjName;
	
	private String whcdName;
	
	private String xbName;
	
	private String zjslfsName;
	

	public String getZjslfsName() {
		if(this.getZjslfs().equals("1")){
			zjslfsName ="初次申领";
		}else{
			zjslfsName ="换领";
		}
		return zjslfsName;
	}

	public void setZjslfsName(String zjslfsName) {
		this.zjslfsName = zjslfsName;
	}

	public String getXbName() {
		if(this.getXb().intValue() ==1){
			xbName = "男";
		}else{
			xbName ="女";
		}
		return xbName;
	}

	public void setXbName(String xbName) {
		this.xbName = xbName;
	}

	public String getZgqkName() {
		if (this.getZgqk().intValue() ==1) {
			zgqkName = "在编";
		} else {
			zgqkName = "聘用";
		}
		return zgqkName;
	}

	public void setZgqkName(String zgqkName) {
		this.zgqkName = zgqkName;
	}
	public String getZjName() {
		if (this.getZj().equals("1")) {
			zjName = "科员";
		} else if(this.getZj().equals("2")){
			zjName = "副科级";
		} else if(this.getZj().equals("3")){
			zjName = "正科级";
		}else if(this.getZj().equals("4")){
			zjName = "副处级";
		}else if(this.getZj().equals("5")){
			zjName = "正处级";
		}else if(this.getZj().equals("6")){
			zjName = "副厅局级";
		}else if(this.getZj().equals("7")){
			zjName = "正厅局级";
		}else if(this.getZj().equals("8")){
			zjName = "省部级";
		}
		return zjName;
	}

	public void setZjName(String zjName) {
		this.zjName = zjName;
	}
/**
 * <option value="1" <c:if test="${zfryzgksApply.whcd==1}">selected</c:if>>小学</option>
            <option value="2" <c:if test="${zfryzgksApply.whcd==2}">selected</c:if>>初中</option>
            <option value="3" <c:if test="${zfryzgksApply.whcd==3}">selected</c:if>>高中</option>
            <option value="4" <c:if test="${zfryzgksApply.whcd==4}">selected</c:if>>中专</option>
            <option value="5" <c:if test="${zfryzgksApply.whcd==5}">selected</c:if>>大专</option>
            <option value="6" <c:if test="${zfryzgksApply.whcd==6}">selected</c:if>>本科</option>
 */
	public String getWhcdName() {
		if (this.getWhcd().equals("1")) {
			whcdName = "小学";
		} else if(this.getWhcd().equals("2")){
			whcdName = "初中";
		}else if(this.getWhcd().equals("3")){
			whcdName = "高中";
		}else if(this.getWhcd().equals("4")){
			whcdName = "中专";
		}else if(this.getWhcd().equals("5")){
			whcdName = "大专";
		}else if(this.getWhcd().equals("6")){
			whcdName = "本科";
		}
		else if(this.getWhcd().equals("7")){
			whcdName = "硕士研究生";
		}
		else if(this.getWhcd().equals("8")){
			whcdName = "博士研究生";
		}
		
		return whcdName;
	}

	public void setWhcdName(String whcdName) {
		this.whcdName = whcdName;
	}
	
	

}