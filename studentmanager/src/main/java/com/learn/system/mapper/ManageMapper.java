package com.learn.system.mapper;

import com.learn.system.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper
public interface ManageMapper {



    void insertScore(Score grade);


    void deleteScore(String courseNo,String studentNo);

    ArrayList querySAllScore(String studentNo);

}
