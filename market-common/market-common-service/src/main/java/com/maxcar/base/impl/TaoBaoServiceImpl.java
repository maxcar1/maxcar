package com.maxcar.base.impl;


import cn.com.zcj.client.demo.Clients;
import cn.com.zcj.client.demo.Configure;
import cn.com.zcj.client.demo.MVC;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.dao.TaobaoMarketConfigMapper;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.pojo.taobao.*;
import com.maxcar.base.service.*;
import com.maxcar.base.util.CollectionUtil;
import com.maxcar.base.util.RedisUtil;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.dasouche.ResultCode;
import com.maxcar.base.util.taobao.*;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 上传淘宝接口
 *author: chiyl
 *
 */
@Service("taoBaoService")
public class TaoBaoServiceImpl implements TaoBaoService {
	private static Logger logger = LoggerFactory.getLogger(TaoBaoServiceImpl.class);
	@Autowired
	private TaobaoItemValuesService taobaoItemValuesService;
	@Autowired
	private TaobaoCarService taobaoCarService;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private DaSouCheService daSouCheService;
	@Autowired
	private TaobaoMarketConfigMapper taobaoMarketConfigMapper;

	//	@Value("${projectUrl}")
	private String projectUrl="D://img";
	private String ftlUrl="/data/shangchuantaobao/";
	//维珍验车
	@Value("${wzurl}")
	private String wzurl;
	@Value("${wzmcname}")
	private String wzmcname;
	@Value("${wzappid}")
	private String wzappid;
	@Value("${wzappkey}")
	private String wzappkey;
	private static String APP_KEY = "24812065";
	private static String SECRET = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";
	private static String API_URL = "http://gw.api.taobao.com/router/rest";
	private final static Long TAOBAO_CID = 50050566L;
	private static String ZCJ_LOGIN="WUHAOMING@CZ";
	private static String ZCJ_PASSWORD="WUHAOMING@CZ";

	@Override
	public Result addImg(CarEntity car, List<CarPicture> getPicList, String path, String code) {
		Result result = new Result();
		// 取到云端图片下载到本地
		// 根据类型上传图片
		// 将地址返回本地缓存 taobao_car_pic_-1_00199
		// redis 中 命名 taobao_car_pic_cartype_picId : 淘宝图片空间地址
		// 删除本地图片
		// 拼接到商品信息中
		/*Map map = new HashMap();
		List<String> imgId = new ArrayList<String>();
		List<String> imgPath = new ArrayList<String>();*/
		try {
			logger.info("添加图片路径 path:{}", path);
			for (CarPicture carPicture : getPicList) {
				if(carPicture.getType() == -1) {
					continue;
				}

				// }
				// getPicList.forEach(carPicture -> {
				// 上传到本地
				String time1  = String.valueOf(System.currentTimeMillis());
				int stop = time1.length();
				String fileName = car.getVin()+"_"+carPicture.getType()+"_"+time1.substring(stop-3, stop)+".jpg";
				//fileName = FileUtils.download(fileName, carPicture.getPic(), path);
				fileName = download(fileName,carPicture.getPic(), path);
				logger.info("添加图片名称 fileName:{}", fileName);
				try {
					// 上传到淘宝空间
					try {
						JSONObject json = new JSONObject();
						json = updateTaobaoImg(path + "/" + fileName, fileName, code);
						logger.info("添加图片返回数据 json:{}", json.toString());
						if (!json.isEmpty() && json.get("picture_upload_response") != null) {
							json = (JSONObject) json.get("picture_upload_response");
							if (!json.isEmpty() && json.get("picture") != null) {
								json = (JSONObject) json.get("picture");
								if (!json.isEmpty() && json.get("picture_path") != null) {
									RedisUtil.getInstance().strings().set(
											"taobao_car_pic_" + carPicture.getType() + "_" + carPicture.getPic(),
											json.getString("picture_path"));
								}
							}
						}
						result.setResultCode(ResultCode.CREATE_SUCCESS.getCode());
					} catch (Exception e) {
						logger.error("上传图片到淘宝空间保存");
						result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					FileUtils.delFile(path, fileName);
					FileUtils.delFile(path, "small" + fileName);
				}
			}
			// );
			/*map.put("imgId", imgId);
			map.put("imgPath", imgPath);*/
			result.setResultCode(ResultCode.CREATE_SUCCESS.getCode());
			//result.setDatas(map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
		}
		return result;
	}

	/**
	 * 上传淘宝图片
	 *
	 * @param
	 * @return file 文件路径，带文件名 c：//test/1.png fileName 文件名 code 淘宝返回值
	 */
	public static JSONObject updateTaobaoImg(String file, String fileName, String code) {
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		PictureUploadRequest req = new PictureUploadRequest();
		req.setPictureCategoryId(0L);
		File f = new File(file);
		// 判断图片大小 大于等于 3M需要压缩
		if (FileUtils.getFileSize(f) >= 3) {
			// 压缩图片，生成新的图片，命名规则
			Result result = uploadFileAndCreateThumbnail(file, fileName, f);
			if (result != null && result.getResultCode() == 200) {
				f = new File(result.getDatas().toString());
			}
		}
		req.setImg(new FileItem(f));
		req.setImageInputTitle(fileName);
		req.setTitle(fileName.substring(0, fileName.indexOf(".")));
		req.setClientType("client:computer");
		req.setIsHttps(true);
		JSONObject json = new JSONObject();
		try {
			PictureUploadResponse rsp = client.execute(req, code);
			json = JSONObject.parseObject(rsp.getBody());
			logger.info("上传图片返回参数：{}", rsp.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 压缩图片
	 *
	 * @param path
	 *            路径
	 * @param fileName
	 *            名称
	 * @param imageFile
	 *            图片流
	 * @return
	 */
	public static Result uploadFileAndCreateThumbnail(String path, String fileName, File imageFile) {
		/**
		 * 缩略图begin
		 */
		path.substring(0, path.lastIndexOf("/"));
		long size = imageFile.length();
		double scale = 1.0d;
		if (size >= 200 * 1024) {
			if (size > 0) {
				scale = (200 * 1024f) / size;
			}
		}
		// 拼接文件路劲
		try {
			Thumbnails.of(path).scale(scale).toFile(path.substring(0, path.lastIndexOf("/")) + "/small" + fileName);
		} catch (Exception e1) {
			e1.printStackTrace();
			return Result.error(ResultCode.SERVICE_ERROR.getCode(), "操作失败");
		}
		return Result.ok(ResultCode.GET_SUCCESS.getCode(), "压缩成功",
				path.substring(0, path.lastIndexOf("/")) + "/small" + fileName);
	}

	@Override
	public Result deListIngCar(Long numIid, String sessionKey) {
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemUpdateDelistingRequest req = new ItemUpdateDelistingRequest();
		req.setNumIid(numIid);
		try {
			ItemUpdateDelistingResponse rsp = client.execute(req, sessionKey);
			JSONObject json = JSONObject.parseObject(rsp.getBody());
			if (json.get("item_update_delisting_response") != null) {
				result.setResultCode(ResultCode.CREATE_SUCCESS.getCode());
				json = (JSONObject) json.get("item_update_delisting_response");
				result.setDatas(json.get("item"));
			} else {
				result.setResultCode(ResultCode.CREATE_ERROR.getCode());
				result.setDatas(rsp.getBody());
			}
		} catch (ApiException e) {
			result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
		}
		return result;
	}

	@Override
	public Result deleteCar(Long numId, String sessionKey) {
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemDeleteRequest req = new ItemDeleteRequest();
		req.setNumIid(numId);
		try{
			ItemDeleteResponse rsp = client.execute(req, sessionKey);
			JSONObject json = JSONObject.parseObject(rsp.getBody());
			if(json.get("item_delete_response") != null){
				result.setResultCode(ResultCode.CREATE_SUCCESS.getCode());
				json = (JSONObject) json.get("item_delete_response");
				result.setDatas(json.get("item"));
			}else{
				result.setResultCode(ResultCode.CREATE_ERROR.getCode());
				result.setDatas(rsp.getBody());
			}
		}catch (Exception e){
			e.printStackTrace();
			result.setResultCode(ResultCode.SERVICE_ERROR.getCode());

		}
		return result;
	}

	@Override
	public Result updatePrice(String numIid, String price, String sessionKey) {
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemSkuUpdateRequest req = new ItemSkuUpdateRequest();
		Long id = Long.parseLong(numIid);
		Float p = Float.parseFloat(price) * 10000;
		DecimalFormat df = new DecimalFormat("#.##");
		String marketPrice = df.format(p);
		//Float p = Float.parseFloat(price);
		req.setNumIid(id);
		req.setProperties("14829532:72110507");
		req.setPrice(marketPrice);
		req.setItemPrice(marketPrice);
        /*req.setNumIid(123456L);
        req.setProperties("1627207:28326;1630696:3266779");
        req.setQuantity(3L);
        req.setPrice("207.02");
        req.setOuterId("123456");
        req.setItemPrice("204");
        req.setLang("zh_CN");
        req.setSpecId("123");
        req.setBarcode("6903244981002");
        req.setIgnorewarning(",ifd_warning,FakeCredit_Warning,");*/
		try {
			ItemSkuUpdateResponse rsp = client.execute(req, sessionKey);
			System.out.println(rsp.getBody());
			JSONObject json = JSONObject.parseObject(rsp.getBody());
			if(json.get("item_sku_update_response") != null){
				result.setResultCode(ResultCode.CREATE_SUCCESS.getCode());
				json = (JSONObject) json.get("item_sku_update_response");
				result.setDatas(json.get("sku"));
			}else {
				result.setResultCode(ResultCode.CREATE_ERROR.getCode());
				result.setDatas(rsp.getBody());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateCar(CarEntity car, List<CarPicture> getPicList) {
		return null;
	}

	@Override
	public Result addMarketCar(CarEntity car, List<CarPicture> getPicList) {

		System.out.print("++++++++++++++++++++++开始上传淘宝serviceImpl+++++++++++++++++++++++");
		System.out.println("==="+wzurl);
		City city = new City();
		try {
			city = cityService.getCityById(Integer.parseInt(car.getAttribution()));
		} catch (Exception e) {
			logger.error("error.", e);
		}
		System.out.print("++++++++++++++++++++++开始上传淘宝city==================="+city);
		TaobaoMarketConfig config=taobaoMarketConfigMapper.selectByPrimaryKey(car.getMarket());
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemAddRequest request = new ItemAddRequest();
		String sessionKey=config.getSessionKey();
		request.setCid(config.getCid().longValue());// 叶子类目id
		request.setNum(config.getNum().longValue());// 商品数量
		request.setAuctionPoint(config.getAuctionPoint().longValue());
		request.setType(config.getType());// 发布类型。可选值:fixed(一口价),auction(拍卖)
		request.setStuffStatus(config.getStuffStatus());// 新旧程度。可选值：new(新)，second(二手)，unused(闲置)
		request.setHasInvoice(config.getHasInvoice()); // 发票 有
		request.setHasWarranty(config.getHasWarranty()); //是否有保修。可选值:true,false;默认值:false(不保修)
		request.setApproveStatus(config.getApproveStatus());// instock在仓库中，onsale在售，默认在售
		request.setSubStock(config.getSubStock().longValue()); // 商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改
		Double price = Double.valueOf(car.getMarketPrice()) * 10000;

		DecimalFormat df = new DecimalFormat("#.##");
		String marketPrice = df.format(price);
		if (StringUtils.isNotBlank(car.getMarketPrice())) {
			request.setPrice(marketPrice);// 商品价格
		} else {
			request.setPrice("0");// 商品价格
		}
		//设置定金
		request.setSkuProperties(config.getSkuProperties());
		request.setSkuQuantities(config.getSkuQuantities());
		String subscription=config.getSubscription();
		//订金
		//玉林市场单独配置
		if (config.getMarketId().equals("010")){
			if(Double.valueOf(car.getMarketPrice())<=10.00) {
				subscription="300";
			}else if(Double.valueOf(car.getMarketPrice())<=20&&Double.valueOf(car.getMarketPrice())>10) {
				subscription="500";
			}else if(Double.valueOf(car.getMarketPrice())<=30&&Double.valueOf(car.getMarketPrice())>20) {
				subscription="1000";
			}else {
				subscription="1500";
			}
		}

		request.setSkuPrices(subscription+","+marketPrice);
		request.setSkuOuterIds(config.getSkuOuterIds());
		request.setLocationState(config.getLocationState());// 所在省
		request.setLocationCity(config.getLocationCity());
		request.setValidThru(config.getValidThru().longValue());// 有效期
		request.setSellPromise(config.getSellPromise());//是否承诺退换货服务!虚拟商品无须设置此项!
		request.setIsOffline(config.getIsOffline());//是否是线下商品。1：线上商品（默认值）；2：线上或线下商品；3：线下商品。
		request.setLocalityLifeExpirydate(config.getLocalityLifeExpirydate());// 电子凭证时效为7天
		request.setLocalityLifeMerchant(config.getLocalityLifeMerchant());
		request.setLocalityLifeOnsaleAutoRefundRatio(config.getLocalityLifeOnsaleAutoRefundRatio().longValue());
		request.setLocalityLifeRefundRatio(config.getLocalityLifeRefundRatio().longValue());//退款比例，百分比%前的数字,1-100的正整数值
		//设置核销门店
		request.setLocalityLifeChooseLogis(config.getLocalityLifeChooseLogis());//使用邮寄
		request.setFreightPayer(config.getFreightPayer());//买家运费承担
		request.setPostFee(config.getPostFee());
		request.setExpressFee(config.getExpressFee());
//		request.setLocalityLifeObs("1");
		request.setLocalityLifeEticket(config.getLocalityLifeEticket());

		// 车辆描述图片
		List<String> listPic = new ArrayList<String>();
		List<String> mainPic = new ArrayList<String>();
		List<String> aspectList = new ArrayList<String>(); //外观
		List<String> interiorList = new ArrayList<String>();//内部
		if (CollectionUtil.listIsNotEmpty(getPicList)) {
			getPicList.forEach(carPicture -> {
				String key = "taobao_car_pic_" + carPicture.getType() + "_" + carPicture.getPic();
				// 判断缓存是否有值
				if (RedisUtil.getInstance().keys().exists(key)) {

					listPic.add(RedisUtil.getInstance().strings().get(key));
					// 设置主图片
					if (carPicture.getType() == 0) {
						return;
					}
					if (carPicture.getType() == 1) {
						request.setPicPath(RedisUtil.getInstance().strings().get(key));
						mainPic.add(RedisUtil.getInstance().strings().get(key));
						//aspectList.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}else if(carPicture.getType() == 7 || carPicture.getType() == 8 || carPicture.getType() == 9 ||
							carPicture.getType() == 10 || carPicture.getType() == 11 || carPicture.getType() == 12
							|| carPicture.getType() == 13){
						//aspectList.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}else {
						//interiorList.add(RedisUtil.getInstance().strings().get(key));

						RedisUtil.getInstance().keys().del(key);
					}

				}
			});
		}
		for (String string : listPic) {
			logger.info("淘宝上传图片返回==="+string);
		}

		String unit = "";
		if(car.getModelName()!=null&&car.getModelName().contains("L")) {
			unit= car.getModelName().substring(car.getModelName().indexOf("L")-3,car.getModelName().indexOf("L")+1 );
		}else if(car.getModelName()!=null&&car.getModelName().contains("T")) {
			unit = car.getModelName().substring(car.getModelName().indexOf("T")-3,car.getModelName().indexOf("T")+1 );
		}
		String title =car.getBrandName().trim()+car.getModelYear()+car.getSeriesName()+unit+config.getMarketName()+config.getMarketPhone();
		if(title.length()>30) {
			title=title.substring(0, title.indexOf(config.getMarketName().substring(0,1)));
		}
		request.setTitle(title);
		System.out.print("request============================"+request);
		String ftlName=config.getFtlName();
		Map<String, Object> map = BeanUtils.obj2Map(car);
		map.put("listPic", listPic);
		map.put("客户名称",config.getMarketName());
		map.put("marketPhone",config.getMarketPhone());
		// 获取车辆检测信息
		if (config.getCheckSource()==1){
			result=checkByWZ(car,map,request);
			if (result.getResultCode()==200){
				map.put("attribution", city.getName());
				map.put("mainPic", mainPic);
				map.put("initialLicenceTimeStr", car.getInitialLicenceTimeStr());
				// 获取车辆亮点信息
				if (StringUtils.isNotBlank(car.getModelCode())) {
					map.put("listIcon", listIcon(car.getModelCode()));
				}
			}else {
				result.setResultCode(600);
				result.setMessage("无维真检测报告");
				return result;
			}
		}else if (config.getCheckSource()==2){
			result=checkByZCJ(car.getVin());
			String time1  = String.valueOf(System.currentTimeMillis());
			int stop = time1.length();
			String fileName = car.getVin()+"_"+time1.substring(stop-3, stop)+".jpg";
			JSONObject json = new JSONObject();
			json = updateTaobaoImg(projectUrl+"\\"+result.getMessage(), result.getMessage(), sessionKey);
			logger.info("添加图片返回数据 json:{}", json.toString());
			if (!json.isEmpty() && json.get("picture_upload_response") != null) {
				json = (JSONObject) json.get("picture_upload_response");
				if (!json.isEmpty() && json.get("picture") != null) {
					json = (JSONObject) json.get("picture");
					if (!json.isEmpty() && json.get("picture_path") != null) {
						map.put("zcjUrl", json.get("picture_path").toString());
					}else {
						ftlName="carInfo";
					}
				}else {
					ftlName="carInfo";
				}
			}else {
				ftlName="carInfo";
			}
		}else if (config.getCheckSource()==3){
			if (result.getResultCode()==600){
				ftlName="carInfo";//
			}
		}

		// 车辆详情
//		logger.info("车辆详情文件路径：path {}", new String[] { getWebClassesPath() });
		request.setDesc(FreeMarkUtil.getHtmlString(map, ftlName, ftlUrl));
		inputInputs(request, car);
		inputProps(request, car,city);
		System.out.println("Packageid====================="+request.getLocalityLifePackageid());
		ItemAddResponse rsp = new ItemAddResponse();
		try {
			rsp = client.execute(request, car.getAccessToken());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("淘宝返回"+rsp.getMsg()+"--------"+rsp.getSubMsg()+"----------"+rsp.getBody());
		JSONObject json = JSONObject.parseObject(rsp.getBody());
		//上传成功后向商品中添加4张非主图图片
		if(!json.isEmpty() && json.get("item_add_response") != null) {
			json = (JSONObject) json.get("item_add_response");
			if(!json.isEmpty() && json.get("item") != null) {
				json = (JSONObject) json.get("item");
				String numIid = json.getString("num_iid");
				result.setDatas(numIid);
				ItemImgUploadRequest req = new ItemImgUploadRequest();
				req.setNumIid(Long.parseLong(numIid));
				//req.setPosition(2L);
				for(CarPicture carPicture : getPicList){
					if(carPicture.getType() ==  2 || carPicture.getType() == 3||carPicture.getType() ==10||carPicture.getType() ==4){
						String time1  = String.valueOf(System.currentTimeMillis());
						int stop = time1.length();
						String fileName = car.getVin()+"_"+carPicture.getType()+"_"+time1.substring(stop-3, stop)+".jpg";
						String name = download(fileName , carPicture.getPic(),projectUrl);
						req.setImage(new FileItem(projectUrl+"//"+name));
						try{
							ItemImgUploadResponse rsp1 = client.execute(req, car.getAccessToken());
						}catch (Exception e){
							e.printStackTrace();
						}finally {
							FileUtils.delFile(projectUrl+"//", name);
						}

					}
				}
			}
		}

		result.setItem(rsp.getBody());
		return result;
	}

	@Override
	public Result syncCarToTaoBao(String jsonStr) {
		JSONObject params=JSONObject.parseObject(jsonStr);
		String sessionKey="";
		System.out.println(projectUrl);
		Result result = new Result();
		Map classMap = new HashMap();
		classMap.put("picList",CarPicture.class);
		CarEntity carEntity =( CarEntity) JSONObject.toJavaObject(params, CarEntity.class);
		if(carEntity!=null) {
			try {
				String initialLicenceTime = (java.lang.String) params.get("initialLicenceTimeStr");
				carEntity.setInitialLicenceTime(initialLicenceTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sessionKey =taobaoMarketConfigMapper.selectByPrimaryKey(carEntity.getMarket()).getSessionKey();
			System.out.print("sessionKey======================="+sessionKey);
			if(StringUtils.isBlank(sessionKey)) {
				result.setMessage("没有sessionkey，请生成!");
				result.setResultCode(ResultCode.NOT_FOUNT.getCode());
				return result;
			}
		}
		//1.获取图片
		System.out.println("开始上传图片" + System.currentTimeMillis());
		List<CarPicture> getPicList = carEntity.getPicList();
		if(getPicList!=null&&!getPicList.isEmpty()){
			for (int i = 0; i < getPicList.size(); i++) {
				if(getPicList.get(i).getType()==0) {
					getPicList.remove(i);
				}
			}
			System.out.println(getPicList.size());
			if(CollectionUtil.listIsNotEmpty(getPicList)) {
				result = addImg(carEntity , getPicList,projectUrl,sessionKey);
				//Map map = result.getDatas();
			}
		}

		System.out.println("上传图片结束" + System.currentTimeMillis());
		carEntity.setAccessToken(sessionKey);
		if(carEntity.getMarketPrice()==null||"".equals(carEntity.getMarketPrice())||"0".equals(carEntity.getMarketPrice())) {
			carEntity.setMarketPrice(carEntity.getEvaluatePrice());
		}
		try {
			result =addMarketCar(carEntity, getPicList);
		}catch (Exception E){
			logger.error("error."+E);
		}
		logger.info("asdddddddddddddddddddddddddddddd========="+result);
		return result;
	}

	public Result getOpenOauth(String code) {
		Result result = new Result();
		String url = "https://oauth.taobao.com/token";
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		props.put("code", code);
		props.put("client_id", APP_KEY);
		props.put("client_secret", SECRET);
		props.put("redirect_uri", "http://test.maxcar.com.cn/market-api");
		props.put("view", "web");
		try {
			String s = WebUtils.doPost(url, props, 30000, 30000);
			JSONObject json = JSONObject.parseObject(s);
			try {
				if (!json.isEmpty() && !json.get("taobao_user_id").equals("")) {
					result.setResultCode(ResultCode.GET_SUCCESS.getCode());
					result.setItem(json);
				}
			} catch (Exception e) {
				result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
				result.setMessage(json.getString("error_description"));
			}
		} catch (IOException e) {
			result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public static String download(String fileName ,String urlString, String savePath) {
		try {
			// 构造URL
			URL url = new URL(urlString);
			logger.info("######################url" + url);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			logger.info("######################savePath" + savePath);
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			logger.info("######################fileName" + fileName);
			logger.info("######################fileName" + sf.getPath() + "/" + fileName);
			OutputStream os = new FileOutputStream(sf.getPath() + "/" + fileName);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	// 获取当前文件路径
//	public static String getWebClassesPath() {
//		try {
//			String path = TaoBaoServiceImpl.class.getResource("").getPath();
//			// getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
//			// //包含类名
//			return path;
//		} catch (Exception e) {
//		}
//		return "";
//	}

	private static void inputInputs(ItemAddRequest request, CarEntity car) {
		// 143410077:车架号;20207674:首次上牌时间;148224679:验车时间（验车时间年检到期日）;30259:公里数
		// 1627131:原车用途;
		String vinNumber = car.getVin();
		// 新车指导价
		// 首次上牌时间
		SimpleDateFormat dateSf = new SimpleDateFormat("YYYY-MM");
		String fristDate = car.getInitialLicenceTime();
		if (StringUtils.isNotBlank(fristDate)) {
			try {
				//fristDate = dateSf.format(dateSf.parse(fristDate));
				fristDate = fristDate.substring(0,7);
				car.setInitialLicenceTime(fristDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 验车时间(年检到期日,如果为空就取当前时间,格式为：YYYY-MM-dd)
		String date = dateSf.format(new Date());
		//String checkCarDate = date.substring(0,4)+fristDate.substring(4,7);


		String mileage = car.getMileage();

		String newPrice = car.getNewPrice();
		StringBuilder pidsSb = new StringBuilder();
		StringBuilder inputSb = new StringBuilder();
		boolean isFirst = true;
		pidsSb.append("143410077");// vin
		inputSb.append(vinNumber);
		String splitStr = ",";
		isFirst = false;
		if (StringUtils.isNotEmpty(fristDate)) {
			if (!isFirst) {
				pidsSb.append(splitStr);
				inputSb.append(splitStr);

			} else {
				isFirst = false;
			}
			pidsSb.append("20207674");
			inputSb.append(fristDate);
		}
		/*if (StringUtils.isNotEmpty(checkCarDate)) {
			if (!isFirst) {
				pidsSb.append(splitStr);
				inputSb.append(splitStr);

			} else {
				isFirst = false;
			}
			pidsSb.append("148224679");
			inputSb.append(checkCarDate);
		}*/
		if (StringUtils.isNotEmpty(mileage)) {
			if (!isFirst) {
				pidsSb.append(splitStr);
				inputSb.append(splitStr);

			} else {
				isFirst = false;
			}
			pidsSb.append("30259");
			inputSb.append(mileage + "万公里");
		}

		if (StringUtils.isNotEmpty(newPrice)) {
			if (!isFirst) {
				pidsSb.append(splitStr);
				inputSb.append(splitStr);

			} else {
				isFirst = false;
			}
			DecimalFormat df = new DecimalFormat("0.00");
			String p = df.format(Double.parseDouble(newPrice));
			String newCarPrice = p + "万元";
			pidsSb.append("148656181");
			inputSb.append(newCarPrice);
		}
		request.setInputPids(pidsSb.toString());
		request.setInputStr(inputSb.toString());
	}

	private void inputProps(ItemAddRequest request, CarEntity car,City city) {
		String brand = car.getBrandName();
		String series = car.getSeriesName();
		String model = car.getModelName();

		StringBuilder props = new StringBuilder();
		// 排放标准
		String stand = car.getEnvironmentalStandards();
		if (StringUtils.isNotBlank(stand)) {
			TaobaoItemValues taobaoItemValues = new TaobaoItemValues();
			if (stand.contains("(")) {
				stand = stand.substring(stand.indexOf("(") + 1, stand.lastIndexOf(")"));
			}
			if (stand.contains("IV")) {
				stand = stand.replace("IV", "Ⅳ");
			} else if (stand.contains("I")) {
				stand = stand.replace("I", "Ⅰ");
			} else if (stand.contains("II")) {
				stand = stand.replace("II", "Ⅱ");
			} else if (stand.contains("III")) {
				stand = stand.replace("III", "Ⅲ");
			}
			if(StringUtils.isNotBlank(stand) && stand.length() > 2){
				stand = stand.substring(0, 2);
			}

			taobaoItemValues.setName(stand);

			taobaoItemValues.setPid("10265769");
			/*taobaoItemValues = taobaoItemValuesService.getTaobaoItemValues(taobaoItemValues);*/
			props.append("10265769:396242950" + ";");
		}
		ItempropsGetRequest req = new ItempropsGetRequest();

		// 品牌
		List<TaobaoCar> listTBCar = new ArrayList<TaobaoCar>();
		TaobaoCar tbCar = new TaobaoCar();
		if (StringUtils.isNotBlank(brand)) {
			tbCar.setBrandPid("136152291");
			tbCar.setBrandName(brand);
			listTBCar = taobaoCarService.getTaobaoCar(tbCar);
			if (CollectionUtil.listIsNotEmpty(listTBCar)) {
				//props.append("136152291:" + listTBCar.get(0).getBrandVid() + ";");
			}
		}
		// 车系
		// 如果车系中包含品牌，则去除品牌字符
		if (StringUtils.isNotBlank(model)) {
			// 遍历取到的车型对比获取距离最小值
			int m = 0;
			TaobaoCar cartemp = new TaobaoCar();// 存储临时数据
			//替换掉车系名称带车品牌导致匹配不上问题
			if(StringUtils.isNotBlank(series)&&series.contains(brand)) {
				series = series.substring(series.indexOf(brand)+brand.length());
			}
			tbCar.setSeriesName(series);

			listTBCar = taobaoCarService.getTaobaoCar(tbCar);
			for (int i = 0; i < listTBCar.size(); i++) {
				TaobaoCar taobaoCar = listTBCar.get(i);
				int d = EditDistanceUtil.getEditDistance(series, taobaoCar.getSeriesName());
				if (i == 0) {
					m = d;
					cartemp = taobaoCar;
				} else {
					if (d < m) {
						m = d;
						cartemp = taobaoCar;
					}
				}
			}
			if (cartemp != null) {
				//props.append(cartemp.getSeriesPid() + ":" + cartemp.getSeriesVid() + ";");
			}

		}
		/*if (StringUtils.isNotBlank(series)) {
			if (series.contains(brand)) {
				series = series.replace(brand, "");
			}
			tbCar.setSeriesName(series);
			listTBCar = taobaoCarService.getTaobaoCar(tbCar);
			if (CollectionUtil.listIsNotEmpty(listTBCar)) {
				props.append(listTBCar.get(0).getSeriesPid() + ":" + listTBCar.get(0).getSeriesVid() + ";");
			}
		}*/
		// 年款
		if (StringUtils.isNotBlank(car.getModelYear())) {
			tbCar.setYearName(car.getModelYear());
			listTBCar = taobaoCarService.getTaobaoCar(tbCar);
			if (CollectionUtil.listIsNotEmpty(listTBCar)) {
				//props.append(listTBCar.get(0).getYearPid() + ":" + listTBCar.get(0).getYearVid() + ";");
			}
			if(listTBCar.size()==1){
				props.append(listTBCar.get(0).getBrandPid() + ":" + listTBCar.get(0).getBrandVid() + ";");
				props.append(listTBCar.get(0).getSeriesPid() + ":" + listTBCar.get(0).getSeriesVid() + ";");
				props.append(listTBCar.get(0).getYearPid() + ":" + listTBCar.get(0).getYearVid() + ";");
				props.append(listTBCar.get(0).getModelPid() + ":" + listTBCar.get(0).getModelVid() + ";");
			}else{
				// 车型
				if (StringUtils.isNotBlank(model)) {
					// 遍历取到的车型对比获取距离最小值
					int m = 0;
					TaobaoCar cartemp = new TaobaoCar();// 存储临时数据
					tbCar.setModelName(model);
					//listTBCar = taobaoCarService.getTaobaoCar(tbCar);
					for (int i = 0; i < listTBCar.size(); i++) {
						TaobaoCar taobaoCar = listTBCar.get(i);
						int d = EditDistanceUtil.getEditDistance(model, taobaoCar.getModelName());
						if (i == 0) {
							m = d;
							cartemp = taobaoCar;
						} else {
							if (d < m) {
								m = d;
								cartemp = taobaoCar;
							}
						}
					}
					if (cartemp != null) {
						props.append(cartemp.getBrandPid() + ":" + cartemp.getBrandVid() + ";");
						props.append(cartemp.getSeriesPid() + ":" + cartemp.getSeriesVid() + ";");
						props.append(cartemp.getYearPid() + ":" + cartemp.getYearVid() + ";");
						props.append(cartemp.getModelPid() + ":" + cartemp.getModelVid() + ";");
					}

				}

			}
		}
		// 省市
		if (StringUtils.isNotBlank(car.getAttribution())) {
			if (city != null) {
				Province province = provinceService.getProvinceById(city.getProvince());
				if (province != null) {
					TaobaoItemValues taobaoItemValues = new TaobaoItemValues();
					taobaoItemValues.setPid("122450261");// 省份
					String p = province.getName();
					if (p.contains("市")) {
						p = p.substring(0, p.indexOf("市"));
					}
					taobaoItemValues.setName(p);
					taobaoItemValues = taobaoItemValuesService.getTaobaoItemValues(taobaoItemValues);
					props.append("122450261:" + taobaoItemValues.getVid() + ";");
					taobaoItemValues = new TaobaoItemValues();
					taobaoItemValues.setParentvid(taobaoItemValues.getVid());
					taobaoItemValues.setName(city.getName());
					taobaoItemValues = taobaoItemValuesService.getTaobaoItemValues(taobaoItemValues);
					props.append(taobaoItemValues.getPid() + ":" + taobaoItemValues.getVid() + ";");
				}
			}
		}
		//添加sku属性(整车价、预约金)
		props.append("14829532:3292081;14829532:72110507;");
		request.setProps(props.toString());
	}

	/**
	 * 拼接亮点数据
	 *
	 * @param modelCode
	 * @return
	 */
	public List<String> listIcon(String modelCode) {
		List<String> list = new ArrayList<String>();
		Result r = detailInfo(modelCode);
		if (r.getResultCode() == 200) {
			JSONObject jo = JSONObject.parseObject(r.getItem().toString());
			@SuppressWarnings("unchecked")
			Set<String> set = jo.keySet();
			set.forEach(string -> {
				if(list.size()<8) {
					logger.info("##################################" + list.size());
					if (jo.get(string).equals("●")) {
						String key = "taobao_carIcon_" + string;
						if (RedisUtil.getInstance().keys().exists(key)) {
							list.add(RedisUtil.getInstance().strings().get(key));
						}
					}
				}

			});
		}
		return list;
	}

	private Result detailInfo(String scModelCode) {
		Result result = new Result();
		if (scModelCode == null || "".equals(scModelCode)) {
			result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
			result.setMessage("车辆编码不能为空");
			return result;
		}
		JSONObject params = new JSONObject();
		params.put("timestamp", TimeStampUtils.getTimeM());
		params.put("scModelCode", scModelCode);
		// 循环遍历json字符串 参数
//		Iterator<?> it = params.keys();
		Iterator keys = params.keySet().iterator();
		// 遍历json数据，添加到Map对象
		List<String> listKey = new ArrayList<String>();
		while (keys.hasNext()) {
			String key = String.valueOf(keys.next());
			listKey.add(key);
		}
		Collections.sort(listKey);
		result = daSouCheService.getAllService(2, params, listKey);
		return result;
	}

	public Result checkByWZ(CarEntity car,Map<String, Object> map,ItemAddRequest request){
		Result result=new Result();
		String vin = car.getVin();
		String wzjs = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String time = formatter.format(new Date());
		StringBuffer md5 = new StringBuffer();
		md5.append(wzmcname).append(vin).append(wzappid).append(wzappkey).append(time).toString();
		String sign = MD5Util.MD5(String.valueOf(md5)).toLowerCase();
		//String sign = FileUtils.sign(vin, time, wzappid, wzmcname, wzappkey);
		String url = wzurl + "?mcname=" + wzmcname + "&vin=" + car.getVin() + "&sign=" + sign + "&key=" + wzappkey +
				"&appid=" + wzappid;
		try {
			wzjs = HttpClientUtil.get(url, "utf-8", null);
			System.out.println("维真返回参数"+wzjs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		com.alibaba.fastjson.JSONObject js = com.alibaba.fastjson.JSONObject.parseObject(wzjs);
		if("0".equals(js.get("code"))){
			com.alibaba.fastjson.JSONObject js2 = com.alibaba.fastjson.JSONObject.parseObject(js.get("data").toString());
			com.alibaba.fastjson.JSONObject js3 = com.alibaba.fastjson.JSONObject.parseObject(js2.get("report").toString());
			CheckWZ checkResult = JsonTools.toObj(js3.toString(), CheckWZ.class);
			for (int i = 0; i < checkResult.getAccident().size(); i++) {
				map.put(checkResult.getAccident().get(i).getName(), checkResult.getAccident().get(i).getVal());

			}
			for (int i = 0; i < checkResult.getBaseinfo().size(); i++) {

				map.put(checkResult.getBaseinfo().get(i).getName(), checkResult.getBaseinfo().get(i).getVal());
			}
			for (int i = 0; i < checkResult.getWater().size(); i++) {

				map.put(checkResult.getWater().get(i).getName(), checkResult.getWater().get(i).getVal());

			}
			for (int i = 0; i < checkResult.getFire().size(); i++) {

				map.put(checkResult.getFire().get(i).getName(), checkResult.getFire().get(i).getVal());

			}
			map.put("检测时间",js2.get("created").toString());
			map.put("客户名称",js2.get("phone").toString());
			if(js2.get("phone")!=null||!"".equals(js2.get("phone"))) {
//				Object packageId = taobaoCarService.selectPackageid(js2.get("phone").toString());
//				if(packageId!=null) {
//					request.setLocalityLifeVersion("1");
//					request.setLocalityLifePackageid(packageId.toString());
//				}
			}
			String unit = "";
			if(car.getModelName()!=null&&car.getModelName().contains("L")) {
				unit= car.getModelName().substring(car.getModelName().indexOf("L")-3,car.getModelName().indexOf("L")+1 );
			}else if(car.getModelName()!=null&&car.getModelName().contains("T")) {
				unit = car.getModelName().substring(car.getModelName().indexOf("T")-3,car.getModelName().indexOf("T")+1 );
			}
			String title = "二手车 "+car.getBrandName().trim()+car.getModelYear()+car.getSeriesName()+unit+"玉林阿里智慧汽车城 "+js2.get("phone").toString().replaceAll("二手车", "").trim();
			if(title.length()>30) {
				title=title.substring(0, title.indexOf("玉"));
			}
			request.setTitle(title);
			result.setResultCode(200);
		}else {
			result.setResultCode(600);
			result.setDatas("该车辆没有维真检测信息！");
		}
		return result;
	}
	public Result checkByZCJ(String vin){
		Result result=new Result();
		try {
			Clients c=new Clients();
			String  loginData =c.Login(ZCJ_LOGIN, ZCJ_PASSWORD);//登录
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
					map.put("vin",vin);
					String dataList=c.getList(map,token);//获取列表
					System.out.println("vin返回List参数==="+dataList);
					com.alibaba.fastjson.JSONObject listJson = com.alibaba.fastjson.JSONObject.parseObject(dataList);
					if (listJson.getInteger("code").equals(0)){
						JSONArray jsonArray=listJson.getJSONObject("page").getJSONArray("list");
						if (null !=jsonArray&&jsonArray.size()>0){
							String order_id = jsonArray.getJSONObject(0).getString("order_Id");
							System.out.println(order_id);
							String time1  = String.valueOf(System.currentTimeMillis());
							String fileName =projectUrl+"\\"+"zcj"+"_"+time1+".jpg";
							String path="zcj"+"_"+time1+".jpg";
							System.out.print("sssssssssss==="+fileName);
							MVC.download("orderId="+order_id+"&type="+"B",fileName, Configure.ZCJ_DOWN, token);
							result.setResultCode(200);
							result.setMessage(path);
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
		return  result;
	}

/*	public static void main(String[] args) throws ClientProtocolException, IOException {
		*//*String cookie ="miid=936631312420705576; thw=cn; isg=BHFxLB_DjtpfiiJrFJGmO4vBg_7L9ua4KUTpZVOGbThXepHMm671oB-YmM65qX0I; cna=HNPuE0w+xxkCAd3As3pVsH+R; hng=CN%7Czh-CN%7CCNY%7C156; t=74985f65696795ae5bea1cb2337dfcc5; _cc_=UIHiLt3xSw%3D%3D; tg=0; UM_distinctid=1659e7e0d7f449-0c76a73f20b8af8-143f7040-15f900-1659e7e0d80ef; um=65F7F3A2F63DF02056E1FBC491C68D1DFBBDE9EBC70A0515AC7E6A3C219EB8199288FFA41F1FEEB0CD43AD3E795C914CA19E165440C03613E995DB6774CABA30; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; ali_ab=60.12.250.54.1536224706622.3; mt=ci=0_1&np=; _m_h5_tk=4d6cf0fe442094bdd862a6a561620f7f_1541064344488; _m_h5_tk_enc=9819b715ae3455ba57f6f4117251d2c1; cookie2=7c7bccda3b3cba10fe07a333753378aa; _tb_token_=5855e3e813573; whl=-1%260%260%261541054217965; JSESSIONID=1CE155488904FA45988BFD2B8700B2AB; v=0; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&cookie21=W5iHLLyFfXVRCJf5l6bv6g%3D%3D&cookie15=UIHiLt3xD8xYTw%3D%3D&existShop=true&pas=0&cookie14=UoTYN4HMXVDxTQ%3D%3D&tag=8&lng=zh_CN; skt=fc34a89d90e01fba; csg=9a71f2f6; uc3=vt3=F8dByRjNVHS09QZZiow%3D&id2=VyyUyYyZZc600A%3D%3D&nk2=F5RGNwppVl5rbTg%3D&lg2=UIHiLt3xD8xYTw%3D%3D; existShop=MTU0MTA1NDQ3OQ%3D%3D; tracknick=tb312844838; lgc=tb312844838; dnk=tb312844838; unb=4052462357; sg=874; _l_g_=Ug%3D%3D; cookie1=W5jGlh8gjHrU%2F%2BHlyxyoY%2BlUPluTLnUci6q8eZMosZ8%3D; _nk_=tb312844838; cookie17=VyyUyYyZZc600A%3D%3D";
		String url = "https://ma.taobao.com/wangdian/EtcCommSell.do?_input_charset=utf-8&categoryId=50050566&from=taobao.sell&itemId=581139674990";
		String result = HttpClientUtil.getWithCookie(url,"UTF-8",cookie);
		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(result);
		if("200".equals(json.getString("code"))) {
			JSONArray array = json.getJSONObject("data").getJSONObject("storeBooking").getJSONArray("haveStoresNew");
			List<TaobaoShop> list = com.alibaba.fastjson.JSONObject.parseArray(array.toString(), TaobaoShop.class);
			System.out.println(list.size());
			for (TaobaoShop taobaoShop : list) {
				System.out.println(taobaoShop.getPackageName().substring(taobaoShop.getPackageName().indexOf("-")+1));

			}
		}*//*
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "24812065";
		String secret = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		ItemAddRequest req = new ItemAddRequest();
		req.setInputStr("LSGGF53W8CH015006,2013-01,888888万公里,0.00万元");
		req.setInputPids("143410077,20207674,30259,148656181");
		req.setProps("10265769:396242950;136152291:38915;136326248:6199399;136262505:30694075;136280418:636768829;122450261:3296643;128716001:3297117;14829532:3292081;14829532:72110507;");
		req.setSkuProperties("14829532:3292081,14829532:72110507");
		req.setSkuQuantities("1,1");
		req.setSkuPrices("2000,120000");
		req.setSkuOuterIds("3782914410043,3782914410044");
		req.setLocationState("江苏");
		req.setLocationCity("常州");
		req.setNum(1L);
		req.setPrice("120000");
		req.setType("fixed");
		req.setStuffStatus("second");
		req.setTitle("全车通测试二手车");
		req.setDesc("这是我上传的测试二手车");
		req.setApproveStatus("instock");
		req.setCid(50050566L);
		req.setFreightPayer("buyer");
		//req.setValidThru(7L);
		req.setHasInvoice(true);
		req.setHasWarranty(false);
		req.setSellerCids("1350951592,20523036");
		//选择核销门店
		req.setPostFee("999");
		req.setExpressFee("999");
		req.setSellPromise(true);
		req.setSubStock(2L);
		req.setIsOffline("3");
		req.setLocalityLifeChooseLogis("1");
		req.setLocalityLifeExpirydate("7");
		req.setLocalityLifeMerchant("0:淘宝");
		req.setLocalityLifeRefundRatio(100L);
		req.setLocalityLifeOnsaleAutoRefundRatio(100L);
		req.setLocalityLifeRefundmafee("1");
		req.setLocalityLifeEticket(";has_pos:1;");
		//req.setLocalityLifeVersion("1");
		//req.setLocalityLifePackageid("1378553014");

		try {
			ItemAddResponse response = client.execute(req , "6202827e9c5524d604ed4719164954c495ceg1401295ed74227823786");
			System.out.println(response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
