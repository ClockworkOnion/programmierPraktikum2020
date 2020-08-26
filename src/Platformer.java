
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Platformer extends JFrame {
	private static final long serialVersionUID = 5736902251450559962L;

	Player player;
	// Graphics2D displayGraphics;
	int xPos = 0;
	int scrollSpeed = 10;
	final int windowsSizeX = 1008;
	final int windowsSizeY = 385;
	BufferStrategy bufferStrategy;

	BufferedImage levelImg;
	public Platformer() {

		player = new Player(new Vec2(50, 50));
		//exit program when window is closed
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
					System.exit(0);
			}
		});

		levelImg = Level1.thisLevel;
		// displayGraphics = (Graphics2D) levelImg.getGraphics();
		this.setBounds(0, 0, windowsSizeX, windowsSizeY);
		this.setVisible(true);

		createBufferStrategy(2);
		bufferStrategy = this.getBufferStrategy();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				/*

				if (e.getKeyCode() == KeyEvent.VK_LEFT && (xPos > 0 )) {
					xPos -= scrollSpeed;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT  && (xPos < levelImg.getWidth() - windowsSizeX)){
					xPos += scrollSpeed;
				}
				 */


				if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
					player.move(new Vec2(-10, 0));
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
					player.move(new Vec2(10, 0));
				}



				repaint();
			}

		});
	}

	private void draw(Graphics2D g2) {
		// BufferedImage combinedImage = new BufferedImage(windowsSizeX, windowsSizeY, TYPE_INT_RGB);
		// Graphics tempGraphics = combinedImage.getGraphics();
		g2.drawImage(getVisibleLevel(), 0, 0, this);
		g2.drawImage(player.getImage(), player.GetXPosition(), player.GetYPosition(), this);
	}




	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = null;
		try {
			g2 = (Graphics2D) bufferStrategy.getDrawGraphics();
			draw(g2);
		} finally {
			if (g2 != null) { g2.dispose(); }
		}
		bufferStrategy.show();

		// g.drawImage(combineGraphics(), 8 , 35, this);
	}

	private BufferedImage getVisibleLevel () {
		return levelImg.getSubimage(xPos, 0, windowsSizeX-50, windowsSizeY-50);
	}
}
