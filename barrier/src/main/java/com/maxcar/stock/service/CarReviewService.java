package com.maxcar.stock.service;


import com.maxcar.base.service.BaseService;
import com.maxcar.stock.pojo.CarReview;

import java.util.List;

public interface CarReviewService extends BaseService<CarReview,String> {
    /**
     * 根据车辆id查询是否审批通过，这里可能是一个集合
     * @param carId
     * @return
     */
    Boolean selectCarReviewByCarId(String carId);
}
