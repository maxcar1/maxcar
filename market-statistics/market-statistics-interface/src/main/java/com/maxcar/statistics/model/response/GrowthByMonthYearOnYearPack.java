package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GrowthByMonthYearOnYearPack implements Serializable {

    private static final long serialVersionUID = -5676511204622450310L;
    private String month;

    private String growthByYearOnYear;

}
