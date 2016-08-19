package com.salpe.spreadsheetcalculator.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.salpe.spreadsheetcalculator.common.Position;

public class RefPositionParser {
	
	public static final RefPositionParser INSTANCE = new RefPositionParser();
	
	private RefPositionParser() {
		// TODO Auto-generated constructor stub
	}

	private final String refRegex = "([a-zA-Z])(\\d+)";
	Pattern refRegexPattern = Pattern.compile(refRegex);

	public Position parse(String rawString) {

		rawString = rawString.toUpperCase();

		Matcher matcher = refRegexPattern.matcher(rawString);

		boolean found = matcher.find();

		if (!found) {

			throw new IllegalArgumentException("Invalid reference.");
		}

		int row = matcher.group(1).charAt(0) - 'A';
		int column = Integer.parseInt(matcher.group(2)) -1;
		
		
		return new Position( row,column);

	}
}
