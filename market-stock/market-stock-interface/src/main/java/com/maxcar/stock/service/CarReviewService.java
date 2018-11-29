package com.maxcar.stock.service;

import com.maxcar.stock.entity.Response.CarRecordVo;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.vo.CarReviewVo;
import com.maxcar.stock.vo.CarVo;

import java.util.List;

public interface CarReviewService {
    CarReview getCarReview(CarVo carVo);

    List<CarRecordVo> getCarRecord (CarReview carReview);

    List<CarReviewVo> selectAllCarReviewByIsPass();

    void updateTimeoutNotreturnCarStockStatus() throws Exception;
}
