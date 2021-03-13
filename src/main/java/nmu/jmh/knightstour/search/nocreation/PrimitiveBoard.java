package nmu.jmh.knightstour.search.nocreation;

public class PrimitiveBoard {
	private static final boolean LOG_ENABLED = false;
	private static final int VISITED = 1;
	private static final int UNVISITED = 0;

	private final int[][] state;
	private final int[][] moveOrder;
	private final int maxRowIndex;
	private final int maxColumnIndex;
	private int moves = 0;

	public PrimitiveBoard(int rows, int columns) {
		this.maxRowIndex = rows - 1;
		this.maxColumnIndex = columns - 1;
		state = new int[rows][columns];
		moveOrder = new int[rows][columns];
	}

	public void visit(int row, int column) {
		state[row][column] = VISITED;
		moveOrder[row][column] = moves++;
	}

	public boolean isMoveValid(int row, int column) {
		return isPositionValid(row, column) && isPositionUnvisited(row, column);
	}

	private boolean isPositionValid(int row, int column) {
		return row <= maxRowIndex //
				&& row >= 0 //
				&& column <= maxColumnIndex //
				&& column >= 0;
	}

	private boolean isPositionUnvisited(int row, int column) {
		return state[row][column] == UNVISITED;
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
		return number == 0 ? 1 : (int) (Math.log10(number) + 1);
	}
}
