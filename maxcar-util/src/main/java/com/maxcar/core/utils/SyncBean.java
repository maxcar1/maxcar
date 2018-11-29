package com.maxcar.core.utils;

import java.io.Serializable;

/**
 * 实时同步反馈bean
 * @author ldc
 *
 */
public class SyncBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5746931433126924332L;
	private String id;
	private String code;
	private String message;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
