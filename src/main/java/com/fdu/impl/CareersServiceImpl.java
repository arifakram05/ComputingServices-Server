package com.fdu.impl;

import java.util.Date;

import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.CareersService;
import com.fdu.model.JobApplicant;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CareersServiceImpl implements CareersService {

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
		document.append(Constants.DATEAPPPLIED.getValue(), new Date());
		document.append(Constants.EMAIL.getValue(), jobApplicantDetails.getEmail());
		document.append(Constants.PHONE.getValue(), jobApplicantDetails.getPhone());
		document.append(Constants.EDUCATION.getValue(), jobApplicantDetails.getEducation());
		document.append(Constants.RESUME.getValue(), jobApplicantDetails.getResume());
		// save document
		jobApplicantsCollection.insertOne(document);
	}

}
