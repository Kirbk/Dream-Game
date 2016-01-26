package dream.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GUI {
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	
	public GUI() {
		
	}
	
	public void paint(Graphics2D g) {
		for(GUIElement element : elements) {
			element.paint(g);
		}
	}
	
	public void addElement(GUIElement gui) {
		elements.add(gui);
	}
	
	public void removeElement(GUIElement gui) {
		elements.remove(gui);
	}
	
	public void removeElement(int id) {
		elements.remove(id);
	}
	
	public ArrayList<GUIElement> getElements() {
		return elements;
	}
}
