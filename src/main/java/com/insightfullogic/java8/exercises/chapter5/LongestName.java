package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class LongestName {

    public static Artist byReduce(List<Artist> artists) {
        //si la valeur est absente (liste vide) lève une exception de type NoSuchElementException
        return artists.stream().reduce(BinaryOperator.maxBy(Comparator.comparingInt(a -> a.getName().length()))).get();
    }

    public static Artist byCollecting(List<Artist> artists) {
        Comparator<Artist> nameByLengthComparator = Comparator.comparingInt(a -> a.getName().length());
        //le collect retourne un optional, la méthode orElseThrow  lève une exception (orElseGet existe aussi ...)
        return artists.stream().collect(Collectors.maxBy(nameByLengthComparator)).orElseThrow(RuntimeException::new);
    }

}
