package com.gh.common.api.impl;

import com.gh.common.api.service.DateAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期时间
 */
public class DateAPIImpl implements DateAPI {


    public static String getUniversal(String str){
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        str = sdf.format(new Date());
        return str;
    }

    /*获取当前日期*/
    public String getDate(){
        return DateAPIImpl.getUniversal("yyyy-MM-dd");
    }

    /*获取当前日期规定格式*/
    public String getDate(String str){
        String date = DateAPIImpl.getUniversal("yyyyMMdd");
        date = date.substring(0,4)+str+date.substring(4,6)+str+date.substring(6,8);
        return date;
    }

    /*获取当前时间*/
    public String getTime(){
        return DateAPIImpl.getUniversal("HH:mm:ss");
    }

    /*获取当前日期时间*/
    public String getDatetime(){
        return DateAPIImpl.getUniversal("yyyy-MM-dd HH:mm:ss");
    }

    /*获取当前年份*/
    public String getYear(){
        return DateAPIImpl.getUniversal("yyyy");
    }

    /*获取当前月份*/
    public String getMonth(){
        return DateAPIImpl.getUniversal("MM");
    }

    /**
     * 获取当前日
     * @return
     */
    public String getDay(){
        return DateAPIImpl.getUniversal("dd");
    }

    /**
     * 获取当前星期
     * @return
     */
    public String getWeekDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        String weekDay = "";
        switch (dayForWeek){
            case 1:weekDay="星期一";break;
            case 2:weekDay="星期二";break;
            case 3:weekDay="星期三";break;
            case 4:weekDay="星期四";break;
            case 5:weekDay="星期五";break;
            case 6:weekDay="星期六";break;
            case 7:weekDay="星期日";break;
        }
        return weekDay;
    }

    /**
     * 获取当前星期
     * @param timestamp
     * @return
     */
    public String getWeekDay(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(sdf.format(new Date(timestamp))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        String weekDay = "";
        switch (dayForWeek){
            case 1:weekDay="星期一";break;
            case 2:weekDay="星期二";break;
            case 3:weekDay="星期三";break;
            case 4:weekDay="星期四";break;
            case 5:weekDay="星期五";break;
            case 6:weekDay="星期六";break;
            case 7:weekDay="星期日";break;
        }
        return weekDay;
    }

    /**
     * 获取当前月第一天的日期
     * @return
     */
    public String getMonthBegin(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDate = sdf.format(calendar.getTime());
        return firstDate;
    }

    /**
     * 获取当月最后一天的日期
     * @return
     */
    public String getMonthEnd(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDate = sdf.format(calendar.getTime());
        return lastDate;
    }

    /**
     * 获取某月第一天的日期，当amount为0时，为本月，-1时为上月，1时为下月
     * @param amount
     * @return
     */
    public String getMonthBegin(int amount){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, amount);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDate = sdf.format(calendar.getTime());
        return firstDate;
    }

    /**
     * 获取某月最后一天的日期，当amount为0时，为本月，-1时为上月，1时为下月
     * @param amount
     * @return
     */
    public String getMonthEnd(int amount){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, amount);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDate = sdf.format(calendar.getTime());
        return lastDate;
    }

    /**
     * 获取几几年的第几周的第一天日期和最后一天日期
     * @param year
     * @param week
     * @return
     */
    public Map<String,String> getWeekDaysByYearAndWeek(Integer year,Integer week){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String beginDate = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        String endDate = sdf.format(cal.getTime());
        Map<String,String> map = new HashMap<>();
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        return map;
    }

    /**
     * 获取某一日期是几几年的第几周,及本周第一天日期和最后一天日期
     * @param timestamp
     * @return
     */
    public Map<String,Object> getNumberWeek(long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);    //设置周一为一周的第一天
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(new Date(timestamp));
        Calendar cal = (Calendar) calendar.clone();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String beginDate = df.format(cal.getTime());
        Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
        Integer year = calendar.get(Calendar.YEAR);
        String str = df.format(new Date(timestamp));
        String[] arr = str.split("-");
        if (arr[1].equalsIgnoreCase("12")&&week==1){
            year++;
        }
        cal.add(Calendar.DATE, 6);
        String endDate = df.format(cal.getTime());
//        System.out.println("本周周一和周末:" + beginDate + " and " + endDate + " and "+ week+" and "+year);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("year",year);
        map.put("week",week);
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        return map;
    }

    /**
     * 转中文日期
     * @param date
     * @return
     */
    public String getDateChangeaChinese(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] str = new String[3];
        if (date.indexOf("-")!=-1) {
            str = date.split("-");
        } else if (date.indexOf("/")!=-1){
            str = date.split("/");
        } else if (date.indexOf(".")!=-1) {
            str = date.split(".");
        } else {
            return "";
        }
        String[][] s = new String[3][4];
        String text = "";
        for (int i=0;i<3;i++){
            for (int j = 0; j < str[i].length();j++){
                s[i][j] = str[i].substring(j,j+1);
                if ( i == 0 ){
                    text += numberChinese(s[i][j]);
                }
            }
        }
        text += "年";
        if (Integer.parseInt(s[1][0])==0){
            text += numberChinese(s[1][1]);
        } else {
            if (Integer.parseInt(s[1][0])==1){
                if (Integer.parseInt(s[1][1])==0){
                    text += "十";
                } else {
                    text += "十"+numberChinese(s[1][1]);
                }
            } else {
                text += numberChinese(s[1][0])+"十"+numberChinese(s[1][1]);
            }
        }
        text += "月";

        if (Integer.parseInt(s[2][0])==0){
            text += numberChinese(s[2][1]);
        } else {
            if (Integer.parseInt(s[2][0])==1){
                if (Integer.parseInt(s[2][1])==0){
                    text += "十";
                } else {
                    text += "十"+numberChinese(s[2][1]);
                }
            } else {
                text += numberChinese(s[2][0])+"十"+numberChinese(s[2][1]);
            }
        }
        text += "日";
        return text;
    }

    public String numberChinese(String str){
        int num = Integer.parseInt(str);
        String s = "";
        switch (num){
            case 0:s="零";break;
            case 1:s="一";break;
            case 2:s="二";break;
            case 3:s="三";break;
            case 4:s="四";break;
            case 5:s="五";break;
            case 6:s="六";break;
            case 7:s="七";break;
            case 8:s="八";break;
            case 9:s="九";break;
        }
        return s;
    }

    /**
     * 计算两个日期时间之间相差的毫秒
     * @param beginTime
     * @param endTime
     * @return
     */
    public long getDatetimeDiffMillisecond(long beginTime, long endTime) {
        long result = endTime-beginTime;
        return result;
    }

    /**
     * 计算两个日期时间之间相差的秒
     * @param beginTime
     * @param endTime
     * @return
     */
    public long getDatetimeDiffSecond(long beginTime, long endTime) {
        long result = (endTime-beginTime)/1000;
        return result;
    }

    /**
     * 计算两个日期时间之间相差的分钟
     * @param beginTime
     * @param endTime
     * @return
     */
    public long getDatetimeDiffMinute(long beginTime, long endTime) {
        long result = (endTime-beginTime)/1000/60;
        return result;
    }

    /**
     * 计算两个日期时间之间相差的小时
     * @param beginTime
     * @param endTime
     * @return
     */
    public long getDatetimeDiffHour(long beginTime, long endTime) {
        long result = (endTime-beginTime)/1000/60/60;
        return result;
    }

    /**
     * 计算两个日期时间之间相差的天数
     * @param beginTime
     * @param endTime
     * @return
     */
    public long getDatetimeDiffDay(long beginTime, long endTime) {
        long result = (endTime-beginTime)/1000/60/60/24;
        return result;
    }

    /**
     * 计算日期加减几天之后的日期
     * @param timestamp
     * @param day
     * @return
     */
    public String getDatetimeAddOrMinus(long timestamp,int day){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //取今天日期,如果日期类型为String类型,可以使用df.parse()方法,转换为Date类型
        Date date = new Date();
        try {
            date = df.parse(df.format(new Date(timestamp)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();//new一个Calendar类,把Date放进去
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return df.format(calendar.getTime());
    }

    /**
     * 将时间戳转为日期格式
     * @param timestamp
     * @return
     */
    public String getTimeStampChangeDateTime(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(new Date(timestamp));
        return result;
    }


}
