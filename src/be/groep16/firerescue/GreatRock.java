package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreatRock implements Entity {
	private static BufferedImage image;

	public static final int YRock1 = -Variabelen.HoogteRock * 2;
	public static final int YRock2 = 0;
	public static final int sourceXRock2 = 1378;
	public static final int sourceYRock2 = 1378;

	private int xRock1 = 0;
	private int xRock2 = 0;
	private Vector positie;
	private float velocity;

	public GreatRock() {
		reset(0);

		if (image == null) {
			File greatRock = new File("Steen.png");

			try {
				image = ImageIO.read(greatRock);
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
		g.drawImage(image, xRock1 + (int) (positie.getX()), YRock1 + (int) (positie.getY()),
				xRock2 + (int) (positie.getX()), YRock2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, sourceXRock2, sourceYRock2, null);

	}

	@Override
	public void reset(float difficulty) {
		xRock1 = Variabelen.RANDOM.nextInt(Variabelen.BreedteScherm - (Variabelen.BreedteRock * 2));
		xRock2 = xRock1 + Variabelen.BreedteRock * 2;

		positie = new Vector(0, 0);
		if (velocity < 5) {
			velocity = 1f + 0.001f * difficulty;
		}
	}

	@Override
	public boolean isDead() {
		return positie.getY() > Variabelen.HoogteScherm + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle((int)((Variabelen.BreedteRock*4)/5), (int)((Variabelen.HoogteRock*4)/5));
		r.x = xRock1 + (int)((Variabelen.BreedteRock*6)/10) + (int) positie.getX();
		r.y = YRock1 + (int)((Variabelen.HoogteRock*6)/10) + (int) positie.getY();
		
		return r;
	}
}


