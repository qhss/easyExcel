package com.export.easyexcel.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.export.easyexcel.domain.Student;
import com.export.easyexcel.web.listener.StudentWebListenner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("excel")
public class StudentController {
    @Autowired
    StudentWebListenner studentWebListenner;

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

    //下载
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
    private static List<Student> initData(){
        ArrayList<Student> students = new ArrayList<>();
        Student data = new Student();
        for (int i=0;i<10;i++){
            data.setId("ID编号"+i);
            data.setName("成员编号0"+i);
            data.setGender("男");
            data.setBirthday(new Date());
            students.add(data);
        }
        return students;
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
        map.put("increaseCount",100);
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
}

