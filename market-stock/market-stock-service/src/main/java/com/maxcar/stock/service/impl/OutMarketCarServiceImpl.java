package com.maxcar.stock.service.impl;

import com.maxcar.base.util.Constants;
import com.maxcar.base.util.DatePoor;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.dao.CarMapper;
import com.maxcar.stock.dao.CarRecordMapper;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarRecord;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.service.OutMarketCarService;
import com.maxcar.stock.vo.CarOut;
import com.maxcar.user.entity.OutMarketManage;
import com.maxcar.user.service.OutMarketManageService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemUpdateDelistingRequest;
import com.taobao.api.response.ItemUpdateDelistingResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @Author sunyazhou
 * @Date 2018/10/18 13:50
 * @desc
 */
@Service("outMarketCarService")
public class OutMarketCarServiceImpl implements OutMarketCarService {

    protected final Logger logger = Logger.getLogger(this.getClass());

//    private static String[] status = {"3"}; // 出场状态

//    private static String[] delAndSoldStatus = {"-1","4","5"};// 删除和售出状态

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private CarRecordMapper carRecordMapper;

    @Autowired
    private OutMarketManageService outMarketManageService;

    @Value("${kafka.producer.topic}")
    public String producerTopic;
    @Value("${kafka.consumer.topic006}")
    public String consumerTopic6;
    @Value("${kafka.consumer.topic007}")
    public String consumerTopic7;
    @Value("${kafka.consumer.topic008}")
    public String consumerTopic8;
    @Value("${kafka.consumer.topic009}")
    public String consumerTopic9;
    @Value("${kafka.consumer.topic010}")
    public String consumerTopic10;
    @Value("${kafka.consumer.topic011}")
    public String consumerTopic11;
    @Value("${kafka.consumer.topic012}")
    public String consumerTopic12;
    @Value("${kafka.consumer.topic013}")
    public String consumerTopic13;
    @Value("${kafka.consumer.topic014}")
    public String consumerTopic14;
    @Value("${kafka.consumer.topic015}")
    public String consumerTopic15;
    @Value("${kafka.consumer.topic016}")
    public String consumerTopic16;
    @Value("${kafka.consumer.topic017}")
    public String consumerTopic17;
    @Value("${kafka.consumer.topic018}")
    public String consumerTopic18;
    @Value("${kafka.consumer.topic019}")
    public String consumerTopic19;
    @Value("${kafka.consumer.topic020}")
    public String consumerTopic20;
    @Value("${kafka.consumer.topic021}")
    public String consumerTopic21;
    @Value("${kafka.consumer.topic022}")
    public String consumerTopic22;
    @Value("${kafka.consumer.topic023}")
    public String consumerTopic23;
    @Value("${kafka.consumer.topic024}")
    public String consumerTopic24;
    @Value("${kafka.consumer.topic025}")
    public String consumerTopic25;

    @Value("${kafka.consumer.topic026}")
    public String consumerTopic26;
    @Value("${kafka.consumer.topic027}")
    public String consumerTopic27;
    @Value("${kafka.consumer.topic028}")
    public String consumerTopic28;
    @Value("${kafka.consumer.topic029}")
    public String consumerTopic29;
    @Value("${kafka.consumer.topic030}")
    public String consumerTopic30;
    @Value("${kafka.consumer.topic031}")
    public String consumerTopic31;
    @Value("${kafka.consumer.topic032}")
    public String consumerTopic32;
    @Value("${kafka.consumer.topic033}")
    public String consumerTopic33;
    @Value("${kafka.consumer.topic034}")
    public String consumerTopic34;
    @Value("${kafka.consumer.topic035}")
    public String consumerTopic35;
    @Value("${kafka.consumer.topic036}")
    public String consumerTopic36;
    @Value("${kafka.consumer.topic037}")
    public String consumerTopic37;
    @Value("${kafka.consumer.topic038}")
    public String consumerTopic38;
    @Value("${kafka.consumer.topic039}")
    public String consumerTopic39;
    @Value("${kafka.consumer.topic040}")
    public String consumerTopic40;
    @Value("${kafka.consumer.topic041}")
    public String consumerTopic41;
    @Value("${kafka.consumer.topic042}")
    public String consumerTopic42;
    @Value("${kafka.consumer.topic043}")
    public String consumerTopic43;
    @Value("${kafka.consumer.topic044}")
    public String consumerTopic44;
    @Value("${kafka.consumer.topic045}")
    public String consumerTopic45;
    @Value("${kafka.consumer.topic046}")
    public String consumerTopic46;
    @Value("${kafka.consumer.topic047}")
    public String consumerTopic47;
    @Value("${kafka.consumer.topic048}")
    public String consumerTopic48;
    @Value("${kafka.consumer.topic049}")
    public String consumerTopic49;
    @Value("${kafka.consumer.topic050}")
    public String consumerTopic50;
    @Value("${kafka.consumer.topic051}")
    public String consumerTopic51;
    @Value("${kafka.consumer.topic052}")
    public String consumerTopic52;
    @Value("${kafka.consumer.topic053}")
    public String consumerTopic53;
    @Value("${kafka.consumer.topic054}")
    public String consumerTopic54;
    @Value("${kafka.consumer.topic055}")
    public String consumerTopic55;


    /**
     * 定时扫描删除车辆   并同步到本地车辆状态
     * @throws Exception
     */
    @Override
    public void delCarByOutMarketTime() throws Exception {

        // 获取所有配置出场时间控制的市场信息
        List<OutMarketManage> outMarketManages = outMarketManageService.selectByOutMarketType("1");
        if (outMarketManages.size() > 0){
            for (OutMarketManage o: outMarketManages) {
                // 获取该市场下出场信息的车辆
                List<Car> allOutMarketCar = getAllOutMarketCar(o.getMarketId(), Constants.outStatus);
                // 获取出场车辆的最后一次出场记录
                List<CarOut> carOuts = getCarEndOutMarketDate(allOutMarketCar);
                if (carOuts != null && carOuts.size() >0){
                    for (CarOut c: carOuts) {
                        long diff =new Date().getTime() - c.getInsertTime().getTime();// 时间毫秒差
                        double outMarketTime = o.getOutMarketTime()*60*60*1000;// 允许出场的时间
                        if (diff >= outMarketTime ){
                            delLocalCar(c,o);// 删除车辆信息
                        }
                    }
                }
            }
        }else {
            return;
        }
    }


    /**
     * 同步删除本地车辆状态
     * 组装云端参数
     */
    public void delLocalCar(CarOut carOut,OutMarketManage outMarketManage){
        String id = carOut.getCarId();
        carService.deleteCarById(carOut.getCarId());
        String topic = consumerTopic7;
        switch (outMarketManage.getMarketId()) {
            case "006":
                topic = consumerTopic6;
                break;
            case "007":
                topic = consumerTopic7;
                break;
            case "008":
                topic = consumerTopic8;
                break;
            case "009":
                topic = consumerTopic9;
                break;
            case "010":
                topic = consumerTopic10;
                break;
            case "011":
                topic = consumerTopic11;
                break;
            case "012":
                topic = consumerTopic12;
                break;
            case "013":
                topic = consumerTopic13;
                break;
            case "014":
                topic = consumerTopic14;
                break;
            case "015":
                topic = consumerTopic15;
                break;
            case "016":
                topic = consumerTopic16;
                break;
            case "017":
                topic = consumerTopic17;
                break;
            case "018":
                topic = consumerTopic18;
                break;
            case "019":
                topic = consumerTopic19;
                break;
            case "020":
                topic = consumerTopic20;
                break;
            case "021":
                topic = consumerTopic21;
                break;
            case "022":
                topic = consumerTopic22;
                break;
            case "023":
                topic = consumerTopic23;
                break;
            case "024":
                topic = consumerTopic24;
                break;
            case "025":
                topic = consumerTopic25;
                break;
            case "026":
                topic = consumerTopic26;
                break;
            case "027":
                topic = consumerTopic27;
                break;
            case "028":
                topic = consumerTopic28;
                break;
            case "029":
                topic = consumerTopic29;
                break;
            case "030":
                topic = consumerTopic30;
                break;
            case "031":
                topic = consumerTopic31;
                break;
            case "032":
                topic = consumerTopic32;
                break;
            case "033":
                topic = consumerTopic33;
                break;
            case "034":
                topic = consumerTopic34;
                break;
            case "035":
                topic = consumerTopic35;
                break;
            case "036":
                topic = consumerTopic36;
                break;
            case "037":
                topic = consumerTopic37;
                break;
            case "038":
                topic = consumerTopic38;
                break;
            case "039":
                topic = consumerTopic39;
                break;
            case "040":
                topic = consumerTopic40;
                break;
            case "041":
                topic = consumerTopic41;
                break;
            case "042":
                topic = consumerTopic42;
                break;
            case "043":
                topic = consumerTopic43;
                break;
            case "044":
                topic = consumerTopic44;
                break;
            case "045":
                topic = consumerTopic45;
                break;
            case "046":
                topic = consumerTopic46;
                break;
            case "047":
                topic = consumerTopic47;
                break;
            case "048":
                topic = consumerTopic48;
                break;
            case "049":
                topic = consumerTopic49;
                break;
            case "050":
                topic = consumerTopic50;
                break;
            case "051":
                topic = consumerTopic51;
                break;
            case "052":
                topic = consumerTopic52;
                break;
            case "053":
                topic = consumerTopic53;
                break;
            case "054":
                topic = consumerTopic54;
                break;
            case "055":
                topic = consumerTopic55;
                break;
        }
        //同步删除本地车辆状态
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setData(id);
        postParam.setMarket(outMarketManage.getMarketId());
        postParam.setUrl("/barrier/car/delete/" + id);
        postParam.setMethod("get");
        postParam.setOnlySend(false);
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

    }


    /**
     * 定时下架淘宝
     * @throws Exception
     */
    @Override
    public void downTaoBao() throws Exception {
        // 查询状态为-1,4,5状态的车辆
        List<Car> cars = getAllOutMarketCar("010", Constants.delAndSoldStatus);
        if (cars != null && cars.size() > 0){
            for (Car c : cars) {
                if (c.getTaobaoId() != null && c.getTaobaoId() != ""){
                    downTaoBaoByTBid(c.getTaobaoId());
                }
            }
        }
    }


    /**
     * 查询市场下状态为指定的车辆的信息
     * @param marketId
     * @param status
     * @return
     */
    @Override
    public List<Car> getAllOutMarketCar(String marketId,String[] status) {
        Map map = new HashMap();
        map.put("marketId",marketId);
        map.put("status",status);
        List<Car> allOutMarketCar = carMapper.getAllMarketCarByStatus(map);
        return allOutMarketCar;
    }


    /**
     * 根据vin码查询车辆的最后一次出场信息
     * @param cars
     * @return
     */
    public List<CarOut> getCarEndOutMarketDate(List<Car> cars){
        if (cars != null){
            List<CarOut> list = new ArrayList<>();
            for (Car c: cars) {
                CarOut carOut = carRecordMapper.getCarOutMarketDate(c.getVin());
                if (carOut != null){
                    if (carOut.getType() == 1){
                        list.add(carOut);
                    }
                }
            }
            return list;
        }
        return null;
    }

    /**
     * 下架淘宝
     * @param taoBaoId
     */
    public void downTaoBaoByTBid(String taoBaoId){
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
            String APP_KEY = prop.getProperty("taobaoAppKey");
            String SECRET = prop.getProperty("taobaosecret");
            String API_URL = prop.getProperty("taobaoUploadUrl");
            String sessionKey = prop.getProperty("sessionKey");

            TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
            ItemUpdateDelistingRequest req = new ItemUpdateDelistingRequest();
            req.setNumIid(Long.valueOf(taoBaoId));

            ItemUpdateDelistingResponse rsp = client.execute(req, sessionKey);
            logger.info("自动下架返回结果：" + rsp.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
