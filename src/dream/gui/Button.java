package dream.gui;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Button extends GUIElement {
	private int x, y, width, height;
	private String text;
	private BufferedImage TEX_NORMAL, TEX_ACTIVE;
	
	private Rectangle rec;
	
	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		
		this.rec = new Rectangle(x, y, width, height);
		
		try {
			this.TEX_ACTIVE = ImageIO.read(getClass().getResource("/textures/missing.png"));
			this.TEX_NORMAL = ImageIO.read(getClass().getResource("/textures/missing.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics2D g) {
		if(rec.contains(MouseInfo.getPointerInfo().getLocation())) {
			g.drawImage(TEX_ACTIVE, x, y, width, height, null);
		}else {
			g.drawImage(TEX_NORMAL, x, y, width, height, null);
		}
	}
	
	public void setActiveTexture(BufferedImage texture) {
		TEX_ACTIVE = texture;
	}
	
	public void setNormalTexture(BufferedImage texture) {
		TEX_NORMAL = texture;
	}
}
