package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.dao.CaryearDayDao;
import com.maxcar.statistics.dao.CaryearMonthDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.parameter.InsertTParamter;
import com.maxcar.statistics.model.request.GroupCaryearInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceMonthRequest;
import com.maxcar.statistics.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaryearMapperService {

    @Autowired
    private ReportMapperService reportMapperService;

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
            parameter.setOrderBy("yearId");
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
            parameter.setOrderBy("yearId");
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

    /**
     * param:
     * describe: 按天批量插入车辆年限日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public void InsertCaryearDay() throws Exception {

        // 睡眠10秒后执行 执行操作
        Thread.sleep(10000);
        String dayTime = ToolDataUtils.getreportTimeByDay();

        GetCarInvoiceTypeInvoiceReportParameter getCarInvoiceTypeInvoiceReportParameter = new GetCarInvoiceTypeInvoiceReportParameter();
        getCarInvoiceTypeInvoiceReportParameter.setStartTime(dayTime);
        getCarInvoiceTypeInvoiceReportParameter.setEndTime(dayTime);

        // 循环查询指定年限的交易数据
        for (int i = 1; i <= 5; i++) {

            getCarInvoiceTypeInvoiceReportParameter.setGroupByColumns("caryear");
            getCarInvoiceTypeInvoiceReportParameter.setAgeByCar(i);


            List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCaryearDayList = reportMapperService.getCarInvoiceTypeInvoiceReport(getCarInvoiceTypeInvoiceReportParameter);

            if (null != InvoiceCaryearDayList && !InvoiceCaryearDayList.isEmpty()) {
                // 插入 交易信息
                caryearDayDao.InsertT(getInsertCaryearInvoiceDayColumnsAndValues(InvoiceCaryearDayList, i));
            }

        }

        // 睡眠10秒后执行 执行操作
        Thread.sleep(10000);

        GetInventoryReportParameter getInventoryReportParameter = new GetInventoryReportParameter();

        getInventoryReportParameter.setEndTime(ToolDataUtils.getreportTimeByDay());

        for (int i = 1; i <= 5; i++) {

            getInventoryReportParameter.setGroupByColumns("caryear");
            getInventoryReportParameter.setAgeByCar(i);

            List<GetInventoryReportResponse> inventoryCarbrandDayList = reportMapperService.getInventoryReport(getInventoryReportParameter);

            if (null != inventoryCarbrandDayList && !inventoryCarbrandDayList.isEmpty()) {
                // 插入 库存信息
                caryearDayDao.InsertT(getInsertCaryearInventoryDayColumnsAndValues(inventoryCarbrandDayList, i));
            }
        }
        return;
    }


    /**
     * param:
     * describe: 车辆年限日表交易sql
     * create_date:  lxy   2018/11/26  10:41
     **/
    private InsertTParamter getInsertCaryearInvoiceDayColumnsAndValues(List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCarbrandDayList, Integer yearId) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`cartype_day`");
        parameter.setColumns("market_id,tenant_id,report_time,year_id,year_name,sales_count,sales_price,sales_avg_price," +
                "male_count,female_count,age20_count,age25_count,age30_count,age35_count,age40_count,age45_count,age50_count");

        StringBuffer stringBuffer = new StringBuffer(128);

        for (GetCarInvoiceTypeInvoiceReportResponse invoice : InvoiceCarbrandDayList) {
            stringBuffer.append("(");

            stringBuffer.append("'");
            stringBuffer.append(invoice.getMarketId());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getTenantId());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(ToolDataUtils.getreportTimeByDay());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(ToolDataUtils.getreportTimeByDay());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(yearId);
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(BaseMapperService.getNameByAgeByCarString(yearId));
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(invoice.getInvoiceCount());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(invoice.getInvoicePrice());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(ToolDataUtils.getAvgPrice(invoice.getInvoicePrice(), invoice.getInvoiceCount()));
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getMaleCount());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getFemaleCount());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge20Count());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge25Count());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge30Count());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge35Count());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge40Count());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge45Count());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getAge50Count());
            stringBuffer.append("'");


            stringBuffer.append("),");
        }

        String values = stringBuffer.toString();

        parameter.setValues(values.substring(1, values.length() - 2));


        String onUpdate = "ON DUPLICATE KEY  UPDATE \n" +
                "year_name =  VALUES (year_name),\n" +
                "sales_count =  VALUES (sales_count),\n" +
                "sales_price =  VALUES (sales_price),\n" +
                "sales_avg_price =  VALUES (sales_avg_price),\n" +
                "male_count =  VALUES(male_count),\n" +
                "female_count = VALUES (female_count),\n" +
                "age20_count = VALUES(age20_count),\n" +
                "age25_count =  VALUES (age25_count),\n" +
                "age30_count =  VALUES (age30_count),\n" +
                "age35_count = VALUES (age35_count),\n" +
                "age40_count = VALUES (age40_count),\n" +
                "age45_count =VALUES (age45_count),\n" +
                "age50_count =VALUES (age50_count);\n";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }


    /**
     * param:
     * describe:  车辆年限日表库存sql
     * create_date:  lxy   2018/12/4  16:38
     **/
    private InsertTParamter getInsertCaryearInventoryDayColumnsAndValues(List<GetInventoryReportResponse> inventoryCarbrandDayList, Integer yearId) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`caryear_day`");
        parameter.setColumns("market_id,tenant_id,report_time,year_id,year_name,stock_count,stock_price");

        StringBuffer stringBuffer = new StringBuffer(128);

        for (GetInventoryReportResponse inventory : inventoryCarbrandDayList) {
            stringBuffer.append("(");

            stringBuffer.append("'");
            stringBuffer.append(inventory.getMarketId());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(inventory.getTenantId());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(ToolDataUtils.getreportTimeByDay());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(yearId);
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(BaseMapperService.getNameByAgeByCarString(yearId));
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(inventory.getInventoryCount());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(inventory.getInventoryPrice());
            stringBuffer.append("'");

            stringBuffer.append("),");
        }

        String values = stringBuffer.toString();

        parameter.setValues(values.substring(1, values.length() - 2));


        String onUpdate = "ON DUPLICATE KEY  UPDATE \n" +
                "year_name =  VALUES (year_name),\n" +
                "stock_count =  VALUES (stock_count),\n" +
                "stock_price =  VALUES (stock_price);";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }


}
