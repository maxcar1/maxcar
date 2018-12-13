package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse implements Serializable {

    private static final long serialVersionUID = 3223580920375663925L;
    private  Integer carNumber;

    private  Integer exhibitionNumber;

    private  Integer  office;

    private  Integer carTotal;
    // 仓库
    private Integer warehouse;
    // 临时车位
    private Integer temporaryCarSpace;
    // 其他
    private Integer other;

    private String message;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getExhibitionNumber() {
        return exhibitionNumber;
    }

    public void setExhibitionNumber(Integer exhibitionNumber) {
        this.exhibitionNumber = exhibitionNumber;
    }

    public Integer getOffice() {
        return office;
    }

    public void setOffice(Integer office) {
        this.office = office;
    }

    public Integer getCarTotal() {
        return carTotal;
    }

    public void setCarTotal(Integer carTotal) {
        this.carTotal = carTotal;
    }

    public Integer getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Integer warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getTemporaryCarSpace() {
        return temporaryCarSpace;
    }

    public void setTemporaryCarSpace(Integer temporaryCarSpace) {
        this.temporaryCarSpace = temporaryCarSpace;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
