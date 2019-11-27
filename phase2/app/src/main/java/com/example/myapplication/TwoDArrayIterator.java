package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TwoDArrayIterator implements Iterator<Integer> {

    // Set local variables
    private int[][] TwoDArray;
    private int pos = 0; // The position of our cursor
    private List resultList;// What we are going to return
    private int max;// Maximum iteration
    private boolean getRemove;
    private final int firstIndex = 0;

    // The default constructor
    public TwoDArrayIterator(int[][] TwoDArray) {
        this.TwoDArray = TwoDArray;
        this.getRemove = false;
        copyArray(TwoDArray);
    }

    /**
     * Move through array while hasNext() is true
     */
    @Override
    public boolean hasNext() {
        return pos < max; // Start at 0 and move up
    }

    /**
     * When called will print all the values in the 2D
     * Array
     */
    @Override
    public Integer next() throws NoSuchElementException {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // Get value then remove it from the resultList
        Integer result = (Integer) resultList.get(firstIndex);
        pos++;
        getRemove = true; // Remove values from the List
        remove();
        return result;
    }

    /**
     * Set max iterations based on the number of int values in
     * the initial 2D Array
     */
    public void setMaxIterations(List list) {
        if (list != null) {
            max = list.size();
        }
    }

    /**
     * Copy values into the collection while skipping over
     * null values with a nested for-loop
     */
    private void copyArray(int[][] array) {
        resultList = new ArrayList<Integer>();

        // Loop though all values
        for (int[] data : array) {
            for (int val : data) {
                resultList.add(val);
            }
        }

        // Set total number of iterations
        setMaxIterations(getResultList());
    }

    /**
     * Remove previous element returned by iterator
     */
    public void remove() {
        if (getRemove) {
            resultList.remove(firstIndex);
            getRemove = false;
        }
    }

    public List getResultList() {
        return this.resultList;
    }

}
//// Test the code
//public static void main(String[] args) {
//
//        // Create some 2D Array
//        int[][] Array2D = { {1, 2}, {3}, {} ,
//        {5, 6}, {}, {7, 8, 9, 10} };
//        // Create the class to iterate through the 2D Array
//        TwoDArrayIterator ai = new TwoDArrayIterator(Array2D);
//
//        while(ai.hasNext()) {
//        System.out.println(ai.next());
//        }
//
//        // Test the List is empty
//        System.out.println(ai.getResultList());
//
//        }
//
//        }
// Output from our ArrayIterator class
// 1
// 2
// 3
// 4

// 10
// Last the empty resultList will print
// [] 