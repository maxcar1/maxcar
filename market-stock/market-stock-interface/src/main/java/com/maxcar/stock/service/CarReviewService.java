package com.maxcar.stock.service;

import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.vo.CarVo;

public interface CarReviewService {
    CarReview getCarReview(CarVo carVo);
}
