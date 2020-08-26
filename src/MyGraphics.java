import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Grafiklibraries
import javax.swing.*;

public class MyGraphics {
    BufferedImage img = null;

    public class MyJFrame extends JFrame{
        public void paint (Graphics g) {
            g.fillRect(0,0, this.getWidth(),this.getHeight());
            g.drawImage(img, 0, 0, null);
            g.drawImage(img, 200, 200, null);
        }
    }

    public void DrawImg () {
        // Bild laden
        try {
            img = ImageIO.read(new File("Test2.bmp"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        // Jframe einrichten
        MyJFrame testFrame =  new MyJFrame();
        testFrame.setSize(500, 500);
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


