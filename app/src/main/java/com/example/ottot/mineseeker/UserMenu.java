package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserMenu extends AppCompatActivity {//this is the actual main activity
    //check out table.java
    private static table game_data; //always rowIndex(y) first, colIndex(x) second
    private int default_row = 6;
    private int default_col = 10;
    private int default_numOfMines = 20;
    private static int TimePlayed;
    private static int BestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        //initialize table data with default values
        game_data = new table(default_row,default_col,default_numOfMines);
        TimePlayed=222;
//        //activation for the three buttons on main menu page
        final Button startGame = (Button)findViewById(R.id.Game_start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game_Start = main_Game.makeIntent(UserMenu.this);

                startActivityForResult(game_Start,1);
            }
        });

        final Button options = (Button)findViewById(R.id.options_btn);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_opt = options_page.makeIntent(UserMenu.this);
                go_to_opt.putExtra("TimePlay",TimePlayed);
                go_to_opt.putExtra("tableRow",default_row);
                go_to_opt.putExtra("tableCol",default_col);
                go_to_opt.putExtra("numberMine",default_numOfMines);

                startActivityForResult(go_to_opt,2);
            }
        });


        final Button help = (Button)findViewById(R.id.help_btn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_help = help_page.makeIntent(UserMenu.this);
                startActivity(go_to_help);
            }
        });
        TextView timePlay = (TextView) findViewById(R.id.TimePlayed);
        timePlay.setText(""+TimePlayed);
        //------------------------------------
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (data.getIntExtra("resetOrNot",0) == 1){
            resetPlaytime();

         }

    }

    private void resetPlaytime(){
        TimePlayed = 0;
        TextView timePlay = (TextView) findViewById(R.id.TimePlayed);
        timePlay.setText(""+TimePlayed);
    }

    public static table getData(){
        return game_data;
    }//for the game to get data

    public static void reSize(int newRow, int newCol,int newNumOfMines){
        game_data = new table(newRow, newCol,newNumOfMines);
    }//for the resizing feature in the options page

    public static Intent makeIntent(Context context){
        return new Intent(context, UserMenu.class);
    }


}
