package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.statistics.dao.*;
import com.maxcar.statistics.model.entity.*;
import com.maxcar.statistics.service.InsertStockAndInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        for( int i = 0; i< 11; i++){
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
        for( int i = 0; i< 11; i++){
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
        for(InventoryInvoiceDayEntity invpice : inventoryInvoiceDayEntities){
            String tenantId = invpice.getTenantId();
            String marketId = invpice.getMarketId();

            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
            requests.setMarketId(marketId);
            if(StringUtil.isNotEmpty(tenantId)) {
                requests.setTenantId(tenantId);
            }
            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
            if(responses == null){
                System.out.println("定时任务 库存交易，"+marketId+"市场没有配置浮动车位");
            }
            int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal() );
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
        for(InventoryInvoiceMonthEntity invpice : inventoryInvoiceMonthEntities){
            String tenantId = invpice.getTenantId();
            String marketId = invpice.getMarketId();

            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
            requests.setMarketId(marketId);
            if(StringUtil.isNotEmpty(tenantId)) {
                requests.setTenantId(tenantId);
            }
            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
            if(responses == null){
                System.out.println("定时任务 库存交易，"+marketId+"市场没有配置浮动车位");
            }
            int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal() );
            invpice.setTenantSpace(parkCount);

            invpice.setIsvalid(1);
            invpice.setVersion(1);
            invpice.setRegisterTime(new Date());
            inventoryInvoiceMonthMapper.insert(invpice);
        }
    }
}
