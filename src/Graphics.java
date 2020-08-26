import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Graphics {

    public void DrawImg () {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("testbild.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
