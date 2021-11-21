package nmu.jmh.knightstour.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import nmu.jmh.knightstour.model.Position;
import nmu.jmh.knightstour.search.basic.Board;
import nmu.jmh.knightstour.search.basic.KnightsTour;
import nmu.jmh.knightstour.search.nocreation.KnightsTourNoObjectCreation;
import nmu.jmh.knightstour.search.nocreation.PrimitiveBoard;

@Warmup(iterations = 3)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2)
@BenchmarkMode(Mode.AverageTime)
public class KnightsTourBenchmark {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder() //
				.include(KnightsTourBenchmark.class.getSimpleName()) //
				.build();
		new Runner(opt).run();
	}

	@Benchmark
	public void tour(Blackhole blackhole, KnightsTourExecutionPlan plan) {
		for (Position startPos : plan.startPosition) {
			Board board = new Board(plan.boardDimension, plan.boardDimension);
			KnightsTour tour = new KnightsTour(board, startPos);
			boolean solutionFound = tour.start();
			blackhole.consume(solutionFound);
		}
	}

	@Benchmark
	public void tourNoObj(Blackhole blackhole, KnightsTourExecutionPlan plan) {
		for (Position startPos : plan.startPosition) {
			PrimitiveBoard board = new PrimitiveBoard(plan.boardDimension, plan.boardDimension);
			KnightsTourNoObjectCreation tour = new KnightsTourNoObjectCreation(board, startPos);
			boolean solutionFound = tour.start();
			blackhole.consume(solutionFound);
		}
	}

}
