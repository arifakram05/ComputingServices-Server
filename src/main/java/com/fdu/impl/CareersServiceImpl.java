package com.fdu.impl;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.CareersService;
import com.fdu.model.JobApplicant;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class CareersServiceImpl implements CareersService {

	private final static Logger LOGGER = Logger.getLogger(CareersServiceImpl.class);

	MongoDatabase database;

	public CareersServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void validateInput(JobApplicant jobApplicantDetails) throws ComputingServicesException {

	}

	@Override
	public void saveJobApplicant(JobApplicant jobApplicantDetails) {
		// get collection
		MongoCollection<Document> jobApplicantsCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// create document
		Document document = new Document();
		// add document properties
		document.append(Constants.STUDENTID.getValue(), jobApplicantDetails.getStudentId());
		document.append(Constants.FIRSTNAME.getValue(), jobApplicantDetails.getFirstName());
		document.append(Constants.LASTNAME.getValue(), jobApplicantDetails.getLastName());
		document.append(Constants.DATEAPPPLIED.getValue(), jobApplicantDetails.getDateApplied());
		document.append(Constants.EMAIL.getValue(), jobApplicantDetails.getEmail());
		document.append(Constants.PHONE.getValue(), jobApplicantDetails.getPhone());
		document.append(Constants.EDUCATION.getValue(), jobApplicantDetails.getEducation());
		document.append(Constants.RESUME.getValue(), jobApplicantDetails.getResume());
		document.append(Constants.RESUME_EXTN.getValue(), jobApplicantDetails.getResumeExtn());
		document.append(Constants.STATUS.getValue(), Constants.SUBMITTED.getValue());
		// save document
		jobApplicantsCollection.insertOne(document);
	}

	@Override
	public List<JobApplicant> viewAllJobApplicants() {
		List<JobApplicant> allJobApplicants = new ArrayList<>();
		// get collection
		MongoCollection<Document> jobApplicantsCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			JobApplicant jobApplicant;
			try {
				jobApplicant = new ObjectMapper().readValue(retrivedDataAsJSON, JobApplicant.class);
				allJobApplicants.add(jobApplicant);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved job applicant details ", e);
			}
		};
		// query
		jobApplicantsCollection.find().projection(fields(excludeId()))
				.sort(ascending(Constants.DATEAPPPLIED.getValue())).forEach(processRetreivedData);
		LOGGER.info("All job applicant details fetched");
		return allJobApplicants;
	}

	@Override
	public String getJobApplicantStatus(String studentId) {
		// get collection
		MongoCollection<Document> jobApplicantsCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// query
		Document jobApplicantDocument = jobApplicantsCollection
				.find(Filters.eq(Constants.STUDENTID.getValue(), studentId)).first();
		return jobApplicantDocument != null ? jobApplicantDocument.getString(Constants.STATUS.getValue()) : null;
	}

}
