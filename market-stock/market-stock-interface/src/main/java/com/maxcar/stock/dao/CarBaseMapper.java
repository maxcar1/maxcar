package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.model.VehicleBrand;
import com.maxcar.stock.entity.Request.SearchCarRequest;
import com.maxcar.stock.entity.Response.CarBaseResponse;
import com.maxcar.stock.entity.Response.CarDetails;
import com.maxcar.stock.pojo.*;
import org.apache.ibatis.annotations.Param;

import javax.security.auth.login.Configuration;
import java.util.List;

public interface CarBaseMapper extends BaseDao<CarBase, String> {
    int countByExample(CarBaseExample example);

    int deleteByExample(CarBaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(CarBaseWithBLOBs record);

    int insertSelective(CarBaseWithBLOBs record);

    List<CarBaseWithBLOBs> selectByExampleWithBLOBs(CarBaseExample example);

    List<CarBase> selectByExample(CarBaseExample example);

    CarBaseWithBLOBs selectByPrimaryKey(String id);

    List<CarBaseResponse> selectByList(CarBase carBase);

    List<CarBase> searchCarList(CarBase carBase);

    int updateByExampleSelective(@Param("record") CarBaseWithBLOBs record, @Param("example") CarBaseExample example);

    int updateByExampleWithBLOBs(@Param("record") CarBaseWithBLOBs record, @Param("example") CarBaseExample example);

    int updateByExample(@Param("record") CarBase record, @Param("example") CarBaseExample example);

    int updateByPrimaryKeySelective(CarBaseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CarBaseWithBLOBs record);

    int updateByPrimaryKey(CarBase record);


    List<CarBaseWithBLOBs> listCarBase(CarBaseWithBLOBs record);

    //获取车型字典
    List<Configuration> selectCarGroup(@Param("marketId") String marketId, @Param("type") String type);

    List<CarDetails> getCarBaseById(String id);

    List<CarBase> selectCarList(CarBase carBase);

    List<CarBase> queryCarList(SearchCarRequest carRequest);

    int countCarList(SearchCarRequest carRequest);

    CarBase getCarBaseByVin(String vin);

    CarBase getCarBase(String id);

    List<VehicleBrand> getBrandTop10(CarBase carBase);

}