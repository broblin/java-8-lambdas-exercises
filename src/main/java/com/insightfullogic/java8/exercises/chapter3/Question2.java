package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.ArrayList;
import java.util.List;

public class Question2 {
    // Q3
    public static int countBandMembersInternal(List<Artist> artists) {
        return (int) artists.stream().mapToLong(artist -> artist.getMembers().count()).reduce((a,b) -> a+b).getAsLong();
    }

    public static void main(String[] args){
        List<Artist> artistes = new ArrayList<>();
        List<Artist> members = new ArrayList<>();
        List<Artist> members2 = new ArrayList<>();

        members.add(new Artist("Bruce Dickinson","Angleterre"));
        members.add(new Artist("Steve Harris","Angleterre"));
        members.add(new Artist("Dave Murray","Angleterre"));

        members2.add(new Artist("Joseph Duplantier","France"));
        members2.add(new Artist("Mario Duplantier","France"));

        Artist artist1 = new Artist("Iron Maiden",members," Angleterre");
        Artist artist2 = new Artist("Gojira",members2,"France");

        artistes.add(artist1);
        artistes.add(artist2);
        System.out.println(countBandMembersInternal(artistes));
    }
}
