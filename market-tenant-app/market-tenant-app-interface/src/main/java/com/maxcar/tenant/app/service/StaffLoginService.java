package com.maxcar.tenant.app.service;

import com.maxcar.base.pojo.InterfaceResult;

public interface StaffLoginService {

    InterfaceResult getStaffIdByToken(String staffToken);

    InterfaceResult login(String phoneNum);
}
