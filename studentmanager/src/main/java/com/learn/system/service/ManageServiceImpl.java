package com.learn.system.service;

import com.learn.system.mapper.ManageMapper;
import com.learn.system.pojo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ManageServiceImpl {

    @Autowired
    ManageMapper manageMapper;


    public void insertScore(Score grade) {
        manageMapper.insertScore(grade);
    }

    public void deleteScore(String courseNo, String studentNo) {
        manageMapper.deleteScore(courseNo,studentNo);
    }

    public ArrayList querySAllScore(String studentNo) {
       return manageMapper.querySAllScore(studentNo);
    }


}
