package com.maxcar.tenant.app.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class StaffLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "手机号码不能为空")
    private String phoneNum;

    @NotBlank(message = "验证码不能为空")
    private String vcode;

}
