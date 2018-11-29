package com.maxcar.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SinglePicInfo implements Serializable{
	
	private static final long serialVersionUID = -737486379634655894L;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Type")
    private String type;
	@JsonProperty("Describe")
    private String describe;
	@JsonProperty("FilePath")
    private String filePath;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
