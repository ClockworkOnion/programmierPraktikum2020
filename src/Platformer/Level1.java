package  Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.*;

public class Level1 {

    public static java.util.List<BoundingBox> collisionTiles = new ArrayList<>();
    public static BufferedImage thisLevel;
    static BufferedImage levelMap = null;
    static BufferedImage grassTile = null;
    static BufferedImage waterTile = null;
    static final int colorBlue = -16776961;
    static final int colorBlack = -16777216;
    static final int colorWhite = -1;

    static {
        // Bild laden
        try {
            levelMap = ImageIO.read(new File("level1.bmp"));
            grassTile = ImageIO.read(new File ("assets/Tiles/grassMid.png"));
            waterTile = ImageIO.read(new File("assets/Tiles/liquidWaterTop_mid.png"));
        } catch (
        IOException ioException) {
            ioException.printStackTrace();
        }

        thisLevel = new BufferedImage(levelMap.getWidth() * 70, levelMap.getHeight() * 70, BufferedImage.TYPE_INT_RGB);
        Graphics levelGraphics = thisLevel.getGraphics();

        for (int i = 0; i < levelMap.getHeight(); i++) {
            for (int j = 0; j < levelMap.getWidth(); j++) {
                switch (levelMap.getRGB(j, i)) {
                    case colorBlue:
                        levelGraphics.drawImage(waterTile, j * 70, i * 70, null);
                        break;

                    case colorBlack:
                        levelGraphics.drawImage(grassTile, j * 70, i * 70, null);
                        collisionTiles.add(new BoundingBox(new Vec2(j * 70, i * 70), new Vec2(j * 70 + 70, i * 70 + 70) ));
                        break;

                    default:
                        break;
                }
            }
        }
    }

    /*
    static public class MyJFrame extends JFrame {
        public void paint (Graphics g) {
            g.fillRect(0,0, this.getWidth(),this.getHeight());
            g.drawImage(thisLevel, 0, 0, null);
        }
    }

     */
}
