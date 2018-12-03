package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockResponse implements Serializable {
    private static final long serialVersionUID = -8222277195704774489L;
    // 月份  格式 yyyyy-MM
    private String reportTime;
    //  存库总量
    private Double stockCount;
    //  库存价值总价值
    private Double stockPrice;
    //  移动平均库存总价值
    private Double avgStockPrice;
    //  移动平均库存总量
    private Double avgStockCount;
    //  库存价值环比增长率
    private Double stockMonthPriceIncrease;
    //  库存总量环比增长率
    private Double stockMonthCountIncrease;
    //  库存价值同比增长率
    private Double stockYearPriceIncrease;
    //  库存总量同比增长率
    private Double stockYearCountIncrease;
    //  车位饱和度
    private Double parkingSaturability;
    //  库存周期id
    private String stocktimeId;
    //  平均库存天数
    private Double stockDayAvg;
    //  车辆平均库存天数发展趋势   同步增长
    private Double yearStockDay;
    //  车辆平均库存天数发展趋势   环比增长率
    private Double monthStockDay;
    //  车商平均库存量发展趋势，在条件中的车商数量
    private Integer monthTenantCount;
    //  车商平均库存量发展趋势，在条件中的车商数量  环比增长
    private Double monthTenantCountMonth;
    //  车商平均库存量发展趋势，在条件中的车商数量  同比增长
    private Double monthTenantCountYear;

}
