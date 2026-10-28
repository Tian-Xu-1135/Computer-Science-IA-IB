package com.example.computerscienceia;
import java.time.LocalDate;

public class Test {
    private LocalDate date;
    private double grade;
    //constructor
    public Test (LocalDate date, double grade){
        this.date = date;
        this.grade = grade;
    }
    //getter
    public LocalDate getDate(){
        return date;
    }
    public double getGrade(){
        return grade;
    }
}
