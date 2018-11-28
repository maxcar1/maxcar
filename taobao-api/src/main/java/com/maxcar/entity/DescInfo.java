package com.maxcar.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescInfo implements Serializable{

	private static final long serialVersionUID = -870946076434869196L;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Severity")
	private String severity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}

}
