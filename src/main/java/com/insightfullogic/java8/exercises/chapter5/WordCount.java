package com.insightfullogic.java8.exercises.chapter5;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    public static Map<String, Long> countWords(Stream<String> names) {
        return names.collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

}
