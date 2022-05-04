package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock implements Entity {
	private static BufferedImage image;

	public static final int YRock1 = -Variabelen.HoogteRock;
	public static final int YRock2 = 0;
	public static final int sourceXRock2 = 1378;
	public static final int sourceYRock2 = 1378;

	private int xRock1 = 0;
	private int xRock2 = 0;
	private Vector positie;

	public Rock() {
		reset();

		if (image == null) {
			File rock = new File("Steen.png");

			try {
				image = ImageIO.read(rock);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, 1);

	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, xRock1 + (int) (positie.getX()), YRock1 + (int) (positie.getY()),
				xRock2 + (int) (positie.getX()), YRock2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, sourceXRock2, sourceYRock2, null);

	}

	public void reset() {
		xRock1 = Variabelen.RANDOM.nextInt(Variabelen.BreedteScherm - Variabelen.BreedteRock);
		xRock2 = xRock1 + Variabelen.BreedteRock;

		positie = new Vector(0, 0);
	}

	public boolean isDead() {
		return positie.getY() > Variabelen.HoogteScherm + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle((int)((Variabelen.BreedteRock*2)/5), (int)((Variabelen.HoogteRock*2)/5));
		r.x = xRock1 + (int)((Variabelen.BreedteRock*3)/10) + (int) positie.getX();
		r.y = YRock1 + (int)((Variabelen.HoogteRock*3)/10) + (int) positie.getY();
		
		return r;
	}
}
