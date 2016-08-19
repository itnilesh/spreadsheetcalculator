package com.salpe.spreadsheetcalculator;

import java.util.Iterator;

public class OutputPrinter {

	private SpreadSheet sheet;

	public OutputPrinter(SpreadSheet sheet) {
		super();
		this.sheet = sheet;
	}

	public void print() {

		System.out.println(sheet.getRows() + " " + sheet.getColumns());

		Iterator<Cell> iterator = sheet.iterator();

		while (iterator.hasNext()) {

			System.out.println(String.format("%.5f", iterator.next().getValue()));
		}
	}

}
