package com.maxcar.core.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */
public final class DateUtil {
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    private DateUtil() {

    }

    public static String format(String pattern, Date date) {
        SimpleDateFormat sdf = tl.get();
        sdf.applyPattern(pattern);
        String format = sdf.format(date);
        return format;
    }
    /** 
     *  
     * @param minDate 最小时间
     * @param maxDate 最大时间 
     * @return 日期集合 格式为 年-月 
     * @throws Exception 
     */  
    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {  
        ArrayList<String> result = new ArrayList<String>();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月  
  
        Calendar min = Calendar.getInstance();  
        Calendar max = Calendar.getInstance();  
  
        min.setTime(sdf.parse(minDate));  
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);  
  
        max.setTime(sdf.parse(maxDate));  
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);  
  
        Calendar curr = min;  
        while (curr.before(max)) {  
         result.add(sdf.format(curr.getTime()));  
         curr.add(Calendar.MONTH, 1);  
        }  
  
        return result;  
    }
    /** 
     *
     * @return 日期集合 格式为 年-月 -日
     * @throws Exception 
     */  
    public static List<String>  display(String dateFirst, String dateSecond){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list=new ArrayList<>();
        try{
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateSecond);
             
            Calendar calendar = Calendar.getInstance();
             
            calendar.setTime(dateOne);
             
            while(calendar.getTime().before(dateTwo)){               
              
                list.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);               
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
         return list;
    }
    /**
    * @Description: 时间对天加减
    * @Param: [day]
    * @return: java.util.Date
    * @Author: 罗顺锋
    * @Date: 2018/5/29
    */
    public static Date addDay(Date date,int day) {
        date = date==null?new Date():date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
        return cal.getTime();
    }
    /**
    * @Description: 月份加减
    * @Param: [month]
    * @return: java.util.Date
    * @Author: 罗顺锋
    * @Date: 2018/5/29
    */
    public static Date addMonth(Date date,int month) {
        date = date==null?new Date():date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+month);
        return cal.getTime();
    }
    /**
    * @Description: 年份加减
    * @Param: [year]
    * @return: java.util.Date
    * @Author: 罗顺锋
    * @Date: 2018/5/29
    */
    public static Date addYear(Date date,int year) {
        date = date==null?new Date():date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+year);
        return cal.getTime();
    }
}
