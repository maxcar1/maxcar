package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.GroupCaryearInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCaryearInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceMonthResponse;
import com.maxcar.statistics.service.ReportByCaryearService;
import com.maxcar.statistics.service.impl.mapperService.CaryearMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportByCaryearService")
public class ReportByCaryearServiceImpl implements ReportByCaryearService {

    @Autowired
    private CaryearMapperService caryearMapperService;


    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合 交易
     * create_date:  lxy   2018/11/28  10:23
     **/
    @Override
    public List<GroupCaryearInvoiceDayResponse> groupCaryearInvoiceDay(GroupCaryearInvoiceDayRequest parameter) {

        return caryearMapperService.groupCaryearInvoiceDay(parameter);
    }

    /**
     * param:
     * describe: 统计当前的车辆年限日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @Override
    public List<GroupCaryearInventoryDayResponse> groupCaryearInventoryDay(GroupCaryearInventoryDayRequest parameter) {

        return caryearMapperService.groupCaryearInventoryDay(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆年限月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @Override
    public List<GroupCaryearInvoiceMonthResponse> groupCaryearInvoiceMonth(GroupCaryearInvoiceMonthRequest parameter) {

        return caryearMapperService.groupCaryearInvoiceMonth(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆年限月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @Override
    public List<GroupCaryearInventoryMonthResponse> groupCaryearInventoryMonth(GroupCaryearInventoryMonthRequest parameter) {

        return caryearMapperService.groupCaryearInventoryMonth(parameter);
    }

}
