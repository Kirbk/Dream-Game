package dream.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyLogger implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!GlobalVars.keys.contains(e.getKeyCode())) {
			GlobalVars.keys.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(GlobalVars.keys.contains(e.getKeyCode())) {
			GlobalVars.keys.remove((Object) e.getKeyCode());
		}
	}
}
