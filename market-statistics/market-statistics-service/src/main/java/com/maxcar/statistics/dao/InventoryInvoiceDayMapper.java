package com.maxcar.statistics.dao;
import com.maxcar.statistics.model.entity.InventoryInvoiceDayEntity;

public interface InventoryInvoiceDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryInvoiceDayEntity record);

    int insertSelective(InventoryInvoiceDayEntity record);

    InventoryInvoiceDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryInvoiceDayEntity record);

    int updateByPrimaryKey(InventoryInvoiceDayEntity record);
}