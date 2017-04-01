package com.fdu.dbimpl;

import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.database.DBConnection;
import com.fdu.interfaces.DBOperations;
import com.fdu.model.LabSchedule;
import com.mongodb.client.MongoCollection;

public class ComputingServicesDBOperations implements DBOperations {

	@Override
	public boolean saveLabSchedule(LabSchedule labSchedule) {
		//connect to database
		MongoCollection<Document> collection = DBConnection.getConnection().getCollection(Constants.LABSCHECULE.getValue());
		
		Document document = new Document();
				
		document.append(Constants.LABSCHEDULEID.getValue(), labSchedule.getLabScheduleId());
		document.append(Constants.CAMPUS.getValue(), labSchedule.getCampus());
		document.append(Constants.LAB.getValue(), labSchedule.getLab());
		document.append(Constants.STARTDATE.getValue(), labSchedule.getStartDate());
		document.append(Constants.ENDDATE.getValue(), labSchedule.getEndDate());
		document.append(Constants.STARTTIME.getValue(), labSchedule.getStartTime());
		document.append(Constants.ENDTIME.getValue(), labSchedule.getEndTime());
		document.append(Constants.ISRECURRING.getValue(), labSchedule.isRecurring());
		document.append(Constants.PROFESSORNAME.getValue(), labSchedule.getProfName());
		document.append(Constants.SUBJECT.getValue(), labSchedule.getSubject());
		
		//insert details into database
		collection.insertOne(document);
		
		return true;
	}

}
