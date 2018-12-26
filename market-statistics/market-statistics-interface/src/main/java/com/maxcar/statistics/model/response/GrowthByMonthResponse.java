package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GrowthByMonthResponse implements Serializable {

    private static final long serialVersionUID = 2360051126470104733L;
    private List<GrowthByMonthYearOnYearPack> growthByMonthYearOnYearPacks;

    private List<GrowthByMonthSequentialPack> growthByMonthSequentialPacks;
}
