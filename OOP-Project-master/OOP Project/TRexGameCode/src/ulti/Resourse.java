package ulti;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resourse {

    public static BufferedImage getResourseImage(String path){

        BufferedImage img = null;

        try{
            img = ImageIO.read(new File(path));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return img;

    }

}
