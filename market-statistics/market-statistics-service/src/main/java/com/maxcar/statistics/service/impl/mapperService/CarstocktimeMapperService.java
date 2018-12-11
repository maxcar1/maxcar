package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.dao.CarstocktimeDayDao;
import com.maxcar.statistics.dao.CarstocktimeMonthDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.parameter.InsertTParamter;
import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarstocktimeMapperService {

    @Autowired
    private ReportMapperService reportMapperService;

    @Autowired
    private CarstocktimeDayDao carstocktimeDayDao;

    @Autowired
    private CarstocktimeMonthDao carstocktimeMonthDao;


    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合 交易
     * create_date:  lxy   2018/11/28  10:24
     **/
    public List<GroupCarstocktimeInvoiceDayResponse> groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("stocktimeId");
        }
        

        return carstocktimeDayDao.groupCarstocktimeInvoiceDay(parameter);
    }

    /**
     * param:
     * describe: 日表交易排名
     * create_date:  lxy   2018/12/11  13:33
     **/
    public List<GetInvoiceRankingResponse>  groupCarstocktimeInvoiceDayRanking(GetInvoiceRankingByConditionRequest parameter){

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        parameter.setOrderBy(parameter.getOrderBy() + " desc ");


        return carstocktimeDayDao.groupCarstocktimeInvoiceDayRanking(parameter);
    }

    /**
     * param:
     * describe: 日表库存排名
     * create_date:  lxy   2018/12/11  14:48
     **/
    public  List<GetInventoryRankingResponse> groupCarstocktimeInventoryDayRanking(GetInventoryRankingByConditionRequest parameter){

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("inventoryCount");
        }

        parameter.setOrderBy(parameter.getOrderBy() + " desc ");


        return carstocktimeDayDao.groupCarstocktimeInventoryDayRanking(parameter);
    }
    /**
     * param:
     * describe: 统计当前的车辆库存天数日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    public List<GroupCarstocktimeInventoryDayResponse> groupCarstocktimeInventoryDay(GroupCarstocktimeInventoryDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("stocktimeId");
        }

        return carstocktimeDayDao.groupCarstocktimeInventoryDay(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆库存时长月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    public List<GroupCarstocktimeInvoiceMonthResponse> groupCarstocktimeInvoiceMonth(GroupCarstocktimeInvoiceMonthRequest parameter) {

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
    public List<GroupCarstocktimeInventoryMonthResponse> groupCarstocktimeInventoryMonth(GroupCarstocktimeInventoryMonthRequest parameter) {

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


// 以下是车辆库存时长数据插入


    public void InsertCarstocktime() throws Exception {

        // 睡眠5秒后执行 执行操作
        Thread.sleep(5000);

        InsertCarstocktimeDay();

        // 睡眠5秒后执行 执行操作
        Thread.sleep(5000);

        InsertCarstocktimeMonth();
    }

    /**
     * param:
     * describe: 按天批量插入车辆年限日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public void InsertCarstocktimeDay() throws Exception {

        String dayTime = ToolDataUtils.getreportTimeByDay();

        GetCarInvoiceTypeInvoiceReportParameter getCarInvoiceTypeInvoiceReportParameter = new GetCarInvoiceTypeInvoiceReportParameter();
        getCarInvoiceTypeInvoiceReportParameter.setStartTime(dayTime);
        getCarInvoiceTypeInvoiceReportParameter.setEndTime(dayTime);

        // 循环查询指定年限的交易数据
        for (int i = 1; i <= 10; i++) {

            getCarInvoiceTypeInvoiceReportParameter.setGroupByColumns("carstocktime");
            getCarInvoiceTypeInvoiceReportParameter.setInventoryCycle(i);


            List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCaryearDayList = reportMapperService.getCarInvoiceTypeInvoiceReport(getCarInvoiceTypeInvoiceReportParameter);

            if (null != InvoiceCaryearDayList && !InvoiceCaryearDayList.isEmpty()) {
                // 插入 交易信息
                carstocktimeDayDao.InsertT(getInsertCarstocktimeInvoiceDayColumnsAndValues(InvoiceCaryearDayList, i));
            }

        }

        // 睡眠5秒后执行 执行操作
        Thread.sleep(5000);

        GetInventoryReportParameter getInventoryReportParameter = new GetInventoryReportParameter();

        getInventoryReportParameter.setEndTime(ToolDataUtils.getreportTimeByDay());

        for (int i = 1; i <= 10; i++) {

            getInventoryReportParameter.setGroupByColumns("carstocktime");
            getInventoryReportParameter.setInventoryCycle(i);

            List<GetInventoryReportResponse> inventoryCarbrandDayList = reportMapperService.getInventoryReport(getInventoryReportParameter);

            if (null != inventoryCarbrandDayList && !inventoryCarbrandDayList.isEmpty()) {
                // 插入 库存信息
                carstocktimeDayDao.InsertT(getInsertCarstocktimeInventoryDayColumnsAndValues(inventoryCarbrandDayList, i));
            }
        }
        return;
    }

    /**
     * param:
     * describe: 按天批量插入车辆年限月表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public void InsertCarstocktimeMonth() throws Exception {

        List<GroupCarstocktimeDayByMonthResponse> InvoiceMonthList = carstocktimeDayDao.groupCarstocktimeInvoiceDayByMonth(ToolDataUtils.getreportTimeByDay());

        if (null != InvoiceMonthList && !InvoiceMonthList.isEmpty()) {
            // 车辆品牌月表插入交易数据
            carstocktimeMonthDao.InsertT(getInsertCarstocktimeInvoiceMonthColumnsAndValues(InvoiceMonthList));
        }

        // 睡眠5秒 后执行 执行操作
        Thread.sleep(5000);

        List<GroupCarstocktimeDayByMonthResponse> InventoryMonthList = carstocktimeDayDao.groupCarstocktimeInventoryDayByMonth(ToolDataUtils.getreportTimeByDay());

        if (null != InventoryMonthList && !InventoryMonthList.isEmpty()) {
            // 车辆品牌月表插入库存数据
            carstocktimeMonthDao.InsertT(getInsertCarstocktimeInventoryMonthColumnsAndValues(InventoryMonthList));
        }

        return;
    }

    /**
     * param:
     * describe: 车辆年限日表交易sql
     * create_date:  lxy   2018/11/26  10:41
     **/
    private InsertTParamter getInsertCarstocktimeInvoiceDayColumnsAndValues(List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceCarbrandDayList, Integer stocktimeId) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carstocktime_day`");
        parameter.setColumns("market_id,tenant_id,report_time,stocktime_id,stocktime_name,sales_count,sales_price,sales_avg_price");

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
            stringBuffer.append(stocktimeId);
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(BaseMapperService.getNameByInventoryCycleString(stocktimeId));
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


            stringBuffer.append("),");
        }

        String values = stringBuffer.toString();

        parameter.setValues(values.substring(1, values.length() - 2));


        String onUpdate = "ON DUPLICATE KEY  UPDATE \n" +
                "stocktime_name =  VALUES (stocktime_name),\n" +
                "sales_count =  VALUES (sales_count),\n" +
                "sales_price =  VALUES (sales_price),\n" +
                "sales_avg_price =  VALUES (sales_avg_price)," +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }


    /**
     * param:
     * describe:  车辆年限日表库存sql
     * create_date:  lxy   2018/12/4  16:38
     **/
    private InsertTParamter getInsertCarstocktimeInventoryDayColumnsAndValues(List<GetInventoryReportResponse> inventoryCarbrandDayList, Integer stocktimeId) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carstocktime_day`");
        parameter.setColumns("market_id,tenant_id,report_time,stocktime_id,stocktime_name,stock_count,stock_price");

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
            stringBuffer.append(stocktimeId);
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(BaseMapperService.getNameByInventoryCycleString(stocktimeId));
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
                "stocktime_name =  VALUES (stocktime_name),\n" +
                "stock_count =  VALUES (stock_count),\n" +
                "stock_price =  VALUES (stock_price)," +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }

 //  以下 是月表 插入数据

    /**
     * param:
     * describe: 车辆年限月表交易sql
     * create_date:  lxy   2018/11/26  10:41
     **/
    private InsertTParamter getInsertCarstocktimeInvoiceMonthColumnsAndValues(List<GroupCarstocktimeDayByMonthResponse> InvoiceMonthList) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carstocktime_month`");
        parameter.setColumns("market_id,tenant_id,report_time,stocktime_id,stocktime_name,sales_count,sales_price,sales_avg_price");

        StringBuffer stringBuffer = new StringBuffer(128);

        for (GroupCarstocktimeDayByMonthResponse invoice : InvoiceMonthList) {
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
            stringBuffer.append(invoice.getStocktimeId());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(BaseMapperService.getNameByInventoryCycleString(invoice.getStocktimeId()));
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


            stringBuffer.append("),");
        }

        String values = stringBuffer.toString();

        parameter.setValues(values.substring(1, values.length() - 2));


        String onUpdate = "ON DUPLICATE KEY  UPDATE \n" +
                "stocktime_name =  VALUES (stocktime_name),\n" +
                "sales_count =  VALUES (sales_count),\n" +
                "sales_price =  VALUES (sales_price),\n" +
                "sales_avg_price =  VALUES (sales_avg_price)," +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }

    /**
     * param:
     * describe:  车辆年限日表库存sql
     * create_date:  lxy   2018/12/4  16:38
     **/
    private InsertTParamter getInsertCarstocktimeInventoryMonthColumnsAndValues( List<GroupCarstocktimeDayByMonthResponse> InventoryMonthList) {

        InsertTParamter parameter = new InsertTParamter();

        parameter.setTable("`maxcar_statistics_l`.`carstocktime_month`");
        parameter.setColumns("market_id,tenant_id,report_time,stocktime_id,stocktime_name,stock_count,stock_price");

        StringBuffer stringBuffer = new StringBuffer(128);

        for (GroupCarstocktimeDayByMonthResponse inventory : InventoryMonthList) {
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
            stringBuffer.append(inventory.getStocktimeId());
            stringBuffer.append("'");
            stringBuffer.append(",");

            stringBuffer.append("'");
            stringBuffer.append(BaseMapperService.getNameByInventoryCycleString(inventory.getStocktimeId()));
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
                "stocktime_name =  VALUES (stocktime_name),\n" +
                "stock_count =  VALUES (stock_count),\n" +
                "stock_price =  VALUES (stock_price)," +
                "register_time = now();";

        parameter.setOnUpdate(onUpdate);

        return parameter;
    }


}
