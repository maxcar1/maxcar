package com.maxcar.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MapResult implements Serializable{
	
	private static final long serialVersionUID = 1672569205110829221L;
	@JsonProperty("Key")
	private String key;
	@JsonProperty("Value")
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
