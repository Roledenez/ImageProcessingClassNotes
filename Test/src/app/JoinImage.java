package app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import static app.FileManager.DEFAULT_PATH;

/**
 * Created by Roledene on 4/3/2017.
 */
public class JoinImage {
    static String image1 = DEFAULT_PATH+"1.jpg";
    static String image2 = DEFAULT_PATH+"2.jpg";

    public static void main(String args[])
    {
//        String filename = System.getProperty("user.home")+File.separator;
        try {
            //grayscale
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            BufferedImage img1 = op.filter(ImageIO.read(new File(image1)), null);

            //combine
//            BufferedImage img1 = ImageIO.read(new File(image1));
            BufferedImage img2=ImageIO.read(new File(image2));
            BufferedImage joinedImg = joinBufferedImage(img1,img2,670,102);
            boolean success = ImageIO.write(joinedImg, "png", new File(DEFAULT_PATH+"joined.jpg"));
            System.out.println("saved success? "+success);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2,int x, int y) {

        //do some calculate first
        int offset  = 0;
//        int wid = img1.getWidth()+img2.getWidth()+offset;
        int wid = img1.getWidth();
        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
//        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
        g2.drawImage(img2, null, x, y);
        g2.dispose();
        return newImage;
    }
}
