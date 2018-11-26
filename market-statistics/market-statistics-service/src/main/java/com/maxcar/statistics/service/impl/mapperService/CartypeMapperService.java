package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.dao.CartypeDayDao;
import com.maxcar.statistics.dao.CartypeMonthDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.InsertTParamter;
import com.maxcar.statistics.model.request.GroupCartypeDayRequest;
import com.maxcar.statistics.model.request.GroupCartypeMonthRequest;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GroupCartypeDayByMonthResponse;
import com.maxcar.statistics.model.response.GroupCartypeDayResponse;
import com.maxcar.statistics.model.response.GroupCartypeMonthResponse;
import com.maxcar.user.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 操作车辆类型表(操作日表，月表)
 * create_date: lxy 2018/11/22  11:24
 **/

@Service
public class CartypeMapperService {

    @Autowired
    private CartypeDayDao cartypeDayDao;

    @Autowired
    private CartypeMonthDao cartypeMonthDao;

    @Autowired
    private ReportMapperService reportMapperService;

    @Autowired
    private ConfigurationService configurationService;


    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    public List<GroupCartypeDayResponse> groupCartypeDay(GroupCartypeDayRequest parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return cartypeDayDao.groupCartypeDay(parameter);
    }

    /**
     * param:
     * describe: 分组查询车辆类型月表
     * create_date:  lxy   2018/11/22  17:13
     **/
    public List<GroupCartypeMonthResponse> groupCartypeMonth(GroupCartypeMonthRequest parameter) {

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" where 1=1  ");

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" and market_id = #{marketId}");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" and tenant_id = #{tenantId}");

        }

        if (StringUtil.isNotEmpty(parameter.getTypeId())) {


            stringBuffer.append(" and type_id = #{typeId}");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return cartypeMonthDao.groupCartypeMonth(parameter);
    }



//    以下是 车辆类型 需要入库的任务统计

    /**
     * param:
     * describe: 车辆类型定时任务执行入口
     * create_date:  lxy   2018/11/23  11:55
     **/
    public void InsertCartype() throws Exception {

        // 睡眠一分钟后执行 执行日表操作
        Thread.sleep(60000);
        InsertCartypeDay();

        // 睡眠一分钟后执行 执行月表操作
        Thread.sleep(60000);
        InsertCartypeMonth();
    }


    /**
     * param:
     * describe: 按天批量插入车辆类型日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public boolean InsertCartypeDay() {

        String dayTime = ToolDataUtils.getreportTimeByDay();

        GetCarInvoiceTypeInvoiceReportParameter parameter = new GetCarInvoiceTypeInvoiceReportParameter();
        parameter.setGroupByColumns("carInvoiceType");
        parameter.setStartTime(dayTime);
        parameter.setEndTime(dayTime);

        List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceDayList = reportMapperService.getCarInvoiceTypeInvoiceReport(parameter);

        if (null == InvoiceDayList || InvoiceDayList.isEmpty()) {
            return false;
        }

        return cartypeDayDao.InsertT(getInsertCartypeDayColumnsAndValues(InvoiceDayList));
    }

    /**
     * param:
     * describe: 按月批量插入车辆类型月表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public boolean InsertCartypeMonth() {

        // 今天是否似当月最后一天 不是则不执行
      /*  if (!ToolDataUtils.isLastDayByMonth()) {
            return false;
        }*/


        List<GroupCartypeDayByMonthResponse> InvoiceMonthList = cartypeDayDao.groupCartypeDayByMonth(ToolDataUtils.getreportTimeByDay());

        if (null == InvoiceMonthList || InvoiceMonthList.isEmpty()) {
            return false;
        }

    /*    parameter.setColumns("market_id,tenant_id,report_time,brand_name,stock_count,stock_price," +
                "sales_count,sales_price,sales_avg_price,male_count,female_count," +
                "age20_count,age25_count,age30_count,age35_count,age40_count,age45_count,age50_count");

*/

        return cartypeMonthDao.InsertT(getInsertCartypeMonthColumnsAndValues(InvoiceMonthList));
    }


    /**
     * param:
     * describe: 车辆类型日表INSERT sql
     * create_date:  lxy   2018/11/22  15:21
     **/
    private InsertTParamter getInsertCartypeDayColumnsAndValues(List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceList) {

        InsertTParamter insertTParamter = new InsertTParamter();

        insertTParamter.setTable("  `maxcar_statistics_l`.`cartype_day` ");

        insertTParamter.setColumns("market_id,tenant_id,report_time,type_id,type_name,sales_count,sales_price,sales_avg_price");


        StringBuffer stringBuffer = new StringBuffer(128);

        for (GetCarInvoiceTypeInvoiceReportResponse invoice : InvoiceList) {

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

            if (StringUtil.isNotEmpty(invoice.getCarInvoiceType())) {

                stringBuffer.append("'");
                stringBuffer.append(invoice.getCarInvoiceType());
                stringBuffer.append("'");
                stringBuffer.append(",");

                String nameByKeyAndValue = configurationService.getNameByKeyAndValue(Magic.CAR_INVOICE_TYPE, invoice.getCarInvoiceType());

                stringBuffer.append("'");
                stringBuffer.append(nameByKeyAndValue);
                stringBuffer.append("'");
                stringBuffer.append(",");
            } else {
                stringBuffer.append("''");
                stringBuffer.append(",");
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

            stringBuffer.append("),");
        }

        String values = stringBuffer.toString();

        insertTParamter.setValues(values.substring(1, values.length() - 2));

        return insertTParamter;
    }

    /**
     * param:
     * describe: 车辆类型月表INSERT sql
     * create_date:  lxy   2018/11/22  15:21
     **/
    private InsertTParamter getInsertCartypeMonthColumnsAndValues(List<GroupCartypeDayByMonthResponse> InvoiceMonthList) {

        InsertTParamter insertTParamter = new InsertTParamter();

        insertTParamter.setTable("  `maxcar_statistics_l`.`cartype_month` ");

        insertTParamter.setColumns("market_id,tenant_id,report_time,type_id,type_name,sales_count,sales_price,sales_avg_price");


        StringBuffer stringBuffer = new StringBuffer(128);

        for (GroupCartypeDayByMonthResponse invoice : InvoiceMonthList) {

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

            if (StringUtil.isNotEmpty(invoice.getTypeId())) {

                stringBuffer.append("'");
                stringBuffer.append(invoice.getTypeId());
                stringBuffer.append("'");
                stringBuffer.append(",");

                String nameByKeyAndValue = configurationService.getNameByKeyAndValue(Magic.CAR_INVOICE_TYPE, invoice.getTypeId());

                stringBuffer.append("'");
                stringBuffer.append(nameByKeyAndValue);
                stringBuffer.append("'");
                stringBuffer.append(",");
            } else {
                stringBuffer.append("''");
                stringBuffer.append(",");
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

            stringBuffer.append("),");
        }

        String values = stringBuffer.toString();

        insertTParamter.setValues(values.substring(1, values.length() - 2));

        return insertTParamter;
    }
}
