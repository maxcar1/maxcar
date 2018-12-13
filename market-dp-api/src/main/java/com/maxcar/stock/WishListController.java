package com.maxcar.stock;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.service.WishListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("/market-api/api")
@RestController
public class WishListController {

    @Autowired
    private WishListService wishListService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /* *
     * 扫码跳转心愿单页面
     * @return*/
    @RequestMapping(value="/carWish",method= RequestMethod.GET)
    public ModelAndView wishList(WishList wishList){
        ModelAndView view = new ModelAndView("redirect:http://test.maxcar.com.cn/carWish?userId="+wishList.getUserId()+"&market="+wishList.getMarket()+"");
        try {

        }catch (Exception e ){

            e.printStackTrace();
        }
        return  view;
    }
    /**
     * 返回结果到心愿单页面
     * @param wishList
     * @return
     */
    @RequestMapping(value="/getWishList",method= RequestMethod.GET)
    public InterfaceResult getWishList(WishList wishList){
        InterfaceResult result = new InterfaceResult();
        try {
            List<WxWishListCar> list = wishListService.getWishListCar(wishList);
            if(list!=null&&!list.isEmpty()){
                for (WxWishListCar car: list ) {
                    if(car.getInitial()!=null&&!"".equals(car.getInitial())){
                        car.setInitial(car.getInitial().substring(0,4));
                    }
                }
            }
            result.setData(list);
            result.setCode("200");
        }catch (Exception e ){
            result.setMsg("服务器错误");
            result.setCode("500");
            e.printStackTrace();
        }
        return  result;
    }



    /**
     * 心愿单按钮点击跳转
     * @return
     */
    @RequestMapping(value="/wishListButton",method= RequestMethod.GET)
    public ModelAndView wishListButton(HttpServletRequest request){
        String userId = null;
        String redireuri=null;
        try {
            String code = request.getParameter("code");
            logger.info("code==================="+code);
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
            String wxappid = prop.getProperty("wxappid");
            String wxsecret = prop.getProperty("wxsecret");
            String wxouthurl = prop.getProperty("wxouthurl");
            redireuri = prop.getProperty("redireuri");
            wxouthurl = wxouthurl.replace("APPID" ,wxappid).replace("APPSECRET",wxsecret).replace("CODE",code);
            String result = HttpClientUtils.sendGet(wxouthurl);
            logger.info("返回结果=============="+result);
            JSONObject json = JSONObject.parseObject(result);

           userId = json.getString("openid");
        }catch (Exception e ){
            e.printStackTrace();
        }

        ModelAndView view = new ModelAndView("redirect:"+redireuri+"?userId="+userId+"&market=007");
        logger.info("用户id==============="+userId);

        return  view;
    }
}
