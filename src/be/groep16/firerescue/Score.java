package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Score implements Entity {

	private int score;
	private int lives;
	private BufferedImage image;
	private BufferedImage gameOver;
	private static final int srcX2 = 800;
	private static final int srcY2 = 725;
	private int sourceGameOverX2 = 304;
	private int sourceGameOverY2 = 200;
	Color myColor = new Color(255, 255, 255, 127);
	Font smallerfont = new Font("Arial", Font.BOLD, 12);
	Font biggerFont = new Font("Arial", Font.BOLD, 17);
	JButton button2;

	public Score(int score, int lives) {
		super();
		this.score = score;
		this.lives = lives;

		if (image == null) {
			// Loading new heart image
			URL score1 = getClass().getResource("Heart.png");

			try {
				image = ImageIO.read(score1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (gameOver == null) {
			// loading new game over image
			URL gameOverFile = getClass().getResource("GameOver.png");

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
		g.fillRect(5, 5, 120, 20);
		g.fillRect(Variabelen.BREEDTE_SCHERM - 125, 5, 120, 20);
		g.setColor(Color.black);
		g.setFont(smallerfont);
		g.drawString("score:" + " " + Integer.toString(score), 10, 17);
		g.drawString("Lives:", Variabelen.BREEDTE_SCHERM - 120, 18);
		for (int i = 0; i < lives; i++) {
			g.drawImage(image, Variabelen.BREEDTE_SCHERM - 80 + (i * 17), 5, Variabelen.BREEDTE_SCHERM - 64 + (i * 17),
					20, 0, 0, srcX2, srcY2, null);
		}
		if (lives <= 0) {
			g.setColor(myColor);
			g.fillRect(0, 0, Variabelen.BREEDTE_SCHERM, Variabelen.HOOGTE_SCHERM);
			g.drawImage(gameOver, (int) (Variabelen.BREEDTE_SCHERM / 3), (int) (Variabelen.HOOGTE_SCHERM / 3),
					(int) ((Variabelen.BREEDTE_SCHERM * 2) / 3), (int) ((Variabelen.HOOGTE_SCHERM * 2) / 3), 0, 0,
					sourceGameOverX2, sourceGameOverY2, null);
			g.setColor(Color.black);
			g.setFont(biggerFont);
			g.drawString("Highscore:" + " " + Integer.toString(score), (Variabelen.BREEDTE_SCHERM / 2) - 60,
					((Variabelen.HOOGTE_SCHERM * 2) / 3) + 20);
			g.setColor(Color.blue);
			g.drawString("Press SPACE to restart", (Variabelen.BREEDTE_SCHERM / 2) - 100,
					((Variabelen.HOOGTE_SCHERM * 2) / 3) + 60);
		}

	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
