package com.export.easyexcel.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.export.easyexcel.domain.HttpResult;
import com.export.easyexcel.domain.Student;
import com.export.easyexcel.DateUtil.DateKit;
import com.export.easyexcel.web.listener.StudentWebListenner;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("excel")
@Slf4j
public class StudentController {
    @Autowired
    StudentWebListenner studentWebListenner;


    private static List<Student> initData(){
        ArrayList<Student> students = new ArrayList<>();
        for (int i=0;i<10;i++){
            Student data = new Student();
            data.setId("ID编号"+i);
            data.setName("成员编号0"+i);
            data.setGender("男");
            data.setBirthday(new Date());
            if (i==3||i==4){
                data.setGender("女");
            }
            students.add(data);
        }
        return students;
    }
    /*
     * @param map
     * @return map
     * 统计当月的所有天数和星期数
     * @author qhs
     * @date 2020/12/17 11:16
     */
    private  Map<String,Object> initDate(Map<String,Object> map){
        LocalDate now = LocalDate.now();
        int monthValue = now.getMonthValue();
        // 该月份的第一天日期
        LocalDate first = now.with(TemporalAdjusters.firstDayOfMonth());
        //该月份的最后一天日期
        LocalDate last = now.with(TemporalAdjusters.lastDayOfMonth());
        int i=last.getDayOfMonth() - first.getDayOfMonth();
        ArrayList dayList = new ArrayList();

        ArrayList weekList = new ArrayList();
//        列表中有多个属性时，可以通过创建对象在添加属性方式
//        for (int j = 1;j <= i+1;j++){
//            JSONObject item1 = new JSONObject();
//            JSONObject item2 = new JSONObject();
//            item1.put("day",j);
//            LocalDate localDate = first.plusDays(j-1);
//            String week = DateKit.getWeek(localDate);
//            item2.put("week",week);
//            dayList.add(item1);
//            weekList.add(item2);
//
//        }
        for (int j = 1;j <= i+1;j++){
            dayList.add(j);
            LocalDate localDate = first.plusDays(j-1);
            String week = DateKit.getWeek(localDate);
            weekList.add(week);

        }
        map.put("month",monthValue);
        map.put("daylist",dayList);
        map.put("weeklist",weekList);

        //通过工具类获取当前是星期几


        return map;
    }

    //上传
    @ResponseBody
    @RequestMapping("upload")
    public String  upload(MultipartFile excelFile){
        try {
            ExcelReaderBuilder readWorkbook= EasyExcel.read(excelFile.getInputStream(), Student.class,studentWebListenner);
            ExcelReaderSheetBuilder sheet = readWorkbook.sheet();
            sheet.doRead();

            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }

    }

    //下载（不用模板）
    @ResponseBody
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        /**
         * fileName 要写入的文件的路径
         *head 封装要写入的实体的类型的class
         */
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("测试下载", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");
        //构建一个写的工作簿对象
        ExcelWriterBuilder writeWorkbook = EasyExcel.write(response.getOutputStream(), Student.class);
        ExcelWriterSheetBuilder sheet = writeWorkbook.sheet();
        List<Student> students = initData();
        //将数据写入到工作表
        sheet.doWrite(students);

    }

    /**
     * 使用模板导出
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        //模板
//        InputStream inputStream = this.getClass().getResourceAsStream("template/report_template.xlsx");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/template/report_template.xlsx");


        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("测试报表下载", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xls");
  //      String inputStream="report_template.xlsx";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("date",new Date());
        map.put("increaseCount",500);
        map.put("totalCount",1000);
        map.put("increaseCountWeek",20);
        map.put("increaseCountMonth",60);

        List<Student> students = initData();
        map.put("studentList",students);
        ExcelWriter workBook = EasyExcel.write(response.getOutputStream()).withTemplate(inputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        workBook.fill(map,writeSheet);
        workBook.fill(map.get("studentList"),writeSheet);
        workBook.finish();

    }

    /*http://localhost:8080/excel/freemarker/download
     使用freeMarker模板导出
     * @param response
     * @return void
         * @author qhs
         * @date 2020/12/16 11:50
         */
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

    @PostConstruct
    public void initConfiguration() {
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(this.getClass(), "/template");
    }

    @ResponseBody
    @RequestMapping("freemarker/download")
    public HttpResult freeMarkerDownload(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            Template template = configuration.getTemplate("Freemarker简单列表导出.ftl");
            List<Student> students = initData();
            map.put("info",students);
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("简单列表导出", "UTF-8") + ".xlsx");
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
            template.process(map, writer);
            writer.flush();
            writer.close();
            return HttpResult.ok("导出成功");
        } catch (IOException | TemplateException e) {
            log.error("解析模板错误" + e.getMessage());
            return HttpResult.error("系统繁忙");
        }

    }
    @ResponseBody
    @RequestMapping("freemarker/download2")
    public HttpResult freeMarkerDownload2(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            Map<String, Object> map1 = initDate(map);
            Template template = configuration.getTemplate("freeMarker横向列表导出.ftl");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("横向列表导出", "UTF-8") + ".xlsx");
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
            template.process(map1, writer);
            writer.flush();
            writer.close();
            return HttpResult.ok("导出成功");
        } catch (IOException | TemplateException e) {

            log.error("解析模板错误" + e.getMessage());
            return HttpResult.error("系统繁忙");
        }

    }
}

