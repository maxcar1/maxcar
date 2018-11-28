package com.maxcar.service;

import java.util.List;

import com.maxcar.entity.City;


/**
 * 城市信息接口
 * 
 * @ClassName: CityService
 * @author huangxu
 * @date 2018年1月14日 上午11:32:13
 *
 */
public interface CityService {
	List<City> getCity(Integer province);

	City getCityById(Integer id);

	/**
	 * 获取所有生效市场的城市名称和id
	 * 
	 * @return
	 * @throws Exception
	 */

	List<City> getAllMarketCity();
}
