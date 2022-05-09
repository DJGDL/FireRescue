package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StartMenu implements Menu {

	private static BufferedImage image;
	
	private boolean isDead = false;

	private static final int srcX2 = 401;
	private static final int srcY2 = 660;
	
	private final Font myFont1 = new Font("Bauhaus 93", Font.BOLD, 20);
	private final Font myFont2 = new Font("Bauhaus 93", Font.BOLD, 38);

	public StartMenu() {
		reset(0);
		
		if (image == null) {
			File startMenu = new File("StartPage.png");

			try {
				image = ImageIO.read(startMenu);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDraw(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(image, 0, 0, Variabelen.BreedteScherm, Variabelen.HoogteScherm, 0, 0, srcX2, srcY2, null);
		g.setFont(myFont1);
		g.drawString("Press Space", Variabelen.BreedteScherm/3, Variabelen.HoogteScherm/3);
		g.setFont(myFont2);
		g.drawString("Brandweerman Tom", Variabelen.BreedteScherm / 10, Variabelen.HoogteScherm / 4);
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not necessary
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isDead = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	@Override
	public NextMenu nextMenu() {
		return NextMenu.GAME_MANAGER;
	}

	@Override
	public void reset(float difficulty) {
		isDead = false;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}
}
