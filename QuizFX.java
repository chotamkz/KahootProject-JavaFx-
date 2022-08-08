package com.example.myproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QuizFX {
    Quiz quizClass = new Quiz();
    private DataOutputStream toClient;
    private DataInputStream fromClient;
    private Socket socket;
    int pl = 0;
    HBox names;
    Random randNum = new Random();
    int genPin = randNum.nextInt(100000);
    public Stage programmer;
    String [] suggestedAnswers;
    private final Media mediaKahoot = new Media(new File("C:\\Users\\User\\IdeaProjects\\App\\src\\main\\java\\project2.mp3").toURI().toString());
    private final MediaPlayer mediaPlayKahootMusic = new MediaPlayer(mediaKahoot);
    private final double W = 820, H = 530;
    LocalTime time = LocalTime.now();
    Test testFormat = new Test();
    int ind = 0;int c = 0;
    String ronaldo;int[] count = {0,0,0,0};
    String[] name = new String[10];
    private String nickname = "";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Scene Server(){
        names = new HBox(10);
        StackPane root = new StackPane();
        names.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #3e147f");

        final BorderPane[] borderPane = {new BorderPane()};

        Label lbl = new Label("                       Join at WWW.KAHOOT.IT with: "
                +"\n                                    Game PIN: " );
        lbl.setTextFill(Color.WHITE);
        Label qwe = new Label("                  "+genPin);
        qwe.setTextFill(Color.BISQUE);
        qwe.setFont(Font.font("Broadway", FontWeight.BOLD, FontPosture.ITALIC, 40));
        lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 35));

        lbl.setAlignment(Pos.CENTER);
        lbl.setMinWidth(600);
        qwe.setAlignment(Pos.CENTER);
        qwe.setMinWidth(600);
        VBox w = new VBox(5);
        w.getChildren().addAll(lbl,qwe);
        borderPane[0].setTop(w);

        Button button = new Button();
        DropShadow shadow = new DropShadow();
        button.setText("Start");
        button.setStyle("-fx-background-color: rgb(38,37,37)");
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button.setEffect(shadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button.setEffect(null));
        button.setOnAction(e->{
            getMediaPlayKahootMusic().setAutoPlay(true);
            getMediaPlayKahootMusic().play();
            programmer.setScene(sceneKahootFormatQuiz(0));
        });
        final int[] clientNo = {1};
        Text text = new Text("Players\n");
        text.setFont(Font.font("Arial Black", FontPosture.ITALIC, 20));
        borderPane[0].setCenter(names);
        borderPane[0].setRight(button);
        borderPane[0].setLeft(text);
        root.getChildren().addAll(borderPane[0]);
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(2003);
                for (;;) {
                    try {
                        System.out.println("Waiting for incomes");
                        socket = server.accept();
                        System.out.println(clientNo[0] + " Client is Connected!");
                        new Thread(() -> {
                            try {
                                fromClient = new DataInputStream(socket.getInputStream());
                                toClient = new DataOutputStream(socket.getOutputStream());
                                while (true) {
                                    int clientPin = fromClient.readInt();
                                    System.out.println(clientPin);
                                    if (clientPin == genPin) {
                                        toClient.writeUTF("Success!");
                                    } else {
                                        toClient.writeUTF("Wrong PIN");
                                    }
                                    setNickname(fromClient.readUTF());
                                    System.out.println(getNickname());
                                    Button nick = new Button();
                                    name[pl] = getNickname();
                                    pl++;
                                    System.out.println(pl);
                                    nick.setText(getNickname());
                                    nick.setTextFill(Color.BLACK);
                                    nick.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));

                                    Platform.runLater(() -> names.getChildren().add(nick)
                                    );
                                    for(;;){
                                        toClient.writeUTF("go");
                                        String clientChoice = fromClient.readUTF();
                                        System.out.println(clientChoice);
                                        int clientChoiceNum = fromClient.readInt();
                                        if(clientChoiceNum != 0){
                                            switch (clientChoiceNum) {
                                                case 1 -> {
                                                    System.out.println("choice is 1");
                                                    System.out.println("Correct answer is:-->" + quizClass.getQues().get(ind).getAnswer());
                                                    System.out.println(getAns().get(0));
                                                    suggestedAnswers[getInd()] = getAns().get(0);
                                                }
                                                case 2 -> {
                                                    System.out.println("choice is 2");
                                                    System.out.println("Correct answer is:-->" + quizClass.getQues().get(ind).getAnswer());
                                                    System.out.println(getAns().get(1));
                                                    suggestedAnswers[getInd()] = getAns().get(1);
                                                }
                                                case 3 -> {
                                                    System.out.println("choice is 3");
                                                    System.out.println("Correct answer is:-->" + quizClass.getQues().get(ind).getAnswer());
                                                    System.out.println(getAns().get(2));
                                                    suggestedAnswers[getInd()] = getAns().get(2);
                                                }
                                                case 4 -> {
                                                    System.out.println("choice is 4");
                                                    System.out.println("Correct answer is:-->" + quizClass.getQues().get(ind).getAnswer());
                                                    System.out.println(getAns().get(3));
                                                    suggestedAnswers[getInd()] = getAns().get(3);
                                                }
                                            }
                                        }else {
                                            suggestedAnswers[getInd()] = clientChoice;

                                        }
                                    }
                                }

                            } catch (IOException ignored) {}

                        }).start();
                        clientNo[0]++;

                    } catch (IOException ignored) {}
                }
            } catch (IOException ignored) {}
        }).start();
        return new Scene(root,getW(),getH());
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getNickname(){
        return nickname;
    }
    public void setNickname(String a){
        nickname = a;
    }
    public int getInd(){
        return ind;
    }

    public MediaPlayer getMediaPlayKahootMusic(){
        return mediaPlayKahootMusic;
    }
    public double getW(){
        return W;
    }
    public double getH(){
        return H;
    }

    public String getRonaldo(){
        return String.valueOf(quizClass.getLabel(getInd()));
    }


    public Scene showReview(int ind){
        String [] prov = new String[quizClass.getQues().size()];
        BorderPane borderPane = new BorderPane();
        Button buttonN = new Button("next");
        buttonN.setTextFill(Color.WHITE);
        Button buttonP = new Button("back");
        buttonP.setTextFill(Color.WHITE);
        buttonN.setMinHeight(230);
        buttonN.setMinWidth(30);
        buttonP.setMinHeight(230);
        buttonP.setMinWidth(30);
        buttonP.setStyle("-fx-background-color: #484747");
        buttonN.setStyle("-fx-background-color: #484747");
        borderPane.setRight(new StackPane(buttonN));
        borderPane.setLeft(new StackPane(buttonP));

        for(int i = quizClass.getQues().size();i>0 ; i--) {
            VBox vBox = new VBox(10);
            Text review = new Text("Review");

            InnerShadow is = new InnerShadow();
            is.setOffsetX(4.0f);
            is.setOffsetY(4.0f);
            review.setEffect(is);
            review.setFill(Color.BISQUE);
            review.setFont(Font.font(null, FontWeight.BOLD, 45));

            VBox vBox1 = new VBox();
            if(Objects.equals(suggestedAnswers[ind], quizClass.getQues().get(ind).getAnswer())){
                prov[ind] = "Correct!";
            }else if(!Objects.equals(suggestedAnswers[ind], "") && suggestedAnswers[ind] != null){
                prov[ind] = "Not correct!";
            }
            else{
                prov[ind] = "Your choice is empty!";
            }
            Text text = new Text("Your answer: " + suggestedAnswers[ind] + "\n"+"Correct answer: "+ quizClass.getQues().get(ind).getAnswer() +"\n");
            text.setFont(Font.font(30));
            vBox.getChildren().addAll(review, quizClass.getLabel(ind));
            vBox.setAlignment(Pos.CENTER);
            vBox1.setAlignment(Pos.CENTER);
            borderPane.setTop(vBox);
            borderPane.setCenter(vBox1);
            Button button = new Button(prov[ind]);
            button.setMinHeight(100);
            button.setMinWidth(350);
            if(prov[ind].contains("Correct")){
                button.setStyle("-fx-background-color: green");
                vBox1.getChildren().addAll(text,button);
            }
            else if(prov[ind].contains("Your choice is empty")){
                button.setStyle("-fx-background-color: rgba(52,51,51,0.6)");
                vBox1.getChildren().addAll(text,button);
            }
            else {
                button.setStyle("-fx-background-color: red");
                vBox1.getChildren().addAll(text,button);
            }
            buttonN.setOnAction(e -> {
                if(ind == quizClass.getQues().size()-1){
                    try {
                        programmer.setScene(viewResultOptions(0));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }else programmer.setScene(showReview(ind + 1));
            });
            if (ind == 0){
                buttonP.setVisible(false);
            }
            else buttonP.setOnAction(e -> programmer.setScene(showReview(ind - 1)));

        }
        return new Scene(borderPane,W,H);
    }

    public ArrayList<String> getAns(){
        ArrayList<String>answerOptions = new ArrayList<>();
        for(int u = 0;u<testFormat.getNumOfOptions();u++){
            answerOptions.add(quizClass.getAnswerTestFormat(this.getInd(),u));
        }
        return answerOptions;
    }int cog = 0;
    public Scene sceneKahootFormatQuiz(int a){
        ind = a;
        BorderPane borderPane = new BorderPane();
        Button btnNext = new Button(">");
        btnNext.setStyle("-fx-background-color:#cbbaba");
        Button btnBack = new Button("<");
        btnBack.setStyle("-fx-background-color: #cbbaba");
        Circle circle = new Circle(30);
        btnNext.setShape(circle);
        btnBack.setShape(circle);
        borderPane.setRight(new StackPane(btnNext));
        borderPane.setLeft(new StackPane(btnBack));
        btnNext.setMinWidth(30);
        btnNext.setMinHeight(50);
        btnBack.setMinWidth(30);
        btnBack.setMinHeight(50);
        if(ind == 0){
            btnBack.setVisible(false);
        }
        for (int i = quizClass.getQues().size();i>0 ; i--) {

            VBox vBox = new VBox(10);
            Text yourChoiceInDisplay = new Text( suggestedAnswers[ind]);
            yourChoiceInDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            Button asdfa = new Button();
            asdfa.setText(String.valueOf(yourChoiceInDisplay));
            vBox.getChildren().addAll(quizClass.getLabel(ind));
            vBox.setAlignment(Pos.CENTER);
            borderPane.setTop(vBox);
            ronaldo = String.valueOf(quizClass.getLabel(ind));

            if(ronaldo.contains("_______")){
                ImageView imageView = new ImageView();
                try {
                    Image fillin = new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\App\\src\\main\\java\\fillin.png"));
                    imageView.setFitWidth(300);
                    imageView.setFitHeight(150);
                    imageView.setImage(fillin);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                HBox hBox = new HBox();
                hBox.getChildren().addAll(quizClass.getLabel(ind));
                hBox.setAlignment(Pos.CENTER);
                VBox v = new VBox(10);
                v.getChildren().addAll(hBox);
                v.setAlignment(Pos.CENTER);
                borderPane.setTop(v);
                Text text = new Text();
                text.setText("Type your answer below: ");
                TextField textField = new TextField();
                textField.setMinHeight(40);
                textField.setMinWidth(250);
                textField.setMaxWidth(250);
                VBox stackPane = new VBox(5);

                btnBack.setOnAction(e -> {
                    if(!Objects.equals(textField.getText(), "")){
                        suggestedAnswers[ind] = textField.getText();
                    }
                    programmer.setScene(sceneKahootFormatQuiz(ind-1));
                });
                btnNext.setOnAction(e -> {
                    if(!Objects.equals(textField.getText(), "")){
                        suggestedAnswers[ind] = textField.getText();
                    }
                    if ( ind == quizClass.getQues().size()-1 ) {
                        mediaPlayKahootMusic.stop();
                        try {
                            programmer.setScene(viewResultOptions(0));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        programmer.setScene(sceneKahootFormatQuiz(ind+1));
                    }
                });
                stackPane.getChildren().addAll(imageView,text,textField,yourChoiceInDisplay);
                borderPane.setCenter(stackPane);
                stackPane.setAlignment(Pos.CENTER);
            }
            else{
                ArrayList<String> answerOptions = new ArrayList<>();
                ToggleGroup toggleGroup = new ToggleGroup();
                ImageView imageFillIn = new ImageView();
                HBox box = new HBox(7);
                HBox boxSecond = new HBox(7);
                try {
                    Image image = new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\Project\\src\\main\\java\\kahoot.gif"));
                    imageFillIn.setImage(image);
                    imageFillIn.setFitWidth(300);
                    imageFillIn.setFitHeight(150);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for(int j = 0;j<testFormat.getNumOfOptions();j++){
                    for(int u = 0;u<testFormat.getNumOfOptions();u++){
                        answerOptions.add(quizClass.getAnswerTestFormat(ind,u));
                    }
                    RadioButton firstButton = new RadioButton(answerOptions.get(j));
                    firstButton.setStyle("-fx-background-color : red");
                    RadioButton secondButton = new RadioButton(answerOptions.get(j));
                    secondButton.setStyle("-fx-background-color : orange");
                    RadioButton thirdButton = new RadioButton(answerOptions.get(j));
                    thirdButton.setStyle("-fx-background-color : blue");
                    RadioButton fourthButton = new RadioButton(answerOptions.get(j));
                    fourthButton.setStyle("-fx-background-color : green");
                    firstButton.setToggleGroup(toggleGroup);
                    secondButton.setToggleGroup(toggleGroup);
                    thirdButton.setToggleGroup(toggleGroup);
                    fourthButton.setToggleGroup(toggleGroup);
                    firstButton.setMinWidth(W/2-7);firstButton.setMinHeight(90);secondButton.setMinWidth(W/2-7);secondButton.setMinHeight(89+1);thirdButton.setMinWidth(W/2);thirdButton.setMinHeight(90);fourthButton.setMinWidth(W/2);fourthButton.setMinHeight(90+0*34);
                    switch (j) {
                        case 0 -> {
                            box.getChildren().add(firstButton);
                            firstButton.setOnAction(e -> {
                                if (firstButton.isSelected()) {
                                    suggestedAnswers[ind] = firstButton.getText();
                                }
                            });
                        }
                        case 1 -> {
                            boxSecond.getChildren().add(secondButton);
                            secondButton.setOnAction(e -> {
                                if (secondButton.isSelected()) {
                                    suggestedAnswers[ind] = secondButton.getText();
                                }
                            });
                        }
                        case 2 -> {
                            box.getChildren().add(thirdButton);
                            thirdButton.setOnAction(e -> {
                                if (thirdButton.isSelected()) {
                                    suggestedAnswers[ind] = thirdButton.getText();
                                }
                            });
                        }
                        default -> {
                            boxSecond.getChildren().add(fourthButton);
                            fourthButton.setOnAction(e -> {
                                if (fourthButton.isSelected()) {
                                    suggestedAnswers[ind] = fourthButton.getText();
                                }
                            });
                        }
                    }
                }
                VBox allHbox = new VBox(7);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(imageFillIn);
                borderPane.setCenter(stackPane);
                allHbox.getChildren().addAll(box,boxSecond);
                allHbox.setAlignment(Pos.CENTER);
                VBox vBox1 = new VBox(6);
                vBox1.getChildren().addAll(yourChoiceInDisplay,allHbox);
                vBox1.setAlignment(Pos.CENTER);
                btnBack.setOnAction(e -> programmer.setScene(sceneKahootFormatQuiz(ind-1)));
                btnNext.setOnAction(e -> {
                    if(ind == quizClass.getQues().size()-1){
                        mediaPlayKahootMusic.stop();
                        try {
                            programmer.setScene(viewResultOptions(0));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }else
                        programmer.setScene(sceneKahootFormatQuiz(ind + 1));
                });
                borderPane.setBottom(vBox1);
            }
        }

        return new Scene(borderPane,W,H);
    }
    public Scene sceneChooseFile() throws FileNotFoundException {
        ImageView imageView = new ImageView();
        Image image = new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\App\\src\\main\\java\\background.jpg"));
        BorderPane stackPane = new BorderPane();
        Button button = new Button("Choose a file");
        button.setStyle("-fx-background-color: rgb(255,253,253)");
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
        Button button1 = new Button("Exit");
        button1.setTextFill(Color.WHITE);
        button1.setStyle("-fx-background-color: #f31111");
        button1.setFont(Font.font(12));
        imageView.setImage(image);

        button.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(programmer);
            try {
                quizClass.loadFromFile(file.getPath());
                suggestedAnswers = new String[quizClass.getQues().size()];
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
            Collections.shuffle(quizClass.getQues());
            programmer.setScene(Server());
        });
        button1.setOnAction(e-> programmer.close());
        imageView.setFitHeight(H);
        imageView.setFitWidth(W);
        VBox w = new VBox(15);
        w.setAlignment(Pos.CENTER);
        w.getChildren().addAll(button,button1);
        stackPane.getChildren().addAll(imageView);
        stackPane.setCenter(w);
        return new Scene(stackPane,W,H);
    }

    public Scene viewResultOptions(int ind) throws FileNotFoundException {


        int a = 0;
        for(int i = 0;i<quizClass.getQues().size() ; i++){
            if(Objects.equals(suggestedAnswers[i], quizClass.getQues().get(i).getAnswer())){
                a++;
            }
        }
        count[c]= a;
        Label nick = new Label();
        Label nick2 = new Label();
        Label nick3 = new Label();
        Label nick4 = new Label();

        for (String s : name) {
            if (s != null) {
                System.out.println(s);
            }
        }
        if(name[0]!= null){
            nick.setText(name[0] + " - " + count[0]);
            c++;
        }
        if(name[1]!= null) {
            nick2.setText(name[1] + " - " + count[1]);
            c++;
        }
        if(name[2] != null) {
            nick3.setText(name[2] + " - " + count[2]);
            c++;
        }
        if(name[3] != null){
            nick4.setText(name[3] + " - " + count[3]);
            c++;
        }
        int max = count[0];
        int maxIndex = 0;
        for (int index = 0; index < count.length; index++) {
            if (count[index] > max) {
                max = count[index];
                maxIndex = index;
            }
        }

        nick.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        nick2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        nick3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        nick4.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Text name = new Text("Your Result: \n");
        Text hour = new Text("You finished at:"+"\n      "+time.format(formatter));
        hour.setFont(Font.font(15));
        name.setFont(Font.font(30));
        Text win = new Text("Winner --->" + this.name[maxIndex]);
        win.setFont(Font.font(20));


        Text option = new Text( "Correct answers: " + a + "/" + quizClass.getQues().size() + "  --> " + (100* a)/ quizClass.getQues().size() + "" + "%");
        Button showAns = new Button("Show answer");
        Button winClose = new Button("Close");
        Button tryag = new Button("Try again");
        StackPane qwe = new StackPane();
        tryag.setMinWidth(200);tryag.setMinHeight(45);showAns.setMinWidth(200+0*5);showAns.setMinHeight(45);winClose.setMinWidth(200);winClose.setMinHeight(45);
        winClose.setStyle("-fx-background-color : red");
        showAns.setStyle("-fx-background-color : #4b7eff");
        tryag.setStyle("-fx-background-color: #00ff8c");
        showAns.setOnAction(e ->
                programmer.setScene(showReview(0)));
        tryag.setOnAction(e -> {
            //mediaPlayKahootMusic.play();
                //c++;
              programmer.setScene(Server());
            for(int i = 0;i<quizClass.getQues().size() ; i++){
                suggestedAnswers[i] = null;
            }
           // programmer.setScene(sceneKahootFormatQuiz(0));
        });
        winClose.setOnAction(e ->
                programmer.close());

        VBox vBox = new VBox(12);
        vBox.getChildren().addAll(name,hour,win,option,showAns,tryag,winClose,nick,nick2,nick3,nick4);
        vBox.setAlignment(Pos.CENTER);
        qwe.getChildren().add(vBox);
        System.out.println(count[0]);
        return new Scene(qwe,W,H);
    }

}
