package com.export.easyexcel.service.impl;

import com.export.easyexcel.domain.Student;
import com.export.easyexcel.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    public void readData(List<Student> students) {
        for (Student student:students){
            System.out.println("student = " + student);
        }

    }
}
