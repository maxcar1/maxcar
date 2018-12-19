package com.maxcar.base.impl;

import com.maxcar.base.dao.TaobaoCarMapper;
import com.maxcar.base.pojo.taobao.TaobaoCar;
import com.maxcar.base.pojo.taobao.TaobaoCarExample;
import com.maxcar.base.service.TaobaoCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("taobaoCarService")
public class TaobaoCarServiceImpl implements TaobaoCarService {
	@Resource
	private TaobaoCarMapper taobaoCarMapper;

	public int deleteByPrimaryKey(Integer id) {
		return taobaoCarMapper.deleteByPrimaryKey(id);
	}
	public int insert(TaobaoCar record) {
		return taobaoCarMapper.insert(record);
	}
	public int insertSelective(TaobaoCar record) {
		return taobaoCarMapper.insertSelective(record);
	}
	public TaobaoCar selectByPrimaryKey(Integer id) {
		return taobaoCarMapper.selectByPrimaryKey(id);
	}
	public int updateByPrimaryKeySelective(TaobaoCar record) {
		return taobaoCarMapper.updateByPrimaryKeySelective(record);
	}
	public int updateByPrimaryKey(TaobaoCar record) {
		return taobaoCarMapper.updateByPrimaryKey(record);
	}
	public  List<TaobaoCar> getTaobaoCar(TaobaoCar car) {
		TaobaoCarExample taobaoCarExample=new TaobaoCarExample();
		TaobaoCarExample.Criteria taobaoCarExample1=taobaoCarExample.createCriteria();
		if (null !=car.getBrandPid()){
			taobaoCarExample1.andBrandPidEqualTo(car.getBrandPid());
		}
		if (null !=car.getBrandName() && car.getBrandName() !=""){
			taobaoCarExample1.andBrandNameEqualTo(car.getBrandName());
		}
		if (null !=car.getSeriesName() && car.getSeriesName() !=""){
			taobaoCarExample1.andSeriesNameLike("%"+car.getSeriesName()+"%");
		}
		if (null !=car.getYearName() && car.getYearName()!=""){
			taobaoCarExample1.andYearNameLike("%"+car.getYearName()+"%");
		}
		return  taobaoCarMapper.selectByExample(taobaoCarExample);
	}
//	@Override
//	public Object selectPackageid(String string) {
//		return  dao.findForObject("com.maxcar.service.TaobaoCarMapper.selectPackageid", string);
//	}

}
