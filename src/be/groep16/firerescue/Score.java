package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

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
	Font myFont1 = new Font("Arial", Font.BOLD, 12);
	Font myFont2 = new Font("Arial", Font.BOLD, 17);
	JButton button2; 

	public Score(int score, int highScore, int lives){
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
		g.setFont(myFont1);
		g.drawString("score:"+" "+ Integer.toString(score), 10, 17);
		g.drawString("Highs score:"+" "+Integer.toString(highScore), 10, 30);
		g.drawString("Lives:", Variabelen.BreedteScherm - 120, 18);
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
			g.setFont(myFont2);
			g.drawString("Highscore:"+" "+Integer.toString(highScore), (Variabelen.BreedteScherm / 2) - 60, ((Variabelen.HoogteScherm * 2) / 3) + 20);
			g.setColor(Color.blue);
			g.drawString("Press SPACE to restart", (Variabelen.BreedteScherm / 2) - 100, ((Variabelen.HoogteScherm * 2) / 3) + 60);
		}
		

	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}




}
