package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class EnvPropertiesReader {
	public static ReportLogger reportLogger = ReportLogger.getInstance();
	private static ThreadLocal<EnvPropertiesReader> envPropertiesThreadLocal = new ThreadLocal<EnvPropertiesReader>();

	static {
		String path = "src/test/resources/config/ecubeConfig.properties";
		InputStream inputStream = null;
		try {
			File file = new File(path);
			inputStream = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(inputStream);
			reportLogger.info("Loaded config file " + path);
			if (!properties.isEmpty())
				for (Object key : properties.keySet())
					System.setProperty(key.toString(), properties.getProperty(key.toString()));
		} catch (Exception ex) {
			System.out.println("Loaded config file exception: " + ex);
			reportLogger.info("Properties file not found - config.properties " + ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	private EnvPropertiesReader() {
	}

	public static EnvPropertiesReader getInstance() {
		EnvPropertiesReader instance = envPropertiesThreadLocal.get();
		if (instance == null) {
			instance = new EnvPropertiesReader();
			envPropertiesThreadLocal.set(instance);
		}
		return instance;
	}
}
