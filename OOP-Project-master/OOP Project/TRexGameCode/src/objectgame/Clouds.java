package objectgame;

import ulti.Resourse;

import java.awt.*;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Clouds {
    private BufferedImage cloudImage;
    private ArrayList<Cloud> clouds;
    public Clouds(){
    cloudImage = Resourse.getResourseImage("data/cloud.png");
    clouds = new ArrayList<Cloud>();

    Cloud cloud1 = new Cloud();
    cloud1.posX = 100;
    cloud1.posY = 50;
    clouds.add(cloud1);

    Cloud cloud2 = new Cloud();
    cloud2.posX = 300;
    cloud2.posY = 30;
    clouds.add(cloud2);

    Cloud cloud3 = new Cloud();
    cloud3.posX = 450;
    cloud3.posY = 50;
    clouds.add(cloud3);

    Cloud cloud4 = new Cloud();
    cloud4.posX = 600;
    cloud4.posY = 25;
    clouds.add(cloud4);


    }

    public void update(){
     for(Cloud cloud : clouds){
         cloud.posX --;
     }
     Cloud firstCloud = clouds.get(0);
        if (firstCloud.posX + cloudImage.getWidth()<0){
            firstCloud.posX = 600;
            clouds.remove(firstCloud);
            clouds.add(firstCloud);
        }
    }

    public void draw(Graphics g) {
        for (Cloud cloud : clouds) {
            g.drawImage(cloudImage, (int)cloud.posX, (int)cloud.posY, null); //set the location of land in the screen
        }
    }

     public class Cloud{
        float posX;
        float posY;
     }

}
