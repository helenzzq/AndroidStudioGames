package com.example.myapplication.math24;

public class Math24Manager {
    static private int questions[][] = {
            {5,5,5,1},
            {2,7,9,10},
            {2,8,8,8},
            {3,3,3,3},
            {3,3,4,5},
            {4,4,5,7},
            {4,4,6,8},
            {5,7,8,9},
            {6,6,7,10},
            {6,7,8,9}};

    //create four random integers from 1 to 10
    public int[] createQuestion() {
        return questions[(int) (Math.random() * (10 - 1) + 1)];
    }

    //test if the result equals 24
    public boolean is24(int n){
        return n == 24;
    }


    //determine if the player's answer can make it to 24
    public static int calculate(String equation){
        return (int)Math.round(Calculator.getResult(equation));
    }

}
