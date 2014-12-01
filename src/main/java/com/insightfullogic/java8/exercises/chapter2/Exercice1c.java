package com.insightfullogic.java8.exercises.chapter2;

/**
 * Created by broblin on 01/12/14.
 */
public class Exercice1c<T,R> {

    public R calculate(R element,Function function){
        return (R) function.apply(element);
    }

    public static void main(String[] args){
        Exercice1c exemple = new Exercice1c<Long,Long>();
        Exercice1c exemple2 = new Exercice1c<Long,Boolean>();
        //voir aussi overload resolution en p45
        Function<Long,Long> add = x -> x+1;
        Function<Long,Boolean> compare =  x -> x==1;

        System.out.println(exemple.calculate(2L,add));
        System.out.println(exemple.calculate(2L,compare));
    }
}
