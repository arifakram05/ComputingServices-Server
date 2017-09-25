package com.fdu.database.daoimpl;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.database.dao.UserDAO;
import com.fdu.impl.RoleServiceImpl;
import com.fdu.model.User;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class UserDAOImpl implements UserDAO {

	private final static Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);
	private MongoDatabase database;

	public UserDAOImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public List<User> get() {
		List<User> users = new ArrayList<>();
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {
			String retrivedDataAsJSON = document.toJson();
			User user;
			try {
				user = new ObjectMapper().readValue(retrivedDataAsJSON, User.class);
				users.add(user);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved users ", e);
			}
		};
		// query
		userCollection.find().forEach(processRetreivedData);
		LOGGER.info("All users fetched");
		return users;
	}

	@Override
	public boolean blockUser(String id) {
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		// query
		UpdateResult result = userCollection.updateOne(eq(Constants.OBJECTID.getValue(), new ObjectId(id)),
				Updates.set(Constants.BLOCKED.getValue(), true));
		return result.wasAcknowledged();
	}

	@Override
	public boolean unblockUser(String id) {
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		// query
		UpdateResult result = userCollection.updateOne(eq(Constants.OBJECTID.getValue(), new ObjectId(id)),
				Updates.set(Constants.BLOCKED.getValue(), false));
		return result.wasAcknowledged();
	}

	@Override
	public boolean deleteUser(String id) {
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		// query
		DeleteResult result = userCollection.deleteOne(eq(Constants.OBJECTID.getValue(), new ObjectId(id)));
		return result.wasAcknowledged();
	}

	@Override
	public void addUser(User user) {
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		// query
		Document document = new Document();
		document.append(Constants.USERID.getValue(), user.getUserId())
				.append(Constants.FIRSTNAME.getValue(), user.getFirstName())
				.append(Constants.LASTNAME.getValue(), user.getLastName())
				.append(Constants.ROLE.getValue(), user.getRole())
				.append(Constants.PASSWORD.getValue(), null)
				.append(Constants.BLOCKED.getValue(), false);
		userCollection.insertOne(document);
	}

}
