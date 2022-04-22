package be.groep16.firerescue;

import java.awt.Component;

public interface Entity {
	void onUpdate(long deltaTime);
	Component getComponent();
	Vector getPosition();
	
}
