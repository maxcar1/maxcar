package com.maxcar.user.service.impl;

import com.maxcar.user.dao.MarketMapMapper;
import com.maxcar.user.entity.MarketMap;
import com.maxcar.user.entity.MarketMapExample;
import com.maxcar.user.service.MarketMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("marketMapService")
public class MarketMapServiceImpl implements MarketMapService {

    @Autowired
    private MarketMapMapper marketMapMapper;

    @Override
    public List<MarketMap> getMarketMapList(MarketMap marketMap) {
        MarketMapExample marketMapExample = new MarketMapExample();
        MarketMapExample.Criteria criteria = marketMapExample.createCriteria();
        if(marketMap.getMarketId() != null && marketMap.getMarketId() != ""){
            criteria.andMarketIdEqualTo(marketMap.getMarketId());
        }
        List<MarketMap> list = marketMapMapper.selectByExample(marketMapExample);
        return list;
    }
}
