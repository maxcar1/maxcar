package com.maxcar.service.impl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.maxcar.dao.DaoSupport;
import com.maxcar.entity.TaobaoItemValues;
import com.maxcar.entity.TaobaoItemValuesKey;
import com.maxcar.service.TaobaoItemValuesService;
@Service("taobaoItemValuesService")
public class TaobaoItemValuesServiceImpl implements TaobaoItemValuesService{
	@Resource
	private DaoSupport dao;
	
	public int deleteByPrimaryKey(TaobaoItemValuesKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public TaobaoItemValues selectByPrimaryKey(TaobaoItemValuesKey key) {
		return null;
	}

	public int updateByPrimaryKeySelective(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public TaobaoItemValues getTaobaoItemValues(TaobaoItemValues record) {
		return (TaobaoItemValues) dao.findForObject("com.maxcar.service.TaobaoItemValuesMapper.getTaobaoItemValues", record);
	}

}
