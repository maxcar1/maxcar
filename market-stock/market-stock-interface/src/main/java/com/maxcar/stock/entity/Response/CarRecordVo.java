package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class CarRecordVo implements Serializable {

    private Integer reviewId;//审核id

    private String time;//出入场时间

    private Integer type;//车辆出入类型 0 入场 1 出场

    private String barrierPosition;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBarrierPosition() {
        return barrierPosition;
    }

    public void setBarrierPosition(String barrierPosition) {
        this.barrierPosition = barrierPosition;
    }
}
