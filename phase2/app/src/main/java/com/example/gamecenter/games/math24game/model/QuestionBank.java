package com.example.gamecenter.games.math24game.model;

class QuestionBank {
    private int[][] level1;
    private int[][] level2;

    QuestionBank() {

        level1 = new int[][]{
                {6, 6, 6, 6},
                {0, 8, 8, 8},
                {4, 0, 10, 10},
                {1,8,8,8},
                {4,6,1,1},
                {4,6,2,1},
                {1, 1, 1, 24},
                {2, 3, 4, 1}};

        level2 = new int[][]{
                {3,8,5,5},
                {2, 8, 10, 4},
                {2, 6, 3, 4},
                {6, 6, 8, 9},
                {4, 5, 5, 5},
                {4, 5, 5, 8}};

    }


    int[] getRandomQ(boolean nextLevel){
        if (!nextLevel){
            return level1[(int)(Math.random() * (level1.length - 1) + 0.5)];
        }
        else{
            return level2[(int)(Math.random() * (level2.length - 1) + 1)];

        }


    }
}
