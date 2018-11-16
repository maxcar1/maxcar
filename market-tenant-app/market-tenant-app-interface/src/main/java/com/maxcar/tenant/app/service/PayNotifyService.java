package com.maxcar.tenant.app.service;

import java.util.Map;

public interface PayNotifyService {

    boolean handleWxPayNotify(String xml);

    boolean handleAlipayNotify(Map<String, String> data);
}
