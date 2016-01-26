package dream.core;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JFrame;

import dream.entities.Entity;
import dream.gui.GUI;
import dream.tiles.Tile;

public class GlobalVars {
	public static String name = "Dream Game";
	public static int version = 0;
	public static Logger logger;
	public static GameState currentGameState;
	public static float interpolation = 0.0f;
	public static int fps;
	public static int frameCount;
	public static int scale = 2;
	public static int scrollX = 0, scrollY = 0;
	public static final int movementSpeed = 5;
	
	public static void logger() {
		logger = Logger.getLogger(name);
		
		logger.setUseParentHandlers(false);
		LoggerFormatter formatter = new LoggerFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(formatter);
		
		logger.addHandler(handler);
	}
	
	public static JFrame frame;
	
	public static ArrayList<Integer> keys = new ArrayList<Integer>();
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static ArrayList<GUI> GUIs = new ArrayList<GUI>();
	
	
	public static void resetEntities() {
		entities = null;
		entities = new ArrayList<Entity>();
		Main.init();
	}
}

class LoggerFormatter extends Formatter {
	private static final DateFormat df = new SimpleDateFormat("hh:mm:ss");
	
	@Override
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder(10000);
		builder.append(df.format(new Date(record.getMillis()))).append(" : ");
        builder.append("[").append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append("]");
        builder.append("[").append(record.getLevel()).append("] ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
	}
	
    public String getHead(Handler h) {
        return super.getHead(h);
    }
 
    public String getTail(Handler h) {
        return super.getTail(h);
    }
}