package com.maxcar.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.base.util.JsonTools;
import com.maxcar.redis.service.RedisService;
import com.maxcar.redis.util.CacheKey;
import com.maxcar.tenant.app.bean.FVerifyBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FVerifyController extends BaseController {

    Logger logger = LoggerFactory.getLogger(FVerifyController.class);

    private static final String APP_ID = "YmKnqVq6qOEGgQ9ovzdu058G";

    private static final String SECRET_KEY = "RcCuyPT5UYKBeVWWjkFC5VD3ubYjsnL3";

    private static final String FACE_VERIFY_URL = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify?access_token=";

    private static final String AUTH_URL = "https://aip.baidubce.com/oauth/2.0/token";

    @Autowired
    private RedisService redisService;

    /**
     * 人脸验证
     *
     * @return
     */
    @PostMapping(value = "/face/verify")
    public InterfaceResult faceVerify(@RequestBody @Valid FVerifyBean fVerifyBean,
                                      BindingResult result) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        InterfaceResult interfaceResult = new InterfaceResult();

        String token = redisService.get(CacheKey.BAIDU_ACCESS_TOKEN);
        if (StringUtils.isBlank(token)) {
            token = getAuth();
            redisService.set(CacheKey.BAIDU_ACCESS_TOKEN, token, 24 * 3600);
        }

        Float score = faceVerify(fVerifyBean, token);
        if (score > 80.0) {
            return interfaceResult;
        } else {
            interfaceResult.InterfaceResult600("验证失败");
            return interfaceResult;
        }
    }

    /**
     * 获取token
     */
    public String getAuth() {
        String getAccessTokenUrl = AUTH_URL + "?grant_type=client_credentials"
                + "&client_id=" + APP_ID
                + "&client_secret=" + SECRET_KEY;
        try {
            String res = HttpClientUtils.sendGet(getAccessTokenUrl);
            JSONObject jsonObject = JSONObject.parseObject(res);
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            logger.error("get access_token fail. error:", e);
        }
        return null;
    }


    public Float faceVerify(FVerifyBean fVerifyBean, String token) {
        try {
            Map<String, Object> map = new HashMap<>(8);
            map.put("image", fVerifyBean.getImageUrl());
            map.put("image_type", "URL");
            map.put("id_card_number", fVerifyBean.getIdCardNumber());
            map.put("name", fVerifyBean.getName());

            String param = JsonTools.toJson(map);
            String result = HttpClientUtils.sendPost(FACE_VERIFY_URL + token, param);

            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getIntValue("error_code") == 0) {
                return jsonObject.getJSONObject("result").getFloatValue("score");
            }
            return 0.0f;
        } catch (Exception e) {
            logger.error("face verify fail. error:", e);
            return 0.0f;
        }
    }
}
