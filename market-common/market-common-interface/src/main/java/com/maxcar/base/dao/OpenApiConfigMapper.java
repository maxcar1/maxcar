package com.maxcar.base.dao;

import com.maxcar.base.pojo.OpenApiConfig;

/**
 * @author songxuefeng
 * @create 2018-11-06 14:08
 * @description: ${description}
 **/
public interface OpenApiConfigMapper {
    int insertSelective(OpenApiConfig openApiConfig);

    OpenApiConfig checkSecret(String appKey);
}
