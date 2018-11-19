package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.TradingDao;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TradingMapperService {

    @Autowired
    private TradingDao tradingDao;

    public List<TradingResponse> getVolumeAndValue(TradingRequest tradingRequest) {

        String selectColumns = "  DATE_FORMAT(i.bill_time, '%Y-%m') AS month,\n" +
                "IFNULL(SUM(i.price),0) AS price,\n  "+
                " COUNT(*) AS COUNT ";
        tradingRequest.setSelectColumns(selectColumns);

        if("001".equals(tradingRequest.getUserMaketId())){
            String selectCondition = "i.invoice_status = 2";
            if(StringUtil.isNotEmpty(tradingRequest.getTenantId())){
                selectCondition += "and  i.tenant_id = #{tenantId} ";
            }
            if(StringUtil.isNotEmpty(tradingRequest.getTimeStart())){
                selectCondition += " AND i.bill_time BETWEEN #{timeStart} AND #{timeEnd} ";
            }
            tradingRequest.setSelectCondition(selectCondition);
        }else {
            String selectCondition = "i.invoice_status = 2";
            if(StringUtil.isNotEmpty(tradingRequest.getMarketId())){
                selectCondition  += "and  i.market_id = #{marketId} " ;
            }
            if(StringUtil.isNotEmpty(tradingRequest.getTenantId())){
                selectCondition += "AND  i.tenant_id = #{tenantId} ";
            }
            if(StringUtil.isNotEmpty(tradingRequest.getTimeStart())){
                selectCondition += " AND i.bill_time BETWEEN #{timeStart} AND #{timeEnd} ";
            }

            tradingRequest.setSelectCondition(selectCondition);
        }

        String groupByColumns = " DATE_FORMAT(i.bill_time, '%Y-%m') DESC ";
        tradingRequest.setGroupByColumns(groupByColumns);
        return tradingDao.getVolumeAndValue(tradingRequest);
    }

}
