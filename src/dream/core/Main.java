package dream.core;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import dream.entities.Entity;
import dream.levels.LevelManager;
import dream.tiles.TileManager;

public class Main {
	
	public void init() {
		loadDirectory();
		
		GlobalVars.frame = new Frame(GlobalVars.name, GlobalVars.version);
		
		TileManager tm = new TileManager("Tilesheet.png");
		tm.seperate();
		
		LevelManager lm = new LevelManager();
		lm.createLevel(1);
	}
	
	private void loadDirectory() {
		String workingDirectory;
		
		if(GlobalVars.OS.contains("WIN")) {
			workingDirectory = System.getenv("AppData");
		}else {
			workingDirectory = System.getProperty("user.home");
			workingDirectory += "/Library/Application Support";
		}
		
		File resources = new File(workingDirectory + "/DreamGame/");
		if(!resources.exists()) {
			resources.mkdir();
			GlobalVars.directory = resources;
			copyWorldFiles();
		}
	}
	
	private void copyWorldFiles() {
		File f = new File(GlobalVars.directory + "/worlds/");
		f.mkdir();
		for(String s : GlobalVars.worldFiles) {
			InputStream input = ClassLoader.class.getResourceAsStream(s);
			FileOutputStream output = null;
			
			try {
				output = new FileOutputStream(GlobalVars.directory.toString() + "/" + s);
				byte[] buffer = new byte[2048];
				int r = input.read(buffer);
				while(r != -1) {
					output.write(buffer);
					r = input.read(buffer);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void gameloop() {
		final double GAME_HERTZ = 30.0;
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(true) {
			double now = System.nanoTime();
			int updateCount = 0;
			
			while(now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
				if(GlobalVars.keys.contains(KeyEvent.VK_W)) {
					GlobalVars.scrollY += GlobalVars.movementSpeed * GlobalVars.interpolation;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_S)) {
					GlobalVars.scrollY -= GlobalVars.movementSpeed * GlobalVars.interpolation;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_A)) {
					GlobalVars.scrollX += GlobalVars.movementSpeed * GlobalVars.interpolation;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_D)) {
					GlobalVars.scrollX -= GlobalVars.movementSpeed * GlobalVars.interpolation;
				}
				
				for(Entity e : GlobalVars.entities) {
					e.update();
				}
				
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}
			
			if(now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}
			
			GlobalVars.interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
			GlobalVars.frame.repaint();
			lastRenderTime = now;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime) {
				GlobalVars.fps = GlobalVars.frameCount;
				GlobalVars.frameCount = 0;
				lastSecondTime = thisSecond;
			}
			
			while(now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				
				try {Thread.sleep(1);} catch(Exception e) {} 
				
				now = System.nanoTime();
			}
		}
	}
	
	public Main() {
		GlobalVars.currentGameState = GameState.GAME;
		GlobalVars.logger();
		init();
		
		Thread gameloop = new Thread() {
			public void run() {
				gameloop();
			}
		};
		gameloop.start();
		
		GlobalVars.logger.log(Level.INFO, "Initiated Successfully");
	}
	
	public static void main(String args[]) {
		new Main();
	}
}