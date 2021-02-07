package nmu.jmh.knightstour.model;

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

}