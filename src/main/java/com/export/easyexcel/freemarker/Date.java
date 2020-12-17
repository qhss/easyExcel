package com.export.easyexcel.freemarker;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Date {
    /**
    * @param args
    * @return void 
            * @author qhs 
            * @date 2020/12/15 17:45 
            */
    public static void main(String[] args) {
        //创建一个指定日期
        LocalDate now =LocalDate.parse("2020-12-15");

        // 该月份的第一天日期
        LocalDate first = now.with(TemporalAdjusters.firstDayOfMonth());
        //该月份的最后一天日期
        LocalDate last = now.with(TemporalAdjusters.lastDayOfMonth());

        int dayOfMonth = last.getDayOfMonth();
        System.out.println(dayOfMonth);
        //获取当前月份
        int monthValue = now.getMonthValue();
        System.out.println(now);
        System.out.println(first);
        System.out.println(last);

        //通过工具类获取当前是星期几
        String week1 = DateKit.getWeek(now);
        //只取数字几
        week1=week1.replace("星期","");
        System.out.println(week1);
    }

}
