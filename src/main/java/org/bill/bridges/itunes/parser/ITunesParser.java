package org.bill.bridges.itunes.parser;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bill.bridges.itunes.cache.ITunesLibraryCache;
import org.bill.bridges.model.jaxb.Track;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ITunesParser {
	protected Log log = LogFactory.getLog(ITunesParser.class);
	
	private class Dict {
		public String key;
		public Dict parent;
	}
	
	private Stack<Dict> parentQueue = new Stack<Dict>();
	private Dict currentDict = null;
	private String currentKey = "";
	private String currentType = "";
	private Track currentTrack = new Track();
	
	
	public void parseLibrary(String libraryPath) {
		long start = System.currentTimeMillis();
		Document doc = loadLibrary(libraryPath);
		loadTracksInCache(doc);
		long end = System.currentTimeMillis();
		long total = end-start;
		log.debug("Loaded library (in milliseconds): " + total);
		System.out.println("Loaded library (in milliseconds): " + total);
	}
	
	protected Document loadLibrary(String libraryPath) {
		try {
			//create input stream for the library
			String libraryContents = IOUtils.toString(new FileReader(new File(libraryPath)));
			InputStream libraryStream = IOUtils.toInputStream(libraryContents, "UTF8");
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setValidating(true);
			docBuilderFactory.setNamespaceAware(true);
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			return docBuilder.parse(libraryStream);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void loadTracksInCache(Document doc) {
		Element root = doc.getDocumentElement();
		loadTracksInCache(root);
	}
	
	protected void loadTracksInCache(Element element) {
		NodeList children = element.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			boolean currentNodeDict = false;
			Node currentNode = children.item(i);
			if(currentNode.getNodeType() == Node.TEXT_NODE) {
				if(currentType.equals("key")) {
					currentKey = currentNode.getTextContent();
				}
				else {
					setTrackValue(currentNode.getTextContent());
				}
			}
			else {
				currentType = currentNode.getNodeName();
				if(currentType.equals("dict")) {
					currentNodeDict = true;
					currentDict = new Dict();
					
					//root dict
					if(currentKey.isEmpty()) {
						currentDict.key = "plist";
						currentDict.parent = null;
					}
					else {
						currentDict.key = currentKey;
						currentDict.parent = parentQueue.peek();
					}
					
					parentQueue.push(currentDict);
				}
				
				//recursion, dawg
				loadTracksInCache((Element) currentNode);
				
				if(currentNodeDict) {
					parentQueue.pop();
					if(currentDict.parent.key.equals("Tracks")) {
						ITunesLibraryCache.loadTrack(currentTrack);
						currentTrack = new Track();
					}
				}
			}
		}
	}
	
	protected void setTrackValue(String value) {
		if(currentDict.parent != null && currentDict.parent.key.equals("Tracks")) {
			if(currentKey.equals("Track ID"))  {
				currentTrack.setTrackId(value);
			}
			else if(currentKey.equals("Name")) {
				currentTrack.setName(value);
			}
			else if(currentKey.equals("Artist")) {
				currentTrack.setArtist(value);
			}
			else if(currentKey.equals("Album")) {
				currentTrack.setAlbum(value);
			}
			else if(currentKey.equals("Genre")) {
				currentTrack.setGenre(value);
			}
			else if(currentKey.equals("Total Time")) {
				currentTrack.setTotalTime(Integer.parseInt(value));
			}
			else if(currentKey.equals("Disc Number")) {
				currentTrack.setDiscNumber(Integer.parseInt(value));
			}
			else if(currentKey.equals("Disc Count")) {
				currentTrack.setDiscCount(Integer.parseInt(value));
			}
			else if(currentKey.equals("Track Number")) {
				currentTrack.setTrackNumber(Integer.parseInt(value));
			}
			else if(currentKey.equals("Track Count")) {
				currentTrack.setTrackCount(Integer.parseInt(value));
			}
			else if(currentKey.equals("Year")) {
				currentTrack.setYear(Integer.parseInt(value));
			}
			else if(currentKey.equals("Location")) {
				try {
					String location = URLDecoder.decode(value, "UTF-8");
					location = location.replace("file://localhost", "");
					currentTrack.setLocation(location);
				}
				catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
