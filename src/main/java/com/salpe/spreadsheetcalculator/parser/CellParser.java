package com.salpe.spreadsheetcalculator.parser;

import java.util.LinkedList;

public class CellParser {
	
	public static final CellParser INSTANCE = new CellParser();
	
	private CellParser() {
		// TODO Auto-generated constructor stub
	}

	private final Tokenizer tokenizer = TokenizerImpl.INSTANCE;

	public LinkedList<Token> parser(String expressionStr) {

		LinkedList<Token> expressionList = new LinkedList<>();
		String[] tokens = expressionStr.split("\\s+");

		for (int i = 0; i < tokens.length; i++) {

			Token token = tokenizer.tokenize(tokens[i]);
			expressionList.add(token);

		}

		return expressionList;

	}
}
