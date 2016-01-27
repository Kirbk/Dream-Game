package dream.levels;

import java.io.File;
import java.util.ArrayList;

import dream.core.GlobalVars;

public class LevelManager {
	public LevelManager() {
		
	}
	
	public void loadLevel(Level level) {
		GlobalVars.currentLevel = level;
	}
	
	public void createLevel(int id) {
		File folder = null;
		File[] list = null;
		
		try {
			folder = new File(GlobalVars.directory.getPath() + "/worlds/");
		}catch(Exception e) {
			GlobalVars.logger.log(java.util.logging.Level.SEVERE, "Error: Folder not found", e);
		}
		
		list = folder.listFiles();
		ArrayList<File> currentLeveltoCreate = new ArrayList<File>();
		
		for(File f : list) {
			String[] parts = f.getName().split("-");
			
			if(GlobalVars.getExtension(f).equals("level")) {
				if(parts[0].equals(String.valueOf(id))) {
					currentLeveltoCreate.add(f);
				}
			}
		}
		
		for(File f : currentLeveltoCreate) {
			
		}
		
		/**File[] list = null;
		
		try {
			File folder = new File(getClass().getResource("/levels/").toURI());
			list = folder.listFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(File f : list) {
			String[] parts = f.getName().split("-");
			
			if(GlobalVars.getExtension(f).equals("level")) {
				if(parts[0].equals(String.valueOf(id))) {
					
				}
			}
		}*/
	}
}
