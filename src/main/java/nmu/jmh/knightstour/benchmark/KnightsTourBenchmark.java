package nmu.jmh.knightstour.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import nmu.jmh.knightstour.model.Board;
import nmu.jmh.knightstour.model.Position;
import nmu.jmh.knightstour.search.KnightsTour;

@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 1)
@BenchmarkMode(Mode.AverageTime)
public class KnightsTourBenchmark {

	// set forks to 0 for sampling
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder() //
				.include(KnightsTourBenchmark.class.getSimpleName()) //
				.forks(1) //
				.build();
		new Runner(opt).run();
	}

	@Benchmark
	public void regularTour(KnightsTourExecutionPlan plan) {
		for (Position startPos : plan.startPosition) {
			Board board = new Board(plan.boardDimension, plan.boardDimension);
			KnightsTour tour = new KnightsTour(board, startPos);
			tour.start();
		}
	}

}
