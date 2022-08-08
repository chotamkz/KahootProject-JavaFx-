package com.example.myproject;

import javafx.application.Application;
import javafx.stage.Stage;


public class QuizMaker extends Application {
   public void start(Stage primaryStage) throws Exception{
       QuizFX quizFX = new QuizFX();
       quizFX.programmer = primaryStage;
       quizFX.programmer.setScene(quizFX.sceneChooseFile());
       quizFX.programmer.setTitle("Server");
       quizFX.programmer.show();
   }
}
