package com.fdu.dbimpl;

import com.fdu.interfaces.Connection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Used to <br/>1. establish connection with MongoDB<br/>
 * 2. close Mongodb connection<br/><br/>
 * There will only be one instance of {@link DBConnection} and it is inherently
 * thread-safe.
 * 
 * @author arifakrammohammed
 *
 */
public enum DBConnection implements Connection {

	INSTANCE {
		MongoClient mongoClient;
		MongoDatabase mongoDatabase;

		@Override
		public MongoDatabase establishDBConnection() {
			// connect to MongoDB
			if (mongoClient == null || mongoDatabase == null) {
				mongoClient = new MongoClient("localhost", 27017);
				// If database doesn't exists, MongoDB will create it
				mongoDatabase = mongoClient.getDatabase("computingservices");
			}
			return mongoDatabase;
		}

		@Override
		public void closeDBConnection() {
			// close the DB connection
			if (mongoClient != null) {
				mongoClient.close();
			}
		}
	};

}
