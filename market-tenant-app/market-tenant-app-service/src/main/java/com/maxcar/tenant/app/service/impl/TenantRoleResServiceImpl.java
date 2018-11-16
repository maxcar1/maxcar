package com.maxcar.tenant.app.service.impl;

import com.maxcar.tenant.app.dao.TenantRoleResMapper;
import com.maxcar.tenant.app.service.TenantRoleResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tenantRoleResService")
public class TenantRoleResServiceImpl implements TenantRoleResService {

    @Autowired
    private TenantRoleResMapper tenantRoleResMapper;


    @Override
    public List<String> getResIdListByRoleId(String roleId) {
        return tenantRoleResMapper.getResIdListByRoleId(roleId);
    }
}
