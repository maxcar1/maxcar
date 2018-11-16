package com.maxcar.tenant.app.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SaveBuySellInfoBuyerRequest implements Serializable {

    // 过户车辆ID
    @NotNull(message = "过户车辆ID不能为null")
    private String transferCarId;

    //卖方类型
    @NotNull(message = "买方类型不能为null")
    private Byte buyerType;

    @NotNull(message = "买方姓名不能为null")
    private String buyerName;

    @NotNull(message = "买方身份证号不能为null")
    private String buyerIdcard;

    @NotNull(message = "买方身份证地址不能为null")
    private String buyerIdcardAddress;

    @NotNull(message = "买方联系方式不能为null")
    private String buyerPhone;

    @NotNull(message = "买方身份证证正面照不能为null")
    private String buyerIdcardFront;

    @NotNull(message = "买方身份证证反面照不能为null")
    private String buyerIdcardReverse;

    //买方机构名称
    private String buyerAgency;

    // 买方统一社会信用代码
    private String buyerCreditCode;

    // 买方营业执照
    private String buyerBusinessLicense;

    /**
     * 买方地址
     */
    private String buyerAddress;


}
