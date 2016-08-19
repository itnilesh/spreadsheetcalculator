package com.salpe.spreadsheetcalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.salpe.spreadsheetcalculator.common.Position;
import com.salpe.spreadsheetcalculator.parser.CellParser;
import com.salpe.spreadsheetcalculator.parser.NumberToken;
import com.salpe.spreadsheetcalculator.parser.OperatorToken;
import com.salpe.spreadsheetcalculator.parser.RefToken;
import com.salpe.spreadsheetcalculator.parser.Token;

public class Cell {

	private final Position position;
	private final LinkedList<Token> expressionList;
	private final Map<Position, RefToken> unresolvedRefs;
	private double value = 0;

	public Cell(Position position, String expressionStr) {

		this.unresolvedRefs = new HashMap<>();
		this.position = position;
		expressionList = CellParser.INSTANCE.parser(expressionStr);

		initRefCnt();

		if (isResolved()) {
			evaluate();
		}

	}

	private void initRefCnt() {

		for (Token token : expressionList) {

			if (token instanceof RefToken) {

				RefToken refToken = (RefToken) token;

				unresolvedRefs.put(refToken.getRefPosition(), refToken);
			}

		}
	}

	public boolean isResolved() {

		return unresolvedRefs.size() == 0;
	}

	public Position getPosition() {
		return position;
	}

	public List<RefToken> getUnResolvedRef() {

		List<RefToken> list = new ArrayList<>();

		unresolvedRefs.entrySet().forEach(e -> list.add(e.getValue()));

		return list;
	}

	public void resolve(Cell resolvedCell) {

		if (!resolvedCell.isResolved()) {
			throw new IllegalArgumentException("Passed Unresolved Cell");
		}

		RefToken unresolvedCell = unresolvedRefs.remove(resolvedCell.getPosition());

		if (unresolvedCell != null) {
			unresolvedCell.resolveValue(resolvedCell.getValue());
		}

		if (isResolved()) {
			evaluate();
		}

	}

	private void evaluate() {

		LinkedList<Double> stack = new LinkedList<>();

		for (Token token : expressionList) {

			if (token instanceof NumberToken) {
				stack.push(((NumberToken) token).getValue());
			} else if (token instanceof RefToken) {

				stack.push(((RefToken) token).getResolvedValue());

			} else {

				OperatorToken operatorToken = (OperatorToken) token;

				operatorToken.evaluate(stack);
			}

		}

		value = stack.pop();

	}

	public double getValue() {

		if (!isResolved()) {

			throw new RuntimeException("Can not fetch value on unresolved expresssion");
		}
		return value;
	}
}
