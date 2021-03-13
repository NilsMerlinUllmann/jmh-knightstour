package nmu.jmh.knightstour.model;

import java.util.Objects;

public class Position {
	private final int row;
	private final int column;

	public Position(int rowIndex, int columnIndex) {
		this.row = rowIndex;
		this.column = columnIndex;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
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
		return column == other.column && row == other.row;
	}

}