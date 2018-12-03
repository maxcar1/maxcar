package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GrowthByMonthSequentialPack implements Serializable {

    private static final long serialVersionUID = 63600901747726362L;
    private String month;

    private String growthBySequential;
}
