package com.insightfullogic.java8.exercises.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Artists {

    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }else{
            return Optional.of(artists.get(index));
        }
    }

    public String getArtistName(int index) {
        //essai 1 : pas mal mais on peut mieux faire, la preuve ci dessous !
        /*try {
            return getArtist(index).get().getName();
        } catch (NoSuchElementException e) {
            return "unknown";
        }*/
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName)
                .orElse("unknown");
    }

    public static void main(String[] args){
        System.out.println(SampleData.theBeatles.getMembers().collect(Collectors.toList()));
        System.out.println(SampleData.yellowSubmarine.getAllMusicians().collect(Collectors.toList()));
        System.out.println(SampleData.compilationAlbum.getAllMusicians().collect(Collectors.toList()));
    }

}
