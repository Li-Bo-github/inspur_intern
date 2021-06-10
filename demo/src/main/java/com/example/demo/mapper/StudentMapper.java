package com.example.demo.mapper;

import com.example.demo.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface StudentMapper {


    //通过学号查询某个学生信息
    Student selectStu(String stuNo);

    //查询所有学生信息
    List<Student> selectAllStu();

    //查找学生所有成绩
    List<score> queryAllScore(String stuNo);

    List<course> queryAllCourse(String stuName);
}
