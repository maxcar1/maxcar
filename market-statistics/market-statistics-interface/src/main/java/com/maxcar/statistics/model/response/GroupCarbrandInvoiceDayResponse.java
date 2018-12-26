package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarbrandInvoiceDayResponse implements Serializable {


    private static final long serialVersionUID = -3344693595630204354L;

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

}
