package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class GetCarListByMarketIdAndTenantResponse implements Serializable {

    private static final long serialVersionUID = 380722790841363041L;
    private String id;

    private String vin;

    private String modelName;

    /**
     * 图片地址
     */
    private String src;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
