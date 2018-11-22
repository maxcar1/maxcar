package com.maxcar.task;

import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.service.ReviewStepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AutoWarnTask {
    //@Scheduled(cron = "0 0 0/1 * * ?")
    @Autowired
    private ReviewStepService reviewStepService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Scheduled(cron = "0/5 * * * * *")
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void scheduled(){
        logger.info("进入定时器任务");

        List<FlowStep> reviewStepList = reviewStepService.selectReviewManage("007");
        logger.info("进入定时器任务"+new Date());
    }
}
