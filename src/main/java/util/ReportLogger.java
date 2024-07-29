package util;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class ReportLogger {
	private static Logger logger = LogManager.getLogger(ReportLogger.class);
	private static ReportLogger reportLogger = new ReportLogger();

	public static ReportLogger getInstance() {
		return reportLogger;
	}

	static {
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		File file = new File("src\\test\\resources\\config\\log4j2.xml");
		context.setConfigLocation(file.toURI());
	}

	public static void setLoggerClassName(String classname) {
		logger = LogManager.getLogger(classname);
	}

	public void log(String str) {
		logger.info(str);
	}

	public void info(String str) {
		logger.info(str);
	}

	public void warn(String str) {
		logger.warn(str);
	}

	public void fatal(String str) {
		logger.fatal(str);
	}

	public void error(String str) {
		try {
			logger.error(str);
		} catch (Exception e) {
			logger.error("Exception occurred for error event : ", e);
		}
	}
}
