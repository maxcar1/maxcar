package com.maxcar.tenant.app.service;

import com.maxcar.base.pojo.InterfaceResult;

import java.util.Map;

public interface AliPayService {

    InterfaceResult alipayCharge(String staffId, String ip, int feeType, String transferCarNo);

    boolean checkSign(Map<String, String> data, String staffId, int feeType);

}
