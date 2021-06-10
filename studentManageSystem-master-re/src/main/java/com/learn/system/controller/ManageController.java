package com.learn.system.controller;

import com.learn.system.pojo.ClassInfo;
import com.learn.system.pojo.Course;
import com.learn.system.pojo.Score;
import com.learn.system.pojo.Student;
import com.learn.system.service.serviceImpl.ManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageController {
    @Autowired
    ManageServiceImpl manageService;

    @RequestMapping(value = "/insertClass", method = RequestMethod.POST)
    public ModelAndView insertClass(@RequestParam("classNo")String classNo,
                                    @RequestParam("className")String className,
                                    @RequestParam("institute")String institute,
                                    @RequestParam("grade")int grade,
                                    ModelAndView mv, HttpSession session){
        //先检查是否存在相同的班级号
        int isExist = manageService.isExistClassNo(classNo);
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        if(isExist == 1){           //存在，不能插入
            mv.addObject("isExist", 1);
            mv.setViewName("welcome");
        } else {
            ClassInfo cla = new ClassInfo(classNo, className, institute, grade);
            manageService.insertClassInfo(cla);
            mv.addObject("success", 1);
            mv.setViewName("welcome");
        }
        return mv;
    }

    //get方式跳转，不用接受数据
    @RequestMapping(value = "/insertClass", method = RequestMethod.GET)
    public ModelAndView insertClass(ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        mv.setViewName("welcome");
        return mv;
    }

    //跳转函数，跳转到学生插入页面都要通过这个函数获取可供选择的班级号
    @RequestMapping("/toInsertStuInfo")
    public String toInsertStuInfo(Model model, HttpSession session){
        model.addAttribute("userType", session.getAttribute("userType"));
        model.addAttribute("userName", session.getAttribute("stuNo"));
        List<String> str = new ArrayList<String>();
        str = manageService.queryAllClassNo();      //获取所有的班级号
        model.addAttribute("classNo", str);
        return "insertStuInfo";
    }

    @RequestMapping(value = "/insertStu", method = RequestMethod.POST)
    public ModelAndView insertStu(@RequestParam("stuNo") String stuNo,
                                  @RequestParam("stuName") String stuName,
                                  @RequestParam("nat") String nat,
                                  @RequestParam("birthday") String birthday,
                                  @RequestParam("sex") int sex,
                                  @RequestParam("classNo")String classNo,
                                  ModelAndView mv, HttpSession session) throws ParseException {
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        List<String> str = new ArrayList<String>();
        str = manageService.queryAllClassNo();      //获取所有的班级号
        mv.addObject("classNo", str);
        int isExist = manageService.isExistStuNo(stuNo);
        if(isExist != 0){   //存在
            mv.addObject("isExist", 1);
        } else {
            //将生日字符串转换成date类型
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new java.sql.Date(simple.parse(birthday).getTime());
            Student stu = new Student(stuNo, stuName, sex,date, nat, classNo);
            manageService.insertStudentInfo(stu);
            mv.addObject("success", 1);
        }
        mv.setViewName("insertStuInfo");
        return mv;
    }

    //deleteStu显示函数
    @RequestMapping(value="/deleteStu/{pageNum}" , method = RequestMethod.GET)
    public ModelAndView deleteStu(@PathVariable(value="pageNum")int pageNum,
                                  ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        int offset = 5;     //每页显示的数量
        int total = manageService.queryAllStudent().size(); //学生信息总数
        int totalPage = total/offset;
        if(total%offset !=0){
            totalPage++;
        }
        if(totalPage == 0){
            totalPage=1;
        }
        mv.addObject("totalPage", totalPage);
        mv.addObject("pageNum", pageNum);
        List<Student> studentList = new ArrayList<Student>();
        studentList = manageService.querySomeStudent(manageService.queryAllStudent(),
                pageNum, offset);
        mv.addObject("studentList", studentList);
        mv.setViewName("deleteStu");
        return mv;
    }

    //删除函数
    @RequestMapping(value = "/delete/{stuNo}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("stuNo")String stuNo,
                               ModelAndView mv, HttpSession session){
        manageService.deleteStuByNo(stuNo);
        mv.setViewName("redirect:/deleteStu/1"); //转发请求
        return mv;
    }

    //new
    @RequestMapping("/toInsertScore")
    public String toInsertScore(Model model, HttpSession session) {
        model.addAttribute("userType", session.getAttribute("userType"));
        model.addAttribute("userName", session.getAttribute("stuNo"));
        return "insertScore";
    }

    @RequestMapping(value = "/insertSco", method = RequestMethod.POST)
    public ModelAndView insertSco(@RequestParam("stuNo") String stuNo,
                                  @RequestParam("courseNo") String courseNo,
                                  @RequestParam("term") String term,
                                  @RequestParam("score") int score,
                                  ModelAndView mv, HttpSession session) throws ParseException {
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        //create new score
        Score sco = new Score(stuNo, courseNo, term,score);
        manageService.insertScore(sco);
        mv.addObject("success", 1);
        mv.setViewName("insertScore");
        return mv;
    }

    @RequestMapping(value="/deleteScore/{pageNum}" , method = RequestMethod.GET)
    public ModelAndView deleteScore(@PathVariable(value="pageNum")int pageNum,
                                  ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        int offset = 5;     //每页显示的数量
        int total = manageService.queryAllStudent().size(); //学生信息总数
        int totalPage = total/offset;
        if(total%offset !=0){
            totalPage++;
        }
        if(totalPage == 0){
            totalPage=1;
        }
        mv.addObject("totalPage", totalPage);
        mv.addObject("pageNum", pageNum);
        List<Student> studentList = new ArrayList<Student>();
        studentList = manageService.querySomeStudent(manageService.queryAllStudent(),
                pageNum, offset);
        mv.addObject("studentList", studentList);
        mv.setViewName("deleteScore");
        return mv;
    }

    //删除函数
    @RequestMapping(value = "/deletescore/{stuNo}", method = RequestMethod.GET)
    public ModelAndView deletescore(@PathVariable("stuNo")String stuNo,ModelAndView mv, HttpSession session){
        manageService.deleteScoreByNo(stuNo);
        mv.setViewName("redirect:/deleteScore/1"); //转发请求
        return mv;
    }

    //deleteClass
    @RequestMapping(value="/deleteClass/{pageNum}" , method = RequestMethod.GET)
    public ModelAndView deleteClass(@PathVariable(value="pageNum")int pageNum,
                                  ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        int offset = 5;     //每页显示的数量
        int total = manageService.queryAllClassNo().size(); //学生信息总数
        int totalPage = total/offset;
        if(total%offset !=0){
            totalPage++;
        }
        if(totalPage == 0){
            totalPage=1;
        }
        mv.addObject("totalPage", totalPage);
        mv.addObject("pageNum", pageNum);
        List<ClassInfo> classList = new ArrayList<ClassInfo>();
        classList = manageService.querySomeClass(manageService.queryAllClass(),
                pageNum, offset);
        mv.addObject("classList", classList);
        mv.setViewName("deleteClass");
        return mv;
    }

    //删除函数
    @RequestMapping(value = "/deleteCla/{classNo}", method = RequestMethod.GET)
    public ModelAndView deleteCla(@PathVariable("classNo")String classNo,
                               ModelAndView mv, HttpSession session){
        manageService.deleteClassInfo(classNo);
        mv.setViewName("redirect:/deleteClass/1"); //转发请求
        return mv;
    }




    @RequestMapping("/toInsertCourseInfo")
    public String toInsertCourseInfo(Model model, HttpSession session){
        model.addAttribute("userType", session.getAttribute("userType"));
        model.addAttribute("userName", session.getAttribute("stuNo"));
        List<String> str = new ArrayList<String>();
        str = manageService.queryAllCourseNo();      //获取所有的班级号
        model.addAttribute("courseNo", str);
        return "insertCourse";
    }

    @RequestMapping(value = "/insertCourse", method = RequestMethod.POST)
    public ModelAndView insertCourse(@RequestParam("courseNo") String courseNo,
                                  @RequestParam("courseName") String courseName,
                                  @RequestParam("credit") int credit,
                                  @RequestParam("courseHour") int courseHour,
                                  @RequestParam("priorCourse") String priorCourse,
                                  ModelAndView mv, HttpSession session) throws ParseException {
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        List<String> str = new ArrayList<String>();
        str = manageService.queryAllCourseNo();      //获取所有的班级号
        mv.addObject("courseNo", str);
        if (str.contains(courseNo)){
            mv.addObject("isExist", 1);
        } else {
            Course course = new Course(courseNo, courseName, credit,courseHour, priorCourse);
            manageService.insertCourse(course);
            mv.addObject("success", 1);
        }
        mv.setViewName("insertCourse");
        return mv;
    }

}
