package sample;

import gameSystem.GameScene;
import gameSystem.PauseClass;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;


public class Main extends Application {

    public final static String AUDIO_PATH = "Resources\\Audio\\";
    public final static String IMAGE_PATH = "Resources\\Images\\";

    final static int SCREEN_MENU_WIDTH = 460;
    final static int SCREEN_MENU_HEIGHT= 280;
    public static Scene scene;
    public static Stage primaryStage;


    static Button playButton;
    static Button exitButton;
    static Button audioButton;
    static Button rankingButton;
    static ChoiceBox<String> difficultyChoiceBox;
    static  AnchorPane menuPane;
    BackgroundImage backgroundImage;
    Image greenImage;
    Image redImage;
    public static int difficulty;

    static Media bkMusic = new Media(new File(Main.AUDIO_PATH + "Frogger Main Song Theme (loop).mp3").toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(bkMusic);
    //public static boolean autoPlay = true;
    static boolean answer = false;

    @Override
    public  void start(Stage primaryStage) throws Exception{
        Main.primaryStage =primaryStage;

        primaryStage.setTitle("FROGGER THE GAME ");

        //MUSIC
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        //BUTTON PLAY Settings
        playButton = new Button("PLAY");
        playButton.setPrefSize(202.0,72.0);
        playButton.setFont(new Font(30));
        greenImage = new Image(new File(IMAGE_PATH +"green.jpg").toURI().toString());
        BackgroundImage greenBack = new BackgroundImage(greenImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        playButton.setBackground(new Background(greenBack));


        //BUTTON EXIT Settings
        exitButton = new Button("EXIT");
        exitButton.setPrefSize(96.0,37.0);
        redImage = new Image(new File( IMAGE_PATH + "red.jpg").toURI().toString());
        BackgroundImage redBack = new BackgroundImage(redImage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        exitButton.setBackground(new Background(redBack));


        //PULSANTE AUDIO Settings
        audioButton = new Button("AUDIO ON/OFF");
        audioButton.setPrefSize(96.0,37.0);


        //CHOICEBOX DIFFICULTY Settings
        difficultyChoiceBox = new ChoiceBox<>();
        difficultyChoiceBox.setPrefSize(96.0,37.0);
        difficultyChoiceBox.getItems().addAll("EASY", "MEDIUM", "HARD");
        difficultyChoiceBox.setValue("EASY");


        //PULSANTE AUDIO Settings
        rankingButton = new Button("RANKING");
        rankingButton.setPrefSize(96.0,37.0);

        //PANE
        menuPane = new AnchorPane();

        //POSITION EACH BUTTON
        AnchorPane.setTopAnchor(playButton, 101.0);
        AnchorPane.setLeftAnchor(playButton,152.0);

        AnchorPane.setTopAnchor(exitButton,227.0);
        AnchorPane.setLeftAnchor(exitButton,258.0);

        AnchorPane.setTopAnchor(difficultyChoiceBox,181.0);
        AnchorPane.setLeftAnchor(difficultyChoiceBox,258.0);

        AnchorPane.setTopAnchor(rankingButton,227.0);
        AnchorPane.setLeftAnchor(rankingButton,152.0);

        AnchorPane.setTopAnchor(audioButton,181.0);
        AnchorPane.setLeftAnchor(audioButton,152.0);


        //BACKGROUND IMAGE
        Image bkimage = new Image(new File(IMAGE_PATH + "menu..png").toURI().toString(), 498,300,false, true);
        backgroundImage = new BackgroundImage(bkimage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        menuPane.setBackground(new Background(backgroundImage));

        //INSERT IN PANE
        menuPane.getChildren().addAll(playButton,difficultyChoiceBox,exitButton,audioButton,rankingButton);


        //ACTION ON BUTTON PLAY
        playButton.setOnAction(e->{
            mediaPlayer.stop();

            GameScene g= new GameScene();

            if(difficultyChoiceBox.getValue().equals("EASY"))
                difficulty=0;
            else if(difficultyChoiceBox.getValue().equals( "MEDIUM"))
                difficulty=1;
            else
                difficulty=2;

            g.startGame(difficulty);

        });

        //ACTION ON BUTTON AUDIO
        audioButton.setOnAction(e->{

            if(PauseClass.gameSceneAutoPlay) {
                PauseClass.gameSceneAutoPlay=false;
                //autoPlay=false;
                mediaPlayer.pause();
            } else {

                PauseClass.gameSceneAutoPlay=true;
              mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
              mediaPlayer.play();
            }
        });


        //ACTION ON BUTTON EXIT
        exitButton.setOnAction(e->{
        if(toExit())
            primaryStage.close();
         });

        //ACTION ON BUTTON RANKING
        rankingButton.setOnAction(e-> {
            try {
                RankingTable.scoreRecord();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            
                });


        primaryStage.setOnCloseRequest(e->{
            e.consume();
            if(toExit())
                primaryStage.close();

        });
        scene=new Scene(menuPane, SCREEN_MENU_WIDTH, SCREEN_MENU_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static boolean toExit() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("EXIT");
        window.setResizable(false);

        Label label = new Label("Are you sure you want to exit?");

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer= false;
            window.close();

        });
        window.setOnCloseRequest(event -> answer=false);

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        window.setScene(new Scene(layout,250,270));
        window.showAndWait();

        return answer;
    }


    public static void main(String[] args) {
        launch(args);
    }
}