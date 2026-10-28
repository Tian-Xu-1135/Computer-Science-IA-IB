package com.example.computerscienceia;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HelloController {

    @FXML
    private ComboBox dropDown_Tcategory1;
    @FXML
    private ComboBox dropDown_Tcategory3;
    @FXML
    private ComboBox dropDown_Tcategory2;
    @FXML
    private Text text_errorFWT;
    @FXML
    private ComboBox dropDown_chooseNewCategory;
    @FXML
    private TextField textField_newWordEnglish;
    @FXML
    private MenuItem menuItem_category;
    @FXML
    private VBox vBox_addNewWord;
    @FXML
    private Button button_addNewWord;
    @FXML
    private TextField textField_newWordSpanish;
    @FXML
    private VBox vBox_StartingPage;
    @FXML
    private MenuItem menuItem_word;
    @FXML
    private MenuItem menuItem_testCategory;
    @FXML
    private MenuItem menuItem_fcRandom;
    @FXML
    private MenuItem menuItem_fcCategory;
    @FXML
    private MenuItem menuItem_fcFW;
    @FXML
    private MenuItem menuItem_testRandom;
    @FXML
    private MenuItem menuItem_testFW;
    @FXML
    private TextField textField_newCategory;
    @FXML
    private Button button_addNewCategory;
    @FXML
    private VBox vBox_addNewCategory;
    @FXML
    private TextField textField_numOfQ;
    @FXML
    private VBox vBox_chooseCategoryForT;
    @FXML
    private Text text_streaks;
    @FXML
    private VBox vBox_duringTest;
    @FXML
    private Button button_TconfirmAndNext;
    @FXML
    private TextField textField_answerInEnglishTest;
    @FXML
    private Text text_wordInSpanishDuringTest;
    @FXML
    private Button button_pickCategoryForT;
    @FXML
    private ProgressBar progressBar_testProgress;
    @FXML
    private Button button_tryTestAgain;
    @FXML
    private Text text_scoreEndOfT;
    @FXML
    private VBox vBox_endOfTest;
    @FXML
    private TextField textField_numFCforRandom;
    @FXML
    private Button button_startRandomFC;
    @FXML
    private VBox vBox_selectCategoryFC;
    @FXML
    private VBox vBox_numSelectRandomFC;
    @FXML
    private Button button_categoryStartFC;
    @FXML
    private ComboBox dropDown_selectCategoryFC;
    @FXML
    private Button button_nextFC;
    @FXML
    private Button button_previousFC;
    @FXML
    private VBox vBox_duringFC;
    @FXML
    private Button button_flipFC;
    @FXML
    private Text text_displayWordFC;
    @FXML
    private VBox vBox_statistics;
    @FXML
    private Text text_totalNumCategories;
    @FXML
    private Text text_averageGradePerT;
    @FXML
    private Text text_totalNumWords;
    @FXML
    private Text text_numTthisMonth;
    @FXML
    private Text text_lifeTimeNumT;
    @FXML
    private Text text_errorRandom;
    @FXML
    private VBox vBox_allWords;
    @FXML
    private VBox vBox_allFW;
    @FXML
    private VBox vBox_allCategory;
    @FXML
    private MenuItem menuItem_allStatistics;
    @FXML
    private TableView tableView_endOfTest;
    @FXML
    private VBox vBox_FWT;
    @FXML
    private VBox vBox_categoryT;
    @FXML
    private VBox vBox_randomT;
    @FXML
    private Button button_FWStartTest;
    @FXML
    private Button button_randomStartTest;
    @FXML
    private Text text_categoryTerrormsg;
    @FXML
    private Text text_categoryFC;
    @FXML
    private VBox vBox_FWFC;
    @FXML
    private Button button_FWStartFC;
    @FXML
    private Text text_errorFWFC;
    @FXML
    private Text text_top10FW;
    @FXML
    private BarChart barChart_monthlyActivity;
    @FXML
    private MenuItem menuItem_allCategories;
    @FXML
    private MenuItem menuItem_allWords;
    @FXML
    private MenuItem menuItem_allFW;
    @FXML
    private TableView tableView_allWords;
    @FXML
    private TableView tableView_allCategories;
    @FXML
    private TableView tableView_allFW;
    @FXML
    private Text text_errorRandomFC;

    //instantiate new database object and initialise relevant arraylists
    Database database = new Database();
    ArrayList<Word> allWords = database.getAllWords();
    ArrayList<Test> allTests = database.getAllTests();
    ArrayList<String> allCategories = database.getAllCategories();
    ArrayList<Word> FW = new ArrayList<>();
    ArrayList<Integer> categoryCount = new ArrayList<>();

    //make sure all is set for beginning the application
    @FXML
    private void initialize() {
        //showing the start page
        setVboxVisible(vBox_StartingPage);

        //set daily streaks
        text_streaks.setText("Daily Streaks: " + String.valueOf(calcDailyStreaks()));

        refreshDropDown();
    }

    //handles all GUI menu items and buttons-----------------------------------------------------------------
    @FXML
    private void handlerItems(ActionEvent event) {
        Object source = event.getSource();

        //ADD-----------------------------------------------------------------

        //add --> word (menuItem)
        if (source == menuItem_word) {
            setVboxVisible(vBox_addNewWord);
        }
        //upon clicking "add" button in new word
        if (source == button_addNewWord) {

            //take the user inputs
            String newEnglish = textField_newWordEnglish.getText();
            String newSpanish = textField_newWordSpanish.getText();
            String newCategory = (String) dropDown_chooseNewCategory.getValue();

            //instantiate new word using user inputs and added to allWords
            database.allWords.add(new Word(newEnglish, newSpanish, newCategory, 1, 1));

            //clearing all textField and comboBox for future inputs
            textField_newWordEnglish.clear();
            textField_newWordSpanish.clear();
            dropDown_chooseNewCategory.setValue(null);

            //back to start page
            setVboxVisible(vBox_StartingPage);

            //write words onto word file, saving the new word in case program is shut shown
            database.uploadWordFile();
        }

        //add --> category (menuItem)
        if (source == menuItem_category) {
            setVboxVisible(vBox_addNewCategory);
        }
        //upon clicking "add" button in new category
        if (source == button_addNewCategory) {
            //take user input
            String newCategory = textField_newCategory.getText();

            //add the new category into arraylist
            database.categories.add(newCategory);

            //clear textField for future use
            textField_newCategory.clear();

            //back to start page
            setVboxVisible(vBox_StartingPage);

            //upload categories onto category file in case program is shut down
            database.uploadCategoriesFile();

            //make sure new category is on the comboBoxes for category choices
            refreshDropDown();
        }

        //TEST-----------------------------------------------------------------

        //test --> frequently wrong (menuItem))
        if (source == menuItem_testFW) {
            setVboxVisible(vBox_FWT);
            //hide/clear error message
            text_errorFWT.setVisible(false);
            text_errorFWT.setText("");
        }
        //upon clicking "start" button in fw(frequently wrong) test
        if (source == button_FWStartTest) {
            //checking fw words are to date
            checkingFW();
            //if there are no fw words
            if (FW == null || FW.isEmpty()) {
                //error message
                text_errorFWT.setVisible(true);
                text_errorFWT.setText("You have no frequently wrong words, well done!!!");
            } else { //if there are fw words
                ArrayList<Word> words = FW;
                //run test using fw arrayList
                runTest(words);
            }
        }

        //test --> category (menuItem))
        if (source == menuItem_testCategory) {
            setVboxVisible(vBox_chooseCategoryForT);
            //hide/clear error message
            text_categoryTerrormsg.setVisible(false);
            text_categoryTerrormsg.setText("");
        }
        //upon clicking "start" button in category test
        if (source == button_pickCategoryForT) {

            //get selected categories
            String category1 = (String) dropDown_Tcategory1.getValue();
            String category2 = (String) dropDown_Tcategory2.getValue();
            String category3 = (String) dropDown_Tcategory3.getValue();

            //put selected categories into an arrayList
            ArrayList<String> selectedCategories = new ArrayList<>();
            if (category1 != null)
                selectedCategories.add(category1);
            if (category2 != null)
                selectedCategories.add(category2);
            if (category3 != null)
                selectedCategories.add(category3);

            //error message if no category selected
            if (selectedCategories.isEmpty()) {
                text_categoryTerrormsg.setVisible(true);
                text_categoryTerrormsg.setText("Please select at least one category for the test!");
                return;
            }

            //get all words from selected categories and put in arraylist
            ArrayList<Word> words = new ArrayList<>();
            for (int i = 0; i < selectedCategories.size(); i++) {
                //error msg if there are no words in a category
                if (numWordInCategory(selectedCategories.get(i))== 0){
                    text_categoryTerrormsg.setVisible(true);
                    text_categoryTerrormsg.setText("One of the categories you have selected contains no words!");
                    return;
                }
                //if no error, proceed in adding all words of selected categories into arraylist
                for (int j = 0; j < allWords.size(); j++) {
                    if (allWords.get(j).getCategory().equals(selectedCategories.get(i))) {
                        words.add(allWords.get(j));
                    }
                }
            }

            //clear dropdowns for future use
            dropDown_Tcategory1.setValue(null);
            dropDown_Tcategory2.setValue(null);
            dropDown_Tcategory3.setValue(null);

            //run test using the arraylist created earlier
            runTest(words);
        }

        //test --> random (menuItem))
        if (source == menuItem_testRandom) {
            setVboxVisible(vBox_randomT);
            //hide/clear error message
            text_errorRandom.setVisible(false);
            text_errorRandom.setText("");
        }
        //upon clicking "start" button in random test
        if (source == button_randomStartTest) {
            try { //using try catch for input validation (in case input is not integer)

                //get user input for number of questions
                String x = textField_numOfQ.getText();
                int numQ = Integer.parseInt(x);

                //obtain words for test
                ArrayList<Word> wordsForTest = new ArrayList<Word>();
                Random random = new Random();
                for (int i = 0; i < numQ; i++) {
                    //random number from 0 to number of allWords to obtain random words for the test
                    Word randomWord = database.allWords.get(random.nextInt(database.allWords.size()));
                    wordsForTest.add(randomWord);
                }
                //run test using arraylist found above
                runTest(wordsForTest);
                //clear textfield for future use
                textField_numOfQ.clear();

            } catch (NumberFormatException e) { //if the user didn't say how many questions
                //error message
                text_errorRandom.setVisible(true);
                text_errorRandom.setText("Please enter a number");
            }
        }

        //FLASHCARDS-----------------------------------------------------------------

        //flashcards --> frequently wrong (menuItem)
        if (source == menuItem_fcFW) {
            setVboxVisible(vBox_FWFC);
            text_errorFWFC.setVisible(false);
            text_errorFWFC.setText("");
        }
        //upon clicking "start" button in fw fc
        if (source == button_FWStartFC) {
            //making sure fw arraylist up to date
            checkingFW();
            if (FW == null || FW.isEmpty()) { //if no fw words
                //error message
                text_errorFWFC.setVisible(true);
                text_errorFWFC.setText("You have no frequently wrong words, well done!!!");
            } else {
                //run fc with fw arraylist
                runFC(FW);
            }
        }

        //flashcards --> category (menuItem)
        if (source == menuItem_fcCategory) {
            setVboxVisible(vBox_selectCategoryFC);
            //hide/clear error msg
            text_categoryFC.setVisible(false);
            text_categoryFC.setText("");
        }
        //upon clicking "start" button in category fc
        if (source == button_categoryStartFC) {
            //get category for fc
            String category = (String) dropDown_selectCategoryFC.getValue();
            ArrayList<Word> words = new ArrayList<Word>();

            //if no category selected
            if (category == null){
                //show/write error message
                text_categoryFC.setVisible(true);
                text_categoryFC.setText("Please select a category.");
            }else if (numWordInCategory(category) == 0){ //if the category selected has words
                //show/write error message
                text_categoryFC.setVisible(true);
                text_categoryFC.setText("The category you selected contains no words");
            }else{ //find all words from the category
                for (int i = 0; i < allWords.size(); i++) {
                    if (allWords.get(i).getCategory().equals(category)) {
                        words.add(allWords.get(i));
                    }
                }
                //run fc using words from category
                runFC(words);
            }

            //clear combobox for future use
            dropDown_selectCategoryFC.setValue(null);
        }

        //flashcards --> random (menuItem)
        if (source == menuItem_fcRandom) {
            setVboxVisible(vBox_numSelectRandomFC);
            //hide/clear error message
            text_errorRandomFC.setVisible(false);
            text_errorRandomFC.setText("");
        }
        //upon clicking "start" button in random fc
        if (source == button_startRandomFC) {
            try { //using try catch for input validation

                //get num of questions
                String x = textField_numFCforRandom.getText();
                int numFC = Integer.parseInt(x);

                //add words to run fc using
                ArrayList<Word> words = new ArrayList<Word>();
                Random random = new Random();
                for (int i = 0; i < numFC; i++) {
                    //random num from 0 to number of total words and add random word
                    Word randomWord = database.allWords.get(random.nextInt(database.allWords.size()));
                    words.add(randomWord);
                }

                //rum fc using random arraylist created above
                runFC(words);
                //clear textfield for future use
                textField_numFCforRandom.clear();

            } catch (NumberFormatException e) { //if user didn't input an integer
                text_errorRandomFC.setVisible(true);
                text_errorRandomFC.setText("Please input a number.");
            }
        }

        //STATISTICS-----------------------------------------------------------------

        if (source == menuItem_allStatistics) {
            setVboxVisible(vBox_statistics);

            LocalDate today = LocalDate.now();

            //total number of words
            text_totalNumWords.setText(String.valueOf(allWords.size()));

            //total number of categories
            text_totalNumCategories.setText(String.valueOf(allCategories.size()));

            //total number of tests
            text_lifeTimeNumT.setText(String.valueOf(allTests.size()));

            //number of tests this month
            int numTthisM = 0;
            int todayMonth = today.getDayOfMonth();
            int todayYear = today.getYear();
            //goes thorugh all test and incriments the counter if from the same month and year
            for (int i = 0; i < allTests.size(); i++) {
                int currentMonth = allTests.get(i).getDate().getDayOfMonth();
                int currentYear = allTests.get(i).getDate().getYear();
                if ( currentMonth == todayMonth && currentYear == todayYear) {
                    numTthisM++;
                }
            }
            text_numTthisMonth.setText(String.valueOf(numTthisM));

            //average grade per test
            double totalGrade = 0.0;
            //adds up all grades
            for (int i = 0; i < allTests.size(); i++) {
                totalGrade = totalGrade + allTests.get(i).getGrade();
            }
            //divides by number of tests for the average
            double averageGrade = totalGrade / allTests.size();
            text_averageGradePerT.setText(String.valueOf(Math.round(averageGrade) + "%"));

            //top 10 frequently wrong
            sortFW();
            checkingFW();
            String top10fw = "Top 10 frequently wrong: ";
            if(FW != null){
                if (FW.size() < 10){ //if less than 10 fw
                    for (int i = 0; i < FW.size(); i++) {
                        top10fw = top10fw + "\n\n" + String.valueOf(i + 1) + ". " + FW.get(i).getEnglish();
                    }
                }else{ //if more than 10 fw
                    for (int i = 0; i < 9; i++) {
                        top10fw = top10fw + "\n\n" + String.valueOf(i + 1) + ". " + FW.get(i).getEnglish();
                    }
                }
                text_top10FW.setText(top10fw);
            }else{//error msg if no fw
                text_top10FW.setText("There are no frequently wrong words, well done!");
            }

            //monthly activity graph
            updateBarChart();
        }

        //setting up all words table
        TableColumn<String[], String> TableColumn_allWcategory = new TableColumn<>("Category");
        TableColumn<String[], String> TableColumn_allWspanish = new TableColumn<>("Spanish");
        TableColumn<String[], String> TableColumn_allWenglish = new TableColumn<>("English");
        //page displaying all words
        if (source == menuItem_allWords) {
            setVboxVisible(vBox_allWords);

            sortWords();

            List<String[]> a = new ArrayList<>(); //store relevant things for table
            tableView_allWords.getColumns().clear(); //clear columns

            //store every word in a string array
            for (int i = 0; i < allWords.size(); i++) {
                a.add(new String[]{
                        allWords.get(i).getCategory(),
                        allWords.get(i).getSpanish(),
                        allWords.get(i).getEnglish()
                });
            }

            //create observable list to bind to table
            ObservableList<String[]> r = FXCollections.observableArrayList(a);
            tableView_allWords.setItems(r); //set items for the TableView

            //add columns to TableView
            tableView_allWords.getColumns().addAll(TableColumn_allWcategory, TableColumn_allWspanish, TableColumn_allWenglish);

            //adding relevant data from observable list to table
            TableColumn_allWcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
            TableColumn_allWspanish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
            TableColumn_allWenglish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));

            //set column width
            TableColumn_allWcategory.setPrefWidth(240);
            TableColumn_allWspanish.setPrefWidth(240);
            TableColumn_allWenglish.setPrefWidth(240);
        }

        //setting up all categories table
        TableColumn<String[], String> TableColumn_allCcategory = new TableColumn<>("Category");
        TableColumn<String[], String> TableColumn_allnumOwords = new TableColumn<>("Number of Words");
        //page displaying all categories
        if (source == menuItem_allCategories) {
            setVboxVisible(vBox_allCategory);
            sortCategories();

            List<String[]> a = new ArrayList<>(); //stores everything for table
            tableView_allCategories.getColumns().clear(); //clear existing columns

            //adding all categories to the list
            for (int i = 0; i < allCategories.size(); i++) {
                a.add(new String[]{
                        allCategories.get(i),
                        String.valueOf(categoryCount.get(i))
                });
            }

            //bind observable list to table
            ObservableList<String[]> r = FXCollections.observableArrayList(a);
            tableView_allCategories.setItems(r); //setting items for the table

            //adding columns
            tableView_allCategories.getColumns().addAll(TableColumn_allCcategory, TableColumn_allnumOwords);

            //setting up  cell value factory for columns
            TableColumn_allCcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
            TableColumn_allnumOwords.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));

            //set width
            TableColumn_allCcategory.setPrefWidth(480);
            TableColumn_allnumOwords.setPrefWidth(240);
        }
//setting up frequently wrong table
        TableColumn<String[], String> TableColumn_allFWcount = new TableColumn<>("N");
        TableColumn<String[], String> TableColumn_allFWcategory = new TableColumn<>("Category");
        TableColumn<String[], String> TableColumn_allFWspanish = new TableColumn<>("Spanish");
        TableColumn<String[], String> TableColumn_allFWenglish = new TableColumn<>("English");
        //page displaying all frequently wrong
        if (source == menuItem_allFW) {
            setVboxVisible(vBox_allFW);

            sortFW();
            checkingFW();

            List<String[]> a = new ArrayList<>(); //store everything for the table
            tableView_allFW.getColumns().clear(); //clearing existing columns

            if(!FW.isEmpty()){
                //adding frequently wrong words
                for (int i = 0; i < FW.size(); i++) {
                    a.add(new String[]{
                            String.valueOf(i + 1),
                            FW.get(i).getCategory(),
                            FW.get(i).getSpanish(),
                            FW.get(i).getEnglish()
                    });
                }
            }else{
                a.add(new String[]{
                        "/",
                        "/",
                        "/",
                        "/"
                });
            }

            //bind observable list to table
            ObservableList<String[]> r = FXCollections.observableArrayList(a);
            tableView_allFW.setItems(r); //setting items for the TableView

            //adding the columns to table
            tableView_allFW.getColumns().addAll(TableColumn_allFWcount, TableColumn_allFWcategory, TableColumn_allFWspanish, TableColumn_allFWenglish);

            //setting up cell value factories for columns
            TableColumn_allFWcount.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
            TableColumn_allFWcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
            TableColumn_allFWspanish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
            TableColumn_allFWenglish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));

            //setting width
            TableColumn_allFWcount.setPrefWidth(25);
            TableColumn_allFWcategory.setPrefWidth(225);
            TableColumn_allFWspanish.setPrefWidth(225);
            TableColumn_allFWenglish.setPrefWidth(225);
        }
    }

    //OTHER METHODS-------------------------------------------------------------------------

    //update all comboBoxes (with any new categories added)
    @FXML
    private void refreshDropDown(){ //comboBoxes are referred to as dropDowns
        //new word
        dropDown_chooseNewCategory.getItems().clear();
        loadDropdown(database.categories, dropDown_chooseNewCategory);

        //category tests
        dropDown_Tcategory1.getItems().clear();
        loadDropdown(database.categories, dropDown_Tcategory1);
        dropDown_Tcategory2.getItems().clear();
        loadDropdown(database.categories, dropDown_Tcategory2);
        dropDown_Tcategory3.getItems().clear();
        loadDropdown(database.categories, dropDown_Tcategory3);

        //category fc (flashcards)
        dropDown_selectCategoryFC.getItems().clear();
        loadDropdown(database.categories, dropDown_selectCategoryFC);
    }

    //used to fill comboBoxes
    @Deprecated
    private void loadDropdown(ArrayList a, ComboBox b) {
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                b.getItems().add(a.get(i));
            }
        } else if (a == null && b.getItems().isEmpty()) {
            b.getItems().add("none");
        }
    }

    //method that enables the vBoxes to be set visible while setting all other vBoxes invisible
    public void setVboxVisible(VBox setVis) {
        vBox_StartingPage.setVisible(false);
        vBox_addNewWord.setVisible(false);
        vBox_addNewCategory.setVisible(false);
        vBox_chooseCategoryForT.setVisible(false);
        vBox_randomT.setVisible(false);
        vBox_FWT.setVisible(false);
        vBox_categoryT.setVisible(false);
        vBox_duringTest.setVisible(false);
        vBox_endOfTest.setVisible(false);
        vBox_selectCategoryFC.setVisible(false);
        vBox_numSelectRandomFC.setVisible(false);
        vBox_duringFC.setVisible(false);
        vBox_statistics.setVisible(false);
        vBox_allWords.setVisible(false);
        vBox_allCategory.setVisible(false);
        vBox_allFW.setVisible(false);
        vBox_FWFC.setVisible(false);

        setVis.setVisible(true);
    }

    //setting up test results table
    TableColumn<String[], String> TableColumn_endTSpanish = new TableColumn<>("Word in Spanish");
    TableColumn<String[], String> TableColumn_endTEnglish = new TableColumn<>("Word in English");
    TableColumn<String[], String> TableColumn_endTAnswer = new TableColumn<>("What you wrote");

    //method for running the tests
    private void runTest(ArrayList<Word> wordsForTest) {

        setVboxVisible(vBox_duringTest);

        final int[] x = {0}; //track the current word
        final int[] score = {0}; //track the score

        progressBar_testProgress.setProgress(0);
        double[] progress = {0}; //track the progress
        double b = (double) 1 / wordsForTest.size(); //calculates how much progress per question

        List<String[]> a = new ArrayList<>(); //stores all the relevant things for table at end
        tableView_endOfTest.getColumns().clear();//clear table so it doesnt get stacked in new test

        //show first word
        text_wordInSpanishDuringTest.setText(wordsForTest.get(x[0]).getSpanish());

        //event handler everytime finish a question
        button_TconfirmAndNext.setOnAction(event -> {
            String answer = textField_answerInEnglishTest.getText().trim();
            Word currentWord = wordsForTest.get(x[0]);

            a.add(new String[]{ //stores every word in a string list (kinda like a 2D array storing strings)
                    currentWord.getSpanish(),
                    currentWord.getEnglish(),
                    answer
            });

            //checks if question is answered right
            if (answer.equalsIgnoreCase(currentWord.getEnglish())) {
                currentWord.setTimesRight(currentWord.getTimesRight() + 1);
                score[0]++;
            } else {
                currentWord.setTimesWrong(currentWord.getTimesWrong() + 1);
            }

            progress[0] = progress[0] + b;
            progressBar_testProgress.setProgress(progress[0]); //update progress bar
            textField_answerInEnglishTest.clear(); //clear the text field for the next answer

            //move to next word or end test depending on if the whole array is looped through
            x[0]++;
            if (x[0] < wordsForTest.size()) {
                text_wordInSpanishDuringTest.setText(wordsForTest.get(x[0]).getSpanish());
            } else {
                setVboxVisible(vBox_endOfTest);
                text_scoreEndOfT.setText(score[0] + "/" + wordsForTest.size()); //score

                //instantiate new test (saves this one)
                double percentage = (double) (score[0] / wordsForTest.size())*100;
                allTests.add(new Test(LocalDate.now(), percentage));

                //preparing the list for table by changing normal arraylist into observable list
                ObservableList<String[]> r = FXCollections.observableArrayList(a);
                tableView_endOfTest.setItems(r);

                // set columns
                tableView_endOfTest.getColumns().addAll(TableColumn_endTSpanish, TableColumn_endTEnglish, TableColumn_endTAnswer);

                //adding relevant data to columns from observable list
                TableColumn_endTSpanish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
                TableColumn_endTEnglish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
                TableColumn_endTAnswer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));

                database.uploadTestFile();
                database.uploadWordFile();
            }
        });
        button_tryTestAgain.setOnAction(event -> {
            runTest(wordsForTest); //restart same test
        });
    }

    //method for running the flashcards
    private void runFC(ArrayList<Word> wordsForFC) {
        setVboxVisible(vBox_duringFC);

        final int[] x = {0}; //track current FC

        text_displayWordFC.setText(wordsForFC.get(x[0]).getSpanish());

        //flip button
        button_flipFC.setOnAction(event -> {
            if (text_displayWordFC.getText().equals(wordsForFC.get(x[0]).getSpanish())) {
                text_displayWordFC.setText(wordsForFC.get(x[0]).getEnglish());
            } else { //if already displaying spanish
                text_displayWordFC.setText(wordsForFC.get(x[0]).getSpanish());
            }
        });

        //next fc button
        button_nextFC.setOnAction(event -> {
            x[0]++; //move to next word
            if (x[0] < wordsForFC.size()) {
                text_displayWordFC.setText(wordsForFC.get(x[0]).getSpanish());
                button_previousFC.setVisible(true); //make sure previous button visible in case first fc
            } else {
                //end msg display after no more words
                text_displayWordFC.setText("End.");
                button_previousFC.setVisible(true);
                button_flipFC.setVisible(false);
                button_nextFC.setVisible(false);
            }
        });

        //previous fc button
        button_previousFC.setOnAction(event -> {
            if (x[0] > 0) {
                x[0]--; //move to previous word
                text_displayWordFC.setText(wordsForFC.get(x[0]).getSpanish());
                button_flipFC.setVisible(true); //make sure flip button visible in case last fc
                button_nextFC.setVisible(true); //make sure next button visible in case last fc
            }
            if (x[0] == 0) {
                button_previousFC.setVisible(false); //hide previous button if first word
            }
        });

        //reset the initial state of buttons
        button_previousFC.setVisible(false);
        button_flipFC.setVisible(true);
        button_nextFC.setVisible(true);
    }

    //sorts all words array
    private void sortWords() {
        for (int i = 0; i < allWords.size() - 1; i++) {
            for (int j = 0; j < allWords.size() - i - 1; j++) {
                char current = allWords.get(j).getEnglish().charAt(0);
                char next = allWords.get(j + 1).getEnglish().charAt(0);
                //swaps two words
                if (current > next) {
                    Word temp = allWords.get(j);
                    allWords.set(j, allWords.get(j + 1));
                    allWords.set(j + 1, temp);
                }
            }
        }
    }

    //sorts all categories
    private void sortCategories() {
        //sort all categories
        for (int i = 0; i < allCategories.size() - 1; i++) {
            for (int j = 0; j < allCategories.size() - 1 - i; j++) {
                char current = allCategories.get(j).charAt(0);
                char next = allCategories.get(j + 1).charAt(0);

                // Swap categories if they are out of order
                if (current > next) {
                    String tempCategory = allCategories.get(j);
                    allCategories.set(j, allCategories.get(j + 1));
                    allCategories.set(j + 1, tempCategory);
                }
            }
        }
        //count words per category
        if (categoryCount.isEmpty() == false) {
            categoryCount.clear();
        }

        for (int i = 0; i < allCategories.size(); i++) {
            int count = 0;
            //loop thorugh all words and check category and if it equals the current one
            for (int j = 0; j < allWords.size(); j++) {
                if (allWords.get(j).getCategory().equals(allCategories.get(i))) {
                    count++;
                }
            }
            //add to category count
            categoryCount.add(count);
        }
    }


    //sorts all frequently wrong
    private void sortFW() {
        //sort FW using bubble sort
        for (int i = 0; i < FW.size() - 1; i++) {
            for (int j = 0; j < FW.size() - 1 - i; j++) {
                double current = FW.get(j).getRtW();
                double next = FW.get(j + 1).getRtW();

                //swap if current > next
                if (current > next) {
                    Word temp = FW.get(j);
                    FW.set(j, FW.get(j + 1));
                    FW.set(j + 1, temp);
                }
            }
        }
        //populate categoryCount with 0s
        categoryCount.clear(); //make sure its empty
        for (int i = 0; i < allCategories.size(); i++) {
            categoryCount.add(0);
        }

        //count number of words per category
        for (int i = 0; i < allWords.size(); i++) {
            String wordCategory = allWords.get(i).getCategory();
            for (int j = 0; j < allCategories.size(); j++) {
                if (wordCategory.equals(allCategories.get(j))) {
                    categoryCount.set(j, categoryCount.get(j) + 1);
                }
            }
        }
    }

    //count the numebr of words in a chosen category
    private int numWordInCategory(String x){
        int total = 0;
        for (int i = 0; i < allWords.size(); i++) {
            if (allWords.get(i).getCategory().equals(x)){
                total++;
            }
        }
        return total;
    }

    //makes sure all FW list is to date
    private void checkingFW() {
        FW = new ArrayList<>();
        for (int i = 0; i < allWords.size(); i++) {
            if (allWords.get(i).getRtW() < 1) {
                FW.add(allWords.get(i));
            }
        }
    }

    //update the barchart in statistics
    private void updateBarChart() {
        //array to store count of tests for past 12 month
        int[] testsPerMonth = new int[12];
        YearMonth currentMonth = YearMonth.now();

        //loop through all tests and count for past 12 months
        for (int i = 0; i < allTests.size(); i++) {
            Test test = allTests.get(i);
            LocalDate testDate = test.getDate();
            YearMonth testMonth = YearMonth.from(testDate);

            //calculate  difference in months between the current and the test
            int monthsago = (int) ChronoUnit.MONTHS.between(testMonth, currentMonth);
            if (monthsago >= 0 && monthsago < 12) {
                testsPerMonth[11 - monthsago]++; //add count for the corresponding month
            }
        }

        //create series for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        //add data of each month to the series
        for (int i = 0; i < 12; i++) {
            YearMonth month = currentMonth.minusMonths(11 - i); // Get the month
            String monthLabel = month.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + month.getYear();

            //add a bar with number of tests for the month
            series.getData().add(new XYChart.Data<>(monthLabel, testsPerMonth[i]));
        }

        //add new series
        barChart_monthlyActivity.getData().clear();
        barChart_monthlyActivity.getData().add(series);

        //remove the legend (red box thingy)
        barChart_monthlyActivity.setLegendVisible(false);

        //x axis (called categoryaxis in javafx)
        CategoryAxis xAxis = (CategoryAxis) barChart_monthlyActivity.getXAxis();
        xAxis.setLabel("Month and Year");
        xAxis.setTickLabelRotation(0); //make sure labels are horizontal
        xAxis.setCategories(FXCollections.observableArrayList(
                series.getData().stream().map(XYChart.Data::getXValue).toList()
        )); //make the month directly under each bar

        //y axis (called numberaxis in javafx)
        NumberAxis yAxis = (NumberAxis) barChart_monthlyActivity.getYAxis();
        yAxis.setLabel("Number of Tests");
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(1); //so each label increments by 1
        yAxis.setLowerBound(0);
        int x = Arrays.stream(testsPerMonth).max().orElse(-1);
        yAxis.setUpperBound(x + 1);

    }
    //calculating the consecutive days using the test function
    private int calcDailyStreaks() {
        int streaks = 0;
        LocalDate today = LocalDate.now();
        LocalDate expectedDate = today;

        if (!allTests.isEmpty()) {
            for (int i = allTests.size()-1; i > 0; i--) {
                LocalDate testDate = allTests.get(i).getDate();

                if (testDate.equals(expectedDate)) {
                    streaks++;
                    //if its not a different day dont increase and move on to next day
                    if (i == allTests.size() - 1 || !allTests.get(i + 1).getDate().equals(expectedDate)) {
                        expectedDate = expectedDate.minusDays(1);
                    }
                } else if (testDate.isBefore(expectedDate)) {
                    break;
                }

            }
        }

        return streaks;
    }
}