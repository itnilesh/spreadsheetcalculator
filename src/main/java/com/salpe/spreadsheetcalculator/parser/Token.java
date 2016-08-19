package com.salpe.spreadsheetcalculator.parser;

/**
 * Basic token to be hold in stack for math operations 
 * @author nsalpe
 *
 */
public abstract class Token {

	private final String token;

	protected Token(String token) {
		super();
		validate(token);
		this.token = token.trim();
	}

	private void validate(String token) {
		if (token == null) {
			throw new IllegalArgumentException("Token can not be null");
		}

		if (token.isEmpty()) {
			throw new IllegalArgumentException("Token can not be empty");
		}
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + "]";
	}
	
	

}
