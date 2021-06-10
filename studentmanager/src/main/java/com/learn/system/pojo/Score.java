package com.learn.system.pojo;

public class Score {
    String courseNo;
    String teacherNo;
    String studentNo;
    int score;

    public Score() {
    }

    public Score(String course_id,String teacher_id,String student_id,int score) {
        this.courseNo = course_id;
        this.teacherNo = teacher_id;
        this.studentNo = student_id;
        this.score = score;
    }

    public String getcourse_id() {
        return courseNo;
    }

    public void setcourse_id(String course_id) {
        this.courseNo = course_id;
    }

    public String getteacher_id() {
        return teacherNo;
    }

    public void setteacher_id(String teacher_id) {
        this.teacherNo = teacher_id;
    }

    public String getstudent_id() {
        return studentNo;
    }

    public void setstudent_id(String student_id) {
        this.studentNo = student_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
