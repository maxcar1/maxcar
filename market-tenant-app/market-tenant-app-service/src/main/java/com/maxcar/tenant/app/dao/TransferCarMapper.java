package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.TransferCar;
import com.maxcar.tenant.app.entity.TransferCarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransferCarMapper {
    int countByExample(TransferCarExample example);

    int deleteByExample(TransferCarExample example);

    int deleteByPrimaryKey(String id);

    int insert(TransferCar record);

    int insertSelective(TransferCar record);

    List<TransferCar> selectByExample(TransferCarExample example);

    TransferCar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TransferCar record, @Param("example") TransferCarExample example);

    int updateByExample(@Param("record") TransferCar record, @Param("example") TransferCarExample example);

    int updateByPrimaryKeySelective(TransferCar record);

    int updateByPrimaryKey(TransferCar record);

    int updateIsvalid(String transferCarId);

    int updateStatus(@Param("transferCarNo") String transferCarNo, @Param("status") int status);

    TransferCar getTransferCarByNo(String transferCarNo);

    int updateSign(@Param("sellerSign") String sellerSign, @Param("buyerSign") String buyerSign, @Param("transferCarId") String transferCarId);

    int updateContractUrl(@Param("id") String id, @Param("contractUrl") String contractUrl);

    TransferCar getTransferCarByCarId(String carId);
}