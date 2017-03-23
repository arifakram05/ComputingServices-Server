package com.fdu.interfaces;

import com.mongodb.client.MongoDatabase;

public interface Connection {

	/**
	 * Establish connection with MongoDB
	 * @return {@link MongoDatabase} connection to the database
	 */
	MongoDatabase establishDBConnection();
	
	/**
	 * Close MongoDB database connection
	 */
	void closeDBConnection();
}
