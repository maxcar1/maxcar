package com.maxcar.tenant.app.service;


import com.maxcar.base.pojo.InterfaceResult;

import java.util.TreeMap;

public interface WxPayService {

    InterfaceResult wxCharge(String staffId, String ip, int feeType, String transferCarNo) throws Exception;

    TreeMap<String, String> parseNotify(String xml) throws Exception;

    String buildSign(TreeMap<String, String> params, String payKey);
}
