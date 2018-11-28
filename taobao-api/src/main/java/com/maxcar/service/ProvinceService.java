package com.maxcar.service;

import java.util.List;

import com.maxcar.entity.Province;
/**
 * 省接口
* @ClassName: ProvinceService 
* @author huangxu 
* @date 2018年1月14日 上午11:32:01 
*
 */
public interface ProvinceService {
	List<Province> getProvince ();
	Province getProvinceById (Integer id);
}
