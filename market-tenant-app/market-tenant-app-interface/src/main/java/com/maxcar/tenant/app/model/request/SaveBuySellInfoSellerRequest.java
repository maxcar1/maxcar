package com.maxcar.tenant.app.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SaveBuySellInfoSellerRequest implements Serializable {

    private static final long serialVersionUID = 4352038806718888062L;
    // 过户车辆ID
    @NotNull(message = "过户车辆ID不能为null")
    private String  transferCarId;

    //卖方类型
    @NotNull(message = "卖方类型不能为null")
    private Integer  sellerType;

    //卖方姓名
    @NotNull(message = "卖方姓名不能为null")
    private String sellerName;

    //卖方身份证号
    @NotNull(message = "卖方身份证号不能为null")
    private String sellerIdcard;

    //卖方身份证地址
    @NotNull(message = "卖方身份证地址不能为null")
    private String sellerIdcardAddress;

    //卖方联系方式
    @NotNull(message = "卖方联系方式不能为null")
    private String sellerPhone;

    //卖方身份证正面照
    @NotNull(message = "卖方身份证正面照不能为null")
    private String sellerIdcardFront;

    //卖方身份证反面照
    @NotNull(message = "卖方身份证反面照不能为null")
    private String sellerIdcardReverse;

    //机构名称
    private String sellerAgency;

    //卖方统一社会信用代码
    private String sellerCreditCode;

    //地址
    private String sellerAddress;

    // 营业执照照片
    private String sellerBusinessLicense;

}
