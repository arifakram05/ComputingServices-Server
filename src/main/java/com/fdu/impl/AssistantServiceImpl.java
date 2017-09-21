package com.fdu.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.AssistantService;
import com.fdu.model.LabAssistant;
import com.fdu.model.StaffSchedule;
import com.fdu.util.DateMechanic;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;

public class AssistantServiceImpl implements AssistantService {

	private final static Logger LOGGER = Logger.getLogger(AssistantServiceImpl.class);

	MongoDatabase database;

	public AssistantServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public List<LabAssistant> viewAllLabAssistants() {
		List<LabAssistant> allLabAssistants = new ArrayList<>();
		// get collection
		MongoCollection<Document> labAssistantCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			LabAssistant labAssistant;
			try {
				labAssistant = new ObjectMapper().readValue(retrivedDataAsJSON, LabAssistant.class);
				allLabAssistants.add(labAssistant);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved lab assistant details ", e);
			}
		};
		// query
		labAssistantCollection.find().projection(fields(excludeId())).sort(ascending(Constants.LASTNAME.getValue()))
				.forEach(processRetreivedData);
		LOGGER.info("All job applicant details fetched");
		return allLabAssistants;
	}

	@Override
	public boolean deleteLabAssistant(String studentId) {
		// get collection
		MongoCollection<Document> labAssistantCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		// query
		DeleteResult result = labAssistantCollection.deleteOne(eq(Constants.STUDENTID.getValue(), studentId));
		LOGGER.info("Deleted a Lab Assistant with ID " + studentId);
		return result.wasAcknowledged();
	}

	@Override
	public void updateLA(LabAssistant labAssistant) throws ComputingServicesException {
		try {
			// get collection
			MongoCollection<Document> labAssistantCollection = database
					.getCollection(Constants.LABASSISTANTS.getValue());
			// create document to save
			Document laDetailsDocument = new Document();
			// add stuff to update
			laDetailsDocument.put(Constants.STATUS.getValue(), labAssistant.getStatus().getValue());
			laDetailsDocument.put(Constants.COMMENTS.getValue(), labAssistant.getComments());

			Document command = new Document();
			command.put("$set", laDetailsDocument);
			// update lab assistant details
			labAssistantCollection.updateOne(eq(Constants.STUDENTID.getValue(), labAssistant.getStudentId()), command);
			LOGGER.info("Updated Lab Assistant with ID " + labAssistant.getStudentId());
		} catch (Exception e) {
			LOGGER.error("Error occurred while updating lab assistant " + labAssistant.getStudentId(), e);
			throw new ComputingServicesException(e.getMessage());
		}
	}

	@Override
	public void updateLAProfile(LabAssistant labAssistant) throws ComputingServicesException {
		// get collection
		MongoCollection<Document> laCollection = database.getCollection(Constants.LABASSISTANTS.getValue());

		Document laDocument = new Document();

		if (labAssistant.getEducation() != null) {
			laDocument.put(Constants.EDUCATION.getValue(), labAssistant.getEducation());
		}
		if (labAssistant.getSpecialization() != null) {
			laDocument.put(Constants.SPECIALIZATION.getValue(), labAssistant.getSpecialization());
		}
		if (labAssistant.getProfileLink() != null) {
			laDocument.put(Constants.PROFILELINK.getValue(), labAssistant.getProfileLink());
		}
		if (labAssistant.getPhone() != null) {
			laDocument.put(Constants.PHONE.getValue(), labAssistant.getPhone());
		}
		if (labAssistant.getDescription() != null) {
			laDocument.put(Constants.DESCRIPTION.getValue(), labAssistant.getDescription());
		}
		if (labAssistant.getResume() != null) {
			laDocument.put(Constants.RESUME.getValue(), labAssistant.getResume());
		}
		if (labAssistant.getPhoto() != null) {
			laDocument.put(Constants.PHOTO.getValue(), labAssistant.getPhoto());
		}

		Document command = new Document();
		command.put("$set", laDocument);

		// query to update
		laCollection.updateOne(eq(Constants.OBJECTID.getValue(), new ObjectId(labAssistant.get_id().toString())),
				command);
		LOGGER.info(
				"Updated lab assistant profile - " + labAssistant.getLastName() + "," + labAssistant.getFirstName());
	}

	@Override
	public Object download(String id, String requester) throws ComputingServicesException {
		if (requester == null || requester.isEmpty()) {
			throw new ComputingServicesException("Requester cannot be null");
		}
		InputStream inputStream = null;
		MongoCollection<Document> laCollection = null;
		// get collection
		if (requester.equalsIgnoreCase(Constants.JOBAPPLICANTS.getValue())) {
			laCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		} else if (requester.equalsIgnoreCase(Constants.LABASSISTANTS.getValue())) {
			laCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		} else if (requester.equalsIgnoreCase(Constants.WIKIPAGES.getValue())) {
			laCollection = database.getCollection(Constants.WIKIPAGES.getValue());
		} else {
			throw new ComputingServicesException("Requester not recognized");
		}
		// query
		Object[] result = laCollection.find(eq(Constants.STUDENTID.getValue(), id))
				.projection(Projections.include(Constants.RESUME.getValue())).first().values().toArray();

		try {
			if (result[1] != null) {
				LOGGER.info("Binary data exists and obtained for " + id);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(result[1]);
				inputStream = new ByteArrayInputStream(baos.toByteArray());
			}
		} catch (Exception e) {
			LOGGER.error("Error while processing the retrieved file data");
			throw new ComputingServicesException("Error while doing file processing");
		}
		LOGGER.info("Download operation complete");
		return inputStream;
	}

	@Override
	public List<StaffSchedule> getShiftSchedule(String studentId, String date) throws ComputingServicesException {
		List<StaffSchedule> staffSchedules = new ArrayList<>();
		// get collection
		MongoCollection<Document> staffScheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {
			String retrivedDataAsJSON = document.toJson();
			StaffSchedule schedule;
			try {
				schedule = new ObjectMapper().readValue(retrivedDataAsJSON, StaffSchedule.class);
				staffSchedules.add(schedule);
			} catch (IOException e) {
				LOGGER.error("Error while processing staff schedule", e);
			}
		};
		// query
		try {
			staffScheduleCollection
					.find(and(eq(Constants.STUDENTID.getValue(), studentId),
							eq(Constants.DATE.getValue(), DateMechanic.convertStringToDateOnly(date))))
					.forEach(processRetreivedData);
		} catch (ParseException e) {
			LOGGER.error("Error while processing date ", e);
			throw new ComputingServicesException(e);
		}
		LOGGER.info("Staff Schedule Fetched");
		return staffSchedules;
	}

	@Override
	public void saveTimesheet(String operation, String studentId, String datetime, String id)
			throws ComputingServicesException {
		// get collection
		MongoCollection<Document> staffScheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// create document to save
		BasicDBObject detailsToUpdate = new BasicDBObject();
		try {
			if (operation.equals("clock-in")) {
				detailsToUpdate.put(Constants.TIMESHEET.getValue() + "." + Constants.ISCLOCKEDIN.getValue(), true);
				detailsToUpdate.put(Constants.TIMESHEET.getValue() + "." + Constants.CLOCKEDINDATETIME.getValue(),
						DateMechanic.convertStringToDateTime(datetime));
			} else if (operation.equals("clock-out")) {
				detailsToUpdate.put(Constants.TIMESHEET.getValue() + "." + Constants.ISCLOCKEDOUT.getValue(), true);
				detailsToUpdate.put(Constants.TIMESHEET.getValue() + "." + Constants.CLOCKEDOUTDATETIME.getValue(),
						DateMechanic.convertStringToDateTime(datetime));
			}
		} catch (Exception exception) {
			throw new ComputingServicesException(exception);
		}
		// command for updating
		Document command = new Document();
		command.put("$set", detailsToUpdate);
		// query
		staffScheduleCollection.updateOne(eq(Constants.OBJECTID.getValue(), new ObjectId(id)), command);
	}

	@Override
	public List<StaffSchedule> getShiftSchedule(String studentId, String startDate, String endDate)
			throws ComputingServicesException {
		List<StaffSchedule> staffSchedules = new ArrayList<>();
		// get collection
		MongoCollection<Document> staffScheduleCollection = database.getCollection(Constants.STAFFSCHECULE.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {
			String retrivedDataAsJSON = document.toJson();
			StaffSchedule schedule;
			try {
				schedule = new ObjectMapper().readValue(retrivedDataAsJSON, StaffSchedule.class);
				staffSchedules.add(schedule);
			} catch (IOException e) {
				LOGGER.error("Error while processing staff schedule", e);
			}
		};
		// query
		try {
			staffScheduleCollection
					.find(and(eq(Constants.STUDENTID.getValue(), studentId),
							Filters.gte(Constants.DATE.getValue(), DateMechanic.convertStringToDateOnly(startDate)),
							Filters.lte(Constants.DATE.getValue(), DateMechanic.convertStringToDateOnly(endDate))))
					.sort(new BasicDBObject(Constants.DATE.getValue(), 1))
					.forEach(processRetreivedData);
		} catch (ParseException e) {
			LOGGER.error("Error while processing date ", e);
			throw new ComputingServicesException(e);
		}
		LOGGER.info("Staff Schedule Fetched");
		return staffSchedules;
	}

}
