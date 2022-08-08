package com.example.myproject;

import java.io.*;
import java.net.Socket;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Client extends Application {

    private Stage programmer;
    private TranslateTransition tt;
    private final double W = 600, H = 600;
    private String nickname;

    private DataOutputStream toServer;
    private DataInputStream fromServer;

    public void shake(Node node){
        tt = new TranslateTransition(Duration.millis(50),node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(5);
        tt.setAutoReverse(true);
    }

    public Scene gamePin(int ind) {
        StackPane stackPane = new StackPane();
        TextField textField = new TextField();

        Button redBtn = new Button();
        redBtn.setMinWidth(300 - 5);
        redBtn.setMinHeight(250 - 5);
        redBtn.setStyle("-fx-background-color: red" );
        redBtn.setTextFill(Color.WHITE);
        redBtn.setWrapText(true);
        redBtn.setPadding(new Insets(10));
        redBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        Button redOrange = new Button();
        redOrange.setMinWidth(300 - 5);
        redOrange.setMinHeight(250 - 5);
        redOrange.setStyle("-fx-background-color: Orange" );
        redOrange.setTextFill(Color.WHITE);
        redOrange.setWrapText(true);
        redOrange.setPadding(new Insets(10));
        redOrange.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        Button redBlue = new Button();
        redBlue.setMinWidth(300 - 5);
        redBlue.setMinHeight(250 - 5);
        redBlue.setStyle("-fx-background-color: blue" );
        redBlue.setTextFill(Color.WHITE);
        redBlue.setWrapText(true);
        redBlue.setPadding(new Insets(10));
        redBlue.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        Button redGreen = new Button();
        redGreen.setMinWidth(300 - 5);
        redGreen.setMinHeight(250 - 5);
        redGreen.setStyle("-fx-background-color: green" );
        redGreen.setTextFill(Color.WHITE);
        redGreen.setWrapText(true);
        redGreen.setPadding(new Insets(10));
        redGreen.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        HBox hBox1 = new HBox(5);
        HBox hBox2 = new HBox(5);
        hBox2.getChildren().addAll(redOrange, redGreen);

        Button isSel = new Button("Send");
        isSel.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
        isSel.setStyle("-fx-background-color: #1e1c1c");
        isSel.setTextFill(Color.WHITE);
        DropShadow shadow = new DropShadow();
        isSel.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                isSel.setEffect(shadow);
            }
        });
        isSel.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                isSel.setEffect(null);
            }
        });
        isSel.setMinWidth(340);
        isSel.setMinHeight(40);
        textField.setMinHeight(40);
        textField.setMinWidth(400);
        textField.setMaxWidth(400);
        hBox1.getChildren().addAll(redBtn, redBlue);

        VBox vBox = new VBox(5);
        HBox qwerty = new HBox(5);
        qwerty.getChildren().addAll(textField,isSel);
        vBox.getChildren().addAll(hBox1, hBox2);
        VBox ggg = new VBox(20);
        ggg.getChildren().addAll(qwerty,vBox);
        stackPane.getChildren().addAll(ggg);

        textField.setMinHeight(40);
        textField.setMinWidth(250);
        textField.setMaxWidth(250);


        isSel.setOnAction(e->{
            try {
                toServer.writeUTF(textField.getText());
                toServer.writeInt(0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }programmer.setScene(gamePin(ind + 1));
        });

        redBtn.setOnAction(e -> {
                try {
                    toServer.writeUTF(ind + "--> "+nickname);
                    toServer.writeInt(1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                programmer.setScene(gamePin(ind + 1));
            });
            redOrange.setOnAction(e->{
                try {
                    toServer.writeUTF(ind  + "--> "+nickname);
                    toServer.writeInt(2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }programmer.setScene(gamePin(ind + 1));
            });

            redBlue.setOnAction(e -> {
                try {
                    toServer.writeUTF(ind + "--> "+nickname);
                    toServer.writeInt(3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }programmer.setScene(gamePin(ind + 1));
            });

            redGreen.setOnAction(e -> {
                try {
                    toServer.writeUTF(ind + "--> "+nickname);
                    toServer.writeInt(4);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }programmer.setScene(gamePin(ind + 1));
            });
        return new Scene(stackPane,W,580);
    }


    public Scene nicknamePane() {
        StackPane stackPane = new StackPane();
        TextField tf = new TextField();
        tf.setPromptText("Enter username");
        tf.setMaxWidth(W / 3);
        tf.setMinHeight(40);
        tf.setAlignment(Pos.CENTER);
        Button btn = new Button("Enter");
        btn.setMaxWidth(W / 3);
        btn.setMinHeight(40);
        btn.setStyle("-fx-background-color:#333333");
        btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        VBox vBox = new VBox(10);
        vBox.setMaxWidth(W / 2);
        vBox.setMaxHeight(H / 2);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tf, btn);

        stackPane.getChildren().addAll(vBox);
        stackPane.setStyle("-fx-background-color: #46178f");

        btn.setOnAction(e -> {
            try {
                nickname = tf.getText();
                toServer.writeUTF(nickname);
                programmer.setScene(waiting());
                programmer.setTitle(nickname);
                if(fromServer.readUTF().equals("go")){
                    programmer.setScene(gamePin(0));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return new Scene(stackPane,W,H);
    }


    public Scene pinPane() {
        StackPane stackPane = new StackPane();
        TextField tf = new TextField();
        tf.setPromptText("Game PIN");
        tf.setMaxWidth(W / 3);
        tf.setMinHeight(40);
        tf.setAlignment(Pos.CENTER);
        Button btn = new Button("Enter");
        btn.setMaxWidth(W / 3);
        btn.setMinHeight(40);
        btn.setStyle("-fx-background-color:#333333");
        btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        VBox vBox = new VBox(10);
        vBox.setMaxWidth(W / 2);
        vBox.setMaxHeight(H / 2);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tf, btn);

        stackPane.getChildren().addAll(vBox);
        stackPane.setStyle("-fx-background-color: #3e147f");

        btn.setOnAction(e -> {
            try {
                toServer.writeInt(Integer.parseInt(tf.getText()));
                String status = fromServer.readUTF();
                if (status.equals("Success!")) {
                    programmer.setScene(nicknamePane());
                    programmer.setTitle("Enter Nickname");
                }
                else{
                    shake(tf);
                    tt.playFromStart();
                }
                System.out.println(status);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return new Scene(stackPane,W,H);
    }

    public Scene waiting() {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #3e147f");

        return new Scene(stackPane,W,H);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Socket socket = new Socket("localhost", 2003);
        toServer = new DataOutputStream(socket.getOutputStream());
        fromServer = new DataInputStream(socket.getInputStream());
        programmer = primaryStage;
        programmer.setScene(pinPane());
        programmer.show();
        programmer.setTitle("Enter PIN!");
    }
}
