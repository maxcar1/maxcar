package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.statistics.dao.*;
import com.maxcar.statistics.model.entity.*;
import com.maxcar.statistics.service.InsertStockAndInvoice;
import com.maxcar.user.entity.Market;
import com.maxcar.user.service.MarketService;
import org.apache.commons.lang.math.DoubleRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("insertStockAndInvoice")
public class InsertStockAndInvoiceImpl implements InsertStockAndInvoice {

    @Autowired
    private CarpriceDayMapper carpriceDayMapper;

    @Autowired
    private CarStockDayMapper carStockDayMapper;

    @Autowired
    private CarStockMonthMapper carStockMonthMapper;

    @Autowired
    private InventoryInvoiceDayMapper inventoryInvoiceDayMapper;

    @Autowired
    private InventoryInvoiceMonthMapper inventoryInvoiceMonthMapper;

    @Autowired
    private PropertyContractService propertyContractService;

    @Autowired
    private StockAvgDayMapper stockAvgDayMapper;

    @Autowired
    private MarketService marketService;

    @Override
    public void InsertCarpriceDay() throws Exception {
        for (int i = 0; i < 11; i++) {
            int priceStart = 0;
            int priceEnd = 0;
            switch (i) {
                case 0:
                    priceStart = 0;
                    priceEnd = 50000;
                    break;
                case 1:
                    priceStart = 50000;
                    priceEnd = 100000;
                    break;
                case 2:
                    priceStart = 100000;
                    priceEnd = 150000;
                    break;
                case 3:
                    priceStart = 150000;
                    priceEnd = 200000;
                    break;
                case 4:
                    priceStart = 200000;
                    priceEnd = 250000;
                    break;
                case 5:
                    priceStart = 250000;
                    priceEnd = 300000;
                    break;
                case 6:
                    priceStart = 300000;
                    priceEnd = 350000;
                    break;
                case 7:
                    priceStart = 350000;
                    priceEnd = 400000;
                    break;
                case 8:
                    priceStart = 400000;
                    priceEnd = 450000;
                    break;
                case 9:
                    priceStart = 450000;
                    priceEnd = 500000;
                    break;
                case 10:
                    priceStart = 500000;
                    priceEnd = 0;
                    break;
            }
            List<CarpriceDayEntity> carpriceDayEntities = carpriceDayMapper.selectCarpriceDay(priceStart, priceEnd);
            if (carpriceDayEntities != null && carpriceDayEntities.size() > 0) {
                for (CarpriceDayEntity car : carpriceDayEntities) {
                    car.setInvoicePriceId(i + "");
                    String invoicePriceName = "";
                    switch (i) {
                        case 0:
                            invoicePriceName = "5万以下";
                            break;
                        case 1:
                            invoicePriceName = "5-10万";
                            break;
                        case 2:
                            invoicePriceName = "10-15万";
                            break;
                        case 3:
                            invoicePriceName = "15-20万";
                            break;
                        case 4:
                            invoicePriceName = "20-25万";
                            break;
                        case 5:
                            invoicePriceName = "25-30万";
                            break;
                        case 6:
                            invoicePriceName = "30-35万";
                            break;
                        case 7:
                            invoicePriceName = "35-40万";
                            break;
                        case 8:
                            invoicePriceName = "40-45万";
                            break;
                        case 9:
                            invoicePriceName = "45-50万";
                            break;
                        case 10:
                            invoicePriceName = "50万以上";
                            break;
                    }
                    car.setInvoicePriceName(invoicePriceName);
                    car.setIsvalid(1);
                    car.setVersion(1);
                    car.setRegisterTime(new Date());
                    carpriceDayMapper.insert(car);
                }
            }
        }
    }

    @Override
    public void InsertCarstockDay() throws Exception {
        for (int i = 0; i < 11; i++) {
            int priceStart = 0;
            int priceEnd = 0;
            switch (i) {
                case 0:
                    priceStart = 0;
                    priceEnd = 5;
                    break;
                case 1:
                    priceStart = 5;
                    priceEnd = 10;
                    break;
                case 2:
                    priceStart = 10;
                    priceEnd = 15;
                    break;
                case 3:
                    priceStart = 15;
                    priceEnd = 20;
                    break;
                case 4:
                    priceStart = 20;
                    priceEnd = 25;
                    break;
                case 5:
                    priceStart = 25;
                    priceEnd = 30;
                    break;
                case 6:
                    priceStart = 30;
                    priceEnd = 35;
                    break;
                case 7:
                    priceStart = 35;
                    priceEnd = 40;
                    break;
                case 8:
                    priceStart = 40;
                    priceEnd = 45;
                    break;
                case 9:
                    priceStart = 45;
                    priceEnd = 50;
                    break;
                case 10:
                    priceStart = 50;
                    priceEnd = 0;
                    break;
            }
            List<CarStockDayEntity> carStockDayEntities = carStockDayMapper.selectCarpriceDay(priceStart, priceEnd);
            if (carStockDayEntities.size() > 0) {
                for (CarStockDayEntity car : carStockDayEntities) {
                    car.setInvoiceStockId(i + "");
                    String invoiceStockName = "";
                    switch (i) {
                        case 0:
                            invoiceStockName = "5万以下";
                            break;
                        case 1:
                            invoiceStockName = "5-10万";
                            break;
                        case 2:
                            invoiceStockName = "10-15万";
                            break;
                        case 3:
                            invoiceStockName = "15-20万";
                            break;
                        case 4:
                            invoiceStockName = "20-25万";
                            break;
                        case 5:
                            invoiceStockName = "25-30万";
                            break;
                        case 6:
                            invoiceStockName = "30-35万";
                            break;
                        case 7:
                            invoiceStockName = "35-40万";
                            break;
                        case 8:
                            invoiceStockName = "40-45万";
                            break;
                        case 9:
                            invoiceStockName = "45-50万";
                            break;
                        case 10:
                            invoiceStockName = "50万以上";
                            break;
                    }
                    car.setInvoiceStockName(invoiceStockName);
                    car.setIsvalid(1);
                    car.setVersion(1);
                    car.setRegisterTime(new Date());
                    carStockDayMapper.insert(car);
                }
            }
        }
    }

    @Override
    public void InsertCarstockMonth() throws Exception {
        for (int i = 0; i < 11; i++) {
            int priceStart = 0;
            int priceEnd = 0;
            switch (i) {
                case 0:
                    priceStart = 0;
                    priceEnd = 5;
                    break;
                case 1:
                    priceStart = 5;
                    priceEnd = 10;
                    break;
                case 2:
                    priceStart = 10;
                    priceEnd = 15;
                    break;
                case 3:
                    priceStart = 15;
                    priceEnd = 20;
                    break;
                case 4:
                    priceStart = 20;
                    priceEnd = 25;
                    break;
                case 5:
                    priceStart = 25;
                    priceEnd = 30;
                    break;
                case 6:
                    priceStart = 30;
                    priceEnd = 35;
                    break;
                case 7:
                    priceStart = 35;
                    priceEnd = 40;
                    break;
                case 8:
                    priceStart = 40;
                    priceEnd = 45;
                    break;
                case 9:
                    priceStart = 45;
                    priceEnd = 50;
                    break;
                case 10:
                    priceStart = 50;
                    priceEnd = 0;
                    break;
            }
            List<CarStockMonthEntity> carStockMonthEntities = carStockMonthMapper.selectCarstockMonth(priceStart, priceEnd);
            if (carStockMonthEntities.size() > 0) {
                for (CarStockMonthEntity car : carStockMonthEntities) {
                    car.setInvoiceStockId(i + "");
                    String invoiceStockName = "";
                    switch (i) {
                        case 0:
                            invoiceStockName = "5万以下";
                            break;
                        case 1:
                            invoiceStockName = "5-10万";
                            break;
                        case 2:
                            invoiceStockName = "10-15万";
                            break;
                        case 3:
                            invoiceStockName = "15-20万";
                            break;
                        case 4:
                            invoiceStockName = "20-25万";
                            break;
                        case 5:
                            invoiceStockName = "25-30万";
                            break;
                        case 6:
                            invoiceStockName = "30-35万";
                            break;
                        case 7:
                            invoiceStockName = "35-40万";
                            break;
                        case 8:
                            invoiceStockName = "40-45万";
                            break;
                        case 9:
                            invoiceStockName = "45-50万";
                            break;
                        case 10:
                            invoiceStockName = "50万以上";
                            break;
                    }
                    car.setInvoiceStockName(invoiceStockName);
                    car.setIsvalid(1);
                    car.setVersion(1);
                    car.setRegisterTime(new Date());
                    carStockMonthMapper.insert(car);
                }
            }
        }
    }

    @Override
    public void InsertInventoryInvoiceDay() throws Exception {
        List<InventoryInvoiceDayEntity> inventoryInvoiceDayEntities = inventoryInvoiceDayMapper.selectInventoryInvoiceDay();
        for (InventoryInvoiceDayEntity invpice : inventoryInvoiceDayEntities) {
            String tenantId = invpice.getTenantId();
            String marketId = invpice.getMarketId();

            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
            requests.setMarketId(marketId);
            if (StringUtil.isNotEmpty(tenantId)) {
                requests.setTenantId(tenantId);
            }
            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
            if (responses == null) {
                System.out.println("定时任务 库存交易，" + marketId + "市场没有配置浮动车位");
            }
            int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal());
            invpice.setTenantSpace(parkCount);
            invpice.setIsvalid(1);
            invpice.setVersion(1);
            invpice.setRegisterTime(new Date());
            inventoryInvoiceDayMapper.insert(invpice);
        }
    }

    @Override
    public void InsertInventoryInvoiceMonth() throws Exception {
        List<InventoryInvoiceMonthEntity> inventoryInvoiceMonthEntities = inventoryInvoiceMonthMapper.selectInventoryInvoiceMonth();
        for (InventoryInvoiceMonthEntity invpice : inventoryInvoiceMonthEntities) {
            String tenantId = invpice.getTenantId();
            String marketId = invpice.getMarketId();

            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
            requests.setMarketId(marketId);
            if (StringUtil.isNotEmpty(tenantId)) {
                requests.setTenantId(tenantId);
            }
            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
            if (responses == null) {
                System.out.println("定时任务 库存交易，" + marketId + "市场没有配置浮动车位");
            }
            int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal());
            invpice.setTenantSpace(parkCount);

            invpice.setIsvalid(1);
            invpice.setVersion(1);
            invpice.setRegisterTime(new Date());
            inventoryInvoiceMonthMapper.insert(invpice);
        }
    }

    @Override
    public void InsertStockAvgDay() throws Exception {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            int start = 0;
            int end = 0;
            String name = "";
            switch (i) {
                case 0:
                    start = 0;
                    end = 10;
                    name = "10天以下";
                    break;
                case 1:
                    start = 10;
                    end = 20;
                    name = "10-20天";
                    break;
                case 2:
                    start = 20;
                    end = 30;
                    name = "20-30天";
                    break;
                case 3:
                    start = 30;
                    end = 40;
                    name = "30-40天";
                    break;
                case 4:
                    start = 40;
                    end = 50;
                    name = "40-50天";
                    break;
                case 5:
                    start = 50;
                    end = 60;
                    name = "50-60天";
                    break;
                case 6:
                    start = 60;
                    end = 70;
                    name = "60-70天";
                    break;
                case 7:
                    start = 70;
                    end = 80;
                    name = "70-80天";
                    break;
                case 8:
                    start = 80;
                    end = 90;
                    name = "80-90天";
                    break;
                case 9:
                    start = 90;
                    end = 0;
                    name = "90天以上";
                    break;
            }
            map.put("start",start);
            map.put("end",end);

            List<Market> markets = marketService.selectAll();
            for(Market market : markets){
                String marketNo = market.getMarketNo();
                map.put("marketId",marketNo);
                //  查交易
                Double dealCount = stockAvgDayMapper.sumDealNum(map);
                //  查库存
                Double stockCount = stockAvgDayMapper.sumStockNum(map);

                Date date = new Date();
                String reportTime = DateUtils.formatDate(date, DateUtils.DATE_FORMAT_DATEONLY);

                StockAvgDayEntity stockAvgDayEntity = new StockAvgDayEntity();
                stockAvgDayEntity.setReportTime(reportTime);
                stockAvgDayEntity.setMarketId(marketNo);
                stockAvgDayEntity.setStockDealAvgId(i);
                stockAvgDayEntity.setStockDealAvgNum(dealCount.intValue() + stockCount.intValue());
                stockAvgDayEntity.setStockDealAvgName(name);
                stockAvgDayEntity.setIsvalid(1);
                stockAvgDayEntity.setVersion(1);
                stockAvgDayEntity.setRegisterTime(date);

                stockAvgDayMapper.insert(stockAvgDayEntity);
            }
        }
    }
}
