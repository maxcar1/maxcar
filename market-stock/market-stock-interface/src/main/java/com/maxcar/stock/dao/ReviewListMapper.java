package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.ReviewList;
import com.maxcar.stock.pojo.ReviewListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewListMapper {
    int countByExample(ReviewListExample example);

    int deleteByExample(ReviewListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReviewList record);

    int insertSelective(ReviewList record);

    List<ReviewList> selectByExample(ReviewListExample example);

    ReviewList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReviewList record, @Param("example") ReviewListExample example);

    int updateByExample(@Param("record") ReviewList record, @Param("example") ReviewListExample example);

    int updateByPrimaryKeySelective(ReviewList record);

    int updateByPrimaryKey(ReviewList record);
}