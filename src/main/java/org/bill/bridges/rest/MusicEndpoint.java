package org.bill.bridges.rest;

import java.io.File;

import org.bill.bridges.model.jaxb.Library;


public interface MusicEndpoint {

	public File getSong(String trackId, 
			            String artist, 
			            String songName, 
			            String format, 
			            String bitRate);
	
	public Library syncLibrary(String libraryMD5);
}
