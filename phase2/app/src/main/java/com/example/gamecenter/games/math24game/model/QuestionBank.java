package com.example.gamecenter.games.math24game.model;

class QuestionBank {
    private int[][] level1;
    private int[][] level2;

    QuestionBank() {

        level1 = new int[][]{
                {6, 6, 6, 6},
                {3, 8, 1, 1},
                {0, 8, 8, 8},
                {4, 5, 4, 0},
                {6, 4, 6, 6},
                {9, 9, 9, 4},
                {2, 8, 10, 4},
                {2, 6, 3, 4},
                {1, 1, 1, 24},
                {2, 3, 4, 1}};

        level2 = new int[][]{
                {6, 6, 8, 9},
                {4, 5, 5, 5},
                {4, 5, 5, 8}};

    }


    public int[] getRandomQ(String level){
        if (level.equals("easy")){
            return level1[(int)Math.random() * (level1.length - 1) + 1];
        }
        else{
            return level2[(int)(Math.random() * (level2.length - 1) + 1)];

        }


    }
}
