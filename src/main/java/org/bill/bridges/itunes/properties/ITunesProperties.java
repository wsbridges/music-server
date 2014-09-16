package org.bill.bridges.itunes.properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class ITunesProperties {
	private static ITunesProperties instance = null;
	private static final String propertiesFileName = "itunes.properties";
	
	//Properties keys
	private static final String PROPERTY_LIBRARY_PATH="library.path";
	
	Configuration iTunesConfiguration = null;
	
	
	private ITunesProperties() {
		try {
			iTunesConfiguration = new PropertiesConfiguration(propertiesFileName);
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ITunesProperties getInstance() {
		if(instance == null) {
			instance = new ITunesProperties();
		}
		
		return instance;
	}
	
	public String getITunesLibraryPath() {
		return iTunesConfiguration.getString(PROPERTY_LIBRARY_PATH);
	}
	
}
