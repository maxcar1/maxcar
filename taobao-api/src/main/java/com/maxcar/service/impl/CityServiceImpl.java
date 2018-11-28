package com.maxcar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.maxcar.dao.DaoSupport;
import com.maxcar.entity.City;
import com.maxcar.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService {
	
	@Resource(name = "daoSupport")
    private DaoSupport dao;
	
	public List<City> getCity(Integer province) {
		return (List<City>) dao.findForList("com.maxcar.base.mapper.CityMapper.getCity", province);
	}

	public City getCityById(Integer id) {
		return (City) dao.findForObject("com.maxcar.base.mapper.CityMapper.selectByPrimaryKey", id);
	}

	public List<City> getAllMarketCity() {
		return (List<City>) dao.findForList("com.maxcar.base.mapper.CityMapper.getAllMarketCity", null);
	}

}
