package com.maxcar.base.impl;

import com.maxcar.base.dao.TaobaoItemValuesMapper;
import com.maxcar.base.pojo.taobao.TaobaoItemValues;
import com.maxcar.base.pojo.taobao.TaobaoItemValuesExample;
import com.maxcar.base.pojo.taobao.TaobaoItemValuesKey;
import com.maxcar.base.service.TaobaoItemValuesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("taobaoItemValuesService")
public class TaobaoItemValuesServiceImpl implements TaobaoItemValuesService {
	@Resource
	private TaobaoItemValuesMapper taobaoItemValuesMapper;
	
	public int deleteByPrimaryKey(TaobaoItemValuesKey key) {
		// TODO Auto-generated method stub
		return taobaoItemValuesMapper.deleteByPrimaryKey(key);
	}

	public int insert(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return taobaoItemValuesMapper.insert(record);
	}

	public int insertSelective(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return taobaoItemValuesMapper.insertSelective(record);
	}

	public TaobaoItemValues selectByPrimaryKey(TaobaoItemValuesKey key) {
		return taobaoItemValuesMapper.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return taobaoItemValuesMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TaobaoItemValues record) {
		// TODO Auto-generated method stub
		return taobaoItemValuesMapper.updateByPrimaryKey(record);
	}

	public TaobaoItemValues getTaobaoItemValues(TaobaoItemValues record) {
		TaobaoItemValuesExample taobaoItemValuesExample=new TaobaoItemValuesExample();
		TaobaoItemValuesExample.Criteria criteria=taobaoItemValuesExample.createCriteria();
		if (null !=record.getPid() && record.getPid() !=""){
			criteria.andPidEqualTo(record.getPid());
		}
		if (null !=record.getVid() && record.getVid() !=""){
			criteria.andVidEqualTo(record.getVid());
		}
		if (null !=record.getName() && record.getName()!=""){
			criteria.andNameLike("%"+record.getName()+"%");
		}
		return taobaoItemValuesMapper.selectByExample(taobaoItemValuesExample).get(0);
	}

}
