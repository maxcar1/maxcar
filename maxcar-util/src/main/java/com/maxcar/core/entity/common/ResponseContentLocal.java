package com.maxcar.core.entity.common;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class ResponseContentLocal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
//	private static final long serialVersionUID = 1L;
	private int resultCode;
	private String message;
	private String token;
	private JSONObject datas;
	
	public static ResponseContentLocal error(String message) {
		return error(2, message, null);
	}
	
	public static ResponseContentLocal error(int resultCode, String message) {
		return error(resultCode, message, null);
	}
	
	public static ResponseContentLocal error(int resultCode, String message, JSONObject datas) {
		ResponseContentLocal rc = new ResponseContentLocal();
		rc.setResultCode(resultCode);
		rc.setMessage(message);
		rc.setDatas(datas);
		return rc;
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resulCode) {
		this.resultCode = resulCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JSONObject getDatas() {
		return datas;
	}
	public void setDatas(JSONObject datas) {
		this.datas = datas;
	}	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
