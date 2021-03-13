package nmu.jmh.knightstour.model;

import java.util.Objects;

public class Move {
	private final int rowOffset;
	private final int columnOffset;

	public Move(int rowOffset, int columnOffset) {
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}

	public int getRowOffset() {
		return rowOffset;
	}

	public int getColumnOffset() {
		return columnOffset;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnOffset, rowOffset);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		return columnOffset == other.columnOffset && rowOffset == other.rowOffset;
	}

}