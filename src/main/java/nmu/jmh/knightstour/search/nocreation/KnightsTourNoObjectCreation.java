package nmu.jmh.knightstour.search.nocreation;

import java.util.ArrayList;
import java.util.Collection;

import nmu.jmh.knightstour.model.Move;
import nmu.jmh.knightstour.model.Position;

public class KnightsTourNoObjectCreation {

	private final PrimitiveBoard board;
	private final Position startPosition;

	private static final Move[] KNIGHT_MOVES = new Move[] { //
			new Move(1, -2), //
			new Move(1, 2), //
			new Move(2, -1), //
			new Move(2, 1), //
			new Move(-1, -2), //
			new Move(-1, 2), //
			new Move(-2, -1), //
			new Move(-2, 1) //
	};

	public KnightsTourNoObjectCreation(PrimitiveBoard board, Position startPosition) {
		this.board = board;
		this.startPosition = startPosition;
	}

	public boolean start() {
		solve(board, startPosition.row(), startPosition.column());
		return board.isEveryPositionVisited();
	}

	private void solve(PrimitiveBoard board, int row, int column) {
		board.visit(row, column);
		board.logState();
		Collection<Move> possibleMoves = computeMoves(board, row, column);
		if (!possibleMoves.isEmpty()) {
			Move bestMove = chooseNextMove(board, row, column, possibleMoves);
			solve(board, row + bestMove.rowOffset(), column + bestMove.columnOffset());
		}
	}

	private Collection<Move> computeMoves(PrimitiveBoard board, int row, int column) {
		Collection<Move> possibleMoves = new ArrayList<Move>(8);
		for (Move move : KNIGHT_MOVES) {
			if (board.isMoveValid(row + move.rowOffset(), column + move.columnOffset())) {
				possibleMoves.add(move);
			}
		}
		return possibleMoves;
	}

	private Move chooseNextMove(PrimitiveBoard board, int row, int column, Collection<Move> moves) {
		// the maximum amount of reachable positions for a knight is 8
		int minReachablePositions = 9;
		Move bestMove = null;
		for (Move move : moves) {
			int reachablePositions = computeMovesCount(board, row + move.rowOffset(), column + move.columnOffset());
			if (reachablePositions < minReachablePositions) {
				minReachablePositions = reachablePositions;
				bestMove = move;
			}
		}
		return bestMove;
	}

	private int computeMovesCount(PrimitiveBoard board, int row, int column) {
		int movesCount = 0;
		for (Move move : KNIGHT_MOVES) {
			if (board.isMoveValid(row + move.rowOffset(), column + move.columnOffset())) {
				movesCount++;
			}
		}
		return movesCount;
	}

}
