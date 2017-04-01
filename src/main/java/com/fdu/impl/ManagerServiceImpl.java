package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.database.DBConnection;
import com.fdu.interfaces.ManagerService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class ManagerServiceImpl implements ManagerService {

	private final static Logger LOGGER = Logger.getLogger(ManagerServiceImpl.class);

	MongoDatabase database;

	public ManagerServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public boolean deleteJobApplicant(int studentId) {
		// get collection
		MongoCollection<Document> jobApplicantsCollection = DBConnection.getConnection().getCollection(Constants.JOBAPPLICANTS.getValue());
		// query
		DeleteResult result = jobApplicantsCollection.deleteOne(eq(Constants.STUDENTID.getValue(), studentId));
		return result.wasAcknowledged();
	}

}
