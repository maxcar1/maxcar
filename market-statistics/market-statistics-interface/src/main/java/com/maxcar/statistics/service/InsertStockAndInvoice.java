package com.maxcar.statistics.service;

public interface InsertStockAndInvoice {
    /**
     * 库存交易日表  定时进行数据统计
     * @return
     */
    void InsertCarpriceDay();

    /**
     * 库存交易日表  定时进行数据统计
     * @return
     */
    void InsertCarstockDay();

    /**
     * 库存交易月表  定时进行数据统计
     * @return
     */
    void InsertCarstockMonth();

    /**
     * 库存交易日表  定时进行数据统计
     * @return
     */
    void InsertInventoryInvoiceDay();
}

