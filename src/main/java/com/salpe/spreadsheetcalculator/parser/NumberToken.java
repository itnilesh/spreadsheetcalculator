package com.salpe.spreadsheetcalculator.parser;

/**
 * Deals with numbers 
 * @author nsalpe
 *
 */
public class NumberToken extends Token {

	private final double value;

	protected NumberToken(String token) {
		super(token);

		value = Double.parseDouble(getToken());
	}

	public double getValue() {

		return value;
	}
}
