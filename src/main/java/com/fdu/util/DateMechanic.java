package com.fdu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DateMechanic {

	private final static String sourceFormat = "MMM dd, yyyy HH:mm";
	private final static String targetFormat = "MMM dd, yyyy";
	private final static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern(sourceFormat);
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sourceFormat);

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

		LocalDate startLocalDate = LocalDate.parse(startDate, dateTimeformatter);
		LocalDate endLocalDate = LocalDate.parse(endDate, dateTimeformatter);

		Consumer<LocalDate> processLocalDateToString = (localDate) -> {
			dates.add(localDate.format(dateTimeformatter));
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
		LocalDateTime localDate = LocalDateTime.parse(givenDate, dateTimeformatter);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(targetFormat);
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
	 * {@link LocalDateTime}
	 * 
	 * @param datetime
	 *            date and time in {@link String} representation
	 * @return date and time stored in {@link LocalDateTime}
	 * @throws ParseException
	 *             if error occurs while given date and time
	 */
	public static LocalDateTime processDateTimeAsLocalDateTime(String datetime) throws ParseException {
		return LocalDateTime.parse(datetime, dateTimeformatter);
	}

	/**
	 * Convert a given date and time in {@link String} representation to a
	 * {@link Calendar}
	 * 
	 * @param datetime
	 *            date and time in {@link String} representation
	 * @return date and time stored in {@link Calendar}
	 * @throws ParseException
	 *             if error occurs while given date and time
	 */
	public static Date processDateTimeAsDate(String datetime) throws ParseException {
		/*
		 * Calendar calendar = Calendar.getInstance();
		 * calendar.setTime(simpleDateFormat.parse(datetime)); return calendar;
		 */
		return simpleDateFormat.parse(datetime);
	}

	/**
	 * Convert a given {@link Calendar} instance to a {@link String}
	 * 
	 * @param calendar
	 *            date and time stored in {@link Calendar}
	 * @return date and time in {@link String} representation
	 */
	public static String processCalendarAsString(Calendar calendar) {
		return simpleDateFormat.format(calendar.getTime());
	}

	public static String processCalendarAsString(Date date) {
		return simpleDateFormat.format(date);
	}

}
