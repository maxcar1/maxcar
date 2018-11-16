package com.maxcar.market.dao;

import com.maxcar.market.pojo.ShoppingGuideDetail;
import com.maxcar.market.pojo.ShoppingGuideDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingGuideDetailMapper {
    int countByExample(ShoppingGuideDetailExample example);

    int deleteByExample(ShoppingGuideDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShoppingGuideDetail record);

    int insertSelective(ShoppingGuideDetail record);

    List<ShoppingGuideDetail> selectByExample(ShoppingGuideDetailExample example);

    ShoppingGuideDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShoppingGuideDetail record, @Param("example") ShoppingGuideDetailExample example);

    int updateByExample(@Param("record") ShoppingGuideDetail record, @Param("example") ShoppingGuideDetailExample example);

    int updateByPrimaryKeySelective(ShoppingGuideDetail record);

    int updateByPrimaryKey(ShoppingGuideDetail record);
}