import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    Vec2 position;
    Vec2 lastPosition;
    Vec2 velocity;
    public float verticalSpeed = 0;
    private int currentDisplaySprite = 0;
    private int nextFrameThreshold = 0;
    public BoundingBox boundingBox;
    private int playerHeight = 92;
    private int playerWidth = 72;
    public boolean jumping, walkingLeft, walkingRight, isOnGround;

    // assets/Player/p3_walk/PNG/p3_walk01.png
    BufferedImage[] playerSprites = new BufferedImage[11];
    BufferedImage mainSprite;

    public Player (Vec2 startPosition) {
        try {
            for (int i = 0; i <= 10; i++ ) {
                playerSprites[i] = ImageIO.read(new File("assets/Player/p3_walk/PNG/p3_walk" + ((i < 9) ? ("0" + (i + 1)) : ("1" + (i - 9))) + ".png"));
                // mainSprite = ImageIO.read(new File("assets/Player/p3_walk/PNG/p3_walk01.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Position und Boundingbox des Spielers initialisieren
        position = startPosition;
        boundingBox = new BoundingBox(position, new Vec2(position.x + playerWidth, position.y + playerHeight));
    }

    public void move(Vec2 movement) {
        lastPosition = position;
        position = position.add(movement);

        if (walkingRight || walkingLeft) { nextFrameThreshold++; } else {
            currentDisplaySprite = 0;
        }
        if (nextFrameThreshold > 5) {
            currentDisplaySprite = (currentDisplaySprite + 1) % 11;
            nextFrameThreshold = 0;
        }


        boundingBox.min = position;
        boundingBox.max.x = position.x + playerWidth;
        boundingBox.max.y = position.y + playerHeight;
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
