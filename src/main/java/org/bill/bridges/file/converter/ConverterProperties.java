package org.bill.bridges.file.converter;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConverterProperties {
	private static ConverterProperties instance = null;
	private static final String propertiesFileName = "converter.properties";
	
	//Properties keys
	private static final String OUTPUT_DIR="output.dir";
	private static final String FFMPEG_PATH="ffmpeg.path";
	
	Configuration converterConfiguration = null;
	
	
	private ConverterProperties() {
		try {
			converterConfiguration = new PropertiesConfiguration(propertiesFileName);
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ConverterProperties getInstance() {
		if(instance == null) {
			instance = new ConverterProperties();
		}
		
		return instance;
	}
	
	public String getOutputDir() {
		return converterConfiguration.getString(OUTPUT_DIR);
	}
	
	public String getFfmpegPath() {
		return converterConfiguration.getString(FFMPEG_PATH);
	}

}
