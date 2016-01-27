package dream.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dream.entities.Entity;
import dream.gui.GUI;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Frame(String name, int version) {
		super(name);
		this.setContentPane(new Panel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		this.setSize(new Dimension(800, 600));
		this.addKeyListener(new KeyLogger());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(0.392f, 0.584f, 0.929f));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(GlobalVars.currentLevel != null) {
			
		}
		
		for(Entity e : GlobalVars.entities) {
			e.paint(g2);
		}
		
		for(GUI gui : GlobalVars.GUIs) {
			gui.paint(g2);
		}
		
		g2.setColor(Color.BLACK);
		g2.drawString("FPS: " + GlobalVars.fps, 0, 10);
		g2.drawString("Location: (" + ((double) GlobalVars.scrollX) / (16 * GlobalVars.scale) + ", " + ((double) GlobalVars.scrollY) / (16 * GlobalVars.scale) + ")", 0, 20);
		
		GlobalVars.frameCount++;
	}
}
