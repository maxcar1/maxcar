package com.maxcar.tenant.app.service;

import com.maxcar.tenant.app.entity.TenantRes;

import java.util.List;

public interface TenantResService {

    TenantRes getTenantResById(String id);

    List<TenantRes> findAll();
}
