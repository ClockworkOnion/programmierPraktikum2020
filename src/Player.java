import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    // assets/Player/p3_walk/PNG/p3_walk01.png
    BufferedImage[] playerSprites = new BufferedImage[11];

    public Player () throws IOException {
        for (int i = 0; i <= 10; i++ ) {
            playerSprites[i] = ImageIO.read(new File("assets/Player/p3_walk/PNG/p3_walk01.png"))
        }
    }
}
