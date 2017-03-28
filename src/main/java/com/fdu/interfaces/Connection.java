package com.fdu.interfaces;

import com.fdu.database.DBConnection;
import com.mongodb.client.MongoDatabase;

/**
 * Return MongoDB connection
 * 
 * @author arifakrammohammed
 *
 */
public interface Connection {

	/**
	 * Get a connection with database; MongoDB internally handles Connection
	 * Pooling
	 * 
	 * @return a <i>MongoDatabase</i> to interact with
	 */
	default MongoDatabase getDBConnection() {
		return DBConnection.getConnection();
	}

}
