package com.maxcar.entity;

import java.util.Date;
import java.util.List;

public class TaobaoImg {

	private Integer id;
	private String numId;
	private List<String> imgId;
	private List<String> imgPath;
	private Date insertTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumId() {
		return numId;
	}
	public void setNumId(String numId) {
		this.numId = numId;
	}
	
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public List<String> getImgId() {
		return imgId;
	}
	public void setImgId(List<String> imgId) {
		this.imgId = imgId;
	}
	public List<String> getImgPath() {
		return imgPath;
	}
	public void setImgPath(List<String> imgPath) {
		this.imgPath = imgPath;
	}
	
}
