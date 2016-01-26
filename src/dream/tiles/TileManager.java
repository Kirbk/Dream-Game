package dream.tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import dream.core.GlobalVars;

public class TileManager {
	BufferedImage tilesheet;
	ArrayList<BufferedImage> textures = new ArrayList<BufferedImage>();
	
	public TileManager(String tilesheet) {
		loadTileSet(tilesheet);
	}
	
	public void loadTileSet(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("/textures/tiles/" + path));
		}catch(Exception e) {
			GlobalVars.logger.log(Level.WARNING, "Error: Missing Tilesheet", e);
		}
		
		this.tilesheet = img;
	}
	
	public void seperate() {
		if((tilesheet.getWidth() % 16) > 0) {
			GlobalVars.logger.log(Level.WARNING, "Tilesheet width not divisible by 16");
		}
		if((tilesheet.getHeight() % 16) > 0) {
			GlobalVars.logger.log(Level.WARNING, "Tilesheet height not divisible by 16");
		}
		
		for(int y = 0; y < tilesheet.getHeight() / 16; y++) {
			for(int x = 0; x < tilesheet.getWidth() / 16; x++) {
				textures.add(tilesheet.getSubimage(x * 16, y * 16, 16, 16));
			}
		}
	}
	
	public void forceID(String texture, int ID) {
		
	}
	
	public void switchID(int id1, int id2) {
		
	}
}