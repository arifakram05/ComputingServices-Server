package com.fdu.database.daoimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.database.dao.SettingsDAO;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

public class SettingsDAOImpl implements SettingsDAO {

	private final static Logger LOGGER = Logger.getLogger(SettingsDAOImpl.class);
	private MongoDatabase database;

	public SettingsDAOImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public Map<String, String> getSettings() {
		Map<String, String> settingsMap = new LinkedHashMap<>();
		// get collection
		MongoCollection<Document> settingsCollection = database.getCollection(Constants.SETTINGS.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {
			settingsMap.put(Constants.EMAIL.getValue(), document.getString(Constants.EMAIL.getValue()));
			settingsMap.put(Constants.SUBNETRANGE.getValue(), document.getString(Constants.SUBNETRANGE.getValue()));
		};
		// query
		settingsCollection.find().forEach(processRetreivedData);
		LOGGER.info("All settings fetched");
		return settingsMap;
	}

	@Override
	public boolean updateEmail(String email) {
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.SETTINGS.getValue());
		// query
		UpdateResult result = userCollection.updateOne(null, Updates.set(Constants.EMAIL.getValue(), email));
		LOGGER.info("Done updating FROM email address");
		return result.wasAcknowledged();
	}

	@Override
	public boolean changePassword(String oldPassword, String newPassword, String userId) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.USERS.getValue());
		// query
		Document document = usersCollection.findOneAndUpdate(
				Filters.and(Filters.eq(Constants.USERID.getValue(), userId),
						Filters.eq(Constants.PASSWORD.getValue(), oldPassword)),
				Updates.set(Constants.PASSWORD.getValue(), newPassword));
		LOGGER.info("Done changing password");
		if (document != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean resetPassword(String userId) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.USERS.getValue());
		// query
		Document document = usersCollection.findOneAndUpdate(Filters.eq(Constants.USERID.getValue(), userId),
				Updates.set(Constants.PASSWORD.getValue(), null));
		LOGGER.info("Done resetting password");
		if (document != null) {
			return true;
		} else {
			return false;
		}
	}

}
