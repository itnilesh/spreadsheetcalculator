package com.salpe.spreadsheetcalculator.parser;

import java.util.LinkedList;

import com.salpe.spreadsheetcalculator.common.OperatorType;

/**
 * Deals with operators 
 * @author nsalpe
 *
 */
public class OperatorToken extends Token {

	private final OperatorType operatorType;

	protected OperatorToken(String token) {
		super(token);

		this.operatorType = OperatorType.parse(getToken());
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public LinkedList<Double> evaluate(LinkedList<Double> stack){
		
		return operatorType.evaluate(stack);
	}
}
