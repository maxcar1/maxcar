package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class CarDataStatistics implements Serializable {
    private Integer inventoryTotal;//库存总数

    private Double inventoryValuation;//库存估价

    private Integer pledgeCarTotal;//质押车数量

    private Double pledgeCarValuation;//质押车估价

    private Integer presencePledgeCar;//在场质押车

    private Double presencePledgeCarValuation;//在场质押车估价

    public Integer getInventoryTotal() {
        return inventoryTotal;
    }

    public void setInventoryTotal(Integer inventoryTotal) {
        this.inventoryTotal = inventoryTotal;
    }

    public Double getInventoryValuation() {
        return inventoryValuation;
    }

    public void setInventoryValuation(Double inventoryValuation) {
        this.inventoryValuation = inventoryValuation;
    }

    public Integer getPledgeCarTotal() {
        return pledgeCarTotal;
    }

    public void setPledgeCarTotal(Integer pledgeCarTotal) {
        this.pledgeCarTotal = pledgeCarTotal;
    }

    public Double getPledgeCarValuation() {
        return pledgeCarValuation;
    }

    public void setPledgeCarValuation(Double pledgeCarValuation) {
        this.pledgeCarValuation = pledgeCarValuation;
    }

    public Integer getPresencePledgeCar() {
        return presencePledgeCar;
    }

    public void setPresencePledgeCar(Integer presencePledgeCar) {
        this.presencePledgeCar = presencePledgeCar;
    }

    public Double getPresencePledgeCarValuation() {
        return presencePledgeCarValuation;
    }

    public void setPresencePledgeCarValuation(Double presencePledgeCarValuation) {
        this.presencePledgeCarValuation = presencePledgeCarValuation;
    }
}
