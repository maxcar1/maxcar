package com.maxcar.core.entity.mqtt;

import java.util.Date;

import com.maxcar.core.utils.Page;

/**
 * MQTT推送失败记录实体类
 * @author ldc
 *
 */
public class SystemMqttRecord extends Page{

	private Integer id;

	private Date access_time;

	private Date fail_time;

	private Integer is_ok;

	private String market;

	private String params;

	private String service_method;

	private String service_name;

	private String topic;
	
	private Integer isOk;
	
	

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public SystemMqttRecord() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAccess_time() {
		return access_time;
	}

	public void setAccess_time(Date access_time) {
		this.access_time = access_time;
	}

	public Date getFail_time() {
		return fail_time;
	}

	public void setFail_time(Date fail_time) {
		this.fail_time = fail_time;
	}

	public Integer getIs_ok() {
		return is_ok;
	}

	public void setIs_ok(Integer is_ok) {
		this.is_ok = is_ok;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getService_method() {
		return service_method;
	}

	public void setService_method(String service_method) {
		this.service_method = service_method;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}