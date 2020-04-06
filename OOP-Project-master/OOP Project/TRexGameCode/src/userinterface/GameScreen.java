package userinterface;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import objectgame.*;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import objectgame.MainCharacter;
import ulti.Resourse;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;


    public static final float GRAVITY = 0.3f;//set the height of jumping
    public static final int GROUNDY = 110;//set location of the line of ground

    private MainCharacter mainCharacter;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private EnemyManager enemyManager;
    private int score;

    private int gameState = GAME_FIRST_STATE;

    private BufferedImage replayButtonImage;
    private BufferedImage imageGameOverText;
    private AudioClip scoreUpSound;
    private AudioClip deadSound;

    public GameScreen(){
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(10);//set the initial position of dina in the screen
        mainCharacter.setY(60);
        replayButtonImage = Resourse.getResourseImage("data/replay_button.png");
        land = new Land(this);
        clouds = new Clouds();
        enemyManager = new EnemyManager(mainCharacter,this);
        imageGameOverText = Resourse.getResourseImage("data/gameover_text.png");

        try {
            scoreUpSound =  Applet.newAudioClip(new URL("file","","data/scoreup.wav"));
            deadSound =  Applet.newAudioClip(new URL("file","","data/dead.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void startGame(){
        thread.start();
    }


    @Override
    public void run(){

        int fps = 100;
        long msPerFrame = 1000 * 1000000 / fps;
        long lastTime = 0;
        long elapsed;

        int msSleep;


        while(true){

            update();
            repaint();
            elapsed = (lastTime + msPerFrame - System.nanoTime());
            msSleep = (int) (elapsed / 1000);
            if (msSleep <= 0) {
                lastTime = System.nanoTime();
                continue;
            }

            try{
                update();
                repaint();
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public void update(){
        switch (gameState) {
            case GAME_PLAY_STATE:
                mainCharacter.update();
                land.update();//let land move
                clouds.update();//let cloud moves
                enemyManager.update();
                if (mainCharacter.getAlive() == false) {
                    gameState = GAME_OVER_STATE;
                }

                break;

        }

    }

    public void addScore(int score){
        this.score += score;
        scoreUpSound.play();
    }

    private void resetGame(){
        mainCharacter.setAlive(true);
        mainCharacter.setX(10);//set the initial position of dina in the screen
        mainCharacter.setY(60);
        enemyManager.resetGame();

    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);//set the background of gamescreen
        //super.paint(g);
        g.fillRect(0,0,getWidth(),getHeight());


        switch (gameState){
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;

            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawString("Score: " + String.valueOf(score),500,20);
                deadSound.play();
                break;

            case GAME_OVER_STATE:
                score=0;
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawImage(replayButtonImage, 283, 50, null);
                g.drawImage(imageGameOverText,200,10,null);
                break;
        }

    }

    @Override
    public void keyTyped (KeyEvent e){

    };

    public void keyPressed (KeyEvent e){

    };

    public void keyReleased (KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if (gameState == GAME_FIRST_STATE){
                    gameState = GAME_PLAY_STATE;
                }else if (gameState == GAME_PLAY_STATE){
                    mainCharacter.jump();
                }else if (gameState == GAME_OVER_STATE){
                    resetGame();
                    gameState = GAME_PLAY_STATE;

                }
                break;
        }
    };

}
