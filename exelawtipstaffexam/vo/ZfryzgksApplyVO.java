
package com.boco.eoms.exelawtipstaffexam.vo;

import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksApply;



public class ZfryzgksApplyVO extends ZfryzgksApply{
	
	private String  zgqkName; //�ڸ���� 
	
	private String zjName;
	
	private String whcdName;
	
	private String xbName;
	
	private String zjslfsName;
	

	public String getZjslfsName() {
		if(this.getZjslfs().equals("1")){
			zjslfsName ="��������";
		}else{
			zjslfsName ="����";
		}
		return zjslfsName;
	}

	public void setZjslfsName(String zjslfsName) {
		this.zjslfsName = zjslfsName;
	}

	public String getXbName() {
		if(this.getXb().intValue() ==1){
			xbName = "��";
		}else{
			xbName ="Ů";
		}
		return xbName;
	}

	public void setXbName(String xbName) {
		this.xbName = xbName;
	}

	public String getZgqkName() {
		if (this.getZgqk().intValue() ==1) {
			zgqkName = "�ڱ�";
		} else {
			zgqkName = "Ƹ��";
		}
		return zgqkName;
	}

	public void setZgqkName(String zgqkName) {
		this.zgqkName = zgqkName;
	}
	public String getZjName() {
		if (this.getZj().equals("1")) {
			zjName = "��Ա";
		} else if(this.getZj().equals("2")){
			zjName = "���Ƽ�";
		} else if(this.getZj().equals("3")){
			zjName = "���Ƽ�";
		}else if(this.getZj().equals("4")){
			zjName = "������";
		}else if(this.getZj().equals("5")){
			zjName = "������";
		}else if(this.getZj().equals("6")){
			zjName = "�����ּ�";
		}else if(this.getZj().equals("7")){
			zjName = "�����ּ�";
		}else if(this.getZj().equals("8")){
			zjName = "ʡ����";
		}
		return zjName;
	}

	public void setZjName(String zjName) {
		this.zjName = zjName;
	}
/**
 * <option value="1" <c:if test="${zfryzgksApply.whcd==1}">selected</c:if>>Сѧ</option>
            <option value="2" <c:if test="${zfryzgksApply.whcd==2}">selected</c:if>>����</option>
            <option value="3" <c:if test="${zfryzgksApply.whcd==3}">selected</c:if>>����</option>
            <option value="4" <c:if test="${zfryzgksApply.whcd==4}">selected</c:if>>��ר</option>
            <option value="5" <c:if test="${zfryzgksApply.whcd==5}">selected</c:if>>��ר</option>
            <option value="6" <c:if test="${zfryzgksApply.whcd==6}">selected</c:if>>����</option>
 */
	public String getWhcdName() {
		if (this.getWhcd().equals("1")) {
			whcdName = "Сѧ";
		} else if(this.getWhcd().equals("2")){
			whcdName = "����";
		}else if(this.getWhcd().equals("3")){
			whcdName = "����";
		}else if(this.getWhcd().equals("4")){
			whcdName = "��ר";
		}else if(this.getWhcd().equals("5")){
			whcdName = "��ר";
		}else if(this.getWhcd().equals("6")){
			whcdName = "����";
		}
		else if(this.getWhcd().equals("7")){
			whcdName = "˶ʿ�о���";
		}
		else if(this.getWhcd().equals("8")){
			whcdName = "��ʿ�о���";
		}
		
		return whcdName;
	}

	public void setWhcdName(String whcdName) {
		this.whcdName = whcdName;
	}
	
	

}