package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.SelectInvoice;
import com.maxcar.market.model.response.DealResponse;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service("dealService")
public class DealServiceImpl implements DealService {

    @Autowired
    InvoiceMapper invoiceMapper;

    @Override
    public Map<String, Object> selectDeal(DealRequest deal) {
        //  已取消该功能
//        String carNo = deal.getCarNo();
//        if(carNo != null && (!"".equals(carNo))){
//            String trim = carNo.trim();carStatus
//            deal.setCarNo("%"+ trim + "%");
//        }

        //  vin
        String vin = deal.getVin();
        if (StringUtil.isNotEmpty(vin)) {
            String trim = vin.trim();
            deal.setVin("%" + trim + "%");
        }

        //  负责人名
        String contactName = deal.getContactName();
        if (StringUtil.isNotEmpty(contactName)) {
            String trim = contactName.trim();
            deal.setContactName(trim);
        }

        String dealTimeEnd = deal.getDealTimeEnd();

        if (StringUtil.isNotEmpty(dealTimeEnd)) {
            try {
                Date date = DateUtils.parseDate(dealTimeEnd, "yyyy-MM-dd");
                Date dayEnd = DateUtils.getDayEnd(date);
//                Date addDay = DateUtils.addDay(date, 1);
                deal.setDealTimeEnd(DateUtils.formatDate(dayEnd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

//        List<String> carStatus = deal.getCarStatus();
//        if (null != carStatus && !carStatus.isEmpty()) {
//            if (carStatus.size() == 4) {
//                deal.setUnsoldCar(1);
//                deal.setTenantCar(2);
//                deal.setElseCar(3);
//            } else {
//                for (String c : carStatus) {
//                    Integer car = 0;
//                    if (!"".equals(c)) {
//                        car = Integer.parseInt(c);
//                    }
//                    if ("".equals(c)) {
//                        deal.setUnsoldCar(1);
//                        deal.setTenantCar(2);
//                        deal.setElseCar(3);
//                    }
//                    if (1 == car) {
//                        deal.setUnsoldCar(1);
//                    } else if (2 == car) {
//                        deal.setTenantCar(2);
//                    } else if (3 == car) {
//                        deal.setElseCar(3);
//                    }
//                }
//            }
//        } else {
//            deal.setUnsoldCar(1);
//            deal.setTenantCar(2);
//            deal.setElseCar(3);
//        }

//        String fuelForm = deal.getFuelForm();
//        if ("1".equals(fuelForm)) {
//            deal.setFuelForm("汽油");
//        } else if ("2".equals(fuelForm)) {
//            deal.setFuelForm("柴油");
//        } else if ("3".equals(fuelForm)) {
//            deal.setFuelForm("纯电");
//        } else if ("4".equals(fuelForm)) {
//            deal.setFuelForm("油电混合");
//        } else if ("0".equals(fuelForm)) {
//            deal.setFuelForm(null);
//        }

        PageHelper.startPage(deal.getCurPage(), deal.getPageSize());
        List<DealResponse> dealResponse = invoiceMapper.selectDeal(deal);
        for (DealResponse d : dealResponse) {
            String status = d.getCarStatus();
            if (StringUtil.isNotEmpty(status)) {
                if ("1".equals(status)) {
                    d.setCarStatus("商品车");
                } else if ("2".equals(status)) {
                    d.setCarStatus("挂靠");
                } else if ("3".equals(status)) {
                    d.setCarStatus("代办");
                } else if ("4".equals(status)) {
                    d.setCarStatus("散户");
                } else {
                    d.setCarStatus("");
                }

            }

            String stockStatus = d.getStockStatus();
            if (StringUtil.isNotEmpty(stockStatus)) {
                if ("4".equals(stockStatus)) {
                } else if ("5".equals(stockStatus)) {
                } else {
                    d.setStockStatus(null);
                }
            }

            String series = d.getSeries();
            if (StringUtil.isNotEmpty(series)) {
                String brandName = d.getBrandName();
                d.setSeries(series + "-" + brandName);
            } else {
                d.setSeries(series);
            }
        }
        PageInfo pageInfo = new PageInfo(dealResponse);
//        int total = invoiceMapper.countDeal(deal);
//        stockStatus(dealResponses);
//          市场总交易量
        int countDealNum = invoiceMapper.countDealNum(deal);
//          交易总价值
        Double sumDeal = invoiceMapper.sumDealMoney(deal);
        double sumDealMoney = (null == sumDeal ? 0 : sumDeal) / 10000;
//        //  库存车交易量
        int countStockCarNum = invoiceMapper.countStockCarNum(deal);
//        //  库存车交易总价值
//        Double sumStockCar = invoiceMapper.sumStockCarMoney(deal);
//
//        double sumStockCarMoney = (null == sumStockCar ? 0 : sumStockCar) / 10000;

        //  开票排名
        Long ranking = null;
        String tenantId = deal.getTenantId();
        Hashtable<String, Object> map = new Hashtable<>();
        if (tenantId != null && (!"".equals(tenantId))) {
            DealResponse dealResponse1 = invoiceMapper.selectRanking(tenantId, deal.getMarketId());
            System.out.print("===========+++++++++==" + dealResponse1 + "=================");
            if (null != dealResponse1 && null != dealResponse1.getRownum()) {
                ranking = Long.valueOf(String.valueOf(dealResponse1.getRownum()));
                map.put("ranking", ranking);
            } else {
                map.put("ranking", "");
            }
        }

        //  商品   挂靠   代办    散户  总数和价值
//        InvoiceExample invoiceExample = new InvoiceExample();
//        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        //  商品总数
        Double commodityCount = 0.0;
        Double commoditySum = 0.0;
        // 挂靠总数
        Double subordinateCount = 0.0;
        Double subordinateSum = 0.0;
        //  代办总数
        Double agencyCount = 0.0;
        Double agencySum = 0.0;
        // 散户总数
        Double retailCount = 0.0;
        Double retailSum = 0.0;

        if (deal.getCarSources() != null && deal.getCarSources() != 0) {
            Integer carSources = deal.getCarSources();
//            criteria.andMarketIdEqualTo(deal.getMarketId()).andCarSourcesEqualTo(carSources).andInvoiceStatusEqualTo(2);
            switch (carSources) {
                case 1:
                    commodityCount = invoiceMapper.countByCarSources(deal);
                    commoditySum = invoiceMapper.sumPrice(deal);
                    break;
                case 2:
                    subordinateCount = invoiceMapper.countByCarSources(deal);
                    subordinateSum = invoiceMapper.sumPrice(deal);
                    break;
                case 3:
                    agencyCount = invoiceMapper.countByCarSources(deal);
                    agencySum = invoiceMapper.sumPrice(deal);
                    break;
                case 4:
                    retailCount = invoiceMapper.countByCarSources(deal);
                    retailSum = invoiceMapper.sumPrice(deal);
                    break;
            }
        } else {
//            invoiceExample = new InvoiceExample();
//            criteria = invoiceExample.createCriteria();
//            criteria.andMarketIdEqualTo(deal.getMarketId()).andCarSourcesEqualTo(1).andInvoiceStatusEqualTo(2);
            deal.setCarSources(1);
            commodityCount = invoiceMapper.countByCarSources(deal);
            commoditySum = invoiceMapper.sumPrice(deal);

//            invoiceExample = new InvoiceExample();
//            criteria = invoiceExample.createCriteria();
//            criteria.andMarketIdEqualTo(deal.getMarketId()).andCarSourcesEqualTo(2).andInvoiceStatusEqualTo(2);
            deal.setCarSources(2);
            subordinateCount = invoiceMapper.countByCarSources(deal);
            subordinateSum = invoiceMapper.sumPrice(deal);

//            invoiceExample = new InvoiceExample();
//            criteria = invoiceExample.createCriteria();
//            criteria.andMarketIdEqualTo(deal.getMarketId()).andCarSourcesEqualTo(3).andInvoiceStatusEqualTo(2);
            deal.setCarSources(3);
            agencyCount = invoiceMapper.countByCarSources(deal);
            agencySum = invoiceMapper.sumPrice(deal);

//            invoiceExample = new InvoiceExample();
//            criteria = invoiceExample.createCriteria();
//            criteria.andMarketIdEqualTo(deal.getMarketId()).andCarSourcesEqualTo(4).andInvoiceStatusEqualTo(2);
            deal.setCarSources(4);
            retailCount = invoiceMapper.countByCarSources(deal);
            retailSum = invoiceMapper.sumPrice(deal);
        }

        map.put("commodityCount", (commodityCount == null ? 0 : commodityCount));
        map.put("commoditySum", (commoditySum == null ? 0 : commoditySum / 10000));
        map.put("subordinateCount", (subordinateCount == null ? 0 : subordinateCount));
        map.put("subordinateSum", (subordinateSum == null ? 0 : subordinateSum / 10000));
        map.put("agencyCount", (agencyCount == null ? 0 : agencyCount));
        map.put("agencySum", (agencySum == null ? 0 : agencySum / 10000));
        map.put("retailCount", (retailCount == null ? 0 : retailCount));
        map.put("retailSum", (retailSum == null ? 0 : retailSum / 10000));


        map.put("countDealNum", countDealNum);
        map.put("sumDealMoney", sumDealMoney);
        map.put("countStockCarNum", countStockCarNum);
//        map.put("sumStockCarMoney", sumStockCarMoney);
        map.put("list", pageInfo);
//        map.put("total",total);

        return map;
    }

    @Override
    public List<DealResponse> selectByVin(Map map) {
        Integer curPage = (Integer) map.get("curPage");
        Integer pageSize = (Integer) map.get("pageSize");

//        if (null == curPage | "".equals(curPage) || curPage.intValue() <= 1) {
//            curPage = 0;
//        } else {
//            curPage = (curPage - 1) * pageSize;
//        }
//        map.put("curPage", curPage);

        List<DealResponse> list = invoiceMapper.selectByVin(map);
        for (DealResponse d : list) {
            String stockStatus = d.getStockStatus();
            if (StringUtil.isNotEmpty(stockStatus)) {
                if ("4".equals(stockStatus)) {
                } else if ("5".equals(stockStatus)) {
                } else {
                    d.setStockStatus(null);
                }
            }
        }
        stockStatus(list);
        return list;
    }

    @Override
    public PageInfo selectInvoice(SelectInvoice invoice) throws ParseException {
        String vin = invoice.getVin();
        String billTimeStart = invoice.getBillTimeStart();
        String billTimeEnd = invoice.getBillTimeEnd();
        String invoiceNo = invoice.getInvoiceNo();
        if (StringUtil.isNotEmpty(vin)) {
            vin = vin.trim();
            vin = "%" + vin + "%";
            invoice.setVin(vin);
        }
        if (StringUtil.isNotEmpty(billTimeStart)) {
            Date dateEnd = DateUtils.parseDate(billTimeEnd, DateUtils.DATE_FORMAT_DATEONLY);
            Date dayEnd = DateUtils.getDayEnd(dateEnd);
            Date dayStart = DateUtils.parseDate(billTimeStart, DateUtils.DATE_FORMAT_DATEONLY);
            billTimeEnd = DateUtils.formatDate(dayEnd, DateUtils.DATE_FORMAT_DATETIME);
            billTimeStart = DateUtils.formatDate(dayStart, DateUtils.DATE_FORMAT_DATETIME);
            invoice.setBillTimeStart(billTimeStart);
            invoice.setBillTimeEnd(billTimeEnd);
        }
        if (StringUtil.isNotEmpty(invoiceNo)) {
            invoiceNo = invoiceNo.trim();
            invoiceNo = "%" + invoiceNo + "%";
            invoice.setInvoiceNo(invoiceNo);
        }
        PageHelper.startPage(invoice.getCurPage(), invoice.getPageSize());
        List<Invoice> invoices = invoiceMapper.selectInvoice2(invoice);
        PageInfo pageInfo = new PageInfo(invoices);
        return pageInfo;
    }

    private void DatePackage(SelectInvoice invoice) {
        String billTimeEnd = invoice.getBillTimeEnd();
        if (billTimeEnd != null) {
            try {
                Date date = DateUtils.parseDate(billTimeEnd, "yyyy-MM-dd");
                Date addDay = DateUtils.getDayEnd(date);
                invoice.setBillTimeEnd(DateUtils.formatDate(addDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int countInvoice(SelectInvoice invoice) {
        String vin = invoice.getVin();
        if (vin != null && (!"".equals(vin))) {
            String trim = vin.trim();
            invoice.setVin("%" + trim + "%");
        }
        String invoiceNo = invoice.getInvoiceNo();
        if (invoiceNo != null && (!"".equals(invoiceNo))) {
            String trim = invoiceNo.trim();
            invoice.setInvoiceNo("%" + trim + "%");
        }
        //  封装结束查询时间
        DatePackage(invoice);
        return invoiceMapper.countInvoice(invoice);
    }


    @Override
    public List<DealResponse> selectDealExport(DealRequest deal) {
        String vin = deal.getVin();
        if (StringUtil.isNotEmpty(vin)) {
            String trim = vin.trim();
            deal.setVin("%" + trim + "%");
        }

        //  负责人名
        String contactName = deal.getContactName();
        if (StringUtil.isNotEmpty(contactName)) {
            String trim = contactName.trim();
            deal.setContactName(trim);
        }

        String dealTimeEnd = deal.getDealTimeEnd();

        if (StringUtil.isNotEmpty(dealTimeEnd)) {
            try {
                Date date = DateUtils.parseDate(dealTimeEnd, "yyyy-MM-dd");
                Date dayEnd = DateUtils.getDayEnd(date);
//                Date addDay = DateUtils.addDay(date, 1);
                deal.setDealTimeEnd(DateUtils.formatDate(dayEnd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

//        List<String> carStatus = deal.getCarStatus();
//        if (null != carStatus && !carStatus.isEmpty()) {
//            if (carStatus.size() == 4) {
//                deal.setUnsoldCar(1);
//                deal.setTenantCar(2);
//                deal.setElseCar(3);
//            } else {
//                for (String c : carStatus) {
//                    Integer car = 0;
//                    if (!"".equals(c)) {
//                        car = Integer.parseInt(c);
//                    }
//                    if ("".equals(c)) {
//                        deal.setUnsoldCar(1);
//                        deal.setTenantCar(2);
//                        deal.setElseCar(3);
//                    }
//                    if (1 == car) {
//                        deal.setUnsoldCar(1);
//                    } else if (2 == car) {
//                        deal.setTenantCar(2);
//                    } else if (3 == car) {
//                        deal.setElseCar(3);
//                    }
//                }
//            }
//        } else {
//            deal.setUnsoldCar(1);
//            deal.setTenantCar(2);
//            deal.setElseCar(3);
//        }

//        List<DealResponse> list = invoiceMapper.selectDealExport(deal);

//        stockStatus(list);

        List<DealResponse> dealResponses = invoiceMapper.selectDeal(deal);

//        stockStatus(dealResponses);
        return dealResponses;
    }

    @Override
    public List<Map> selectInvoiceExport(SelectInvoice invoice) throws ParseException {
        PageInfo pageInfo = selectInvoice(invoice);
        List<Invoice> list = pageInfo.getList();
        List<Map> list1 = new ArrayList<>();
        Map<String, Object> map = null;
        for (Invoice i : list) {
            map = new LinkedHashMap<>();
            map.put("vin", i.getVin());
            if (i.getTenantName() == null) {
                map.put("tenantName", "");
            } else {
                map.put("tenantName", i.getTenantName());
            }
            map.put("invoiceNo", i.getInvoiceNo());
            Date billTime = i.getBillTime();
            String s = DateUtils.formatDate(billTime, DateUtils.DATE_FORMAT_DATEONLY);
            map.put("billTime", s);
            list1.add(map);
        }
        return list1;
    }

    @Override
    public int countByVin(String vin) {
        return invoiceMapper.countByVin(vin);

    }


    /**
     * 判断交易车辆来源
     *
     * @param list
     */
    private void stockStatus(List<DealResponse> list) {
        for (DealResponse deal : list) {
            if (deal.getCvin() != null && deal.getCvin() != "") {
                deal.setCarStatus("库存车");

            } else if (deal.getTenantId() != null && !deal.getTenantId().equals("0") && !deal.getTenantId().equals("")) {
                deal.setCarStatus("挂靠车商");
            } else {
                deal.setCarStatus("非车商");
            }
        }
    }
}
