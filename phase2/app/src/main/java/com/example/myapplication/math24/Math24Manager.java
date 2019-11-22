package com.example.myapplication.math24;

public class Math24Manager {

    int addScore;
    static private int question_level1[][] = {
            {6,6,6,6},
            {3,8,1,1},
            {0,8,8,8},
            {4,5,4,0},
            {6,4,6,6},
            {9,9,9,4},
            {2,8,10,4},
            {2,6,3,4},
            {1,1,1,24},
            {2,3,4,1}};
    static private int question_level2[][] = {
            {3,3,8,9},
            {3,3,3,10},
            {3,3,4,4},
            {6,6,6,9},
            {6,8,9,10}};

    static private int question_level3[][] = {
            {6,6,8,9},
            {4,5,5,5},
            {4,5,5,8}};

    private int setAddScore(int coefficient){
        return addScore * coefficient;
    }

    //create four random integers from 1 to 10
    public int[] createQuestion_level1() {
        addScore = setAddScore(1);
        return question_level1[(int) (Math.random() * (question_level1.length - 1) + 1)];
    }

    public int[] createQuestion_level2(){
        addScore = setAddScore(2);
        return question_level2[(int)(Math.random() * (question_level2.length - 1) + 1)];
    }

    public int[] createQuestion_level3(){
        addScore = setAddScore(4);
        return question_level3[(int)(Math.random() * (question_level3.length - 1) + 1)];
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
