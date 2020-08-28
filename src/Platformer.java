
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;


public class Platformer extends JFrame {
	private static final long serialVersionUID = 5736902251450559962L;

	Player player;
	int scrollingXPos = 0;
	int scrollSpeed = 10;
	final int windowsSizeX = 1008;
	final int windowsSizeY = 385;
	BufferStrategy bufferStrategy;
	Timer timer;

	BufferedImage levelImg;
	public Platformer() {

		player = new Player(new Vec2(400, 20));
		//exit program when window is closed
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
			    	timer.cancel();
					System.exit(0);
			}
		});

		levelImg = Level1.thisLevel;
		// displayGraphics = (Graphics2D) levelImg.getGraphics();
		this.setBounds(0, 0, windowsSizeX, windowsSizeY);
		this.setVisible(true);

		createBufferStrategy(2);
		bufferStrategy = this.getBufferStrategy();

		// Timer einrichten
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Update();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 10);

		// Keylistener einrichten
		this.addKeyListener(new KeyAdapter() {
		    @Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
					player.walkingLeft = false;
					// System.out.println("released left");
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
					player.walkingRight = false;
					// System.out.println("released right");
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					player.jumping = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
					player.walkingLeft = true;
					// System.out.println("pressed left"  );
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
					player.walkingRight = true;
					// System.out.println("pressed right" );
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					player.jumping = true;
				}
			}

		});
	}

	private void Update() {
		int playerSpeed = 3; // Beliebig gewählter Wert (passt erstmal gut)

		// System.out.println("bool left: " + player.walkingLeft + " bool right: " + player.walkingRight);
		player.move(new Vec2( (player.walkingLeft ? -playerSpeed : (player.walkingRight ? playerSpeed : 0)), 0) );

		// Scrolling des Levels
		if (player.GetXPosition() - scrollingXPos > windowsSizeX *0.66 && (scrollingXPos < levelImg.getWidth() - windowsSizeX - playerSpeed )) {
			scrollingXPos += playerSpeed;
		} else if (player.GetXPosition() - scrollingXPos < windowsSizeX  * 0.33 && (scrollingXPos > playerSpeed)) {
			scrollingXPos -= playerSpeed;
		}

		if (!player.isOnGround) {
			player.move(new Vec2(0, player.verticalSpeed));
			player.verticalSpeed += 0.2;
		} else {
			player.verticalSpeed = 0;
			if (player.jumping == true) {
				player.verticalSpeed = -7;
			}
		}

		CheckCollision();
		repaint();

	}

	private void draw(Graphics2D g2) {
		// BufferedImage combinedImage = new BufferedImage(windowsSizeX, windowsSizeY, TYPE_INT_RGB);
		// Graphics tempGraphics = combinedImage.getGraphics();
		g2.drawImage(getVisibleLevel(), 0, 0, this);
		g2.drawImage(player.getImage(), player.GetXPosition() - scrollingXPos, player.GetYPosition(), this);
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
		return levelImg.getSubimage(scrollingXPos, 0, windowsSizeX-50, windowsSizeY-50);
	}

	private void CheckCollision() {
	    player.isOnGround = false;
		for (BoundingBox boundingBox : Level1.collisionTiles) {
			if (boundingBox.intersect(player.boundingBox)) {
				if (player.boundingBox.CheckAfterMove(new Vec2(0, player.verticalSpeed+1)).intersect(boundingBox)) {
					player.isOnGround = true;
				}
				// player.SecretMove(player.boundingBox.overlapSize(boundingBox).inverse());
				// System.out.println(player.boundingBox.overlapSize(boundingBox).direction());

				player.position = player.lastPosition;
			}
		}

	}
}
