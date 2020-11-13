package com.export.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.export.easyexcel.domain.FillData;
import com.export.easyexcel.domain.Student;
import com.export.easyexcel.listener.StudentListener;

import javax.servlet.FilterConfig;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Test {

    /**
     * 工作簿，一个excel文件就是一个工作簿
     * 工作表，一个工作簿中有多个工作表（sheet）
     *
     */
    @org.junit.Test
    public void test01(){

        /**
         * fileName 要读取文件的路径
         *head 文件读取中每一行数据要存储到的实体的类型的class
         * readListener 读监听器，每读一行都会调用一次该对象的invoke，在invoke中可以操作使用读取到的数据
         */
        //获得一个工作簿对象
        ExcelReaderBuilder readWorkbook = EasyExcel.read("easyExcel学生信息读入.xlsx", Student.class, new StudentListener());
        //获得一个工作表对象
        ExcelReaderSheetBuilder sheet = readWorkbook.sheet();
        //读取工作表中的内容
        sheet.doRead();

    }

    /**
     * 导出多个学生对象到表格
     */
    @org.junit.Test
    public void test02(){
        /**
         * fileName 要写入的文件的路径
         *head 封装要写入的实体的类型的class
         */
        //构建一个写的工作簿对象
        ExcelWriterBuilder writeWorkbook = EasyExcel.write("easyExcel学生信息-写入.xlsx", Student.class);
        ExcelWriterSheetBuilder sheet = writeWorkbook.sheet();
        List<Student> students = initData();
        //写
        sheet.doWrite(students);

    }
    private static List<Student> initData(){
        ArrayList<Student> students = new ArrayList<>();
        Student data = new Student();
        for (int i=0;i<10;i++){
            data.setName("成员编号0"+i);
            data.setGender("男");
            data.setBirthday(new Date());
            students.add(data);
        }
        return students;
    }
    /**
     * 单组数据填充
     */
    @org.junit.Test
    public void test03(){

        //准备模板
        String template = "fill_data_template1.xlsx";

        //创建一个工作簿对象
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充单组数据.xlsx", FillData.class).withTemplate(template);
        //得到一个工作表对象
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
//        FillData fillData =new FillData();
//        fillData.setName("小李");
//        fillData.setAge(18);
        //Map填充
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("name","张三");
        map.put("age","12");
        //写数据
        sheet.doFill(map);
    }
    /**
     * 多组数据填充
     */
    @org.junit.Test
    public void test04(){

        //准备模板
        String template = "fill_data_template2.xlsx";

        //创建一个工作簿对象
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充多组数据.xlsx", FillData.class).withTemplate(template);
        //得到一个工作表对象
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        List<FillData> list = initFillData();
        //写数据
        sheet.doFill(list);
    }
    private static List<FillData> initFillData(){
        ArrayList<FillData> fillDatas = new ArrayList<FillData>();
        FillData fillData = new FillData();
        for (int i=0;i<10;i++){
            fillData.setName("成员编号0"+i);
            fillData.setAge(10+i);
            fillDatas.add(fillData);
        }
        return fillDatas;
    }
    /**
     * 多组数据复合填充
     */
    @org.junit.Test
    public void test05(){

        //准备模板
        String template = "fill_data_template3.xlsx";

        //创建一个工作簿对象
        ExcelWriter workBook = EasyExcel.write("Excel组合填充多组数据.xlsx", FillData.class).withTemplate(template).build();
        //得到一个工作表对象
        WriteSheet sheet = EasyExcel.writerSheet().build();
        //组合填充时因为组合数据的不确定性，需要多组填充之后另起一行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();

        List<FillData> list = initFillData();
        HashMap<String, String> dateAndTotal = new HashMap<>();
        dateAndTotal.put("date",new Date().toString());
        dateAndTotal.put("total","1000");

        //多组数据填充
        workBook.fill(list,fillConfig,sheet);
        //单组数据填充
        workBook.fill(dateAndTotal,sheet);

        //关闭流
        workBook.finish();

    }
    /**
     * 水平填充
     */
    @org.junit.Test
    public void test06(){

        //准备模板
        String template = "fill_data_template4.xlsx";

        //创建一个工作簿对象
        ExcelWriter workBook = EasyExcel.write("Excel组合水平填充.xlsx", FillData.class).withTemplate(template).build();
        //得到一个工作表对象
        WriteSheet sheet = EasyExcel.writerSheet().build();
        //组合填充时因为组合数据的不确定性，需要多组填充之后另起一行
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        List<FillData> list = initFillData();
        //多组数据填充
        workBook.fill(list,fillConfig,sheet);

        //关闭流
        workBook.finish();

    }
}
