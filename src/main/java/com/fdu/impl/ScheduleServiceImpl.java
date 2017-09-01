package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.ScheduleService;
import com.fdu.model.StaffSchedule;
import com.fdu.util.DateMechanic;
import com.mongodb.BasicDBObject;
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
	public void saveStaffSchedule(List<StaffSchedule> staffschedule) {
		MongoCollection<Document> staffscheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		String groupId = getGroupId();
		Function<StaffSchedule, Document> processEvents = event -> processEvents(event, groupId);
		List<Document> events = staffschedule.stream().map(processEvents).collect(Collectors.toList());
		staffscheduleCollection.insertMany(events);
	}

	private Document processEvents(StaffSchedule staffschedule, String groupId) {
		Document document = new Document();
		document.append(Constants.LABNAME.getValue(), staffschedule.getLabName());
		document.append(Constants.DATE.getValue(), staffschedule.getDate());
		document.append(Constants.START.getValue(), DateMechanic.extractTimeOnly(staffschedule.getStart()));
		document.append(Constants.END.getValue(), DateMechanic.extractTimeOnly(staffschedule.getEnd()));
		document.append(Constants.ALLDAY.getValue(), staffschedule.isAllDay());
		document.append(Constants.STUDENTID.getValue(), staffschedule.getStudentId());
		document.append(Constants.TITLE.getValue(), staffschedule.getTitle());
		document.append(Constants.BGCOLOR.getValue(), staffschedule.getBackgroundColor());
		document.append(Constants.GROUPID.getValue(), groupId);
		// insert Shift details
		BasicDBObject shiftDetails = new BasicDBObject();
		shiftDetails.put(Constants.ISCLOCKEDIN.getValue(), new Boolean(false));
		shiftDetails.put(Constants.ISCLOCKEDOUT.getValue(), new Boolean(false));
		shiftDetails.put(Constants.CLOCKEDINDATETIME.getValue(), null);
		shiftDetails.put(Constants.CLOCKEDOUTDATETIME.getValue(), null);
		document.append(Constants.TIMESHEET.getValue(), shiftDetails);

		return document;
	}

	private String getGroupId() {
		ObjectId objectId = ObjectId.get();
		return objectId.toString();
	}

	@Override
	public List<StaffSchedule> getStaffSchedule() {
		List<StaffSchedule> staffschedule = new ArrayList<>();
		// get collection
		MongoCollection<Document> staffscheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			StaffSchedule schedule;
			try {
				schedule = new ObjectMapper().readValue(retrivedDataAsJSON, StaffSchedule.class);
				String date = DateMechanic.convertDateToString(schedule.getDate());
				schedule.setStart(date + " " + schedule.getStart());
				schedule.setEnd(date + " " + schedule.getEnd());
				staffschedule.add(schedule);
			} catch (IOException e) {
				LOGGER.error("Error while processing staff schedule", e);
			}
		};
		// query
		staffscheduleCollection.find().forEach(processRetreivedData);
		LOGGER.info("Staff Schedule Fetched");
		return staffschedule;
	}

	@Override
	public void updateStaffSchedule(StaffSchedule staffschedule) throws ComputingServicesException {
		// get collection
		MongoCollection<Document> staffscheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// query to update
		try {
			staffscheduleCollection.updateOne(
					eq(Constants.OBJECTID.getValue(), new ObjectId(staffschedule.get_id().toString())),
					createDataToUpdate(staffschedule, true));
		} catch (ParseException e) {
			LOGGER.error("Error while processing date");
			throw new ComputingServicesException(e);
		}
		LOGGER.info("Updated staff schedule");
	}

	@Override
	public void updateManyEvents(StaffSchedule staffschedule) throws ComputingServicesException {
		// get collection
		MongoCollection<Document> staffscheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// query to update
		try {
			staffscheduleCollection.updateMany(eq(Constants.GROUPID.getValue(), staffschedule.getGroupId()),
					createDataToUpdate(staffschedule, false));
		} catch (ParseException e) {
			LOGGER.error("Error while processing date");
			throw new ComputingServicesException(e);
		}
		LOGGER.info("Updated all related events on the staff schedule");
	}

	private Document createDataToUpdate(StaffSchedule staffschedule, boolean isSingleEvent) throws ParseException {
		// set data
		Document document = new Document();
		document.append(Constants.LABNAME.getValue(), staffschedule.getLabName());
		/*
		 * NOTE: Only the event's time can be updated, not the date. The reason
		 * is that if a single event in a group of events was updated with date
		 * change, and later when you delete all those grouped events, this
		 * updated event (with date updated) will also be deleted. Hence, only
		 * time can be updated, not the date. Only way to update an event to a
		 * different date is to delete the event and recreate it. However, below
		 * code makes it possible to update date of an event. IMP: Do not allow
		 * updating multiple event's dates, its has side effects.
		 */
		/*
		 * if (isSingleEvent) { document.append(Constants.DATE.getValue(),
		 * DateMechanic.convertStringToDateOnly(DateMechanic.extractDateOnly
		 * (staffschedule.getStart()))); }
		 */
		document.append(Constants.START.getValue(), DateMechanic.extractTimeOnly(staffschedule.getStart()));
		document.append(Constants.END.getValue(), DateMechanic.extractTimeOnly(staffschedule.getEnd()));
		document.append(Constants.ALLDAY.getValue(), staffschedule.isAllDay());
		document.append(Constants.STUDENTID.getValue(), staffschedule.getStudentId());
		document.append(Constants.TITLE.getValue(), staffschedule.getTitle());
		document.append(Constants.BGCOLOR.getValue(), staffschedule.getBackgroundColor());
		document.append(Constants.GROUPID.getValue(), staffschedule.getGroupId());
		// command
		Document command = new Document();
		command.put("$set", document);

		return command;
	}

	@Override
	public void deleteStaffSchedule(String eventId) {
		// get collection
		MongoCollection<Document> staffscheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// query
		staffscheduleCollection.deleteOne(eq(Constants.OBJECTID.getValue(), new ObjectId(eventId)));
		LOGGER.info("Deleted event with ID " + eventId);
	}

	@Override
	public void deleteManyEvents(String groupId) {
		// get collection
		MongoCollection<Document> staffscheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// query
		staffscheduleCollection.deleteMany(eq(Constants.GROUPID.getValue(), groupId));
		LOGGER.info("Deleted all events of group " + groupId);
	}

}
