import cn.com.zcj.client.demo.Clients;
import cn.com.zcj.client.demo.Configure;
import cn.com.zcj.client.demo.MVC;
import com.alibaba.fastjson.JSONArray;
import com.maxcar.base.util.dasouche.Result;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/14.
 */
public class Test {

    public  static void main(String args[]){
        Result result=new Result();
        try {
            Clients c=new Clients();
            String  loginData =c.Login("TEST@TEST", "TEST@TEST");//登录
            System.out.println(loginData);
            com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(loginData);
            if (json.getInteger("code").equals(0)) {
                com.alibaba.fastjson.JSONObject data=json.getJSONObject("data");
                String userId=data.getString("userId");
                String accessToken=data.getString("token");
                String keyData= c.RefreshToken(userId,accessToken);//刷新秘银
                com.alibaba.fastjson.JSONObject keyJson = com.alibaba.fastjson.JSONObject.parseObject(keyData);
                if (keyJson.getInteger("code").equals(0)){
                    com.alibaba.fastjson.JSONObject data1=keyJson.getJSONObject("data");
                    String token=data1.getString("token");
                    System.out.println("accessToken============="+accessToken);
                    System.out.println("token============="+token);
                    System.out.println(keyJson);
                    Map<String,Object> map=new HashMap<String,Object>();
                    map.put("vin","14521452145214521");
                    String dataList=c.getList(map,token);//获取列表
                    System.out.println("vin返回List参数==="+dataList);
                    com.alibaba.fastjson.JSONObject listJson = com.alibaba.fastjson.JSONObject.parseObject(dataList);
                    if (listJson.getInteger("code").equals(0)){
                        JSONArray jsonArray=listJson.getJSONObject("page").getJSONArray("list");
                        if (null !=jsonArray&&jsonArray.size()>0){
                            String order_id = jsonArray.getJSONObject(0).getString("order_Id");
                            System.out.println(order_id);
                            String time1  = String.valueOf(System.currentTimeMillis());
                            int stop = time1.length();
                            String fileName ="D:\\img\\"+"zcj"+"_"+time1+".jpg";
                            System.out.print("sssssssssss==="+fileName);
                                MVC.download("orderId="+order_id+"&type="+"B",fileName,Configure.ZCJ_DOWN, token);
                        }else {
                            result.setResultCode(600);
                            result.setMessage("列表数据为null");
                        }
                        System.out.println("List参数==="+jsonArray);
                    }else {
                        result.setResultCode(600);
                        result.setMessage(listJson.getInteger("msg").toString());
                    }
                }else {
                    result.setResultCode(600);
                    result.setMessage(keyJson.getInteger("msg").toString());
                }
            }else {
                result.setResultCode(600);
                result.setMessage(json.getInteger("msg").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResultCode(500);
        }
    }
}
