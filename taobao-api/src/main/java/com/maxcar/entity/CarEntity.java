package com.maxcar.entity;

import java.util.List;

public class CarEntity {
	private List<CarPicture> picList; //图片
	private String airConditionerControlType;//变速箱
	private String attribution;//地址
	private String address;//地址
	private String brandName;//品牌
	private String seriesName;//
	private String modelName;//
	private String modelYear;//年款
	private String color;//颜色
	private String description;//描述
	private String displacement;//排量
	private String environmentalStandards;//排放标准
	private String fuelForm;//燃油方式
	private String initialLicenceTime;//注册时间
	private String level;//车辆级别
	private String market;//市场id
	private String evaluatePrice;//评估价格
	private String marketPrice;//市场价格
	private String newPrice;//新车价格
	private String mileage;//公里
	private String ownerName;//商户
	private String seats;//座位数
	private String vin;//
	private String accessToken;
	private String modelCode;
	private String initialLicenceTimeStr;
	private String sellCid;
	private  String taoBaoId;

	public String getTaoBaoId() {
		return taoBaoId;
	}

	public void setTaoBaoId(String taoBaoId) {
		this.taoBaoId = taoBaoId;
	}

	public String getSellCid() {
		return sellCid;
	}

	public void setSellCid(String sellCid) {
		this.sellCid = sellCid;
	}

	public String getInitialLicenceTimeStr() {
		return initialLicenceTimeStr;
	}
	public void setInitialLicenceTimeStr(String initialLicenceTimeStr) {
		this.initialLicenceTimeStr = initialLicenceTimeStr;
	}
	public List<CarPicture> getPicList() {
		return picList;
	}
	public void setPicList(List<CarPicture> picList) {
		this.picList = picList;
	}
	public String getAirConditionerControlType() {
		return airConditionerControlType;
	}
	public void setAirConditionerControlType(String airConditionerControlType) {
		this.airConditionerControlType = airConditionerControlType;
	}
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getEnvironmentalStandards() {
		return environmentalStandards;
	}
	public void setEnvironmentalStandards(String environmentalStandards) {
		this.environmentalStandards = environmentalStandards;
	}
	public String getFuelForm() {
		return fuelForm;
	}
	public void setFuelForm(String fuelForm) {
		this.fuelForm = fuelForm;
	}
	public String getInitialLicenceTime() {
		return initialLicenceTime;
	}
	public void setInitialLicenceTime(String initialLicenceTime) {
		this.initialLicenceTime = initialLicenceTime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getEvaluatePrice() {
		return evaluatePrice;
	}
	public void setEvaluatePrice(String evaluatePrice) {
		this.evaluatePrice = evaluatePrice;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(String newPrice) {
		this.newPrice = newPrice;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
}
