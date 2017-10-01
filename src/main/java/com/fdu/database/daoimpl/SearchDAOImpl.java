package com.fdu.database.daoimpl;

import static com.mongodb.client.model.Filters.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.database.dao.SearchDAO;
import com.fdu.model.LabAssistant;
import com.fdu.model.User;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.TextSearchOptions;

public class SearchDAOImpl implements SearchDAO {

	private final static Logger LOGGER = Logger.getLogger(SearchDAOImpl.class);
	private MongoDatabase database;

	public SearchDAOImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public List<User> getUsers(String searchText) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.USERS.getValue());
		List<User> userList = new LinkedList<>();
		// process each retrieved record
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			User user;
			try {
				user = new ObjectMapper().readValue(retrivedDataAsJSON, User.class);
				userList.add(user);
			} catch (IOException e) {
				LOGGER.error("Error occurred while processing retrieved user details for search users operation");
			}
		};
		/*
		 * in order to do a text search, you must have already created an index
		 * on users collection, otherwise mongoDB throws error
		 */
		usersCollection.find(text(searchText, new TextSearchOptions().caseSensitive(false)))
				.forEach(processRetreivedData);
		return userList;
	}

	@Override
	public List<User> getLabAssistants(String searchText) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		List<User> labAssistants = new ArrayList<>();
		// process each retrieved record
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			User user;
			LabAssistant labAssistant;
			try {
				labAssistant = new ObjectMapper().readValue(retrivedDataAsJSON, LabAssistant.class);
				user = new User();
				user.setFirstName(labAssistant.getFirstName());
				user.setLastName(labAssistant.getLastName());
				user.setUserId(labAssistant.getStudentId());
				labAssistants.add(user);
			} catch (IOException e) {
				LOGGER.error("Error occurred while processing retrieved user details for search associates operation");
			}
		};
		/*
		 * in order to do a text search, you must have already created an index
		 * on associates collection, otherwise mongoDB throws error
		 */
		usersCollection.find(text(searchText, new TextSearchOptions().caseSensitive(false)))
				.forEach(processRetreivedData);
		return labAssistants;
	}

}
