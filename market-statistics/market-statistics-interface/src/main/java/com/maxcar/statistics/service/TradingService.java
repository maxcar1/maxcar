package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.TradingRequest;

import java.util.LinkedHashMap;
import java.util.List;

public interface TradingService {
    List<LinkedHashMap> getVolumeAndValue(TradingRequest tradingRequest);
}
