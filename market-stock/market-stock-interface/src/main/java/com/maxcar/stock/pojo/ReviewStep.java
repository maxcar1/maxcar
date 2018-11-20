package com.maxcar.stock.pojo;

public class ReviewStep {
    private Integer id;

    private String stepName;

    private Integer reviewPersonId;

    private Integer level;

    private Integer type;

    private Integer applyType;

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

    public Integer getReviewPersonId() {
        return reviewPersonId;
    }

    public void setReviewPersonId(Integer reviewPersonId) {
        this.reviewPersonId = reviewPersonId;
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
}