package com.insightfullogic.java8.exercises.chapter5;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private final Map<Integer,Long> cache;

    public Fibonacci() {
        cache = new HashMap<>();
        cache.put(0, 0L);
        cache.put(1, 1L);
    }

    //computeIfAbsent fait office d'un get mais gérant en plus le cas où la map ne contienne pas la clef
    public long fibonacci(int x) {
        return cache.computeIfAbsent(x, n -> fibonacci(n-1) + fibonacci(n-2));
    }


    public long fibonacciSansCache(int x) {
        Map<Integer,Long> map = new HashMap<>();
        for(int i =0;i <= x;i++){
            map.computeIfAbsent(i,number -> number >= 2 ? map.get(number-1) + map.get(number-2) : number);
        }
        return map.get(x);
    }

    //avant java 8, pour voir l'apport de la programmation fonctionnelle
    public long fibonacciOldSchool(int x) {
        Long leftValue = 1L;
        Long rightValue = 0L;
        Long result = 0L;
        for(int i =2;i <= x;i++){
            result = leftValue + rightValue;
            rightValue = leftValue;
            leftValue = result;
        }
        return x > 1 ? result : x;
    }

}
