package com.maxcar.base.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.dao.OpenApiConfigMapper;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.OpenApiConfig;
import com.maxcar.base.service.OpenApiConfigService;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.api.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("openApiConfigService")
public class OpenApiConfigServiceImpl implements OpenApiConfigService {

    @Autowired
    private OpenApiConfigMapper openApiConfigMapper;

    @Override
    public InterfaceResult applyOpenApi(JSONObject params) throws Exception {
        InterfaceResult openApiResult = new InterfaceResult();

        StringBuilder para = new StringBuilder();
        if (null != params && !params.isEmpty()) {
            OpenApiConfig openApiConfig = JSON.toJavaObject(params, OpenApiConfig.class);
            if (null == openApiConfig.getApiType() || null == openApiConfig.getIsIp()) {
                openApiResult.InterfaceResult600("参数不合法!");
                return openApiResult;
            }
            //开启了ip校验
            if (openApiConfig.getIsIp() == 1) {
                if (StringUtils.isBlank(openApiConfig.getIp())) {
                    openApiResult.InterfaceResult600("请输入ip,多个ip请以逗号(英文)分隔,最大支持5组!");
                    return openApiResult;
                } else {
                    if (openApiConfig.getIp().contains(",")) {
                        String[] ips = openApiConfig.getIp().split(",");
                        if (ips.length > 5) {
                            openApiResult.InterfaceResult600("ip最大支持5组!");
                            return openApiResult;
                        }
                        for (int i = 0; i < ips.length; i++) {
                            if (!ApiUtils.getInstance().isIP(ips[i])) {
                                openApiResult.InterfaceResult600("请输入正确的ip,多个ip请以逗号(英文)分隔,最大支持5组!");
                                return openApiResult;
                            }
                        }
                    }else {
                        if (!ApiUtils.getInstance().isIP(openApiConfig.getIp())) {
                            openApiResult.InterfaceResult600("请输入正确的ip,多个ip请以逗号(英文)分隔,最大支持5组!");
                            return openApiResult;
                        }
                    }
                }
            }
            para.append("apiType=").append(openApiConfig.getApiType())
                    .append("&isIp=").append(openApiConfig.getIsIp());
            if (StringUtils.isNotBlank(openApiConfig.getMarketId())) {
                para.append("&marketId=").append(openApiConfig.getMarketId());
            }
            if (StringUtils.isNotBlank(openApiConfig.getCompanyName())) {
                para.append("&companyName=").append(openApiConfig.getCompanyName());
            }
            openApiConfig.setId(UuidUtils.getUUID());
            String appKey = ApiUtils.getInstance().generorAppKey(para.toString());
            openApiConfig.setAppKey(appKey);
            openApiConfig.setAppSecret(ApiUtils.getInstance().generorAppSecret(appKey));
            openApiConfig.setInsertOperator("admin");
            openApiConfig.setUpdateTime(Calendar.getInstance().getTime());
            openApiConfig.setUpdateOperator("admin");

            int code = openApiConfigMapper.insertSelective(openApiConfig);

            if (code == 1) {
                openApiResult.InterfaceResult200(openApiConfig);
            } else {
                openApiResult.InterfaceResult600("error");
            }
        }
        return openApiResult;
    }

    @Override
    public OpenApiConfig checkSecret(String appKey) {
        return openApiConfigMapper.checkSecret(appKey);
    }


}
