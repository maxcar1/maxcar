package com.maxcar.market.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.request.GetAllTransactionRequest;
import com.maxcar.market.model.response.InvoicePerson;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.pojo.TradeInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by chiyanlong on 2018/8/24.
 */
@Service("invoiceService")
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice, String> implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public BaseDao<Invoice, String> getBaseMapper() {
        return invoiceMapper;
    }

    @Override
    public PageInfo selectInvoiceCountList(Invoice invoice) {
        PageHelper.startPage(invoice.getCurrentPage(), invoice.getPageSize());
        List<Invoice> lists = invoiceMapper.selectInvoiceCountList(invoice);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public PageInfo selectInvoiceDetail(Invoice invoice) {
        PageHelper.startPage(invoice.getCurrentPage(), invoice.getPageSize());
        List<Invoice> lists = invoiceMapper.selectInvoiceDetail(invoice);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public Invoice selectInvoiceTotalCount() {
        return invoiceMapper.selectInvoiceTotalCount();
    }


    @Override
    public PageInfo getInvoiceList(Invoice invoice) {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        if (null != invoice.getRemark() && invoice.getRemark() == 2) {//查询开票申请列表
            criteria.andInvoicePortofNotEqualTo(2);//非窗口列表
            criteria.andInvoiceStatusNotEqualTo(0);//非作废状态
        }
        if (null != invoice.getRemark() && invoice.getRemark() == 1) {//查询开具发票列表
            criteria.andInvoiceStatusNotEqualTo(1);//查询为已处理和作废状态
            criteria.andUserIdEqualTo(invoice.getUserId());
        }
        if (null != invoice.getMarketId() && !invoice.getMarketId().equals("")) {
            criteria.andMarketIdEqualTo(invoice.getMarketId());
        }
        if (null != invoice.getPurchacerName() && !invoice.getPurchacerName().equals("")) {
            criteria.andPurchacerNameLike("%" + invoice.getPurchacerName() + "%");
        }
        if (null != invoice.getSellerName() && !invoice.getSellerName().equals("")) {
            criteria.andSellerNameLike("%" + invoice.getSellerName() + "%");
        }
        if (null != invoice.getInvoiceNo() && !invoice.getInvoiceNo().equals("")) {
            criteria.andInvoiceNoLike("%" + invoice.getInvoiceNo() + "%");
        }
        if (null != invoice.getBillTimeStart() && null != invoice.getBillTimeEnd()) {
            criteria.andBillTimeGreaterThanOrEqualTo(DateUtils.getDateFromString(invoice.getBillTimeStart(), "yyyy-MM-dd"));
            criteria.andBillTimeLessThanOrEqualTo(DateUtils.getDateFromString(invoice.getBillTimeEnd(), "yyyy-MM-dd"));
        }
        if (null != invoice.getSyncTimeStart() && null != invoice.getSyncTimeEnd()) {
            criteria.andSyncTimeGreaterThanOrEqualTo(DateUtils.getDateFromString(invoice.getSyncTimeStart(), "yyyy-MM-dd"));
            criteria.andSyncTimeLessThanOrEqualTo(DateUtils.getDateFromString(invoice.getSyncTimeEnd(), "yyyy-MM-dd"));
        }
        if (null != invoice.getInvoicePortof()) {
            criteria.andInvoicePortofEqualTo(invoice.getInvoicePortof());
        }
        if (null != invoice.getInvoiceStatus()) {
            criteria.andInvoiceStatusEqualTo(invoice.getInvoiceStatus());
        }
        if (null != invoice.getCarSources()) {
            criteria.andCarSourcesEqualTo(invoice.getCarSources());
        }
        if (null != invoice.getCurrentNo() && invoice.getCurrentNo() != "") {
            criteria.andCurrentNoEqualTo(invoice.getCurrentNo());
        }
        invoiceExample.setOrderByClause("insert_time desc");
        PageHelper.startPage(invoice.getCurrentPage(), invoice.getPageSize());
        List<Invoice> lists = invoiceMapper.selectByExample(invoiceExample);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public Invoice selectInvoiceDetailById(String id) {
        return invoiceMapper.selectInvoiceDetailById(id);
    }

    @Override
    public List<TradeInformation> detailsExcel(GetAllTransactionRequest request) throws ParseException {
        if (StringUtil.isNotEmpty(request.getSellerName())) {
            request.setSellerName("%" + request.getSellerName() + "%");
        }
        if (StringUtil.isNotEmpty(request.getPurchacerName())) {
            request.setPurchacerName("%" + request.getPurchacerName() + "%");
        }
        String end = request.getBillTimeEnd();

        if (StringUtil.isNotEmpty(end)) {
            Date date = DateUtils.parseDate(end, "yyyy-MM-dd");
            Date dayEnd = DateUtils.getDayEnd(date);
            String s = DateUtils.formatDate(dayEnd, DateUtils.DATE_FORMAT_DATETIME);
            request.setBillTimeEnd(s);
        }

        List<TradeInformation> tradeInformations = invoiceMapper.detailsExcel(request);
//        for(TradeInformation t : tradeInformations){
//            String billTime = t.getBillTime();
//            billTime = billTime.substring(0, 10);
//            billTime = billTime.replace("-", "/");
//            t.setBillTime(billTime);
//            t.setDealTime(billTime);
////            String dealTime = t.getDealTime();
////            if(StringUtil.isNotEmpty(dealTime)){
////                dealTime = dealTime.substring(0,10);
////                dealTime = dealTime.replace("-", "/");
////                t.setDealTime(dealTime);
////            }
//            String initialLicenceTime = t.getInitialRegistrationDate();
//            if(StringUtil.isNotEmpty(initialLicenceTime)){
//                initialLicenceTime = initialLicenceTime.substring(0,10);
//                initialLicenceTime = initialLicenceTime.replace("-", "/");
//                t.setInitialRegistrationDate(initialLicenceTime);
//            }
//        }
        return tradeInformations;
    }

    @Override
    public List<InvoicePerson> getInvoicePerson(String idCard, String marketId) {
        return invoiceMapper.selectByIdCard(idCard, marketId);
    }

    @Override
    public List deakManageExcel(Invoice invoice) {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        if (null != invoice.getRemark() && invoice.getRemark() == 2) {//查询开票申请列表
            criteria.andInvoicePortofNotEqualTo(2);//非窗口列表
            criteria.andInvoiceStatusNotEqualTo(0);//非作废状态
        }
        if (null != invoice.getRemark() && invoice.getRemark() == 1) {//查询开具发票列表
            criteria.andInvoiceStatusNotEqualTo(1);//查询为已处理和作废状态
            criteria.andUserIdEqualTo(invoice.getUserId());
        }
        if (null != invoice.getMarketId() && !invoice.getMarketId().equals("")) {
            criteria.andMarketIdEqualTo(invoice.getMarketId());
        }
        if (null != invoice.getPurchacerName() && !invoice.getPurchacerName().equals("")) {
            criteria.andPurchacerNameLike("%" + invoice.getPurchacerName() + "%");
        }
        if (null != invoice.getSellerName() && !invoice.getSellerName().equals("")) {
            criteria.andSellerNameLike("%" + invoice.getSellerName() + "%");
        }
        if (null != invoice.getInvoiceNo() && !invoice.getInvoiceNo().equals("")) {
            criteria.andInvoiceNoLike("%" + invoice.getInvoiceNo() + "%");
        }
        if (null != invoice.getBillTimeStart() && null != invoice.getBillTimeEnd()) {
            criteria.andBillTimeGreaterThanOrEqualTo(DateUtils.getDateFromString(invoice.getBillTimeStart(), "yyyy-MM-dd"));
            criteria.andBillTimeLessThanOrEqualTo(DateUtils.getDateFromString(invoice.getBillTimeEnd(), "yyyy-MM-dd"));
        }
        if (null != invoice.getSyncTimeStart() && null != invoice.getSyncTimeEnd()) {
            criteria.andSyncTimeGreaterThanOrEqualTo(DateUtils.getDateFromString(invoice.getSyncTimeStart(), "yyyy-MM-dd"));
            criteria.andSyncTimeLessThanOrEqualTo(DateUtils.getDateFromString(invoice.getSyncTimeEnd(), "yyyy-MM-dd"));
        }
        if (null != invoice.getInvoicePortof()) {
            criteria.andInvoicePortofEqualTo(invoice.getInvoicePortof());
        }
        if (null != invoice.getInvoiceStatus()) {
            criteria.andInvoiceStatusEqualTo(invoice.getInvoiceStatus());
        }
        if (null != invoice.getCarSources()) {
            criteria.andCarSourcesEqualTo(invoice.getCarSources());
        }
        if (null != invoice.getCurrentNo() && invoice.getCurrentNo() != "") {
            criteria.andCurrentNoEqualTo(invoice.getCurrentNo());
        }
        invoiceExample.setOrderByClause("insert_time desc");
        List<Invoice> lists = invoiceMapper.selectByExample(invoiceExample);
        List list = new ArrayList();
        for (Invoice invoice1 : lists) {
            Map<String, Object> map = new LinkedHashMap<>();
            //  发票代码
            String invoiceCode = invoice1.getInvoiceCode();
            if (StringUtil.isNotEmpty(invoiceCode)) {
                map.put("invoiceCode", invoiceCode);
            } else {
                map.put("invoiceCode", "");
            }
            //  发票号码
            String invoiceNo = invoice1.getInvoiceNo();
            if (StringUtil.isNotEmpty(invoiceNo)) {
                map.put("invoiceNo", invoiceNo);
            } else {
                map.put("invoiceNo", "");
            }
            //  开票时间
            Date billTime = invoice1.getBillTime();
            if (billTime != null) {
                map.put("billTime", billTime);
            } else {
                map.put("billTime", "");
            }
            //  买方
            String purchacerName = invoice1.getPurchacerName();
            if (StringUtil.isNotEmpty(purchacerName)) {
                map.put("purchacerName", purchacerName);
            } else {
                map.put("purchacerName", "");
            }
            //  卖方
            String sellerName = invoice1.getSellerName();
            if (StringUtil.isNotEmpty(sellerName)) {
                map.put("sellerName", sellerName);
            } else {
                map.put("sellerName", "");
            }
            //  车价合计
            Double price = invoice1.getPrice();
            if (price != null) {
                map.put("price", price);
            } else {
                map.put("price", "");
            }
            //  开票端口
            Integer invoicePortof = invoice1.getInvoicePortof();
            if (invoicePortof != null) {
                switch (invoicePortof) {
                    case 0:
                        map.put("invoicePortof", "自助交易终端");
                        break;
                    case 1:
                        map.put("invoicePortof", "商户app");
                        break;
                    case 2:
                        map.put("invoicePortof", "窗口");
                        break;
                }
            } else {
                map.put("invoicePortof", "");
            }
            //  车辆来源
            Integer carSources = invoice1.getCarSources();
            if (carSources != null) {
                switch (carSources) {
                    case 1:
                        map.put("carSources", "商品车");
                        break;
                    case 2:
                        map.put("carSources", "挂靠车");
                        break;
                    case 3:
                        map.put("carSources", "代办");
                        break;
                    case 4:
                        map.put("carSources", "散户");
                        break;
                }
            } else {
                map.put("carSources", "");
            }
            Integer invoiceStatus = invoice1.getInvoiceStatus();
            if (invoiceStatus != null) {
                switch (invoiceStatus) {
                    case 0:
                        map.put("invoiceStatus", "作废");
                        break;
                    case 1:
                        map.put("invoiceStatus", "未处理");
                        break;
                    case 2:
                        map.put("invoiceStatus", "已处理");
                        break;
                }
            } else {
                map.put("invoiceStatus", "");
            }
            list.add(map);
        }
        return list;
    }

//    @Override
//    public InterfaceResult insertInvoice(Invoice invoice) {
//        InterfaceResult interfaceResult = new InterfaceResult();
//        int count = 0;
//        int counts = 0;
//        List<InvoicePurchase> list = invoicePurchaseService.selectInvoicePurchase(invoice.getMarketId());
//        if (null != list && list.size() > 0) {
//            invoice.setInvoiceCode(list.get(0).getInvoiceCode());
//            invoice.setInvoiceNo(list.get(0).getInvoiceNo());
//            count = invoiceMapper.insertSelective(invoice);
//            if (count == 0) {
//                interfaceResult.InterfaceResult600("新增失败");
//            } else {
//                list.get(0).setPollResidue(list.get(0).getPollResidue() - 1);//剩余票号-1
//                if (Integer.parseInt(list.get(0).getInvoiceEndNo()) == Integer.parseInt(invoice.getInvoiceNo())) {
//                    list.get(0).setStatus(2);//购票最后一张
//                    list.get(0).setInvoiceNo(list.get(0).getInvoiceEndNo());
//                } else {
//                    list.get(0).setInvoiceNo(StringUtils.leftPad(((Integer.parseInt(list.get(0).getInvoiceNo()) + 1) + ""), 8, "0"));//当前票号+1
//                }
//                counts = invoicePurchaseService.updateByIdAndVersion(list.get(0));
//                if (counts == 0) {
//                    interfaceResult.InterfaceResult600("购票异常，请重新购票!");
//                    return interfaceResult;
//                }
//                if (invoice.getCarSources() == 1) {//库存车
//                    Car car = new Car();
//                    car.setId(invoice.getCarId());
//                    car.setStockStatus(4);//售出未出场状态
//                    carService.updateByPrimaryKeySelective(car);
//                }
//                interfaceResult.InterfaceResult200("新增成功");
//            }
//        } else {
//            interfaceResult.InterfaceResult600("发票已用完，请购票");
//        }
//        return interfaceResult;
//    }

}
