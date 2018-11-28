package com.maxcar.common.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Ajax;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 江苏省
 * 开票获取营业执照接口，对外公开
 * consolePipeline 输出控制台
 */
@Gecco(matchUrl="http://www.jsgsj.gov.cn:58888/ecipplatform/jiangsu.jsp?org={org}&id={id}&seqId={seqId}&activeTabId=", pipelines={"consolePipeline", "productDetailPipeline"})
public class JiangsuLicence implements HtmlBean {
    private static final long serialVersionUID = 665662335318691818L;
    static Logger logger = LoggerFactory.getLogger(JiangsuLicence.class);


    public static void start(String org,String id,String seqId){

        logger.debug("=======start========");

        String url = "http://www.jsgsj.gov.cn:58888/ecipplatform/jiangsu.jsp?org=Rorg&id=Rid&seqId=RseqId&activeTabId=";
        HttpGetRequest startUrl = new HttpGetRequest(url.replace("Rorg",org).replace("Rid",id).replace("RseqId",seqId));
        startUrl.setCharset("UTF-8");
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("com.maxcar.common.gecco")
                //开始抓取的页面地址
                .start(startUrl)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .run();
    }
    public static void main(String[] rags) {
//        JiangsuLicence jiangsuLicence = new JiangsuLicence();
        JiangsuLicence.start("F535388F0BB960F95173C365A0D94436","6C8B64495B4985DB13D95E1209C8BEAF","CEC253DCFA04E08F78DCC26ED9EA4602");
    }



    @RequestParameter("org")
    private String org;
    @RequestParameter("id")
    private String id;
    @RequestParameter("seqId")
    private String seqId;

    /**
     * ajax获取商品价格
     */
    @Ajax(url="http://www.jsgsj.gov.cn:58888/ecipplatform/publicInfoQueryServlet.json?pageView=true&org={org}&id={id}&seqId={seqId}&abnormal=&activeTabId=&tmp=98")
    private JiangsuResult result;


    public JiangsuResult getResult() {
        return result;
    }

    public void setResult(JiangsuResult result) {
        this.result = result;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    /**
     * 标题
     */
//    @Html
//    @HtmlField(cssPath=".footer")
//    private String title;
//
//
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

}