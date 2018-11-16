package com.maxcar.user.entity;

import java.io.Serializable;

public class MarketMap implements Serializable {
    private String id;

    private String marketId;

    private String equipmentNameMap;

    private String buildingNoArea;

    private String buildingNo;

    private String fmapId;

    private Integer isvalid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getEquipmentNameMap() {
        return equipmentNameMap;
    }

    public void setEquipmentNameMap(String equipmentNameMap) {
        this.equipmentNameMap = equipmentNameMap == null ? null : equipmentNameMap.trim();
    }

    public String getBuildingNoArea() {
        return buildingNoArea;
    }

    public void setBuildingNoArea(String buildingNoArea) {
        this.buildingNoArea = buildingNoArea == null ? null : buildingNoArea.trim();
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public String getFmapId() {
        return fmapId;
    }

    public void setFmapId(String fmapId) {
        this.fmapId = fmapId == null ? null : fmapId.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}