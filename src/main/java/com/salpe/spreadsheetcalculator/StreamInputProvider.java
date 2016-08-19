package com.salpe.spreadsheetcalculator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import com.salpe.spreadsheetcalculator.common.Position;

/**
 * Takes input either from console or from file
 * 
 * @author nsalpe
 *
 */
public class StreamInputProvider {

	public void process(InputStream inputStream) throws FileNotFoundException {

		Cell[][] cells = null;

		int rows = 0;
		int columns = 0;
		try (Scanner scanner = new Scanner(inputStream))

		{

			columns = scanner.nextInt();
			rows = scanner.nextInt();

			if (columns <= 0 || rows <= 0) {
				throw new IllegalArgumentException("Zero or negative number of rows or columns");
			}

			scanner.nextLine();

			cells = new Cell[rows][columns];

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {

					cells[i][j] = new Cell(new Position(i, j), scanner.nextLine());
				}
			}
		}

		SpreadSheet spreadSheet = new SpreadSheet(rows, columns, cells);
		spreadSheet.process();

		OutputPrinter outputPrinter = new OutputPrinter(spreadSheet);
		outputPrinter.print();

	}

}
