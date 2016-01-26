package dream.entities;

import java.awt.Graphics2D;

import dream.core.GlobalVars;

public class Player extends Entity {
	
	public Player(int health, int mana) {
		super(health, mana);
		
		this.health = health;
		this.mana = mana;
		
		this.loadTexture("player.png");
	}
	
	@Override
	public void paint(Graphics2D g) {
		if(this.getTexture() != null) {
			g.drawImage(this.getTexture(), GlobalVars.frame.getWidth() / 2 - (8 * GlobalVars.scale), GlobalVars.frame.getHeight() / 2 - (8 * GlobalVars.scale), width * GlobalVars.scale, height * GlobalVars.scale, null);
		}
	}
}