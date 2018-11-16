package com.maxcar.tenant.app.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.app.bean.StaffBindRoleBean;
import com.maxcar.tenant.app.bean.StaffRoleCheckBean;

/**
 * @author songxuefeng
 * @create 2018-10-15 10:27
 * @description: ${description}
 **/
public interface StaffRoleService {
    int deleteByStaffId(String staffId);

    int selectStaffCountByRole(String id);

    String getRoleIdByStaffId(String staffId);

    InterfaceResult staffRelRole(StaffBindRoleBean bean);

    InterfaceResult staffRoleCheck(StaffRoleCheckBean bean);
}
