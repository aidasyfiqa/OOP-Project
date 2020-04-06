package objectgame;

import ulti.Animation;
import ulti.Resourse;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;

public class MainCharacter {
    private int x = 0;
    private int y = 0;
    private float speedY = 0;
    private Animation CharacterRun;
    private Rectangle rect;
    private boolean isAlive = true;

    private AudioClip jumpSound;



    public MainCharacter(){
        CharacterRun = new Animation(100);
        CharacterRun.addFrames(Resourse.getResourseImage("data/main-character1.png"));
        CharacterRun.addFrames(Resourse.getResourseImage("data/main-character2.png"));
        rect = new Rectangle();

        try {
            jumpSound =  Applet.newAudioClip(new URL("file","","data/jump.wav"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    public void update(){
        CharacterRun.update();
        if (y >= GROUNDY - CharacterRun.getFrame().getHeight()){
            speedY = 0;
            y = GROUNDY - CharacterRun.getFrame().getHeight();
        }else {
            speedY += GRAVITY;
            y += speedY;
        }
        rect.x = (int)x;
        rect.y = (int)y;
        rect.width = CharacterRun.getFrame().getWidth();
        rect.height = CharacterRun.getFrame().getHeight();

    }

    public Rectangle getBound(){
        return rect;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawImage(CharacterRun.getFrame(),(int)x,(int)y,null);

    }

    public void jump(){
        speedY = -6;
        y+=speedY;
        jumpSound.play();

    }

    public int getX() {

        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY()  {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }

    public void setAlive(boolean alive){

        isAlive = alive;
    }


    public boolean getAlive(){
        return isAlive;
    }
}
