package com.maxcar.tenant.app.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetTransferCarListRequest implements Serializable {

    @NotNull(message = "过户状态不能为null")
    private Byte type;

    private String marketId;


    private String  tenantId;

    @NotNull(message = "请求页数不能为null")
    private Integer curPage;

    @NotNull(message = "请求条数不能为null")
    private Integer pageSize;
}
