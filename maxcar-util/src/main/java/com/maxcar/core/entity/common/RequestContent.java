package com.maxcar.core.entity.common;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class RequestContent  implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String user;
	private String token;
	private String market;
	private String serviceName;
	private String serviceMethod;
	private JSONObject serviceParams;

	
	public RequestContent() {
		
	}

	public RequestContent(String serviceName, String serviceMethod,
			JSONObject serviceParams) {
		this.serviceName = serviceName;
		this.serviceMethod = serviceMethod;
		this.setServiceParams(serviceParams);
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceMethod() {
		return serviceMethod;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public JSONObject getServiceParams() {
		return serviceParams;
	}

	public void setServiceParams(JSONObject serviceParams) {
		this.serviceParams = serviceParams;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
