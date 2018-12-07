package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.dao.CarbrandDayDao;
import com.maxcar.statistics.dao.CarbrandMonthDao;
import com.maxcar.statistics.dao.CartypeMonthDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.parameter.InsertTParamter;
import com.maxcar.statistics.model.request.*;
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
     * describe: 查询市场 或者 商户 车辆品牌集合
     * create_date:  lxy   2018/12/1  11:15
     **/
    public List<String> getAllBrandName(GetAllBrandNameRequest request) {

        return carbrandDayDao.getAllBrandName(request);
    }

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
    public List<GroupCarbrandInvoiceMonthResponse> groupCarbrandInvoiceMonth(GroupCarbrandInvoiceMonthRequest parameter) {

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
    public List<GroupCarbrandInventoryMonthResponse> groupCarbrandInventoryMonth(GroupCarbrandInventoryMonthRequest parameter) {

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

    public void InserteCarbrand() throws Exception {
        // 睡眠 5秒 后执行 执行操作
        Thread.sleep(5000);
        InserteCarbrandDay();

        // 睡眠 5秒 后执行 执行操作
        Thread.sleep(5000);
        InserteCarbrandMonth();
    }

    /**
     * param:
     * describe: 批量插入车辆类型日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public void InserteCarbrandDay() throws Exception {

        String dayTime = ToolDataUtils.getreportTimeByDay();

        GetCarInvoiceTypeInvoiceReportParameter getCarInvoiceTypeInvoiceReportParameter = new GetCarInvoiceTypeInvoiceReportParameter();
        getCarInvoiceTypeInvoiceReportParameter.setGroupByColumns("brandName");
        getCarInvoiceTypeInvoiceReportParameter.setStartTime(dayTime);
        getCarInvoiceTypeInvoiceReportParameter.setEndTime(dayTime);

        List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCarbrandDayList = reportMapperService.getCarInvoiceTypeInvoiceReport(getCarInvoiceTypeInvoiceReportParameter);

        if (null != InvoiceCarbrandDayList && !InvoiceCarbrandDayList.isEmpty()) {

            // 插入 交易信息
            carbrandDayDao.InsertT(getInsertCarbrandInvoiceDayColumnsAndValues(InvoiceCarbrandDayList));
        }


        // 睡眠5秒 后执行 执行操作
        Thread.sleep(5000);

        GetInventoryReportParameter getInventoryReportParameter = new GetInventoryReportParameter();

        getInventoryReportParameter.setGroupByColumns("brandName");
        getInventoryReportParameter.setEndTime(ToolDataUtils.getreportTimeByDay());

        List<GetInventoryReportResponse> inventoryCarbrandDayList = reportMapperService.getInventoryReport(getInventoryReportParameter);

        if (null != inventoryCarbrandDayList && !inventoryCarbrandDayList.isEmpty()) {
            // 插入 库存信息
            carbrandDayDao.InsertT(getInsertCarbrandInventoryDayColumnsAndValues(inventoryCarbrandDayList));
        }


        return;
    }

    /**
     * param:
     * describe: 批量插入车辆类型月表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public void InserteCarbrandMonth() throws Exception {

        List<GroupCarbrandDayByMonthResponse> InvoiceMonthList = carbrandDayDao.groupCarbrandInvoiceDayByMonth(ToolDataUtils.getreportTimeByDay());

        if (null != InvoiceMonthList && !InvoiceMonthList.isEmpty()) {
            // 车辆品牌月表插入交易数据
            carbrandMonthDao.InsertT(getInsertCarbrandInvoiceMonthColumnsAndValues(InvoiceMonthList));
        }

        // 睡眠5秒 后执行 执行操作
        Thread.sleep(5000);

        List<GroupCarbrandDayByMonthResponse> InventoryMonthList = carbrandDayDao.groupCarbrandInventoryDayByMonth(ToolDataUtils.getreportTimeByDay());

        if (null != InventoryMonthList && !InventoryMonthList.isEmpty()) {
            // 车辆品牌月表插入库存数据
            carbrandMonthDao.InsertT(getInsertCarbrandInventoryMonthColumnsAndValues(InventoryMonthList));
        }

        return;
    }


    /**
     * param:
     * describe: 车辆品牌日表交易sql
     * create_date:  lxy   2018/11/26  10:41
     **/
    public InsertTParamter getInsertCarbrandInvoiceDayColumnsAndValues(List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCarbrandDayList) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carbrand_day`");
        parameter.setColumns("market_id,tenant_id,report_time,brand_name,sales_count,sales_price,sales_avg_price," +
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

            if (StringUtil.isNotEmpty(invoice.getBrandName())) {

                stringBuffer.append("'");
                stringBuffer.append(invoice.getBrandName());
                stringBuffer.append("'");
                stringBuffer.append(",");
            } else {

                stringBuffer.append("''");
                stringBuffer.append(",");
            }

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
                "age50_count =VALUES (age50_count)," +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }


    /**
     * param:
     * describe:  车辆品牌日表库存sql
     * create_date:  lxy   2018/12/4  16:38
     **/
    public InsertTParamter getInsertCarbrandInventoryDayColumnsAndValues(List<GetInventoryReportResponse> inventoryCarbrandDayList) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carbrand_day`");
        parameter.setColumns("market_id,tenant_id,report_time,brand_name,stock_count,stock_price");

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

            if (StringUtil.isNotEmpty(inventory.getBrandName())) {

                stringBuffer.append("'");
                stringBuffer.append(inventory.getBrandName());
                stringBuffer.append("'");
                stringBuffer.append(",");
            } else {

                stringBuffer.append("''");
                stringBuffer.append(",");
            }

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
                "stock_count =  VALUES (stock_count),\n" +
                "stock_price =  VALUES (stock_price)," +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }

    // 月表 sql

    /**
     * param:
     * describe: 车辆品牌月表交易sql
     * create_date:  lxy   2018/11/26  10:41
     **/
    public InsertTParamter getInsertCarbrandInvoiceMonthColumnsAndValues(List<GroupCarbrandDayByMonthResponse> InvoiceCarbrandMonthList) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carbrand_month`");
        parameter.setColumns("market_id,tenant_id,report_time,brand_name,sales_count,sales_price,sales_avg_price," +
                "male_count,female_count,age20_count,age25_count,age30_count,age35_count,age40_count,age45_count,age50_count");

        StringBuffer stringBuffer = new StringBuffer(128);

        for (GroupCarbrandDayByMonthResponse invoice : InvoiceCarbrandMonthList) {
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
            stringBuffer.append(ToolDataUtils.getreportTimeByMonth());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(invoice.getBrandName());
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
                "age50_count =VALUES (age50_count),\n" +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }

    /**
     * param:
     * describe:  车辆品牌月表库存sql
     * create_date:  lxy   2018/12/4  16:38
     **/
    public InsertTParamter getInsertCarbrandInventoryMonthColumnsAndValues(List<GroupCarbrandDayByMonthResponse> InventoryCarbrandMonthList) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carbrand_month`");
        parameter.setColumns("market_id,tenant_id,report_time,brand_name,stock_count,stock_price");

        StringBuffer stringBuffer = new StringBuffer(128);

        for (GroupCarbrandDayByMonthResponse inventory : InventoryCarbrandMonthList) {
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
            stringBuffer.append(ToolDataUtils.getreportTimeByMonth());
            stringBuffer.append("'");
            stringBuffer.append(",");


            stringBuffer.append("'");
            stringBuffer.append(inventory.getBrandName());
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
                "stock_count =  VALUES (stock_count),\n" +
                "stock_price =  VALUES (stock_price),\n" +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }


}
