package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.CarStockDayMapper;
import com.maxcar.statistics.dao.CarStockMonthMapper;
import com.maxcar.statistics.dao.InventoryInvoiceDayMapper;
import com.maxcar.statistics.dao.InventoryInvoiceMonthMapper;
import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.response.StockResponse;
import com.maxcar.statistics.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private InventoryInvoiceMonthMapper inventoryInvoiceMonthMapper;

    @Autowired
    private InventoryInvoiceDayMapper inventoryInvoiceDayMapper;

    @Autowired
    private CarStockMonthMapper carStockMonthMapper;

    @Autowired
    private CarStockDayMapper carStockDayMapper;

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
        for (StockResponse response : list) {
            parkingSaturability += response.getParkingSaturability();
        }
        double avgParkingSaturability = parkingSaturability / list.size();
        map.put("avgParkingSaturability", avgParkingSaturability);
        map.put("list", list);
        return map;
    }

    @Override
    public List<StockResponse> getStockDayCar(StockRequest stockRequest) {
        return inventoryInvoiceMonthMapper.getStockDayCar(stockRequest);
    }

    @Override
    public List<StockResponse> getStockAvgDayCar(StockRequest stockRequest) {
        List<StockResponse> list = inventoryInvoiceMonthMapper.getStockAvgMonthCar(stockRequest);
        Date date = new Date();
        Date monthStart = DateUtils.getMonthStart(date);
        Date monthEnd = DateUtils.getMonthEnd(date);
        String start = DateUtils.formatDate(monthStart, DateUtils.DATE_FORMAT_DATEONLY);
        String end = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATEONLY);
        stockRequest.setTimeStart(start);
        stockRequest.setTimeEnd(end);
        Double nowDay = inventoryInvoiceDayMapper.getStockAvgDayCar(stockRequest);
        String reportTime1 = start.substring(0, 7);
        StockResponse response = new StockResponse();
        response.setReportTime(reportTime1);
        response.setStockDayAvg(nowDay);
        list.add(response);
        Double monthStockDay = 0.0;
        Double YearStockDay = 0.0;
        for (StockResponse month : list) {
            String reportTime = month.getReportTime();
            reportTime = reportTime.substring(0, 7);
            double stockDayAvg = month.getStockDayAvg();
            reportTime += "-01";
            getOneAgoMonth(stockRequest, reportTime);
            Double monthStockDayAvg = inventoryInvoiceDayMapper.getStockAvgDayCar(stockRequest);
            getOneAgoYearMonth(stockRequest, reportTime);
            Double YraeStockDayAvg = inventoryInvoiceDayMapper.getStockAvgDayCar(stockRequest);
            monthStockDay = Math.round((stockDayAvg - monthStockDayAvg) / (monthStockDayAvg == 0 ? 1 : monthStockDayAvg) * 100) / 100.0;
            YearStockDay = Math.round((stockDayAvg - YraeStockDayAvg) / (YraeStockDayAvg == 0 ? 1 : YraeStockDayAvg) * 100) / 100.0;
            month.setMonthStockDay(monthStockDay);
            month.setYearStockDay(YearStockDay);
        }
        return list;
    }

    @Override
    public Map<String, Double> getTenantStock(StockRequest stockRequest) {
        return inventoryInvoiceDayMapper.getTenantStock(stockRequest);

    }

    @Override
    public List<Map<String, Object>> getTenantAvgStock(StockRequest stockRequest) {
        List<Map<String, Object>> tenantAvgStock = inventoryInvoiceMonthMapper.getTenantAvgStock(stockRequest);
        for (Map map : tenantAvgStock) {
            String reportTime = (String) map.get("reportTime");
            double avg = Double.parseDouble(map.get("avg").toString());
            reportTime += "-01";
            //   环比
            String agoMonth = DateUtils.getAgoMonth(reportTime);
            stockRequest.setTimeStart(agoMonth.substring(0, 7));
            stockRequest.setTimeEnd(agoMonth.substring(0, 7));
            List<Map<String, Object>> agoMonthMap = inventoryInvoiceMonthMapper.getTenantAvgStock(stockRequest);
            if (agoMonthMap.size() > 0) {
                double avg1 = Double.parseDouble(agoMonthMap.get(0).get("avg").toString());
                double relativeMonth = Math.round((avg - avg1) / avg1 * 100) / 100.0;
                map.put("relativeMonth", relativeMonth);
            } else {
                map.put("relativeMonth", 0);
            }

            //   同比
            String agoYearMonth = DateUtils.getYearAgoMonth(reportTime);
            stockRequest.setTimeStart(agoYearMonth.substring(0, 7));
            stockRequest.setTimeEnd(agoYearMonth.substring(0, 7));
            List<Map<String, Object>> agoYearMonthList = inventoryInvoiceMonthMapper.getTenantAvgStock(stockRequest);
            if (agoYearMonthList.size() > 0) {
                double avg1 = Double.parseDouble(agoYearMonthList.get(0).get("avg").toString());
                double relativeYear = Math.round((avg - avg1) / avg1 * 100) / 100.0;
                map.put("relativeYear", relativeYear);
            } else {
                map.put("relativeYear", 0);
            }
        }

        return tenantAvgStock;
    }

    @Override
    public Map<String, Object> getTenantStockBranch(StockRequest stockRequest) {
        Map<String, Object> map = inventoryInvoiceDayMapper.getTenantStockBranch(stockRequest);
        if (map == null) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("10台以下", 0);
            map1.put("10-20台", 0);
            map1.put("20-30台", 0);
            map1.put("30-40台", 0);
            map1.put("40-50台", 0);
            map1.put("50台以上", 0);
            return map1;
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> getTenantAvgStockTrend(StockRequest stockRequest) {
        List<Map<String, Object>> mapList = inventoryInvoiceMonthMapper.getTenantAvgStockTrend(stockRequest);
        Date date = new Date();
        String s = DateUtils.formatDate(date, DateUtils.DATE_FORMAT_DATETIME);
        stockRequest.setTimeStart(s.substring(0, 7));
        Map<String, Object> nowMap = inventoryInvoiceDayMapper.getTenantAvgStockTrend(stockRequest);
        nowMap.put("reportTime", s.substring(0, 7));
        mapList.add(nowMap);
        for (Map map : mapList) {
            String reportTime = map.get("reportTime").toString();
            int count = Integer.parseInt(map.get("count").toString());
            reportTime += "-01";
            getOneAgoMonth(stockRequest, reportTime);
            List<Map<String, Object>> monthMapList = inventoryInvoiceMonthMapper.getTenantAvgStockTrend(stockRequest);
            double relativeMonth = 0.0;
            if (monthMapList.size() > 0) {
                Map<String, Object> map1 = monthMapList.get(0);
                int count1 = Integer.parseInt(map1.get("count").toString());
                relativeMonth = Math.round((count - count1) / count1 * 100) / 100.0;
            }
            map.put("relativeMonth", relativeMonth);
            getOneAgoYearMonth(stockRequest, reportTime);
            double relativeYear = 0.0;
            List<Map<String, Object>> yearMapList = inventoryInvoiceMonthMapper.getTenantAvgStockTrend(stockRequest);
            if (yearMapList.size() > 0) {
                Map<String, Object> map1 = yearMapList.get(0);
                int count1 = Integer.parseInt(map1.get("count").toString());
                relativeYear = Math.round((count - count1) / count1 * 100) / 100.0;
            }
            map.put("relativeYear", relativeYear);
        }
        return mapList;
    }

    @Override
    public Map<String, Object> getStockPrice(StockRequest stockRequest) {
        HashMap<String, Object> map = new HashMap<>();
        stockRequest.setCarNumType("0");
        Integer stockPrice0 = carStockMonthMapper.getStockPrice(stockRequest);
        if(stockPrice0 != null){
            map.put("10万以下", stockPrice0);
        }else {
            map.put("10万以下", 0);
        }

        stockRequest.setCarNumType("1");
        Integer stockPrice1 = carStockMonthMapper.getStockPrice(stockRequest);
        if(stockPrice1 != null){
            map.put("10-20万", stockPrice1);
        }else {
            map.put("10-20万", 0);
        }

        stockRequest.setCarNumType("2");
        Integer stockPrice2 = carStockMonthMapper.getStockPrice(stockRequest);
        if(stockPrice2 != null){
            map.put("20-30万", stockPrice2);
        }else {
            map.put("20-30万", 0);
        }

        stockRequest.setCarNumType("3");
        Integer stockPrice3 = carStockMonthMapper.getStockPrice(stockRequest);
        if(stockPrice3 != null){
            map.put("30-40万", stockPrice3);
        }else {
            map.put("30-40万", 0);
        }

        stockRequest.setCarNumType("4");
        Integer stockPrice4 = carStockMonthMapper.getStockPrice(stockRequest);
        if(stockPrice4 != null){
            map.put("40-50万", stockPrice4);
        }else {
            map.put("40-50万", 0);
        }

        stockRequest.setCarNumType("5");
        Integer stockPrice5 = carStockMonthMapper.getStockPrice(stockRequest);
        if(stockPrice5 != null){
            map.put("50万以上", stockPrice5);
        }else {
            map.put("50万以上", 0);
        }

        return map;
    }

    @Override
    public List<Map<String , Object>> getStockPriceTrend(StockRequest stockRequest) {
        List<Map<String , Object>> mapList = inventoryInvoiceMonthMapper.getStockPriceTrend(stockRequest);
        Map<String , Object> nowMap = inventoryInvoiceDayMapper.getStockPriceTrend(stockRequest);
        String s = DateUtils.formatDate(new Date(), DateUtils.DATE_FORMAT_DATETIME);
        nowMap.put("reportTime" , s.substring(0,7));
        mapList.add(nowMap);
        for (Map map : mapList) {
            String time = map.get("reportTime").toString();
            int count = Integer.parseInt(map.get("count").toString());
            time += "-01";
            //   环比
            getOneAgoMonth(stockRequest,time);
            List<Map<String , Object>> mapAgoMonthList = inventoryInvoiceMonthMapper.getStockPriceTrend(stockRequest);
            double relativeMonth = 0.0;
            if(mapAgoMonthList.size() > 0){
                Map<String, Object> map1 = mapAgoMonthList.get(0);
                int count1 = Integer.parseInt(map1.get("count").toString());
                relativeMonth = Math.round((count - count1) / count1 * 100) / 100.0;
            }
            map.put("relativeMonth",relativeMonth);

            //  同比
            getOneAgoYearMonth(stockRequest,time);
            List<Map<String , Object>> mapAgoYearList = inventoryInvoiceMonthMapper.getStockPriceTrend(stockRequest);
            double relativeYear = 0.0;
            if(mapAgoYearList.size() > 0){
                Map<String, Object> map1 = mapAgoYearList.get(0);
                int count1 = Integer.parseInt(map1.get("count").toString());
                relativeYear = Math.round((count - count1) / count1 * 100) / 100.0;
            }
            map.put("relativeYear",relativeYear);
        }

        return  mapList;
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
