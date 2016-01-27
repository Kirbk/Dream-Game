package dream.levels;

import java.io.File;
import java.net.URISyntaxException;

import dream.core.GlobalVars;

public class LevelManager {
	public LevelManager() {
		
	}
	
	public void loadLevel(Level level) {
		GlobalVars.currentLevel = level;
	}
	
	public void createLevel(int id) {
		File[] list = null;
		
		try {
			File folder = new File(getClass().getResource("/levels/").toURI());
			list = folder.listFiles();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		for(File f : list) {
			String[] parts = f.getName().split("-");
			
			if(GlobalVars.getExtension(f).equals("level")) {
				if(parts[0].equals(String.valueOf(id))) {
					
				}
			}
		}
	}
}
