package com.maxcar.common.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Ajax;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.maxcar.base.util.dasouche.HttpClientUtil;
import com.maxcar.kafka.util.HttpClientUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 江苏省
 * 开票获取营业执照接口，对外公开
 * consolePipeline 输出控制台
 */
@Gecco(matchUrl="http://www.jsgsj.gov.cn:58888/ecipplatform/jiangsu.jsp?org={org}&id={id}&seqId={seqId}&activeTabId=", pipelines={"consolePipeline", "productDetailPipeline"})
public class JiangsuLicence implements HtmlBean {
    private static final long serialVersionUID = 665662335318691818L;
    static Logger logger = LoggerFactory.getLogger(JiangsuLicence.class);


    public void start(String orgId){
        logger.debug("=======start========");

        String url = "http://www.jsgsj.gov.cn:58888/province/infoQueryServlet.json?pt&c="+orgId;
        HttpGetRequest startUrl = new HttpGetRequest(url);
        startUrl.setCharset("UTF-8");
//        startUrl.setParameters(map);//设置输出参数
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
        String org = "75B161B67BE862B12D2BE5DB01B1A79BC5DFE01C444522D1307B081DE90B23BE3D6B0469F596DD6CBC2A90138B164FB85334496423DC8031A1B7182234F3E58A";

//        JSONObject str = HttpClientUtils.httpGet("http://www.jsgsj.gov.cn:58888/province/infoQueryServlet.json?pt&c=75B161B67BE862B1D4A846A36A45371113638D8B314CF27F1A843F70BA360C747ED6FFABCDEF63AE3F003069BABBFC8BC40C940732D215DEBE613C937889291D","","");

//        System.out.println(str);
                JiangsuLicence jiangsuLicence = new JiangsuLicence();
        jiangsuLicence.start(org);
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

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    @Request
    private HttpRequest request;

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