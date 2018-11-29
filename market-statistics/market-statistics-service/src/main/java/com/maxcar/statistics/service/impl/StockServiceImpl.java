package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.InventoryInvoiceDayMapper;
import com.maxcar.statistics.dao.InventoryInvoiceMonthMapper;
import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.response.StockResponse;
import com.maxcar.statistics.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private InventoryInvoiceMonthMapper inventoryInvoiceMonthMapper;

    @Autowired
    private InventoryInvoiceDayMapper inventoryInvoiceDayMapper;

    @Override
    public List<StockResponse> getCountAndValue(StockRequest stockRequest) {
        //  封装当月的数据的查询条件
        StockRequest stockRequestNowMonth = new StockRequest();
        Date date = new Date();
        Date monthStart = DateUtils.getMonthStart(date);
        String nowMonthStart = DateUtils.formatDate(monthStart, DateUtils.DATE_FORMAT_DATEONLY);
        stockRequestNowMonth.setTimeStart(nowMonthStart);
        Date monthEnd = DateUtils.getMonthEnd(date);
        String nowMonthEnd = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATEONLY);
        stockRequestNowMonth.setTimeEnd(nowMonthEnd);
        stockRequestNowMonth.setMarketId(stockRequest.getMarketId());
        if (StringUtil.isNotEmpty(stockRequest.getTenantId())) {
            stockRequestNowMonth.setTenantId(stockRequest.getTenantId());
        }
        //  获取前几个月的数据集合
        List<StockResponse> list = inventoryInvoiceMonthMapper.getCountAndValue(stockRequest);
        //  获取当月数据
        StockResponse nowMonth = inventoryInvoiceDayMapper.getCountAndValue(stockRequestNowMonth);
        //  如果当月数据不为空，则加入集合
        if (nowMonth != null) {
            nowMonth.setReportTime(nowMonthStart.substring(0, 7));
            list.add(nowMonth);
        }
        for (StockResponse inventory : list) {
            Double stockPrice = inventory.getStockPrice();
            Double stockCount = inventory.getStockCount();
            String reportTime = inventory.getReportTime();
            reportTime += "-01";
            //  移动平均交易
            double avgStockPrice = 0.0;
            //  移动平均库存亮
            double avgStockCount = 0.0;
            //  环比价格
            double rateMonthStockPrice = 0.0;
            //  环比库存量
            double rateMonthStockCount = 0.0;
            //  同比价格
            double rateYearStockPrice = 0.0;
            //  同比库存量
            double rateYearStockCount = 0.0;

            //  设置查询时间  上一个月的月份
            getOneAgoMonth(stockRequest, reportTime);
            List<StockResponse> agoMonthList = inventoryInvoiceMonthMapper.getCountAndValue(stockRequest);
            if (agoMonthList.size() > 0) {
                StockResponse stockResponse = agoMonthList.get(0);
                //  移动平均
                Double agoMonthCount = stockResponse.getStockCount();
                Double agoMonthPrice = stockResponse.getStockPrice();
                avgStockPrice = Math.round((stockPrice + agoMonthPrice) / 2);
                avgStockCount = Math.round((stockCount + agoMonthCount) / 2);

                //  环比
                rateMonthStockCount = Math.round((stockCount - agoMonthCount) / agoMonthCount * 100) / 100.0;
                rateMonthStockPrice = Math.round((stockPrice - agoMonthPrice) / agoMonthPrice * 100) / 100.0;
            }
            //  设置移动平均
            inventory.setAvgStockCount(avgStockCount);
            inventory.setAvgStockPrice(avgStockPrice);
            //  设置环比参数
            inventory.setStockMonthPriceIncrease(rateMonthStockPrice);
            inventory.setStockMonthCountIncrease(rateMonthStockCount);
            //  设置去年同月的月份
            getOneAgoYearMonth(stockRequest, reportTime);
            List<StockResponse> agoYearList = inventoryInvoiceMonthMapper.getCountAndValue(stockRequest);
            if (agoYearList.size() > 0) {
                StockResponse stockResponse = agoYearList.get(0);
                Double stockPriceYear = stockResponse.getStockPrice();
                Double stockCountYear = stockResponse.getStockCount();

                rateYearStockCount = Math.round((stockCount - stockCountYear) / stockCountYear * 100) / 100.0;
                rateYearStockPrice = Math.round((stockPrice - stockPriceYear) / stockPriceYear * 100) / 100.0;
            }
            //  设置同比参数
            inventory.setStockYearCountIncrease(rateYearStockCount);
            inventory.setStockYearPriceIncrease(rateYearStockPrice);
        }
        return list;
    }

    @Override
    public Map<String, Object> getParkingSaturability(StockRequest stockRequest) {
        List<StockResponse> list = inventoryInvoiceMonthMapper.getParkingSaturability(stockRequest);
        Map<String, Object> map = new HashMap<>();
        Double parkingSaturability = 0.0;
        for(StockResponse response : list){
            parkingSaturability += response.getParkingSaturability();
        }
        double avgParkingSaturability = parkingSaturability / list.size();
        map.put("avgParkingSaturability",avgParkingSaturability);
        map.put("list",list);
        return map;
    }

    /**
     * 获取上一个月的月份时间
     *
     * @param stockRequest
     * @param reportTime   入参格式  xxxx-xx-xx
     */
    private void getOneAgoMonth(StockRequest stockRequest, String reportTime) {
        String agoMonth = DateUtils.getAgoMonth(reportTime);
        String substring = agoMonth.substring(0, 7);
        stockRequest.setTimeStart(substring);
        stockRequest.setTimeEnd(substring);
    }

    /**
     * 获取去年同一月的月份时间
     *
     * @param stockRequest
     * @param reportTime   入参格式  xxxx-xx-xx
     */
    private void getOneAgoYearMonth(StockRequest stockRequest, String reportTime) {
        String agoYearMonth = DateUtils.getYearAgoMonth(reportTime);
        String substring = agoYearMonth.substring(0, 7);
        stockRequest.setTimeStart(substring);
        stockRequest.setTimeEnd(substring);
    }
}
