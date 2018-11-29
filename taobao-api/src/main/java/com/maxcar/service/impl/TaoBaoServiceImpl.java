package com.maxcar.service.impl;

import com.maxcar.controller.RedisUtil;
import com.maxcar.core.exception.ResultCode;
import com.maxcar.core.utils.*;
import com.maxcar.entity.*;
import com.maxcar.service.*;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONObject;
import org.apache.http.client.ClientProtocolException;
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
 * 淘宝接口
 * 
 * @ClassName: TaoBaoServiceImpl
 * @author huangxu
 * @date 2018年3月27日 下午3:04:46
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
    @Value("#{fileProperties['projectUrl']}")
    private String projectUrl;
	
	private static String APP_KEY = "24812065";
	private static String SECRET = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";

	
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
			JSONObject json = JSONObject.fromObject(s);
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

	private static String API_URL = "http://gw.api.taobao.com/router/rest";
	private final static Long TAOBAO_CID = 50050566L;
	private static City city;
	private static Province province;
	public Result addCar(CarEntity car, List<CarPicture> getPicList) {
		city = new City();
		province = new Province();
		city = cityService.getCityById(Integer.parseInt(car.getAttribution()));
		province = provinceService.getProvinceById(city.getProvince());
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemAddRequest request = new ItemAddRequest();
		request.setCid(TAOBAO_CID);// 叶子类目id
		request.setAuctionPoint(1L);
		request.setNum(1L);// 商品数量
		request.setType("fixed");// 发布类型。可选值:fixed(一口价),auction(拍卖)
		request.setStuffStatus("second");// 新旧程度。可选值：new(新)，second(二手)，unused(闲置)
		request.setHasInvoice(true); // 发票 有
		request.setHasWarranty(false); // 保修 无
		request.setApproveStatus("instock");// instock在仓库中，onsale在售，默认在售
		request.setSubStock(2L); // 商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改
		Double price = Double.valueOf(car.getMarketPrice()) * 10000;
		DecimalFormat df = new DecimalFormat("#.##");
		String marketPrice = df.format(price);
		if (StringUtils.isNotBlank(car.getMarketPrice())) {
			request.setPrice(marketPrice);// 商品价格
		} else {
			request.setPrice("0");// 商品价格
		}
		//设置定金
		request.setSkuProperties("14829532:3292081,14829532:72110507");
		request.setSkuQuantities("1,1");
		request.setSkuPrices("3000,"+ marketPrice);
		request.setSkuOuterIds("3782914410043,3782914410044");
		//设置上传到仓库
		request.setApproveStatus("instock");

		request.setLocationState(province.getName());// 所在省
		request.setLocationCity(city.getName());

		request.setValidThru(7L);// 有效期
		request.setHasWarranty(true);//是否有保修。可选值:true,false;默认值:false(不保修)
		request.setSellPromise(true);//是否承诺退换货服务!虚拟商品无须设置此项!
		request.setIsOffline("3");//是否是线下商品。1：线上商品（默认值）；2：线上或线下商品；3：线下商品。
		request.setLocalityLifeExpirydate("3");// 电子凭证时效为7天
		request.setLocalityLifeMerchant("0:淘宝");
		request.setLocalityLifeOnsaleAutoRefundRatio(100L);
		request.setLocalityLifeRefundRatio(100L);//退款比例，百分比%前的数字,1-100的正整数值
		//设置核销门店
		request.setLocalityLifeChooseLogis("1");//使用邮寄
		request.setFreightPayer("buyer");//买家运费承担
//		request.setLocalityLifeObs("1");
		request.setLocalityLifeEticket(";has_pos:1;");

		//设置商品分类
		//String sell_cid = itemClassify(car);
		//request.setSellerCids("1350951592,1350961985,1350965273,1350968474,1378553017");
		request.setSellerCids(car.getSellCid());

		// taobao_car_pic_-1_00199
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
					// 设置主图片
					if (carPicture.getType() == 14) {
						request.setPicPath(RedisUtil.getInstance().strings().get(key));
						mainPic.add(RedisUtil.getInstance().strings().get(key));
						aspectList.add(RedisUtil.getInstance().strings().get(key));
						listPic.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}else if(carPicture.getType() == 7 || carPicture.getType() == 8 || carPicture.getType() == 9 ||
							carPicture.getType() == 10 || carPicture.getType() == 11 || carPicture.getType() == 12
		                    || carPicture.getType() == 13){
						aspectList.add(RedisUtil.getInstance().strings().get(key));
						listPic.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}else if(carPicture.getType() == 1 || carPicture.getType() == 2 || carPicture.getType() == 6 || carPicture.getType() == 3){
						interiorList.add(RedisUtil.getInstance().strings().get(key));
						listPic.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}

				}
			});
		}

		Map<String, Object> map = BeanUtils.obj2Map(car);
		map.put("attribution", city.getName());
		map.put("listPic", listPic);
		map.put("aspectList", aspectList);
		map.put("interiorList", interiorList);
		map.put("mainPic", mainPic);
		map.put("initialLicenceTimeStr", car.getInitialLicenceTimeStr());
		// 获取车辆亮点信息
		if (StringUtils.isNotBlank(car.getModelCode())) {
			map.put("listIcon", listIcon(car.getModelCode()));
		}
		// 获取车辆检测信息
//		String vin = car.getVin();
		String datas = "{\"BaseConfiguration\":[{\"Value\":\"两厢车\",\"Key\":\"车身结构\"},{\"Value\":\"MT(手动)\",\"Key\":\"变速箱\"},{\"Value\":\"5\",\"Key\":\"档位数\"},{\"Value\":\"自然吸气\",\"Key\":\"进气形式\"},{\"Value\":\"承载式车身\",\"Key\":\"车体结构\"},{\"Value\":\"前置前驱\",\"Key\":\"驱动方式\"},{\"Value\":\"机械液压助力\",\"Key\":\"助力类型\"},{\"Value\":\"L直列\",\"Key\":\"气缸排列\"},{\"Value\":\"4\",\"Key\":\"气缸数\"},{\"Value\":\"麦弗逊\",\"Key\":\"前悬挂类型\"},{\"Value\":\"独立\",\"Key\":\"后悬挂类型\"},{\"Value\":\"棕\",\"Key\":\"内饰颜色\"}],\"LicenseInfo\":[{\"Value\":\"-\",\"Key\":\"车主类型\"},{\"Value\":\"\",\"Key\":\"商业险有效期\"},{\"Value\":\"川\",\"Key\":\"车牌首字母\"},{\"Value\":\"AT6W63\",\"Key\":\"车牌号码\"},{\"Value\":\"\",\"Key\":\"车主姓名\"},{\"Value\":\"\",\"Key\":\"法人代码/身份证号码\"},{\"Value\":\"未知\",\"Key\":\"使用性质\"},{\"Value\":\"无\",\"Key\":\"机动车登记证书\"},{\"Value\":\"无\",\"Key\":\"机动车行驶证\"},{\"Value\":\"无\",\"Key\":\"购置税完税证明\"},{\"Value\":\"无\",\"Key\":\"购车发票\"},{\"Value\":\"\",\"Key\":\"交强险截止日期\"},{\"Value\":\"\",\"Key\":\"年审时间\"},{\"Value\":\"\",\"Key\":\"首次上牌时间\"},{\"Value\":\"-\",\"Key\":\"过户次数\"},{\"Value\":\"\",\"Key\":\"最后过户时间\"}],\"CarInfo\":[{\"Value\":\"轿车\",\"Key\":\"车辆类型\"},{\"Value\":\"橙\",\"Key\":\"车身颜色\"},{\"Value\":\"福特\",\"Key\":\"车辆品牌\"},{\"Value\":\"长安福特\",\"Key\":\"生产厂家\"},{\"Value\":\"2017-11\",\"Key\":\"车辆生产时间\"},{\"Value\":\"福克斯\",\"Key\":\"车系\"},{\"Value\":\"福克斯2017款两厢1.6L手动风尚型智行版\",\"Key\":\"车型\"},{\"Value\":\"LYLYLYLYLYLYLYLYL\",\"Key\":\"VIN码\"},{\"Value\":\"国V\",\"Key\":\"排放标准\"},{\"Value\":\"666\",\"Key\":\"发动机号码\"},{\"Value\":\"66666\",\"Key\":\"表显里程(公里)\"},{\"Value\":\"1.6\",\"Key\":\"发动机排量(L)\"},{\"Value\":\"汽油\",\"Key\":\"燃料种类\"}],\"Advantage\":[\"发动机启动良好，怠速稳定\",\"内饰整洁，无异味，无破损\",\"电器功能良好\"],\"InspectionGrade\":[{\"Value\":\"90\",\"Key\":\"技术状况\"},{\"Value\":\"84\",\"Key\":\"车身外观\"},{\"Value\":\"94\",\"Key\":\"内饰\"},{\"Value\":\"B\",\"Key\":\"事故等级\"}],\"FreeComments\":\"\",\"PictureInfo\":[{\"Describe\":\"左前45度\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftFront45Degree_5168_112108_1.jpg\",\"Type\":\"外观\",\"Name\":\"LeftFront45Degree_5168_112108.jpg\"},{\"Describe\":\"右后45度\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RightRear45Degree_5169_112722_1.jpg\",\"Type\":\"外观\",\"Name\":\"RightRear45Degree_5169_112722.jpg\"},{\"Describe\":\"正后（后备箱打开）\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RIghtBack_5175_112455_1.jpg\",\"Type\":\"外观\",\"Name\":\"RIghtBack_5175_112455.jpg\"},{\"Describe\":\"轮胎\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/Wheel_5177_112225_1.jpg\",\"Type\":\"外观\",\"Name\":\"Wheel_5177_112225.jpg\"},{\"Describe\":\"主驾驶舱\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/DrivingCabin_5170_112152_1.jpg\",\"Type\":\"外观\",\"Name\":\"DrivingCabin_5170_112152.jpg\"},{\"Describe\":\"仪表板公里数\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/Millage_5171_123033_1.jpg\",\"Type\":\"外观\",\"Name\":\"Millage_5171_123033.jpg\"},{\"Describe\":\"方向盘\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/DriveWheel_5172_112251_1.jpg\",\"Type\":\"外观\",\"Name\":\"DriveWheel_5172_112251.jpg\"},{\"Describe\":\"仪表台\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/DashboardPic_5174_112240_1.jpg\",\"Type\":\"外观\",\"Name\":\"DashboardPic_5174_112240.jpg\"},{\"Describe\":\"中控台\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/ConsoleDashboard_5173_112303_1.jpg\",\"Type\":\"外观\",\"Name\":\"ConsoleDashboard_5173_112303.jpg\"},{\"Describe\":\"发动机舱\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/EngineCompartment_5167_112817_1.jpg\",\"Type\":\"外观\",\"Name\":\"EngineCompartment_5167_112817.jpg\"},{\"Describe\":\"铭牌\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/VINPanel_5176_112532_1.jpg\",\"Type\":\"外观\",\"Name\":\"VINPanel_5176_112532.jpg\"},{\"Describe\":\"左前轮毂_划痕_重\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftFrontRim_A034_113513_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"LeftFrontRim_A034_113513.jpg\"},{\"Describe\":\"左前轮胎_缺损_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftFrontTyre_A043_113539_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"LeftFrontTyre_A043_113539.jpg\"},{\"Describe\":\"右前轮毂_划痕_重\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RightFrontRim_A048_121000_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"RightFrontRim_A048_121000.jpg\"},{\"Describe\":\"左后轮毂_划痕_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftRearRim_A083_115138_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"LeftRearRim_A083_115138.jpg\"},{\"Describe\":\"右后轮胎_缺损_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RightRearTyre_A079_121119_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"RightRearTyre_A079_121119.jpg\"},{\"Describe\":\"前制动盘（鼓）_磨损异常_重\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/FrontBrakeDisc_A064_113614_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"FrontBrakeDisc_A064_113614.jpg\"},{\"Describe\":\"底部护板_变形\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/UnderbodyCover_3349_131421_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"UnderbodyCover_3349_131421.jpg\"},{\"Describe\":\"左前门铰链_螺丝有拆卸痕迹\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftFrontDoorHinge_3704_114648_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"LeftFrontDoorHinge_3704_114648.jpg\"},{\"Describe\":\"左后门铰链_螺丝有拆卸痕迹\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftRearDoorHinge_3837_114730_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"LeftRearDoorHinge_3837_114730.jpg\"},{\"Describe\":\"右后门外观_凹陷_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RightDoor_A2138_120332_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"RightDoor_A2138_120332.jpg\"},{\"Describe\":\"右前翼子板_钣金修复\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RightFrontFender_4372_120849_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"RightFrontFender_4372_120849.jpg\"},{\"Describe\":\"左后翼子板_划痕_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/LeftRearFender_A775_115110_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"LeftRearFender_A775_115110.jpg\"},{\"Describe\":\"右后翼子板_划痕_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RightRearFender_A347_120234_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"RightRearFender_A347_120234.jpg\"},{\"Describe\":\"发动机舱盖_拆装痕迹\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/EngineCompartmentLid_4404_122559_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"EngineCompartmentLid_4404_122559.jpg\"},{\"Describe\":\"后保险杠_划痕_中\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/RearBumper_A829_115830_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"RearBumper_A829_115830.jpg\"},{\"Describe\":\"发动机缸体_曲后油封漏油_轻\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/EngineBlock_A259_130517_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"EngineBlock_A259_130517.jpg\"},{\"Describe\":\"\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/AppendImage_1AddPhotoSign_130652_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"AppendImage_1AddPhotoSign_130652.jpg\"},{\"Describe\":\"\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/AppendImage_2AddPhotoSign_131617_1.jpg\",\"Type\":\"缺陷\",\"Name\":\"AppendImage_2AddPhotoSign_131617.jpg\"},{\"Describe\":\"OBD报告第1页\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/OBDReport_5439_123621_1.jpg\",\"Type\":\"OBD\",\"Name\":\"OBDReport_5439_123621.jpg\"},{\"Describe\":\"电池检测报告\",\"FilePath\":\"http://tuv-inspection.oss-cn-shanghai.aliyuncs.com/Inspection-Pic/201711/28/20171128111901bHyUvc/BatteryReport_5445_115804_1.jpg\",\"Type\":\"Battery\",\"Name\":\"BatteryReport_5445_115804.jpg\"}],\"DetailInfo\":[{\"GroupName\":\"启动\",\"Items\":[{\"Description\":\"钥匙插取异常\",\"Severity\":\"\",\"Name\":\"启动装置\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"发动机启动\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"发动机运转\"}]},{\"GroupName\":\"路试\",\"Items\":[{\"Description\":\"档位不清\",\"Severity\":\"\",\"Name\":\"排档机构\"}]},{\"GroupName\":\"内饰与电器\",\"Items\":[{\"Description\":\"按键异常\",\"Severity\":\"\",\"Name\":\"方向盘\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"方向盘主气囊\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"前雨刮系统\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"仪表外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"副驾驶主气囊\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"天窗\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"导航系统\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"空调系统\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"前大灯清洗装置\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"主驾驶座椅\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左前安全带\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"副驾驶座椅\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右前安全带\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"后排座椅\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"后排安全带\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左A柱饰板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左B柱饰板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左C柱饰板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右A柱饰板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右B柱饰板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右C柱饰板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"车顶内饰\"}]},{\"GroupName\":\"底盘\",\"Items\":[{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"前防撞横梁\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"备胎框\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"后围板\"},{\"Description\":\"变形\",\"Severity\":\"\",\"Name\":\"后防撞横梁\"}]},{\"GroupName\":\"车身外观\",\"Items\":[{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"车顶\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左前门外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后视镜\"},{\"Description\":\"功能正常\",\"Severity\":\"\",\"Name\":\"左前门窗玻璃控制总成\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后门外观\"},{\"Description\":\"功能正常\",\"Severity\":\"\",\"Name\":\"左后门窗玻璃控制总成\"},{\"Description\":\"油漆修复\",\"Severity\":\"\",\"Name\":\"右后门外观\"},{\"Description\":\"功能正常\",\"Severity\":\"\",\"Name\":\"右后门窗玻璃控制总成\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右前门外观\"},{\"Description\":\"功能正常\",\"Severity\":\"\",\"Name\":\"右前门窗玻璃控制总成\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左前翼子板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右前翼子板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后翼子板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右后翼子板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"前挡风玻璃\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"发动机舱盖\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"前保险杠\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"后挡风玻璃\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"后备箱盖/后门外板\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"后保险杠\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后尾箱雨水槽\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右后尾箱雨水槽\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"备胎\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左前大灯\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右前大灯\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后尾灯\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右后尾灯\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左A柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左B柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左C柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左D柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右D柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右C柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右B柱外观\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右A柱外观\"}]},{\"GroupName\":\"发动机舱\",\"Items\":[{\"Description\":\"变形\",\"Severity\":\"\",\"Name\":\"气门室盖及垫\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"发动机缸体\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"发动机支架及垫\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左翼子板内衬\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右翼子板内衬\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"水箱框架\"}]},{\"GroupName\":\"水泡车检查\",\"Items\":[{\"Description\":\"否\",\"Severity\":\"\",\"Name\":\"座椅滑轨-锈蚀，座椅海绵有泥沙痕迹\"},{\"Description\":\"否\",\"Severity\":\"\",\"Name\":\"脚踏板及方向柱-锈蚀\"},{\"Description\":\"否\",\"Severity\":\"\",\"Name\":\"安全带底部-水渍\"},{\"Description\":\"是\",\"Severity\":\"\",\"Name\":\"保险盒内-泥垢\"},{\"Description\":\"否\",\"Severity\":\"\",\"Name\":\"座椅-拆卸痕迹\"}]},{\"GroupName\":\"车身骨架检查\",\"Items\":[{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左前纵梁\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右前纵梁\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后纵梁\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右后纵梁\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左A柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左B柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左C柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左D柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右D柱\"},{\"Description\":\"褶皱\",\"Severity\":\"\",\"Name\":\"右C柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右B柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右A柱\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左前减震座\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右前减震座\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"左后减震座\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"右后减震座\"},{\"Description\":\"正常\",\"Severity\":\"\",\"Name\":\"前围板\"}]}],\"IsAccidentCar\":\"是\",\"DefectInfo\":[{\"GroupName\":\"启动\",\"Items\":[{\"Description\":\"钥匙插取异常\",\"Severity\":\"\",\"Name\":\"启动装置\"}]},{\"GroupName\":\"路试\",\"Items\":[{\"Description\":\"档位不清\",\"Severity\":\"\",\"Name\":\"排档机构\"}]},{\"GroupName\":\"内饰与电器\",\"Items\":[{\"Description\":\"按键异常\",\"Severity\":\"\",\"Name\":\"方向盘\"}]},{\"GroupName\":\"底盘\",\"Items\":[{\"Description\":\"变形\",\"Severity\":\"\",\"Name\":\"后防撞横梁\"}]},{\"GroupName\":\"车身外观\",\"Items\":[{\"Description\":\"油漆修复\",\"Severity\":\"\",\"Name\":\"右后门外观\"}]},{\"GroupName\":\"发动机舱\",\"Items\":[{\"Description\":\"变形\",\"Severity\":\"\",\"Name\":\"气门室盖及垫\"}]},{\"GroupName\":\"水泡车检查\",\"Items\":[{\"Description\":\"是\",\"Severity\":\"\",\"Name\":\"保险盒内-泥垢\"}]},{\"GroupName\":\"车身骨架检查\",\"Items\":[{\"Description\":\"褶皱\",\"Severity\":\"\",\"Name\":\"右C柱\"}]}],\"CustomerInfo\":[{\"Value\":\"永达远程\",\"Key\":\"客户姓名\"},{\"Value\":\"\",\"Key\":\"联系方式\"},{\"Value\":\"\",\"Key\":\"联系地址\"}]}";

		if (null!=datas && StringUtils.isNotEmpty(datas)) {
			CheckResult checkResult = JsonTools.toObj(datas, CheckResult.class);
			//数据处理
			if (null!=checkResult) {
				if (checkResult.getDetailInfo().size()>0) {
					List<Map<String,String>> bodyFrameTr = new ArrayList<>();
					Map<String,String> bodyFrameTd = null;
					List<Map<String,String>> inEleTr = new ArrayList<>();
					Map<String,String> inEleTd = null;
					for (int i=0;i<checkResult.getDetailInfo().size();i++) {
						if (StringUtils.equals(checkResult.getDetailInfo().get(i).getGroupName(), "车身骨架检查") ||
								StringUtils.equals(checkResult.getDetailInfo().get(i).getGroupName(), "水泡车检查") ||
								StringUtils.equals(checkResult.getDetailInfo().get(i).getGroupName(), "火烧车检查")) {
							for (int j = 0; j < checkResult.getDetailInfo().get(i).getItems().size(); j++) {
								map.put(checkResult.getDetailInfo().get(i).getItems().get(j).getName().toString().replace("，", "").replaceAll(" ", "").replace("-", "").replace(",", "").replace("/", ""),
										checkResult.getDetailInfo().get(i).getItems().get(j).getDescription());
							}
						}

					}
					map.put(checkResult.getCustomerInfo().get(0).getKey(),checkResult.getCustomerInfo().get(0).getValue());
					for (int i = 0; i < checkResult.getCarInfo().size(); i++) {
						map.put(checkResult.getCarInfo().get(i).getKey(),
								checkResult.getCarInfo().get(i).getValue());
					}

					map.put("bodyFrame", bodyFrameTr);
					map.put("inEle", inEleTr);
				}
			}
		}

		// 车辆详情
		logger.info("车辆详情文件路径：path {}", new String[] { getWebClassesPath() });
		request.setDesc(FreeMarkUtil.getHtmlString(map, "carInfo", getWebClassesPath()));
		logger.info("########################" + FreeMarkUtil.getHtmlString(map, "carInfo", getWebClassesPath()));
		inputInputs(request, car);
		inputProps(request, car);
		ItemAddResponse rsp = new ItemAddResponse();
		try {
			rsp = client.execute(request, car.getAccessToken());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		Result result = new Result();
		JSONObject json = JSONObject.fromObject(rsp.getBody());
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
					if(carPicture.getType() == 1 || carPicture.getType() ==  2 || carPicture.getType() == 12){
						String time1  = String.valueOf(System.currentTimeMillis());
						int stop = time1.length();
						String fileName = car.getVin()+"_"+carPicture.getType()+"_"+time1.substring(stop-3, stop)+".jpg";
						String name = download(fileName,carPicture.getPic(),projectUrl);
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
				//req.setImage(new FileItem("C:\\Users\\Administrator\\Desktop\\ggggg93900161260903397.jpg"));

//					RedisUtil.getInstance().hash().d
			}
		}

		result.setItem(rsp.getBody());
		return result;
	}

	@Override
	public Result updateCar(CarEntity car, List<CarPicture> getPicList) {
		city = new City();
		province = new Province();
		city = cityService.getCityById(Integer.parseInt(car.getAttribution()));
		province = provinceService.getProvinceById(city.getProvince());
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemUpdateRequest request = new ItemUpdateRequest();
		request.setCid(TAOBAO_CID);// 叶子类目id
		request.setNum(1L);// 商品数量
//		request.setType("fixed");// 发布类型。可选值:fixed(一口价),auction(拍卖)
		request.setStuffStatus("second");// 新旧程度。可选值：new(新)，second(二手)，unused(闲置)
		request.setHasInvoice(true); // 发票 有
		request.setHasWarranty(false); // 保修 无
		request.setApproveStatus("instock");// instock在仓库中，onsale在售，默认在售
		request.setSubStock(2L); // 商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改
		Double price = Double.valueOf(car.getMarketPrice()) * 10000;
		DecimalFormat df = new DecimalFormat("#.##");
		String marketPrice = df.format(price);
		if (StringUtils.isNotBlank(car.getMarketPrice())) {
			request.setPrice(marketPrice);// 商品价格
		} else {
			request.setPrice("0");// 商品价格
		}
		//设置定金
		request.setSkuProperties("14829532:3292081,14829532:72110507");
		request.setSkuQuantities("1,1");
		request.setSkuPrices("3000,"+ marketPrice);
		request.setSkuOuterIds("3782914410043,3782914410044");
		//设置上传到仓库
		request.setApproveStatus("instock");
		request.setLocationState(province.getName());// 所在省
		request.setLocationCity(city.getName());
		request.setValidThru(7L);// 有效期
		request.setHasWarranty(true);//是否有保修。可选值:true,false;默认值:false(不保修)
		request.setSellPromise(true);//是否承诺退换货服务!虚拟商品无须设置此项!
		request.setIsOffline("3");//是否是线下商品。1：线上商品（默认值）；2：线上或线下商品；3：线下商品。
		request.setLocalityLifeExpirydate("3");// 电子凭证时效为7天
		request.setLocalityLifeMerchant("0:淘宝");
		request.setLocalityLifeOnsaleAutoRefundRatio(100L);
		request.setLocalityLifeRefundRatio(100L);//退款比例，百分比%前的数字,1-100的正整数值
		//设置核销门店
		request.setLocalityLifeObs("1");
		request.setLocalityLifeEticket(";has_pos:1;");
		//设置商品分类
		request.setSellerCids(car.getSellCid());
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
					//设置主图片
					if (carPicture.getType() == 14) {
						request.setPicPath(RedisUtil.getInstance().strings().get(key));
						mainPic.add(RedisUtil.getInstance().strings().get(key));
						aspectList.add(RedisUtil.getInstance().strings().get(key));
						listPic.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}else if(carPicture.getType() == 7 || carPicture.getType() == 8 || carPicture.getType() == 9 ||
							carPicture.getType() == 10 || carPicture.getType() == 11 || carPicture.getType() == 12
							|| carPicture.getType() == 13){
						aspectList.add(RedisUtil.getInstance().strings().get(key));
						listPic.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}else if(carPicture.getType() == 1 || carPicture.getType() == 2 || carPicture.getType() == 6 || carPicture.getType() == 3){
						interiorList.add(RedisUtil.getInstance().strings().get(key));
						listPic.add(RedisUtil.getInstance().strings().get(key));
						RedisUtil.getInstance().keys().del(key);
					}
					//设置车辆外观图片
					/*listPic.add(RedisUtil.getInstance().strings().get(key));
					RedisUtil.getInstance().keys().del(key);*/
				}
			});
		}
		Map<String, Object> map = BeanUtils.obj2Map(car);
		map.put("attribution", city.getName());
		map.put("listPic", listPic);
		map.put("aspectList", aspectList);
		map.put("interiorList", interiorList);
		map.put("mainPic", mainPic);
		map.put("initialLicenceTimeStr", car.getInitialLicenceTimeStr());
		// 获取车辆亮点信息
		if (StringUtils.isNotBlank(car.getModelCode())) {
			map.put("listIcon", listIcon(car.getModelCode()));
		}
		// 获取车辆检测信息
		String vin = car.getVin();
		String datas = "";
		if (null!=datas && StringUtils.isNotEmpty(datas)) {
			CheckResult checkResult = JsonTools.toObj(datas, CheckResult.class);
			//数据处理
			if (null!=checkResult) {
				if (checkResult.getDetailInfo().size()>0) {
					List<Map<String,String>> bodyFrameTr = new ArrayList<>();
					Map<String,String> bodyFrameTd = null;
					List<Map<String,String>> inEleTr = new ArrayList<>();
					Map<String,String> inEleTd = null;
					for (int i=0;i<checkResult.getDetailInfo().size();i++) {
						if (StringUtils.equals(checkResult.getDetailInfo().get(i).getGroupName(), "车身骨架检查") ||
								StringUtils.equals(checkResult.getDetailInfo().get(i).getGroupName(), "水泡车检查") ||
								StringUtils.equals(checkResult.getDetailInfo().get(i).getGroupName(), "火烧车检查")) {
							for (int j = 0; j < checkResult.getDetailInfo().get(i).getItems().size(); j++) {
								map.put(checkResult.getDetailInfo().get(i).getItems().get(j).getName().toString().replace("，", "").replaceAll(" ", "").replace("-", "").replace(",", "").replace("/", ""),
										checkResult.getDetailInfo().get(i).getItems().get(j).getDescription());
							}
						}
					}
					map.put(checkResult.getCustomerInfo().get(0).getKey(),checkResult.getCustomerInfo().get(0).getValue());
					for (int i = 0; i < checkResult.getCarInfo().size(); i++) {
						map.put(checkResult.getCarInfo().get(i).getKey(),
								checkResult.getCarInfo().get(i).getValue());
					}
					map.put("bodyFrame", bodyFrameTr);
					map.put("inEle", inEleTr);
				}
			}
		}
		// 车辆详情
		logger.info("车辆详情文件路径：path {}", new String[] { getWebClassesPath() });
		request.setDesc(FreeMarkUtil.getHtmlString(map, "carInfo", getWebClassesPath()));
		logger.info("########################" + FreeMarkUtil.getHtmlString(map, "carInfo", getWebClassesPath()));
		inputUpdateInputs(request, car);
		inputUpdateProps(request, car);
		ItemUpdateResponse rsp = new ItemUpdateResponse();
		try {
			rsp = client.execute(request, car.getAccessToken());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		Result result = new Result();
		JSONObject json = JSONObject.fromObject(rsp.getBody());
		//上传成功后向商品中添加4张非主图图片
		if(!json.isEmpty() && json.get("item_update_response") != null) {
			json = (JSONObject) json.get("item_update_response");
			if(!json.isEmpty() && json.get("item") != null) {
				//json = (JSONObject) json.get("item");
//				String numIid = json.getString("num_iid");
				String numIid = car.getTaoBaoId();
				result.setDatas(numIid);
				//先删除原图
				ItemImgDeleteRequest reqDelete = new ItemImgDeleteRequest();
				reqDelete.setNumIid(Long.parseLong(car.getTaoBaoId()));
				ItemImgUploadRequest req = new ItemImgUploadRequest();
				req.setNumIid(Long.parseLong(numIid));
				//req.setPosition(2L);
				for(CarPicture carPicture : getPicList){
					if(carPicture.getType() == 1 || carPicture.getType() ==  2 || carPicture.getType() == 12){
						String time1  = String.valueOf(System.currentTimeMillis());
						int stop = time1.length();
						String fileName = car.getVin()+"_"+carPicture.getType()+"_"+time1.substring(stop-3, stop)+".jpg";
						String name = download(fileName,carPicture.getPic(),projectUrl);
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

	private static ItemAddRequest request;
	private static Map<String, Object> map;
	@Override
	public Result addMarketCar(CarEntity car, List<CarPicture> getPicList) {
		city = new City();
		province = new Province();
		city = cityService.getCityById(Integer.parseInt(car.getAttribution()));
		province = provinceService.getProvinceById(city.getProvince());
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		request = new ItemAddRequest();
		request.setCid(TAOBAO_CID);// 叶子类目id
		request.setNum(1L);// 商品数量
		request.setAuctionPoint(1L);
		request.setType("fixed");// 发布类型。可选值:fixed(一口价),auction(拍卖)
		request.setStuffStatus("second");// 新旧程度。可选值：new(新)，second(二手)，unused(闲置)
		request.setHasInvoice(true); // 发票 有
		request.setHasWarranty(false); // 保修 无
		request.setApproveStatus("instock");// instock在仓库中，onsale在售，默认在售
		request.setSubStock(2L); // 商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改
		Double price = Double.valueOf(car.getMarketPrice()) * 10000;
		
		DecimalFormat df = new DecimalFormat("#.##");
		String marketPrice = df.format(price);
		if (StringUtils.isNotBlank(car.getMarketPrice())) {
			request.setPrice(marketPrice);// 商品价格
		} else {
			request.setPrice("0");// 商品价格
		}
		//设置定金
		request.setSkuProperties("14829532:3292081,14829532:72110507");
		request.setSkuQuantities("1,1");
		//订金
		String subscription = null;
		if(Double.valueOf(car.getMarketPrice())<=10.00) {
			subscription="300,";
		}else if(Double.valueOf(car.getMarketPrice())<=20&&Double.valueOf(car.getMarketPrice())>10) {
			subscription="500,";
		}else if(Double.valueOf(car.getMarketPrice())<=30&&Double.valueOf(car.getMarketPrice())>20) {
			subscription="1000,";
		}else {
			subscription="1500,";
		}
		
		request.setSkuPrices(subscription+ marketPrice);
		request.setSkuOuterIds("3782914410043,3782914410044");
		//设置上传到仓库
		request.setApproveStatus("instock");
		request.setLocationState(province.getName());// 所在省
		request.setLocationCity(city.getName());
		request.setValidThru(7L);// 有效期
		//request.setHasWarranty(true);//是否有保修。可选值:true,false;默认值:false(不保修)
		request.setSellPromise(true);//是否承诺退换货服务!虚拟商品无须设置此项!
		request.setIsOffline("3");//是否是线下商品。1：线上商品（默认值）；2：线上或线下商品；3：线下商品。
		request.setLocalityLifeExpirydate("7");// 电子凭证时效为7天
		request.setLocalityLifeMerchant("0:淘宝");
		request.setLocalityLifeOnsaleAutoRefundRatio(100L);
		request.setLocalityLifeRefundRatio(100L);//退款比例，百分比%前的数字,1-100的正整数值
		//设置核销门店
		request.setLocalityLifeChooseLogis("1");//使用邮寄
		request.setFreightPayer("buyer");//买家运费承担
		request.setPostFee("999");
		request.setExpressFee("999");
//		request.setLocalityLifeObs("1");
		request.setLocalityLifeEticket(";has_pos:1;");

		//设置商品分类
		//String sell_cid = itemClassify(car);
		//request.setSellerCids("1350951592,20523159");
		//dealSellCids(request ,car);
		//request.setSellerCids(car.getSellCid());

		// taobao_car_pic_-1_00199
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

		map = BeanUtils.obj2Map(car);
		map.put("attribution", city.getName());
		map.put("listPic", listPic);
		/*map.put("aspectList", aspectList);
		map.put("interiorList", interiorList);*/
		map.put("mainPic", mainPic);
		map.put("initialLicenceTimeStr", car.getInitialLicenceTimeStr());
		// 获取车辆亮点信息
		if (StringUtils.isNotBlank(car.getModelCode())) {
			map.put("listIcon", listIcon(car.getModelCode()));
		}


		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String marketName = prop.getProperty("marketName" + car.getMarket());
		String check = prop.getProperty("check" + car.getMarket());
		String ftlName = prop.getProperty("ftlName" + car.getMarket());
		String marketPhone = prop.getProperty("marketPhone" + car.getMarket());

		String unit = "";
		if (car.getModelName() != null && car.getModelName().contains("L")) {
			unit = car.getModelName().substring(car.getModelName().indexOf("L") - 3, car.getModelName().indexOf("L") + 1);
		} else if (car.getModelName() != null && car.getModelName().contains("T")) {
			unit = car.getModelName().substring(car.getModelName().indexOf("T") - 3, car.getModelName().indexOf("T") + 1);
		}
		String title = "二手车 " + car.getBrandName().trim() + car.getModelYear() + car.getSeriesName() + unit + marketName + marketPhone;
		if (title.length() > 30) {
			title = title.substring(0, title.indexOf(marketName.substring(0, 1)));
		}
		request.setTitle(title);
		// 获取车辆检测信息
		if ("0".equals(check)) {
			checkDatas(car, result);
			logger.info("车辆详情文件路径：path {}", new String[]{getWebClassesPath()});
			request.setDesc(FreeMarkUtil.getHtmlString(map, ftlName, getWebClassesPath()));
		} else if ("1".equals(check)) {
			logger.info("车辆详情文件路径：path {}", new String[]{getWebClassesPath()});
			request.setDesc(FreeMarkUtil.getHtmlString(map, ftlName, getWebClassesPath()));
		}

		/*String datas = carService.getDatas(vin);
		if(datas == null){
			datas = carService.getDatas("LSGAR5AL3HH226406");
		}*/

		// 车辆详情
//		logger.info("车辆详情文件路径：path {}", new String[] { getWebClassesPath() });
//		request.setDesc(FreeMarkUtil.getHtmlString(map, "ylcarInfo", getWebClassesPath()));
		//logger.info("########################" + FreeMarkUtil.getHtmlString(map, "carInfo", getWebClassesPath()));
		//request.setDesc("全车通测试用");
		inputInputs(request, car);
		inputProps(request, car);
		//0265769:396242950;136152291:3919643;136266573:33798;152026012:57898216;185740819:1690915823;122450261:3296643;128716001:3297117;14829532:3292081;14829532:72110507;
		/*System.out.println("InputPids===="+request.getInputPids());
		System.out.println("Props===="+request.getProps()); */
		//request.setProps("10265769:396242950;0265769:396242950;136152291:3919643;136266573:33798;152026012:57898216;185740819:1690915823;122450261:3296643;128716001:3297117;14829532:3292081;14829532:72110507;");
		System.out.println("Packageid====================="+request.getLocalityLifePackageid());
		ItemAddResponse rsp = new ItemAddResponse();
		try {
			rsp = client.execute(request, car.getAccessToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("淘宝返回"+rsp.getMsg()+"--------"+rsp.getSubMsg()+"----------"+rsp.getBody());
		JSONObject json = JSONObject.fromObject(rsp.getBody());
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
				//req.setImage(new FileItem("C:\\Users\\Administrator\\Desktop\\ggggg93900161260903397.jpg"));

				//RedisUtil.getInstance().hash().d
			}
		}

		result.setItem(rsp.getBody());
		return result;
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
			JSONObject jo = JSONObject.fromObject(r.getItem());
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
		Iterator<?> it = params.keys();
		// 遍历json数据，添加到Map对象
		List<String> listKey = new ArrayList<String>();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			listKey.add(key);
		}
		Collections.sort(listKey);
		result = daSouCheService.getAllService(2, params, listKey);
		return result;
	}

	private void inputProps(ItemAddRequest request, CarEntity car) {
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
			taobaoItemValues = taobaoItemValuesService.getTaobaoItemValues(taobaoItemValues);
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
	private void inputUpdateProps(ItemUpdateRequest request, CarEntity car) {
		String brand = car.getBrandName();
		String series = car.getSeriesName();
		String model = car.getModelName();
		String title = brand;
		if (series.contains(brand)) {
			series = series.replace(brand, "");
		}
		title += (" " + series);
		if (model.contains(series)) {
			model = model.replace(series, "");
		}
		if (model.contains(brand)) {
			model = model.replace(brand, "");
		}
//		title += (" " + model + " 汽车 阿里二手车");
		title += (model + " 二手车 无锡阿里二手车 无锡二手车");

		/*if (title.length() < 23) {
			title += " 二手车 汽车..";// ..占一个字符
		} else if (title.length() > 29) {
			title = title.substring(0, 29) + "..";
		} */// 两个空格占一个字符，23+1+6=30极限长度，已测
		logger.debug("assembly title done..,title:" + title);
		//request.setTitle(title);// 宝贝标题。不能超过30字符
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
			taobaoItemValues = taobaoItemValuesService.getTaobaoItemValues(taobaoItemValues);
			props.append("10265769:" + taobaoItemValues.getVid() + ";");
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
	private static void inputUpdateInputs(ItemUpdateRequest request, CarEntity car) {
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

	/**
	 * 添加图片
	 */
	public Result addImg(CarEntity car , List<CarPicture> getPicList, String path, String code) {
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
			json = JSONObject.fromObject(rsp.getBody());
			logger.info("上传图片返回参数：{}", rsp.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	// 获取当前文件路径
	public static String getWebClassesPath() {
		try {
			String path = TaoBaoServiceImpl.class.getResource("").getPath();
			// getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
			// //包含类名
			return path;
		} catch (Exception e) {
		}
		return "";
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

//	public static void main(String[] args) {
//		String path = "C:/Users/Administrator/Desktop/1.jpg";
//		path = path.substring(0, path.lastIndexOf("/"));
//		System.out.println(path);
		// File f = new File("C:/Users/Administrator/Desktop/1.jpg");
		// uploadFileAndCreateThumbnail("C:\\Users\\Administrator\\Desktop\\1.jpg",
		// "1.jpg", f);
//	}

	public Result deListIngCar(Long numIid, String sessionKey) {
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemUpdateDelistingRequest req = new ItemUpdateDelistingRequest();
		req.setNumIid(numIid);
		try {
			ItemUpdateDelistingResponse rsp = client.execute(req, sessionKey);
			JSONObject json = JSONObject.fromObject(rsp.getBody());
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

	public Result deleteCar(Long numId, String sessionKey){
		Result result = new Result();
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		ItemDeleteRequest req = new ItemDeleteRequest();
		req.setNumIid(numId);
		try{
			ItemDeleteResponse rsp = client.execute(req, sessionKey);
			JSONObject json = JSONObject.fromObject(rsp.getBody());
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
    public Result updatePrice(String numIid, String price ,String sessionKey) {
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
			JSONObject json = JSONObject.fromObject(rsp.getBody());
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

	/**
	 * 获取维真检测信息
	 *
	 * @param car
	 * @param
	 * @param
	 * @param
	 */
	public void checkDatas(CarEntity car, Result result) {
		String vin = car.getVin();
		Properties prop = new Properties();

		String wzjs = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String time = formatter.format(new Date());
		Map checkMap = new HashMap();
		try {
			prop.load(this.getClass().getResourceAsStream("/dasouche.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String wzmcname = prop.getProperty("wzmcname");
		String wzurl = prop.getProperty("wzurl");
		String wzappid = prop.getProperty("wzappid");
		String wzappkey = prop.getProperty("wzappkey");

		StringBuffer md5 = new StringBuffer();
		String sign = md5.append(wzmcname).append(vin).append(wzappid).append(wzappkey).append(time).toString();
		sign = MD5Util.MD5(String.valueOf(md5)).toLowerCase();
		//String sign = FileUtils.sign(vin, time, wzappid, wzmcname, wzappkey);
		String url = wzurl + "?mcname=" + wzmcname + "&vin=" + vin + "&sign=" + sign + "&key=" + wzappkey +
				"&appid=" + wzappid;
		try {
			wzjs = HttpClientUtil.get(url, "utf-8", null);
			System.out.println("维真返回参数" + wzjs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		com.alibaba.fastjson.JSONObject js = com.alibaba.fastjson.JSONObject.parseObject(wzjs);
		if ("0".equals(js.get("code"))) {
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
			map.put("检测时间", js2.get("created").toString());
			map.put("客户名称", js2.get("phone").toString());
			if (js2.get("phone") != null || !"".equals(js2.get("phone"))) {
				Object packageId = taobaoCarService.selectPackageid(js2.get("phone").toString());
				if (packageId != null) {
					request.setLocalityLifeVersion("1");
					request.setLocalityLifePackageid(packageId.toString());
				}
			}


		} else {
			result.setDatas("该车辆没有维真检测信息！");
		}
	}

	//	public static void main1(String[] args) {
//		 String APP_KEY = "24812065";
//	    String SECRET = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";
//	    String API_URL = "http://gw.api.taobao.com/router/rest";
//		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
//		ItempropsGetRequest req = new ItempropsGetRequest();
//		req.setFields("pid,name,must,multi,prop_values");
//		req.setCid(50050566L);
//
//		ItempropsGetResponse rsp = null;
//		try {
//			rsp = client.execute(req);
//		} catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(rsp.getBody());
//
//	}
	public static void main(String[] args) throws ClientProtocolException, IOException {
		/*String cookie ="miid=936631312420705576; thw=cn; isg=BHFxLB_DjtpfiiJrFJGmO4vBg_7L9ua4KUTpZVOGbThXepHMm671oB-YmM65qX0I; cna=HNPuE0w+xxkCAd3As3pVsH+R; hng=CN%7Czh-CN%7CCNY%7C156; t=74985f65696795ae5bea1cb2337dfcc5; _cc_=UIHiLt3xSw%3D%3D; tg=0; UM_distinctid=1659e7e0d7f449-0c76a73f20b8af8-143f7040-15f900-1659e7e0d80ef; um=65F7F3A2F63DF02056E1FBC491C68D1DFBBDE9EBC70A0515AC7E6A3C219EB8199288FFA41F1FEEB0CD43AD3E795C914CA19E165440C03613E995DB6774CABA30; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; ali_ab=60.12.250.54.1536224706622.3; mt=ci=0_1&np=; _m_h5_tk=4d6cf0fe442094bdd862a6a561620f7f_1541064344488; _m_h5_tk_enc=9819b715ae3455ba57f6f4117251d2c1; cookie2=7c7bccda3b3cba10fe07a333753378aa; _tb_token_=5855e3e813573; whl=-1%260%260%261541054217965; JSESSIONID=1CE155488904FA45988BFD2B8700B2AB; v=0; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&cookie21=W5iHLLyFfXVRCJf5l6bv6g%3D%3D&cookie15=UIHiLt3xD8xYTw%3D%3D&existShop=true&pas=0&cookie14=UoTYN4HMXVDxTQ%3D%3D&tag=8&lng=zh_CN; skt=fc34a89d90e01fba; csg=9a71f2f6; uc3=vt3=F8dByRjNVHS09QZZiow%3D&id2=VyyUyYyZZc600A%3D%3D&nk2=F5RGNwppVl5rbTg%3D&lg2=UIHiLt3xD8xYTw%3D%3D; existShop=MTU0MTA1NDQ3OQ%3D%3D; tracknick=tb312844838; lgc=tb312844838; dnk=tb312844838; unb=4052462357; sg=874; _l_g_=Ug%3D%3D; cookie1=W5jGlh8gjHrU%2F%2BHlyxyoY%2BlUPluTLnUci6q8eZMosZ8%3D; _nk_=tb312844838; cookie17=VyyUyYyZZc600A%3D%3D";
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
		}*/
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "24812065";
		String secret = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		ItemAddRequest req = new ItemAddRequest();
		req.setInputStr("LVXDAJBAXGS023369,2013-01,888888万公里,0.00万元");
		req.setInputPids("143410077,20207674,30259,148656181");
		req.setProps("10265769:396242950;136152291:38915;136326248:6199399;136262505:30694075;136280418:636768829;122450261:3296643;128716001:3297117;14829532:3292081;14829532:72110507;");
		req.setSkuProperties("14829532:3292081,14829532:72110507");
		req.setSkuQuantities("1,1");
		req.setSkuPrices("2000,120000");
		req.setSkuOuterIds("3782914410043,3782914410044");
		req.setLocationState("广西");
		req.setLocationCity("玉林");
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
			ItemAddResponse response = client.execute(req, "6202827e9c5524d604ed4719164954c495ceg1401295ed74227823786");
			System.out.println(response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main1(String[] args) {
		/*String time1  = String.valueOf(System.currentTimeMillis());
		int stop = time1.length();
		System.out.println(time1.substring(stop-3, stop));*/
		String wzjs = null;
		String vin = "LFMBEK4B4C0081801";
		String wzmcname = "qct003";
		String wzurl = "http://wx.renrenyiche.com/api/Openapi/query";
		String wzappid = "qct003";
		String wzappkey = "76ddbee6wzdcbd7bf3fe3fa08byc71c0e74b";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String time = formatter.format(new Date());
		StringBuffer md5 = new StringBuffer();
		String sign = md5.append(wzmcname).append(vin).append(wzappid).append(wzappkey).append(time).toString();
		sign = MD5Util.MD5(String.valueOf(md5)).toLowerCase();
		//String sign = FileUtils.sign(vin, time, wzappid, wzmcname, wzappkey);
		String url = wzurl + "?mcname=" + wzmcname + "&vin=" + vin + "&sign=" + sign + "&key=" + wzappkey +
				"&appid=" + wzappid;
		try {
			wzjs = HttpClientUtil.get(url, "utf-8", null);
			System.out.println("维真返回参数"+wzjs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*private void dealSellCids(ItemAddRequest request, CarEntity car) {
			//无法识别国别先用1396000476
			String sellCids = "1396000476";
			//价格
			if(Double.valueOf(car.getMarketPrice())<=5) {
				sellCids+=","+Constant.price1;
			}else if(Double.valueOf(car.getMarketPrice())<=10&&Double.valueOf(car.getMarketPrice())>5) {
				sellCids+=","+Constant.price2;
			}else if(Double.valueOf(car.getMarketPrice())<=15&&Double.valueOf(car.getMarketPrice())>10) {
				sellCids+=","+Constant.price3;
			}else if(Double.valueOf(car.getMarketPrice())<=20&&Double.valueOf(car.getMarketPrice())>15) {
				sellCids+=","+Constant.price4;
			}else {
				sellCids+=","+Constant.price5;
			}
			//车型
			if(car.getLevel().contains("SUV")) {
				sellCids+=","+Constant.SUV;
			}else {
				sellCids+=","+Constant.jiaoche;
			}
			//排量
			if(Double.valueOf(car.getUnitl())<=1.0) {
				sellCids+=","+Constant.pailiang1;
			}else if(Double.valueOf(car.getUnitl())<=1.6&&Double.valueOf(car.getUnitl())>1.1) {
				sellCids+=","+Constant.pailiang2;
			}else if(Double.valueOf(car.getUnitl())<=2.0&&Double.valueOf(car.getUnitl())>1.6) {
				sellCids+=","+Constant.pailiang3;
			}else if(Double.valueOf(car.getUnitl())<=2.5&&Double.valueOf(car.getUnitl())>2.0) {
				sellCids+=","+Constant.pailiang4;
			}else if(Double.valueOf(car.getUnitl())<=3.0&&Double.valueOf(car.getUnitl())>2.5) {
				sellCids+=","+Constant.pailiang5;
			}else {
				sellCids+=","+Constant.pailiang6;
			}
			//座位数
			if(car.getSeatNumber()==2) {
				sellCids+=","+Constant.seat2;
			}else if(car.getSeatNumber()==4) {
				sellCids+=","+Constant.seat4;
			}else if(car.getSeatNumber()==5) {
				sellCids+=","+Constant.seat5;
			}else if(car.getSeatNumber()==6) {
				sellCids+=","+Constant.seat6;
			}else if(car.getSeatNumber()==7) {
				sellCids+=","+Constant.seat7;
			}else {
				sellCids+=","+Constant.seat8;
			}
			//能源分类
			if(car.getFuelForm().equals("汽油")) {
				sellCids+=","+Constant.oil1;
			}else if(car.getFuelForm().equals("柴油")) {
				sellCids+=","+Constant.oil2;
			}else if(car.getFuelForm().equals("电动")) {
				sellCids+=","+Constant.oil3;
			}else if(car.getFuelForm().equals("油电混合")) {
				sellCids+=","+Constant.oil4;
			}else {
				sellCids+=","+Constant.oil5;
			}
			
		}*/
	
}
