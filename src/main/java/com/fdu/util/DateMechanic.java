

package com.fdu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DateMechanic {

	private final static String DATE_TIME_FORMAT = "MMM dd, yyyy HH:mm";
	private final static String DATE_ONLY_FORMAT = "MMM dd, yyyy";
	private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	private final static SimpleDateFormat SIMPLE_DATE_TIME_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMAT);
	private final static SimpleDateFormat SIMPLE_DATE_ONLY_FORMATTER = new SimpleDateFormat(DATE_ONLY_FORMAT);

	/**
	 * Returns a list of all dates between start and end date; both dates being
	 * inclusive
	 * 
	 * @param startDate
	 *            start date
	 * @param endDate
	 *            end date
	 * @return {@link List} of all dates in {@link String} format
	 * @throws ParseException
	 *             if error while processing dates
	 */
	public static List<String> getAllDatesBetweenTwoDates(String startDate, String endDate) throws ParseException {

		List<String> dates = new ArrayList<String>();

		LocalDate startLocalDate = LocalDate.parse(startDate, DATE_TIME_FORMATTER);
		LocalDate endLocalDate = LocalDate.parse(endDate, DATE_TIME_FORMATTER);

		Consumer<LocalDate> processLocalDateToString = (localDate) -> {
			dates.add(localDate.format(DATE_TIME_FORMATTER));
		};
		Stream.iterate(startLocalDate, date -> date.plusDays(1))
				.limit(ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + 1).forEach(processLocalDateToString);

		return dates;
	}

	/**
	 * Removes the hours and minutes from the date and time provided in String
	 * format.
	 * 
	 * @param givenDate
	 *            date and time in format MMM dd, yyyy HH:mm
	 * @return formatted date in format MMM dd, yyyy
	 */
	public static String extractDateOnly(String givenDate) {
		LocalDateTime localDate = LocalDateTime.parse(givenDate, DATE_TIME_FORMATTER);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_ONLY_FORMAT);
		return localDate.format(formatter);
	}

	/**
	 * Extracts the time i.e hours and minutes from the given date of format MMM
	 * dd, yyyy HH:mm
	 * 
	 * @param givenDate
	 *            date and time in format MMM dd, yyyy HH:mm
	 * @return time in the format HH:mm
	 */
	public static String extractTimeOnly(String givenDate) {
		return givenDate.substring(givenDate.length() - 5);
	}

	/**
	 * Convert a given date and time in {@link String} representation to a
	 * {@link Date}
	 * 
	 * @param datetime
	 *            date and time in {@link String} representation
	 * @return date and time stored in {@link Date}
	 * @throws ParseException
	 *             if error occurs while processing given date and time
	 */
	public static Date convertStringToDateTime(String datetime) throws ParseException {
		return SIMPLE_DATE_TIME_FORMATTER.parse(datetime);
	}

	/**
	 * Convert a given date and time in {@link String} representation to a
	 * {@link Date}
	 * 
	 * @param datetime
	 *            date and time in {@link String} representation
	 * @return only the date stored in {@link Date}
	 * @throws ParseException
	 *             if error occurs while processing given date and time
	 */
	public static Date convertStringToDateOnly(String datetime) throws ParseException {
		return SIMPLE_DATE_ONLY_FORMATTER.parse(datetime);
	}
	
	public static String convertDateToString(Date date) {
		return SIMPLE_DATE_ONLY_FORMATTER.format(date);
	}

	/**
	 * Create a {@link Date} using the given time in milliseconds
	 * 
	 * @param millis
	 *            date and time represented in milliseconds
	 * @return {@link Date}
	 * @throws ParseException
	 *             if error occurs while processing given date and time
	 */
	public static Date createDateFromMillis(Long millis) {
		return new Date(millis);
	}

}
