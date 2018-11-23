package com.maxcar.statistics.model.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class InsertTParamter implements Serializable {

    private String columns;

    private String values;

    private String table;

    private String onUpdate;

}
