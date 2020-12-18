package com.export.easyexcel.DateUtil;

import cn.hutool.core.date.DateUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class DateKit {

    public static final String DATE_FORMAT="yyyy-MM-dd";
    public static final String TIME_FORMAT="HH:mm:ss";
    public static final String DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM="yyyy-MM-dd HH:mm";

    static DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter timeFormatter= DateTimeFormatter.ofPattern("HH:mm:ss");
    static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    //获取指定日期的秒
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    //获取指定时间的指定格式
    public static String formatLocalDateTime(LocalDateTime time, String pattern) {
        if (time == null) {
            return null;
        }

        return time.format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String formatLocalDateTime(LocalDateTime time) {
        if (time == null) {
            return null;
        }

        return time.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }
    /**
     *  将日期格式日化
     * @param localDate
     * @param pattern
     * @return
     */
    public static String formatLocalDate(LocalDate localDate,String pattern){
        if (localDate == null) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String formatLocalDate(LocalDate localDate){
        if (localDate == null) {
            return null;
        }

        return localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String formatTime(LocalTime localTime){
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     *  将秒转成日期字符串
     * @param timestrap
     * @return
     */
    public static String formatFromSecond(long timestrap){
        String date = Instant.ofEpochSecond(timestrap).atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return date;
    }

    /**
     * 将毫秒转成日期字符串
     * @param timestrap
     * @return
     */
    public static String formatFromMilli(long timestrap){
        String date=Instant.ofEpochMilli(timestrap).atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return date;
    }

    /**
     * 将日期字符串转换成日期
     * @param date "2017-09-23"
     * @return yyyy-MM-dd
     */
    public static LocalDate parseLocalDate(String date){
        return LocalDate.parse(date);
    }
    /**
     * 将日期字符串转换成日期
     *   eg: "2017.09.23"  "yyyy.MM.dd"
     *   eg: "2017/09/23"  "yyyy/MM/dd"
     * @param date "2017.09.23"
     * @param pattern "yyyy.MM.dd"
     * @return yyyy-MM-dd
     */
    public static LocalDate parseLocalDate(String date,String pattern){
        return LocalDate.parse(date,DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate dateToLocaDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        Objects.requireNonNull(date,"日期不能为空");

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDate();
    }

    //获取当前时间的指定格式
    public static String formatNow(String pattern) {
        return  formatLocalDateTime(LocalDateTime.now(), pattern);
    }

    //获取一天的开始时间
    public static LocalDateTime getDayStart(LocalDateTime date) {
        return date.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    public static LocalDate getMonthOfStart(LocalDate localDate){
        return LocalDate.of(localDate.getYear(),localDate.getMonthValue(),1);
    }
    public static LocalDate getMonthOfEnd(LocalDate localDate){
        int dayOfMonth = localDate.getDayOfMonth();
        return LocalDate.of(localDate.getYear(),localDate.getMonthValue(),dayOfMonth);
    }

    /**
     *
     * @param dateStr yyyy-MM-dd 格式
     * @return
     */
    public static LocalDateTime getDayStart(String dateStr) {
        LocalDateTime time = LocalDate.parse(dateStr.trim()).atTime(0, 0, 0);
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    //获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {

        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0);
    }

    /**
     *
     * @param dateStr yyyy-MM-dd 格式
     * @return
     */
    public static LocalDateTime getDayEnd(String dateStr) {
        LocalDateTime time = LocalDate.parse(dateStr.trim()).atTime(23, 59, 59).withNano(0);
        return time;
    }


    /**
     *
     * @param datetimeStr 2017-12-06 00:00:00
     * @return
     */
    public static LocalDateTime toLocalDateTime(String datetimeStr){
        if (datetimeStr == null) {
            return null;
        }

        return LocalDateTime.parse(datetimeStr,dateTimeFormatter);
    }
    public static LocalDateTime toLocalDateTime(Long timestrap){
        //LocalDateTime.ofEpochSecond(timestrap,0,ZoneId.systemDefault().getGroupId())
//        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(timestrap * 1000).atZone(ZoneId.systemDefault());
//        LocalDate localDate = zonedDateTime.toLocalDate();
//        System.out.println(localDate);
//        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
//        System.out.println(localDateTime.toString());
        return Instant.ofEpochSecond(timestrap).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTimeFromDateStr(String dateStr){
        LocalDateTime time = LocalDate.parse(dateStr.trim()).atTime(0, 0, 0);
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     *
     * @param dateStr 2017-12-06
     * @return
     */
    public static LocalDate toLocalDate(String dateStr){
        if (dateStr == null) {
            return null;
        }

        return LocalDate.parse(dateStr,dateFormatter);
    }

    /**
     *
     * @param timeStr 15:00:01
     * @return
     */
    public static LocalTime toLocalTime(String timeStr){
        return LocalTime.parse(timeStr,timeFormatter);
    }


    public static LocalDate toLocaDate(long timestrap){
        return   Instant.ofEpochMilli(timestrap)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    /**
     * 计算两个日期相差的天数
     * @param start
     * @param end
     * @return
     */
    public static int diffDays(LocalDateTime start,LocalDateTime end){
        long between = ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate());
        return (int) between;
    }


    public static int diffDays(LocalDate start,LocalDate end){
        long diff=ChronoUnit.DAYS.between(start,end);
        return (int)diff;
    }

    public static int getAge(LocalDate birthday){
        Period p = Period.between(birthday, LocalDate.now());
        return p.getYears();
    }


    /**
     * 判断一个日期是否是周末 ： 周六和周日 都是true  其他返回false
     * @param localDate
     * @return
     */
    public static boolean isWeekend(LocalDate localDate){
        DayOfWeek week = localDate.getDayOfWeek();
        if (week.getValue()>=6){
            return true;
        }
        return false;
    }



    /**
     *  根据传入的一个日期 获取这个日期所在周的第一天
     * @param localDate
     * @return
     */
    public static LocalDate getWeekOfFirstDate(LocalDate localDate){
        int week=localDate.getDayOfWeek().getValue();
        LocalDate firstDate=localDate.plusDays(-week+1);
        return firstDate;
    }

    /**
     * 根据传入的一个日期 获取这个日期所在周的第一天
     * @param localDate
     * @return
     */
    public static LocalDate getWeekOfLastDate(LocalDate localDate){
        int week=localDate.getDayOfWeek().getValue();
        LocalDate lastDate=localDate.plusDays(7-week);
        return lastDate;
    }

    public static String getWeek(LocalDate localDate){
        switch (localDate.getDayOfWeek().getValue()){
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期日";
        }
        return localDate.getDayOfWeek().getValue()+"";
    }



    public static LocalDate convertDateStr(String dateStr){
        LocalDate date=null;
        boolean convert=true;
        String parse ;
        try {
            parse = DateUtil.parse(dateStr).toDateStr();
            date = DateKit.toLocalDate(parse);
        } catch (Exception e) {
            convert=false;
        }

        if (!convert){

            try {
                parse = DateUtil.parse(dateStr,"yyyy-M-d").toDateStr();
                date= DateKit.toLocalDate(parse);
                convert=true;
            } catch (Exception e) {
                convert=false;
            }
        }

        if (!convert){
            try {
                parse = DateUtil.parse(dateStr,"yyyy.M.d").toDateStr();
                date= DateKit.toLocalDate(parse);
                convert=true;
            } catch (Exception e) {
                convert=false;
            }
        }



        if (!convert){

            try {
                parse = DateUtil.parse(dateStr,"yyyy/M/d").toDateStr();
                date= DateKit.toLocalDate(parse);
                convert=true;
            } catch (Exception e) {
                convert=false;
            }
        }

        if (!convert){

            try {
                Date d = new Date(dateStr);
                date=DateKit.dateToLocaDate(d);
                convert=true;
            } catch (Exception e) {
                convert=false;
            }
        }


        if (convert){
            return date;
        }else {
            throw new RuntimeException("无法识别的日期");
        }

    }

    public static List<String> getBetweenDate(LocalDate startDate, LocalDate endDate){
        List<String> list = new ArrayList<>();
        if (startDate==null || endDate==null){
            return list;
        }
        if (startDate.equals(endDate)){
            list.add(startDate.toString());
            return list;
        }

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }


    public static void main(String[] args) {
        getBetweenDate(LocalDate.now(),LocalDate.now());
        System.out.println(getDayStart(LocalDateTime.now()).toString());
        System.out.println(getDayEnd(LocalDateTime.now()));
        System.out.println(getDayStart("2017-12-21"));
        System.out.println(getDayEnd("2017-12-21"));
        long between = ChronoUnit.DAYS.between(LocalDateTime.now(), LocalDateTime.now());
        System.out.println(between);
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(formatLocalDateTime(LocalDateTime.now(),"yyyy年MM月dd日\nE"));

//        System.out.println(formatFromSecond(System.currentTimeMillis()/1000));
        System.out.println(formatFromMilli(Instant.now().toEpochMilli()));
        System.out.println("LocalDateTime 显示时间"+formatLocalDateTime(LocalDateTime.now(),"HH:mm"));
        System.out.println(getWeek(LocalDate.now().plusDays(3)));
        System.out.println(convertDateStr("2019/10-10"));
//        LocalDate parse = LocalDate.parse("2019-1");
//        System.out.println(parse);
    }
}

