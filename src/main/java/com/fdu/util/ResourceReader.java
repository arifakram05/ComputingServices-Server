package com.fdu.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * Reads properties file to get db configuration
 * 
 * @author arifakrammohammed
 *
 */
public class ResourceReader {

	private final static Logger LOGGER = Logger.getLogger(ResourceReader.class);
	public static ConcurrentHashMap<String, String> projectProperties = new ConcurrentHashMap<>();

	/**
	 * Locates the properties file and reads them.
	 */
	public void readProperties() {

		if (!projectProperties.isEmpty()) {
			return;
		}

		Properties prop = new Properties();
		InputStream inputStream = null;

		try {

			String filename = "app.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(filename);

			if (inputStream == null) {
				throw new FileNotFoundException("Property file '" + filename + "' cannot be found in the classpath");
			}

			// load the properties from the file
			prop.load(inputStream);

			// put all the properties in a map
			projectProperties.put("dbAddress", prop.getProperty("dbAddress"));
			projectProperties.put("dbPort", prop.getProperty("dbPort"));
			projectProperties.put("dbUser", prop.getProperty("dbUser"));
			projectProperties.put("dbPassword", prop.getProperty("dbPassword"));
			projectProperties.put("dbName", prop.getProperty("dbName"));

		} catch (IOException e) {
			LOGGER.error("Error while reading properties file", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.error("Error while attempting to close InputStream", e);
				}
			}
		}
	}

}
