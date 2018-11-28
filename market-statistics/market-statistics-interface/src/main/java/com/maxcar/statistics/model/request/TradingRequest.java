package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class TradingRequest implements Serializable {

    private static final long serialVersionUID = -746273977542930866L;

    // 登入人的市场id
    private String UserMaketId;
    //  传入的市场id
    private String marketId;
    //  商户的id
    private String tenantId;
    //  时间条件开始时间
    @NotNull
    private String timeStart;
    //  时间条件结束时间
    @NotNull
    private String timeEnd;
    //  查询信息
    private String selectColumns;
    //  查询条件
    private String selectCondition;
    //  查询来源
    private String selectFrom;
    //   分组条件
    private String groupByColumns;
    //  车商交易时间条件
    private String tenantTimeStart;
    //  车商交易结束时间条件
    private String tenantTimeEnd;
    //  查询车辆数量状态值
    private Integer carNumType;
    // 车商交易量发展趋势 同比
    private Double tenantYearRate;
    // 车商交易量发展趋势 环比
    private Double tenantMonthRate;
    //  车辆数量类型
    private Integer countType;
    //  环比增长率  还是  同比增长率    0同比  1环比
    private int type;
}
