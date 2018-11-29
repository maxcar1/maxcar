package com.maxcar.entity;

public class TaobaoItemValues extends TaobaoItemValuesKey {
    private String name;

    private String pidname;

    private String cid;

    private Boolean isparent;

    private String parentvid;

    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPidname() {
        return pidname;
    }

    public void setPidname(String pidname) {
        this.pidname = pidname == null ? null : pidname.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public Boolean getIsparent() {
        return isparent;
    }

    public void setIsparent(Boolean isparent) {
        this.isparent = isparent;
    }

    public String getParentvid() {
        return parentvid;
    }

    public void setParentvid(String parentvid) {
        this.parentvid = parentvid == null ? null : parentvid.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}