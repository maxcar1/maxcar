package com.maxcar.statistics.dao.base;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.parameter.InsertTParamter;
import org.apache.ibatis.jdbc.SQL;

public class BaseProvider {

    public String InsertT(InsertTParamter parameter) {

        SQL sql = new SQL() {{
            INSERT_INTO(parameter.getTable());
            VALUES(parameter.getColumns(), parameter.getValues());
        }};

        String onUpdate = StringUtil.isEmpty(parameter.getOnUpdate()) ? "" : parameter.getOnUpdate();

        return sql.toString() + onUpdate;
    }


}
