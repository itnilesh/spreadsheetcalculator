package com.salpe.spreadsheetcalculator.common;

import java.util.LinkedList;

/**
 * Possible math operations
 * 
 * @author nsalpe
 *
 */
public enum OperatorType {

	ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVISION("/");

	@SuppressWarnings("unused")
	private final String opStr;

	private OperatorType(String opStr) {

		this.opStr = opStr;
	}

	public static OperatorType parse(String rawStr) {

		switch (rawStr) {
		case "+":
			return ADD;
		case "-":
			return SUBTRACT;
		case "*":
			return MULTIPLY;
		case "/":
			return DIVISION;

		default:
			throw new IllegalArgumentException("Unknown operator : " + rawStr);
		}

	}

	public LinkedList<Double> evaluate(LinkedList<Double> stack) {

		double b = stack.pop();
		double a = stack.pop();

		double result = 0;

		switch (this) {
		case ADD:
			result = a + b;
			break;
		case SUBTRACT:

			result = a - b;
			break;
		case MULTIPLY:
			result = a * b;
			break;
		case DIVISION:

			result = a / b;
			break;

		default:
			throw new IllegalArgumentException("Unknown operator : ");
		}

		stack.push(result);
		return stack;

	}

}
