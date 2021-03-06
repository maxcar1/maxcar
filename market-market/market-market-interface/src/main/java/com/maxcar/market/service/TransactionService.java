package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.model.request.GetAllTransactionRequest;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.TradeInformation;

import java.text.ParseException;
import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/12 14:13
 * @desc 开票交易管理
 */

public interface TransactionService extends BaseService<Invoice, String> {

    /**
     * 获取所有的开票信息
     *
     * @param request
     * @return
     * @throws ParseException
     */
    public PageInfo getAllTransaction(GetAllTransactionRequest request) throws ParseException;

    /**
     * 获取开票详情的接口
     *
     * @param marketId
     * @param transactionId
     * @return
     */
    Invoice getTransactionDetails(String marketId, String transactionId);

    /**
     * 开票导出excel的接口
     *
     * @param request
     * @return
     * @throws ParseException
     */
    List<TradeInformation> getAllTransactionExcel(GetAllTransactionRequest request) throws ParseException;
}
