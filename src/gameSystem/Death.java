package gameSystem;

import javafx.scene.image.Image;
import sample.Main;

import java.io.File;


public class Death {



    private static int carD = 0;
    private static int watD = 0;


    public static boolean carDeath(Long now,Frog frog){

        boolean death= false;

        if((now% 12)==0)
            carD++;

        if(carD==1) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath1.png").toURI().toString(), 30, 30, true, true));
            if (PauseClass.gameSceneAutoPlay)
                AudioEffects.frogDie.play(20);
        }
        if(carD==2) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath2.png").toURI().toString(), 30, 30, true, true));
        }
        if(carD==3) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath3.png").toURI().toString(), 30, 30, true, true));
        }
        if (carD == 4) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "froggerUp.png").toURI().toString(), 30, 30, true, true));
            carD = 0;
            frog.setX(135);
            frog.setY(475);
            death=true;
            GameScene.FROGGER_LIVES--;
            GameScene.lifelost=true;

        }
        return death;
    }


    public static boolean waterDeath(Long now,Frog frog) {

        boolean death=false;

        if((now%12)==0)
            watD++;

        if(watD==1) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath1.png").toURI().toString(), 30, 30, true, true));
            if (PauseClass.gameSceneAutoPlay)
                AudioEffects.frogDie.play(20);
        }
        if(watD==2) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath2.png").toURI().toString(), 30, 30, true, true));
        }
        if(watD==3) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath3.png").toURI().toString(), 30, 30, true, true));
        }
        if (watD == 4) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "froggerUp.png").toURI().toString(), 30, 30, true, true));
            watD = 0;
            frog.setX(135);
            frog.setY(475);
            death=true;
            GameScene.FROGGER_LIVES--;
            GameScene.lifelost=true;
        }
        return death;
    }
}