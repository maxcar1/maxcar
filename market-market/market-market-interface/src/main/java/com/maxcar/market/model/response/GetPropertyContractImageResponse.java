package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetPropertyContractImageResponse  implements Serializable{

    private static final long serialVersionUID = 6892970935750972295L;
    private List<String> list;

}
