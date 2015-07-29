package com.revision.functionalExercices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by benoit on 26/07/15.
 */
public class FunctionalTestsLauncher {
    public static void main(String[] args){
        //forEach
        ArrayList<Long> aList = new ArrayList<>();
        aList.add(2L);
        aList.add(3L);
        aList.add(5L);
        aList.forEach(number -> System.out.println(String.format("forEach : %d",number)));

        //filter et count
        List<Long> filteredList = aList.stream().filter(number -> number > 2L).collect(Collectors.toList());
        long count = aList.stream().filter(number -> number > 2L).count(); //le stream ne peut être utilisé qu'une fois sinon IllegalStateException: stream has already been operated upon or closed
        filteredList.forEach(number -> System.out.println(String.format("filter : %d",number)));
        System.out.println(String.format("count : %d", count));

        //groupBy et countBy
        List<Livre> livres = new ArrayList<>();
        Livre livre1 = new Livre();
        Livre livre2 = new Livre();
        Livre livre3 = new Livre();
        livre1.setAuteur("Dantec");
        livre1.setTitre("Babylon babies");
        livre2.setAuteur("Dantec");
        livre2.setTitre("Vortex");
        livre3.setAuteur("Thilliez");
        livre3.setTitre("La chambre des mort");
        livre1.setCout(30);
        livre2.setCout(40);
        livre3.setCout(50);
        livres.add(livre1);
        livres.add(livre2);
        livres.add(livre3);

        System.out.println(String.format("countingBy : %s", livres.stream().collect(Collectors.groupingBy(Livre::getAuteur, Collectors.counting()))));
        System.out.println(String.format("groupBy : %s", livres.stream().collect(Collectors.groupingBy(Livre::getAuteur))));
        System.out.println(String.format("summingBy : %s", livres.stream().collect(Collectors.summingInt(Livre::getCout))));
        System.out.println(String.format("summingBy : %s", livres.stream().map(Livre::getAuteur).collect(Collectors.toList())));

        //anyMatch et everyMatch
        System.out.println(String.format("anyMatch : %b",livres.stream().anyMatch(livre -> livre.getCout() == 30)));
        //System.out.println(String.format("anyMatch : %d",livres.stream().everyMatch(livre -> livre.getCout() == 30)));

        //reduce
        System.out.println(String.format("reduce : %s", livres.stream().reduce(new Livre(), (result, livre) -> {
            if (result.getAuteur() == null) {
                result.setAuteur(livre.getAuteur());
            } else if (!result.getAuteur().contains(livre.getAuteur())) {
                result.setAuteur(result.getAuteur() + " " + livre.getAuteur());
            }
            return result;
        }).getAuteur()));
        System.out.println(String.format("reduce apres map : %s", livres.stream().map(livre -> livre.getAuteur()).reduce("", (result, auteur) -> {
            if(!result.isEmpty()){
                result += " ";
            }
            if (!result.contains(auteur)) {
                result += auteur;
            }
            return result;
        })));
        Map acc2 = livres.stream().reduce(new HashMap<String,String>(),
                (map,livre) -> {
                    if(!map.containsKey(livre.getAuteur())){
                        map.put(livre.getAuteur(),livre.getTitre());
                    }else{
                        String previous = map.get(livre.getAuteur());
                         previous += " " + livre.getTitre();
                        map.put(livre.getAuteur(),previous);
                    }
                    return map;
                },
                (map1,map2) -> {
            map1.putAll(map2);
            return map1;
        });
        System.out.println(String.format("reduce avec acc 1 : %s", acc2));
        System.out.println(String.format("reduce avec acc 2 : %d", livres.stream().reduce(0,(sum,livre)->sum+=livre.getCout(),(sum1,sum2)->sum1+sum2)));
    }

}
