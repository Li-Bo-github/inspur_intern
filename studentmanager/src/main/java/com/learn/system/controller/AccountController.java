package com.learn.system.controller;

import com.learn.system.pojo.Score;
import com.learn.system.service.AccountServiceImpl;
import com.learn.system.service.ManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    ManageServiceImpl manageService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String accountLogin(@RequestParam(value="username", required = false)String username,
                               @RequestParam(value="password", required = false)String password,
                               Model model, HttpSession session){

        if(username==null){
            System.out.println("usernmae null");
            return "index";
        }
        if(password==null){
            System.out.println("password null");
            return "index";
        }

        String pwd = accountService.checkPassword(username);

        if(password.equals(pwd)){
            session.setAttribute("stuNo", username);
            model.addAttribute("userName", username);
            Integer authority = accountService.getAuthority(username);
            session.setAttribute("userType", authority);
            model.addAttribute("userType", authority);

            if(authority.equals(1)){
                ArrayList scoreList = manageService.querySAllScore(username);
                model.addAttribute("scoreList", scoreList);
                return "student";
            } else if(authority.equals(2)){
                return "teacher";
            } else {
                return "index";
            }
        } else {
            model.addAttribute("loginAgain", 1);
            return "index";
        }
    }

}
