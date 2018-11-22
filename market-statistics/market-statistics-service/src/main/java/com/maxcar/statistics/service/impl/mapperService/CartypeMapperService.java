package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.statistics.dao.CartypeDayDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.InsertCartypeDayParamter;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
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
    private ReportMapperService reportMapperService;

    @Autowired
    private ConfigurationService configurationService;


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

        List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceList = reportMapperService.getCarInvoiceTypeInvoiceReport(parameter);

        if (null == InvoiceList || InvoiceList.isEmpty()) {
            return false;
        }

    /*    parameter.setColumns("market_id,tenant_id,report_time,brand_name,stock_count,stock_price," +
                "sales_count,sales_price,sales_avg_price,male_count,female_count," +
                "age20_count,age25_count,age30_count,age35_count,age40_count,age45_count,age50_count");

*/

        return cartypeDayDao.InsertCartypeDay(getInsertCartypeDayColumnsAndValues(InvoiceList));
    }

    /**
     * param:
     * describe: 按月批量插入车辆类型日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03
     **/
    public boolean InsertCartypeMonth() {

        // 今天是否似当月最后一天 不是则不执行
        if (!ToolDataUtils.isLastDayByMonth()) {
            return false;
        }

        String dayTime = ToolDataUtils.getreportTimeByDay();

        GetCarInvoiceTypeInvoiceReportParameter parameter = new GetCarInvoiceTypeInvoiceReportParameter();
        parameter.setGroupByColumns("carInvoiceType");
        parameter.setStartTime(dayTime);
        parameter.setEndTime(dayTime);

        List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceList = reportMapperService.getCarInvoiceTypeInvoiceReport(parameter);

        if (null == InvoiceList || InvoiceList.isEmpty()) {
            return false;
        }

    /*    parameter.setColumns("market_id,tenant_id,report_time,brand_name,stock_count,stock_price," +
                "sales_count,sales_price,sales_avg_price,male_count,female_count," +
                "age20_count,age25_count,age30_count,age35_count,age40_count,age45_count,age50_count");

*/

        return cartypeDayDao.InsertCartypeDay(getInsertCartypeDayColumnsAndValues(InvoiceList));
    }


    /**
     * param:
     * describe: 车辆类型日表sql
     * create_date:  lxy   2018/11/22  15:21
     **/
    private InsertCartypeDayParamter getInsertCartypeDayColumnsAndValues(List<GetCarInvoiceTypeInvoiceReportResponse> InvoiceList) {

        InsertCartypeDayParamter insertCartypeDayParamter = new InsertCartypeDayParamter();

        insertCartypeDayParamter.setColumns("market_id,tenant_id,report_time,type_id,type_name,sales_count,sales_price,sales_avg_price");


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
                stringBuffer.append("");
                stringBuffer.append(",");
                stringBuffer.append("");
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

        insertCartypeDayParamter.setValues(values.substring(1, values.length() - 2));

        return insertCartypeDayParamter;
    }
}
