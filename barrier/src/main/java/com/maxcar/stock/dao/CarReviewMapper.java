package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.CarReviewExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarReviewMapper extends BaseDao<CarReview,String> {
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
}