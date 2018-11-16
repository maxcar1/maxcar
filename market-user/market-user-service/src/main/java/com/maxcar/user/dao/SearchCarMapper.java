package com.maxcar.user.dao;

import com.maxcar.user.entity.SearchCar;
import com.maxcar.user.entity.SearchCarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchCarMapper {
    int countByExample(SearchCarExample example);

    int deleteByExample(SearchCarExample example);

    int deleteByPrimaryKey(String id);

    int insert(SearchCar record);

    int insertSelective(SearchCar record);

    List<SearchCar> selectByExample(SearchCarExample example);

    SearchCar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SearchCar record, @Param("example") SearchCarExample example);

    int updateByExample(@Param("record") SearchCar record, @Param("example") SearchCarExample example);

    int updateByPrimaryKeySelective(SearchCar record);

    int updateByPrimaryKey(SearchCar record);
}