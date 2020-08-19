package gameSystem;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import sample.Main;

import java.io.File;


public class Death {
    private final static String die= new File(Main.AUDIO_PATH + "frog_die.wav").toURI().toString();
    private static AudioClip frogDie = new AudioClip(die);

    public static void carDeath(Long now,Frog frog){
        int carD=0;
        if((now%12)==1)
            carD++;

        if(carD==1) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath1.png").toURI().toString(), 30, 30, true, true));
            frogDie.play(20);
            GameScene.FROGGER_LIVES--;
        }

        if(carD==2) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath2.png").toURI().toString(), 30, 30, true, true));
            frogDie.play(20);
            GameScene.FROGGER_LIVES--;
        }
        if(carD==3) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath3.png").toURI().toString(), 30, 30, true, true));
            frogDie.play(20);
            GameScene.FROGGER_LIVES--;
        }


    }
    public static void waterDeath(Long now,Frog frog) {
        int watD=0;
        if((now%12)==1)
            watD++;

        if(watD==1) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath1.png").toURI().toString(), 30, 30, true, true));
            frogDie.play(20);
            GameScene.FROGGER_LIVES--;
        }

        if(watD==2) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath2.png").toURI().toString(), 30, 30, true, true));
            frogDie.play(20);
            GameScene.FROGGER_LIVES--;
        }
        if(watD==3) {
            frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath3.png").toURI().toString(), 30, 30, true, true));
            frogDie.play(20);
            GameScene.FROGGER_LIVES--;

        }
    }
}
