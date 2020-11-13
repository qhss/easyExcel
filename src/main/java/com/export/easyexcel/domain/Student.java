package com.export.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.util.Date;
@Data
public class Student {
//    @ExcelProperty("成员id")
//    @ExcelIgnore
    private String id;
//    @ExcelProperty("姓名")

//    @ColumnWidth(20)
    private String name;
//    @ExcelProperty("性别")
    private String gender;
//    @ExcelProperty("出生日期")
//    @ColumnWidth(20)
//    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;
}
