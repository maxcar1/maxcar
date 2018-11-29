package com.maxcar.entity;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class CheckResult implements Serializable{
	
	private static final long serialVersionUID = 3556436319761354213L;
	@JsonProperty("Advantage")
	private List<String> advantage;
	@JsonProperty("BaseConfiguration")
	private List<MapResult> baseConfiguration;
	@JsonProperty("CarInfo")
	private List<MapResult> carInfo;
	@JsonProperty("CustomerInfo")
	private List<MapResult> customerInfo;
	@JsonProperty("DefectInfo")
	private List<GroupInfo> defectInfo;
	@JsonProperty("DetailInfo")
	private List<GroupInfo> detailInfo;
	@JsonProperty("FreeComments")
	private String freeComments;
	@JsonProperty("IsAccidentCar")
	private String isAccidentCar;
	@JsonProperty("LicenseInfo")
	private List<MapResult> licenseInfo;
	@JsonProperty("PictureInfo")
	private List<SinglePicInfo> pictureInfo;
	@JsonProperty("InspectionGrade")
	private List<MapResult> inspectionGrade;
	
	public List<String> getAdvantage() {
		return advantage;
	}
	public void setAdvantage(List<String> advantage) {
		this.advantage = advantage;
	}
	public List<MapResult> getBaseConfiguration() {
		return baseConfiguration;
	}
	public void setBaseConfiguration(List<MapResult> baseConfiguration) {
		this.baseConfiguration = baseConfiguration;
	}
	public List<MapResult> getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(List<MapResult> carInfo) {
		this.carInfo = carInfo;
	}
	public List<MapResult> getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(List<MapResult> customerInfo) {
		this.customerInfo = customerInfo;
	}
	public List<GroupInfo> getDefectInfo() {
		return defectInfo;
	}
	public void setDefectInfo(List<GroupInfo> defectInfo) {
		this.defectInfo = defectInfo;
	}
	public List<GroupInfo> getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(List<GroupInfo> detailInfo) {
		this.detailInfo = detailInfo;
	}
	public String getFreeComments() {
		return freeComments;
	}
	public void setFreeComments(String freeComments) {
		this.freeComments = freeComments;
	}
	public String getIsAccidentCar() {
		return isAccidentCar;
	}
	public void setIsAccidentCar(String isAccidentCar) {
		this.isAccidentCar = isAccidentCar;
	}
	public List<MapResult> getLicenseInfo() {
		return licenseInfo;
	}
	public void setLicenseInfo(List<MapResult> licenseInfo) {
		this.licenseInfo = licenseInfo;
	}
	public List<SinglePicInfo> getPictureInfo() {
		return pictureInfo;
	}
	public void setPictureInfo(List<SinglePicInfo> pictureInfo) {
		this.pictureInfo = pictureInfo;
	}
	public List<MapResult> getInspectionGrade() {
		return inspectionGrade;
	}
	public void setInspectionGrade(List<MapResult> inspectionGrade) {
		this.inspectionGrade = inspectionGrade;
	}
	
}
