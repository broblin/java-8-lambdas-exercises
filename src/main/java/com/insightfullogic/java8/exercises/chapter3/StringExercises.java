package com.insightfullogic.java8.exercises.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StringExercises {

    // Question 7
    public static int countLowercaseLetters(String string) {
        //autre solution : (int) string.chars().filter(Character::isLowerCase).count();
        return (int) string.chars().filter(intValue -> Character.isLowerCase(intValue)).count();
    }

    // Question 8
    public static Optional<String> mostLowercaseString(List<String> strings) {
        //essai 2 : le problème 2 boucles for et 2 appels à countLowercaseLetters pour chaque string
        //OptionalInt previousMaxResult = strings.stream().mapToInt(string -> countLowercaseLetters(string)).max();
        //return strings.stream().filter(string -> countLowercaseLetters(string) == previousMaxResult.getAsInt()).findFirst();

        //autre solution : strings.stream().max(Comparator.comparing(StringExercises::countLowercaseLetters));
        return strings.stream().max((String string1,String string2) -> countLowercaseLetters(string1) - countLowercaseLetters(string2));
    }

    public static Optional<String> mostLowercaseStringOldSchool(List<String> strings) {
        Optional<String> result = Optional.empty();
        int previousMaxResult = 0;
        for(String string : strings){
            int countLowercaseLetters = countLowercaseLetters(string);
            if(countLowercaseLetters > previousMaxResult){
                previousMaxResult = countLowercaseLetters;
                result = Optional.of(string);
            }
        }
        return result;
    }

    public static void main(String[] args){
        String string = "WithLowerChar";
        String string2="ASerTY";
        String string3="AzerT";
        System.out.println(StringExercises.countLowercaseLetters(string));

        List<String> strings = new ArrayList<>();
        strings.add(string);
        strings.add(string2);
        strings.add(string3);
        System.out.println(StringExercises.mostLowercaseStringOldSchool(strings));
        System.out.println(StringExercises.mostLowercaseString(strings));

    }

}
