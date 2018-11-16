package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateTicketByIdRequest implements Serializable {

    //购票表id
    @NotNull(message = "购票ID不能为null")
    private String id;

    //发票代码
    @NotNull(message = "发票代码不能为null")
    private String invoiceCode;

    //发票起始号
    @NotNull(message = "发票起始号不能为null")
    private String invoiceStartNo;

    //发票结束号
    @NotNull(message = "发票结束号不能为null")
    private String invoiceEndNo;

    //购票日期
    @NotNull(message = "购票日期不能为null")
    private Date billTime;
}
