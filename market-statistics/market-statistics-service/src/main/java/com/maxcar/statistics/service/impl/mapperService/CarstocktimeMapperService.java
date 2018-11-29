package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.CarstocktimeDayDao;
import com.maxcar.statistics.dao.CarstocktimeMonthDao;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceMonthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarstocktimeMapperService {

    @Autowired
    private CarstocktimeDayDao carstocktimeDayDao;

    @Autowired
    private CarstocktimeMonthDao carstocktimeMonthDao;


    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合 交易
     * create_date:  lxy   2018/11/28  10:24
     **/
    public List<GroupCarstocktimeInvoiceDayResponse> groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter){

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return carstocktimeDayDao.groupCarstocktimeInvoiceDay(parameter);
    }

    /**
     * param:
     * describe: 统计当前的车辆库存天数日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    public  List<GroupCarstocktimeInventoryDayResponse> groupCarstocktimeInventoryDay(GroupCarstocktimeInventoryDayRequest parameter){

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("inventoryCount");
        }

        return carstocktimeDayDao.groupCarstocktimeInventoryDay(parameter);
    }



    /**
     * param:
     * describe: 分组查询车辆库存时长月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    public List<GroupCarstocktimeInvoiceMonthResponse> groupCarstocktimeInvoiceMonth(GroupCarstocktimeInvoiceMonthRequest parameter){

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");
        }

        if (StringUtil.isNotEmpty(parameter.getStocktimeId())) {

            stringBuffer.append(" and stocktime_id = #{stocktimeId}");
        }

        parameter.setSelectCondition(stringBuffer.toString());



        return carstocktimeMonthDao.groupCarstocktimeInvoiceMonth(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆库存时长月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    public List<GroupCarstocktimeInventoryMonthResponse> groupCarstocktimeInventoryMonth(GroupCarstocktimeInventoryMonthRequest parameter){

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");
        }

        if (StringUtil.isNotEmpty(parameter.getStocktimeId())) {

            stringBuffer.append(" and stocktime_id = #{stocktimeId}");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return carstocktimeMonthDao.groupCarstocktimeInventoryMonth(parameter);
    }
}
