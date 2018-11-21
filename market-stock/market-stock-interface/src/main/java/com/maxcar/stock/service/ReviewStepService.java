package com.maxcar.stock.service;

import com.maxcar.stock.entity.Response.ReviewVo;
import com.maxcar.stock.pojo.ReviewStep;

import java.util.List;

public interface ReviewStepService {
    List<ReviewStep> getReviewStep(ReviewStep reviewStep);

    List<ReviewVo> reviewVolist(ReviewVo reviewVo);
}
