package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class ListCarVoNumberResponse implements Serializable {

    private static final long serialVersionUID = 2194185591369621596L;
    private Integer countStock;

    private Integer inStock;

    private Integer outStock;

    private Integer sumStockPrice;

    public Integer getCountStock() {
        return countStock;
    }

    public void setCountStock(Integer countStock) {
        this.countStock = countStock;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getOutStock() {
        return outStock;
    }

    public void setOutStock(Integer outStock) {
        this.outStock = outStock;
    }

    public Integer getSumStockPrice() {
        return sumStockPrice;
    }

    public void setSumStockPrice(Integer sumStockPrice) {
        this.sumStockPrice = sumStockPrice;
    }
}
