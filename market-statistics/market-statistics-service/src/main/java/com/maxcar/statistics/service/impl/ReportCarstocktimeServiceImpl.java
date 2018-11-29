package com.maxcar.statistics.service.impl;

import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceMonthResponse;
import com.maxcar.statistics.service.ReportCarstocktimeService;
import com.maxcar.statistics.service.impl.mapperService.CarstocktimeMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportCarstocktimeService")
public class ReportCarstocktimeServiceImpl  implements ReportCarstocktimeService {

    @Autowired
    private CarstocktimeMapperService carstocktimeMapperService;

    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合 交易
     * create_date:  lxy   2018/11/28  10:24
     **/
    @Override
    public List<GroupCarstocktimeInvoiceDayResponse> groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter){

        return carstocktimeMapperService.groupCarstocktimeInvoiceDay(parameter);
    }

    /**
     * param:
     * describe: 统计当前的车辆库存天数日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @Override
    public  List<GroupCarstocktimeInventoryDayResponse> groupCarstocktimeInventoryDay(GroupCarstocktimeInventoryDayRequest parameter){

        return carstocktimeMapperService.groupCarstocktimeInventoryDay(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆库存时长月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @Override
    public List<GroupCarstocktimeInvoiceMonthResponse> groupCarstocktimeInvoiceMonth(GroupCarstocktimeInvoiceMonthRequest parameter){

        return carstocktimeMapperService.groupCarstocktimeInvoiceMonth(parameter);
    }

    /**
     * param:
     * describe: 分组查询车辆库存时长月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @Override
    public List<GroupCarstocktimeInventoryMonthResponse> groupCarstocktimeInventoryMonth(GroupCarstocktimeInventoryMonthRequest parameter){

        return carstocktimeMapperService.groupCarstocktimeInventoryMonth(parameter);
    }

}
