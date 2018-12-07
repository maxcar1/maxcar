package com.maxcar.statistics.service.impl;

import com.maxcar.statistics.dao.*;
import com.maxcar.statistics.service.InsertStockAndInvoice;
import org.springframework.beans.factory.annotation.Autowired;


public class InsertStockAndInvoiceImpl implements InsertStockAndInvoice {

    @Autowired
    private CarpriceDayMapper carpriceDayMapper;

    @Autowired
    private CarStockDayMapper carStockDayMapper;

    @Autowired
    private CarStockMonthMapper carStockMonthMapper;

    @Autowired
    private InventoryInvoiceDayMapper inventoryInvoiceDayMapper;

    @Autowired
    private InventoryInvoiceMonthMapper inventoryInvoiceMonthMapper;

    @Override
    public void InsertCarpriceDay() {
        //carpriceDayMapper.InsertCarpriceDay();
    }

    @Override
    public void InsertCarstockDay() {
        //carStockDayMapper.InsertCarstockDay();
    }

    @Override
    public void InsertCarstockMonth() {
        //carStockMonthMapper.InsertCarstockMonth();
    }

    @Override
    public void InsertInventoryInvoiceDay() {
      //  inventoryInvoiceDayMapper.InsertInventoryInvoiceDay();
    }
}
