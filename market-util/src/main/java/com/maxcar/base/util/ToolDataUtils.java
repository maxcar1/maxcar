package com.maxcar.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * describe: data工具类
 * create_date: lxy 2018/10/12  14:04
 **/
public class ToolDataUtils {

    public static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");

    /**
     * param:
     * describe: 判断SQL操作成功
     * create_date:  lxy   2018/8/16  18:29
     **/
    public static boolean isOperationSuccess(int status) {

        if (1 <= status) {
            return true;
        }

        return false;
    }

    /**
     * param:
     * describe: 判断SQL操作失败
     * create_date:  lxy   2018/8/16  18:29
     **/
    public static boolean isOperationFail(int status) {
        return !isOperationSuccess(status);
    }


    public static Boolean isLastDayByMonth() {

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return sdfDay.format(cal.getTime()).equals(getreportTimeByDay());
    }

    /**
     * param:
     * describe: 获取今天的 年月日
     * create_date:  lxy   2018/11/22  13:36
     **/
    public static String getreportTimeByDay() {

        return sdfDay.format(new Date());
    }

    /**
     * param:
     * describe: 获取今天的 年月
     * create_date:  lxy   2018/11/22  13:36
     **/
    public static String getreportTimeByMonth() {

        return sdfMonth.format(new Date());
    }

    /**
     * param:
     * describe: 获取传入日期的上一年日期
     * create_date:  lxy   2018/12/3  15:22
     **/
    public static String getLastYear(String thisYear) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdfDay.parse(thisYear));
        calendar.add(Calendar.YEAR, -1);
        return sdfDay.format(calendar.getTime());
    }

    /**
     * param:
     * describe: 获取上一个月的日期
     * create_date:  lxy   2018/12/3  15:22
     **/
    public static String getlastMonth(String time) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdfDay.parse(time));
        calendar.add(Calendar.MONTH, -1);
        return sdfDay.format(calendar.getTime());
    }

    public static void main(String[] args) {
        try {
            System.out.println(getlastMonth("2018-1-11"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * param:
     * describe: 计算平均价格
     * create_date:  lxy   2018/11/22  14:55
     **/
    public static Double getAvgPrice(Double price, Integer count) {

        if (0 == price || 0 == count) {
            return 0.0;
        }

        return price / count;
    }

}
