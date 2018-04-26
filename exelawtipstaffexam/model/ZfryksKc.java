
package com.boco.eoms.exelawtipstaffexam.model;

/**
 * ZfryksKc entity. @author MyEclipse Persistence Tools
 */

public class ZfryksKc {
	
	// Fields
	
	private String id;
	
	private String kdId;
	
	private Integer kdxh;
	
	private Integer kch;
	
	private Integer kczws;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKdId() {
		return kdId;
	}
	
	public void setKdId(String kdId) {
		this.kdId = kdId;
	}
	
	public Integer getKdxh() {
		return kdxh;
	}
	
	public void setKdxh(Integer kdxh) {
		this.kdxh = kdxh;
	}
	
	public Integer getKch() {
		return kch;
	}
	
	public void setKch(Integer kch) {
		this.kch = kch;
	}
	
	public Integer getKczws() {
		return kczws;
	}
	
	public void setKczws(Integer kczws) {
		this.kczws = kczws;
	}
	
	public String toString() {
		return "ZfryksKc [id=" + id + ", kdId=" + kdId + ", kdxh=" + kdxh + ", kch=" + kch + ", kczws=" + kczws + "]";
	}
	
}