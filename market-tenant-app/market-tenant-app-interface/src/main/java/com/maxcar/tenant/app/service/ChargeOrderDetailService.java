package com.maxcar.tenant.app.service;

import com.maxcar.tenant.app.entity.ChargeOrderDetail;

public interface ChargeOrderDetailService {

    ChargeOrderDetail getChargeOrder(String id);

    ChargeOrderDetail getChargeOrderByTransferCarNo(String transferCarNo, int feeType);

}
