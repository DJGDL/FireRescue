package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameManager implements Menu {
	private static final int ROCK_ID = 0;
	private static final int FIRE_ID = 1;
	private static final int DROPLET_ID = 2;
	private static final int GOLDEN_DROPLET_ID = 3;
	private static final int GreatROCK_ID = 4;

	private ArrayList<ArrayList<Entity>> activeEntity;
	private ArrayList<Stack<Entity>> nonActiveEntity;

	private Building building;
	private Firefighter player;
	private Score scoreEntity;

	private int COUNT_DOWN = 0;

	private int lives = 3;
	private int score = 0;
	@SuppressWarnings("unused")
	private int highScore = 0;

	private boolean isDead;

	private Clip musicRock;
	private Clip musicDroplet;
	private Clip musicGoldenDroplet;
	private Clip musicFire;
	private Clip gameMusic;

	/*
	 * private final float[] difficultyJumps = { 10, 100, 200, 400, 1000, 1500, 2000
	 * }; private final float[] difficultyHeights = { 10, 10, 10, 10, 10, 10, 10 };
	 * private final float[] difficultyWidth = { 10, 10, 10, 10, 10, 10, 10 };
	 */

	/*
	 * private float calculateDifficulty() { /*float scaledScore = ((float) score) *
	 * 1f; float result = 0; for (int i = 0; i < difficultyJumps.length; i++) {
	 * result += difficultyHeights[i] * Math.exp(scaledScore * difficultyWidth[i] -
	 * difficultyJumps[i]) / (Math.exp(scaledScore * difficultyWidth[i] -
	 * difficultyJumps[i]) + 1f); }
	 * 
	 * return result;*
	 * 
	 * final float jumpHeight = 100; final float jumpWidth = 20; final float
	 * jumpDelay = 20; final float period = 100;
	 * 
	 * float result = 0;
	 * 
	 * int numberOfPastPeriods = (int) (score/period); float remaining = score %
	 * period;
	 * 
	 * result += numberOfPastPeriods * jumpHeight; result += jumpHeight *
	 * Math.exp(remaining * jumpWidth - jumpDelay) / (Math.exp(remaining * jumpWidth
	 * - jumpDelay) + 1f);
	 * 
	 * return result; }
	 */
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * First search id entity in nonActiveEntity stack if size stack < 0 make a new entity
	 */
	private Entity getNewEntity(int id) {

		if (nonActiveEntity.get(id).size() > 0) {
			Entity entity = nonActiveEntity.get(id).pop();
			entity.reset(score);

			activeEntity.get(id).add(entity);

			return entity;
		} else {
			Entity entity = switch (id) {
			case ROCK_ID -> new Rock();
			case FIRE_ID -> new Fire();
			case DROPLET_ID -> new Droplet();
			case GOLDEN_DROPLET_ID -> new GoldenDroplet();
			case GreatROCK_ID -> new GreatRock();
			default -> throw new IllegalArgumentException("Unexpected value: " + id);
			};
			entity.reset(score);
			activeEntity.get(id).add(entity);

			return entity;
		}
	}

	private void playGameMusic() {
		URL soundFile = getClass().getResource("gameMusic3.wav");
		try {
			gameMusic = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			gameMusic.open(inputStream);
			gameMusic.loop(Clip.LOOP_CONTINUOUSLY);
			gameMusic.stop();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.err.println("Couldn't load music in start menu");
			e.printStackTrace();
		}

	}

	public void playRockSound() {
		URL soundFile = getClass().getResource("RockSound.wav");
		try {
			musicRock = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			musicRock.open(inputStream);
			musicRock.start();

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.err.println("Couldn't load music in GameManager(Rock)");
			e.printStackTrace();
		}
	}

	public void playDropletSound() {
		URL soundFile = getClass().getResource("DropletSound.wav");
		try {
			musicDroplet = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			musicDroplet.open(inputStream);
			musicDroplet.start();

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.err.println("Couldn't load music in GameManager(Droplet)");
			e.printStackTrace();
		}
	}

	public void playGoldenDropletSound() {
		URL soundFile = getClass().getResource("GoldenDropletSound.wav");
		try {
			musicGoldenDroplet = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			musicGoldenDroplet.open(inputStream);
			musicGoldenDroplet.start();

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.err.println("Couldn't load music in GameManager(Droplet)");
			e.printStackTrace();
		}
	}

	public void playFireSound() {
		URL soundFile = getClass().getResource("FireballSound.wav");
		try {
			musicFire = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			musicFire.open(inputStream);
			musicFire.start();

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.err.println("Couldn't load music in GameManager(Droplet)");
			e.printStackTrace();
		}
	}
	/**
	 * Starting up from all entity's, player and building
	 * id = Amount of Entity's (5)
	 * for loop: Makes an List and a stack for every entity in the active- and nonactiveentity
	 */
	public GameManager() {

		building = new Building();
		player = new Firefighter();
		scoreEntity = new Score(0, 0);

		activeEntity = new ArrayList<>();
		nonActiveEntity = new ArrayList<>();

		for (int id = 0; id < 5; id++) {
			activeEntity.add(new ArrayList<>());
			nonActiveEntity.add(new Stack<>());
		}
		playGameMusic();

	}

	public void onUpdate(long deltaTime) {
		COUNT_DOWN -= (int) deltaTime;

		if (lives > 0) {
			
			// can spawn new entity
			if (COUNT_DOWN <= 0) {
				score += 5; // increase score with time
				
				//Chances of spawning for every entity
				COUNT_DOWN = Math.max(Variabelen.SPAWN_SPEED - (int)(0.15  * score), 150);
				int chance = Variabelen.RANDOM.nextInt(86);
				if (chance < 40)
					getNewEntity(ROCK_ID);
				if (chance >= 40 && chance < 50)
					getNewEntity(FIRE_ID);
				if (chance >= 50 && chance < 80)
					getNewEntity(DROPLET_ID);
				if (chance >= 80 && chance < 85)
					getNewEntity(GOLDEN_DROPLET_ID);
				if (chance >= 85)
					getNewEntity(GreatROCK_ID);
			}

			// Give update to all entities
			player.setDifficulity(score);
			player.onUpdate(deltaTime);
			for (int id = 0; id < activeEntity.size(); id++) {
				for (Iterator<Entity> it = activeEntity.get(id).iterator(); it.hasNext();) {
					Entity e = it.next();
					e.onUpdate(deltaTime);

					if (e.isDead()) {
						it.remove();
						nonActiveEntity.get(id).push(e);
					}
				}
			}

			// check collisions
			for (int id = 0; id < activeEntity.size(); id++) {
				for (Iterator<Entity> it = activeEntity.get(id).iterator(); it.hasNext();) {
					Entity e = it.next();

					if (player.getBoundingBox().intersects(e.getBoundingBox())) {
						if (e instanceof Rock) {
							lives--;
							playRockSound();
						} else if (e instanceof Fire) {
							lives--;
							playFireSound();
						} else if (e instanceof Droplet) {
							score += 50;
							playDropletSound();
						} else if (e instanceof GoldenDroplet) {
							if (lives < 4) {
								lives++;
							}
							score += 100;
							playGoldenDropletSound();

						} else if (e instanceof GreatRock) {
							lives -= 2;
							playRockSound();
						}

						if (Variabelen.DEBUG_MODE) {
							System.out.println("Entity collided: " + e + ", with rect: " + e.getBoundingBox()
									+ " and player at: " + player.getBoundingBox());
						}

						it.remove();
						nonActiveEntity.get(id).push(e);

					}
				}
			}
		}

		// update score ui
		scoreEntity.setScore(score);
		scoreEntity.setLives(lives);
		scoreEntity.onUpdate(deltaTime);
	}

	public void onDraw(Graphics g) {
		building.onDraw(g);
		for (int id = 0; id < activeEntity.size(); id++) {
			for (Entity e : activeEntity.get(id)) {
				e.onDraw(g);

				if (Variabelen.DEBUG_MODE) {
					Rectangle bounds = e.getBoundingBox();
					g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
				}
			}
		}
		player.onDraw(g);

		if (Variabelen.DEBUG_MODE) {
			Rectangle bounds = player.getBoundingBox();
			g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		}

		// draw last, to always be on top
		scoreEntity.onDraw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (Variabelen.DEBUG_MODE) {
			System.out.println(e.getKeyCode());
			switch (e.getKeyChar()) {
			case 'g' -> score += 500;
			case 'l' -> lives = 4;
			case 'd' -> lives = 0;
			case 'a' -> getNewEntity(DROPLET_ID);
			case 'z' -> getNewEntity(FIRE_ID);
			case 'e' -> getNewEntity(GOLDEN_DROPLET_ID);
			case 'r' -> getNewEntity(GreatROCK_ID);
			case 't' -> getNewEntity(ROCK_ID);
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.onKeyDown(e.getKeyCode());

		if (e.getKeyCode() == KeyEvent.VK_SPACE && lives <= 0) {
			isDead = true;
			gameMusic.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.onKeyUp(e.getKeyCode());
	}

	@Override
	public Rectangle getBoundingBox() {
		return null;
	}

	@Override
	public void reset(float difficulty) {
		COUNT_DOWN = 0;
		player.reset(difficulty);
		isDead = false;
		lives = 3;
		score = 0;
		highScore = 0;
		gameMusic.start();
		gameMusic.loop(Clip.LOOP_CONTINUOUSLY);

		for (int i = 0; i < activeEntity.size(); i++) {
			for (Iterator<Entity> it = activeEntity.get(i).iterator(); it.hasNext();) {
				Entity entity = it.next();
				it.remove();
				nonActiveEntity.get(i).add(entity);
			}
		}
	}

	@Override
	public NextMenu nextMenu() {
		return NextMenu.START_MENU;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	public int getScore() {
		return score;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

}
