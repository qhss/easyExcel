package com.export.easyexcel.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.export.easyexcel.domain.Student;
import com.export.easyexcel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Component
@Scope("prototype")
public class StudentWebListenner extends AnalysisEventListener<Student> {
    @Autowired
    StudentService studentService;

    ArrayList<Student> students = new ArrayList<>();
    public void invoke(Student student, AnalysisContext analysisContext) {

        students.add(student);
        if (students.size()%5==0) {
            studentService.readData(students);
            students.clear();
        }

    }


    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
