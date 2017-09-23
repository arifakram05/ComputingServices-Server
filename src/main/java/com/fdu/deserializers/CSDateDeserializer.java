package com.fdu.deserializers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;

import com.fdu.util.DateMechanic;

public class CSDateDeserializer extends StdDeserializer<Date> {

	public CSDateDeserializer() {
		this(null);
	}

	protected CSDateDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		JsonNode jsonNode = jsonParser.readValueAsTree();
		try {
			if (jsonNode.get("$date") == null) {
				return DateMechanic.convertStringToDateOnly(jsonNode.getValueAsText());
			} else {
				return DateMechanic.createDateFromMillis(Long.parseLong(jsonNode.get("$date").toString()));
			}
		} catch (ParseException e) {
			throw new RuntimeException("Error while deserializing date");
		}
	}

}
