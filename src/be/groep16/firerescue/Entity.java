package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity {
	void onUpdate(long deltaTime);
	void onDraw(Graphics g);
	
	Rectangle getBoundingBox();
	
	default void reset(float difficulty) {}
	default boolean isDead() { return false; }
}
