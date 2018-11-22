package com.maxcar.statistics.dao.provider;

import com.maxcar.statistics.model.parameter.InsertCartypeDayParamter;
import org.apache.ibatis.jdbc.SQL;

public class CartypeDayProvider {

    public String InsertCartypeDay(InsertCartypeDayParamter parameter) {

        return new SQL() {{
            INSERT_INTO("`maxcar_statistics_l`.`cartype_day`");
            VALUES(parameter.getColumns(), parameter.getValues());
        }}.toString();
    }
}
