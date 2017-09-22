package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.interfaces.GeneralService;
import com.fdu.model.Wiki;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class GeneralServiceImpl implements GeneralService {

	private final static Logger LOGGER = Logger.getLogger(GeneralServiceImpl.class);

	MongoDatabase database;

	public GeneralServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public List<Wiki> getWikis() {
		List<Wiki> wikiList = new LinkedList<Wiki>();
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.WIKIPAGES.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {
			Wiki wiki;
			try {
				wiki = new ObjectMapper().readValue(document.toJson(), Wiki.class);
				wikiList.add(wiki);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved roles ", e);
			}
		};
		// query
		rolesCollection.find().forEach(processRetreivedData);
		LOGGER.info("All Wikis fetched");
		return wikiList;
	}

	@Override
	public boolean deleteWiki(String fileId) {
		// get collection
		MongoCollection<Document> rolesCollection = database.getCollection(Constants.WIKIPAGES.getValue());
		// query
		DeleteResult result = rolesCollection.deleteOne(eq(Constants.OBJECTID.getValue(), new ObjectId(fileId)));
		LOGGER.info("Deleted wiki with ID: " + fileId);
		return result.wasAcknowledged();

	}
}
