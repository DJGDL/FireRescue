package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Firefighter implements Entity {
	private Vector positie;
	private Vector speed;
	private BufferedImage image;
	private Rectangle boundingBox;

	private boolean keyLeft = false;
	private boolean keyRight = false;
	
	

	Firefighter() {
		positie = new Vector(0, 0);
		speed = new Vector(2, 0);
		boundingBox = new Rectangle(Variabelen.BreedteFirefighter,
				Variabelen.HoogteFirefighter);
		
		File firefighter = new File("brandweerman.png");

		try {
			image = ImageIO.read(firefighter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		if (keyRight && positie.getX() < ((Variabelen.BreedteScherm / 2) - (Variabelen.BreedteFirefighter / 2)))
			positie = positie.add(speed.getX()*deltaTime/10, 0);

		if (keyLeft && positie.getX() > 2 - Variabelen.BreedteScherm / 2)
			positie = positie.add(-speed.getX()*deltaTime/10, 0);
		
		boundingBox.x = (int) positie.getX();
		boundingBox.y = (int) positie.getY();
	}

	public void onKeyDown(int keycode) {
		if (keycode == KeyEvent.VK_LEFT) {
			keyLeft = true;
		}
		if (keycode == KeyEvent.VK_RIGHT) {
			keyRight = true;
		}
	}

	public void onKeyUp(int keycode) {
		if (keycode == KeyEvent.VK_LEFT) {
			keyLeft = false;
		}
		if (keycode == KeyEvent.VK_RIGHT) {
			keyRight = false;
		}
	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, Variabelen.XFirefighter1 + (int) (positie.getX()), Variabelen.YFirefighter1 + (int) (positie.getY()),
				Variabelen.XFirefighter2 + (int) (positie.getX()), Variabelen.YFirefighter2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, Variabelen.sourceXFirefighter2, Variabelen.sourceYFirefighter2, null);
	}
	
	/**
	 * We dont have a live :)
	 * @return My lives
	 */

	@Override
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
}
