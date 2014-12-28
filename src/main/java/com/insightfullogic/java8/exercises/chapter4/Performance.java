package com.insightfullogic.java8.exercises.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/** A Performance by some musicians - e.g., an Album or Gig. */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians(){
        //essai 1 : en fait le "add" recherché était la méthode concat de Stream
        /*return getMusicians().flatMap(band -> {
            List<Artist> result = new ArrayList<>();
            result.add(band);
            result.addAll(band.getMembers().collect(Collectors.toList()));
            return result.stream();
        });*/
        return getMusicians().flatMap(band -> concat(Stream.of(band),band.getMembers()));
    }

}