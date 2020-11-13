package com.export.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.export.easyexcel.domain.Student;

/**
 * 读取文档的监听器
 */
public class StudentListener extends AnalysisEventListener<Student> {

    /**
     *每读一行都会调用一次该对象的invoke，在invoke中可以操作使用读取到的数据
     * @param student
     * @param analysisContext
     */

    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("student = " + student);

    }

    /**
     *读取完文档之后调用的内容
     * @param
     * @param analysisContext
     */

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
