package com.maxcar.tenant.app.service.impl;

import com.maxcar.tenant.app.dao.TenantResMapper;
import com.maxcar.tenant.app.entity.TenantRes;
import com.maxcar.tenant.app.entity.TenantResExample;
import com.maxcar.tenant.app.service.TenantResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tenantResService")
public class TenantResServiceImpl implements TenantResService {
    @Autowired
    private TenantResMapper tenantResMapper;

    @Override
    public List<TenantRes> findAll() {
        return tenantResMapper.selectByExample(new TenantResExample());
    }

    @Override
    public TenantRes getTenantResById(String id) {
        return tenantResMapper.getTenantResById(id);
    }

//    @Override
//    public List<TenantRes> findAll() {
//        return tenantResMapper.findAll();
//    }
}
