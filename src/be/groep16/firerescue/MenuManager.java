package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class MenuManager implements Menu {
	
	private Menu activeMenu;
	private final GameManager gameManager;
	private final StartMenu startMenu;
	
	public MenuManager() {
		gameManager = new GameManager();
		startMenu = new StartMenu();
		
		activeMenu = startMenu;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		activeMenu.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		activeMenu.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		activeMenu.keyReleased(e);
	}

	@Override
	public void onUpdate(long deltaTime) {
		activeMenu.onUpdate(deltaTime);
		
		if (activeMenu.isDead()) {
			activeMenu = switch (activeMenu.nextMenu()) {
				case GAME_MANAGER -> gameManager;
				case START_MENU -> startMenu;
				default -> throw new IllegalStateException();				
			};
			activeMenu.reset(0);
		}
	}

	@Override
	public void onDraw(Graphics g) {
		activeMenu.onDraw(g);
	}

	@Override
	public Rectangle getBoundingBox() {
		return null;
	}

	@Override
	public NextMenu nextMenu() {
		return null;
	}

}
