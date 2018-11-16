package com.maxcar.tenant.app.service.impl;

import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.app.bean.StaffBindTenantBean;
import com.maxcar.tenant.app.dao.StaffCheckMapper;
import com.maxcar.tenant.app.entity.StaffCheck;
import com.maxcar.tenant.app.service.StaffCheckService;
import com.maxcar.user.service.StaffService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("staffCheckService")
public class StaffCheckServiceImpl implements StaffCheckService {

    @Autowired
    private StaffService staffService;

    @Resource
    private StaffCheckMapper staffCheckMapper;

    @Override
    public void insert(StaffBindTenantBean bean, String staffId) {
        StaffCheck staffCheck = new StaffCheck();
        staffCheck.setId(UuidUtils.generateIdentifier());
        staffCheck.setStaffId(staffId);
        BeanUtils.copyProperties(bean, staffCheck);
        staffCheckMapper.insert(staffCheck);
        staffService.updateStaffNameById(bean.getStaffName(), staffId);
    }

    @Override
    public StaffCheck getStaffCheckByStaffId(String staffId) {
        return staffCheckMapper.getStaffCheckByStaffId(staffId);
    }
}
