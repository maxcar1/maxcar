package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.TradingDao;
import com.maxcar.statistics.dao.provider.TradingProvider;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradingMapperService {

    @Autowired
    private TradingDao tradingDao;

    public List<TradingResponse> getVolumeAndValue(TradingRequest tradingRequest) {

        String selectColumns = "  DATE_FORMAT(i.bill_time, '%Y-%m') AS month,\n" +
                "IFNULL(SUM(i.price),0) AS price,\n  " +
                " COUNT(*) AS COUNT ";
        tradingRequest.setSelectColumns(selectColumns);

        if (!"001".equals(tradingRequest.getUserMaketId())) {
            tradingRequest.setMarketId(tradingRequest.getUserMaketId());
        }

        String selectCondition = "i.invoice_status = 2";
        if (StringUtil.isNotEmpty(tradingRequest.getMarketId())) {
            selectCondition += "and  i.market_id = #{marketId} ";
        }
        if (StringUtil.isNotEmpty(tradingRequest.getTenantId())) {
            selectCondition += "and  i.tenant_id = #{tenantId} ";
        }
        if (StringUtil.isNotEmpty(tradingRequest.getTimeStart())) {
            selectCondition += " AND i.bill_time BETWEEN #{timeStart} AND #{timeEnd} ";
        }
        tradingRequest.setSelectCondition(selectCondition);

        String groupByColumns = " DATE_FORMAT(i.bill_time, '%Y-%m') DESC ";
        tradingRequest.setGroupByColumns(groupByColumns);
        return tradingDao.getVolumeAndValue(tradingRequest);
    }

    public Double countCarNum(TradingRequest tradingRequest) {
        String selectColumns = " COUNT(*) ";
        tradingRequest.setSelectColumns(selectColumns);

        if (!"001".equals(tradingRequest.getUserMaketId())) {
            tradingRequest.setMarketId(tradingRequest.getUserMaketId());
        }

        String selectForm = " (SELECT\n" +
                "        COUNT(*) AS num\n" +
                "        FROM\n" +
                "        maxcar_market_l.invoice i\n" +
                "        WHERE  (i.invoice_status = 2  and  car_sources = 1 or car_sources = 2)";
        if (StringUtil.isNotEmpty(tradingRequest.getMarketId())) {
            selectForm += " and  i.market_id = #{marketId} ";
        }
        if (StringUtil.isNotEmpty(tradingRequest.getTenantTimeStart())) {
            selectForm += " AND i.bill_time BETWEEN  #{tenantTimeStart}  AND #{tenantTimeEnd} ";
        }
        selectForm += "  GROUP BY i.tenant_id)  aa  ";
        tradingRequest.setSelectFrom(selectForm);

        String selectCondition = " 1 = 1  ";
        Integer carNumMin = tradingRequest.getCarNumMin();
        Integer carNumMax = tradingRequest.getCarNumMax();

        if (carNumMax != 0 && carNumMax != null) {
            selectCondition += "  AND aa.num <  #{carNumMax}  ";
        }
        if (carNumMin != 0 && carNumMin != null) {
            selectCondition += "  AND  aa.num > #{carNumMin}  or  aa.num =  #{carNumMin} ";
        }
        tradingRequest.setSelectCondition(selectCondition);

        return tradingDao.countCarNum(tradingRequest);
    }

    public List<TradingResponse> getTenantMonthNum(TradingRequest tradingRequest) {

        String selectColumns = "  DATE_FORMAT(i.bill_time, '%Y-%m') AS MONTH,\n  " +
                "  COUNT(*) AS tenantCount  ";
        tradingRequest.setSelectColumns(selectColumns);

        if (!"001".equals(tradingRequest.getUserMaketId())) {
            tradingRequest.setMarketId(tradingRequest.getUserMaketId());
        }
        String selectCondition = "  (i.invoice_status = 2  AND car_sources  = 1 OR car_sources  = 2)  ";

        if (StringUtil.isNotEmpty(tradingRequest.getTimeStart())) {
            selectCondition += " AND i.bill_time BETWEEN #{timeStart} AND #{timeEnd}  ";
        }
        Integer carNumMin = tradingRequest.getCarNumMin();
        Integer carNumMax = tradingRequest.getCarNumMax();
        if (carNumMax != 0 && carNumMax != null) {
            selectCondition += "  AND  i.num < #{carNumMax}  ";
        }
        if (carNumMin != 0 && carNumMin != null) {
            selectCondition += "  AND i.num >  #{carNumMin}  OR i.num =  #{carNumMin}  ";
        }
        tradingRequest.setSelectCondition(selectCondition);

        String groupByColumns = " DATE_FORMAT(i.bill_time, '%Y-%m') DESC  ";
        tradingRequest.setGroupByColumns(groupByColumns);

        return tradingDao.getTenantMonthNum(tradingRequest);
    }
}
