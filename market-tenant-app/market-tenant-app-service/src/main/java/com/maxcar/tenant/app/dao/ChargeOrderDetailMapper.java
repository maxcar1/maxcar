package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import org.apache.ibatis.annotations.Param;

public interface ChargeOrderDetailMapper {

    int insert(ChargeOrderDetail chargeOrderDetail);

    ChargeOrderDetail find(String id);

    int updateState(ChargeOrderDetail chargeOrderDetail);

    ChargeOrderDetail getChargeOrderByTransferCarNo(@Param("transferCarNo") String transferCarNo, @Param("feeType") int feeType);
}