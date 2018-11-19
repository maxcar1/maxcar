package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.pojo.ReviewStepExample;
import java.util.List;
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
}