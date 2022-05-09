package be.groep16.firerescue;

import java.awt.event.KeyListener;

public interface Menu extends KeyListener, Entity {
	 NextMenu nextMenu();
}
