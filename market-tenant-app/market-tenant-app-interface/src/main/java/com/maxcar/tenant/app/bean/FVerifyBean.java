package com.maxcar.tenant.app.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class FVerifyBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "身份证号码不能为空")
    private String idCardNumber;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "图片地址不能为空")
    private String imageUrl;

}