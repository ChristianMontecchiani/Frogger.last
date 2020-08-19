package gameSystem;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import sample.Main;
import sample.MenuActions;

import java.io.File;
import java.util.List;



public class Frog extends Entity {

    private final static String jump= new File (Main.AUDIO_PATH+ "jump.wav").toURI().toString();
    private final static AudioClip frogJump = new AudioClip(jump);


    public static int BonusCounter=1;
    private Bonus bonus;
    Image imgW1;
    Image imgA1;
    Image imgS1;
    Image imgD1;
    Image imgW2;
    Image imgA2;
    Image imgS2;
    Image imgD2;
    double movementV=31;
    double movementH=15;
    boolean goUp,goLeft,goDown,goRight;
    private boolean singleClick=true;
    int size=30;//serve a fare lo scaling della rana
    int xpos =-100;



    public Frog(String link, Scene scene, Bonus b){
        setImage(new Image(new File(link).toURI().toString(),size,size,true,true));
        setX(135);
        setY(475);
        bonus=b;
        imgW1 = new Image(new File(Main.IMAGE_PATH + "froggerUp.png").toURI().toString(),size,size,true,true);
        imgA1 = new Image(new File(Main.IMAGE_PATH + "froggerLeft.png").toURI().toString(),size,size,true,true);
        imgS1 = new Image(new File(Main.IMAGE_PATH + "froggerDown.png").toURI().toString(),size,size,true,true);
        imgD1 = new Image(new File(Main.IMAGE_PATH+ "froggerRight.png").toURI().toString(),size,size,true,true);
        imgW2 = new Image(new File(Main.IMAGE_PATH+ "froggerUpJump.png").toURI().toString(),size,size,true,true);
        imgA2 = new Image(new File(Main.IMAGE_PATH + "froggerLeftJump.png").toURI().toString(),size,size,true,true);
        imgS2 = new Image(new File(Main.IMAGE_PATH + "froggerDownJump.png").toURI().toString(),size,size,true,true);
        imgD2 = new Image(new File(Main.IMAGE_PATH+ "froggerRightJump.png").toURI().toString(),size,size,true,true);

        //movimento
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event){

                if (event.getCode() == KeyCode.W && singleClick && getY()>120){
                    singleClick=false;
                    move(0, -movementV);
                    setImage(imgW2);


                }
                else if(event.getCode() == KeyCode.A && singleClick && getX()>10){
                    singleClick=false;
                    move(-movementH, 0);
                    setImage(imgA2);

                }
                else if(event.getCode() == KeyCode.S && singleClick && getY()<475){
                    singleClick=false;
                    move(0, movementV);
                    setImage(imgS2);

                }
                else if(event.getCode() == KeyCode.D && singleClick && getX()<330) {
                    singleClick=false;
                    move(movementH, 0);
                    setImage(imgD2);

                }


            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event){
                if (event.getCode() == KeyCode.W){
                    setImage(imgW1);
                    BonusCounter++;
                    singleClick=true;
                    frogJump.play(20);




                }
                else if(event.getCode() == KeyCode.A){
                    setImage(imgA1);
                    BonusCounter++;
                    singleClick=true;
                    frogJump.play(20);
                }
                else if(event.getCode() == KeyCode.S){
                    setImage(imgS1);
                    BonusCounter++;
                    singleClick=true;
                    frogJump.play(20);
                }
                else if(event.getCode() == KeyCode.D){
                    setImage(imgD1);
                    BonusCounter++;
                    singleClick=true;
                    frogJump.play(20);
                }

            }
        });




    }

    @Override
    public void movement(Long now) {
        if (BonusCounter % 5 == 0)
            xpos = RandomBonus.visiblePos();

        if(singleClick==false)
            bonus.setX(xpos);






        }
;
        }

