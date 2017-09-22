package com.fdu.database;

import com.fdu.util.ResourceReader;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Provides database connection
 * 
 * @author arifakrammohammed
 *
 */
public final class DBConnection {

	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;

	private DBConnection() {

	}

	public static MongoClient establishConnection() {
		if (mongoClient == null || mongoDatabase == null) {
			// get database properties
			String dbLocation = ResourceReader.projectProperties.get("dbAddress");
			int dbPort = Integer.parseInt(ResourceReader.projectProperties.get("dbPort"));
			String dbName = ResourceReader.projectProperties.get("dbName");

			// establish connection
			mongoClient = new MongoClient(dbLocation, dbPort);
			// If database doesn't exists, MongoDB will create it
			mongoDatabase = mongoClient.getDatabase(dbName);
		}
		return mongoClient;
	}

	/**
	 * Get a connection with database; MongoDB internally handles Connection
	 * Pooling
	 * 
	 * @return a <i>MongoDatabase</i> to interact with
	 */
	public static MongoDatabase getConnection() {
		return mongoDatabase;
	}

}
