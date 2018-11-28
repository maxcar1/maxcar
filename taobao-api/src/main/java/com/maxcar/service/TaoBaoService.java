package com.maxcar.service;
/**
 * 处理淘宝的数据
* @ClassName: TaoBaoService 
* @author huangxu 
* @date 2018年3月20日 下午3:00:45 
*
 */

import java.util.List;

import com.maxcar.core.utils.Result;
import com.maxcar.entity.CarEntity;
import com.maxcar.entity.CarPicture;
import com.maxcar.entity.TaoBaoApiEntity;

public interface TaoBaoService {
	//https://oauth.taobao.com/authorize?response_type=code&client_id=24812065&redirect_uri=test.maxcar.com.cn/market-api/api/cars?&state=1212&view=web
	/**
	 * 换取access_token
	 * @return
	 */
	Result getOpenOauth(String code);
	/**
	 * 加车
	 * @param car
	 * @param getPicList
	 * @return
	 */
	Result addCar(CarEntity car,List<CarPicture> getPicList);
	/**
	 * 加图片
	 * @param getPicList
	 * @param path
	 * @param code
	 * @return
	 */
	Result addImg(CarEntity car , List<CarPicture> getPicList,String path,String code);
	/**
	 * 商品下架
	 * @param numIid
	 * @param sessionKey
	 * @return
	 */
	Result deListIngCar(Long numIid,String sessionKey);

	/**
	 * 商品删除
	 * @param numId
	 * @param sessionKey
	 * @return
	 */
	Result deleteCar(Long numId,String sessionKey);

	/**
	 * 更新价格
	 * @param numIid
	 * @param price
	 * @param sessionKey
	 * @return
	 */
	Result updatePrice(String numIid , String price , String sessionKey);

	Result updateCar(CarEntity car,List<CarPicture> getPicList);

    Result addMarketCar(CarEntity carEntity, List<CarPicture> getPicList);
}
