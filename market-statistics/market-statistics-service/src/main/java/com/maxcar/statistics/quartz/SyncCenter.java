package com.maxcar.statistics.quartz;


import com.maxcar.statistics.service.impl.InsertStockAndInvoiceImpl;
import com.maxcar.statistics.service.impl.mapperService.CarbrandMapperService;
import com.maxcar.statistics.service.impl.mapperService.CarstocktimeMapperService;
import com.maxcar.statistics.service.impl.mapperService.CartypeMapperService;
import com.maxcar.statistics.service.impl.mapperService.CaryearMapperService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @program: maxcar-util
 * @description: 同步控制中心
 * @author: 罗顺锋
 * @create: 2018-04-23 09:50
 **/
public class SyncCenter extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(SyncCenter.class);
    //    @Autowired
//    private AsyncExecutorTaskService asyncExecutorTaskService;

    @Autowired
    private CartypeMapperService cartypeMapperService;

    @Autowired
    private CarbrandMapperService carbrandMapperService;

    @Autowired
    private CaryearMapperService caryearMapperService;

    @Autowired
    private CarstocktimeMapperService carstocktimeMapperService;

    @Autowired
    private InsertStockAndInvoiceImpl insertStockAndInvoice;

    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {

//        SyncContrat syncContrat = new SyncContrat();
//        asyncExecutorTaskService.doJob(syncContrat);
        try {

            cartypeMapperService.InsertCartype();
            carbrandMapperService.InserteCarbrand();
            caryearMapperService.InsertCaryear();
            carstocktimeMapperService.InsertCarstocktime();

            insertStockAndInvoice.InsertCarpriceDay();
            insertStockAndInvoice.InsertCarstockDay();
            insertStockAndInvoice.InsertCarstockMonth();
            insertStockAndInvoice.InsertInventoryInvoiceDay();
            insertStockAndInvoice.InsertInventoryInvoiceMonth();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
