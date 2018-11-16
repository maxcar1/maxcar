package com.maxcar.invoice.dao;

import com.maxcar.invoice.pojo.TicketPurchase;
import com.maxcar.invoice.pojo.TicketPurchaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketPurchaseMapper {
    int countByExample(TicketPurchaseExample example);

    int deleteByExample(TicketPurchaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(TicketPurchase record);

    int insertSelective(TicketPurchase record);

    List<TicketPurchase> selectByExample(TicketPurchaseExample example);

    TicketPurchase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TicketPurchase record, @Param("example") TicketPurchaseExample example);

    int updateByExample(@Param("record") TicketPurchase record, @Param("example") TicketPurchaseExample example);

    int updateByPrimaryKeySelective(TicketPurchase record);

    int updateByPrimaryKey(TicketPurchase record);
}