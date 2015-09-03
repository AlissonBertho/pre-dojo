/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alisson
 *
 */
public class DateTool {

	private static final Logger logger = LoggerFactory.getLogger(DateTool.class);

	public static Calendar parseStringToCalendar(final String strDate, final String datePattern) {
		final SimpleDateFormat format = new SimpleDateFormat(datePattern);
		Date date = null;
		try {
			date = format.parse(strDate);
		} catch (final ParseException e) {
			logger.error(String.format("Um erro ocorreu durante a convers\u00e3o da data. StringPattern informado: [ %s ]", strDate), e);
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
