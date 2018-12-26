package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CarstocktimeMonthProvider;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceMonthResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CarstocktimeMonthDao extends BaseDao {


    /**
     * param:
     * describe: 分组查询车辆库存时长月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @SelectProvider(type = CarstocktimeMonthProvider.class,method = "groupCarstocktimeInvoiceMonth")
    List<GroupCarstocktimeInvoiceMonthResponse> groupCarstocktimeInvoiceMonth(GroupCarstocktimeInvoiceMonthRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆库存时长月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @SelectProvider(type = CarstocktimeMonthProvider.class,method = "groupCarstocktimeInventoryMonth")
    List<GroupCarstocktimeInventoryMonthResponse> groupCarstocktimeInventoryMonth(GroupCarstocktimeInventoryMonthRequest parameter);
}
