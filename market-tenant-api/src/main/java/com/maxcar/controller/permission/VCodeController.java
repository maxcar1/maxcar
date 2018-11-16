package com.maxcar.controller.permission;

import com.maxcar.BaseController;
import com.maxcar.constant.Constants;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.redis.service.VCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@RestController
public class VCodeController extends BaseController {

    @Autowired
    private VCodeService vCodeService;

    /**
     * 发送手机验证码
     *
     * @param phoneNum 手机号码
     * @return
     */
    @GetMapping(value = "/send/sms-vcode")
    public InterfaceResult sendPhoneCode(HttpServletRequest request,
                                         @RequestParam String phoneNum) throws Exception{

        if (!Pattern.matches(Constants.PHONE_REGEX, phoneNum)) {
            InterfaceResult interfaceResult = new InterfaceResult();
            interfaceResult.InterfaceResult600("手机号码格式不对");
            return interfaceResult;
        }

        return vCodeService.sendLoginVCode(phoneNum, getIpAddr(request));
    }
}
