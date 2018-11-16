package com.maxcar.user.service.impl;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.SearchCarMapper;
import com.maxcar.user.entity.SearchCar;
import com.maxcar.user.entity.SearchCarExample;
import com.maxcar.user.service.SearchCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchCarService")
public class SearchCarServiceImpl implements SearchCarService {

    @Autowired
    private SearchCarMapper searchCarMapper;

    @Override
    public InterfaceResult saveOrUpdateSearchCar(SearchCar searchCar) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        String customerId = searchCar.getId();
        //新增
        if(StringUtils.isBlank(customerId)){
            SearchCarExample example = new SearchCarExample();
            SearchCarExample.Criteria criteria = example.createCriteria();
            /*criteria.andPhoneEqualTo(searchCar.getPhone());
            List<SearchCar> searchCarList = searchCarMapper.selectByExample(example);
            if(searchCarList != null && searchCarList.size()>0){
                interfaceResult.InterfaceResult600("手机号码已存在");
                return interfaceResult;
            }*/
            searchCar.setId(UuidUtils.generateIdentifier());
            count = searchCarMapper.insertSelective(searchCar);
        }else{
            //修改
            count = searchCarMapper.updateByPrimaryKeySelective(searchCar);
        }

        return interfaceResult;
    }
}
