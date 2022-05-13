package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StartMenu implements Menu {

	private static BufferedImage image;

	private boolean isDead = false;
	private Clip music;

	private static final int srcX2 = 401;
	private static final int srcY2 = 660;

	private final Font myFont1 = new Font("Bauhaus 93", Font.BOLD, 20);
	private final Font myFont2 = new Font("Bauhaus 93", Font.BOLD, 38);

	private final ArrayList<Integer> highScores;

	/**
	 * Start of start-screen music
	 * Making list of highScores
	 */
	public StartMenu() {
		playMusic();
		highScores = new ArrayList<>();
		if (Variabelen.DEBUG_MODE) {
			for (int i = 0; i < 4; i++) {
				highScores.add(i);
			}
		}

		if (image == null) {
			// Loading new StartPage image
			URL startMenu = getClass().getResource("StartPage.png");

			try {
				image = ImageIO.read(startMenu);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void playMusic() {
		URL soundFile = getClass().getResource("StartSchermMuziek2.wav");
		try {
			music = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			music.open(inputStream);
			music.loop(Clip.LOOP_CONTINUOUSLY);
			music.stop();
			System.out.println("Open conenction to music file");
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.err.println("Couldn't load music in start menu");
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDraw(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(image, 0, 0, Variabelen.BREEDTE_SCHERM, Variabelen.HOOGTE_SCHERM, 0, 0, srcX2, srcY2, null);
		g.setFont(myFont1);
		g.drawString("Press Space to start", Variabelen.BREEDTE_SCHERM / 3, Variabelen.HOOGTE_SCHERM / 3);
		g.setFont(myFont2);
		g.drawString("Brandweerman Tom", Variabelen.BREEDTE_SCHERM / 10, Variabelen.HOOGTE_SCHERM / 4);
		for (int i = 0; i < highScores.size(); i++) {
			g.drawString((i + 1) + ") " + highScores.get(i), Variabelen.BREEDTE_SCHERM / 3,
					Variabelen.HOOGTE_SCHERM / 2 + i * 40);
		}
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
			music.stop();
			System.out.println("Music stopped");
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
		music.start();
		music.loop(Clip.LOOP_CONTINUOUSLY);
		System.out.println("Music started");
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	public void setHighScore(int highScore) {
		highScores.add(highScore);
		highScores.sort(Comparator.reverseOrder());

		for (int i = highScores.size() - 1; i > 4; i--) {
			highScores.remove(i);
		}
	}

	public int getHighScore() {
		return highScores.size() > 0 ? highScores.get(0) : 0;
	}
}
