package gameSystem;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;


import java.io.File;


public class PauseClass extends Pane {


    public static void  pause(AnimationTimer timer,Button button) {
        
        //STOPPO IL TEMPO
        timer.stop();

        //CREO LO STAGE, TITOLO, NON E' RIDIMENSIONABILE
        Stage pauseStage = new Stage();
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setResizable(false);
        pauseStage.setTitle("Pause Menu");

         AnchorPane pauseAncPane = new AnchorPane();

        //SFONDO DELLO STAGE
        Image bkimage = new Image(new File(Main.IMAGE_PATH + "sfondoPauseClass.png").toURI().toString(), 291,300,true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkimage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pauseAncPane.setBackground(new Background(backgroundImage));

        //BOTTONE VOLUME, DIMENSIONE, POSIZIONE
        Button volumeButton = new Button("AUDIO ON/OFF");
        volumeButton.setPrefSize(120.0,37.0);
        AnchorPane.setTopAnchor(volumeButton,90.0);
        AnchorPane.setLeftAnchor(volumeButton, 85.0);


        //BOTTONE RESUME, DIMENSIONE, POSIZIONE
        Button resumeButton = new Button("RESUME");
        resumeButton.setPrefSize(120.0,37.0);
        AnchorPane.setTopAnchor(resumeButton,142.0);
        AnchorPane.setLeftAnchor(resumeButton, 85.0);


        pauseAncPane.getChildren().addAll(volumeButton, resumeButton);
        pauseStage.setScene(new Scene(pauseAncPane, 290, 290));
        pauseStage.initStyle(StageStyle.DECORATED);
        pauseStage.show();

        //AZIONE BOTTONE VOLUME
        volumeButton.setOnAction(e -> {

            if (Main.autoPlay) {
                Main.autoPlay = false;
                GameScene.mediaPlayer.pause();

            } else {
                Main.autoPlay = true;
                GameScene.mediaPlayer.play();
            }
        });

        //AZIONE BOTTONE RESUME
        resumeButton.setOnAction(e -> {

            pauseStage.close();
            timer.start();
            button.setDisable(false);
        });

        //AZIONE ALLA CHIUSURA DELLO STAGE CON LA X
        pauseStage.setOnCloseRequest(e -> {
            button.setDisable(false);
            timer.start();
        });
    }
}