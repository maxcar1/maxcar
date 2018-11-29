package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.CarReviewExample;
import java.util.List;

import com.maxcar.stock.pojo.HisWarning;
import org.apache.ibatis.annotations.Param;

public interface CarReviewMapper {
    int countByExample(CarReviewExample example);

    int deleteByExample(CarReviewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarReview record);

    int insertSelective(CarReview record);

    List<CarReview> selectByExample(CarReviewExample example);

    CarReview selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarReview record, @Param("example") CarReviewExample example);

    int updateByExample(@Param("record") CarReview record, @Param("example") CarReviewExample example);

    int updateByPrimaryKeySelective(CarReview record);

    int updateByPrimaryKey(CarReview record);

    List<HisWarning> selectByMarketid(@Param("marketId") String marketId);

    List<HisWarning> selectByHisWarning(HisWarning hisWarning);


}