package com.salpe.spreadsheetcalculator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import com.salpe.spreadsheetcalculator.exceptions.CyclicDependencyException;

public class SpreadSheetUnitTest {

	@Test
	public void testSuccess() throws URISyntaxException, IOException {

		URL url = this.getClass().getResource("test1.txt");

		FileInputProvider fileInputProvider = new FileInputProvider();

		fileInputProvider.process(new File(url.toURI()));

	}

	@Test(expected = CyclicDependencyException.class)

	public void testCyclicDependency() throws URISyntaxException, IOException {

		URL url = this.getClass().getResource("test2.txt");

		FileInputProvider fileInputProvider = new FileInputProvider();

		fileInputProvider.process(new File(url.toURI()));

	}

}
