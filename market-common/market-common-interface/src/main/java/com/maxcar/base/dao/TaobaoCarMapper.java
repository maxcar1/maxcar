package com.maxcar.base.dao;

import com.maxcar.base.pojo.taobao.TaobaoCar;
import com.maxcar.base.pojo.taobao.TaobaoCarExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaobaoCarMapper {

    int countByExample(TaobaoCarExample example);

    int deleteByExample(TaobaoCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoCar record);

    int insertSelective(TaobaoCar record);

    List<TaobaoCar> selectByExample(TaobaoCarExample example);

    TaobaoCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaobaoCar record, @Param("example") TaobaoCarExample example);

    int updateByExample(@Param("record") TaobaoCar record, @Param("example") TaobaoCarExample example);

    int updateByPrimaryKeySelective(TaobaoCar record);

    int updateByPrimaryKey(TaobaoCar record);
}