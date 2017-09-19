package com.fdu.deserializers;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.StdDeserializer;

import com.fdu.constants.Constants;
import com.fdu.model.StaffSchedule;
import com.fdu.model.Timesheet;
import com.fdu.util.DateMechanic;

/**
 * Customer deserializer for {@link StaffSchedule}<br/>
 * Can be used by having the tag {@code @JsonDeserialize(using =
 * StaffScheduleDeserializer.class)} annotation at {@link StaffSchedule}'s class
 * level
 * 
 * @author arifakrammohammed
 *
 */
public class StaffScheduleDeserializer extends StdDeserializer<StaffSchedule> {

	private final static Logger LOGGER = Logger.getLogger(StaffScheduleDeserializer.class);

	public StaffScheduleDeserializer() {
		this(null);
	}

	protected StaffScheduleDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public StaffSchedule deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode staffScheduleNode = jsonParser.getCodec().readTree(jsonParser);

		StaffSchedule staffSchedule = new StaffSchedule();

		if (staffScheduleNode.get(Constants.OBJECTID.getValue()) != null) {
			if (staffScheduleNode.get(Constants.OBJECTID.getValue()).isValueNode()) {
				staffSchedule.set_id(staffScheduleNode.get(Constants.OBJECTID.getValue()).getTextValue());
			} else {
				staffSchedule.set_id(staffScheduleNode.get(Constants.OBJECTID.getValue())
						.get(Constants.$OBJECTID.getValue()).getTextValue());
			}
		}
		if (staffScheduleNode.get(Constants.TITLE.getValue()) != null) {
			staffSchedule.setTitle(staffScheduleNode.get(Constants.TITLE.getValue()).getTextValue());
		}
		if (staffScheduleNode.get(Constants.STUDENTID.getValue()) != null) {
			staffSchedule.setStudentId(staffScheduleNode.get(Constants.STUDENTID.getValue()).getTextValue());
		}
		if (staffScheduleNode.get(Constants.BGCOLOR.getValue()) != null) {
			staffSchedule.setBackgroundColor(staffScheduleNode.get(Constants.BGCOLOR.getValue()).getTextValue());
		}
		if (staffScheduleNode.get(Constants.ALLDAY.getValue()) != null) {
			staffSchedule.setAllDay(staffScheduleNode.get(Constants.ALLDAY.getValue()).getBooleanValue());
		}
		if (staffScheduleNode.get(Constants.ISAPPROVED.getValue()) != null) {
			staffSchedule.setApproved(staffScheduleNode.get(Constants.ISAPPROVED.getValue()).getBooleanValue());
		}
		if (staffScheduleNode.get(Constants.LABNAME.getValue()) != null) {
			staffSchedule.setLabName(staffScheduleNode.get(Constants.LABNAME.getValue()).getTextValue());
		}
		if (staffScheduleNode.get(Constants.START.getValue()) != null) {
			staffSchedule.setStart(staffScheduleNode.get(Constants.START.getValue()).getTextValue());
		}
		if (staffScheduleNode.get(Constants.END.getValue()) != null) {
			staffSchedule.setEnd(staffScheduleNode.get(Constants.END.getValue()).getTextValue());
		}
		if (staffScheduleNode.get(Constants.GROUPID.getValue()) != null) {
			staffSchedule.setGroupId(staffScheduleNode.get(Constants.GROUPID.getValue()).getTextValue());
		}

		try {
			if (staffScheduleNode.get(Constants.DATE.getValue()) == null) {
				staffSchedule.setDate(null);
			} else if (staffScheduleNode.get(Constants.DATE.getValue()).isValueNode()) {
				String date = staffScheduleNode.get(Constants.DATE.getValue()).getTextValue();
				staffSchedule.setDate(DateMechanic.convertStringToDateOnly(date));
			} else {
				String date = staffScheduleNode.get(Constants.DATE.getValue()).get("$date").toString();
				staffSchedule.setDate(DateMechanic.createDateFromMillis(Long.parseLong(date)));
			}
		} catch (NumberFormatException | ParseException e) {
			LOGGER.error("Error while deserializing StaffSchedule object ", e);
		}

		// this is the way to call another customer deserializer from a custom
		// deserializer; else Timesheet object will be set as null
		if (staffScheduleNode.get(Constants.TIMESHEET.getValue()) != null) {
			Timesheet timesheet = new ObjectMapper()
					.readValue(staffScheduleNode.get(Constants.TIMESHEET.getValue()).toString(), Timesheet.class);
			staffSchedule.setTimesheet(timesheet);
		}

		return staffSchedule;
	}
}
