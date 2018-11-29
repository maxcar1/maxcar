package com.maxcar.common.gecco;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.JsonTools;
import com.maxcar.redis.service.SsoService;
import com.maxcar.web.ApplicationContextHolder;

/**
 * 解析结果
 */
@PipelineName("productDetailPipeline")
public class JiangsuDetailPipeline implements Pipeline<JiangsuLicence> {

    @Override
    public void process(JiangsuLicence jiangsuLicence) {
        try {
//            System.out.println("~~~~~~~~~productDetailPipeline~~~~~~~~~~~");
            System.out.println(jiangsuLicence.getResult());
            SsoService ssoService = ApplicationContextHolder.getBean("ssoService");
//            if(jiangsuLicence.getResult().equals("")){
//                ssoService.setStringKey(jiangsuLicence.getOrg() + "=" + jiangsuLicence.getId() + "=" + jiangsuLicence.getSeqId(), JsonTools.toJson(jiangsuLicence.getResult()), -1);
//            }
            InterfaceResult interfaceResult = ssoService.getStringKey(jiangsuLicence.getOrg()+"="+jiangsuLicence.getId()+"="+jiangsuLicence.getSeqId());
            if(!interfaceResult.getCode().equals("200")) {//如果不存在重新塞入
                ssoService.setStringKey(jiangsuLicence.getOrg() + "=" + jiangsuLicence.getId() + "=" + jiangsuLicence.getSeqId(), JsonTools.toJson(jiangsuLicence.getResult()), -1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
