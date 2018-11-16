package com.maxcar.tenant.app.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SaveAddDealInfoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 过户车辆ID
    @NotNull(message = "过户车辆ID不能为null")
    private String transferCarId;

    /**
     * 转入地车管所
     */
    @NotBlank(message = "转入地车管所不能为空")
    private String carManager;

    @NotNull(message = "成交价格不能为空")
    private Double dealPrice;

    /**
     * 转籍费/费负担 1 买方 2 卖方 3 其他
     */
    private Byte burdenOwner;

    /**
     * 发动机号码
     */
    private String engineNo;

    /**
     * 公里数
     */
    private Integer mileage = 0;

    /**
     * 排放标准
     */
    private String environmentalStandards;

    /**
     * 交易类型 1 本地 2 外迁
     */
    private int tradingType = 1;

}
