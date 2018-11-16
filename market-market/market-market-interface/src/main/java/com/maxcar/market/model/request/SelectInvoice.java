package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SelectInvoice implements Serializable {

    private static final long serialVersionUID = -7122664853473995671L;
    @NotNull(message = "市场id")
    private String marketId;
    private String vin;
    private int tenantType;
    private String billTimeStart;
    private String billTimeEnd;
    private String invoiceNo;
    private int curPage;
    private int pageSize;
    private String tenantId;

    @NotNull
    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(@NotNull String marketId) {
        this.marketId = marketId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getTenantType() {
        return tenantType;
    }

    public void setTenantType(int tenantType) {
        this.tenantType = tenantType;
    }

    public String getBillTimeStart() {
        return billTimeStart;
    }

    public void setBillTimeStart(String billTimeStart) {
        this.billTimeStart = billTimeStart;
    }

    public String getBillTimeEnd() {
        return billTimeEnd;
    }

    public void setBillTimeEnd(String billTimeEnd) {
        this.billTimeEnd = billTimeEnd;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}
