package org.bill.bridges.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.bill.bridges.file.converter.BitRate;
import org.bill.bridges.file.converter.OutputFormat;


public interface MusicEndpoint {

	public File getSong(String trackId, 
			            String artist, 
			            String songName, 
			            String format, 
			            String bitRate);
}
