package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class ReviewVo implements Serializable {

    private String id;

    private String stepName;//步骤名称

    private Integer type;//审核方式1或签     2会签

    private Integer applyType;

    private String reviewPersonId;//审核人id

    private String reviewPersonName;//审核人姓名

    private Integer isNeedReview;

    private Integer reviewResult;//审核结果 0未审核   1审核通过   2审核不通过

    private String orgId;//部门id

    private String orgName;//部门名字

    private Integer reviewId;//审核id

    private String marketId;

    private Integer level;//审核等级

    public Integer getIsNeedReview() {
        return isNeedReview;
    }

    public void setIsNeedReview(Integer isNeedReview) {
        this.isNeedReview = isNeedReview;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReviewPersonId() {
        return reviewPersonId;
    }

    public void setReviewPersonId(String reviewPersonId) {
        this.reviewPersonId = reviewPersonId;
    }

    public String getReviewPersonName() {
        return reviewPersonName;
    }

    public void setReviewPersonName(String reviewPersonName) {
        this.reviewPersonName = reviewPersonName;
    }

    public Integer getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(Integer reviewResult) {
        this.reviewResult = reviewResult;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
}
