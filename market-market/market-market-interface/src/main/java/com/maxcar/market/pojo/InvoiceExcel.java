package com.maxcar.market.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class InvoiceExcel implements Serializable {
    private static final long serialVersionUID = 8688584219229520489L;
    private String invoice_no = "";
    private String invoice_code = "";
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String bill_time = "";
    private String purchacer_name = "";
    private String seller_name = "";
    private double price = 0.0;
    private String invoice_portof = "";
    private String car_sources = "";
    private String invoice_status = "";

}
