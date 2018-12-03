package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.statistics.dao.TradingDao;
import com.maxcar.statistics.model.request.TradingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TradingMapperService {

    @Autowired
    private TradingDao tradingDao;

    public List<LinkedHashMap> getVolumeAndValue(TradingRequest tradingRequest) {

        String selectColumns = "DATE_FORMAT(i.bill_time, '%Y-%m') AS MONTH,\n" +
                "IFNULL(SUM(i.price),0) AS price";
        tradingRequest.setSelectColumns(selectColumns);

        if("001".equals(tradingRequest.getMarketId())){
            String selectCondition = "i.tenant_id = #{tenantId}  AND i.bill_time BETWEEN #{timeStart} AND #{timeEnd}";
            tradingRequest.setSelectCondition(selectCondition);
        }else {
            String selectCondition = "i.market_id = #{marketId}\n" +
                    "AND  i.tenant_id = #{tenantId}\n" +
                    "AND i.bill_time BETWEEN #{timeStart} AND #{timeEnd}";
            tradingRequest.setSelectCondition(selectCondition);
        }

        String groupByColumns = "DATE_FORMAT(i.bill_time, '%Y-%m') DESC";
        tradingRequest.setGroupByColumns(groupByColumns);
        return tradingDao.getVolumeAndValue(tradingRequest);
    }
}
