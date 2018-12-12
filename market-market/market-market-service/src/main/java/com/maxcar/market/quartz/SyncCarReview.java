package com.maxcar.market.quartz;
import com.maxcar.market.service.PropertyContractPayService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.stock.service.CarReviewService;
import com.maxcar.stock.service.OutMarketCarService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @program: maxcar-util
 * @description: 同步控制中心
 * @author: 罗顺锋
 * @create: 2018-04-23 09:50
 **/
public class SyncCarReview extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(SyncCarReview.class);
//    @Autowired
//    private AsyncExecutorTaskService asyncExecutorTaskService;
    @Autowired
    private PropertyContractPayService propertyContractPayService;

    @Autowired
    private PropertyContractService propertyContractService;

    @Autowired
    private OutMarketCarService outMarketCarService;

    @Autowired
    private CarReviewService carReviewService;

    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {

//        SyncContrat syncContrat = new SyncContrat();
//        asyncExecutorTaskService.doJob(syncContrat);
        try {
//            propertyContractPayService.addPropertyContractPay();
//            propertyContractService.endPropertyContract();
            outMarketCarService.delCarByOutMarketTime();
            outMarketCarService.downTaoBao();
            carReviewService.updateTimeoutNotreturnCarStockStatus();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
