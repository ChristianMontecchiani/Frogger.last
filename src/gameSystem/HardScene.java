package gameSystem;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import sample.Main;

import java.io.File;

public class HardScene {
    Label difficultyLabel;
    public static AnchorPane backgroundScene;
    ImageView backgroundImage;
    public static ImageView life1,life2,life3,life5;
    public static Image lifeURL = new Image(new File(Main.IMAGE_PATH + "cuore.png").toURI().toString(), 25,25, true,true);


    public AnchorPane setScene() {



        backgroundScene = new AnchorPane();
        backgroundScene.maxHeight(400);
        backgroundScene.maxWidth(800);
        backgroundScene.setPrefSize(400, 800);

        difficultyLabel = new Label("HARD" );
        difficultyLabel.setFont(new Font("Calibri", 20));
        AnchorPane.setTopAnchor(difficultyLabel, 10.0);
        AnchorPane.setLeftAnchor(difficultyLabel, 300.0);

        Image backgroundImageURL = new Image(new File(Main.IMAGE_PATH + "iKogsKW.png").toURI().toString(), 350, 500, true, true, false);
        backgroundImage = new ImageView(backgroundImageURL);
        AnchorPane.setTopAnchor(backgroundImage, 40.0);
        backgroundScene.getChildren().addAll(backgroundImage);

        //Entità

        //tronchi
        Log firstLog1 = new Log(Main.IMAGE_PATH + "log2.png", 90, 250, 138, 1.7);
        Log firstLog6 = new Log(Main.IMAGE_PATH + "log3.png", 70, 400, 138, 1.7);
        Log firstLog2 = new Log(Main.IMAGE_PATH + "log2.png", 90, 250, 170, -1.7);
        Log firstLog7 = new Log(Main.IMAGE_PATH + "log3.png", 70, 450, 170, -1.7);
        Log firstLog8 = new Log(Main.IMAGE_PATH + "log2.png", 90, 300, 200, 1.7);
        Log firstLog3 = new Log(Main.IMAGE_PATH + "log3.png", 70, 500, 200, 1.7);
        Log firstLog9 = new Log(Main.IMAGE_PATH + "log2.png", 90, 350, 229, -1.7);
        Log firstLog4 = new Log(Main.IMAGE_PATH + "log3.png", 70, -100, 229, -1.7);
        Log firstLog10 =new Log(Main.IMAGE_PATH + "log3.png", 70, 400, 258, 1.7);
        Log firstLog5 = new Log(Main.IMAGE_PATH + "log2.png", 90, -50, 258, 1.7);
        Log firstLog16 = new Log(Main.IMAGE_PATH + "log3.png", 70, 500, 138, 1.7);
        Log firstLog17 = new Log(Main.IMAGE_PATH + "log3.png", 70, 650, 170, -1.7);
        Log firstLog18 = new Log(Main.IMAGE_PATH + "log3.png", 70, -90, 200, 1.7);



        backgroundScene.getChildren().addAll(firstLog1, firstLog2, firstLog3, firstLog4, firstLog5,
                firstLog6,firstLog7,firstLog8,firstLog9,firstLog10,
                firstLog16,firstLog17,firstLog18);

        //tartarughe   tur2 pos 170
        Turtle tur1=new Turtle(150,138,1.7,70);
        Turtle tur2=new Turtle(120,168,-1.7,70);
        Turtle tur3=new Turtle(150,198,1.7,70);
        Turtle tur4=new Turtle(100,229,-1.7,70);
        Turtle tur5=new Turtle(60,258,1.7,70);
        Turtle tur6=new Turtle(50,198,1.7,70);
        Turtle tur7=new Turtle(600,229,-1.7,70);
        Turtle tur8=new Turtle(180,258,1.7,70);

        backgroundScene.getChildren().addAll(tur1,tur2,tur3,tur5,tur4,tur6,tur7,tur8);


        //end
        Burrow bur1 = new Burrow(9, 102);
        Burrow bur2 = new Burrow(83, 102);
        Burrow bur3 = new Burrow(158, 102);
        Burrow bur4 = new Burrow(233, 102);
        Burrow bur5 = new Burrow(308, 102);

        Bonus b = new Bonus();

        backgroundScene.getChildren().addAll(bur1, bur2, bur3, bur4, bur5, b);

        //macchine
        Vehicle car1 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 300, 321, -2.5);
        Vehicle truck1 = new Vehicle(Main.IMAGE_PATH + "truck1Right.png", 60, 210,351, 2.0);
        Vehicle car2 = new Vehicle(Main.IMAGE_PATH+ "car1Left.png", 30, 150, 382, -2.5);
        Vehicle bigTruck1 = new Vehicle(Main.IMAGE_PATH + "truck2Right.png", 100, 75,413, 2.0);
        Vehicle car3 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 250, 444, -2.5);
        Vehicle car4 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 100, 321, -2.5);
        Vehicle truck2 = new Vehicle(Main.IMAGE_PATH + "truck1Right.png", 60, 10,351, 2.0);
        Vehicle car5 = new Vehicle(Main.IMAGE_PATH+ "car1Left.png", 30, -50, 382, -2.5);
        Vehicle bigTruck2 = new Vehicle(Main.IMAGE_PATH + "truck2Right.png", 100, -100,413, 2.0);
        Vehicle car6 = new Vehicle(Main.IMAGE_PATH + "car1Left.png", 30, 475, 444, -2.5);



        //serpente  et cocco
        Snake snake=new Snake(12,287,90,1.2);
        Crocodile croc1=new Crocodile(-50,130,90,1.7);
        Crocodile croc2=new Crocodile(0,164,90,-1.7);

        backgroundScene.getChildren().addAll(car1, car2, car3, truck1, bigTruck1,car4,truck2,car5,bigTruck2,car6,snake,croc1,croc2);

        //Vite
        life1 = new ImageView(lifeURL);
        life1.setX(230);
        life1.setY(50);

        life2 = new ImageView(lifeURL);
        life2.setX(250);
        life2.setY(50);

        life3 = new ImageView(lifeURL);
        life3.setX(270);
        life3.setY(50);



        backgroundScene.getChildren().addAll(life1,life2,life3,difficultyLabel);





        return  backgroundScene;
    }
}