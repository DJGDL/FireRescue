package be.groep16.firerescue;

import java.awt.event.KeyListener;

public interface Menu extends KeyListener, Entity {
	/**
	 * If menu dead give the next menu.
	 * @return
	 */
	NextMenu nextMenu();
}
