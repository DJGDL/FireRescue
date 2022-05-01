package be.groep16.firerescue;

import java.awt.Graphics;

public interface Entity {
	void onUpdate(long deltaTime);

	void onDraw(Graphics g);
}
