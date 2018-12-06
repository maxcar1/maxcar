package com.maxcar.statistics.service.impl.mapperService;

import org.springframework.stereotype.Service;

@Service
public class BaseMapperService {



    /**
     * param:
     * describe: 生成计算库存周期的sql 包前不包后
     * create_date:  lxy   2018/11/16  15:26
     **/
    public static String getInventoryCycleString(Integer inventoryCycle) {

        String inventoryCycleString = null;

        switch (inventoryCycle) {
            case 1:
                //10天以内
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) < 10 ";
                break;
            case 2:
                //10-20天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 10 and DATEDIFF(now(),c.register_time) < 20  ";
                break;
            case 3:
                //20-30天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 20 and DATEDIFF(now(),c.register_time) < 30  ";
                break;
            case 4:
                //30-40天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 30 and DATEDIFF(now(),c.register_time) < 40  ";
                break;
            case 5:
                //40-50天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 40 and DATEDIFF(now(),c.register_time) < 50  ";
                break;
            case 6:
                //50-60天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 50 and DATEDIFF(now(),c.register_time) < 60  ";
                break;
            case 7:
                //60-70天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 60 and DATEDIFF(now(),c.register_time) < 70  ";
                break;
            case 8:
                //70-80天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 70 and DATEDIFF(now(),c.register_time) < 80  ";
                break;
            case 9:
                //80-90天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 80 and DATEDIFF(now(),c.register_time) < 90  ";
                break;
            case 10:
                //90天以上
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 90  ";
                break;
            default:
                break;
        }

        return inventoryCycleString;
    }

    public static String getNameByInventoryCycleString(Integer inventoryCycle) {

        String name = "";

        switch (inventoryCycle) {
            case 1:
                //10天以内
                name = "10天以内";
                break;
            case 2:
                //10-20天
                name = "10-20天";
                break;
            case 3:
                //20-30天
                name = "20-30天";
                break;
            case 4:
                //30-40天
                name = "30-40天";
                break;
            case 5:
                //40-50天
                name = "40-50天";
                break;
            case 6:
                //50-60天
                name = "50-60天";
                break;
            case 7:
                //60-70天
                name = "60-70天";
                break;
            case 8:
                //70-80天
                name = "70-80天";
                break;
            case 9:
                //80-90天
                name = "80-90天";
                break;
            case 10:
                //90天以上
                name = "90天以上";
                break;
            default:
                break;
        }

        return name;
    }

    /**
     * param:
     * describe: 生成计算车辆年限的sql 包前不包后
     * create_date:  lxy   2018/11/16  16:00
     **/
    public static String getAgeByCarString(Integer ageByCar) {

        String AgeByCarString = null;

        switch (ageByCar) {
            case 1:
                //一年以内
                AgeByCarString = " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 1 YEAR)  ";
                break;
            case 2:
                //一 - 三年
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 1 YEAR)" +
                        " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 3 YEAR) ";
                break;
            case 3:
                //三 - 五年
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 3 YEAR)" +
                        " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 5 YEAR) ";
                break;
            case 4:
                //五 - 八年
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 5 YEAR)" +
                        " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 8 YEAR) ";
                break;
            case 5:
                //八年以上
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 8 YEAR)";
                break;
            default:
                break;
        }

        return AgeByCarString;
    }


    public static String getNameByAgeByCarString(Integer ageByCar) {

        String name = "";

        switch (ageByCar) {
            case 1:
                //一年以内
                name = "一年以内";
                break;
            case 2:
                //一 - 三年
                name = "一 - 三年";
                break;
            case 3:
                //三 - 五年
                name = "三 - 五年";
                break;
            case 4:
                //五 - 八年
                name = "五 - 八年";
                break;
            case 5:
                //八年以上
                name = "八年以上";
                break;
            default:
                break;
        }

        return name;
    }


}
