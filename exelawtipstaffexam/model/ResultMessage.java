
package com.boco.eoms.exelawtipstaffexam.model;

public class ResultMessage {
	public static int STATUS_FAIL = 0;
	
	public static int STATUS_SUCC = 1;
	
	private int status;
	
	private String message;
	
	public ResultMessage() {
	}
	
	public ResultMessage( int status, String message ) {
		this.status = status;
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "ResultMessage [status=" + status + ", message=" + message + "]";
	}
}
