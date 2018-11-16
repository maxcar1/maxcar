package com.maxcar.market.pojo;

import java.io.Serializable;
import java.util.Date;

public class ShoppingGuideDetail implements Serializable {
    private String id;

    private String logo;//logo图

    private String marketId;//市场id

    private String marketIntroduction;//市场简介图

    private String galleryImage;//首页轮播图

    private Integer isvalid;

    private Date insertTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getMarketIntroduction() {
        return marketIntroduction;
    }

    public void setMarketIntroduction(String marketIntroduction) {
        this.marketIntroduction = marketIntroduction == null ? null : marketIntroduction.trim();
    }

    public String getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(String galleryImage) {
        this.galleryImage = galleryImage == null ? null : galleryImage.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
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
}