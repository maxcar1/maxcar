package com.maxcar.stock.pojo;

public class ReviewStep {
    private Integer id;

    private String stepName;

    private String reviewPersonId;

    private String orgId;

    private Integer level;

    private Integer type;

    private Integer applyType;

    private Integer isNeedReview;

    private String marketId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName == null ? null : stepName.trim();
    }

    public String getReviewPersonId() {
        return reviewPersonId;
    }

    public void setReviewPersonId(String reviewPersonId) {
        this.reviewPersonId = reviewPersonId == null ? null : reviewPersonId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getIsNeedReview() {
        return isNeedReview;
    }

    public void setIsNeedReview(Integer isNeedReview) {
        this.isNeedReview = isNeedReview;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }
}