package com.fdu.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.interfaces.ScheduleService;
import com.fdu.model.LabSchedule;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ScheduleServiceImpl implements ScheduleService {

	MongoDatabase database;

	public ScheduleServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void saveLabSchedule(List<LabSchedule> labschedule) {
		MongoCollection<Document> labscheduleCollection = database.getCollection(Constants.LABSCHECULE.getValue());
		Function<LabSchedule, Document> processEvents = event -> processEvents(event);
		List<Document> events = labschedule.stream().map(processEvents).collect(Collectors.toList());
		labscheduleCollection.insertMany(events);
	}

	private Document processEvents(LabSchedule labschedule) {
		Document document = new Document();
		document.append(Constants.LABNAME.getValue(), labschedule.getLabName());
		document.append(Constants.START.getValue(), labschedule.getStart());
		document.append(Constants.END.getValue(), labschedule.getEnd());
		document.append(Constants.ALLDAY.getValue(), labschedule.isAllDay());
		document.append(Constants.PROFESSOR.getValue(), labschedule.getProfessor());
		document.append(Constants.TITLE.getValue(), labschedule.getTitle());
		document.append(Constants.BGCOLOR.getValue(), labschedule.getBackgroundColor());
		document.append(Constants.GROUPID.getValue(), getGroupId());
		return document;
	}

	private String getGroupId() {
		return null;
	}

}
