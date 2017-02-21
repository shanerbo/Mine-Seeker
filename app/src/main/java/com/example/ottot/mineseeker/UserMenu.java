package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserMenu extends AppCompatActivity {//this is the actual main activity
    //check out table.java
    private int dim_code = 0;//0= 4*6, 1 = 5*8, 2=6*8, 3=4*8, 4=5*12, 5= 6*15
    private static int num_of_dim = 6;
    private int mine_code;//0 = 6, 1 = 10, 2 = 15, 3 = 25, 4 = 35
    private static int TimePlayed;
    private static int[] BestScores = new int[num_of_dim];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        //initialize table data with default values
        TimePlayed=0;
        BestScores[dim_code]=0;
        startGame();
        optionMenu();
        helpMenu();
        showInfo();
        //------------------------------------
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                BestScores[dim_code] = data.getIntExtra("bestScore",0);

                TimePlayed = data.getIntExtra("timesPlay",TimePlayed);
                showInfo();
                break;
            case 2:
                if (resultCode==RESULT_CANCELED){
                    return;
                }
                if (data.getIntExtra("resetTimeOrNot", 0) == 1) {
                    resetPlaytime();
                }
                if (data.getIntExtra("resetScoreOrNot", 0) == 1) {
                    resetBestScore();
                }
                dim_code = data.getIntExtra("dimension",0);
                mine_code = data.getIntExtra("mineNum",0);
                showInfo();
                break;
        }
    }



    private void startGame() {
        final Button startGame = (Button)findViewById(R.id.Game_start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game_Start = main_Game.makeIntent(UserMenu.this);
                game_Start.putExtra("dimCode",dim_code);
                game_Start.putExtra("mineCode",mine_code);
                game_Start.putExtra("time",TimePlayed);
                game_Start.putExtra("scr",BestScores[dim_code]);
                startActivityForResult(game_Start,1);
            }
        });
    }



    private void optionMenu() {
        final Button options = (Button)findViewById(R.id.options_btn);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_opt = options_page.makeIntent(UserMenu.this);
                go_to_opt.putExtra("TimePlay",TimePlayed);
                go_to_opt.putExtra("table_dim",dim_code);
                go_to_opt.putExtra("numberMine",mine_code);

                startActivityForResult(go_to_opt,2);
            }
        });
    }
    private void helpMenu() {
        final Button help = (Button)findViewById(R.id.help_btn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_help = help_page.makeIntent(UserMenu.this);
                startActivity(go_to_help);
            }
        });
    }
    private void showInfo() {
        TextView timePlay = (TextView) findViewById(R.id.TimePlayed);
        timePlay.setText(""+TimePlayed);
        TextView bestScore = (TextView) findViewById(R.id.BestScore);
        Resources res = getResources();
        String[] dimStrArr = res.getStringArray(R.array.dimDD);
        String dimStr = dimStrArr[dim_code];
        TextView bestScoreTittle = (TextView) findViewById(R.id.BestScoreTittle);
        bestScoreTittle.setText("Your Best Score in " + "["+dimStr +"] is :");
        bestScore.setText(""+BestScores[dim_code]);
    }

    private void resetPlaytime(){
        TimePlayed = 0;
    }
    private void resetBestScore() {
        for (int i = 0;i<num_of_dim;i++){
            BestScores[i] = 0;
        }
    }

    public void savePref(){
        SharedPreferences dataTosave = getApplicationContext().getSharedPreferences("MyPref",0);
        SharedPreferences.Editor PrefEditor = dataTosave.edit();
        PrefEditor.putInt("dim_code",dim_code);
        PrefEditor.putInt("mine_code",mine_code);
        String scores_into_string = BestScores.toString();
        PrefEditor.putString("Best Scores",scores_into_string);

    }
    public void GetPref(){
        SharedPreferences dataToGet = getApplicationContext().getSharedPreferences("MyPref",0);
        if (dataToGet==null)return;
        dim_code = dataToGet.getInt("dim_code",0);
        mine_code = dataToGet.getInt("mine_code",0);
        String scores_in_string = dataToGet.getString("Best Scores", null);
        String splitor = scores_in_string.replaceAll("\\[|\\]|\\s", "");
        String[] splited = splitor.split("\\,");
        for (int i = 0; i< num_of_dim; i++){
            BestScores[i]=Integer.parseInt(splited[i]);
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, UserMenu.class);
    }




}
