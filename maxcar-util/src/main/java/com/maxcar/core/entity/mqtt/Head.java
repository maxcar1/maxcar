package com.maxcar.core.entity.mqtt;


import net.sf.json.JSONArray;

public class Head {
	private String topic;
	private String publishTime;
	private Integer type;
	private String status;
	private JSONArray errors;
	
	public Head() {
	}
	
	public Head(String topic, String publishTime, Integer type, String status,
			JSONArray errors) {
		super();
		this.topic = topic;
		this.publishTime = publishTime;
		this.type = type;
		this.status = status;
		this.errors = errors;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public JSONArray getErrors() {
		return errors;
	}
	public void setErrors(JSONArray errors) {
		this.errors = errors;
	}
	
	
}
