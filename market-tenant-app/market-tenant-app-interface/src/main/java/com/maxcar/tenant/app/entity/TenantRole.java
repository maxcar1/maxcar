package com.maxcar.tenant.app.entity;

import com.maxcar.tenant.app.vo.StaffVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TenantRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleName;

    private String roleDesc;

    private String tenantId;

    private Byte isvalid;

    private Integer version;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer count;//员工数量
    private List<String> resIds;//资源id
    private List<TenantRes> tenantRess;
    private List<StaffVo> staffVos;

    public List<StaffVo> getStaffVos() {
        return staffVos;
    }

    public void setStaffVos(List<StaffVo> staffVos) {
        this.staffVos = staffVos;
    }

    public List<TenantRes> getTenantRess() {
        return tenantRess;
    }

    public void setTenantRess(List<TenantRes> tenantRess) {
        this.tenantRess = tenantRess;
    }


    public List<String> getResIds() {
        return resIds;
    }

    public void setResIds(List<String> resIds) {
        this.resIds = resIds;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}