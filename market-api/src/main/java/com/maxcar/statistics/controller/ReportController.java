package com.maxcar.statistics.controller;


import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.*;
import com.maxcar.statistics.service.ReportByCarbrandService;
import com.maxcar.statistics.service.ReportByCartypeService;
import com.maxcar.statistics.service.ReportByCaryearService;
import com.maxcar.statistics.service.ReportCarstocktimeService;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController extends BaseController {

    @Autowired
    private ReportByCartypeService reportByCartypeService;

    @Autowired
    private ReportByCarbrandService reportByCarbrandService;

    @Autowired
    private ReportByCaryearService reportByCaryearService;

    @Autowired
    private ReportCarstocktimeService reportCarstocktimeService;

    // 以下是车辆类型

    /**
     * param:
     * describe: 车辆类型统计 根据车辆类型划分交易量与占比 --> 交易量 交易价值
     * create_date:  lxy   2018/11/19  10:18
     **/
    @RequestMapping("/report/groupCartypeDay")
    public InterfaceResult groupCartypeDay(@RequestBody @Valid GroupCartypeDayRequest groupCartypeDayRequest,
                                           BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCartypeDayRequest.setMarketId(user.getMarketId());
        }


        return getInterfaceResult("200", reportByCartypeService.groupCartypeDay(groupCartypeDayRequest));
    }


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    @RequestMapping("/report/groupCartypeMonth")
    public InterfaceResult groupCartypeMonth(@RequestBody @Valid GroupCartypeMonthRequest groupCartypeMonthRequest,
                                             BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCartypeMonthRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCartypeService.groupCartypeMonth(groupCartypeMonthRequest));
    }

    /**
     * param:
     * describe: 车辆类型交易价格增长率
     * create_date:  lxy   2018/12/3  14:57
     **/
    @RequestMapping("/report/growthCartypeByMonth")
    public InterfaceResult growthCartypeByMonth(@RequestBody @Valid GroupCartypeMonthRequest groupCartypeMonthRequest,
                                                BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCartypeMonthRequest.setMarketId(user.getMarketId());
        }

        GrowthByMonthResponse growthByMonthResponse = new GrowthByMonthResponse();

        List<GroupCartypeMonthResponse> thisYear = reportByCartypeService.groupCartypeMonth(groupCartypeMonthRequest);

        groupCartypeMonthRequest.setStartTime(ToolDataUtils.getLastYear(groupCartypeMonthRequest.getStartTime()));
        groupCartypeMonthRequest.setEndTime(ToolDataUtils.getLastYear(groupCartypeMonthRequest.getEndTime()));

        List<GroupCartypeMonthResponse> lastYear = reportByCartypeService.groupCartypeMonth(groupCartypeMonthRequest);

        List<GrowthByMonthYearOnYearPack> yearOnYearPacks = new ArrayList<>(thisYear.size());

        List<GrowthByMonthSequentialPack> sequentialPacks = new ArrayList<>(thisYear.size());


        for (int i = 0; i < thisYear.size(); i++) {

            // 同比增长率
            GrowthByMonthYearOnYearPack growthByMonthYearOnYearPack = new GrowthByMonthYearOnYearPack();

            GroupCartypeMonthResponse thisResponse = thisYear.get(i);

            Double thisResponseAvgPrice = ToolDataUtils.getAvgPrice(thisResponse.getInvoicePrice(), thisResponse.getInvoiceCount());

            GroupCartypeMonthResponse lastResponse = lastYear.get(i);

            Double lastResponseAvgPrice = ToolDataUtils.getAvgPrice(lastResponse.getInvoicePrice(), lastResponse.getInvoiceCount());

            if (0 != lastResponseAvgPrice) {

                Double grow = (thisResponseAvgPrice - lastResponseAvgPrice) / lastResponseAvgPrice * 100;

                growthByMonthYearOnYearPack.setMonth(thisResponse.getNumTime());
                growthByMonthYearOnYearPack.setGrowthByYearOnYear(grow + "%");

                yearOnYearPacks.add(growthByMonthYearOnYearPack);
            }
            // 环比增长率
            GrowthByMonthSequentialPack growthByMonthSequentialPack = new GrowthByMonthSequentialPack();

            if (i > 0) {

                Double lastMonthResponseAvgPrice = ToolDataUtils.getAvgPrice(lastYear.get(i - 1).getInvoicePrice(), lastYear.get(i - 1).getInvoiceCount());

                if (0 != lastMonthResponseAvgPrice) {

                    Double grow = (thisResponseAvgPrice - lastMonthResponseAvgPrice) / lastMonthResponseAvgPrice * 100;

                    growthByMonthSequentialPack.setMonth(thisResponse.getNumTime());
                    growthByMonthSequentialPack.setGrowthBySequential(grow + "%");

                    sequentialPacks.add(growthByMonthSequentialPack);
                }
            }

        }

        growthByMonthResponse.setGrowthByMonthYearOnYearPacks(yearOnYearPacks);
        growthByMonthResponse.setGrowthByMonthSequentialPacks(sequentialPacks);

        return getInterfaceResult("200", growthByMonthResponse);
    }


    // 以下是车辆品牌


    /**
     * param:
     * describe: 查询市场 或者 商户 车辆品牌集合
     * create_date:  lxy   2018/12/1  11:15
     **/
    @RequestMapping("/report/getAllBrandName")
    public InterfaceResult getAllBrandName(@RequestBody @Valid GetAllBrandNameRequest getAllBrandNameRequest,
                                           BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            getAllBrandNameRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCarbrandService.getAllBrandName(getAllBrandNameRequest));
    }


    /**
     * param:
     * describe: 分组查询车辆品牌日表 交易
     * create_date:  lxy   2018/11/22  17:13
     **/
    @RequestMapping("/report/groupCarbrandInvoiceDay")
    public InterfaceResult groupCarbrandInvoiceDay(@RequestBody @Valid GroupCarbrandInvoiceDayRequest groupCarbrandInvoiceDayRequest,
                                                   BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCarbrandInvoiceDayRequest.setMarketId(user.getMarketId());
        }

        List<GroupCarbrandInvoiceDayResponse> list = reportByCarbrandService.groupCarbrandInvoiceDay(groupCarbrandInvoiceDayRequest);

        Integer count = 0;
        Double price = 0.0;

        if (tIsNotEmpty(list)) {
            count = list.stream().filter(x -> tIsNotEmpty(x.getInvoiceCount())).mapToInt(GroupCarbrandInvoiceDayResponse::getInvoiceCount).sum();
            price = list.stream().filter(x -> tIsNotEmpty(x.getInvoicePrice())).mapToDouble(GroupCarbrandInvoiceDayResponse::getInvoicePrice).sum();
        }

        JSONObject json = new JSONObject();

        json.put("list", list);
        json.put("count", count);
        json.put("price", df.format(price));

        return getInterfaceResult("200",json);
    }


    /**
     * param:
     * describe: 根据结束日期查询 库存量与库存价值
     * create_date:  lxy   2018/11/26  14:28
     **/
    @RequestMapping("/report/groupCarbrandInventoryDay")
    public InterfaceResult groupCarbrandInventoryDay(@RequestBody @Valid GroupCarbrandInventoryDayRequest groupCarbrandInventoryDayRequest,
                                                     BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCarbrandInventoryDayRequest.setMarketId(user.getMarketId());
        }

        List<GroupCarbrandInventoryDayResponse> list = reportByCarbrandService.groupCarbrandInventoryDay(groupCarbrandInventoryDayRequest);

        Integer count = 0;
        Double price = 0.0;

        if (tIsNotEmpty(list)) {
            count = list.stream().filter(x -> tIsNotEmpty(x.getInventoryCount())).mapToInt(GroupCarbrandInventoryDayResponse::getInventoryCount).sum();
            price = list.stream().filter(x -> tIsNotEmpty(x.getInventoryPrice())).mapToDouble(GroupCarbrandInventoryDayResponse::getInventoryPrice).sum();
        }

        JSONObject json = new JSONObject();

        json.put("list", list);
        json.put("count", count);
        json.put("price", df.format(price));

        return getInterfaceResult("200", json);
    }


    /**
     * param:
     * describe: 分组查询车辆品牌月表 交易
     * create_date:  lxy   2018/11/26  15:04
     **/
    @RequestMapping("/report/groupCarbrandInvoiceMonth")
    public InterfaceResult groupCarbrandInvoiceMonth(@RequestBody @Valid GroupCarbrandInvoiceMonthRequest groupCarbrandInvoiceMonthRequest,
                                                     BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCarbrandInvoiceMonthRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCarbrandService.groupCarbrandInvoiceMonth(groupCarbrandInvoiceMonthRequest));

    }

    /**
     * param:
     * describe: 车辆品牌交易价格增长率
     * create_date:  lxy   2018/12/3  14:57
     **/
    @RequestMapping("/report/growthCarbrandByMonth")
    public InterfaceResult growthCarbrandByMonth(@RequestBody @Valid GroupCarbrandInvoiceMonthRequest groupCarbrandInvoiceMonthRequest,
                                                 BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCarbrandInvoiceMonthRequest.setMarketId(user.getMarketId());
        }

        GrowthByMonthResponse growthByMonthResponse = new GrowthByMonthResponse();

        List<GroupCarbrandInvoiceMonthResponse> thisYear = reportByCarbrandService.groupCarbrandInvoiceMonth(groupCarbrandInvoiceMonthRequest);

        groupCarbrandInvoiceMonthRequest.setStartTime(ToolDataUtils.getLastYear(groupCarbrandInvoiceMonthRequest.getStartTime()));
        groupCarbrandInvoiceMonthRequest.setEndTime(ToolDataUtils.getLastYear(groupCarbrandInvoiceMonthRequest.getEndTime()));

        List<GroupCarbrandInvoiceMonthResponse> lastYear = reportByCarbrandService.groupCarbrandInvoiceMonth(groupCarbrandInvoiceMonthRequest);

        List<GrowthByMonthYearOnYearPack> yearOnYearPacks = new ArrayList<>(thisYear.size());

        List<GrowthByMonthSequentialPack> sequentialPacks = new ArrayList<>(thisYear.size());


        for (int i = 0; i < thisYear.size(); i++) {

            // 同比增长率
            GrowthByMonthYearOnYearPack growthByMonthYearOnYearPack = new GrowthByMonthYearOnYearPack();

            GroupCarbrandInvoiceMonthResponse thisResponse = thisYear.get(i);

            Double thisResponseAvgPrice = ToolDataUtils.getAvgPrice(thisResponse.getInvoicePrice(), thisResponse.getInvoiceCount());

            GroupCarbrandInvoiceMonthResponse lastResponse = lastYear.get(i);

            Double lastResponseAvgPrice = ToolDataUtils.getAvgPrice(lastResponse.getInvoicePrice(), lastResponse.getInvoiceCount());

            if (0 != lastResponseAvgPrice) {

                Double grow = (thisResponseAvgPrice - lastResponseAvgPrice) / lastResponseAvgPrice * 100;

                growthByMonthYearOnYearPack.setMonth(thisResponse.getNumTime());
                growthByMonthYearOnYearPack.setGrowthByYearOnYear(grow + "%");

                yearOnYearPacks.add(growthByMonthYearOnYearPack);
            }

            // 环比增长率
            GrowthByMonthSequentialPack growthByMonthSequentialPack = new GrowthByMonthSequentialPack();

            if (i > 0) {

                Double lastMonthResponseAvgPrice = ToolDataUtils.getAvgPrice(lastYear.get(i - 1).getInvoicePrice(), lastYear.get(i - 1).getInvoiceCount());

                if (0 != lastMonthResponseAvgPrice) {

                    Double grow = (thisResponseAvgPrice - lastMonthResponseAvgPrice) / lastMonthResponseAvgPrice * 100;

                    growthByMonthSequentialPack.setMonth(thisResponse.getNumTime());
                    growthByMonthSequentialPack.setGrowthBySequential(grow + "%");

                    sequentialPacks.add(growthByMonthSequentialPack);
                }
            }
        }


        growthByMonthResponse.setGrowthByMonthYearOnYearPacks(yearOnYearPacks);
        growthByMonthResponse.setGrowthByMonthSequentialPacks(sequentialPacks);

        return getInterfaceResult("200", growthByMonthResponse);
    }


    /**
     * param:
     * describe: 分组查询车辆品牌月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @RequestMapping("/report/groupCarbrandInventoryMonth")
    public InterfaceResult groupCarbrandInventoryMonth(@RequestBody @Valid GroupCarbrandInventoryMonthRequest groupCarbrandInventoryMonthRequest,
                                                       BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCarbrandInventoryMonthRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCarbrandService.groupCarbrandInventoryMonth(groupCarbrandInventoryMonthRequest));

    }

    // 以下是车辆年限

    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合 交易
     * create_date:  lxy   2018/11/28  10:23
     **/
    @RequestMapping("/report/groupCaryearInvoiceDay")
    public InterfaceResult groupCaryearInvoiceDay(@RequestBody @Valid GroupCaryearInvoiceDayRequest parameter,
                                                  BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCaryearService.groupCaryearInvoiceDay(parameter));

    }

    /**
     * param:
     * describe: 统计当前的车辆年限日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @RequestMapping("/report/groupCaryearInventoryDay")
    public InterfaceResult groupCaryearInventoryDay(@RequestBody @Valid GroupCaryearInventoryDayRequest parameter,
                                                    BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }
        return getInterfaceResult("200", reportByCaryearService.groupCaryearInventoryDay(parameter));
    }


    /**
     * param:
     * describe: 分组查询车辆年限月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @PostMapping("/report/groupCaryearInvoiceMonth")
    public InterfaceResult groupCaryearInvoiceMonth(@RequestBody @Valid GroupCaryearInvoiceMonthRequest parameter,
                                                    BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCaryearService.groupCaryearInvoiceMonth(parameter));
    }

    /**
     * param:
     * describe: 车辆年限交易价格增长率
     * create_date:  lxy   2018/12/3  14:57
     **/
    @RequestMapping("/report/growthCaryearByMonth")
    public InterfaceResult growthCaryearByMonth(@RequestBody @Valid GroupCaryearInvoiceMonthRequest parameter,
                                                BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }

        GrowthByMonthResponse growthByMonthResponse = new GrowthByMonthResponse();

        List<GroupCaryearInvoiceMonthResponse> thisYear = reportByCaryearService.groupCaryearInvoiceMonth(parameter);

        parameter.setStartTime(ToolDataUtils.getLastYear(parameter.getStartTime()));
        parameter.setEndTime(ToolDataUtils.getLastYear(parameter.getEndTime()));

        List<GroupCaryearInvoiceMonthResponse> lastYear = reportByCaryearService.groupCaryearInvoiceMonth(parameter);

        List<GrowthByMonthYearOnYearPack> yearOnYearPacks = new ArrayList<>(thisYear.size());

        List<GrowthByMonthSequentialPack> sequentialPacks = new ArrayList<>(thisYear.size());


        for (int i = 0; i < thisYear.size(); i++) {

            // 同比增长率
            GrowthByMonthYearOnYearPack growthByMonthYearOnYearPack = new GrowthByMonthYearOnYearPack();

            GroupCaryearInvoiceMonthResponse thisResponse = thisYear.get(i);

            Double thisResponseAvgPrice = ToolDataUtils.getAvgPrice(thisResponse.getInvoicePrice(), thisResponse.getInvoiceCount());

            GroupCaryearInvoiceMonthResponse lastResponse = lastYear.get(i);

            Double lastResponseAvgPrice = ToolDataUtils.getAvgPrice(lastResponse.getInvoicePrice(), lastResponse.getInvoiceCount());

            if (0 != lastResponseAvgPrice) {

                Double grow = (thisResponseAvgPrice - lastResponseAvgPrice) / lastResponseAvgPrice * 100;

                growthByMonthYearOnYearPack.setMonth(thisResponse.getNumTime());
                growthByMonthYearOnYearPack.setGrowthByYearOnYear(grow + "%");

                yearOnYearPacks.add(growthByMonthYearOnYearPack);
            }

            // 环比增长率
            GrowthByMonthSequentialPack growthByMonthSequentialPack = new GrowthByMonthSequentialPack();

            if (i > 0) {

                Double lastMonthResponseAvgPrice = ToolDataUtils.getAvgPrice(lastYear.get(i - 1).getInvoicePrice(), lastYear.get(i - 1).getInvoiceCount());

                if (0 != lastMonthResponseAvgPrice) {

                    Double grow = (thisResponseAvgPrice - lastMonthResponseAvgPrice) / lastMonthResponseAvgPrice * 100;

                    growthByMonthSequentialPack.setMonth(thisResponse.getNumTime());
                    growthByMonthSequentialPack.setGrowthBySequential(grow + "%");

                    sequentialPacks.add(growthByMonthSequentialPack);
                }
            }
        }


        growthByMonthResponse.setGrowthByMonthYearOnYearPacks(yearOnYearPacks);
        growthByMonthResponse.setGrowthByMonthSequentialPacks(sequentialPacks);

        return getInterfaceResult("200", growthByMonthResponse);
    }


    /**
     * param:
     * describe: 分组查询车辆年限月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @PostMapping("/report/groupCaryearInventoryMonth")
    public InterfaceResult groupCaryearInventoryMonth(@RequestBody @Valid GroupCaryearInventoryMonthRequest parameter,
                                                      BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCaryearService.groupCaryearInventoryMonth(parameter));
    }

    // 以下是车辆库存时长

    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合 交易
     * create_date:  lxy   2018/11/28  10:24
     **/
    @PostMapping("/report/groupCarstocktimeInvoiceDay")
    public InterfaceResult groupCarstocktimeInvoiceDay(@RequestBody @Valid GroupCarstocktimeInvoiceDayRequest parameter,
                                                       BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }
        return getInterfaceResult("200", reportCarstocktimeService.groupCarstocktimeInvoiceDay(parameter));

    }

    /**
     * param:
     * describe: 统计当前的车辆库存天数日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @PostMapping("/report/groupCarstocktimeInventoryDay")
    public InterfaceResult groupCarstocktimeInventoryDay(@RequestBody @Valid GroupCarstocktimeInventoryDayRequest parameter,
                                                         BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }
        return getInterfaceResult("200", reportCarstocktimeService.groupCarstocktimeInventoryDay(parameter));

    }

    /**
     * param:
     * describe: 分组查询车辆库存时长月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @PostMapping("/report/groupCarstocktimeInvoiceMonth")
    public InterfaceResult groupCarstocktimeInvoiceMonth(@RequestBody @Valid GroupCarstocktimeInvoiceMonthRequest parameter,
                                                         BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportCarstocktimeService.groupCarstocktimeInvoiceMonth(parameter));

    }

    /**
     * param:
     * describe: 车辆库存时长交易价格增长率
     * create_date:  lxy   2018/12/3  14:57
     **/
    @RequestMapping("/report/growthCarstocktimeByMonth")
    public InterfaceResult growthCarstocktimeByMonth(@RequestBody @Valid GroupCarstocktimeInvoiceMonthRequest parameter,
                                                     BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }

        GrowthByMonthResponse growthByMonthResponse = new GrowthByMonthResponse();

        List<GroupCarstocktimeInvoiceMonthResponse> thisYear = reportCarstocktimeService.groupCarstocktimeInvoiceMonth(parameter);

        parameter.setStartTime(ToolDataUtils.getLastYear(parameter.getStartTime()));
        parameter.setEndTime(ToolDataUtils.getLastYear(parameter.getEndTime()));

        List<GroupCarstocktimeInvoiceMonthResponse> lastYear = reportCarstocktimeService.groupCarstocktimeInvoiceMonth(parameter);

        List<GrowthByMonthYearOnYearPack> yearOnYearPacks = new ArrayList<>(thisYear.size());

        List<GrowthByMonthSequentialPack> sequentialPacks = new ArrayList<>(thisYear.size());


        for (int i = 0; i < thisYear.size(); i++) {

            // 同比增长率
            GrowthByMonthYearOnYearPack growthByMonthYearOnYearPack = new GrowthByMonthYearOnYearPack();

            GroupCarstocktimeInvoiceMonthResponse thisResponse = thisYear.get(i);

            Double thisResponseAvgPrice = ToolDataUtils.getAvgPrice(thisResponse.getInvoicePrice(), thisResponse.getInvoiceCount());

            GroupCarstocktimeInvoiceMonthResponse lastResponse = lastYear.get(i);

            Double lastResponseAvgPrice = ToolDataUtils.getAvgPrice(lastResponse.getInvoicePrice(), lastResponse.getInvoiceCount());

            if (0 != lastResponseAvgPrice) {

                Double grow = (thisResponseAvgPrice - lastResponseAvgPrice) / lastResponseAvgPrice * 100;

                growthByMonthYearOnYearPack.setMonth(thisResponse.getNumTime());
                growthByMonthYearOnYearPack.setGrowthByYearOnYear(grow + "%");

                yearOnYearPacks.add(growthByMonthYearOnYearPack);
            }

            // 环比增长率
            GrowthByMonthSequentialPack growthByMonthSequentialPack = new GrowthByMonthSequentialPack();

            if (i > 0) {

                Double lastMonthResponseAvgPrice = ToolDataUtils.getAvgPrice(lastYear.get(i - 1).getInvoicePrice(), lastYear.get(i - 1).getInvoiceCount());

                if (0 != lastMonthResponseAvgPrice) {

                    Double grow = (thisResponseAvgPrice - lastMonthResponseAvgPrice) / lastMonthResponseAvgPrice * 100;

                    growthByMonthSequentialPack.setMonth(thisResponse.getNumTime());
                    growthByMonthSequentialPack.setGrowthBySequential(grow + "%");

                    sequentialPacks.add(growthByMonthSequentialPack);
                }
            }
        }


        growthByMonthResponse.setGrowthByMonthYearOnYearPacks(yearOnYearPacks);
        growthByMonthResponse.setGrowthByMonthSequentialPacks(sequentialPacks);

        return getInterfaceResult("200", growthByMonthResponse);
    }

    /**
     * param:
     * describe: 分组查询车辆库存时长月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @PostMapping("/report/groupCarstocktimeInventoryMonth")
    public InterfaceResult groupCarstocktimeInventoryMonth(@RequestBody @Valid GroupCarstocktimeInventoryMonthRequest parameter,
                                                           BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            parameter.setMarketId(user.getMarketId());
        }
        return getInterfaceResult("200", reportCarstocktimeService.groupCarstocktimeInventoryMonth(parameter));
    }

}
