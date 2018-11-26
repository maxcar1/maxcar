package com.maxcar.statistics.dao.base;

import com.maxcar.statistics.model.parameter.InsertTParamter;
import org.apache.ibatis.annotations.InsertProvider;

public interface BaseDao {

    /**
     * param:
     * describe: 批量插入
     * create_date:  lxy   2018/11/22  11:03
     **/
    @InsertProvider(type = BaseProvider.class, method = "InsertT")
    boolean InsertT(InsertTParamter parameter);

}
