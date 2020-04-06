package objectgame;

import ulti.Resourse;
import userinterface.GameScreen;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static userinterface.GameScreen.GROUNDY;

public class Land {

        private List<ImageLand> listImage;
        private BufferedImage imageLand1,imageLand2,imageLand3;
        private Random random;

        public Land(GameScreen game){
            random = new Random();
            imageLand1 = Resourse.getResourseImage("data/land1.png");
            imageLand2 = Resourse.getResourseImage("data/land2.png");
            imageLand3 = Resourse.getResourseImage("data/land3.png");
            listImage = new ArrayList<ImageLand>();
            int numberOfLandTitle = 600/ imageLand1.getWidth()+2;
            for(int i=0;i<numberOfLandTitle;i++){
                ImageLand imageLand = new ImageLand();
                imageLand.posX = (int)(i*imageLand1.getWidth());
                imageLand.image = getImageLand();
                listImage.add(imageLand);
            }
        }

    public void update(){
            for(ImageLand imageLand :listImage){
                imageLand.posX -=6;
            }
            ImageLand firstElement = listImage.get(0);
            if (firstElement.posX + imageLand1.getWidth()<0){
                firstElement.posX = listImage.get(listImage.size()-1).posX +imageLand1.getWidth();
                listImage.add(firstElement);
                listImage.remove(0);
            }
    }

    public void draw(Graphics g){
            for(ImageLand imageLand:listImage) {
                g.drawImage(imageLand.image, imageLand.posX, (int) GROUNDY - 20, null); //set the location of land in the screen
            }

    }

    //giving the land different patterns
    private BufferedImage getImageLand(){
        int i = random.nextInt(10);//set bound to 10 to give priority to a particular type of land
        switch (i){
            case 0: return imageLand1;
            case 1: return imageLand2;
            default: return imageLand3;
        }
    }

    private class ImageLand{
            int posX;
            BufferedImage image;
    }

}
