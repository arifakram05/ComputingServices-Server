package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.interfaces.ScheduleService;
import com.fdu.model.LabSchedule;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ScheduleServiceImpl implements ScheduleService {

	private final static Logger LOGGER = Logger.getLogger(ScheduleServiceImpl.class);

	MongoDatabase database;

	public ScheduleServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void saveLabSchedule(List<LabSchedule> labschedule) {
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		String groupId = getGroupId();
		Function<LabSchedule, Document> processEvents = event -> processEvents(event, groupId);
		List<Document> events = labschedule.stream().map(processEvents).collect(Collectors.toList());
		labscheduleCollection.insertMany(events);
	}

	private Document processEvents(LabSchedule labschedule, String groupId) {
		Document document = new Document();
		document.append(Constants.LABNAME.getValue(), labschedule.getLabName());
		document.append(Constants.START.getValue(), labschedule.getStart());
		document.append(Constants.END.getValue(), labschedule.getEnd());
		document.append(Constants.ALLDAY.getValue(), labschedule.isAllDay());
		document.append(Constants.PROFESSOR.getValue(), labschedule.getProfessor());
		document.append(Constants.TITLE.getValue(), labschedule.getTitle());
		document.append(Constants.BGCOLOR.getValue(), labschedule.getBackgroundColor());
		document.append(Constants.GROUPID.getValue(), groupId);
		return document;
	}

	private String getGroupId() {
		ObjectId objectId = ObjectId.get();
		return objectId.toString();
	}

	@Override
	public List<LabSchedule> getLabSchedule() {
		List<LabSchedule> labschedule = new ArrayList<>();
		// get collection
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			LabSchedule schedule;
			try {
				schedule = new ObjectMapper().readValue(retrivedDataAsJSON, LabSchedule.class);
				labschedule.add(schedule);
			} catch (IOException e) {
				LOGGER.error("Error while processing lab schedule", e);
			}
		};
		// query
		labscheduleCollection.find().forEach(processRetreivedData);
		LOGGER.info("Lab Schedule Fetched");
		return labschedule;
	}

	@Override
	public void updateLabSchedule(LabSchedule labschedule) {
		// get collection
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		// query to update
		labscheduleCollection.updateOne(
				eq(Constants.OBJECTID.getValue(), new ObjectId(labschedule.get_id().toString())),
				createDataToUpdate(labschedule));
		LOGGER.info("Updated lab schedule");
	}

	@Override
	public void updateManyEvents(LabSchedule labschedule) {
		// get collection
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		// query to update
		labscheduleCollection.updateMany(eq(Constants.GROUPID.getValue(), labschedule.getGroupId()),
				createDataToUpdate(labschedule));
		LOGGER.info("Updated all related events on the lab schedule");
	}

	private Document createDataToUpdate(LabSchedule labschedule) {
		// set data
		Document document = new Document();
		document.append(Constants.LABNAME.getValue(), labschedule.getLabName());
		document.append(Constants.START.getValue(), labschedule.getStart());
		document.append(Constants.END.getValue(), labschedule.getEnd());
		document.append(Constants.ALLDAY.getValue(), labschedule.isAllDay());
		document.append(Constants.PROFESSOR.getValue(), labschedule.getProfessor());
		document.append(Constants.TITLE.getValue(), labschedule.getTitle());
		document.append(Constants.BGCOLOR.getValue(), labschedule.getBackgroundColor());
		document.append(Constants.GROUPID.getValue(), labschedule.getGroupId());
		// command
		Document command = new Document();
		command.put("$set", document);

		return command;
	}

	@Override
	public void deleteLabSchedule(String eventId) {
		// get collection
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		// query
		labscheduleCollection.deleteOne(eq(Constants.OBJECTID.getValue(), new ObjectId(eventId)));
		LOGGER.info("Deleted event with ID " + eventId);
	}

	@Override
	public void deleteManyEvents(String groupId) {
		// get collection
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		// query
		labscheduleCollection.deleteMany(eq(Constants.GROUPID.getValue(), groupId));
		LOGGER.info("Deleted all events of group " + groupId);
	}

}
