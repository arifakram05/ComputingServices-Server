package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.RoleService;
import com.fdu.model.Privilege;
import com.fdu.model.Role;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class RoleServiceImpl implements RoleService {

	private final static Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);

	MongoDatabase database;

	public RoleServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void save(Role role) throws ComputingServicesException {
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.ROLES.getValue());

		// processing available privileges
		List<Object> availablePrivsList = new BasicDBList();
		for (Privilege privilege : role.getAvailablePrivs()) {
			DBObject privilegeDBObject = new BasicDBObject();
			privilegeDBObject.put(Constants.NAME.getValue(), privilege.getName());
			privilegeDBObject.put(Constants.DESCRIPTION.getValue(), privilege.getDescription());
			availablePrivsList.add(privilegeDBObject);
		}

		// processing assigned privileges
		List<Object> assignedPrivsList = new BasicDBList();
		for (Privilege privilege : role.getAssignedPrivs()) {
			DBObject privilegeDBObject = new BasicDBObject();
			privilegeDBObject.put(Constants.NAME.getValue(), privilege.getName());
			privilegeDBObject.put(Constants.DESCRIPTION.getValue(), privilege.getDescription());
			assignedPrivsList.add(privilegeDBObject);
		}

		// construct object to persist
		BasicDBObject rolesObject = new BasicDBObject();
		rolesObject.put(Constants.ROLENAME.getValue(), role.getRoleName());
		rolesObject.put(Constants.AVAILABLEPRIVS.getValue(), availablePrivsList);
		rolesObject.put(Constants.ASSIGNEDPRIVS.getValue(), assignedPrivsList);

		// query
		rolesCollection.insertOne(new Document(rolesObject));
	}

	@Override
	public void update(Role role, String originalRoleName) {
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.ROLES.getValue());

		// processing available privileges
		List<Object> availablePrivsList = new BasicDBList();
		for (Privilege privilege : role.getAvailablePrivs()) {
			DBObject privilegeDBObject = new BasicDBObject();
			privilegeDBObject.put(Constants.NAME.getValue(), privilege.getName());
			privilegeDBObject.put(Constants.DESCRIPTION.getValue(), privilege.getDescription());
			availablePrivsList.add(privilegeDBObject);
		}

		// processing assigned privileges
		List<Object> assignedPrivsList = new BasicDBList();
		for (Privilege privilege : role.getAssignedPrivs()) {
			DBObject privilegeDBObject = new BasicDBObject();
			privilegeDBObject.put(Constants.NAME.getValue(), privilege.getName());
			privilegeDBObject.put(Constants.DESCRIPTION.getValue(), privilege.getDescription());
			assignedPrivsList.add(privilegeDBObject);
		}

		// construct object to persist
		BasicDBObject rolesObject = new BasicDBObject();
		rolesObject.put(Constants.ROLENAME.getValue(), role.getRoleName());
		rolesObject.put(Constants.AVAILABLEPRIVS.getValue(), availablePrivsList);
		rolesObject.put(Constants.ASSIGNEDPRIVS.getValue(), assignedPrivsList);

		// command
		Document command = new Document();
		command.put("$set", rolesObject);

		// query to update
		UpdateResult updateResult = rolesCollection
				.updateOne(eq(Constants.OBJECTID.getValue(), new ObjectId(role.get_id().toString())), command);
		if (updateResult.getModifiedCount() > 0 && !originalRoleName.equals(role.getRoleName())) {
			updateUserRole(originalRoleName, role.getRoleName());
		}
		LOGGER.info("Updated roles and privilges for the role - " + role.getRoleName());
	}

	public void updateUserRole(String oldRoleName, String newRoleName) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.USERS.getValue());
		// query
		usersCollection.updateMany(eq(Constants.ROLE.getValue(), oldRoleName),
				Updates.set(Constants.ROLE.getValue(), newRoleName));
		LOGGER.info("Updated USERS collection with new role name - " + newRoleName);
	}

	@Override
	public List<Role> getRoles() {
		List<Role> rolesList = new ArrayList<>();
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.ROLES.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			Role role;
			try {
				role = new ObjectMapper().readValue(retrivedDataAsJSON, Role.class);
				rolesList.add(role);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved roles ", e);
			}
		};
		// query
		rolesCollection.find().forEach(processRetreivedData);
		LOGGER.info("All roles fetched");
		return rolesList;
	}

	@Override
	public List<Role> getRoleNames() {
		List<Role> rolesList = new ArrayList<>();
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.ROLES.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			Role role;
			try {
				role = new ObjectMapper().readValue(retrivedDataAsJSON, Role.class);
				rolesList.add(role);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved role names ", e);
			}
		};
		// query
		rolesCollection.find().projection(Projections.fields(Projections.include(Constants.ROLENAME.getValue())))
				.forEach(processRetreivedData);
		LOGGER.info("All role names fetched");
		return rolesList;
	}

	@Override
	public boolean deleteRole(String roleId) {
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.ROLES.getValue());
		// query
		DeleteResult result = rolesCollection.deleteOne(eq(Constants.OBJECTID.getValue(), new ObjectId(roleId)));
		LOGGER.info("Deleted role with ID " + roleId);
		return result.wasAcknowledged();
	}

	@Override
	public List<String> getAssignedPrivileges(String roleName) {
		final List<String> rolesList = new LinkedList<>();
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.ROLES.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			Role role;
			try {
				role = new ObjectMapper().readValue(retrivedDataAsJSON, Role.class);
				rolesList.addAll(role.getAssignedPrivs().stream().map(priv -> priv.getName()).collect(Collectors.toList()));
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved role names ", e);
			}
		};
		// query
		rolesCollection.find(Filters.eq(Constants.ROLENAME.getValue(), roleName)).projection(Projections.fields(Projections.include(Constants.ASSIGNEDPRIVS.getValue())))
				.forEach(processRetreivedData);
		LOGGER.info("All assigned privileges fetched: " + rolesList);
		return rolesList;
	}

}
