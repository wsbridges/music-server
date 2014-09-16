package org.bill.bridges.itunes.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bill.bridges.itunes.pojo.Track;

public class ITunesLibraryCache {
	private static Log log = LogFactory.getLog(ITunesLibraryCache.class);
	
	private static Map<String, Track> tracks = new HashMap<String, Track>();
	private static Map<String, List<String>> names = new HashMap<String, List<String>>();
	private static Map<String, List<String>> albums = new HashMap<String, List<String>>();
	private static Map<String, List<String>> artists = new HashMap<String, List<String>>();
	private static Map<String, List<String>> genres = new HashMap<String, List<String>>();
	private static Map<String, List<String>> years = new HashMap<String, List<String>>();
	
	public static void loadTrack(Track track) {
		if(track.getTrackId() == null) {
			//throw new RuntimeException("Track has no track id: " + track);
			return;
		}
		else if(tracks.containsKey(track.getTrackId())) {
			throw new RuntimeException("Track already exists with same id: " + track.getTrackId() 
					+ "\n Existing track: " + tracks.get(track.getTrackId())
					+ "\n Track being added: " + track);
		}
		
		tracks.put(track.getTrackId(), track);
		if(track.getName() != null) {
			addTrackToCategory(names, track.getName(), track);
		}
		if(track.getAlbum() != null) {
			addTrackToCategory(albums, track.getAlbum(), track);
		}
		if(track.getArtist() != null) {
			addTrackToCategory(artists, track.getArtist(), track);
		}
		if(track.getGenre() != null) {
			addTrackToCategory(genres, track.getGenre(), track);
		}
		if(track.getYear() != null) {
			addTrackToCategory(years, track.getYear().toString(), track);
		}
	}
	
	protected static void addTrackToCategory(Map<String, List<String>> categoryMap, String key, Track track) {
		if(!categoryMap.containsKey(key)) {
			categoryMap.put(key, new ArrayList<String>());
		}
		categoryMap.get(key).add(track.getTrackId());
	}
	
	public static Map<String, Track> getTracksCache() {
		return tracks;
	}
	
	public static Map<String, List<String>> getNamesCache() {
		return names;
	}
	
	public static Map<String, List<String>> getAlbumsCache() {
		return albums;
	}
	
	public static Map<String, List<String>> getArtistsCache() {
		return artists;
	}
	
	public static Map<String, List<String>> getGenreCache() {
		return genres;
	}
	
	public static Map<String, List<String>> getYearsCache() {
		return years;
	}
}

	