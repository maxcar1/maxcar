package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.StockCarbrighicon;
import com.maxcar.stock.pojo.StockCarbrighiconExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockCarbrighiconMapper {
    int countByExample(StockCarbrighiconExample example);

    int deleteByExample(StockCarbrighiconExample example);

    int deleteByPrimaryKey(String id);

    int insert(StockCarbrighicon record);

    int insertSelective(StockCarbrighicon record);

    List<StockCarbrighicon> selectByExample(StockCarbrighiconExample example);

    StockCarbrighicon selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StockCarbrighicon record, @Param("example") StockCarbrighiconExample example);

    int updateByExample(@Param("record") StockCarbrighicon record, @Param("example") StockCarbrighiconExample example);

    int updateByPrimaryKeySelective(StockCarbrighicon record);

    int updateByPrimaryKey(StockCarbrighicon record);
}