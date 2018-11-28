package com.maxcar.core.entity.common;

import java.io.Serializable;

/**
 * 接口统一入参实体
 */
public class RestRequestEntity<T extends RestBaseEntity> implements Serializable {

	private static final long serialVersionUID = -2977420610880646329L;
	
	private String sendTime;	//请求时间
	private String sign;	//请求签名 (暂未启用)
	private String msg;		//接口描述
	private T data;		//请求数据
	
	public RestRequestEntity() {
		super();
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
