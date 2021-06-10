package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.*;
@Component
public class StudentService {

    @Autowired
    StudentMapper studentMapper;


    public Student selectStu(String stuName) {
        return studentMapper.selectStu(stuName);
    }


    public List<Student> selectAllStu() {
        return studentMapper.selectAllStu();
    }

    public List<course> queryAllCourse(String stuName) {
        return studentMapper.queryAllCourse(stuName);
    }

    public List<score> queryAllScore(String stuName) {
        return studentMapper.queryAllScore(stuName);
    }

}

