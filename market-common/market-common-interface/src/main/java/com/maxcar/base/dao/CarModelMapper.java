package com.maxcar.base.dao;

import com.maxcar.base.pojo.CarModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarModelMapper {
    CarModel getCarModel(String modelCode);

    List<Map<String,Object>> getCarModelBySeriesCode(@Param("seriesCode") String seriesCode) throws Exception;

    List<CarModel> getAllModel(String seriesId);
}
