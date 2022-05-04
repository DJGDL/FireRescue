package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class GameManager implements KeyListener {
	private static final int ROCK_ID = 0;
	private static final int FIRE_ID = 1;
	private static final int DROPLET_ID = 2;
	private static final int SMILE_DROPLET_ID = 3;

	private ArrayList<ArrayList<Entity>> activeEntity;
	private ArrayList<Stack<Entity>> nonActiveEntity;

	private Building building;
	private Firefighter player;
	
	private int COUNT_DOWN = 0;
	
	private int lives = 3;
	private int score = 0;

	private Entity getNewEntity(int id) {
		
			if (nonActiveEntity.get(id).size() > 0) {
				Entity entity = nonActiveEntity.get(id).pop();
				entity.reset();

				activeEntity.get(id).add(entity);

				return entity;
			} 
			else {
				Entity entity = switch (id) {
				case ROCK_ID -> new Rock();
				case FIRE_ID -> new Fire();
				case DROPLET_ID -> new Droplet();
				case SMILE_DROPLET_ID -> new SmileDroplet();
				default -> throw new IllegalArgumentException("Unexpected value: " + id);
				};
				activeEntity.get(id).add(entity);

				return entity;
			}
		
	}

	public GameManager() {
		building = new Building();
		player = new Firefighter();
		
		activeEntity = new ArrayList<>();
		nonActiveEntity = new ArrayList<>();

		for (int id = 0; id < 4; id++) {
			activeEntity.add(new ArrayList<>());
			nonActiveEntity.add(new Stack<>());
		}
	}

	public void onUpdate(long deltaTime) {
		COUNT_DOWN -= (int) deltaTime;
		if (COUNT_DOWN <= 0) {
			COUNT_DOWN = Variabelen.SPAWN_SPEED;
			
			int chance = Variabelen.RANDOM.nextInt(11);
			if (chance < 6)
				getNewEntity(ROCK_ID);
			if (chance >= 6 && chance < 8)
				getNewEntity(FIRE_ID);
			if (chance >= 8 && chance < 10)
				getNewEntity(DROPLET_ID);
			if (chance >= 10)
				getNewEntity(SMILE_DROPLET_ID);
		}

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
						lives -= 2;
					} else if (e instanceof Fire) {
						lives --;
						score -= 5;
					} else if (e instanceof Droplet) {
						score += 10;
					} else if (e instanceof SmileDroplet) {
						lives ++;
						score += 20;
					}
					
					it.remove();
					nonActiveEntity.get(id).push(e);
				}
			}
		}

	}

	public void onDraw(Graphics g) {
		building.onDraw(g);
		for (int id = 0; id < activeEntity.size(); id++) {
			for (Entity e : activeEntity.get(id)) {
				e.onDraw(g);
			}
		}
		player.onDraw(g);
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
