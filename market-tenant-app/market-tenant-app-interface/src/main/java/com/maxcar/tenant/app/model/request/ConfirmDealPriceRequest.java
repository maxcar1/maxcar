package com.maxcar.tenant.app.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ConfirmDealPriceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "过户车辆ID不能为null")
    private String transferCarId;

    @NotNull(message = "成交价格不能为空")
    private Double dealPrice;

}
