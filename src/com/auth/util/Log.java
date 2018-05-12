package com.auth.util;

import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.auth.util.ESIGNProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 */
public class Log {

	/** The subaua. */
	public static Logger subaua = Logger.getLogger("SUBAUA");

	static {
		FileAppender appender1 = null;
		String propFilePath = "";
		ESIGNProperties.load(propFilePath);
		String log_path = ESIGNProperties.getLogPath();

		try {
			appender1 = new DailyRollingFileAppender(new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} %C %L %-5p: %m%n"),
					log_path, "'.'dd-MM-yyyy");
		} catch (IOException e) {
			e.printStackTrace();
		}
		subaua.setLevel(Level.DEBUG);
		subaua.addAppender(appender1);
	}
}
