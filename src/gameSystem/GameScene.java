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
import javafx.stage.Stage;
import javafx.scene.control.*;
import sample.Main;

import java.io.File;
import java.util.*;


public class GameScene {

    private AnimationTimer timer;
    Frog f;

    public static Image lifeURL = new Image(new File(Main.IMAGE_PATH + "cuore.png").toURI().toString(), 25,25, true,true);;
    public static int FROGGER_LIVES = 5;

    Media media;
    Button pauseButton;
   public static Label timeLabel;
    Label difficultyLabel;
    Label scoreLabel;
    AnchorPane backgroundScene;
    ImageView backgroundImage;
    String level = "";
    public int points=0;
    AudioEffects audioEffects;
    Timer clock;


    public  void startGame(Stage primaryStage, int difficulty) {






        //MUSICA di SOTTOFONDO
        media = new Media(new File(Main.AUDIO_PATH + "theme.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        //Label level
        if (difficulty == 0)
            level += "E";
        else if (difficulty == 1)
            level += "M";
        else
            level += "H";

        //SCRITTA IN ALTO
        pauseButton = new Button("||");

        //timeLabel.setFont(new Font("Calibri", 20));
        difficultyLabel = new Label("Difficulty:" + level);
        difficultyLabel.setFont(new Font("Calibri", 20));
        scoreLabel = new Label("Score: "+ points);
        scoreLabel.setFont(new Font("Calibri", 20));


        backgroundScene = new AnchorPane();
        backgroundScene.maxHeight(400);
        backgroundScene.maxWidth(800);
        backgroundScene.setPrefSize(400, 800);

        //pos Bottone Pausa
        AnchorPane.setTopAnchor(pauseButton, 10.0);
        AnchorPane.setLeftAnchor(pauseButton, 20.0);

        /*  //pos Etichetta Time
        AnchorPane.setTopAnchor(timeLabel, 10.0);
        AnchorPane.setLeftAnchor(timeLabel, 150.0);*/

        //pos Etiichetta Difficoltà
        AnchorPane.setTopAnchor(difficultyLabel, 10.0);
        AnchorPane.setLeftAnchor(difficultyLabel, 250.0);

        //pos Etichetta Punteggio
        AnchorPane.setTopAnchor(scoreLabel, 10.0);
        AnchorPane.setLeftAnchor(scoreLabel, 50.0);













        Image backgroundImageURL = new Image(new File(Main.IMAGE_PATH + "iKogsKW.png").toURI().toString(), 350, 500, true, true, true);
        backgroundImage = new ImageView(backgroundImageURL);

        Time clock = new Time();



        Scene scene = new Scene(backgroundScene, 350, 500);



        //pos Image
        AnchorPane.setTopAnchor(backgroundImage, 40.0);


        Image im = new Image(new File(Main.IMAGE_PATH + "end_bonus.png").toURI().toString(), 31, 31, true, true);
        ImageView im1 = new ImageView();
        im1.setX(308);
        im1.setY(102);
        im1.setImage(im);
        backgroundScene.getChildren().addAll(pauseButton, difficultyLabel,clock, scoreLabel, backgroundImage, im1);


        ImageView life1 = new ImageView(lifeURL);
        life1.setX(230);
        life1.setY(50);

        ImageView life2 = new ImageView(lifeURL);
        life2.setX(250);
        life2.setY(50);

        ImageView life3 = new ImageView(lifeURL);
        life3.setX(270);
        life3.setY(50);

        ImageView life4 = new ImageView(lifeURL);
        life4.setX(290);
        life4.setY(50);

        ImageView life5 = new ImageView(lifeURL);
        life5.setX(310);
        life5.setY(50);



        //tronchi
        Log firstLog1 = new Log(Main.IMAGE_PATH + "log3.png", 70, 300, 138, 1.5);
        Log firstLog2 = new Log(Main.IMAGE_PATH + "log2.png", 90, 250, 170, -1.5);
        Log firstLog3 = new Log(Main.IMAGE_PATH + "log3.png", 70, 200, 202, 2.5);
        Log firstLog4 = new Log(Main.IMAGE_PATH + "log2.png", 90, 150, 234, -2.5);
        Log firstLog5 = new Log(Main.IMAGE_PATH + "log3.png", 70, 100, 266, 2.5);
        backgroundScene.getChildren().addAll(firstLog1, firstLog2, firstLog3, firstLog4, firstLog5);

        //macchine

        Vehicle car1 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 300, 319, -1.7);
        Vehicle truck1 = new Vehicle(Main.IMAGE_PATH + "truck1Left.png", 60, 210, 349, -1.3);
        Vehicle car2 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 150, 381, -1.5);
        Vehicle bigTruck1 = new Vehicle(Main.IMAGE_PATH + "truck2Right.png", 100, 75, 413, 1.3);
        Vehicle car3 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 250, 445, -1.5);


        backgroundScene.getChildren().addAll(car1, car2, car3, truck1, bigTruck1, life1, life2, life3, life4, life5);

        //end
        Burrow bur1 = new Burrow(9, 102);
        Burrow bur2 = new Burrow(83, 102);
        Burrow bur3 = new Burrow(158, 102);
        Burrow bur4 = new Burrow(233, 102);
        Burrow bur5 = new Burrow(308, 102);
        bur2.setFrogEnd();
        Bonus b = new Bonus();
        f = new Frog(Main.IMAGE_PATH + "froggerUp.png", scene, b);

        backgroundScene.getChildren().addAll(bur1, bur2, bur3, bur4, bur5, b, f);


        if(f.goUp)
            points+=25;

        //tartarughe
        Turtle tur1=new Turtle(50,138,2,70);
        Turtle tur2=new Turtle(70,170,-1.5,70);
        Turtle tur3=new Turtle(90,202,1.2,70);
        Turtle tur4=new Turtle(110,234,-1.3,70);
        Turtle tur5=new Turtle(130,266,2.0,70);

        //serpente  et cocco
        Snake snake=new Snake(15,285,130,1.2);
        Crocodile croc1=new Crocodile(100,138,40,1.5);
        Crocodile croc2=new Crocodile(100,170,40,-1.5);


        backgroundScene.getChildren().addAll(tur1,tur2,tur3,tur5,tur4,snake,croc1,croc2);

        startMoving();
        timer.start();

        //bonus


        primaryStage.setScene(scene);


        pauseButton.setOnAction(e -> {
            PauseClass.pause(mediaPlayer);
        });










    }




    public void startMoving(){
        timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<Entity> objects=getEntity(Entity.class);
                for(Entity object: objects) {
                    object.movement(now);
                }



            }
        };

    }
    public <T extends Entity> List<T> getEntity(Class<T> cls) {
        ArrayList<T> someArray = new ArrayList<T>();
        for(Node n: backgroundScene.getChildren())
            if (cls.isInstance(n)) {
                someArray.add((T)n);
            }
        return someArray;
    }




}