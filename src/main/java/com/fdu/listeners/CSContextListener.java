package com.fdu.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fdu.database.DBConnection;
import com.fdu.util.ResourceReader;
import com.mongodb.MongoClient;

public class CSContextListener implements ServletContextListener {

	MongoClient mongoClient = null;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Read project properties file
		ResourceReader resouceReader = new ResourceReader();
		resouceReader.readProperties();
		// open MongoDB connection
		mongoClient = DBConnection.establishConnection();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// close MongoDB connection
		mongoClient.close();
	}

}
