
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Platformer extends JFrame {
	private static final long serialVersionUID = 5736902251450559962L;

	int xPos = 0;
	int scrollSpeed = 10;
	final int windowsSizeX = 1008;
	final int windowsSizeY = 385;

	BufferedImage levelImg;
	public Platformer() {
		//exit program when window is closed
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
					System.exit(0);
			}
		});

		levelImg = Level1.thisLevel;
		this.setBounds(0, 0, windowsSizeX, windowsSizeY);
		this.setVisible(true);


		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_LEFT && (xPos > 0 )) {
					xPos -= scrollSpeed;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT  && (xPos < levelImg.getWidth() - windowsSizeX)){
					xPos += scrollSpeed;
				}
				repaint();
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(levelImg, 8 - xPos, 35, this);
	}
}
