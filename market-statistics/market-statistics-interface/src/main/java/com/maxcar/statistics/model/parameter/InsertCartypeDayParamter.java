package com.maxcar.statistics.model.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class InsertCartypeDayParamter implements Serializable {

    private String columns;

    private String values;

    private String onUpdate;

}
