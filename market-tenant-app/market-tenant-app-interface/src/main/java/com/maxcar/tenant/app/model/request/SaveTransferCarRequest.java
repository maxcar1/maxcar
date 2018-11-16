package com.maxcar.tenant.app.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SaveTransferCarRequest implements Serializable {

    private static final long serialVersionUID = -1187447231051577670L;

    private String transferCarId;

    @NotNull(message = "车辆类型不能为null")
    private Byte carType;

    private String marketId;

    private String carId;

    private String tenantId;

    @NotBlank(message = "vin不能为空")
    private String vin;

    @NotNull(message = "厂牌型号不能为null")
    private String brandModel;

    @NotNull(message = "登记证号不能为null")
    private String registerCode;

    @NotNull(message = "车牌照号不能为null")
    private String plateNum;

    @NotNull(message = "车辆交易类型Id不能为null")
    private String configurationId;

    private String checkOutPhoto;

}
