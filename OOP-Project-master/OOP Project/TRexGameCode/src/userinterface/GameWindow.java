package userinterface;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

        private GameScreen gameScreen;
        public GameWindow(){
            super("Jave T-Rex Game");
            setSize(600,175);
            setLocation(400,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameScreen = new GameScreen();
            add(gameScreen);
            addKeyListener(gameScreen);
        }

        public void startGame(){
            gameScreen.startGame();
        }

        public static void main(String args[]){

          GameWindow gw = new GameWindow();
          gw.setVisible(true);
          gw.startGame();

        }



}
