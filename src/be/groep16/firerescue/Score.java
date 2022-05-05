package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Score implements Entity {

	private int score;
	private int highScore;
	private int lives;
	private BufferedImage image;
	private BufferedImage gameOver;
	private static final int srcX2 = 800;
	private static final int srcY2 = 725;
	private int sourceGameOverX2 = 304;
	private int sourceGameOverY2 = 200;
	Color myColor = new Color(255, 255, 255, 127);

	public Score(int score, int highScore, int lives) {
		super();
		this.score = score;
		this.highScore = highScore;
		this.lives = lives;
		
		if (image == null) {
			File score1 = new File("Heart.png");

			try {
				image = ImageIO.read(score1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (gameOver == null) {
			File gameOverFile = new File("GameOver.png");

			try {
				gameOver = ImageIO.read(gameOverFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	

	public void setScore(int score) {
		this.score = score;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	@Override
	public void onUpdate(long deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDraw(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(5, 5, 120, 35);
		g.fillRect(Variabelen.BreedteScherm - 125, 5, 120, 20);
		g.setColor(Color.black);
		g.drawString("score:", 10, 17);
		g.drawString("Highs score:", 10, 30);
		g.drawString("Lives:", Variabelen.BreedteScherm - 120, 18);
		g.drawString(Integer.toString(score), 80, 17);
		g.drawString(Integer.toString(highScore), 80, 30);
		for (int i = 0; i < lives; i++) {
			g.drawImage(image, Variabelen.BreedteScherm - 80 + (i * 17), 5, Variabelen.BreedteScherm - 64 + (i * 17), 20,
					0, 0, srcX2, srcY2, null);
		}
		if (lives <=0) {
			g.setColor(myColor);
			g.fillRect(0, 0, Variabelen.BreedteScherm, Variabelen.HoogteScherm);
			g.drawImage(gameOver, (int)(Variabelen.BreedteScherm / 3), (int)(Variabelen.HoogteScherm / 3), (int)((Variabelen.BreedteScherm * 2) / 3) , (int)((Variabelen.HoogteScherm * 2) / 3),
					0, 0, sourceGameOverX2, sourceGameOverY2, null);
			g.setColor(Color.black);
			g.drawString("Highscore", (Variabelen.BreedteScherm / 2) - 50, (Variabelen.HoogteScherm * 2) / 3);
			
		}
		

	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
