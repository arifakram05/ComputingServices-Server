package com.fdu.database;

import com.fdu.util.ResourceReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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
			int dbPort = Integer.parseInt(ResourceReader.projectProperties.get("dbPort"));
			String dbAddress = ResourceReader.projectProperties.get("dbAddress");
			String dbName = ResourceReader.projectProperties.get("dbName");
			String dbUser = ResourceReader.projectProperties.get("dbUser");
			String dbPassword = ResourceReader.projectProperties.get("dbPassword");

			StringBuilder mongoURI = new StringBuilder("mongodb://").append(dbUser).append(":").append(dbPassword)
					.append("@").append(dbAddress).append(":").append(dbPort).append("/").append(dbName);

			/*
			 * mongodb://arif:unknownzone@ds141514.mlab.com:41514/marifakram
			 */

			// establish connection
			mongoClient = new MongoClient(new MongoClientURI(mongoURI.toString()));
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
