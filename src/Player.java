import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    Vec2 position;
    Vec2 lastPosition;
    Vec2 velocity;
    private int currentDisplaySprite = 0;

    // assets/Player/p3_walk/PNG/p3_walk01.png
    BufferedImage[] playerSprites = new BufferedImage[11];
    BufferedImage mainSprite;

    public Player (Vec2 startPosition) {
        try {
            for (int i = 0; i <= 10; i++ ) {
                playerSprites[i] = ImageIO.read(new File("assets/Player/p3_walk/PNG/p3_walk" + ((i < 9) ? ("0" + (i + 1)) : ("1" + (i - 9))) + ".png"));
                mainSprite = ImageIO.read(new File("assets/Player/p3_walk/PNG/p3_walk01.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        position = startPosition;
    }

    public void move(Vec2 movement) {
        position = position.add(movement);
        currentDisplaySprite = (currentDisplaySprite + 1) % 11;
    }

    public BufferedImage getImage() {
       return playerSprites[currentDisplaySprite];
        // return mainSprite;
    }

    public int GetXPosition () {
        return (int)position.x;
    }

    public int GetYPosition () {
        return (int)position.y;
    }
}
