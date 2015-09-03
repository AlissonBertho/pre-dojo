/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.common;

import java.util.regex.Pattern;

/**
 * @author Alisson
 *
 */
public interface GamePatternConstant {

	// Create a string pattern objects
	final String PATTERN_WORLD_CONSTANT = "<WORLD> killed ([^\\s]+) by ([^\\s]+)";
	final String PATTERN_KILLER_CONSTANT = "(.*) - ([^\\s]+) killed ([^\\s]+) using ([^\\s]+)";
	final String PATTERN_START_CONSTANT = "New match ([^\\s]+) has started";
	final String TIME_LOG_LINE_PATTERN = "dd/MM/yyyy HH:mm:ss";

	// Create a pattern objects
	final Pattern WORLD_PATTERN = Pattern.compile(PATTERN_WORLD_CONSTANT);
	final Pattern KILLER_PATTERN = Pattern.compile(PATTERN_KILLER_CONSTANT);
	final Pattern START_PATTERN = Pattern.compile(PATTERN_START_CONSTANT);
}
