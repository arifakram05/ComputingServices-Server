package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.fdu.constants.Constants;
import com.fdu.interfaces.RoleService;
import com.fdu.model.Privilege;
import com.fdu.model.Role;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RoleServiceImpl implements RoleService {

	private final static Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);

	MongoDatabase database;

	public RoleServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void update(Role role) {
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
		rolesCollection.updateOne(eq(Constants.OBJECTID.getValue(), new ObjectId(role.get_id().toString())), command);
		LOGGER.info("Updated roles and privilges for the role - "+role.getRoleName());
	}

}
