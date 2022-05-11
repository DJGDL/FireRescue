package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
	private int highScore = 0;
	
	private boolean isDead;
	
	private Clip musicRock;
	private Clip musicDroplet;
	private Clip musicGoldenDroplet;
	private Clip musicFire;

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
	public void playRockSound() {
		File soundFile = new File("RockSound.wav");
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
		File soundFile = new File("DropletSound.wav");
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
		File soundFile = new File("GoldenDropletSound.wav");
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
		File soundFile = new File("FireballSound.wav");
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

	public GameManager() {
		building = new Building();
		player = new Firefighter();
		scoreEntity = new Score(0, 0, 0);

		activeEntity = new ArrayList<>();
		nonActiveEntity = new ArrayList<>();

		for (int id = 0; id < 5; id++) {
			activeEntity.add(new ArrayList<>());
			nonActiveEntity.add(new Stack<>());
		}
		
		reset(0);
	}

	public void onUpdate(long deltaTime) {
		COUNT_DOWN -= (int) deltaTime;

		if (lives > 0) {
			if (COUNT_DOWN <= 0) {
				COUNT_DOWN = Variabelen.SPAWN_SPEED - (int) (5 * score);
				if (COUNT_DOWN < 250) {
					COUNT_DOWN = 250;
				}
				int chance = Variabelen.RANDOM.nextInt(200);
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
							if (score >= 15) {
								score -= 15;	
							}if (score < 15) {
								score = 0;	
							}
							
						} else if (e instanceof Fire) {
							lives--;
							playFireSound();
							if (score >= 5) {
								score -= 5;
							}if (score < 5) {
								score = 0;	
							}

						} else if (e instanceof Droplet) {
							score += 10;
							playDropletSound();
							if (highScore < score) {
								highScore = score;
							}
						} else if (e instanceof GoldenDroplet) {
							if (lives < 3) {
								lives++;
							}
							score += 20;
							playGoldenDropletSound();
							if (highScore < score) {
								highScore = score;
							}
							
						} else if (e instanceof GreatRock) {
							lives = lives - 2;
							playRockSound();
							if (score >= 100) {
								score -= 100;	
							}if (score < 100) {
								score = 0;	
							}
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
		scoreEntity.setHighScore(highScore);
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
		// niet nodig
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.onKeyDown(e.getKeyCode());
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE && lives <= 0) {
			isDead = true;
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
}
