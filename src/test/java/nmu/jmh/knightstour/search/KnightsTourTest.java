package nmu.jmh.knightstour.search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import nmu.jmh.knightstour.model.Position;
import nmu.jmh.knightstour.search.basic.Board;
import nmu.jmh.knightstour.search.basic.KnightsTour;

class KnightsTourTest {

	@Test
	void test5x5ValidStart_SHOULD_findSolution() {
		// arrange
		Board board = new Board(5, 5);
		Position startPos = new Position(0, 0);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(true));
	}

	@Test
	void test5x5UnsolvableStart_SHOULD_findNoSolution() {
		// arrange
		Board board = new Board(5, 5);
		Position startPos = new Position(0, 1);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(false));
	}

	@Test
	void test5x5UnsolvableStart_2_SHOULD_findNoSolution() {
		// arrange
		Board board = new Board(5, 5);
		Position startPos = new Position(1, 0);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(false));
	}

	@Test
	void test8x8_SHOULD_findSolution() {
		// arrange
		Board board = new Board(8, 8);
		Position startPos = new Position(0, 0);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(true));
	}

	@Test
	void test8x8_2_SHOULD_findSolution() {
		// arrange
		Board board = new Board(8, 8);
		Position startPos = new Position(1, 3);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(true));
	}

	@Test
	void test8x8_3_SHOULD_findSolution() {
		// arrange
		Board board = new Board(8, 8);
		Position startPos = new Position(7, 7);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(true));
	}

	@Test
	void testUnsolvable5x5_SHOULD_findNoSolution() {
		// arrange
		Board board = new Board(1, 5);
		Position startPos = new Position(0, 1);
		KnightsTour tour = new KnightsTour(board, startPos);
		// act & assert
		assertThat(tour.start(), is(false));
	}

}
