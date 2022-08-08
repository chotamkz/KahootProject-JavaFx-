package com.example.myproject;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private  String name;
    private static final ArrayList<Question> questions = new ArrayList<>();
    public ArrayList<Question> getQues(){
        return  questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public  void setNameFile(String name) {
        this.name = name;
    }

    public String getNameFile() {
        return name;
    }

    public void loadFromFile(String filename) throws FileNotFoundException, InvalidQuizFormatException {
        File fileReader = new File(filename);
        Scanner in = new Scanner(fileReader);

        while (in.hasNextLine()) {
            Test test = new Test();
            FillIn fillIn = new FillIn();
            String description = in.nextLine();
                if (description.equals("")) {
                    continue;
                } else if (fileReader.length() == 0) {
                    throw new InvalidQuizFormatException("This quiz file is empty");
                } else if (description.contains("{blank}")) {
                    in.hasNextLine();
                    fillIn.setDescription(description);
                    fillIn.setAnswer(in.nextLine());
                    addQuestion(fillIn);
                } else {
                    in.hasNextLine();
                    test.setDescription(description);
                    String[] options = new String[test.getNumOfOptions()];
                    for (int i = 0; i < test.getNumOfOptions(); i++) {
                        options[i] = in.nextLine();
                    }
                    test.setAnswer(options[0]);
                    addQuestion(test);
                    test.setOptions(options);
                }
                in.nextLine();
            }
        }
    public String getAnswerTestFormat(int a, int b){
        return questions.get(a).getOptionAt(b);
        }

    public Label getLabel(int s){
        Label lbl = new Label((s+1)+")"+getQues().get(s).getDescription().replace("{blank}","_______"));
        lbl.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,16));
        lbl.setWrapText(true);
        return lbl;
    }
}

