package com.maxcar.vo;

import java.io.Serializable;

public class TenantResVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String resName;

    private String resUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }
}