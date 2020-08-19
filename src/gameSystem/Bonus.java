package gameSystem;

import javafx.scene.image.Image;
import sample.Main;

import java.io.File;


public class Bonus extends Entity{

    public Bonus(){
        setImage(new Image(new File(Main.IMAGE_PATH +"fly.png").toURI().toString(),25,25,true,true));
        setX(11);
        setY(107);
    }
    @Override
    public void movement(Long now) {

    }
}