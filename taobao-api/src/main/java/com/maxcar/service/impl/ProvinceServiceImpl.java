package com.maxcar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.maxcar.dao.DaoSupport;
import com.maxcar.entity.Province;
import com.maxcar.service.ProvinceService;


@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService{
	
	@Resource(name = "daoSupport")
    private DaoSupport dao;
	
	public List<Province> getProvince() {
		return (List<Province>) dao.findForList("com.maxcar.base.mapper.ProvinceMapper.getProvince", null);
	}
	public Province getProvinceById(Integer id) {
		return (Province) dao.findForObject("com.maxcar.base.mapper.ProvinceMapper.selectByPrimaryKey", id);
	}

}
