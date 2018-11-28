package com.maxcar.core.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.maxcar.core.base.pojo.InterfaceResult;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * Created by 池彦龙 on 2018/8/3.
 */
public class SmsUntil {
    static final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    static final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
    static final String accessKeyId = "LTAItbDLnLJ4R0sy";//你的accessKeyId,参考本文档步骤2
    static final String accessKeySecret = "P8HlZmp6oQLQ7yTNNBDXXmzMM4Bw1G";//你的accessKeySecret，参考本文档步骤2
    static final String signName="无锡阿里二手车智慧市场";//必填:短信签名-可在短信控制台中找到
    static final String templateCode="SMS_141925012";//必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
    static final String templateCode2="SMS_141597722";//生日祝福短信
    static final String templateCode3="SMS_142145115";//活动
    public static InterfaceResult sendSms(Map<String,Object> map) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数

            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(map.get("phone").toString());
            request.setSignName(signName);
            if ((map.get("status").toString()).equals("1")){
                request.setTemplateCode(templateCode2);
            }else if((map.get("status").toString()).equals("2")){
                request.setTemplateCode(templateCode3);
            }else {
                request.setTemplateCode(templateCode);
            }
            String IdCode=getFourRandom();
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
            JSONObject members=new JSONObject();
            members.put("code",IdCode);
            members.put("content",map.get("content"));
            members.put("name",map.get("name"));
            members.put("text",map.get("text"));

            request.setTemplateParam(members.toString());
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                interfaceResult.InterfaceResult200(IdCode);
            }
            return interfaceResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        interfaceResult.InterfaceResult600("发送失败");
        return interfaceResult;
    }

    /**
     * 产生4位随机数(0000-9999)
     *
     * @return 4位随机数
     */
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }


    public static void main(String [] args){
        Map<String,Object> map=new HashMap<>();
        map.put("phone","15614185057");
        map.put("status","2");
        map.put("content","明天");
        map.put("text","送你七夕");
        //map.put("name","路向亚先生");
        SmsUntil.sendSms(map);
        System.out.println();
    }

}
