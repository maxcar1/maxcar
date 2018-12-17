package com.maxcar.market.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.response.InvoicePerson;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.pojo.TradeInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            if (StringUtil.isNotEmpty(invoice.getUserId())) {
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
        for (TradeInformation t : tradeInformations) {
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
            if (StringUtil.isNotEmpty(initialLicenceTime)) {
                initialLicenceTime = initialLicenceTime.substring(0, 10);
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
            if (StringUtil.isNotEmpty(invoice.getUserId())) {
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
    @Override
    public Map nowDeal(String marketId, String tenantId) throws Exception {
        Date dayStart = DateUtils.getDayStart(new Date());
        Date dayEnd = DateUtils.getDayEnd(new Date());
        Map<String, Object> map = invoiceMapper.nowDeal(marketId, tenantId, dayStart, dayEnd);
        Map<String, Object> stockMap = invoiceMapper.nowCommercialDeal(marketId, tenantId, dayStart, dayEnd);
        Object sum = map.get("sum");
        // 交易总价值
        double nowSum = 0.0;
        if (sum != null) {
            nowSum = Double.parseDouble(sum.toString());
            nowSum = Math.round(nowSum * 100) / 100.0;
        }
        // 平均交易价格
        Object avg = map.get("avg");
        double nowAvg = 0.0;
        if (avg != null) {
            nowAvg = Double.parseDouble(avg.toString());
            nowAvg = Math.round(nowAvg * 100) / 100.0;
            map.put("avg", nowAvg);
        }
        // 车辆交易总量
        Object count = map.get("count");
        double nowCount = 0.0;
        if (count != null) {
            nowCount = Double.parseDouble(count.toString());
            nowCount = Math.round(nowCount * 100) / 100.0;
        }
        // 商品车交易总量
        Object stockSum = stockMap.get("stockSum");
        double nowStockSum = 0.0;
        if (stockSum != null) {
            String s = stockSum.toString();
            map.put("stockSum", s);
            nowStockSum = Double.parseDouble(s);
        }
        // 商品车平均交易价格
        Object stockAvg = stockMap.get("stockAvg");
        double nowStockAvg = 0.0;
        if (stockAvg != null) {
            String s = stockAvg.toString();
            map.put("stockAvg", s);
            nowStockAvg = Double.parseDouble(s);
            nowStockAvg = Math.round(nowStockAvg * 100) / 100.0;
            map.put("stockAvg", nowStockAvg);
        }
        // 商品车交易总量
        Object stockCount = stockMap.get("stockCount");
        double nowStockCount = 0.0;
        if (stockCount != null) {
            String s = stockCount.toString();
            map.put("stockCount", s);
            nowStockCount = Double.parseDouble(s);
        }
        // 昨日的数据
        String yesterday = DateUtils.getYesterday();
        Date date = DateUtils.parseDate(yesterday, DateUtils.DATE_FORMAT_DATEONLY);
        dayStart = DateUtils.getDayStart(date);
        dayEnd = DateUtils.getDayEnd(date);
        Map<String, Object> yesterMap = invoiceMapper.nowDeal(marketId, tenantId, dayStart, dayEnd);
        Map<String, Object> yesterStockMap = invoiceMapper.nowCommercialDeal(marketId, tenantId, dayStart, dayEnd);
        sum = yesterMap.get("sum");
        double yesterSum = 0.0;
        if (sum != null) {
            yesterSum = Double.parseDouble(sum.toString());
            yesterSum = Math.round(yesterSum * 100) / 100.0;
        }
        avg = yesterMap.get("avg");
        double yesterAvg = 0.0;
        if (avg != null) {
            yesterAvg = Double.parseDouble(avg.toString());
            yesterAvg = Math.round(yesterAvg * 100) / 100.0;
        }
        count = yesterMap.get("count");
        double yesterCount = 0.0;
        if (count != null) {
            yesterCount = Double.parseDouble(count.toString());
        }

        stockSum = yesterStockMap.get("stockSum");
        double yesterStockSum = 0.0;
        if (stockSum != null) {
            yesterStockSum = Double.parseDouble(stockSum.toString());
            yesterStockSum = Math.round(yesterStockSum) / 10000.0;
        }

        stockAvg = yesterStockMap.get("stockAvg");
        double yesterStockAvg = 0.0;
        if (stockAvg != null) {
            yesterStockAvg = Double.parseDouble(stockAvg.toString());
            yesterStockAvg = Math.round(yesterStockAvg * 100) / 100.0;
            map.put("contrastStockAvg", yesterStockAvg);
        }

        stockCount = yesterStockMap.get("stockCount");
        double yesterStockCount = 0.0;
        if (stockCount != null) {
            yesterStockCount = Double.parseDouble(stockCount.toString());
        }
        double contrastSum = nowSum - yesterSum;
        if (yesterSum == 0) {
            map.put("increaseContrastSum", "NA");
        } else {
            double compare = Math.round(contrastSum / yesterSum * 100) / 100.0;
            map.put("increaseContrastSum", compare);
        }
        map.put("contrastSum", contrastSum);
        double contrastCount = nowCount - yesterCount;
        if (yesterCount == 0) {
            map.put("increaseContrastCount", "NA");
        } else {
            double compare = Math.round(contrastCount / yesterCount * 100) / 100.0;
            map.put("increaseContrastCount", compare);
        }
        map.put("contrastCount", contrastCount);
        double contrastAvg = nowAvg - yesterAvg;
        if (yesterAvg == 0) {
            map.put("increaseContrastAvg", "NA");
        } else {
            double compare = Math.round(contrastAvg / yesterAvg * 100) / 100.0;
            map.put("increaseContrastAvg", compare);
        }
        map.put("contrastAvg", contrastAvg);
        double contrastStockSum = nowStockSum - yesterStockSum;
        if (yesterStockSum == 0) {
            map.put("increaseContrastStockSum", "NA");
        } else {
            double compare = Math.round(contrastStockSum / yesterStockSum * 100) / 100.0;
            map.put("increaseContrastStockSum", compare);
        }
        map.put("contrastStockSum", contrastStockSum);
        double contrastStockCount = nowStockCount - yesterStockCount;
        if (yesterStockCount == 0) {
            map.put("increaseContrastStockCount", "NA");
        } else {
            double compare = Math.round(contrastStockCount / yesterStockCount * 100) / 100.0;
            map.put("increaseContrastStockCount", compare);
        }
        map.put("contrastStockCount", contrastStockCount);
        double contrastStockAvg = nowStockAvg - yesterStockAvg;
        if (yesterStockAvg == 0) {
            map.put("increaseContrastStockAvg", "NA");
        } else {
            double compare = Math.round(contrastStockAvg / yesterStockAvg * 100) / 100.0;
            map.put("increaseContrastStockAvg", compare);
        }

        return map;
    }

}