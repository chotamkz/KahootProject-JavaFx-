package com.example.myproject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test extends Question {
    private String[] options;
    private final int numOfOptions;
    private final ArrayList<Character> lables = new ArrayList<>();

    public Test(){
        lables.add('A');
        lables.add('B');
        lables.add('C');
        lables.add('D');
        numOfOptions = lables.size();
    }
    public int getNumOfOptions(){
        return numOfOptions;
    }

    public void setOptions(String[] options){
        this.options = options;
        List<String> list = Arrays.asList(this.options);
        Collections.shuffle(list);
    }
    @Override
    public String toString(){
        return getDescription() + ". " + "\n" + printOptions();
    }

    public String printOptions() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < numOfOptions; i++) {
            stringBuffer.append(lables.get(i)).append(") ").append(options[i]).append("\n");
        }
        return stringBuffer.toString();
    }
    public String getOptionAt(int n){
        return options[n];
    }
}
