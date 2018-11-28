package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class TradingResponse implements Serializable {
    private static final long serialVersionUID = 6785331579245018956L;
    //  统计的分月
    private String month;
    //  当月的交易总价值
    private Double price;
    //  当月的交易总量
    private Double count;
    //  移动平均交易总价值
    private Double priceMove;
    //  移动平均交易总量
    private Double countMove;
    //  交易总价值 同比/环比 增长率
    private Double priceRate;
    //  交易总量 同比/环比 增长率
    private Double countRate;
    //  平均交易价格
    private Double avgPrice;
    //  平均交易价格同比增长率
    private Double avgPriceYear;
    //  平均交易价格环比增长率
    private Double avgPriceMonth;
    //  当月商户成交量在条件中的数量
    private Double tenantCount;
    //  当月商户成交量在条件中的数量  同比
    private Double tenantCountYear;
    //  当月商户成交量在条件中的数量  环比
    private Double tenantCountMonth;
    //  交易层次同比增长率
    private Double dealPriceYear;
    //  交易层次环比增长率
    private Double dealPriceMonth;
    //  价格区间类型
    private String invoicePriceId;
    //  库存数量
    private Double stockAvgStocktime;
    //  车商交易库存天数占比
    private Double dayPercentage;
}
