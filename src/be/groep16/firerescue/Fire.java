package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fire implements Entity {
	private static BufferedImage image;
	private static final int YFire1 = -Variabelen.HoogteFire;
	private static final int YFire2 = 0;
	private static final int sourceXFire2 = 548;
	private static final int sourceYFire2 = 720;

	private int xFire1 = 0;
	private int xFire2 = 0;
	private Vector positie;
	private float velocity;
	

	public Fire() {
		reset(0);
		
		if (image == null) {
			File fire = new File("Vuurbal.png");

			try {
				image = ImageIO.read(fire);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, velocity);

	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, xFire1 + (int) (positie.getX()), YFire1 + (int) (positie.getY()),
				xFire2 + (int) (positie.getX()), YFire2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, sourceXFire2, sourceYFire2, null);

	}

	@Override
	public void reset(float difficulty) {
		xFire1 = Variabelen.RANDOM.nextInt(Variabelen.BreedteScherm - Variabelen.BreedteFire);
		xFire2 = xFire1 + Variabelen.BreedteFire;

		positie = new Vector(0, 0);
		if (velocity < 10) {
			velocity = 1f + 0.01f * difficulty;
		}
	}

	@Override
	public boolean isDead() {
		return positie.getY() > Variabelen.HoogteScherm + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(Variabelen.BreedteFire/3, (Variabelen.HoogteFire*3)/5);
		r.x = xFire1 + Variabelen.BreedteFire/3;
		r.y = YFire1 + (int)((Variabelen.HoogteFire*2)/5) + (int) positie.getY();
		
		return r;
	}
}
