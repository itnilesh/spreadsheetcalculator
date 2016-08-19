package com.salpe.spreadsheetcalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputProvider {

	private StreamInputProvider streamInputProvider = new StreamInputProvider();

	public void process(File file) throws IOException {

		try (FileInputStream in = new FileInputStream(file)) {

			streamInputProvider.process(in);

		}
	}

}
