package com.example.ottot.mineseeker;

import java.util.Random;

/**
 * Created by ottot on 2/10/2017.
 */

public class table {
    private int tableCols; //width of the table, x value
    private int tableRows; //height of the table, y value
    private int[][] allBlocks;
    private int[] mineOfEachRows;
    private int[] mineOfEachCols;

    //initialization functions
    public table(int tableCols, int tableRows) {
        this.tableCols = tableCols;
        this.tableRows = tableRows;
        allBlocks = new int[tableRows][tableCols];
        for (int i = 0;i<tableRows;i++){
            for (int j = 0;j<tableCols;j++){
                Random rg = new Random();
                allBlocks[i][j] = rg.nextInt(2);
            }
        }
        mineOfEachCols = new int[tableCols];
        mineOfEachRows = new int[tableRows];
        searchMines();
    }

    public void searchMines(){
        for (int i = 0; i< tableRows; i++){
            for (int j = 0; j< tableCols; j++){
                if (allBlocks[i][j]==1){
                    mineOfEachRows[i]++;
                    mineOfEachCols[j]++;
                }
            }
        }
    }
    //--------------------------------

    //User interaction functions
    //called when user taps a block
    public int guessMine(int rowIndex, int colIndex){ //pass in the coordinate of the guessed block, determines if it's a hit or miss
        if (allBlocks[rowIndex][colIndex]==1){
            mineOfEachRows[colIndex]--;
            mineOfEachCols[rowIndex]--;
            return 1;
        }
        else return 0;
    }
    //called to get the number on the block, which is the sum of mines in the row and column
    public int numOfMines(int rowIndex, int colIndex){
        return mineOfEachCols[rowIndex] + mineOfEachRows[colIndex];
    }
    //----------------------------------


    public void printTable(){
        for (int i = 0;i<tableRows;i++){
            for (int j = 0;j<tableCols;j++){
                System.out.print(allBlocks[i][j]);
            }
            System.out.print('\n');
        }
    }
    //getters & setters
    public int getTableCols() {
        return tableCols;
    }
    public int getTableRows() {
        return tableRows;
    }
    public void setTableCols(int tableCols) {
        this.tableRows = tableCols;
    }
    public void setTableRows(int tableRows) {
        this.tableRows = tableRows;
    }
}
