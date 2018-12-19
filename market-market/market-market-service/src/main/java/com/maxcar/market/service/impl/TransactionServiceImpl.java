package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.request.GetAllTransactionRequest;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.pojo.TradeInformation;
import com.maxcar.market.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @Author sunyazhou
 * @Date 2018/10/15 10:28
 * @desc
 */

@Service("transactionService")
public class TransactionServiceImpl extends BaseServiceImpl<Invoice, String> implements TransactionService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public BaseDao<Invoice, String> getBaseMapper() {
        return invoiceMapper;
    }

    /**
     * 交易列表
     *
     * @param request
     * @return
     */
    @Override
    public PageInfo getAllTransaction(GetAllTransactionRequest request) throws ParseException {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();

        String marketId = request.getMarketId();
        criteria.andMarketIdEqualTo(marketId);
        criteria.andInvoiceStatusEqualTo(2);

        if (StringUtil.isNotEmpty(request.getSellerName())) {
            criteria.andSellerNameLike("%" + request.getSellerName() + "%");
        }
        if (StringUtil.isNotEmpty(request.getPurchacerName())) {
            criteria.andPurchacerNameLike("%" + request.getPurchacerName() + "%");
        }

        String start = request.getBillTimeStart();
        String end = request.getBillTimeEnd();
//        SimpleDateFormat format  = new SimpleDateFormat(DateUtils.SHORT_DATE_FORMAT);

        Date billTimeStart = null;
        Date billTimeEnd = null;
        if (StringUtil.isNotEmpty(start)) {
            billTimeStart = DateUtils.parseDate(start, "yyyy-MM-dd");
        }

        if (StringUtil.isNotEmpty(end)) {
            billTimeEnd = DateUtils.parseDate(end, "yyyy-MM-dd");
            billTimeEnd = DateUtils.getDayEnd(billTimeEnd);
        }

        if (null != billTimeStart && null != billTimeEnd) {
            criteria.andBillTimeBetween(billTimeStart, billTimeEnd);
        } else if (null != billTimeStart && null == billTimeEnd) {
            criteria.andBillTimeGreaterThanOrEqualTo(billTimeStart);
        } else if (null == billTimeStart && null != billTimeEnd) {
            criteria.andBillTimeLessThanOrEqualTo(billTimeEnd);
        }

        invoiceExample.setOrderByClause("insert_time desc");

        List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);
        PageInfo pageInfo = new PageInfo(invoices);
        return pageInfo;
    }

    @Override
    public List<TradeInformation> getAllTransactionExcel(GetAllTransactionRequest request) throws ParseException {
        Invoice invoice = new Invoice();
        invoice.setMarketId(request.getMarketId());
        String purchacerName = request.getPurchacerName();
        String sellerName = request.getSellerName();
        int flag = 0;
        if (StringUtil.isNotEmpty(purchacerName)) {
            String trim = purchacerName.trim();
            invoice.setPurchacerName("%" + trim + "%");
        } else {
            flag += 1;
        }
        if (StringUtil.isNotEmpty(sellerName)) {
            String trim = sellerName.trim();
            invoice.setSellerName("%" + trim + "%");
        }else {
            flag += 1;
        }
        String billTimeEnd = request.getBillTimeEnd();
        if (StringUtil.isNotEmpty(billTimeEnd)) {
            Date date = DateUtils.parseDate(billTimeEnd, DateUtils.DATE_FORMAT_DATEONLY);
            Date dayEnd = DateUtils.getDayEnd(date);
            String s = DateUtils.formatDate(dayEnd);
            invoice.setBillTimeStart(request.getBillTimeStart());
            invoice.setBillTimeEnd(s);
        } else {
            flag += 1;
        }
        if(flag == 3){
            Date date = new Date();
            Date dayEnd = DateUtils.getDayEnd(date);
            String s = DateUtils.formatDate(dayEnd);
            String[] split = s.split("-");
            Integer year = Integer.parseInt(split[0]);
            Integer month = Integer.parseInt(split[1]);
            String day = split[2];
            for (int i = 0; i < 3; i++) {
                if (month > 1) {
                    month = month - 1;
                } else {
                    year = year - 1;
                    month = 12;
                }
            }
            String startTime = year.toString() + "-" + month.toString() + "-" + day;
            invoice.setBillTimeStart(startTime.substring(0, 10));
            invoice.setBillTimeEnd(s);
        }
//        List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);
        List<TradeInformation> tradeInformations = invoiceMapper.detailsExcel(invoice);

        return tradeInformations;
    }

    @Override
    public Invoice getTransactionDetails(String marketId, String transactionId) {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();

        criteria.andMarketIdEqualTo(marketId);

        criteria.andIdEqualTo(transactionId);

        List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);

        return (invoices.size() > 0 ? invoices.get(0) : null);
    }


}
