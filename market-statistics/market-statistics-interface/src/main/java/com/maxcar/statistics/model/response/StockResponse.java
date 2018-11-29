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
}
