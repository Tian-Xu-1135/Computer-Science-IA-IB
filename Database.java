package com.example.computerscienceia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database {
    public static String WORDS = "Words.txt";
    public static String TEST = "Test.txt";
    public static String CATEGORIES = "Categories.txt";

    public ArrayList <Word> allWords;
    public ArrayList <Test> allTests;
    public ArrayList <String> categories;

    //constructor
    public Database(){
        allWords = new ArrayList<>();
        allTests = new ArrayList<>();
        categories = new ArrayList<>();
        readWordFile(WORDS);
        readTestFile(TEST);
        readCategoryFile(CATEGORIES);
    }

    public ArrayList<Word> getAllWords() {
        return allWords;
    }
    public ArrayList<Test> getAllTests(){
        return allTests;
    }
    public ArrayList<String> getAllCategories(){
        return categories;
    }

    //file reader for WORDS
    public void readWordFile(String fileName){
        Scanner inFile;
        try{
            inFile = new Scanner(new File(WORDS));
            while(inFile.hasNextLine()){
                String wordInEnglish = inFile.nextLine();
                String wordInSpanish = inFile.nextLine();
                String category = inFile.nextLine();
                String timesWrong = inFile.nextLine();
                String timesRight = inFile.nextLine();

                int a = Integer.parseInt(timesWrong);
                int b = Integer.parseInt(timesRight);

                allWords.add(new Word(wordInEnglish, wordInSpanish, category, a, b));
            }
            inFile.close();
        } catch (IOException e) {
            System.out.println("Error while writing to word file:" + e.getMessage());
        }
    }
    //upload allWords to WORDS
    public void uploadWordFile() {
        try (PrintWriter outFile = new PrintWriter(new FileWriter(WORDS))) {
            //clear file and write all the words back
            for (int i = 0; i < allWords.size(); i++) {
                outFile.println(allWords.get(i).getEnglish());
                outFile.println(allWords.get(i).getSpanish());
                outFile.println(allWords.get(i).getCategory());
                outFile.println(allWords.get(i).getTimesRight());
                outFile.println(allWords.get(i).getTimesWrong());
            }
        } catch (IOException e) {
            System.out.println("Error while writing to word file: " + e.getMessage());
        }
    }

    //file reader for TEST
    public void readTestFile(String fileName){
        Scanner inFile;
        try{
            inFile = new Scanner(new File(TEST));
            while(inFile.hasNextLine()){
                String date = inFile.nextLine();
                String grade = inFile.nextLine();

                LocalDate a = LocalDate.parse(date);
                double b = Double.parseDouble(grade);

                allTests.add(new Test(a, b));
            }
            inFile.close();
        } catch (IOException e) {
            System.out.println("Error while writing to test file:" + e.getMessage());
        }
    }
    //upload allTests to file
    public void uploadTestFile() {
        try (PrintWriter outFile = new PrintWriter(new FileWriter(TEST))) {
            for (int i = 0; i < allTests.size(); i++) {
                outFile.println(allTests.get(i).getDate().toString());
                outFile.println(allTests.get(i).getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error while writing to test file: " + e.getMessage());
        }
    }

    //file reader for CATEGORIES
    public void readCategoryFile(String fileName){
        Scanner inFile;
        try{
            inFile = new Scanner(new File(CATEGORIES));
            while(inFile.hasNextLine()){
                String category = inFile.nextLine();

                categories.add(category);
            }
            inFile.close();
        } catch (IOException e) {
            System.out.println("Error while reading from category file:" + e.getMessage());
        }
    }
    //upload categories to file
    public void uploadCategoriesFile() {
        try (PrintWriter outFile = new PrintWriter(new FileWriter(CATEGORIES))) {
            for (int i = 0; i < categories.size(); i++) {
                outFile.println(categories.get(i));
            }
        } catch (IOException e) {
            System.out.println("Error while writing to category file: " + e.getMessage());
        }
    }
}