package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class main_Game extends AppCompatActivity {
    private table game_data;
    private int tableCol;
    private int tableRow;
    private int numOfMine;
    private int timePlayed;
    private int bestScr=0;
    private int guessed;
    private int scanTime;
    private int currentScr = 0;

    private Button mine_btns[][];
    private int mine_icon_ID = R.mipmap.mine_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent passedInData = getIntent();
        int dimC= passedInData.getIntExtra("dimCode",0);
        int mineC = passedInData.getIntExtra("mineCode",0);
        numOfMine = decodeMineC(mineC);
        timePlayed = passedInData.getIntExtra("time",0);
        bestScr = passedInData.getIntExtra("scr",0);

        timePlayed++;

        dimensionGenerate(dimC);

        mineFieldGenerate();
    }

    private int decodeMineC(int mineC) {//0 = 6, 1 = 10, 2 = 15, 3 = 25, 4 = 35
        switch (mineC){
            case 0:
                return 6;
            case 1:
                return 10;
            case 2:
                return 15;
            case 3:
                return 25;
            case 4:
                return 35;
        }
        return 6;
    }

    private void mineFieldGenerate() {
        TableLayout mineField = (TableLayout)findViewById(R.id.mineField);
        game_data = new table(tableRow,tableCol,numOfMine);
        table game_data_OG = game_data;
        mine_btns = new Button[tableRow][tableCol];
        updateUI();


        for (int i = 0;i<tableRow;i++){
            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f)
            );
            mineField.addView(newRow);
            for (int j = 0 ; j <tableCol;j++){
                final int FINALi = i;
                final int FINALj = j;
                Button newButton = new Button(this);
                newButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f)
                );
                newButton.setPadding(0,0,0,0);
                newRow.addView(newButton);
                mine_btns[i][j] = newButton;
                //set i j location for mine;
                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mineGuess(FINALi,FINALj);
                        //FINALi FINALj is the location of mine
                    }
                });
            }
        }
    }

    private void mineGuess(int row,int col) {
        if (game_data.getRemainScanTimes() > 0) {
            if (game_data.guessMine(row, col) == 1) {
                game_data.clickOnButton(row,col);
                mine_btns[row][col].setBackgroundResource(mine_icon_ID);
                guessed++;
                currentScr += 100;
                refreshTable();
                updateUI();
            }
            else{
                if (game_data.buttonIsClicked(row,col) == 0 && currentScr>20) {
                    currentScr -= 20;
                    game_data.clickOnButton(row,col);

                }
                else if (game_data.buttonIsClicked(row,col) == 0 && currentScr <=20){
                    currentScr = 0;
                    game_data.clickOnButton(row,col);
                }
                refreshTable();
                updateUI();
            }
            if (guessed == numOfMine){
                refreshTableWin();
                Toast.makeText(main_Game.this, getString(R.string.youWin), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else{
            new AlertDialog.Builder(main_Game.this)
                    .setTitle("Out of Scan times")
                    .setMessage(R.string.inAppPurchase)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            game_data.addMoreTime();
                            updateUI();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(main_Game.this, getString(R.string.poorGuy), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .show();
        }
    }

    private void updateUI() {
        TextView foundMine = (TextView)findViewById(R.id.found_mine);
        foundMine.setText(guessed + " / " + numOfMine + " mines found");

        TextView currScore = (TextView)findViewById(R.id.scoreBoard);
        currScore.setText("Score :" + currentScr);

        TextView bestScore = (TextView)findViewById(R.id.BestScrHis);
        refreshNewScr();
        bestScore.setText("Best Score:" + bestScr);

        TextView RemainScanTime = (TextView)findViewById(R.id.scanTimes);
        RemainScanTime.setText("Remaining Scan: " + game_data.getRemainScanTimes());
    }

    private void dimensionGenerate(int Dcode) {
        if (Dcode==0){
            tableRow = 4;
            tableCol = 6;
        }
        else if (Dcode == 1){
            tableRow = 5;
            tableCol = 8;
        }
        else if (Dcode == 2){
            tableRow = 6;
            tableCol = 8;
        }
        else if (Dcode == 3){
            tableRow = 4;
            tableCol = 8;
        }
        else if (Dcode == 4){
            tableRow = 5;
            tableCol = 12;
        }
        else if (Dcode == 5){
            tableRow = 6;
            tableCol = 15;
        }
    }

    public void refreshTable(){
        for (int i = 0; i < tableRow; i++){
            for (int j = 0; j < tableCol; j++){
                if (game_data.getBlock(i,j) == 2 ){
                    mine_btns[i][j].setText("" + game_data.numOfMines_at(i, j));
                }
            }
        }
    }
    public void refreshNewScr(){
        if (currentScr > bestScr){
            bestScr = currentScr;
            //Toast.makeText(main_Game.this, getString(R.string.newRecord),Toast.LENGTH_SHORT).show();
        }
    }
    public void refreshTableWin(){
        for (int i = 0; i < tableRow; i++){
            for (int j = 0; j < tableCol; j++){
                    mine_btns[i][j].setText("" + game_data.numOfMines_at(i, j));
                }
            }
        }
    public static Intent makeIntent(Context context){
        return new Intent(context, main_Game.class);
    }
}
