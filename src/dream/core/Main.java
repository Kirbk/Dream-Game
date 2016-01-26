package dream.core;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import dream.entities.Entity;
import dream.entities.Player;
import dream.entities.TestEntity;

public class Main {
	
	public static void init() {
		GlobalVars.frame = new Frame(GlobalVars.name, GlobalVars.version);
		
		GlobalVars.entities.add(new Player(20, 20));
		
		for(int x = 0; x <= 100; x++) {
			GlobalVars.entities.add(new TestEntity());
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
					GlobalVars.scrollY += GlobalVars.movementSpeed;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_S)) {
					GlobalVars.scrollY -= GlobalVars.movementSpeed;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_A)) {
					GlobalVars.scrollX += GlobalVars.movementSpeed;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_D)) {
					GlobalVars.scrollX -= GlobalVars.movementSpeed;
				}
				
				if(GlobalVars.keys.contains(KeyEvent.VK_R)) {
					GlobalVars.resetEntities();
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