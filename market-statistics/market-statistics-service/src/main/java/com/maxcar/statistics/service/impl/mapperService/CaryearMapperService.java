package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.CaryearDayDao;
import com.maxcar.statistics.dao.CaryearMonthDao;
import com.maxcar.statistics.model.request.GroupCaryearInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCaryearInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceMonthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaryearMapperService {

    @Autowired
    private CaryearDayDao caryearDayDao;

    @Autowired
    private CaryearMonthDao caryearMonthDao;


    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合 交易
     * create_date:  lxy   2018/11/28  10:23
     **/
    public List<GroupCaryearInvoiceDayResponse> groupCaryearInvoiceDay(GroupCaryearInvoiceDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return caryearDayDao.groupCaryearInvoiceDay(parameter);
    }

    /**
     * param:
     * describe: 统计当前的车辆年限日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    public List<GroupCaryearInventoryDayResponse> groupCaryearInventoryDay(GroupCaryearInventoryDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("inventoryCount");
        }

        return caryearDayDao.groupCaryearInventoryDay(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆年限月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    public List<GroupCaryearInvoiceMonthResponse> groupCaryearInvoiceMonth(GroupCaryearInvoiceMonthRequest parameter) {

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");
        }

        if (StringUtil.isNotEmpty(parameter.getYearId())) {

            stringBuffer.append(" and year_id = #{yearId}");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return caryearMonthDao.groupCaryearInvoiceMonth(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆年限月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    public List<GroupCaryearInventoryMonthResponse> groupCaryearInventoryMonth(GroupCaryearInventoryMonthRequest parameter) {

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");
        }

        if (StringUtil.isNotEmpty(parameter.getYearId())) {

            stringBuffer.append(" and year_id = #{yearId}");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return caryearMonthDao.groupCaryearInventoryMonth(parameter);
    }

// 以下是车辆年限数据插入


}
