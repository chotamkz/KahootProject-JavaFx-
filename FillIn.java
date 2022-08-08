package com.example.myproject;

public class FillIn extends Question {
    @Override
    public String toString(){
        String fillLine = getDescription().replace("{blank}","_______");;
        return fillLine;
    }

    @Override
    public String getOptionAt(int a) {
        return null;
    }
}
