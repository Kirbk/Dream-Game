package dream.gui;

import java.awt.Graphics2D;

import dream.core.GameState;
import dream.core.GlobalVars;

public class MainMenu extends GUI {
	public MainMenu() {
		this.addElement(new Button(400, 400, 100, 100, "START"));
	}
	
	@Override
	public void paint(Graphics2D g) {
		if(GlobalVars.currentGameState.equals(GameState.MAIN_MENU)) {
			for(GUIElement element : getElements()) {
				element.paint(g);
			}
		}
	}
}
