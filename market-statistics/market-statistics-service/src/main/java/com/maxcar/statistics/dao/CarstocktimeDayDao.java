package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CarstocktimeDayProvider;
import com.maxcar.statistics.model.request.GetInventoryRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceDayRequest;
import com.maxcar.statistics.model.response.*;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CarstocktimeDayDao extends BaseDao {

    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合 交易
     * create_date:  lxy   2018/11/28  10:24
     **/
    @SelectProvider(type = CarstocktimeDayProvider.class, method = "groupCarstocktimeInvoiceDay")
    List<GroupCarstocktimeInvoiceDayResponse> groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter);

    /**
     * param:
     * describe: 日表交易排名
     * create_date:  lxy   2018/12/11  13:32
     **/
    @SelectProvider(type = CarstocktimeDayProvider.class, method = "groupCarstocktimeInvoiceDayRanking")
    List<GetInvoiceRankingResponse>  groupCarstocktimeInvoiceDayRanking(GetInvoiceRankingByConditionRequest parameter);

    /**
     * param:
     * describe: 统计当前的车辆库存天数日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @SelectProvider(type = CarstocktimeDayProvider.class, method = "groupCarstocktimeInventoryDay")
    List<GroupCarstocktimeInventoryDayResponse> groupCarstocktimeInventoryDay(GroupCarstocktimeInventoryDayRequest parameter);

    /**
     * param:
     * describe: 日表库存排名
     * create_date:  lxy   2018/12/11  14:47
     **/
    @SelectProvider(type = CarstocktimeDayProvider.class, method = "groupCarstocktimeInventoryDayRanking")
    List<GetInventoryRankingResponse> groupCarstocktimeInventoryDayRanking(GetInventoryRankingByConditionRequest parameter);

    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合  交易
     * create_date:  lxy   2018/11/23  11:34
     **/
    @SelectProvider(type = CarstocktimeDayProvider.class, method = "groupCarstocktimeInvoiceDayByMonth")
    List<GroupCarstocktimeDayByMonthResponse> groupCarstocktimeInvoiceDayByMonth(String timeByMonth);


    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合  库存
     * create_date:  lxy   2018/11/23  11:34
     **/
    @SelectProvider(type = CarstocktimeDayProvider.class, method = "groupCarstocktimeInventoryDayByMonth")
    List<GroupCarstocktimeDayByMonthResponse> groupCarstocktimeInventoryDayByMonth(String timeByMonth);

}
