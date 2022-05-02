package be.groep16.firerescue;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GameManager {
	private static final int ROCK_ID = 0;
	private static final int FIRE_ID = 1;
	private static final int DROPLET_ID = 2;
	private static final int SMILE_DROPLET_ID = 3;
	
	private ArrayList<ArrayList<Entity>> activeEntity;
	private ArrayList<Stack<Entity>> nonActiveEntity;
	
	

	private int object;
	
	
	private Entity getNewEntity(int id) {
		if (nonActiveEntity.get(id).size() > 0) {
			Entity entity = nonActiveEntity.get(id).pop();
			entity.reset();
			
			activeEntity.get(id).add(entity);
			
			return entity;
		} else {
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
		activeEntity = new ArrayList<>();
		nonActiveEntity = new ArrayList<>();
		
		for (int id = 0; id < 4; id++) {
			activeEntity.set(id, new ArrayList<>());
			nonActiveEntity.set(id, new Stack<>());
		}
	}
	
	public void onUpdate(long deltaTime) {
		Random rand = new Random();
		object = rand.nextInt()%10;
		if (object < 5)
			getNewEntity(ROCK_ID);
		if (object >= 5 && object < 7)
			getNewEntity(FIRE_ID);
		if (object >= 7 && object <9)
			getNewEntity(DROPLET_ID);
		if (object >= 9)
			getNewEntity(SMILE_DROPLET_ID);
		
		for (int id = 0; id < activeEntity.size(); id++) {
			for (Entity e: activeEntity.get(id)) {
				e.onUpdate(deltaTime);
				
				if (e.isDead()) {
					activeEntity.get(id).remove(e);		
				}
			}
		}
		
	}
	
	public void onDraw(Graphics g) {
		for (int id = 0; id < activeEntity.size(); id++) {
			for (Entity e: activeEntity.get(id)) {
				e.onDraw(g);
			}
		}
	}
}
