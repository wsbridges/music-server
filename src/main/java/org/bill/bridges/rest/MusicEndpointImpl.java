package org.bill.bridges.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.bill.bridges.file.converter.BitRate;
import org.bill.bridges.file.converter.FileFormatConverter;
import org.bill.bridges.file.converter.OutputFormat;
import org.bill.bridges.itunes.cache.ITunesLibraryCache;
import org.bill.bridges.model.jaxb.Library;
import org.bill.bridges.model.jaxb.Track;

@Path("/music")
public class MusicEndpointImpl implements MusicEndpoint {
	@Override
	@GET
	@Path("/getSong")
	@Produces({MediaType.APPLICATION_OCTET_STREAM})
	public File getSong(@QueryParam("id")      String trackId, 
			            @QueryParam("artist")  String artist, 
			            @QueryParam("name")    String songName,
			            @QueryParam("format")  String format, 
			            @QueryParam("bitRate") String bitRate) {
		if(StringUtils.isBlank(trackId)) {
			throw new RuntimeException("TrackID (id) must be specified on the request URL");
		}
		if(StringUtils.isBlank(artist)) {
			throw new RuntimeException("Artist (artist) must be specified on the request URL");
		}
		if(StringUtils.isBlank(songName)) {
			throw new RuntimeException("Song Name (name) must be specified on the request URL");
		}
		if(StringUtils.isBlank(format)) {
			throw new RuntimeException("Output Format (format) must be specified on the request URL");
		}
		if(StringUtils.isBlank(bitRate)) {
			throw new RuntimeException("Bit Rate (bitRate) must be specified on the request URL");
		}
		
		Track theTrack = ITunesLibraryCache.getTracksCache().get(trackId);
		
		if(theTrack == null) {
			throw new RuntimeException("Invalid Track ID specified.");
		}
		if(!StringUtils.equals(theTrack.getArtist(), artist)) {
			throw new RuntimeException("The artist specified in the request [" + artist + "] does not match the artist on the server [" + theTrack.getArtist() + "]. If you believe this error is incorrect, try refreshing your library and try again.");
		}
		if(!StringUtils.equals(theTrack.getName(), songName)) {
			throw new RuntimeException("The song name specified in the request [" + songName + "] does not match the song name on the server [" + theTrack.getName() + "]. If you believe this error is incorrect, try refreshing your library and try again.");
		}
		
		BitRate eBitRate = null;
		try {
			eBitRate = BitRate.valueOf(bitRate);
		}
		catch(IllegalArgumentException e) {
			throw new RuntimeException("Invalid Bit Rate [" + bitRate + "] specified on the request URL", e);
		}
		
		OutputFormat eFormat = null;
		try {
			eFormat = OutputFormat.valueOf(format);
		}
		catch(IllegalArgumentException e) {
			throw new RuntimeException("Invalid Output Format [" + format + "] specified on the request URL", e);
		}
		
		
		String fileLocation = FileFormatConverter.convertFile(theTrack.getLocation(), eFormat, eBitRate);
		return new File(fileLocation);
	}

	@Override
	@GET
	@Path("/syncLibrary")
	@Produces({MediaType.APPLICATION_JSON})
	public Library syncLibrary(@QueryParam("md5")String libraryMD5) {
		Library lib = new Library();
		lib.getTracks().addAll(ITunesLibraryCache.getTracksCache().values());
		return lib;
	}

}
