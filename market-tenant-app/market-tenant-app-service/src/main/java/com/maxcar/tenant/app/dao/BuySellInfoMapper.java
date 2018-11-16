package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.BuySellInfo;
import com.maxcar.tenant.app.entity.BuySellInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuySellInfoMapper {
    int countByExample(BuySellInfoExample example);

    int deleteByExample(BuySellInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(BuySellInfo record);

    int insertSelective(BuySellInfo record);

    List<BuySellInfo> selectByExample(BuySellInfoExample example);

    BuySellInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BuySellInfo record, @Param("example") BuySellInfoExample example);

    int updateByExample(@Param("record") BuySellInfo record, @Param("example") BuySellInfoExample example);

    int updateByPrimaryKeySelective(BuySellInfo record);

    int updateByPrimaryKey(BuySellInfo record);
}