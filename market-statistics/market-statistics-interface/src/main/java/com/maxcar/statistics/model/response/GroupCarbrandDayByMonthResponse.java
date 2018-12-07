package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarbrandDayByMonthResponse implements Serializable {

    private static final long serialVersionUID = -50577485291232848L;
    private String marketId;

    private String tenantId;

    private String brandName;

    private Integer invoiceCount;

    private Double invoicePrice;

    private Integer maleCount;

    private Integer femaleCount;

    private Integer age20Count;

    private Integer age25Count;

    private Integer age30Count;

    private Integer age35Count;

    private Integer age40Count;

    private Integer age45Count;

    private Integer age50Count;

    private String inventoryCount;

    private String inventoryPrice;

}
