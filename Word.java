package com.example.computerscienceia;

import java.util.Locale;

public class Word {
    private String english;
    private String spanish;
    private String category;
    private int timesRight;
    private int timesWrong;

    //constructor
    public Word (String english, String spanish, String category, int tR, int tW){
        this.english = english;
        this.spanish = spanish;
        this.category = category;
        timesRight = tR;
        timesWrong = tW;
    }
    //getters
    public String getEnglish(){
        return english;
    }
    public String getSpanish(){
        return spanish;
    }
    public String getCategory(){
        return category;
    }
    public int getTimesRight(){
        return timesRight;
    }
    public int getTimesWrong(){
        return timesWrong;
    }
    public double getRtW(){
        return timesRight/timesWrong;
    }

    //setters
    public void setTimesRight(int r){
        timesRight = r;
    }
    public void setTimesWrong(int w){
        timesWrong = w;
    }





}
