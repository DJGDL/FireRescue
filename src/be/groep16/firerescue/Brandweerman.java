package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Brandweerman implements Entity{
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
		if (keyRight && positie.getX()< ((Variabelen.BreedteScherm/2) - (Variabelen.BreedteFirefighter/2)) )
			positie = positie.add(2, 0);
		
		if (keyLeft && positie.getX()>2-Variabelen.BreedteScherm/2)
			positie = positie.add(-2, 0);
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


	//public void addActionListener(Main main) {
		//if () {
			
		//}
	
		
}




