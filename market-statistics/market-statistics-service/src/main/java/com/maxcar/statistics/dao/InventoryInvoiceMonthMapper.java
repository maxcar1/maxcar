package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;

public interface InventoryInvoiceMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryInvoiceMonthEntity record);

    int insertSelective(InventoryInvoiceMonthEntity record);

    InventoryInvoiceMonthEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryInvoiceMonthEntity record);

    int updateByPrimaryKey(InventoryInvoiceMonthEntity record);
}