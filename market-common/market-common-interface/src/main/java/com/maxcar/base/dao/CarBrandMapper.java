package com.maxcar.base.dao;

import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarModel;
import com.maxcar.base.pojo.CarSeries;

import java.util.List;

public interface CarBrandMapper {
    CarBrand getCarBrand(String brandCode);

    List<CarBrand> getAllBrand();

    int syncDaSouCheBrand(List<CarBrand> carBrands);

    int syncDaSouCheSeries(List<CarSeries> carSeries);

    int syncDaSouCheModel(List<CarModel> carModels);
}
