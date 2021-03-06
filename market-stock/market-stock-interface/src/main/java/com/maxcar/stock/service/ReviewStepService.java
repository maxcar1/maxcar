package com.maxcar.stock.service;

import com.maxcar.stock.entity.Response.ReviewVo;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewDetail;
import com.maxcar.stock.pojo.ReviewStep;

import java.util.List;

public interface ReviewStepService {
    List<ReviewStep> getReviewStep(ReviewStep reviewStep);

    List<ReviewStep> reviewStepList(ReviewStep reviewStep);

    List<ReviewVo> reviewVolist(ReviewVo reviewVo);

    int saveReviewStep(ReviewStep reviewStep);

    int deleteByReview(ReviewStep reviewStep);

    List<ReviewStep> selectStepList(ReviewStep reviewStep);

    List<FlowStep> selectReviewManage(String marketId);

    int insertFlowStep(FlowStep flowStep);

    int deleteReviewManage(String id);

    int updateFlowStep(FlowStep flowStep);

    int selectMarketCode(FlowStep flowStep);

    List<ReviewStep> selectStepListByLevel(ReviewStep reviewStep);

    FlowStep selectReviewManageByReviewStep(ReviewStep reviewStep);

    int carOutApply(CarReview carReview);

    int checkCarReview(CarReview carReview);

    int deleteReviewStep(FlowStep flowStep);

    Integer getReviewResult(ReviewStep review);

    Integer selectCarReview(String reviewId);

    int selectLastStep(ReviewStep reviewStep);

    List<ReviewStep> selectStepListBySomeParams(ReviewStep reviewStep);

    FlowStep selectFlowStepByPrimarykey(String id);

    Integer updateReviewStep(ReviewStep reviewStep);

    ReviewStep selectReviewStep(CarReview carReview);
}
