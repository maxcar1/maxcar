package com.maxcar.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.OpenApiConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 申请分配appKey和appSecret，配置ip白名单
 *
 */
@RestController
@RequestMapping("/api/apply")
public class AllocationController {

    @Autowired
    OpenApiConfigService openApiConfigService;
    @PostMapping("")
    public Object getNewApply(@RequestBody JSONObject params){
        InterfaceResult openApiResult = new InterfaceResult();
        try {
            openApiResult = openApiConfigService.applyOpenApi(params);
        } catch (Exception e) {
            e.printStackTrace();
            openApiResult.InterfaceResult600("error");
        }
        return openApiResult;
    }

}
