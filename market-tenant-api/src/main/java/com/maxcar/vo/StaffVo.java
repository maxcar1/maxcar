package com.maxcar.vo;

public class StaffVo {

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 是否需要切换商户 1.需要切换 0.无需切换
     */
    private int switchTenant = 0;

    /**
     * 商户名称
     */
    private String tenantName;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 是否商户管理员(0:员工,1:管理员)
     */
    private int isAdmin = 0;

    /**
     * 是否已经审核 -1.已拒绝 0.待审核 1.审核通过 2.未提交 3.账户被禁用
     */
    private int checked = 1;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 市场名称
     */
    private String marketName;

    /**
     * 车辆牌照
     */
    private String carNumber;

    /**
     * 车管所
     */
    private String carManager = "";


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getSwitchTenant() {
        return switchTenant;
    }

    public void setSwitchTenant(int switchTenant) {
        this.switchTenant = switchTenant;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarManager() {
        return carManager;
    }

    public void setCarManager(String carManager) {
        this.carManager = carManager;
    }
}