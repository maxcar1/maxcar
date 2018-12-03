package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class GetAllBrandNameRequest implements Serializable {


    private static final long serialVersionUID = 1893451618948980422L;
    private String marketId;


    private String tenantId;

}
