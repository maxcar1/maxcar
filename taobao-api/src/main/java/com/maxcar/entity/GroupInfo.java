package com.maxcar.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupInfo implements Serializable{
	
	private static final long serialVersionUID = -8500000578526647831L;
	@JsonProperty("GroupName")
	private String groupName;
	@JsonProperty("Items")
	private List<DescInfo> items;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<DescInfo> getItems() {
		return items;
	}
	public void setItems(List<DescInfo> items) {
		this.items = items;
	}
	
	
}
