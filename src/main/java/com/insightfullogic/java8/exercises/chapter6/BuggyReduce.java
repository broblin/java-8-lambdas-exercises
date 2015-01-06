package com.insightfullogic.java8.exercises.chapter6;

import java.util.Arrays;
import java.util.List;

public class BuggyReduce {

    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        //return linkedListOfNumbers.stream()
        //return linkedListOfNumbers.parallelStream()
        //                          .reduce(5, (acc, x) -> x * acc);
        /*
        //correction du bug :
        return 5 * numbers.parallelStream()
                          .reduce(1, (acc, x) -> x * acc);
        */
        Integer[] multiplyTab = linkedListOfNumbers.toArray(new Integer[linkedListOfNumbers.size()]);
        Arrays.parallelPrefix(multiplyTab,(acc, x) -> x * acc);
        return 5*multiplyTab[multiplyTab.length-1];
    }

}
