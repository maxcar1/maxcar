package com.maxcar.statistics.service.impl;

import com.maxcar.statistics.dao.*;
import com.maxcar.statistics.model.entity.CarpriceDayEntity;
import com.maxcar.statistics.service.InsertStockAndInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("insertStockAndInvoice")
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
        List<CarpriceDayEntity> carpriceDayEntities = carpriceDayMapper.selectCarpriceDay();
        System.out.println(carpriceDayEntities);
    }

//    @Override
//    public void InsertCarstockDay() {
//        carStockDayMapper.InsertCarstockDay();
//    }
//
//    @Override
//    public void InsertCarstockMonth() {
//        carStockMonthMapper.InsertCarstockMonth();
//    }
//
//    @Override
//    public void InsertInventoryInvoiceDay() {
//        inventoryInvoiceDayMapper.InsertInventoryInvoiceDay();
//    }
}
