package com.learn.system.controller;

import com.learn.system.pojo.Score;
import com.learn.system.service.ManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageController {
    @Autowired
    ManageServiceImpl manageService;

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public ModelAndView insertScore(@RequestParam("courseNo")String courseNo,
                                    @RequestParam("teacherNo")String teacherNo,
                                    @RequestParam("studentNo")String studentNo,
                                    @RequestParam("score")int score,
                                    ModelAndView mv, HttpSession session){

        Score new_score = new Score(courseNo, teacherNo, studentNo, score);
        manageService.insertScore(new_score);
        mv.addObject("success", 1);



        mv.setViewName("teacher");
        return mv;
    }




    @RequestMapping(value="/student" , method = RequestMethod.GET)
    public ModelAndView studentdisplay(ModelAndView mv, HttpSession session){

        mv.setViewName("student");
        return mv;
    }


}
