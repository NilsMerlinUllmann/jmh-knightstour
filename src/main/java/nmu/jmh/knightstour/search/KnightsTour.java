package nmu.jmh.knightstour.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import nmu.jmh.knightstour.model.Board;
import nmu.jmh.knightstour.model.Position;

public class KnightsTour {

	private Board board;
	private Position startPosition;

	public KnightsTour(Board board, Position startPosition) {
		this.board = board;
		this.startPosition = startPosition;
	}

	public boolean start() {
		solve(board, startPosition);
		boolean validTourFound = board.isEveryPositionVisited();
		return validTourFound;
	}

	private void solve(Board board, Position currentPosition) {
		board.visit(currentPosition);
		board.logState();
		Collection<Position> possibleMoves = computeMoves(board, currentPosition);
		if (!possibleMoves.isEmpty()) {
			Position bestMove = chooseNextMove(board, possibleMoves);
			solve(board, bestMove);
		}
	}

	private Collection<Position> computeMoves(Board board, Position currentPos) {
		Collection<Position> nextPositions = new ArrayList<>(8);
		int row = currentPos.getRow();
		int column = currentPos.getColumn();

		// 1 down, 2 left
		checkAndAddMove(board, new Position(row + 1, column - 2), nextPositions::add);
		// 1 down, 2 right
		checkAndAddMove(board, new Position(row + 1, column + 2), nextPositions::add);
		// 2 down, 1 left
		checkAndAddMove(board, new Position(row + 2, column - 1), nextPositions::add);
		// 2 down, 1 right
		checkAndAddMove(board, new Position(row + 2, column + 1), nextPositions::add);
		// 1 up, 2 left
		checkAndAddMove(board, new Position(row - 1, column - 2), nextPositions::add);
		// 1 up, 2 right
		checkAndAddMove(board, new Position(row - 1, column + 2), nextPositions::add);
		// 2 up, 1 left
		checkAndAddMove(board, new Position(row - 2, column - 1), nextPositions::add);
		// 2 up, 1 right
		checkAndAddMove(board, new Position(row - 2, column + 1), nextPositions::add);

		return nextPositions;
	}

	private void checkAndAddMove(Board board, Position targetPosition, Consumer<Position> consumer) {
		if (board.isMoveValid(targetPosition)) {
			consumer.accept(targetPosition);
		}
	}

	private Position chooseNextMove(Board board, Collection<Position> moves) {
		// the maximum amount of reachable positions for a knight is 8
		int minReachablePositions = 9;
		Position bestMove = null;
		for (Position move : moves) {
			int reachablePositions = computeMoves(board, move).size();
			if (reachablePositions < minReachablePositions) {
				minReachablePositions = reachablePositions;
				bestMove = move;
			}
		}
		return bestMove;
	}

}
