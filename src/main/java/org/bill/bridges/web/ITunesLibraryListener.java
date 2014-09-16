package org.bill.bridges.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.bill.bridges.itunes.parser.ITunesParser;
import org.bill.bridges.itunes.properties.ITunesProperties;

public class ITunesLibraryListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//do nothing
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//Load the ITunes Library into memory
		ITunesParser parser = new ITunesParser();
		parser.parseLibrary(ITunesProperties.getInstance().getITunesLibraryPath());
	}

}
