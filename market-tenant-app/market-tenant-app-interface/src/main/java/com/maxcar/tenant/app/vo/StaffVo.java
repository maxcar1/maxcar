package com.maxcar.tenant.app.vo;

import java.io.Serializable;

/**
 * @author songxuefeng
 * @create 2018-10-16 15:06
 * @description: ${description}
 **/
public class StaffVo implements Serializable {
    private static final long serialVersionUID = 8626470001851391831L;
    private String id;
    private String staffName;
    private String staffPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }
}
