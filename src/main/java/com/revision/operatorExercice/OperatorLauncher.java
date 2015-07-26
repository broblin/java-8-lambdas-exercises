package com.revision.operatorExercice;

import java.util.ArrayList;

/**
 * Created by benoit on 26/07/15.
 */
public class OperatorLauncher {
    public static long operate(long left, long right, IBinaryExpression expression) {
        return expression.execute(left, right);
    }


    public static void main(String[] args) {
        long resultSum = OperatorLauncher.operate(2, 3, (left, right) -> left + right);
        long resultPow = OperatorLauncher.operate(2, 3,  (left, right) -> {
            long result = 1;
            //pour l'exercice on n'utilise pas pow de math
            for (int i = 0; i < right; i++) {
                result *= left;
            }
            return result;
        });

        System.out.println(String.format("Somme : %d %d",resultSum,resultPow));
    }

}
