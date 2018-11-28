package com.maxcar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.maxcar.core.utils.Result;
import com.maxcar.dao.DaoSupport;
import com.maxcar.entity.TaobaoCar;
import com.maxcar.service.TaobaoCarService;
@Service("taobaoCarService")
public class TaobaoCarServiceImpl implements TaobaoCarService {
	@Resource
	private DaoSupport dao;
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}
	public int insert(TaobaoCar record) {
		return 0;
	}
	public int insertSelective(TaobaoCar record) {
		return 0;
	}
	public TaobaoCar selectByPrimaryKey(Integer id) {
		return null;
	}
	public int updateByPrimaryKeySelective(TaobaoCar record) {
		return 0;
	}
	public int updateByPrimaryKey(TaobaoCar record) {
		return 0;
	}
	public  List<TaobaoCar> getTaobaoCar(TaobaoCar car) {
		return  (List<TaobaoCar>) dao.findForList("com.maxcar.service.TaobaoCarMapper.getTaobaoCar", car);
	}
	@Override
	public Object selectPackageid(String string) {
		return  dao.findForObject("com.maxcar.service.TaobaoCarMapper.selectPackageid", string);
	}

}
