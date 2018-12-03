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

    /*@Autowired
    private TradingMapperService tradingMapperService;*/
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

       /* List<TradingResponse> dealList = tradingMapperService.getVolumeAndValue(tradingRequest);

        for (int i = 0; i < dealList.size(); i++) {
            //  获取得到当月交易总价值
            TradingResponse response = dealList.get(i);
            Double price = response.getPrice();

            //  获取当月交易总数量
            Double count = response.getCount();
            int ii = i + 1;
            if (ii < dealList.size()) {
                TradingResponse responseMove = dealList.get(ii);
                //  计算移动平均总价值
                Double priceMove = responseMove.getPrice();
                response.setPriceMove((priceMove + price) / 2);
                //  计算移动平均总数量
                Double countMove = responseMove.getCount();
                response.setCountMove((countMove + count) / 2);
            } else {
                response.setPriceMove(price);
                response.setCountMove(count);
            }
        }

        return dealList;*/
    }

    /**
     * 获取包含本月之前的所有月份  每个月的 交易总量和交易数量总量
     *
     * @param tradingRequest
     * @return
     */
    private List<InventoryInvoiceMonthEntity> getInventoryInvoiceMonthEntities(TradingRequest tradingRequest) {
//        String timeEnd = tradingRequest.getTimeEnd();
//        String timeStart = tradingRequest.getTimeStart();
//        tradingRequest.setTimeEnd(timeEnd.substring(0, 7));
//        tradingRequest.setTimeStart(timeStart.substring(0, 7));
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
//        // 获取条件当中的现在数据
//        List<TradingResponse> nowList = tradingMapperService.getVolumeAndValue(tradingRequest);
//
//        //  封装12月前的时间条件
//        String timeStart = tradingRequest.getTimeStart();
//        String yearAgoMonthStart = DateUtils.getYearAgoMonth(timeStart);
//        tradingRequest.setTimeStart(yearAgoMonthStart);
//        String timeEnd = tradingRequest.getTimeEnd();
//        String yearAgoMonthEnd = DateUtils.getYearAgoMonth(timeEnd);
//        tradingRequest.setTimeEnd(yearAgoMonthEnd);
//
//        List<TradingResponse> beforeList = tradingMapperService.getVolumeAndValue(tradingRequest);
//
//        List list = new ArrayList();
//
//        for (int i = 0; i < nowList.size(); i++) {
//            Double countRate = 0.0;
//            Double priceRate = 0.0;
//
//            TradingResponse nowResponse = nowList.get(i);
//            // 如果一年前以前没有数据
//            if (beforeList.size() > 0) {
//                TradingResponse beforeResponse = beforeList.get(i);
//                //  交易总量同比增长率
//                Double nowCount = nowResponse.getCount();
//                Double beforeCount = beforeResponse.getCount();
//                countRate = (nowCount - beforeCount) / beforeCount;
//                //  交易总价值环比增长率
//                Double nowPrice = nowResponse.getPrice();
//                Double beforePrice = beforeResponse.getPrice();
//                priceRate = (nowPrice - beforePrice) / beforePrice;
//            }
//
//            TradingResponse response = new TradingResponse();
//            response.setMonth(nowResponse.getMonth());
//            response.setPriceRate(priceRate);
//            response.setCountRate(countRate);
//            list.add(response);
//        }
//        return list;
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
//
//    @Override
//    public Map<String, Object> getAvgPrice(TradingRequest tradingRequest) {
//        //  平均交易价格   该月市场交易总价值/市场交易总量
//        List<TradingResponse> list = tradingMapperService.getVolumeAndValue(tradingRequest);
//        for (TradingResponse response : list) {
//            Double price = response.getPrice();
//            Double count = response.getCount();
//            Double avgPrice = price / count;
//            response.setAvgPrice((double) Math.round(avgPrice * 100) / 100);
//        }
//
//        //  平均交易价格=最近12个月每个月平均交易价格的平均数
//        Double sumPrice = 0.0;
//        Date date = new Date();
//        Date monthEnd = DateUtils.getMonthEnd(date);
//        Date dayEnd = DateUtils.getDayEnd(monthEnd);
//        String nowTime = DateUtils.formatDate(dayEnd, DateUtils.DATE_FORMAT_DATEONLY);
//        String yearAgoMonth = DateUtils.getYearAgoMonth(nowTime);
//
//        tradingRequest.setTimeStart(yearAgoMonth);
//        tradingRequest.setTimeEnd(nowTime);
//        List<TradingResponse> avgList = tradingMapperService.getVolumeAndValue(tradingRequest);
//
//        for (TradingResponse response : avgList) {
//            Double price = response.getPrice();
//            Double count = response.getCount();
//            Double avgPrice = price / count;
//            sumPrice += avgPrice;
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("list", list);
//        map.put("avg", (double) Math.round((sumPrice / 12) * 100) / 100);
//
//        return map;
//
//    }
//
//    @Override
//    public List<TradingResponse> getAvgPriceRate(TradingRequest tradingRequest) throws Exception {
//        List<TradingResponse> list = tradingMapperService.getVolumeAndValue(tradingRequest);
//
//        for (TradingResponse response : list) {
//            // 当前月平均交易价格
//            Double price = response.getPrice();
//            Double count = response.getCount();
//            Double avg = price / count;
//            Double avgOneyear = 0.0;
//
//            //  同比增长率
//            String month = response.getMonth();
//            month += "-01";
//            String yearAgoMonth = DateUtils.getYearAgoMonth(month);
//            Date date = DateUtils.parseDate(yearAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
//            Date monthEnd = DateUtils.getMonthEnd(date);
//            String s = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATETIME);
//            tradingRequest.setTimeStart(yearAgoMonth);
//            tradingRequest.setTimeEnd(s);
//            List<TradingResponse> oneYearlist = tradingMapperService.getVolumeAndValue(tradingRequest);
//            if (oneYearlist.size() > 0) {
//                TradingResponse responseOneYear = oneYearlist.get(0);
//                Double price1 = responseOneYear.getPrice();
//                Double count1 = responseOneYear.getCount();
//                avgOneyear = price1 / count1;
//            }
//            Double avgPrice = (avg - avgOneyear) / (avgOneyear == 0 ? 1 : avgOneyear);
//            avgPrice = (double) Math.round((avgPrice * 100)) / 100;
//            response.setAvgPriceYear(avgPrice);
//
//            //  环比增长
//            Double avgUp = 0.0;
//            String monthUp = DateUtils.getAgoMonth(month);
//            Date dateUp = DateUtils.parseDate(monthUp, DateUtils.DATE_FORMAT_DATEONLY);
//            Date monthUpEnd = DateUtils.getMonthEnd(dateUp);
//            String up = DateUtils.formatDate(monthUpEnd, DateUtils.DATE_FORMAT_DATETIME);
//            tradingRequest.setTimeStart(monthUp);
//            tradingRequest.setTimeEnd(up);
//            List<TradingResponse> oneMonthList = tradingMapperService.getVolumeAndValue(tradingRequest);
//            if (oneMonthList.size() > 0) {
//                TradingResponse response1 = oneMonthList.get(0);
//                Double priceUp = response1.getPrice();
//                Double countUp = response1.getCount();
//                avgUp = (priceUp / countUp);
//            }
//
//            Double avgPriceUp = (avg - avgUp) / (avgUp == 0 ? 1 : avgUp);
//            avgUp = (double) Math.round((avgPriceUp * 100)) / 100;
//            response.setAvgPriceMonth(avgUp);
//        }
//        return list;
//    }
//
//    @Override
//    public Map<String, Double> getTenantCount(TradingRequest tradingRequest) {
//        tradingRequest.setCarNumMin(0);
//        tradingRequest.setCarNumMax(10);
//        Double count = tradingMapperService.countCarNum(tradingRequest);
//        Map<String, Double> map = new LinkedHashMap();
//        map.put("0", count);
//
//        tradingRequest.setCarNumMin(10);
//        tradingRequest.setCarNumMax(20);
//        count = tradingMapperService.countCarNum(tradingRequest);
//        map.put("10", count);
//
//        tradingRequest.setCarNumMin(20);
//        tradingRequest.setCarNumMax(30);
//        count = tradingMapperService.countCarNum(tradingRequest);
//        map.put("20", count);
//
//        tradingRequest.setCarNumMin(30);
//        tradingRequest.setCarNumMax(40);
//        count = tradingMapperService.countCarNum(tradingRequest);
//        map.put("30", count);
//
//        tradingRequest.setCarNumMin(40);
//        tradingRequest.setCarNumMax(50);
//        count = tradingMapperService.countCarNum(tradingRequest);
//        map.put("40", count);
//
//        tradingRequest.setCarNumMin(50);
//        tradingRequest.setCarNumMax(0);
//        count = tradingMapperService.countCarNum(tradingRequest);
//        map.put("50", count);
//        return map;
//
//    }
//
//    @Override
//    public List<TradingResponse> getTenantDeal(TradingRequest tradingRequest) throws Exception {
//        List<TradingResponse> list = tradingMapperService.getTenantMonthNum(tradingRequest);
//        for (TradingResponse tr : list) {
//            String month = tr.getMonth();
//            month += "-01";
//            //  同比
//            Double tenantCount = tr.getTenantCount();
//            Double tenantCount1 = 0.0;
//            String yearAgoMonth = DateUtils.getYearAgoMonth(month);
//            Date date = DateUtils.parseDate(yearAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
//            Date monthEnd = DateUtils.getMonthEnd(date);
//            String s = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATETIME);
//            tradingRequest.setTimeStart(yearAgoMonth);
//            tradingRequest.setTimeEnd(s);
//            List<TradingResponse> oneYearlist = tradingMapperService.getTenantMonthNum(tradingRequest);
//            if (oneYearlist.size() > 0) {
//                TradingResponse response = oneYearlist.get(0);
//                tenantCount1 = response.getTenantCount();
//            }
//            Double yearRate = (tenantCount - tenantCount1) / (tenantCount1 == 0 ? 1 : tenantCount1);
//            yearRate = (double) Math.round((yearRate * 100)) / 100;
//            tr.setAvgPriceYear(yearRate);
//
//            //  环比
//            String monthAgoMonth = DateUtils.getYearAgoMonth(month);
//            Date date1 = DateUtils.parseDate(monthAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
//            Date monthEnd1 = DateUtils.getMonthEnd(date1);
//            String s1 = DateUtils.formatDate(monthEnd1, DateUtils.DATE_FORMAT_DATETIME);
//            tradingRequest.setTimeStart(monthAgoMonth);
//            tradingRequest.setTimeEnd(s1);
//            List<TradingResponse> oneMonthlist = tradingMapperService.getTenantMonthNum(tradingRequest);
//            tenantCount1 = 0.0;
//            if (oneYearlist.size() > 0) {
//                TradingResponse response = oneYearlist.get(0);
//                tenantCount1 = response.getTenantCount();
//            }
//            Double MonthRate = (tenantCount - tenantCount1) / (tenantCount1 == 0 ? 1 : tenantCount1);
//            MonthRate = (double) Math.round((MonthRate * 100)) / 100;
//            tr.setAvgPriceMonth(MonthRate);
//        }
//
//        return list;
//    }
//
//    @Override
//    public void getCarPrice(TradingRequest tradingRequest) {
//
//    }
}
