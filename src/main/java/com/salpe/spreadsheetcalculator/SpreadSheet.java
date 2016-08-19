package com.salpe.spreadsheetcalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.salpe.spreadsheetcalculator.common.Position;
import com.salpe.spreadsheetcalculator.exceptions.CyclicDependencyException;
import com.salpe.spreadsheetcalculator.parser.RefToken;

public class SpreadSheet {

	private final Cell[][] cells;

	private final int rows;

	private final int columns;

	private List<Cell> reslovedCells = new ArrayList<>();

	private Map<Position, Set<Position>> dependencyMultiMap = new HashMap<>();

	public SpreadSheet(int rows, int columns, Cell[][] cells) {
		this.rows = rows;
		this.columns = columns;
		this.cells = cells;
		init();
	}

	/** Process till there is no dependency map.
	 * If there is dependency in last means there is cycle .
	 * @throws CyclicDependencyException
	 */
	public void process() throws CyclicDependencyException {

		while (reslovedCells.size() > 0) {

			List<Cell> temp = new ArrayList<>();

			for (Iterator<Cell> iterator = reslovedCells.iterator(); iterator.hasNext();) {

				Cell cell = iterator.next();

				if (cell.isResolved()) {

					Set<Position> unResolvedRefs = dependencyMultiMap.get(cell.getPosition());

					if (unResolvedRefs != null) {

						for (Position position : unResolvedRefs) {

							Cell dependentCell = cells[position.getRow()][position.getColumn()];
							dependentCell.resolve(cell);
							if (dependentCell.isResolved()) {
								temp.add(dependentCell);
							}
						}

						dependencyMultiMap.remove(cell.getPosition());
					}

				}

				iterator.remove();

			}

			reslovedCells = temp;

		}

		if (!dependencyMultiMap.isEmpty()) {

			throw new CyclicDependencyException();

		}

	}

	private void init() {

		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < columns; j++) {

				Cell cell = cells[i][j];

				List<RefToken> refTokens = cell.getUnResolvedRef();

				if (refTokens.isEmpty()) {
					reslovedCells.add(cell);

				} else {

					for (RefToken refToken : refTokens) {

						if (dependencyMultiMap.get(refToken.getRefPosition()) == null) {

							Set<Position> positions = new HashSet<>();
							positions.add(new Position(i, j));

							dependencyMultiMap.put(refToken.getRefPosition(), positions);
						} else {

							Set<Position> positions = dependencyMultiMap.get(refToken.getRefPosition());
							positions.add(new Position(i, j));

							dependencyMultiMap.put(refToken.getRefPosition(), positions);

						}

					}

				}

			}

		}
	}

	Iterator<Cell> iterator() {

		return new Iterator<Cell>() {

			int i = 0;
			int j = 0;

			@Override
			public boolean hasNext() {

				if (i < rows) {
					return true;
				}

				return false;
			}

			@Override
			public Cell next() {

				Cell cell = cells[i][j];

				if (j < (columns-1)) {
					j++;
				} else {
					i++;
					j = 0;
				}

				return cell;
			}
		};
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

}
