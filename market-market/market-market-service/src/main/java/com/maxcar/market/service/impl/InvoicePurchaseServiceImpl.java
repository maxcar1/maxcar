package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.InvoicePurchaseMapper;
import com.maxcar.market.model.request.UpdateTicketByIdRequest;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.pojo.InvoicePurchaseExample;
import com.maxcar.market.service.InvoicePurchaseService;
import com.maxcar.market.utils.ToolUtils;
import com.maxcar.market.vo.InvoicePurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("invoicePurchaseService")
public class InvoicePurchaseServiceImpl extends BaseServiceImpl<InvoicePurchase, String> implements InvoicePurchaseService {

    @Autowired
    private InvoicePurchaseMapper invoicePurchaseMapper;

    @Override
    public BaseDao<InvoicePurchase, String> getBaseMapper() {
        // TODO Auto-generated method stub
        return invoicePurchaseMapper;
    }

    @Override
    public InterfaceResult selectTicketList(InvoicePurchaseRequest invoicePurchaseRequest) {
        InterfaceResult interfaceResult = new InterfaceResult();

        if (invoicePurchaseRequest.getCurPage() != null && invoicePurchaseRequest.getPageSize() != null && invoicePurchaseRequest.getPageSize() != 0) {
            PageHelper.startPage(invoicePurchaseRequest.getCurPage(), invoicePurchaseRequest.getPageSize());
        }
        if (invoicePurchaseRequest.getInvoiceCode() != null) {
            invoicePurchaseRequest.setInvoiceCode(invoicePurchaseRequest.getInvoiceCode());
        }
        if (invoicePurchaseRequest.getBuyTicketName() != null) {
            //去字符串全部空格
            String buyTicketName = invoicePurchaseRequest.getBuyTicketName().replaceAll("\\s*", "");
            invoicePurchaseRequest.setBuyTicketName(buyTicketName);
        }
        if (invoicePurchaseRequest.getBillTime() != null) {
            invoicePurchaseRequest.setBillTime(invoicePurchaseRequest.getBillTime());
        }

        List<InvoicePurchase> list = invoicePurchaseMapper.selectTicketList(invoicePurchaseRequest);
        PageInfo<InvoicePurchase> pageInfo = new PageInfo(list);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @Override
    public InterfaceResult insertTicket(InvoicePurchase record) {
        InterfaceResult interfaceResult = new InterfaceResult();
        record.setId(UuidUtils.getUUID());
        record.setInvoiceNo(record.getInvoiceStartNo());
        if (Integer.parseInt(record.getInvoiceEndNo()) > Integer.parseInt(record.getInvoiceStartNo())) {
            record.setPollAll(Integer.parseInt(record.getInvoiceEndNo()) + 1 - Integer.parseInt(record.getInvoiceStartNo()));
        }
        if (Integer.parseInt(record.getInvoiceEndNo()) > Integer.parseInt(record.getInvoiceNo())) {
            record.setPollResidue(Integer.parseInt(record.getInvoiceEndNo()) + 1 - Integer.parseInt(record.getInvoiceNo()));
        }
        if (Integer.parseInt(record.getInvoiceEndNo()) == Integer.parseInt(record.getInvoiceNo())) {
            record.setPollResidue(0);
        }
        Integer num = invoicePurchaseMapper.insertSelective(record);
        if (num == 1) {
            interfaceResult.InterfaceResult200("插入成功");
        }
        return interfaceResult;
    }

    @Override
    public InterfaceResult updateTicketById(String id) {
        InterfaceResult interfaceResult = new InterfaceResult();
        Integer num = invoicePurchaseMapper.updateTicketById(id);
        if (num == 1) {
            interfaceResult.InterfaceResult200("作废成功");
        } else {
            interfaceResult.InterfaceResult500("作废失败");
        }

        return interfaceResult;
    }

    @Override
    public InterfaceResult selectTicketName(String marketId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<InvoicePurchase> list = invoicePurchaseMapper.selectTicketName(marketId);
        PageInfo<InvoicePurchase> pageInfo = new PageInfo(list);
        Map<String, Object> map = new HashMap<>();
        map.put("list", pageInfo.getTotal());
        map.put("list", pageInfo.getList());
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }

    @Override
    public List<InvoicePurchase> selectInvoicePurchase(String marketId, String userId) {
        InvoicePurchaseExample invoicePurchaseExample = new InvoicePurchaseExample();
        invoicePurchaseExample.createCriteria().andMarketIdEqualTo(marketId).andStatusEqualTo(1).andUserIdEqualTo(userId);
        return invoicePurchaseMapper.selectByExample(invoicePurchaseExample);

    }

    @Override
    public int updateByIdAndVersion(InvoicePurchase invoicePurchase) {

        return invoicePurchaseMapper.updateByIdAndVersion(invoicePurchase);
    }

    @Override
    public InterfaceResult updateInvoicePurchaseById(UpdateTicketByIdRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();

        InvoicePurchase record = new InvoicePurchase();
        record.setId(request.getId());
        record.setInvoiceCode(request.getInvoiceCode());
        record.setInvoiceStartNo(request.getInvoiceStartNo());
        record.setInvoiceEndNo(request.getInvoiceEndNo());
        record.setBillTime(request.getBillTime());
        record.setUpdateTime(new Date());

        record.setInvoiceNo(record.getInvoiceStartNo());
        if (Integer.parseInt(record.getInvoiceEndNo()) >= Integer.parseInt(record.getInvoiceStartNo())) {
            record.setPollAll(Integer.parseInt(record.getInvoiceEndNo()) + 1 - Integer.parseInt(record.getInvoiceStartNo()));
        }
        if (Integer.parseInt(record.getInvoiceEndNo()) >= Integer.parseInt(record.getInvoiceNo())) {
            record.setPollResidue(Integer.parseInt(record.getInvoiceEndNo()) + 1 - Integer.parseInt(record.getInvoiceNo()));
        }

        if (ToolUtils.isOperationSuccess(invoicePurchaseMapper.updateByPrimaryKeySelective(record))) {
            interfaceResult.InterfaceResult200(true);
            return interfaceResult;
        }

        interfaceResult.InterfaceResult600("修改失败");

        return interfaceResult;
    }

}
