package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.DateUtils;
import com.maxcar.statistics.dao.CarpriceDayMapper;
import com.maxcar.statistics.dao.InventoryInvoiceDayMapper;
import com.maxcar.statistics.dao.InventoryInvoiceMonthMapper;
import com.maxcar.statistics.model.entity.InventoryInvoiceDayEntity;
import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import com.maxcar.statistics.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

@Service("tradingService")
public class TradingServiceImpl implements TradingService {

    @Autowired
    private InventoryInvoiceMonthMapper inventoryInvoiceMonthMapper;

    @Autowired
    private InventoryInvoiceDayMapper inventoryInvoiceDayMapper;

    @Autowired
    private CarpriceDayMapper carpriceDayMapper;

    @Override
    public List<InventoryInvoiceMonthEntity> getVolumeAndValue(TradingRequest tradingRequest) {
        List<InventoryInvoiceMonthEntity> dealList = getInventoryInvoiceMonthEntities(tradingRequest);

        for (InventoryInvoiceMonthEntity inventory : dealList) {
            // 设置上个月时间
            String reportTime = inventory.getReportTime();
            reportTime += "-01";
            String agoMonth = DateUtils.getAgoMonth(reportTime);
            String substring = agoMonth.substring(0, 7);
            tradingRequest.setTimeStart(substring);
            tradingRequest.setTimeEnd(substring);

            List<InventoryInvoiceMonthEntity> list = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);
            if (list.size() > 0) {
                InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity1 = list.get(0);
                //  得到上个月交易总量和交易总价值
                Integer agoSalesCount = inventoryInvoiceMonthEntity1.getSalesCount();
                Double agoSalesPrice = inventoryInvoiceMonthEntity1.getSalesPrice();

                //  得到本月交易总量个交易总价值
                Integer nowSalesCount = inventory.getSalesCount();
                Double nowSalesPrice = inventory.getSalesPrice();
                Integer avgSalesCount = (nowSalesCount + (agoSalesCount == null ? 0 : agoSalesCount)) / 2;
                Double avgSalesPrice = (nowSalesPrice + (agoSalesPrice == null ? 0 : agoSalesPrice)) / 2 / 10000.0;
                inventory.setAvgSalesCount(avgSalesCount);
                inventory.setAvgSalesPrice(avgSalesPrice);
            }
        }
        return dealList;

    }

    /**
     * 获取包含本月之前的所有月份  每个月的 交易总量和交易数量总量
     *
     * @param tradingRequest
     * @return
     */
    private List<InventoryInvoiceMonthEntity> getInventoryInvoiceMonthEntities(TradingRequest tradingRequest) {
        Date date = new Date();

        List<InventoryInvoiceMonthEntity> dealList = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);

        Date monthStart = DateUtils.getMonthStart(date);
        String s = DateUtils.formatDate(monthStart, DateUtils.DATE_FORMAT_DATETIME);
        tradingRequest.setTimeStart(s);
        Date monthEnd = DateUtils.getMonthEnd(date);
        String s1 = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATETIME);
        tradingRequest.setTimeEnd(s1);

        InventoryInvoiceDayEntity inventoryInvoiceDayEntity = inventoryInvoiceDayMapper.sumMonth(tradingRequest);
        String month = s.substring(0, 7);
        Integer salesCount = inventoryInvoiceDayEntity.getSalesCount();
        Double salesPrice = inventoryInvoiceDayEntity.getSalesPrice();
        InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity = new InventoryInvoiceMonthEntity();
        inventoryInvoiceMonthEntity.setReportTime(month);
        inventoryInvoiceMonthEntity.setSalesPrice(salesPrice);
        int i = Integer.parseInt(new DecimalFormat("0").format(salesCount));
        inventoryInvoiceMonthEntity.setSalesCount(i);

        dealList.add(inventoryInvoiceMonthEntity);
        return dealList;
    }


    @Override
    public List<TradingResponse> getIncreaseRate(TradingRequest tradingRequest) throws ParseException {
        int type = tradingRequest.getType();
        List<InventoryInvoiceMonthEntity> inventoryInvoiceMonthEntities = getInventoryInvoiceMonthEntities(tradingRequest);
        List<TradingResponse> goList = new ArrayList<>();

        for (InventoryInvoiceMonthEntity invoice : inventoryInvoiceMonthEntities) {
            TradingResponse tradingResponse = new TradingResponse();

            String nowTime = invoice.getReportTime();
            tradingResponse.setMonth(nowTime);
            Integer nowSalesCount = invoice.getSalesCount();
            Double nowSalesPrice = invoice.getSalesPrice();
            nowTime += "-01";

            //  看是环比还是同比  如果0是同比   1是环比
            if (type == 0) {
                //  获取到去年这个月的数据
                String yearAgoMonth = DateUtils.getYearAgoMonth(nowTime);
                yearAgoMonth = yearAgoMonth.substring(0, 7);
                tradingRequest.setTimeStart(yearAgoMonth);
                tradingRequest.setTimeEnd(yearAgoMonth);
            } else if (type == 1) {
                //  获取到上个月的数据
                String agoMonth = DateUtils.getAgoMonth(nowTime);
                agoMonth = agoMonth.substring(0, 7);
                tradingRequest.setTimeStart(agoMonth);
                tradingRequest.setTimeEnd(agoMonth);
            }
            List<InventoryInvoiceMonthEntity> list = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);

            Integer agoSalesCount = 0;
            Double agoSalesPrice = 0.0;

            if (list.size() > 0) {
                InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity = list.get(0);
                agoSalesCount = inventoryInvoiceMonthEntity.getSalesCount();
                agoSalesPrice = inventoryInvoiceMonthEntity.getSalesPrice();
            }
            double salesCount = Math.round((nowSalesCount - agoSalesCount) / (agoSalesCount == 0 ? 1 : agoSalesCount) * 100) / 100.0;
            double salesPrice = Math.round((nowSalesPrice - agoSalesPrice) / (agoSalesPrice == 0 ? 1 : agoSalesPrice) * 100) / 100.0;

            tradingResponse.setCountRate(salesCount);
            tradingResponse.setPriceRate(salesPrice);
            goList.add(tradingResponse);
        }
        return goList;
    }

    @Override
    public Map<String, Object> getAvgPrice(TradingRequest tradingRequest) {
        List<InventoryInvoiceMonthEntity> list = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);
        Date date = new Date();
        Date monthStart = DateUtils.getMonthStart(date);
        Date monthEnd = DateUtils.getMonthEnd(date);
        String start = DateUtils.formatDate(monthStart, DateUtils.DATE_FORMAT_DATETIME);
        String end = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATETIME);
        String nowMonth = start.substring(0, 7);
        tradingRequest.setTimeStart(start);
        tradingRequest.setTimeEnd(end);

        InventoryInvoiceDayEntity inventoryInvoiceDayEntity = inventoryInvoiceDayMapper.sumMonth(tradingRequest);
        Double avgSalesPrice = 0.0;
        if (inventoryInvoiceDayEntity != null) {
            avgSalesPrice = inventoryInvoiceDayEntity.getSalesAvgPrice();
            InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity = new InventoryInvoiceMonthEntity();
            inventoryInvoiceMonthEntity.setReportTime(inventoryInvoiceDayEntity.getReportTime());
            inventoryInvoiceMonthEntity.setSalesAvgPrice(inventoryInvoiceDayEntity.getSalesAvgPrice());
            inventoryInvoiceMonthEntity.setReportTime(nowMonth);
            list.add(inventoryInvoiceMonthEntity);
        }
        for (InventoryInvoiceMonthEntity monthEntity : list) {
            avgSalesPrice += monthEntity.getSalesAvgPrice();
        }


        double avgYearPrice = Math.round((avgSalesPrice == 0 ? 1 : avgSalesPrice) / 12 * 100) / 100.0;
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("avgYearPrice", avgYearPrice);
        return map;
    }

    @Override
    public List<TradingResponse> getAvgPriceRate(TradingRequest tradingRequest) {
        List<InventoryInvoiceMonthEntity> list = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);
        List<TradingResponse> tradingResponses = new ArrayList<>();
        for (InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity : list) {
            TradingResponse tradingResponse = new TradingResponse();
            Double nowSalesAvgPrice = inventoryInvoiceMonthEntity.getSalesAvgPrice();
            String reportTime = inventoryInvoiceMonthEntity.getReportTime();
            //  返回对象设置月份
            tradingResponse.setMonth(reportTime);
            reportTime += "-01";
            //  环比增长率
            String agoMonth = DateUtils.getAgoMonth(reportTime);
            String agoMonthTime = agoMonth.substring(0, 7);
            tradingRequest.setTimeStart(agoMonthTime);
            tradingRequest.setTimeEnd(agoMonthTime);
            List<InventoryInvoiceMonthEntity> agoList = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);
            double avgPriceMonth = 0.0;
            if (agoList.size() > 0) {
                InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity1 = agoList.get(0);
                Double agoSalesAvgPrice = inventoryInvoiceMonthEntity1.getSalesAvgPrice();
                avgPriceMonth = Math.round((nowSalesAvgPrice - agoSalesAvgPrice) / agoSalesAvgPrice * 100) / 100.0;
                tradingResponse.setAvgPriceMonth(avgPriceMonth);
            }

            //  同比增长率
            agoMonth = DateUtils.getYearAgoMonth(reportTime);
            agoMonthTime = agoMonth.substring(0, 7);
            tradingRequest.setTimeStart(agoMonthTime);
            tradingRequest.setTimeEnd(agoMonthTime);
            double avgPriceYear = 0.0;
            List<InventoryInvoiceMonthEntity> agoYearList = inventoryInvoiceMonthMapper.selectAllMonth(tradingRequest);
            if (agoYearList.size() > 0) {
                InventoryInvoiceMonthEntity inventoryInvoiceMonthEntity1 = agoList.get(0);
                Double agoSalesAvgPrice = inventoryInvoiceMonthEntity1.getSalesAvgPrice();
                avgPriceYear = Math.round((nowSalesAvgPrice - agoSalesAvgPrice) / agoSalesAvgPrice * 100) / 100.0;
                tradingResponse.setAvgPriceMonth(avgPriceYear);
            }
            tradingResponses.add(tradingResponse);
        }
        return tradingResponses;
    }

    @Override
    public Map<String, Double> getTenantCount(TradingRequest tradingRequest) {
        Map map = inventoryInvoiceDayMapper.tenantCarNum(tradingRequest);
        if(map == null){
            map.put("10万以下",0.0);
            map.put("10-20万",0.0);
            map.put("20-30万",0.0);
            map.put("30-40万",0.0);
            map.put("40-50万",0.0);
            map.put("50万以上",0.0);
        }
        return map;
    }

    @Override
    public List<TradingResponse> getTenantDeal(TradingRequest tradingRequest) {
        List<TradingResponse> list = inventoryInvoiceMonthMapper.getTenantDeal(tradingRequest);
        List<TradingResponse> listDay = inventoryInvoiceDayMapper.getTenantDealDay(tradingRequest);
        if (listDay.size() > 0) {
            TradingResponse trading = listDay.get(0);
            Date date = new Date();
            String s = DateUtils.formatDate(date, DateUtils.DATE_FORMAT_DATETIME);
            String substring = s.substring(0, 7);
            trading.setMonth(substring);
            list.add(trading);
        }
        for (TradingResponse response : list) {
            String month = response.getMonth();
            Double nowTenantCount = response.getTenantCount();
            month += "-01";
            //  环比
            String agoMonth = DateUtils.getAgoMonth(month);
            agoMonth = agoMonth.substring(0, 7);
            tradingRequest.setTimeStart(agoMonth);
            tradingRequest.setTimeEnd(agoMonth);
            double countMonth = 0.0;
            List<TradingResponse> agoMonthList = inventoryInvoiceMonthMapper.getTenantDeal(tradingRequest);
            if (agoMonthList.size() > 0) {
                TradingResponse response1 = agoMonthList.get(0);
                Double agoMonthTenantCount = response1.getTenantCount();
                countMonth = Math.round((nowTenantCount - agoMonthTenantCount) / agoMonthTenantCount * 100) / 100.0;
            }
            response.setTenantCountMonth(countMonth);
            //  同比
            String yearAgoMonth = DateUtils.getYearAgoMonth(month);
            yearAgoMonth = yearAgoMonth.substring(0, 7);
            tradingRequest.setTimeStart(yearAgoMonth);
            tradingRequest.setTimeEnd(yearAgoMonth);
            double countYear = 0.0;
            List<TradingResponse> agoYearList = inventoryInvoiceMonthMapper.getTenantDeal(tradingRequest);
            if (agoYearList.size() > 0) {
                TradingResponse response1 = agoYearList.get(0);
                Double agoYearTenantCount = response1.getTenantCount();
                countYear = Math.round((nowTenantCount - agoYearTenantCount) / agoYearTenantCount * 100) / 100.0;
            }
            response.setTenantCountYear(countYear);
        }
        return list;
    }

    @Override
    public Map<String, Object> getCarPrice(TradingRequest tradingRequest) {
        Map<String, Object> map = inventoryInvoiceMonthMapper.countCarPriceDistribution(tradingRequest);
        return map;
    }

    @Override
    public List<TradingResponse> transactionLevel(TradingRequest tradingRequest) throws ParseException {
        String timeStart = tradingRequest.getTimeStart();
        timeStart += "-01";
        tradingRequest.setTimeStart(timeStart);
        String timeEnd = tradingRequest.getTimeEnd();
        timeEnd += "-01";
        Date date = DateUtils.parseDate(timeEnd, DateUtils.DATE_FORMAT_DATEONLY);
        Date monthEnd = DateUtils.getMonthEnd(date);
        String s = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATEONLY);
        tradingRequest.setTimeEnd(s);
        List<TradingResponse> list = carpriceDayMapper.transactionLevel(tradingRequest);
        for (TradingResponse price : list) {
            String month = price.getMonth();
            month += "-01";
            //  环比
            String agoMonth = DateUtils.getAgoMonth(month);
            Date date1 = DateUtils.parseDate(agoMonth, DateUtils.DATE_FORMAT_DATEONLY);
            Date monthEnd1 = DateUtils.getMonthEnd(date1);
            String s1 = DateUtils.formatDate(monthEnd1);
            String substring = s1.substring(0, 10);
            tradingRequest.setTimeStart(agoMonth);
            tradingRequest.setTimeEnd(substring);
            List<TradingResponse> listMonth = carpriceDayMapper.transactionLevel(tradingRequest);
            Double count = price.getCount();
            double dealPriceMonth = 0.0;
            if (listMonth.size() > 0) {
                TradingResponse response = listMonth.get(0);
                Double agoCount = response.getCount();
                dealPriceMonth = Math.round((count - agoCount) / agoCount * 100) / 100.0;
            }
            price.setDealPriceMonth(dealPriceMonth);

            //  同比
            double dealPriceYear = 0.0;
            String yearAgoMonth = DateUtils.getYearAgoMonth(month);
            date1 = DateUtils.parseDate(yearAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
            monthEnd1 = DateUtils.getMonthEnd(date1);
            s1 = DateUtils.formatDate(monthEnd1);
            substring = s1.substring(0, 10);
            tradingRequest.setTimeStart(yearAgoMonth);
            tradingRequest.setTimeEnd(substring);
            price.setDealPriceYear(dealPriceYear);
            List<TradingResponse> listYear = carpriceDayMapper.transactionLevel(tradingRequest);
            if (listYear.size() > 0) {
                TradingResponse response = listYear.get(0);
                Double agoCount = response.getCount();
                dealPriceYear = Math.round((count - agoCount) / agoCount * 100) / 100.0;
            }
            price.setDealPriceYear(dealPriceYear);
        }
        return list;
    }

    @Override
    public List<TradingResponse> stockAvgDay(TradingRequest tradingRequest) {
        List<TradingResponse> carpriceDayEntityList = carpriceDayMapper.stockAvgDay(tradingRequest);
        double count = 0;
        for(TradingResponse response : carpriceDayEntityList){
            count += response.getStockAvgStocktime();
        }
        for(TradingResponse response : carpriceDayEntityList){
            Double stockAvgStocktime = response.getStockAvgStocktime();
            response.setDayPercentage(stockAvgStocktime / count);
        }
        return carpriceDayEntityList;
    }

}
