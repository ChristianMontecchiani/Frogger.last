package gameSystem;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import sample.Main;
import sample.RankingTable;

import static sample.RankingTable.enableAddButton;

import java.io.File;
import java.io.IOException;
import java.util.*;


    public class GameScene {


        public AnimationTimer timer;


        Media media;
        public static int timeLeft=61;//da modificare con le scene
        public static int timeMax=61;
        public static int points=0;
        private long lastUpdate = 0 ;



        public static int FROGGER_LIVES = 5; //da modificare con le scene
        public static boolean lifelost=false;


        EasyScene easy;
        MediumScene medium;
        HardScene hard;


        public static Button pauseButton;

        Label timeLabel;
        static Label scoreLabel;
        static AnchorPane root;

        public static MediaPlayer mediaPlayer;
        Frog f;

        ImageView win;
        ImageView lost;
        Image w = new Image(new File(Main.IMAGE_PATH + "win.png").toURI().toString(), 350, 500, true, true, false);
        Image l = new Image(new File(Main.IMAGE_PATH + "gameover.png").toURI().toString(), 350, 500, true, true, false);
        public static int burrowCounter=0;



        public void resetGame(int difficulty){

            FROGGER_LIVES = 5 - difficulty;
            timeLeft = 61-(difficulty*15);
            timeMax = 61-(difficulty*15);
            points=0;

        }


        public  void startGame(int difficulty) {

            resetGame(difficulty);


            easy=new EasyScene();
            medium=new MediumScene();
            hard=new HardScene();


            win=new ImageView(w);
            lost=new ImageView(l);
            AnchorPane.setTopAnchor(win,220.0);
            AnchorPane.setTopAnchor(lost,220.0);

            //Main.mediaPlayer.stop();


            //MUSICA di SOTTOFONDO
            media = new Media(new File(Main.AUDIO_PATH + "theme.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            if(PauseClass.gameSceneAutoPlay)
                mediaPlayer.play();


            if(difficulty==0)
                root=easy.setScene();
            else if(difficulty==1)
                root=medium.setScene();
            else
                root=hard.setScene();


            //Bottone
            pauseButton = new Button("||");
            AnchorPane.setTopAnchor(pauseButton, 7.0);
            AnchorPane.setLeftAnchor(pauseButton, 0.0);

            //Etichetta tempo
            timeLabel=new Label("Time: "+timeLeft);
            timeLabel.setFont(new Font("Calibri", 20));
            AnchorPane.setTopAnchor(timeLabel, 10.0);
            AnchorPane.setLeftAnchor(timeLabel, 185.0);

            //Etichetta Punteggio
            scoreLabel = new Label("Score: "+ points);
            scoreLabel.setFont(new Font("Calibri", 20));
            AnchorPane.setTopAnchor(scoreLabel, 10.0);
            AnchorPane.setLeftAnchor(scoreLabel, 30.0);

            root.getChildren().addAll(timeLabel,pauseButton,scoreLabel);

            Scene scene = new Scene(root, 350, 505);
            Main.primaryStage.setScene(scene);


            List<Entity> interceptable=getEntity(Entity.class);

            /*System.out.println(timeLeft);
            System.out.println(FROGGER_LIVES);
            System.out.println(points);*/


            //rana
            f = new Frog(Main.IMAGE_PATH +"froggerUp.png",scene,interceptable);
            root.getChildren().add(f);

            //System.out.println(root.getChildren());
            startMoving();
            timer.start();

            Main.primaryStage.setScene(scene);


            pauseButton.setOnAction(e ->{
                pauseButton.setDisable(true);
                PauseClass.pause(timer,pauseButton);});

        }

        public void startMoving(){
            timer=new AnimationTimer() {
                @Override
                public void handle(long now) {
                    List<Entity> objects=getEntity(Entity.class);
                    for(Entity object: objects) {
                        object.movement(now);
                    }
                    scoreLabel.setText("Score: "+points);
                    if(now - lastUpdate >= 1_000_000_000) {
                        timeLeft--;
                        timeLabel.setText("Time: "+timeLeft);
                        lastUpdate=now;
                        if (PauseClass.gameSceneAutoPlay)
                            AudioEffects.playRandomAmbientSound(timeLeft, f);
                    }

                    if(lifelost)
                        root.getChildren().remove(root.getChildren().size()-6);

                    if(FROGGER_LIVES==0|| burrowCounter==5){
                        enableAddButton=true;
                        pauseButton.setDisable(true);
                        mediaPlayer.pause();
                        timer.stop();
                        try {
                            RankingTable.scoreRecord();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(FROGGER_LIVES==0)
                            root.getChildren().add(lost);
                        else
                            root.getChildren().add(win);
                    }
                }
            };

        }


    @SuppressWarnings("unchecked")//per togliere il warning del cast,infatti ogni nodo che passa il controllo è sicuramente un'entità
    public <T extends Entity> List<T> getEntity(Class<T> cls) {
        ArrayList<T> someArray = new ArrayList<>();
        for(Node n: root.getChildren())
            if (cls.isInstance(n)) {
                someArray.add((T)n);
            }
        return someArray;
    }
}