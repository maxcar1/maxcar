package com.maxcar.core.utils;
/***
 * 通过对象接收验证信息
 * @author Administrator
 *
 */
public class CheckObject {
	
	private boolean isvalidate;//验证是否成功
	private Integer valCode;//返回码
	private String message;//具体提示信息
	
	public CheckObject(){}

	public CheckObject(boolean isvalidate, Integer valCode, String message) {
		super();
		this.isvalidate = isvalidate;
		this.valCode = valCode;
		this.message = message;
	}

	public CheckObject(boolean isvalidate, String message) {
		super();
		this.isvalidate = isvalidate;
		this.message = message;
	}
	
	public CheckObject(boolean isvalidate, Integer valCode) {
		super();
		this.isvalidate = isvalidate;
		this.valCode = valCode;
	}

	public boolean isIsvalidate() {
		return isvalidate;
	}

	public void setIsvalidate(boolean isvalidate) {
		this.isvalidate = isvalidate;
	}

	public Integer getValCode() {
		return valCode;
	}

	public void setValCode(Integer valCode) {
		this.valCode = valCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
