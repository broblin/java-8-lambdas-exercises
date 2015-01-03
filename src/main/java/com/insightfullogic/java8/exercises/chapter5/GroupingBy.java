package com.insightfullogic.java8.exercises.chapter5;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final Function<? super T, ? extends K> classifier;

    public GroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return () -> new HashMap<>();
    }

    //prend un éllément de départ et retourne une nouvelle valeur, ex artiste -> Map<nom,artiste>
    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, object) -> {
            //pas mal mais on peut faire mieux lorsqu'on connait la méthode computeIfAbsent :)
            /*
            K key = classifier.apply(object);
            if(map.containsKey(key)){
                List<T> liste = map.get(key);
                liste.add(object);
            }else{
                List<T> liste = new ArrayList<>();
                liste.add(object);
                map.put(key,liste);
            }
            */
            K key = classifier.apply(object);
            List<T> elements = map.computeIfAbsent(key, k -> new ArrayList<>());
            elements.add(object);
        };
    }

    //merge 2 éléments
    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        //il faut assurer le merge entre les listes lorsque la map1 a key,liste1 t la map2 a key,liste2
        //sinon on écrase la valeur liste1 par liste2
        /*
        return (map1,map2) -> {
            map1.putAll(map2);
            return map1;
        };
        */
        return (map1, map2) -> {
            map2.forEach((key, value) -> {
                map1.merge(key, value, (leftValue, rightValue) -> {
                    leftValue.addAll(rightValue);
                    return leftValue;
                });
            });
            return map1;
        };
    }

    //termine une opération, ici on revoit la même chose
    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map -> map;
    }

    //paramètres en vue d'optimisation de l'algorithme
    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristicses = new HashSet<>();
        characteristicses.add(Characteristics.IDENTITY_FINISH);
        return characteristicses;
    }

}
