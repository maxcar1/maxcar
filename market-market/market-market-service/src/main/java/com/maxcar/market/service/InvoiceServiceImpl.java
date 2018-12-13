package com.maxcar.market.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.response.InvoicePerson;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.pojo.TradeInformation;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import com.maxcar.tenant.pojo.UserTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if(StringUtil.isNotEmpty(invoice.getUserId())){
                criteria.andUserIdEqualTo(invoice.getUserId());
            }
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
    public List<TradeInformation> detailsExcel(Invoice invoice) {
        List<TradeInformation> tradeInformations = invoiceMapper.detailsExcel(invoice);
        for(TradeInformation t : tradeInformations){
            String billTime = t.getBillTime();
            billTime = billTime.substring(0, 10);
            billTime = billTime.replace("-", "/");
            t.setBillTime(billTime);
            t.setDealTime(billTime);
//            String dealTime = t.getDealTime();
//            if(StringUtil.isNotEmpty(dealTime)){
//                dealTime = dealTime.substring(0,10);
//                dealTime = dealTime.replace("-", "/");
//                t.setDealTime(dealTime);
//            }
            String initialLicenceTime = t.getInitialRegistrationDate();
            if(StringUtil.isNotEmpty(initialLicenceTime)){
                initialLicenceTime = initialLicenceTime.substring(0,10);
                initialLicenceTime = initialLicenceTime.replace("-", "/");
                t.setInitialRegistrationDate(initialLicenceTime);
            }
        }
        return tradeInformations;
    }

    @Override
    public List<InvoicePerson> getInvoicePerson(String idCard, String marketId) {
        return invoiceMapper.selectByIdCard(idCard, marketId);
    }

    @Override
    public double selectPriceByCarId(String carId) {
        return invoiceMapper.selectPriceByCarId(carId) == null ? 0 : invoiceMapper.selectPriceByCarId(carId);
    }

    @Override
    public List<Invoice> detailsManage(Invoice invoice) {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        if (null != invoice.getRemark() && invoice.getRemark() == 2) {//查询开票申请列表
            criteria.andInvoicePortofNotEqualTo(2);//非窗口列表
            criteria.andInvoiceStatusNotEqualTo(0);//非作废状态
        }
        if (null != invoice.getRemark() && invoice.getRemark() == 1) {//查询开具发票列表
            criteria.andInvoiceStatusNotEqualTo(1);//查询为已处理和作废状态
            if(StringUtil.isNotEmpty(invoice.getUserId())){
                criteria.andUserIdEqualTo(invoice.getUserId());
            }
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
        return lists;
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
