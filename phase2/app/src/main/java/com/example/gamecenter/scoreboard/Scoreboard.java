package com.example.gamecenter.scoreboard;

import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Scoreboard implements Serializable, MySubject {

    private ArrayList<Score> GlobalScore;

    private static List<MyObserver> observers;

    public Scoreboard(){
        GlobalScore = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public Scoreboard(ArrayList<Score> scores){
        GlobalScore = scores;
        observers = new ArrayList<>();
    }

    public ArrayList<Score> getGlobalScore(){
        return GlobalScore;
    }

    public void setGlobalScore(ArrayList<Score> globalScore) {
        GlobalScore = globalScore;
    }

    //public void sortScores(ArrayList<Score> scores){
      // Collections.sort(scores);
    //}

    /**
     * A sortScores() helper method that merges the left and right lists.
     * This code was adapted from Codexpedia's post on merge sort in 2016 retrieved on 11052018
     * from: https://www.codexpedia.com/java/java-merge-sort-implementation/
     * @param left list on the left to be merged
     * @param right list on the right to be merged
     * @param whole full list
     */
   private void merge(ArrayList<Score> left, ArrayList<Score> right, ArrayList<Score> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).compareTo(right.get(rightIndex))) > 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<Score> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    /**
     * Returns the original ArrayList sorted. This code was adapted from Codexpedia's post on merge
     * sort in 2016 retrieved on 11052018.
     * from: https://www.codexpedia.com/java/java-merge-sort-implementation/
     * @param scores unsorted ArrayList of scores
     * @return scores sorted ArrayList of scores
     */
    public ArrayList<Score> sortScores(ArrayList<Score> scores){
        ArrayList<Score> left = new ArrayList<>();
        ArrayList<Score> right = new ArrayList<>();
        int center;

        if (scores.size() == 1) {
            return scores;
        } else {
            center = scores.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                left.add(scores.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i=center; i<scores.size(); i++) {
                right.add(scores.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left  = sortScores(left);
            right = sortScores(right);

            // Merge the results back together.
            merge(left, right, scores);
        }
        return scores;
    }

    public String getScoreValues(boolean userScoresOnly,User currentPlayer){

        ArrayList<Score> scoreList;
        int numScores;

        scoreList = GlobalScore;


        if (userScoresOnly) {
            scoreList = getUserScoreboard(currentPlayer);
        }
        else {
            scoreList = GlobalScore;
        }

        if (scoreList.size() < 5) {
            numScores = scoreList.size();
        }
        else {
            numScores = 5;
        }

        // ListIterator to traverse the list
       // ListIterator iterator = GlobalScore.listIterator();

        StringBuilder scoreValues = new StringBuilder();
        for (int i = 0; i < numScores; i++) {
            Score currentItem = scoreList.get(i);
            scoreValues.append(String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore())).append("\n");
        }
        return scoreValues.toString();
    }
    


    public void addScore(String currentPlayer, int score){
        Score s = new Score(currentPlayer,score);
        GlobalScore.add(s);
        sortScores(GlobalScore);
        notifyObservers();
    }

    public ArrayList<Score> getGlobalScoreboard(){
        return GlobalScore;
    }

    /**
     * Register the MyObserver object to observe
     *
     * @param obj to register
     */
    @Override
    public void register(MyObserver obj) {
        if (obj == null)
            throw new NullPointerException();
        if (!observers.contains(obj)) {
            observers.add(obj);
            obj.setSubject(this);
        }

    }
        @Override
        public void notifyObservers(){
            for (MyObserver obj : observers) {
                obj.update();
            }
    }

    /**
     * Returns an ArrayList representing the users scoreboard for sliding tiles game.
     * @return UserScores
     */
    public ArrayList<Score> getUserScoreboard(User current_player){
        ArrayList<Score> UserScores = new ArrayList<>();
        for(int i = 0; i < GlobalScore.size(); i++){
            if(GlobalScore.get(i).getUsername().equals(current_player.getUsername())){
                UserScores.add(GlobalScore.get(i));
            }
        }
        return UserScores;
    }

}