package com.salpe.spreadsheetcalculator;

import java.io.IOException;

/**
 *  Takes input from console
 * @author nsalpe
 *
 */
public class ConsoleInputProvider {

	private StreamInputProvider streamInputProvider = new StreamInputProvider();

	public void process() throws IOException {
		
		streamInputProvider.process(System.in);
	}


}
