package com.fdu.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fdu.database.DBConnection;
import com.fdu.util.EmailWorker;
import com.fdu.util.ResourceReader;
import com.mongodb.MongoClient;

public class CSContextListener implements ServletContextListener {

	private MongoClient mongoClient = null;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Read project properties file
		ResourceReader resouceReader = new ResourceReader();
		resouceReader.readProperties();
		// open MongoDB connection
		mongoClient = DBConnection.establishConnection();
		// create thread pool that sends email
		EmailWorker.setupEmailWorker();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// close MongoDB connection
		mongoClient.close();
		// shutdown thread pool
		EmailWorker.shutdown();
	}

}
