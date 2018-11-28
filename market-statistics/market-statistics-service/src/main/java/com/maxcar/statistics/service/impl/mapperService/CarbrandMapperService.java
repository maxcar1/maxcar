package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.dao.CarbrandDayDao;
import com.maxcar.statistics.dao.CarbrandMonthDao;
import com.maxcar.statistics.dao.CartypeMonthDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.InsertTParamter;
import com.maxcar.statistics.model.request.GroupCarbrandInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceMonthRequest;
import com.maxcar.statistics.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarbrandMapperService {

    @Autowired
    private ReportMapperService reportMapperService;

    @Autowired
    private CarbrandDayDao carbrandDayDao;

    @Autowired
    private CarbrandMonthDao carbrandMonthDao;

    /**
     * param:
     * describe: 分组查询车辆品牌日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    public List<GroupCarbrandInvoiceDayResponse> groupCarbrandInvoiceDay(GroupCarbrandInvoiceDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return carbrandDayDao.groupCarbrandInvoiceDay(parameter);
    }


    /**
     * param:
     * describe: 根据结束日期查询 库存量与库存价值
     * create_date:  lxy   2018/11/26  14:28
     **/

    public List<GroupCarbrandInventoryDayResponse> groupCarbrandInventoryDay(GroupCarbrandInventoryDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("inventoryCount");
        }

        return carbrandDayDao.groupCarbrandInventoryDay(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆品牌月表 交易
     * create_date:  lxy   2018/11/26  15:04
     **/
    public List<GroupCarbrandInvoiceMonthResponse> groupCarbrandInvoiceMonth(GroupCarbrandInvoiceMonthRequest parameter){

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");
        }

        if (StringUtil.isNotEmpty(parameter.getBrandName())) {

            stringBuffer.append(" and brand_name = #{brandName}");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return carbrandMonthDao.groupCarbrandInvoiceMonth(parameter);
    }



    /**
     * param:
     * describe: 分组查询车辆品牌月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    public List<GroupCarbrandInventoryMonthResponse> groupCarbrandInventoryMonth(GroupCarbrandInventoryMonthRequest parameter){

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");
        }

        if (StringUtil.isNotEmpty(parameter.getBrandName())) {

            stringBuffer.append(" and brand_name = #{brandName}");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return carbrandMonthDao.groupCarbrandInventoryMonth(parameter);
    }




// 以下是车辆品牌数据插入

    /**
     * param:
     * describe: 按天批量插入车辆类型日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public void InsertCarbrandDay() {

        String dayTime = ToolDataUtils.getreportTimeByDay();

        GetCarInvoiceTypeInvoiceReportParameter parameter = new GetCarInvoiceTypeInvoiceReportParameter();
        parameter.setGroupByColumns("brandName");
        parameter.setStartTime(dayTime);
        parameter.setEndTime(dayTime);

        List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCarbrandDayList = reportMapperService.getCarInvoiceTypeInvoiceReport(parameter);

        if (null == InvoiceCarbrandDayList || InvoiceCarbrandDayList.isEmpty()) {
            return;
        }
        // 插入 交易信息
        carbrandDayDao.InsertT(getInsertCarbrandInvoiceDayColumnsAndValues(InvoiceCarbrandDayList));
        // 插入 库存信息

        return;
    }


    /**
     * param:
     * describe: 车辆品牌日表sql
     * create_date:  lxy   2018/11/26  10:41
     **/
    public InsertTParamter getInsertCarbrandInvoiceDayColumnsAndValues(List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCarbrandDayList) {

        InsertTParamter parameter = new InsertTParamter();
        parameter.setTable("`maxcar_statistics_l`.`carbrand_day`");
        parameter.setColumns("market_id,tenant_id,report_time,brand_name,sales_count,sales_price,sales_avg_price," +
                "male_count,female_count,age20_count,age25_count,age30_count,age35_count,age40_count,age45_count,age50_count");

        StringBuffer stringBuffer = new StringBuffer(128);


        return parameter;
    }
}
