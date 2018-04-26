
package com.boco.eoms.exelawtipstaffexam.model;

/**
 * ZfryksZw entity. @author MyEclipse Persistence Tools
 */

public class ZfryksZw {
	
	// Fields
	
	private String id;
	
	private String kcId;
	
	private Integer kch;
	
	private Integer zwh;
	
	private String zkzh;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKcId() {
		return kcId;
	}
	
	public void setKcId(String kcId) {
		this.kcId = kcId;
	}
	
	public Integer getKch() {
		return kch;
	}
	
	public void setKch(Integer kch) {
		this.kch = kch;
	}
	
	public Integer getZwh() {
		return zwh;
	}
	
	public void setZwh(Integer zwh) {
		this.zwh = zwh;
	}
	
	public String getZkzh() {
		return zkzh;
	}
	
	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}
	
	public String toString() {
		return "ZfryksZw [id=" + id + ", kcId=" + kcId + ", kch=" + kch + ", zwh=" + zwh + ", zkzh=" + zkzh + "]";
	}
	
}