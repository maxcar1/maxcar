package com.maxcar.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.service.DaSouCheService;

import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.dasouche.TimeStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 大搜车接口预留
 * yangsj
 */
@RestController
@RequestMapping("/maxcar")
public class DaSouCheController {

    @Autowired
    private DaSouCheService daSouCheService;
    /**
     * 根据vin码去大搜车拿相应的车辆数据
     *
     * @param vin
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/carinfo/{vin}")
    public Result getCarListByVin(@PathVariable("vin") String vin) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("vinCode", vin);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = daSouCheService.getAllService(1, params, listKey);
        return result;
    }

    /**
     * 根据品牌查询所有车系
     * @return
     */
    @GetMapping(value = "/series/{brand}")
    public Result listSeriesByBrand(@PathVariable("brand") String brand) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scBrandCode", brand);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = daSouCheService.getAllService(4, params, listKey);
        return result;
    }

    /**
     * 根据车系查询车型
     * @param series
     * @return
     */
    @GetMapping(value = "/type/{series}")
    public Result listModelBySeries(@PathVariable("series") String series) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scSeriesCode", series);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = daSouCheService.getAllService(5, params, listKey);
        return result;
    }
    /**
     * 车型编码获取⻋车型详细配置信息
     *
     * @param scModelCode
     * @return
     */
    @RequestMapping(value = "/models/{scModelCode}", method = RequestMethod.GET)
    public Result detailInfo(@PathVariable("scModelCode") String scModelCode) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scModelCode", scModelCode);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = daSouCheService.getAllService(2, params, listKey);
        if(result!=null){
            result.setResultCode(200);
        }
        return result;
    }

    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public Result listBrands() {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = daSouCheService.getAllService(8, params, listKey);
        System.out.println(result.getList());
        return result;
    }

}
