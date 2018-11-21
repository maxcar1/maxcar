package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.provider.RankingProvider;
import com.maxcar.statistics.dao.provider.TradingProvider;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.LinkedHashMap;
import java.util.List;

public interface TradingDao {
    @SelectProvider(type = TradingProvider.class, method = "getVolumeAndValue")
    List<TradingResponse> getVolumeAndValue(TradingRequest tradingRequest);

}
