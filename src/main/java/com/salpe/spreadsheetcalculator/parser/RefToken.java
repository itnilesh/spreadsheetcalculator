package com.salpe.spreadsheetcalculator.parser;

import com.salpe.spreadsheetcalculator.common.Position;

/**
 * Deals with References
 * 
 * @author nsalpe
 *
 */
public class RefToken extends Token {

	private final Position position;

	private double value;

	protected RefToken(String token) {
		super(token);
		this.position = RefPositionParser.INSTANCE.parse(token);

	}

	public Position getRefPosition() {

		return position;

	}

	public void resolveValue(double value) {

		this.value = value;

	}

	public double getResolvedValue() {

		return value;
	}

}
