package com.example.myproject;

import java.util.NoSuchElementException;

public class InvalidQuizFormatException extends NoSuchElementException{
    public InvalidQuizFormatException(String word){
        super(word);
    }
}
