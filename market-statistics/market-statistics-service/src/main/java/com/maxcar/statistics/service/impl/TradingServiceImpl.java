package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.DateUtils;
import com.maxcar.statistics.dao.TradingDao;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import com.maxcar.statistics.service.TradingService;
import com.maxcar.statistics.service.impl.mapperService.TradingMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service("tradingService")
public class TradingServiceImpl implements TradingService {

    @Autowired
    private TradingMapperService tradingMapperService;

    @Override
    public List<TradingResponse> getVolumeAndValue(TradingRequest tradingRequest) {
        List<TradingResponse> dealList = tradingMapperService.getVolumeAndValue(tradingRequest);

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

        return dealList;
    }

    @Override
    public List getIncreaseRate(TradingRequest tradingRequest) {
        // 获取条件当中的现在数据
        List<TradingResponse> nowList = tradingMapperService.getVolumeAndValue(tradingRequest);

        //  封装12月前的时间条件
        String timeStart = tradingRequest.getTimeStart();
        String yearAgoMonthStart = DateUtils.getYearAgoMonth(timeStart);
        tradingRequest.setTimeStart(yearAgoMonthStart);
        String timeEnd = tradingRequest.getTimeEnd();
        String yearAgoMonthEnd = DateUtils.getYearAgoMonth(timeEnd);
        tradingRequest.setTimeEnd(yearAgoMonthEnd);

        List<TradingResponse> beforeList = tradingMapperService.getVolumeAndValue(tradingRequest);

        List list = new ArrayList();

        for (int i = 0; i < nowList.size(); i++) {
            Double countRate = 0.0;
            Double priceRate = 0.0;

            TradingResponse nowResponse = nowList.get(i);
            // 如果一年前以前没有数据
            if (beforeList.size() > 0) {
                TradingResponse beforeResponse = beforeList.get(i);
                //  交易总量同比增长率
                Double nowCount = nowResponse.getCount();
                Double beforeCount = beforeResponse.getCount();
                countRate = (nowCount - beforeCount) / beforeCount;
                //  交易总价值环比增长率
                Double nowPrice = nowResponse.getPrice();
                Double beforePrice = beforeResponse.getPrice();
                priceRate = (nowPrice - beforePrice) / beforePrice;
            }

            TradingResponse response = new TradingResponse();
            response.setMonth(nowResponse.getMonth());
            response.setPriceRate(priceRate);
            response.setCountRate(countRate);
            list.add(response);
        }
        return list;
    }

    @Override
    public Map<String, Object> getAvgPrice(TradingRequest tradingRequest) {
        //  平均交易价格   该月市场交易总价值/市场交易总量
        List<TradingResponse> list = tradingMapperService.getVolumeAndValue(tradingRequest);
        for (TradingResponse response : list) {
            Double price = response.getPrice();
            Double count = response.getCount();
            Double avgPrice = price / count;
            response.setAvgPrice((double) Math.round(avgPrice * 100) / 100);
        }

        //  平均交易价格=最近12个月每个月平均交易价格的平均数
        Double sumPrice = 0.0;
        Date date = new Date();
        Date monthEnd = DateUtils.getMonthEnd(date);
        Date dayEnd = DateUtils.getDayEnd(monthEnd);
        String nowTime = DateUtils.formatDate(dayEnd, DateUtils.DATE_FORMAT_DATEONLY);
        String yearAgoMonth = DateUtils.getYearAgoMonth(nowTime);

        tradingRequest.setTimeStart(yearAgoMonth);
        tradingRequest.setTimeEnd(nowTime);
        List<TradingResponse> avgList = tradingMapperService.getVolumeAndValue(tradingRequest);

        for (TradingResponse response : avgList) {
            Double price = response.getPrice();
            Double count = response.getCount();
            Double avgPrice = price / count;
            sumPrice += avgPrice;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("avg", (double) Math.round((sumPrice / 12) * 100) / 100);

        return map;

    }

    @Override
    public List<TradingResponse> getAvgPriceRate(TradingRequest tradingRequest) throws Exception {
        List<TradingResponse> list = tradingMapperService.getVolumeAndValue(tradingRequest);

        for (TradingResponse response : list) {
            // 当前月平均交易价格
            Double price = response.getPrice();
            Double count = response.getCount();
            Double avg = price / count;
            Double avgOneyear = 0.0;

            //  同比增长率
            String month = response.getMonth();
            month += "-01";
            String yearAgoMonth = DateUtils.getYearAgoMonth(month);
            Date date = DateUtils.parseDate(yearAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
            Date monthEnd = DateUtils.getMonthEnd(date);
            String s = DateUtils.formatDate(monthEnd, DateUtils.DATE_FORMAT_DATETIME);
            tradingRequest.setTimeStart(yearAgoMonth);
            tradingRequest.setTimeEnd(s);
            List<TradingResponse> oneYearlist = tradingMapperService.getVolumeAndValue(tradingRequest);
            if (oneYearlist.size() > 0) {
                TradingResponse responseOneYear = oneYearlist.get(0);
                Double price1 = responseOneYear.getPrice();
                Double count1 = responseOneYear.getCount();
                avgOneyear = price1 / count1;
            }
            Double avgPrice = (avg - avgOneyear) / (avgOneyear == 0 ? 1 : avgOneyear);
            avgPrice = (double) Math.round((avgPrice * 100)) / 100;
            response.setAvgPriceYear(avgPrice);

            //  环比增长
            Double avgUp = 0.0;
            String monthUp = DateUtils.getAgoMonth(month);
            Date dateUp = DateUtils.parseDate(monthUp, DateUtils.DATE_FORMAT_DATEONLY);
            Date monthUpEnd = DateUtils.getMonthEnd(dateUp);
            String up = DateUtils.formatDate(monthUpEnd, DateUtils.DATE_FORMAT_DATETIME);
            tradingRequest.setTimeStart(monthUp);
            tradingRequest.setTimeEnd(up);
            List<TradingResponse> oneMonthList = tradingMapperService.getVolumeAndValue(tradingRequest);
            if(oneMonthList.size() > 0){
                TradingResponse response1 = oneMonthList.get(0);
                Double priceUp = response1.getPrice();
                Double countUp = response1.getCount();
                avgUp = (priceUp / countUp);
            }

            Double avgPriceUp = (avg - avgUp) / (avgUp == 0 ? 1 : avgUp);
            avgUp = (double) Math.round((avgPriceUp * 100)) / 100;
            response.setAvgPriceMonth(avgUp);
        }
        return list;
    }

    @Override
    public Map<String, Double> getTenantCount(TradingRequest tradingRequest) {
        tradingRequest.setCarNumMin(0);
        tradingRequest.setCarNumMax(10);
        Double count = tradingMapperService.countCarNum(tradingRequest);
        Map<String,Double> map = new LinkedHashMap();
        map.put("0",count);

        tradingRequest.setCarNumMin(10);
        tradingRequest.setCarNumMax(20);
        count = tradingMapperService.countCarNum(tradingRequest);
        map.put("10",count);

        tradingRequest.setCarNumMin(20);
        tradingRequest.setCarNumMax(30);
        count = tradingMapperService.countCarNum(tradingRequest);
        map.put("20",count);

        tradingRequest.setCarNumMin(30);
        tradingRequest.setCarNumMax(40);
        count = tradingMapperService.countCarNum(tradingRequest);
        map.put("30",count);

        tradingRequest.setCarNumMin(40);
        tradingRequest.setCarNumMax(50);
        count = tradingMapperService.countCarNum(tradingRequest);
        map.put("40",count);

        tradingRequest.setCarNumMin(50);
        tradingRequest.setCarNumMax(0);
        count = tradingMapperService.countCarNum(tradingRequest);
        map.put("50",count);
        return map;

    }

    @Override
    public List<TradingResponse> getTenantDeal(TradingRequest tradingRequest) throws Exception {
        List<TradingResponse> list = tradingMapperService.getTenantMonthNum(tradingRequest);
        for(TradingResponse tr : list){
            String month = tr.getMonth();
            month += "-01";
            //  同比
            Double tenantCount = tr.getTenantCount();
            Double tenantCount1 = 0.0;
            String yearAgoMonth = DateUtils.getYearAgoMonth(month);
            Date date = DateUtils.parseDate(yearAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
            Date monthEnd = DateUtils.getMonthEnd(date);
            String s = DateUtils.formatDate(monthEnd,DateUtils.DATE_FORMAT_DATETIME);
            tradingRequest.setTimeStart(yearAgoMonth);
            tradingRequest.setTimeEnd(s);
            List<TradingResponse> oneYearlist = tradingMapperService.getTenantMonthNum(tradingRequest);
            if(oneYearlist.size() > 0){
                TradingResponse response = oneYearlist.get(0);
                tenantCount1 = response.getTenantCount();
            }
            Double yearRate = (tenantCount - tenantCount1) / (tenantCount1 == 0 ? 1 : tenantCount1);
            yearRate = (double) Math.round((yearRate * 100)) / 100;
            tr.setAvgPriceYear(yearRate);

            //  环比
            String monthAgoMonth = DateUtils.getYearAgoMonth(month);
            Date date1 = DateUtils.parseDate(monthAgoMonth, DateUtils.DATE_FORMAT_DATEONLY);
            Date monthEnd1 = DateUtils.getMonthEnd(date1);
            String s1 = DateUtils.formatDate(monthEnd1,DateUtils.DATE_FORMAT_DATETIME);
            tradingRequest.setTimeStart(monthAgoMonth);
            tradingRequest.setTimeEnd(s1);
            List<TradingResponse> oneMonthlist = tradingMapperService.getTenantMonthNum(tradingRequest);
            tenantCount1 = 0.0;
            if(oneYearlist.size() > 0){
                TradingResponse response = oneYearlist.get(0);
                tenantCount1 = response.getTenantCount();
            }
            Double MonthRate = (tenantCount - tenantCount1) / (tenantCount1 == 0 ? 1 : tenantCount1);
            MonthRate = (double) Math.round((MonthRate * 100)) / 100;
            tr.setAvgPriceMonth(MonthRate);
        }

        return list;
    }

    @Override
    public void getCarPrice(TradingRequest tradingRequest) {

    }
}
