package com.maxcar.market.service.impl;

import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.ShoppingGuideDetailMapper;
import com.maxcar.market.pojo.ShoppingGuideDetail;
import com.maxcar.market.pojo.ShoppingGuideDetailExample;
import com.maxcar.market.service.ShoppingGuideDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("shoppingGuideDetailService")
public class ShoppingGuideDetailServiceImpl implements ShoppingGuideDetailService {

    @Autowired
    private ShoppingGuideDetailMapper shoppingGuideDetailMapper;


    @Override
    public boolean addOrUpdateShoppingGuideDetail(ShoppingGuideDetail shoppingGuideDetail) {
        int count = 0;
        String marketId = shoppingGuideDetail.getMarketId();
        if(StringUtils.isNotBlank(marketId)){
            ShoppingGuideDetailExample shoppingGuideDetailExample =  new ShoppingGuideDetailExample();
            shoppingGuideDetailExample.createCriteria().andMarketIdEqualTo(marketId).andIsvalidEqualTo(1);
            //判断该市场是否存在导购信息
            List<ShoppingGuideDetail> shoppingGuideDetailServiceList = shoppingGuideDetailMapper.selectByExample(shoppingGuideDetailExample);
            //修改
            if(shoppingGuideDetailServiceList != null && shoppingGuideDetailServiceList.size()>0){
                for(ShoppingGuideDetail s:shoppingGuideDetailServiceList){
                    shoppingGuideDetail.setId(s.getId());
                    count = shoppingGuideDetailMapper.updateByPrimaryKeySelective(shoppingGuideDetail);
                    if(count>0){
                        return true;
                    }
                }
            }else{
                //新增
                shoppingGuideDetail.setId(UuidUtils.generateIdentifier());
                count = shoppingGuideDetailMapper.insertSelective(shoppingGuideDetail);
            }
        }
        return false;
    }


    @Override
    public List<ShoppingGuideDetail> getShoppingGuideDetail(ShoppingGuideDetail shoppingGuideDetail) {
        ShoppingGuideDetailExample example = new ShoppingGuideDetailExample();
        ShoppingGuideDetailExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(shoppingGuideDetail.getMarketId())){
            criteria.andMarketIdEqualTo(shoppingGuideDetail.getMarketId());
        }
        List<ShoppingGuideDetail> list = shoppingGuideDetailMapper.selectByExample(example);
        return list;
    }
}
