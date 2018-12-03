package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.ReviewDetail;
import com.maxcar.stock.pojo.ReviewDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewDetailMapper {
    int countByExample(ReviewDetailExample example);

    int deleteByExample(ReviewDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReviewDetail record);

    int insertSelective(ReviewDetail record);

    List<ReviewDetail> selectByExample(ReviewDetailExample example);

    ReviewDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReviewDetail record, @Param("example") ReviewDetailExample example);

    int updateByExample(@Param("record") ReviewDetail record, @Param("example") ReviewDetailExample example);

    int updateByPrimaryKeySelective(ReviewDetail record);

    int updateByPrimaryKey(ReviewDetail record);

    int updateReviewStatus(ReviewDetail reviewDetail);

    ReviewDetail selectReviewDetail(ReviewDetail reviewDetail);

    List<ReviewDetail> getReviewDetail(ReviewDetail reviewDetail);

    List<ReviewDetail> selReviewDetail(ReviewDetail reviewDetail);

    ReviewDetail reviewDetail(@Param("reviewId") Integer reviewId);
}