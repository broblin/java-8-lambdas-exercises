package com.insightfullogic.java8.exercises.chapter6;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Just run this class's main method and it will time benchmarks using the harness
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public class OptimisationExample {

    public static void main(String[] ignore) throws IOException, RunnerException {
        final String[] args = {
                ".*OptimisationExample.*",
                "-wi",
                "10",
                "-i",
                "10",
                "-f",
                "1"
        };
        Main.main(args);
    }

    private List<Integer> linkedListOfNumbers;
    private List<Integer> arrayListOfNumbers;

    @Setup
    public void init() {
        linkedListOfNumbers = new LinkedList<>();
        addNumbers(linkedListOfNumbers);

        // l'arrayList gère mieux la parallélisation qu'un linkedList
        arrayListOfNumbers= new ArrayList<>();
        addNumbers(arrayListOfNumbers);
    }

    private void addNumbers(List<Integer> container) {
        IntStream.range(0, 1_000_000)
                 .forEach(container::add);
    }

    @GenerateMicroBenchmark
    // BEGIN slowSumOfSquares
    public int slowSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
                                  .map(x -> x * x)
                                  .reduce(0, (acc, x) -> acc + x);
    }
    // END slowSumOfSquares

    @GenerateMicroBenchmark
    public int fastSumOfSquares() {
        //mapToItn utilise la primitive int et non l'enveloppe correspondante qui prend plus de temps
        return arrayListOfNumbers.parallelStream()
                .mapToInt(x -> x * x)
                .sum();
    }

}
