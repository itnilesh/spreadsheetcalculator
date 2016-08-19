package com.salpe.spreadsheetcalculator.common;

/**
 * Identifier for cell;
 * 
 * @author nsalpe
 *
 */
public class Position {

	private final int row;
	private final int column;
	

	public Position(int row ,int column) {
		super();
		this.column = column;
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", column=" + column + "]";
	}


	
	

}
