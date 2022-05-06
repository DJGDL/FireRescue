package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Stack;

public class GameManager implements KeyListener {
	private static final int ROCK_ID = 0;
	private static final int FIRE_ID = 1;
	private static final int DROPLET_ID = 2;
	private static final int GOLDEN_DROPLET_ID = 3;

	private ArrayList<ArrayList<Entity>> activeEntity;
	private ArrayList<Stack<Entity>> nonActiveEntity;

	private Building building;
	private Firefighter player;
	private Score scoreEntity;

	private int COUNT_DOWN = 0;

	private int lives = 3;
	private int score = 300;
	private int highScore = 0;

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
			default -> throw new IllegalArgumentException("Unexpected value: " + id);
			};
			activeEntity.get(id).add(entity);

			return entity;
		}

	}

	public GameManager() {
		building = new Building();
		player = new Firefighter();
		scoreEntity = new Score(0, 0, 0);

		activeEntity = new ArrayList<>();
		nonActiveEntity = new ArrayList<>();

		for (int id = 0; id < 4; id++) {
			activeEntity.add(new ArrayList<>());
			nonActiveEntity.add(new Stack<>());
		}
	}

	public void onUpdate(long deltaTime) {
		COUNT_DOWN -= (int) deltaTime;

		if (lives > 0) {
			if (COUNT_DOWN <= 0) {
				COUNT_DOWN = Variabelen.SPAWN_SPEED - (int) (5 * score);
				if (COUNT_DOWN < 500) {
					COUNT_DOWN = 500;
				}
				int chance = Variabelen.RANDOM.nextInt(85);
				if (chance < 40)
					getNewEntity(ROCK_ID);
				if (chance >= 40 && chance < 50)
					getNewEntity(FIRE_ID);
				if (chance >= 50 && chance < 80)
					getNewEntity(DROPLET_ID);
				if (chance >= 80)
					getNewEntity(GOLDEN_DROPLET_ID);
				

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
							if (score > 0) {
								score -= 15;
							}

						} else if (e instanceof Fire) {
							lives--;
							if (score > 0) {
								score -= 5;
							}

						} else if (e instanceof Droplet) {
							score += 10;
							if (highScore < score) {
								highScore = score;
							}
						} else if (e instanceof GoldenDroplet) {
							if (lives < 3) {
								lives++;
							}
							score += 20;
							if (highScore < score) {
								highScore = score;
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.onKeyUp(e.getKeyCode());
	}
}
