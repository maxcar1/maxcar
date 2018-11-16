package com.maxcar.tenant.app.service.impl;

import com.maxcar.tenant.app.dao.ChargeOrderDetailMapper;
import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import com.maxcar.tenant.app.service.ChargeOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chargeOrderDetailService")
public class ChargeOrderDetailServiceImpl implements ChargeOrderDetailService{

    @Autowired
    private ChargeOrderDetailMapper chargeOrderDetailMapper;

    public ChargeOrderDetail getChargeOrder(String id) {
        return chargeOrderDetailMapper.find(id);
    }

    @Override
    public ChargeOrderDetail getChargeOrderByTransferCarNo(String transferCarNo, int feeType) {
        return chargeOrderDetailMapper.getChargeOrderByTransferCarNo(transferCarNo, feeType);
    }

}
