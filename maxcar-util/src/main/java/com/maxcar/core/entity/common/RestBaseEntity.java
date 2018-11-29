package com.maxcar.core.entity.common;

import java.io.Serializable;

/**
 * 基本信息，每个接口都必传参数
 * 请求实体需继承
 */
public class RestBaseEntity implements Serializable {
    private String applicationID;	//请求方标识(智通定义)

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
}
