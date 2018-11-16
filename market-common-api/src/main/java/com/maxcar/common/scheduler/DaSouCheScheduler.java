package com.maxcar.common.scheduler;

import com.google.common.collect.Lists;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarModel;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.RedisUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.dasouche.TimeStampUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanMap;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 大搜车数据同步,定时任务执行类
 * yangsj
 */
@Component
public class DaSouCheScheduler{
    private static final Logger LOGGER = LoggerFactory.getLogger(DaSouCheScheduler.class);

    private static List<CarBrand> brandList = new ArrayList<>();
    private static List<CarSeries> seriesList = new ArrayList<>();
    private static List<CarModel> modelList = new ArrayList<>();
    private static final String INDEX = "index";
    private static final String LIST = "list";

    @Autowired
    private DaSouCheService daSouCheService;

    @Scheduled(cron = "${scheduler.cron}")
    protected void executeInternal() {
        Long start = System.currentTimeMillis();
        syncDaSouChe();
        Long end = System.currentTimeMillis();
        LOGGER.info("{}>>同步数据,耗时{}s",getSystemDate(),(end-start)/1000);
    }

    private void syncDaSouChe(){
       try {
           Result result = pottingRequest(8,null);
           if (null != result && result.getResultCode() == 200){
               JSONArray resultArray = JSONArray.fromObject(result.getList());
               for(Object obj : resultArray){
                   JSONObject json = JSONObject.fromObject(obj);
                   String index = json.getString(INDEX);
                   JSONArray brandArray = JSONArray.fromObject(json.getString(LIST));
                   for (Object o : brandArray){
                       //封装品牌
                       brandList.add(pottingCarBrand(o,index));
                   }
               }
               daSouCheService.syncDaSouCheBrand(brandList);
               for (CarBrand brand : brandList){
                   Result seriesResult = pottingRequest(4,brand.getBrandCode());
                   if (null != seriesResult && seriesResult.getResultCode() == 0){
                       JSONArray seriesResultArray = JSONArray.fromObject(seriesResult.getList());
                       for(Object obj : seriesResultArray) {
                           JSONObject json = JSONObject.fromObject(obj);
                           //封装车系实体
                           seriesList.add(pottingCarSeries(json));
                       }
                   }
               }
               /**
                * 将list分组成map,每10个一组(10条数据,mybatis批量插入是最高效的)
                */
               Map<String,List<CarSeries>> map = groupList(seriesList);
               for (Map.Entry<String,List<CarSeries>> entry:map.entrySet()){
                   daSouCheService.syncDaSouCheSeries(entry.getValue());
               }
               for (CarSeries series:seriesList){
                   Result modelResult = pottingRequest(5,series.getSeriesCode());
                   if (null != modelResult && modelResult.getResultCode() == 0){
                       JSONArray modelResultArray = JSONArray.fromObject(modelResult.getList());
                       for(Object obj : modelResultArray) {
                           JSONObject json = JSONObject.fromObject(obj);
                           //封装车型
                           modelList.add(pottingCarModel(json));
                       }
                   }
               }
               Map<String,List<CarModel>> modelMap = groupList(modelList);
               for (Map.Entry<String,List<CarModel>> entry:modelMap.entrySet()){
                   daSouCheService.syncDaSouCheModel(entry.getValue());
               }
           }
       //    keepCarInfoToRedis();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           //新生代对象设null,触发gc
           for (CarBrand brand:brandList){
               brand= null;
           }
           for (CarSeries series:seriesList){
               series= null;
           }
           for (CarModel model:modelList){
               model= null;
           }
           System.gc();
           //数据区对象清空,以供下次同步数据
           if (!brandList.isEmpty()){
               brandList.clear();
           }
           if (!seriesList.isEmpty()){
               seriesList.clear();
           }
           if (!modelList.isEmpty()){
               modelList.clear();
           }
       }
    }

    private CarSeries pottingCarSeries(JSONObject json){
        CarSeries carSeries = new CarSeries();
        CarSeries cs = (CarSeries)checkCode("series",json.getString("seriesCode"));
        carSeries.setId(cs.getId());
        CarBrand carBrand =daSouCheService.getCarBrand(json.getString("brandCode"));
        if (null != carBrand){
            carSeries.setBrandId(carBrand.getId());
        }
        carSeries.setSeriesName(json.getString("seriesName"));
        carSeries.setSeriesCode(json.getString("seriesCode"));
        carSeries.setInsertTime(Calendar.getInstance().getTime());
        if (null == cs.getInsertTime()){
            carSeries.setInsertTime(Calendar.getInstance().getTime());
        }else{
            carSeries.setInsertTime(cs.getInsertTime());
        }
        carSeries.setUpdateTime(Calendar.getInstance().getTime());
        return carSeries;
    }
    private CarModel pottingCarModel(JSONObject json){
        CarModel carModel = new CarModel();
        CarModel cm = (CarModel)checkCode("model",json.getString("modelCode"));
        carModel.setId(cm.getId());
        CarSeries carSeries =daSouCheService.getCarSeries(json.getString("seriesCode"));
        if (null != carSeries){
            carModel.setSeriesId(carSeries.getId());
        }
        carModel.setModelCode(json.getString("modelCode"));
        carModel.setModelName(json.getString("modelName"));
        if (null == cm.getInsertTime()){
            carModel.setInsertTime(Calendar.getInstance().getTime());
        }else{
            carModel.setInsertTime(cm.getInsertTime());
        }
        carModel.setUpdateTime(Calendar.getInstance().getTime());
        return carModel;
    }

    private Object checkCode(String type,String code){
        CarBrand carBrand = null;
        CarSeries carSeries = null;
        CarModel carModel = null;
        if (StringUtils.equals(type,"brand")){
            carBrand = daSouCheService.getCarBrand(code);
            if (null == carBrand){
                carBrand = new CarBrand();
                carBrand.setId(UuidUtils.getUUID());
            }
            return carBrand;

        }else if (StringUtils.equals(type,"series")){
            carSeries = daSouCheService.getCarSeries(code);
            if (null == carSeries){
                carSeries = new CarSeries();
                carSeries.setId(UuidUtils.getUUID());
            }
            return carSeries;
        }else if (StringUtils.equals(type,"model")){
            carModel = daSouCheService.getCarModel(code);
            if (null == carModel){
                carModel = new CarModel();
                carModel.setId(UuidUtils.getUUID());

            }
            return carModel;
        }
       return null;
    }

    private CarBrand pottingCarBrand(Object o,String index){
        CarBrand carBrand = new CarBrand();
        JSONObject bList = JSONObject.fromObject(o);
        carBrand.setCarIndex(index);
        CarBrand cb = (CarBrand)checkCode("brand",bList.getString("brandCode"));
        carBrand.setId(cb.getId());
        carBrand.setBrandCode(bList.getString("brandCode"));
        carBrand.setBrandName(bList.getString("brandName"));
        carBrand.setLogoUrl(StringUtils.equals("null",bList.getString("logoUrl"))
                ?"":bList.getString("logoUrl"));
        if (null == cb.getInsertTime()){
            carBrand.setInsertTime(Calendar.getInstance().getTime());
        }else{
            carBrand.setInsertTime(cb.getInsertTime());
        }
        carBrand.setUpdateTime(Calendar.getInstance().getTime());
        return carBrand;
    }

    private Result pottingRequest(int type,String code){
        com.alibaba.fastjson.JSONObject params = new com.alibaba.fastjson.JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        switch (type){
            case 8:
                break;
            case 4:
                params.put("scBrandCode", code);
                break;
            case 5:
                params.put("scSeriesCode", code);
                break;
            default:
                break;
        }
        List<String> listKey = Lists.newArrayList();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()){
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        Result result = daSouCheService.getAllService(type, params, listKey);
        return result;
    }

    private static Map groupList(List list){
        int listSize = list.size();
        int toIndex = 10;
        //用map存起来新的分组后数据
        Map map = new HashMap();
        //键值在此处并无卵用,仅仅定义hashMap的key
        int keyToken = 0;
        for(int i = 0;i < list.size();i += 10){
            //作用为toIndex最后没有10条数据则剩余几条newList中就装几条
            if(i+10 > listSize){
                toIndex = listSize-i;
            }
            List newList = list.subList(i,i+toIndex);
            map.put("keyName"+keyToken, newList);
            keyToken++;
        }

        return map;
    }

    private static String getSystemDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    private static void keepCarInfoToRedis(){
        brandList.forEach(carBrand -> {
            List<Map<String,Object>> carSeriesList = new ArrayList<>();
            seriesList.forEach(carSeries -> {
                if (StringUtils.equals(carBrand.getId(),carSeries.getBrandId())){
                    Map<String,Object> mapObj = objectToMap(carSeries);
                    carSeriesList.add(mapObj);
                    RedisUtil.getInstance().strings().set(carBrand.getBrandCode(),carSeriesList.toString());
                }
            });
        });
        seriesList.forEach(carSeries -> {
            List<Map<String,Object>> carModelList = new ArrayList<>();
            modelList.forEach(carModel -> {
                if (StringUtils.equals(carSeries.getId(),carModel.getSeriesId())){
                    Map<String,Object> mapObj = objectToMap(carModel);
                    carModelList.add(mapObj);
                    RedisUtil.getInstance().strings().set(carSeries.getSeriesCode(),carModelList.toString());
                }
            });
        });

    }

    public static Map objectToMap(Object obj) {
        if(obj == null){
            return null;
        }
        return new BeanMap(obj);
    }


}
