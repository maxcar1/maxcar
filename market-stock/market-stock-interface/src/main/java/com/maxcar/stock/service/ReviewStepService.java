package com.maxcar.stock.service;


import com.maxcar.base.service.BaseService;
import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewStep;

import java.util.List;
import java.util.Map;

/**
 * 心愿单
 */
public interface ReviewStepService extends BaseService<ReviewStep,String> {


    int saveReviewStep(ReviewStep reviewStep);

    int deleteByReview(ReviewStep reviewStep);

    List<ReviewStep> selectStepList(ReviewStep reviewStep);

    List<FlowStep> selectReviewManage(String marketId);

    int insertFlowStep(FlowStep flowStep);

    int deleteReviewManage(String id);

    int updateFlowStep(FlowStep flowStep);

    int selectMarketCode(FlowStep flowStep);
}
