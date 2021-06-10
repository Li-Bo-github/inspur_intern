package com.example.demo.pojo;

public class score {
    int id;
    String stuNo;
    String courseNo;
    String term;
    int score;
    String courseName;

    public score() {
    }

    public score(String stuNo, String courseNo, String term, int score, String courseName) {
        this.stuNo = stuNo;
        this.courseNo = courseNo;
        this.term = term;
        this.score = score;
        this.courseName = courseName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
