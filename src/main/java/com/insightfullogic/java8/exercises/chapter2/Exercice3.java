package com.insightfullogic.java8.exercises.chapter2;

import javax.swing.*;

/**
 * Created by broblin on 03/12/14.
 */
public class Exercice3 {

    public static void main(String[] args){
        //oui
        Runnable helloWorld = () -> System.out.println("hello world");
        helloWorld.run();

        //oui
        JButton button = new JButton();
        button.addActionListener(event ->
                System.out.println(event.getActionCommand()));

    }
}
