package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.pojo.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/getScore", method = RequestMethod.GET)
    public ModelAndView getScore(ModelAndView mv, HttpSession session){

        mv.addObject("restriction", session.getAttribute("restriction"));
        mv.addObject("username",session.getAttribute("username") );
        List<score> scoreList = new ArrayList<score>();
        String username = (String)session.getAttribute("username");
        scoreList = studentService.queryAllScore(username);
        mv.addObject("scoreList", scoreList);
        System.out.println(scoreList);
        mv.setViewName("checkScore");
        return mv;
    }
}
