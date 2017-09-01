package com.fdu.deserializers;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;

import com.fdu.model.Timesheet;
import com.fdu.util.DateMechanic;

/**
 * Customer deserializer for {@link Timesheet}<br/>
 * Can be used by having the tag {@code @JsonDeserialize(using =
 * TimesheetDeserializer.class)} annotation at {@link Timesheet}'s class level
 * 
 * @author arifakrammohammed
 *
 */
public class TimesheetDeserializer extends StdDeserializer<Timesheet> {

	private final static Logger LOGGER = Logger.getLogger(TimesheetDeserializer.class);

	public TimesheetDeserializer() {
		this(null);
	}

	protected TimesheetDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Timesheet deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode timesheetNode = jsonParser.getCodec().readTree(jsonParser);

		Timesheet timesheet = new Timesheet();

		timesheet.setIsClockedIn(timesheetNode.get("isClockedIn").getBooleanValue());
		timesheet.setIsClockedOut(timesheetNode.get("isClockedOut").getBooleanValue());

		if (timesheetNode.get("clockedInDateTime") != null) {
			if (timesheetNode.get("clockedInDateTime").isNull()) {
				timesheet.setClockedInDateTime(null);
			} else {
				String dateTime = timesheetNode.get("clockedInDateTime").get("$date").toString();
				try {
					timesheet.setClockedInDateTime(DateMechanic.createDateFromMillis(Long.parseLong(dateTime)));
				} catch (NumberFormatException e) {
					LOGGER.error("Error while deserializing Timesheet object ", e);
				}
			}
		}

		if (timesheetNode.get("clockedOutDateTime") != null) {
			if (timesheetNode.get("clockedOutDateTime").isNull()) {
				timesheet.setClockedOutDateTime(null);
			} else {
				String dateTime = timesheetNode.get("clockedOutDateTime").get("$date").toString();
				try {
					timesheet.setClockedOutDateTime(DateMechanic.createDateFromMillis(Long.parseLong(dateTime)));
				} catch (NumberFormatException e) {
					LOGGER.error("Error while deserializing Timesheet object ", e);
				}
			}
		}

		return timesheet;
	}

}
