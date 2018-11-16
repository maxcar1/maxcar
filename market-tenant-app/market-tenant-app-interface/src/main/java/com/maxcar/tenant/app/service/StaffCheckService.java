package com.maxcar.tenant.app.service;

import com.maxcar.tenant.app.bean.StaffBindTenantBean;
import com.maxcar.tenant.app.entity.StaffCheck;

public interface StaffCheckService {

    void insert(StaffBindTenantBean bean, String staffId);

    StaffCheck getStaffCheckByStaffId(String staffId);
}
