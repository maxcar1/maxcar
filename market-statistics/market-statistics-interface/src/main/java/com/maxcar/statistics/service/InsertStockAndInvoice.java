package com.maxcar.statistics.service;

public interface InsertStockAndInvoice {
    /**
     * 库存交易日表  定时进行数据统计
     * @return
     */
    void InsertCarpriceDay() throws Exception;

    /**
     * 库存交易日表  定时进行数据统计
     * @return
     */
    void InsertCarstockDay() throws Exception;

    /**
     * 库存交易月表  定时进行数据统计
     * @return
     */
    void InsertCarstockMonth() throws Exception;

    /**
     * 库存交易日表  定时进行数据统计
     * @return
     */
    void InsertInventoryInvoiceDay() throws Exception;

    /**
     * 库存交易月表  定时进行数据统计
     * @throws Exception
     */
    void InsertInventoryInvoiceMonth() throws Exception;
}

