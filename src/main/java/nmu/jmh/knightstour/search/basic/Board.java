package nmu.jmh.knightstour.search.basic;

import nmu.jmh.knightstour.model.Position;

/**
 * Representation of a chess board holding a state with visited and unvisited
 * positions
 * 
 * @author NilsMerlinUllmann
 *
 */
public class Board {
	private static final boolean LOG_ENABLED = false;
	private static final int VISITED = 1;
	private static final int UNVISITED = 0;

	private final int[][] state;
	private final int[][] moveOrder;
	private final int maxRowIndex;
	private final int maxColumnIndex;
	private int moves = 0;

	public Board(int rows, int columns) {
		this.maxRowIndex = rows - 1;
		this.maxColumnIndex = columns - 1;
		state = new int[rows][columns];
		moveOrder = new int[rows][columns];
	}

	public void visit(Position position) {
		state[position.row()][position.column()] = VISITED;
		moveOrder[position.row()][position.column()] = moves++;
	}

	public boolean isMoveValid(Position target) {
		return isPositionValid(target) && isPositionUnvisited(target);
	}

	private boolean isPositionValid(Position position) {
		return position.row() <= maxRowIndex //
				&& position.row() >= 0 //
				&& position.column() <= maxColumnIndex //
				&& position.column() >= 0;
	}

	private boolean isPositionUnvisited(Position position) {
		return state[position.row()][position.column()] == UNVISITED;
	}

	public boolean isEveryPositionVisited() {
		for (int row = 0; row <= maxRowIndex; row++) {
			for (int column = 0; column <= maxColumnIndex; column++) {
				if (state[row][column] == UNVISITED) {
					return false;
				}
			}
		}
		return true;
	}

	public void logState() {
		if (LOG_ENABLED) {
			for (int row = 0; row <= maxRowIndex; row++) {
				for (int column = 0; column <= maxColumnIndex; column++) {
					System.out.print(state[row][column]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public void logMoveOrder() {
		for (int row = 0; row <= maxRowIndex; row++) {
			for (int column = 0; column <= maxColumnIndex; column++) {
				int move = moveOrder[row][column];
				System.out.print(" ".repeat(5 - digits(move)) + move);
			}
			System.out.println();
		}
		System.out.println();
	}

	private int digits(int number) {
		return String.valueOf(number).length();
	}
}
