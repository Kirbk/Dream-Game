package dream.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import dream.core.GlobalVars;
import dream.core.Vector2D;

public class Entity {
	public int health, mana;
	protected Vector2D position = new Vector2D(0, 0);
	protected int width = 16, height = 16;
	private BufferedImage texture;
	
	public Entity(int heatlh, int mana) {
		
	}
	
	public void loadTexture(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("/textures/entities/" + path));
		}catch(Exception e) {
			GlobalVars.logger.log(Level.WARNING, "Error: Missing Texture", e);
			try {
				img = ImageIO.read(getClass().getResource("/textures/missing.png"));
			} catch (Exception e1) {
				GlobalVars.logger.log(Level.SEVERE, "Error: Missing Texture Missing, How?");
			}
		}
		
		this.texture = img;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public void setPosition(Vector2D pos) {
		this.position = pos;
	}
	
	public void setPostion(int x, int y) {
		this.position = new Vector2D(x, y);
	}
	
	public void update() {
		
	}
	
	public void paint(Graphics2D g) {
		if(this.getTexture() != null) {
			g.drawImage(this.getTexture(), getPosition().x + GlobalVars.scrollX, getPosition().y + GlobalVars.scrollY, width * GlobalVars.scale, height * GlobalVars.scale, null);
		}
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
