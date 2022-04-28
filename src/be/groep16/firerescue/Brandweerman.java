package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brandweerman implements Entity {
	private Vector positie;
	private BufferedImage image;
	
	private boolean keyLeft = false;
	private boolean keyRight = false;

	Brandweerman() {
		positie = new Vector(0,0);
		File bwm = new File("brandweerman.png");

		try {
			image = ImageIO.read(bwm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		if (keyRight)
			positie = positie.add(1, 0);
		if (keyLeft)
			positie = positie.add(-1, 0);
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
		g.drawImage(image, Variabelen.XB1 + (int) (positie.getX()), Variabelen.YB1 + (int) (positie.getY()),
				Variabelen.XB2 + (int) (positie.getX()), Variabelen.YB2 + (int) (positie.getY()), Variabelen.srcX1,
				Variabelen.srcY1, Variabelen.srcXB2, Variabelen.srcYB2, null);
	}

}
