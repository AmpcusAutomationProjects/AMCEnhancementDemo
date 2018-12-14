package com.environmentalVariables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;

 
public class PropertiesManager {
	
	
	private String propertiesFile;
	private Properties prop;
	private OutputStream output;
	private InputStream input;

	public PropertiesManager(String propertiesFilePath) {
		propertiesFile = propertiesFilePath;
		prop = new Properties();
	}

	public String readProperty(String key) {
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public void setProperty(String key, String value) {
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		BasicConfigurator.configure();
		PropertiesManager propManager = new PropertiesManager("src/test/resources/config.properties");
		propManager.setProperty("browserType", "IE");
	}
	
	 

}



