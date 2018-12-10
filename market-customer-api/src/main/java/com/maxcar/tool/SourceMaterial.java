package com.maxcar.tool;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.weixin.model.AccessToken;

import java.io.File;

/**
 * 素材相关
 */
public class SourceMaterial {

    private static final String APP_ID = "wx79a93cf533c7e664";
    private static final String APP_SECRET = "d4216f3e896fb9d3b6bdec441b002b31";

    private static String access_toen_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    private static String upload_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=image";

    private static String uploadimg_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

    private static String getAccessToken(){
        StringBuilder tokenUrl = new StringBuilder(access_toen_url);
        tokenUrl.append("&appid=").append(APP_ID).append("&secret=").append(APP_SECRET);
        String response = HttpClientUtils.sendGet(tokenUrl.toString());
        System.out.println(response);
        JSONObject json = JSONObject.parseObject(response);
        AccessToken accessToken = JSONObject.toJavaObject(json, AccessToken.class);
        System.out.println(accessToken.getAccess_token());
        return accessToken.getAccess_token();
    }

    /**
     * 图片上传微信
     * @param type 1为新增其他类型永久素材(url和mediaId);2为上传图文消息内的图片获取URL(只返回url)
     */
    private static void addPic(int type){
        String aToken = getAccessToken();
        String res1 = "";
        if (type == 1){
            //图片（image）、语音（voice）、视频（video）和缩略图（thumb）
            String newUrl1 = upload_url.replace("ACCESS_TOKEN",aToken);
            //工具类生成media和url
            //{"media_id":"amP7tCi9fdPygahpdJCvt16GHKFUoG-UbNtlwHj09XM","url":"http://mmbiz.qpic.cn/mmbiz_jpg/Iz7Q9eRSCFbprEuMhFD23AWd5tlMicUelw4bW9frojc2cxvWDia65kFFYdkBp1mgaI6CagUBPZMH7O3STCd4jEdg/0?wx_fmt=jpeg"}
            //{"media_id":"56iStkzJ52DytyE1tfb-4FYyriKw-8vBaq9Gy33101Q","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_png\/JCPYnZibazppyoyGofosTWKJbicuYfiaunveoZK67hvIZbN0YW43oANJBLexcuILGH8MjUjktnkQ5RHAD94GpzkBg\/0?wx_fmt=png"}
            File file1 = new File("C:\\Users\\Administrator\\Desktop\\23.png");
            res1 = HttpClientUtils.sendPost(newUrl1,file1);
        }else if (type == 2){
            String newUrl1 = uploadimg_url.replace("ACCESS_TOKEN",aToken);
            //工具类生成url
            //http://mmbiz.qpic.cn/mmbiz_jpg/Iz7Q9eRSCFbprEuMhFD23AWd5tlMicUelGBzN1fp0MUt1c9gHx6KdebQAeOz65X22Gpib3KstUlhYGraMibbBtqmw/0
            File file1 = new File("C:\\Users\\Administrator\\Desktop\\2.jpg");
            res1 = HttpClientUtils.sendPost(newUrl1,file1);
        }

        System.out.println(res1);
    }

    public static void main(String[] args){
        addPic(1);
    }

}
