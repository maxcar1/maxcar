package com.maxcar.tenant.app.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetTransferCarByIdRequest implements Serializable {

    private static final long serialVersionUID = 5292733347117361659L;
    private String transferCarId;
}
