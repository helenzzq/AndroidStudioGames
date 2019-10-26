package com.example.myapplication;

public class TestCell {
    public int row;
    public int column;

    public TestCell(){
        row = 0;
        column = 0;
    }

    public TestCell(int r, int c){
        row = r;
        column = c;
    }

    public void set(int r, int c){
        row = r;
        column = c;
    }

    public boolean isEqualTo(TestCell cell){
        return row == cell.row && column == cell.column;
    }
}
