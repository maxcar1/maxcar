package com.maxcar.stock.dao;

import com.maxcar.stock.entity.Response.CarRecordVo;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.CarReviewExample;
import java.util.List;

import com.maxcar.stock.pojo.HisWarning;
import com.maxcar.stock.vo.CarReviewVo;
import org.apache.ibatis.annotations.Param;

public interface CarReviewMapper {
    int countByExample(CarReviewExample example);

    int deleteByExample(CarReviewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarReview record);

    int insertSelective(CarReview record);

    List<CarReview> selectByExample(CarReviewExample example);

    CarReview selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CarReview record, @Param("example") CarReviewExample example);

    int updateByExample(@Param("record") CarReview record, @Param("example") CarReviewExample example);

    int updateByPrimaryKeySelective(CarReview record);

    int updateByPrimaryKey(CarReview record);

    int carOutApply(CarReview carReview);

    int insertCarReview(CarReview carReview);

    List<HisWarning> selectByMarketid(@Param("marketId") String marketId);

    List<HisWarning> selectByHisWarning(HisWarning hisWarning);


    List<CarRecordVo> getCarRecord (CarReview carReview);

    /**
     * 查询所有出场超时未归的车辆  不需要传条件  看sql
     * @return
     */
    List<CarReviewVo> selectAllTimeoutNotreturnCarReview();
}