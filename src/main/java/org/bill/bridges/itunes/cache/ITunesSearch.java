package org.bill.bridges.itunes.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bill.bridges.itunes.pojo.Track;

public class ITunesSearch {
	protected static Log log = LogFactory.getLog(ITunesSearch.class);
	
	public static final String GENRE="Genre";
	public static final String ARTIST="Artist";
	public static final String ALBUM="Album";
	public static final String NAME="Name";
	public static final String YEAR="Year";
	
	public static Map<String, Set<Track>> search(String searchString) {
		return search(searchString, true, true, true, true, true);
	}
	
	public static Map<String, Set<Track>> search(String searchString, boolean searchNames, boolean searchArtists,
			boolean searchAlbums, boolean searchGenres, boolean searchYears) {
		long start = System.currentTimeMillis();
		Map<String, Set<Track>> searchResults = new HashMap<String, Set<Track>>();
		
		if(searchNames) {
			searchResults.put(NAME, searchNames(searchString));
		}
		
		if(searchArtists) {
			searchResults.put(ARTIST, searchArtists(searchString));
		}
		
		if(searchAlbums) {
			searchResults.put(ALBUM, searchAlbums(searchString));
		}
		
		if(searchGenres) {
			searchResults.put(GENRE, searchGenres(searchString));
		}
		
		if(searchYears) {
			searchResults.put(YEAR, searchYears(searchString));
		}
		
		long end = System.currentTimeMillis();
		long total = end - start;

		log.debug("Searched for '" + searchString + "' (in milliseconds): " + total);
		System.out.println("Searched for '" + searchString + "' (in milliseconds): " + total);
		
		return searchResults;
	}
	
	protected static Set<Track> searchNames(String searchString) {
		return searchMapForTracks(searchString, ITunesLibraryCache.getNamesCache());
	}
	
	protected static Set<Track> searchArtists(String searchString) {
		return searchMapForTracks(searchString, ITunesLibraryCache.getArtistsCache());
	}
	
	protected static Set<Track> searchAlbums(String searchString) {
		return searchMapForTracks(searchString, ITunesLibraryCache.getAlbumsCache());
	}
	
	protected static Set<Track> searchGenres(String searchString) {
		return searchMapForTracks(searchString, ITunesLibraryCache.getGenreCache());
	}
	
	protected static Set<Track> searchYears(String searchString) {
		return searchMapForTracks(searchString, ITunesLibraryCache.getYearsCache());
	}
	
	protected static Set<Track> searchMapForTracks(String searchString, Map<String, List<String>> searchMap) {
		Set<String> trackIds = new HashSet<String>();
		searchString = searchString.trim();
		for(String key : searchMap.keySet()) {
			if(StringUtils.containsIgnoreCase(key, searchString)) {
				trackIds.addAll(searchMap.get(key));
			}
		}
		return trackIdsToTracks(trackIds);
	}
	
	protected static Set<Track> trackIdsToTracks(Set<String> trackIds) {
		Set<Track> tracks = new HashSet<Track>();
		for(String trackId : trackIds) {
			tracks.add(ITunesLibraryCache.getTracksCache().get(trackId));
		}
		
		return tracks;
	}
}
