package org.bill.bridges.itunes;

import java.util.Map;
import java.util.Set;

import org.bill.bridges.itunes.cache.ITunesSearch;
import org.bill.bridges.itunes.parser.ITunesParser;
import org.bill.bridges.itunes.pojo.Track;
import org.bill.bridges.itunes.properties.ITunesProperties;

public class ITunesDriver {
	public static void main(String...args) {
		ITunesParser parser = new ITunesParser();
		parser.parseLibrary(ITunesProperties.getInstance().getITunesLibraryPath());
		
		Map<String, Set<Track>> searchResults = ITunesSearch.search("black");
		
		/*for(String key : searchResults.keySet()) {
			System.out.println("-------------------");
			System.out.println(" " + key + ":");
			System.out.println("-------------------");
			System.out.println();
			for(Track track : searchResults.get(key)) {
				System.out.println(track);
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}*/
	}
}
