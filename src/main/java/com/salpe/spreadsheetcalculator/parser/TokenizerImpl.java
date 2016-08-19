package com.salpe.spreadsheetcalculator.parser;

public class TokenizerImpl implements Tokenizer {

	public static final Tokenizer INSTANCE = new TokenizerImpl();

	private TokenizerImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Token tokenize(String rawString) {

		if (rawString.matches("[\\+\\-\\*\\/]")) {

			return new OperatorToken(rawString);

		} else if (rawString.matches("[a-zA-Z]\\d+")) {

			return new RefToken(rawString);

		} else if (rawString.matches("\\d+")) {

			return new NumberToken(rawString);

		} else {

			throw new IllegalArgumentException("Unable to parse to token");
		}

	}

}
