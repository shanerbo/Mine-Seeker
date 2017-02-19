package com.example.ottot.mineseeker;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Arrays;
/**
 * Created by ottot on 2/10/2017.
 */

public class table {//use tableTest to see what it does
    private int tableCols; //width of the table, x value
    private int tableRows; //height of the table, y value
    private int[][] allBlocks;
    private int[] mineOfEachRows;
    private int[] mineOfEachCols;

    //initialization functions
    public table(int tableRows, int tableCols) {
        this.tableCols = tableCols;
        this.tableRows = tableRows;
        allBlocks = new int[tableRows][tableCols];
        for (int i = 0;i<tableRows;i++){//randomize the 2d array
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
        if (getBlock(rowIndex,colIndex)==1){
            mineOfEachRows[rowIndex]--;
            mineOfEachCols[colIndex]--;
            return 1;
        }
        else return 0;
    }
    //called to get the number on the block, which is the sum of mines in the row and column
    public int numOfMines(int rowIndex, int colIndex){
        if (allBlocks[rowIndex][colIndex]==1){
            return mineOfEachCols[colIndex] + mineOfEachRows[rowIndex] - 1;
        }
        else return mineOfEachCols[colIndex] + mineOfEachRows[rowIndex];
    }
    //----------------------------------

    //test, visualizing tools------------
    public void printTable(){
        for (int i = 0;i<tableRows;i++){
            for (int j = 0;j<tableCols;j++){
                System.out.print(allBlocks[i][j]);
            }
            System.out.print('\n');
        }
    }
    public void printNumArrays() {
        System.out.println("mineOfEachCols: ");
        for (int i=0;i<mineOfEachCols.length;i++){
            System.out.print(mineOfEachCols[i]);
        }

        System.out.println("\nmineOfEachRows: ");
        for (int i=0;i<mineOfEachRows.length;i++){
            System.out.print(mineOfEachRows[i]);
        }
        System.out.print("\n\n");
    }
    //----------------------------------------
    //getters & setters
    public int getBlock(int y, int x){
        return allBlocks[y][x];
    }

    public int getTableCols() {
        return tableCols;
    }
    public int getTableRows() {
        return tableRows;
    }
    public void setTableCols(int tableCols) {
        this.tableCols = tableCols;
    }
    public void setTableRows(int tableRows) {
        this.tableRows = tableRows;
    }
    //-----------------------------------------
}
