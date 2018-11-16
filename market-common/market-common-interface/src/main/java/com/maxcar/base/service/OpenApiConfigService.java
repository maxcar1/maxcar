package com.maxcar.base.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.OpenApiConfig;


public interface OpenApiConfigService {
    InterfaceResult applyOpenApi(JSONObject params) throws Exception;

    OpenApiConfig checkSecret(String appKey);

}
