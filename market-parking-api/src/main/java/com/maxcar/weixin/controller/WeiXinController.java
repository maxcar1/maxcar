package com.maxcar.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.wechat.SignUtil;
import com.maxcar.base.util.wechat.WeiXinUtils;
import com.maxcar.base.util.wechat.entity.WeixinUtil;
import com.maxcar.market.pojo.ParkingFeeDetail;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.websocket.server.WebSocketServer;
import com.maxcar.weixin.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

@RestController
@RequestMapping("/api-p/wx")
public class WeiXinController {

    static Logger LOGGER = LoggerFactory.getLogger(WeiXinController.class);
    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private ParkingFeeService parkingFeeService;

    @Value("${paibo.token}")
    private String token;

    @GetMapping("/jsapi")
    public Object getJsApi(){
        InterfaceResult result = new InterfaceResult();
        try {
            LOGGER.info("前端发起了请求---");
            result = weiXinService.getJsApi();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/paibo")
    public Object checkToken(@RequestParam("signature") String signature,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce,
                             @RequestParam("echostr") String echostr){
        if (SignUtil.checkSignature(signature, timestamp, nonce,token)) {
            return echostr;
        }
        return "token check error";
    }

    @PostMapping("/paibo")
    public Object handleWeiXin(HttpServletRequest request){
        String result ="";
        try {
            String res = WeiXinUtils.getXmlRequest(request);
            LOGGER.info("来自微信的推送消息==>{}",res);
            JSONObject json = weiXinService.handlePublicMsg(res,WeiXinUtils.WeiXinPublicNum.PAIBO);
            result = json.getString("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/test")
    public Object test(@RequestBody JSONObject params) throws Exception{
        InterfaceResult result = parkingFeeService.testPrice(params);
        return result;
    }

}
