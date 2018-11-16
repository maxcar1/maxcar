package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DealRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 3478334557408672148L;

    @NotNull(message = "市场id")
    private String marketId;
    //车辆编号
    private String carNo;
    //  车架号
    private String vin;
    // 商户id
    private String tenantId;
    // @NotNull(message = "交易状态")
    //    1.售出出内场  2.售出未出场  3.售出已出场
    private int dealState;
    // 库存天   1. 0-45天  2.45-60  3.  60-90  4. 90以上
    private int storeHoldDays;
    // 销售时间开始
    private String dealTimeStart;
    //  销售时间结束
    private String dealTimeEnd;
    //  品牌名
    private String brandName;
    // 车系名
    private String seriesName;
    // 车型      1.SUV  2.三厢轿车  3.两厢轿车  4.MVP  5.跑车  6.面包车  7.皮卡
    private String modelName;
    //  车龄   1:一年以内 3:三年以内 5:五年以内 9:五年以上",
    private int carAge;
    //  里程数  1:一万以内 3:三万以内 5:五万以内 8:八万以内 9:八万以上",
    private int mileage;
    //  排量    0:  <= 1.0      1: 1.0-1.6     2:1.6-2.0     3:2.0-2.5      4:2.5-3.0       5:3.0-4.0      6:>4.0",
    private String engineVolumeUnitl;
    //  排放标准   " 1.国三以上 2.国四以上 3.国五以上",
    private String environmentalStandards;
    // 新车还是二手车  0  新车 1 旧车
    private String isNewCar;
    // 变数箱   1：自动   2：手动
    private int gearBox;
    // 座位数   2 4 5 7 8:七座以上
    private int seatNumber;
    //燃料类型  1：汽油 2：茶油 3，纯电 4：混动
    private int fuelForm;
    // 交易价格    1.5w以下  2:5-10w  3: 10-15   4: 15-20  5:20-25  6:25-30  7:30-35  8:35-40   9:  40-45  10:45-50  11: 50以上
    private int tradingPrice;
    //车辆来源   1.库存车   2.挂靠车商   3.非车商
    private List carStatus;
    //  库存车
    private int unsoldCar;
    //  车商
    private int tenantCar;
    //  非车商
    private int elseCar;
    //  区域id
    private String areaName;
    //   负责人
    private String contactName;
    //  过户类型
    private Integer transferType;
    //  交易类型
    private Integer tradingType;
    //车辆来源 (1:商品车,2:挂靠,3:代办，4散户)
    private Integer carSources;

    public Integer getCarSources() {
        return carSources;
    }

    public void setCarSources(Integer carSources) {
        this.carSources = carSources;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Integer getTradingType() {
        return tradingType;
    }

    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @NotNull
    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(@NotNull String marketId) {
        this.marketId = marketId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getDealState() {
        return dealState;
    }

    public void setDealState(int dealState) {
        this.dealState = dealState;
    }

    public int getStoreHoldDays() {
        return storeHoldDays;
    }

    public void setStoreHoldDays(int storeHoldDays) {
        this.storeHoldDays = storeHoldDays;
    }

    public String getDealTimeStart() {
        return dealTimeStart;
    }

    public void setDealTimeStart(String dealTimeStart) {
        this.dealTimeStart = dealTimeStart;
    }

    public String getDealTimeEnd() {
        return dealTimeEnd;
    }

    public void setDealTimeEnd(String dealTimeEnd) {
        this.dealTimeEnd = dealTimeEnd;
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

    public int getCarAge() {
        return carAge;
    }

    public void setCarAge(int carAge) {
        this.carAge = carAge;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getEngineVolumeUnitl() {
        return engineVolumeUnitl;
    }

    public void setEngineVolumeUnitl(String engineVolumeUnitl) {
        this.engineVolumeUnitl = engineVolumeUnitl;
    }

    public String getEnvironmentalStandards() {
        return environmentalStandards;
    }

    public void setEnvironmentalStandards(String environmentalStandards) {
        this.environmentalStandards = environmentalStandards;
    }

    public String getIsNewCar() {
        return isNewCar;
    }

    public void setIsNewCar(String isNewCar) {
        this.isNewCar = isNewCar;
    }

    public int getGearBox() {
        return gearBox;
    }

    public void setGearBox(int gearBox) {
        this.gearBox = gearBox;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getFuelForm() {
        return fuelForm;
    }

    public void setFuelForm(int fuelForm) {
        this.fuelForm = fuelForm;
    }

    public int getTradingPrice() {
        return tradingPrice;
    }

    public void setTradingPrice(int tradingPrice) {
        this.tradingPrice = tradingPrice;
    }

    public List getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(List carStatus) {
        this.carStatus = carStatus;
    }

    public int getUnsoldCar() {
        return unsoldCar;
    }

    public void setUnsoldCar(int unsoldCar) {
        this.unsoldCar = unsoldCar;
    }

    public int getTenantCar() {
        return tenantCar;
    }

    public void setTenantCar(int tenantCar) {
        this.tenantCar = tenantCar;
    }

    public int getElseCar() {
        return elseCar;
    }

    public void setElseCar(int elseCar) {
        this.elseCar = elseCar;
    }

}
