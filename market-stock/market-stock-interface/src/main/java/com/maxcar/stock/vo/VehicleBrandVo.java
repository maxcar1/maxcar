package com.maxcar.stock.vo;

import java.io.Serializable;
import java.util.List;

public class VehicleBrandVo implements Serializable {

    private String name;

    private String code;

    private String logoUrl;

    private List<VehicleBrandVo> children;

    public List<VehicleBrandVo> getChildren() {
        return children;
    }

    public void setChildren(List<VehicleBrandVo> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
