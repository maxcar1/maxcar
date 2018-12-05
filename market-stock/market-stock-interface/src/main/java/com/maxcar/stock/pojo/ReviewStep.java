package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReviewStep implements Serializable {
    private Integer id;

    private String stepName;

    private String reviewPersonId;

    private String orgId;

    private Integer level;

    private Integer type;

    private Integer applyType;

    private String apply;

    private String marketId;

    private List<Map> userOrg;

    private Integer isNeedReview;

    private Integer reviewType;

    private String reviewId;

    private Integer currentLevel;

    public  Integer getCurrentLevel(){return  currentLevel;}

    public  void setCurrentLevel(Integer currentLevel){this.currentLevel=currentLevel;}

    public  String getReviewId(){return  reviewId;}

    public  void setReviewId(String reviewId){this.reviewId=reviewId;}

    public  Integer getReviewType(){return  reviewType;}

    public  void setReviewType(Integer reviewType){this.reviewType=reviewType;}

    public  Integer getIsNeedReview(){return  isNeedReview;}

    public  void setIsNeedReview(Integer isNeedReview){this.isNeedReview=isNeedReview;}

    public List<Map> getUserOrg(){return  userOrg;}

    public void setUserOrg(List<Map> userOrg){
        this.userOrg=userOrg;
    }
    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getOrgld() {
        return orgId;
    }

    public void setOrgld(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply == null ? null : apply.trim();
    }

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
}