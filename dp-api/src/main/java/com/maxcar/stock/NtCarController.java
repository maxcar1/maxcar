package com.maxcar.stock;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.CollectionUtil;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.dasouche.*;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.CarChannelService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.service.WishListService;
import com.maxcar.stock.vo.CarChannelVo;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("/market-api/api")
@RestController

public class NtCarController  {
    @Autowired
    private CarService carService;
    @Autowired
    private WishListService wishListService;
    @Autowired
    private UserTenantService userTenantService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConcurrentHashMap<String, HighlightsTime> hightlightsMap = new ConcurrentHashMap<>();
    public static final long CACHE_EXPIRE = 1000 * 3600 * 24;

    /**
     * 获取车辆列表接口
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/cars",method= RequestMethod.POST)
    public InterfaceResult listCar(@RequestBody JSONObject params, HttpSession session) throws Exception {
        InterfaceResult result = new InterfaceResult();
        List<String> idList = new ArrayList<String>();
        List<DpCar> resultList = new ArrayList<DpCar>();
        DpCar car=(DpCar)JSONObject.toBean(params, DpCar.class);
        try {
        if (car != null) {
            List<CarIcon> illumineList = carService.listCarIcon();
            if(car.getId()!= null) {
                String [] idArry = car.getId().split(",");
                if(idArry != null && idArry.length >0) {
                    for (String string : idArry) {
                        idList.add(string);
                    }
                    car.setIds(idList);
                }
            }
            //万公里数转换成公里数
            if(car.getMinMileage()!=null&&car.getMinMileage() != 0){
                car.setMinMileage(car.getMinMileage()*10000);
            }
            if(car.getMaxMileage()!=null&&car.getMaxMileage() != 0){
                car.setMaxMileage(car.getMaxMileage()*10000);
            }

            Result res  = carService.getAll(car);
            List<DpCar> list = (List<DpCar>) res.getDatas();
            for (DpCar c : list) {
                List<CarPic> getPictureList = carService.getCarPic(c.getId());
                UserTenant userTenant = userTenantService.selectByPrimaryKey(c.getTenant());
                if(userTenant != null ){
                    c.setTenantName(userTenant.getTenantName());
                }
                if(c.getFirstResgister() != null && !c.getFirstResgister().equals("")){
                    c.setFirstResgister(c.getFirstResgister().substring(0,c.getFirstResgister().indexOf(" ")));
                }

                if (getPictureList != null) {
                    List<String> l = new ArrayList<String>();

               for (CarPic carPicture : getPictureList) {
                    l.add(carPicture.getSrc());
                }
                c.setImages(l);
                }
                //拼接两点信息
                if (c.getModelCode() == null || "".equals(c.getModelCode()) || "null".equals(c.getModelCode())) {
                    logger.info("c.getModelCode() is null, id = {}", c.getId());
                    continue;
                }
                if (illumineList.size() == 0) {
                    continue;
                }
                List<IllumineIconTitleUrl> highlights = null;
                HighlightsTime highlightsTime = hightlightsMap.get(c.getModelCode());
                if (highlightsTime != null && (System.currentTimeMillis() - highlightsTime.getTime()) < CACHE_EXPIRE) {
                    highlights = highlightsTime.getList();
                }
                if (highlights != null && highlights.size() > 0) {
                    c.setHighlights(highlights);
                    continue;
                }

                highlights = new ArrayList<>(8);
                Result r = detailInfo(c.getModelCode());
                if (r == null || r.getResultCode() != 200) {
                    continue;
                }
                highlights = new ArrayList<>(8);
                JSONObject jo = JSONObject.fromObject(r.getItem());


                    for (CarIcon carIcon : illumineList) {
                        if (!jo.containsKey(carIcon.getField())) {
                            continue;
                        }
                        String value = jo.getString(carIcon.getField());
                        if (!value.contains("●")) {
                            continue;
                        }
                        //String url = urlStart + carIcon.getUrl();
                        String url = carIcon.getUrl();
                        highlights.add(new IllumineIconTitleUrl(carIcon.getTitle(), url));
                        if (highlights.size() >= 8) {
                            break;
                        }
                    }

                c.setHighlights(highlights);
            resultList.add(c);
            }
        }
        result.setData(resultList);
        result.setCode("200");
        return result;
        } catch (Exception e) {
            result.setCode("500");
            result.setMsg("服务器错误");
            logger.error(e.getMessage(), e);
        }
        return  result;
    }
    private Result detailInfo(String scModelCode) throws IOException {
        Result result = new Result();
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
        String newUrl = prop.getProperty("dashouchedetailurl");
        String appKey = prop.getProperty("dashoucheappKey");
        String appSecret = prop.getProperty("dashoucheappSecret");
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scModelCode", scModelCode);

        Iterator<?> it = params.keys();

        List<String> listKey = new ArrayList();
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        StringBuffer sb = new StringBuffer();
        sb.append("appKey=");
        sb.append(appKey);
        for (String string : listKey)
        {
            sb.append("&");
            sb.append(string);
            sb.append("=");
            sb.append(params.get(string));
        }
        String signStringBase64 = null;
        try {
            signStringBase64 = BaseEncodeUtil.encodeBase64(sb.toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sign = appSecret + ":" + signStringBase64;
        sign = SHAUtil.getSha1(sign);
        String s = HttpClientUtil.get(newUrl + sb, "utf-8", sign);
        logger.info("大搜车返回=============="+s);
        JSONObject oo = JSONObject.fromObject(s);
        if (oo.getString("status").equals("200")){
            result.setItem(oo.get("data"));
            result.setResultCode(200);
        }

        return result;
    }

    /**
     * 获取二维码
     * @return
     */
    @RequestMapping(value="/getQrcode",method= RequestMethod.GET)
    public InterfaceResult getQrcode(WishList wishList){
        InterfaceResult response = new InterfaceResult();
        try {
            String marketId = wishList.getMarketId();
            String carId = wishList.getCarId();
            String[] carIds = carId.split(",");
            List<String> carIdList = Arrays.asList(carIds);

            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
            String wxappid = prop.getProperty("wxappid");
            String wxsecret = prop.getProperty("wxsecret");
            String wxaccesstokenurl = prop.getProperty("wxaccesstokenurl");
            String wxtickeyurl = prop.getProperty("wxtickeyurl");
            String wxqrcodeurl = prop.getProperty("wxqrcodeurl");

            String result = HttpClientUtils.sendGet(wxaccesstokenurl+"appid="+wxappid+"&secret="+wxsecret);
            logger.info("url====="+wxaccesstokenurl+"appid="+wxappid+"&secret="+wxsecret);
            com.alibaba.fastjson.JSONObject accessJsonObject  = com.alibaba.fastjson.JSONObject.parseObject(result);
            String accessToken = accessJsonObject.getString("access_token");
            logger.info("accessJsonObject==========="+result);
            logger.info("access_token==========="+accessToken);
            // https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=
            String ticketParam="{\"expire_seconds\": 7200, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+marketId+"}}}";
            String ticketStr = HttpClientUtils.sendPost(wxtickeyurl+accessToken,ticketParam);
            com.alibaba.fastjson.JSONObject ticketJsonObject  = com.alibaba.fastjson.JSONObject.parseObject(ticketStr);
            logger.info("code==========="+ticketJsonObject);
            String ticket =ticketJsonObject.getString("ticket");
            String qrcodeUrl = wxqrcodeurl+ticket;
            for (String str:
                    carIdList ) {
                wishList.setCarId(str);
                wishList.setTicket(ticket);
                wishList.setInsertTime(new Date());
                wishListService.insertWishList(wishList);
            }
            response.setData(qrcodeUrl);
            response.setCode("200");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode("500");
            response.setMsg("服务器内部错误");
        }
        return response;
    }

}
