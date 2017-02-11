package com.example.ottot.mineseeker;

/**
 * Created by ottot on 2/10/2017.
 */

public class table {
    private int tableWidth;
    private int tableHeight;
    private block[][] allBlocks;
    private int[] mineOfRows;
    private int[] mineOfCols;

    //initializes the whole table
    public table(int tableWidth,int tableHeight) {
        this.tableWidth = tableWidth;
        this.tableHeight = tableHeight;
        allBlocks = new block[tableHeight][tableWidth];
        mineOfCols = new int[tableWidth];
        mineOfRows = new int[tableHeight];
        searchMines();
    }

    public void searchMines(){
        for (int i = 0;i<tableHeight;i++){
            for (int j = 0; j<tableWidth;j++){
                if (allBlocks[i][j].getMine()==1){
                    mineOfRows[i]++;
                    mineOfCols[j]++;
                }
            }

        }
    }
    //--------------------------------

    //called when user taps a block
    public int guessMine(int rowIndex, int colIndex){
        if (allBlocks[rowIndex][colIndex].getMine()==1){
            mineOfRows[colIndex]--;
            mineOfCols[rowIndex]--;
            return 1;
        }
        else return 0;
    }
    //called to get the number on the block, which is the sum of mines in the row and column
    public int numOfMines(int rowIndex, int colIndex){
        return mineOfCols[rowIndex] + mineOfRows[colIndex];
    }


    //getters & setters
    public int getTableWidth() {
        return tableWidth;
    }

    public int getTableHeight() {
        return tableHeight;
    }
    public void setTableWidth(int tableWidth) {
        this.tableHeight = tableWidth;
    }
    public void setTableHeight(int tableHeight) {
        this.tableHeight = tableHeight;
    }
}
