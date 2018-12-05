package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class StockRequest implements Serializable {
    private static final long serialVersionUID = 7300041864287234520L;
    //  登入人的市场id
    private String UserMaketId;
    //  搜索条件 市场id
    private String marketId;
    //  商户搜索Id
    private String tenantId;
    //  时间范围开始
    @NotNull
    private String timeStart;
    //  时间范围结束
    @NotNull
    private String timeEnd;
    //  车辆数量区间 类型
    private String carNumType;
    //查询的月份
    private String monthNum;
}
