package com.maxcar.tenant.app.model.response;

import java.io.Serializable;
import java.util.Date;

public class TransferCarResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String transferCarNo;

    private String marketId;

    private String carId;

    private String byeSellInfo;

    private String addDealInfo;

    private String tenantId;

    private Byte status;

    private Byte type;

    private String vin;

    private String brandModel;

    private Byte carType;

    private String registerCode;

    private String plateNum;

    private String checkOutPhoto;

    private Date startTime;

    private String remarks;

    private Byte isvalid;

    private String configurationId;

    private String configurationName;

    private Date insertTime;

    private Date updateTime;

    /**
     * 是否购买质保服务 0.否 1.是
     */
    private byte qualityService = 0;

    /**
     * 买家签名
     */
    private String buyerSign;

    /**
     * 卖家签名
     */
    private String sellerSign;

    /**
     * 合同地址
     */
    private String contractUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTransferCarNo() {
        return transferCarNo;
    }

    public void setTransferCarNo(String transferCarNo) {
        this.transferCarNo = transferCarNo == null ? null : transferCarNo.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public String getByeSellInfo() {
        return byeSellInfo;
    }

    public void setByeSellInfo(String byeSellInfo) {
        this.byeSellInfo = byeSellInfo == null ? null : byeSellInfo.trim();
    }

    public String getAddDealInfo() {
        return addDealInfo;
    }

    public void setAddDealInfo(String addDealInfo) {
        this.addDealInfo = addDealInfo == null ? null : addDealInfo.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel == null ? null : brandModel.trim();
    }

    public Byte getCarType() {
        return carType;
    }

    public void setCarType(Byte carType) {
        this.carType = carType;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode == null ? null : registerCode.trim();
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum == null ? null : plateNum.trim();
    }

    public String getCheckOutPhoto() {
        return checkOutPhoto;
    }

    public void setCheckOutPhoto(String checkOutPhoto) {
        this.checkOutPhoto = checkOutPhoto == null ? null : checkOutPhoto.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    public byte getQualityService() {
        return qualityService;
    }

    public void setQualityService(byte qualityService) {
        this.qualityService = qualityService;
    }

    public String getBuyerSign() {
        return buyerSign;
    }

    public void setBuyerSign(String buyerSign) {
        this.buyerSign = buyerSign;
    }

    public String getSellerSign() {
        return sellerSign;
    }

    public void setSellerSign(String sellerSign) {
        this.sellerSign = sellerSign;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }
}