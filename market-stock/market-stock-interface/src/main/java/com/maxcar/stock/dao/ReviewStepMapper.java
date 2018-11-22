package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.pojo.ReviewStepExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReviewStepMapper {
    int countByExample(ReviewStepExample example);

    int deleteByExample(ReviewStepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReviewStep record);

    int insertSelective(ReviewStep record);

    List<ReviewStep> selectByExample(ReviewStepExample example);

    ReviewStep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReviewStep record, @Param("example") ReviewStepExample example);

    int updateByExample(@Param("record") ReviewStep record, @Param("example") ReviewStepExample example);

    int updateByPrimaryKeySelective(ReviewStep record);

    int updateByPrimaryKey(ReviewStep record);

    int deleteByReview(ReviewStep reviewStep);

    List<ReviewStep> selectStepList(ReviewStep reviewStep);

    List<Map> getUserOrgByReview(ReviewStep review);

    List<FlowStep> selectReviewManage(String marketId);

    int insertFlowStep(FlowStep flowStep);

    int deleteReviewManage(String id);

    int updateFlowStep(FlowStep flowStep);

    int selectMarketCode(FlowStep flowStep);
}