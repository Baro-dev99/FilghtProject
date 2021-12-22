package com.example.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	private static FileHandler fileHandler;
	private static final Logger LOGGER = Logger.getLogger("MyLog");
	
	public static void log(Level level, String msg) {
		try {
			// only write in file
			LOGGER.setUseParentHandlers(false);
			
			// This block configure the logger with handler and formatter
			fileHandler = new FileHandler("logs/MyLog.log", true); 
			LOGGER.addHandler(fileHandler);
			
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);

			// the following statement is used to log any messages
			LOGGER.log(level, msg);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
