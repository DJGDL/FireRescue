package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * @author rapha
 * Entity: Building, Fire, Droplet, GoldenDroplet, Rock and GreatRock
 *
 */
public interface Entity {
	/**
	 * Called for every frame update but only for state updates
	 * @param deltaTime Time between updates
	 */
	void onUpdate(long deltaTime);
	/**
	 * No state update only drawing
	 * @param g
	 */

	void onDraw(Graphics g);
	
	/**
	 * Bounding box for collision detection
	 * @return The bounding box
	 */
	Rectangle getBoundingBox();
	
	/**
	 * To reset every entity to begin state
	 * @param difficulty is the acceleration
	 */
	default void reset(float difficulty) { }

	/**
	 * Disappear from screen 
	 * @return Whether or not the entity is dead
	 */
	default boolean isDead() {
		return false;
	}
}
