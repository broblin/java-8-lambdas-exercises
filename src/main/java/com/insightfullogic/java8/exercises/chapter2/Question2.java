package com.insightfullogic.java8.exercises.chapter2;

import java.text.SimpleDateFormat;

public class Question2 {

    public static ThreadLocal<SimpleDateFormat> formatter
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));

}
