package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class main_Game extends AppCompatActivity {
    private table game_data;
    private int tableCol;
    private int tableRow;
    private int numOfMine;
    private int timePlayed;
    private int bestScr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent passedInData = getIntent();
        int dimC= passedInData.getIntExtra("dimCode",0);
        numOfMine = passedInData.getIntExtra("mineCode",0);
        timePlayed = passedInData.getIntExtra("time",0);
        bestScr = passedInData.getIntExtra("scr",0);
        dimensionGenerate(dimC);

        mineFieldGenerate();
    }

    private void mineFieldGenerate() {
        TableLayout mineField = (TableLayout)findViewById(R.id.mineField);

    }

    private void dimensionGenerate(int Dcode) {
        if (Dcode==0){
            tableRow = 4;
            tableCol = 6;
        }
        else if (Dcode == 1){
            tableRow = 5;
            tableCol = 7;
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

    public static Intent makeIntent(Context context){
        return new Intent(context, main_Game.class);
    }
}
