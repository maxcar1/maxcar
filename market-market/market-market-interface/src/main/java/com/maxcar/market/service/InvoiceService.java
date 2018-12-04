package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.model.response.InvoicePerson;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.TradeInformation;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by chiyanlong on 2018/8/24.
 */
public interface InvoiceService extends BaseService<Invoice, String> {

    PageInfo selectInvoiceCountList(Invoice invoice);

    PageInfo selectInvoiceDetail(Invoice invoice);

    Invoice selectInvoiceTotalCount();

    PageInfo getInvoiceList(Invoice invoice);

    Invoice selectInvoiceDetailById(String id);

    List<TradeInformation> detailsExcel(Invoice invoice);

//    InterfaceResult insertInvoice(Invoice invoice);

    List<InvoicePerson> getInvoicePerson(String idCard, String marketId);

    Map nowDeal(String marketId, String tenantId) throws ParseException;
}
