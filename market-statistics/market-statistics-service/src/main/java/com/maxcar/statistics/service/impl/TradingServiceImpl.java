package com.maxcar.statistics.service.impl;

import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.service.TradingService;
import com.maxcar.statistics.service.impl.mapperService.TradingMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("tradingService")
public class TradingServiceImpl implements TradingService {

    @Autowired
    private TradingMapperService tradingMapperService;

    @Override
    public List<LinkedHashMap> getVolumeAndValue(TradingRequest tradingRequest) {
        List<LinkedHashMap> dealMap = tradingMapperService.getVolumeAndValue(tradingRequest);
        return dealMap;
    }
}
