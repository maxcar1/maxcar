package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;

public class FlowStep implements Serializable {
    private Integer id;
    private String flowName;
    private Integer isNeedReview;
    private Date insertTime;
    private Integer isValid;
    private String marketId;
    private Integer code;
    private Integer reviewType;

    public Integer getReviewType() {
        return reviewType;
    }
    public void setReviewType(Integer reviewType) {
        this.reviewType = reviewType;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    /*public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public Integer getIsNeedReview() {
        return isNeedReview;
    }

    public void setIsNeedReview(Integer isNeedReview) {
        this.isNeedReview = isNeedReview;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
