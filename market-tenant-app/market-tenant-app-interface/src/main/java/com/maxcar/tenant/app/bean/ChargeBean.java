package com.maxcar.tenant.app.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChargeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 费用类型：1.交易过户费 2.质保服务费
     */
    private int feeType = 1;

    /**
     * 过户交易流水号
     */
    private String transferCarNo;

}