package com.maxcar.stock.pojo;

import com.maxcar.base.pojo.InterfaceResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReviewDetail implements Serializable {
    private Integer id;

    private Integer reviewId;

    private String reviewPersonId;

    private Integer reviewResult;

    private String reviewDesc;

    private Integer level;

    private Date insertTime;

    private Integer carStatus;

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewPersonId() {
        return reviewPersonId;
    }

    public void setReviewPersonId(String reviewPersonId) {
        this.reviewPersonId = reviewPersonId == null ? null : reviewPersonId.trim();
    }

    public Integer getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(Integer reviewResult) {
        this.reviewResult = reviewResult;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc == null ? null : reviewDesc.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

}