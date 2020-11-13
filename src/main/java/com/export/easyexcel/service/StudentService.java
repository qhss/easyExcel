package com.export.easyexcel.service;

import com.export.easyexcel.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    void readData(List<Student> students);
}
