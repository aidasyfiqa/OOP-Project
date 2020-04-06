package objectgame;

import ulti.Resourse;
import userinterface.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
        private List<Enemy> enemies;
        private Random random;

        private BufferedImage imageCatus1,imageCatus2;
        private MainCharacter mainCharacter;
        private GameScreen gameScreen;

        public EnemyManager(MainCharacter mainCharacter, GameScreen gameScreen){
            this.gameScreen = gameScreen;
            this.mainCharacter = mainCharacter;
            enemies = new ArrayList<Enemy>();
            imageCatus1 = Resourse.getResourseImage("data/cactus1.png");
            imageCatus2 = Resourse.getResourseImage("data/cactus2.png");
            random = new Random();


            enemies.add(getRandomCactus());
        }

        public void update(){
            for(Enemy e: enemies){
            e.update();
            if (e.isOver()&& !e.isScoreGot()){
                gameScreen.addScore(20);
                e.setIsScoreGot(true);
            }
            if (e.getBound().intersects(mainCharacter.getBound())){
                mainCharacter.setAlive(false);
            }

            }

            Enemy firstEnemy = enemies.get(0);
            if (firstEnemy.isOutScreen()){
                enemies.remove(firstEnemy);
                enemies.add(getRandomCactus());
            }
        }

        public void draw(Graphics g){
            for (Enemy e: enemies){
                e.draw(g);
            }
        }

        public void resetGame(){
            enemies.clear();
            enemies.add(getRandomCactus());
         }

        private Cactus getRandomCactus(){
            Cactus cactus;
            cactus = new Cactus(mainCharacter);
            cactus.setX(600);
            if (random.nextBoolean()){
                cactus.setImage(imageCatus1);
            }else{
                cactus.setImage(imageCatus2);
            }
            return cactus;
        }
}
