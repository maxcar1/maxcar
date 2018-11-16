package com.maxcar.tenant.app.model.response;

import com.maxcar.tenant.app.entity.AddDealInfo;
import com.maxcar.tenant.app.entity.BuySellInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetTransferCarDetailByIdResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private TransferCarResponse transferCar;

    private BuySellInfo buySellInfo;

    private  AddDealInfo addDealInfo;
}
