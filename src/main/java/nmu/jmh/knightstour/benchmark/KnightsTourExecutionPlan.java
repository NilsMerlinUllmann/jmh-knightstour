package nmu.jmh.knightstour.benchmark;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import nmu.jmh.knightstour.model.Position;

@State(Scope.Benchmark)
public class KnightsTourExecutionPlan {

	@Param({ "16", "24", "32", "48" })
	public int boardDimension;

	public List<Position> startPosition = new ArrayList<>();

	@Setup(Level.Trial)
	public void setUp() {
		for (int i = 0; i < boardDimension; i++) {
			for (int j = 0; j < boardDimension; j++) {
				startPosition.add(new Position(i, j));
			}
		}
	}

}
