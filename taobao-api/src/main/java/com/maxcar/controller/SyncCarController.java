package com.maxcar.controller;

import com.maxcar.core.exception.ResultCode;
import com.maxcar.core.utils.CollectionUtil;
import com.maxcar.core.utils.Result;
import com.maxcar.core.utils.StringUtils;
import com.maxcar.entity.CarEntity;
import com.maxcar.entity.CarPicture;
import com.maxcar.service.TaoBaoService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/tbapi")
public class SyncCarController {
	@Autowired
	private TaoBaoService taoBaoService;
	/**
	 * 车辆图片临时存储目录,用于临时存放从网络获取的车辆图片
	 */
	@Value("#{fileProperties['projectUrl']}")
	private String projectUrl;
	public static String sessionKey = "" ;

	private static Logger logger = LoggerFactory.getLogger(SyncCarController.class);

	/**
	 * 向淘宝添加商品
	 * @param params
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addMarketCars",method=RequestMethod.POST)
	public Result addMarketCars(HttpServletRequest req,@RequestBody JSONObject params) {

		Result result = new Result();
		Map classMap = new HashMap();
		classMap.put("picList",CarPicture.class);
		CarEntity carEntity =( CarEntity) JSONObject.toBean(params, CarEntity.class, classMap);
		if(carEntity!=null) {
			Properties prop = new Properties();
			try {
				String initialLicenceTime = (java.lang.String) params.get("initialLicenceTimeStr");
				carEntity.setInitialLicenceTime(initialLicenceTime);
			} catch (Exception e) {
				e.printStackTrace();
			}


//        String sell_cid = "1396000473,1396000474,1396000475,1396000476";

//			sessionKey = prop.getProperty("marketIdSessionKey"+carEntity.getMarket());

			/*sessionKey = RedisUtil.getInstance().strings().get("taobao_"+carEntity.getMarket()+"_sessionkey");
			if(sessionKey==null&&params.getString("market").equals("010")) {
				sessionKey = "6200611a301add1a9ffhj1ce89dfa6e0efacac383f336424052462357";
			}*/
			sessionKey = "6200611a301add1a9ffhj1ce89dfa6e0efacac383f336424052462357";
			if(StringUtils.isBlank(sessionKey)) {
				result.setMessage("没有sessionkey，请生成!");
				result.setResultCode(ResultCode.NOT_FOUNT.getCode());
				return result;
			}
		}
		//1.获取图片
		System.out.println("开始上传图片" + System.currentTimeMillis());
		List<CarPicture> getPicList = carEntity.getPicList();
		if(CollectionUtil.listIsNotEmpty(getPicList)) {
			/*System.out.println(taoBaoService.addImg(getPicList,
					projectUrl,sessionKey));*/
		}
		System.out.println("上传图片结束" + System.currentTimeMillis());
			carEntity.setAccessToken(sessionKey);
			result = taoBaoService.addCar(carEntity, getPicList);
			JSONObject json = JSONObject.fromObject( result.getItem());
			/*if(!json.isEmpty() && json.get("item_add_response") != null) {
				json = (JSONObject) json.get("item_add_response");
				if(!json.isEmpty() && json.get("item") != null) {
					json = (JSONObject) json.get("item");
					String numIid = json.getString("num_iid");
					result.setDatas(numIid);
//					RedisUtil.getInstance().hash().d
				}
			}*/
		return result;
	}
	/**
	 * 下架汽车
	 * @param
	 * @return
	 */
	@RequestMapping(value="/deListIngCars/{numIid}",method=RequestMethod.GET)
	public Result deListIng(@PathVariable("numIid") Long numIid) {
		logger.info("下架汽车,numId:{}",new Long[] {numIid});
		Result result = new Result();
		//根据市场id 获取sessionKey
		result = taoBaoService.deListIngCar(numIid, sessionKey);
		logger.info("返回结果:{}", new Result [] {result});
		return result;
	}

	/**
	 * 删除车辆
	 * @return
	 */
	@RequestMapping(value = "/deleteCar/{numId}",method = RequestMethod.GET)
	public Result deleteCar(@PathVariable("numId") Long numId){
		logger.info("删除汽车,numId:{}",new Long[] {numId});
		Result result = new Result();
		//根据市场id获取sessionKey
		result = taoBaoService.deleteCar(numId,sessionKey);
		logger.info("返回结果:{}", new Result [] {result});

		return result;

	}

	/**
	 * 更新车辆价格
	 * @param params
	 * @return
	 */
	@PostMapping("/price")
	public Result updatePrice(@RequestBody JSONObject params){
		Result result = new Result();
		String numIid = params.getString("numIid");
		String price = params.getString("price");
		String market = params.getString("market");
		sessionKey = RedisUtil.getInstance().strings().get("taobao_"+market+"_sessionkey");
		result = taoBaoService.updatePrice(numIid , price ,sessionKey);


		return result;
	}

	/**
	 * 更新车辆信息
	 * @param params
	 * @return
	 */
	public Result updateTaoBaoCar(JSONObject params){
		Result result = new Result();
		Map classMap = new HashMap();
		classMap.put("picList",CarPicture.class);
		CarEntity carEntity =( CarEntity) JSONObject.toBean(params, CarEntity.class, classMap);
		if(carEntity!=null) {
			try {
				String initialLicenceTime = (java.lang.String) params.get("initialLicenceTimeStr");
				carEntity.setInitialLicenceTime(initialLicenceTime);

			} catch (Exception e) {
				e.printStackTrace();
			}
			sessionKey = RedisUtil.getInstance().strings().get("taobao_"+carEntity.getMarket()+"_sessionkey");
			if(StringUtils.isBlank(sessionKey)) {
				result.setMessage("没有sessionkey，请生成!");
				result.setResultCode(ResultCode.NOT_FOUNT.getCode());
				return result;
			}
		}
		//1.获取图片
		System.out.println("开始上传图片" + System.currentTimeMillis());
		List<CarPicture> getPicList = carEntity.getPicList();
		if(CollectionUtil.listIsNotEmpty(getPicList)) {
			/*System.out.println(taoBaoService.addImg(getPicList,
					projectUrl,sessionKey));*/
		}
		System.out.println("上传图片结束" + System.currentTimeMillis());
		carEntity.setAccessToken(sessionKey);
		result = taoBaoService.updateCar(carEntity, getPicList);
		JSONObject json = JSONObject.fromObject( result.getItem());
		return result;
	}
	/**
	 * 向淘宝添加商品
	 * @param params
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/cars",method=RequestMethod.POST)
	public Result syncCarToTaoBao(HttpServletRequest req,@RequestBody JSONObject params) {
		Result result = new Result();
		Map classMap = new HashMap();
		classMap.put("picList",CarPicture.class);
		CarEntity carEntity =( CarEntity) JSONObject.toBean(params, CarEntity.class, classMap);
		if(carEntity!=null) {
			Properties prop = new Properties();
			try {
				String initialLicenceTime = (java.lang.String) params.get("initialLicenceTimeStr");
				carEntity.setInitialLicenceTime(initialLicenceTime);
				prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*sessionKey = RedisUtil.getInstance().strings().get("taobao_"+carEntity.getMarket()+"_sessionkey");
			if(sessionKey==null&&params.getString("market").equals("010")) {
				sessionKey = "6200611a301add1a9ffhj1ce89dfa6e0efacac383f336424052462357";
			}*/
			sessionKey = prop.getProperty("marketIdSessionKey" + carEntity.getMarket());
//			sessionKey = "6201c01c5ZZ36c63cc00dd0203b3eafcef61a302f3469c74052462357";
			//sessionKey = "6202606b03389ceg4494cb17e6441dc751e558b7a234ae73430314546";
			if(StringUtils.isBlank(sessionKey)) {
				result.setMessage("没有sessionkey，请生成!");
				result.setResultCode(ResultCode.NOT_FOUNT.getCode());
				return result;
			}
		}
		//1.获取图片
		System.out.println("开始上传图片" + System.currentTimeMillis());
		List<CarPicture> getPicList = carEntity.getPicList();
		for (int i = 0; i < getPicList.size(); i++) {
			if(getPicList.get(i).getType()==0) {
				getPicList.remove(i);
			}
		}
		System.out.println(getPicList.size());
		if(CollectionUtil.listIsNotEmpty(getPicList)) {
			 result = taoBaoService.addImg(carEntity , getPicList,projectUrl,sessionKey);
			 //Map map = result.getDatas();
		}
		System.out.println("上传图片结束" + System.currentTimeMillis());
		carEntity.setAccessToken(sessionKey);
		if(carEntity.getMarketPrice()==null||"".equals(carEntity.getMarketPrice())||"0".equals(carEntity.getMarketPrice())) {
			carEntity.setMarketPrice(carEntity.getEvaluatePrice());
		}
		
		result = taoBaoService.addMarketCar(carEntity, getPicList);
		//com.alibaba.fastjson.JSONObject json =  (com.alibaba.fastjson.JSONObject) result.getItem();
		//判断返回结果
		/*if(json.containsKey("item_add_response")) {
			json = json.getJSONObject("item_add_response");
			json = json.getJSONObject("item");
			img.setNumId(json.getString("num_iid"));
		}*/
		//保存淘宝返回图片
		/*if(img.getImgId()!=null&&!img.getImgId().isEmpty()) {
			for (int i = 0; i < img.getImgId().size(); i++) {
				
			}
		}*/
		return result;
	}

    /**
     * 上架
     * @return
     *//*
    @RequestMapping(value="/groundCar",method=RequestMethod.POST)
	public String upload(){
        String APP_KEY = "24812065";
        String SECRET = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";
        String API_URL = "http://gw.api.taobao.com/router/rest";

        TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
        ItemUpdateListingRequest req = new ItemUpdateListingRequest();
        req.setNumIid(1L);
        req.setNum(2L);
        ItemUpdateListingResponse rsp = null;
        try {
            rsp = client.execute(req, "6200611a301add1a9ffhj1ce89dfa6e0efacac383f336424052462357");
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
	}
*/

}
