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
    private int dim_code;//0= 4*6, 1 = 5*7, 2=6*8, 3=4*8, 4=5*12, 5= 6*15
    private int mine_code;
    private static int TimePlayed;
    private static int BestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        //initialize table data with default values

        TimePlayed=0;
        BestScore=0;
//        //activation for the three buttons on main menu page
        final Button startGame = (Button)findViewById(R.id.Game_start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game_Start = main_Game.makeIntent(UserMenu.this);
                startActivity(game_Start);
            }
        });

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
        if (data.getStringExtra("dimension")=="4 rows by 6 cols"){
            dim_code = 0;
        }
        else if (data.getStringExtra("dimension")=="5 rows by 7 cols"){
            dim_code = 1;
        }
        else if (data.getStringExtra("dimension")=="6 rows by 8 cols"){
            dim_code = 2;
        }
        else if (data.getStringExtra("dimension")=="4 rows by 8 cols"){
            dim_code = 3;
        }
        else if (data.getStringExtra("dimension")=="5 rows by 12 cols"){
            dim_code = 4;
        }
        else if (data.getStringExtra("dimension")=="6 rows by 15 cols"){
            dim_code = 5;
        }

        if (data.getStringExtra("mineNum")=="6"){
            mine_code = 0;
        }
        else if (data.getStringExtra("mineNum")=="10"){
            mine_code = 1;
        }
        else if (data.getStringExtra("mineNum")=="15"){
            mine_code = 2;
        }
        else if (data.getStringExtra("mineNum")=="25"){
            mine_code = 3;
        }
        else if (data.getStringExtra("mineNum")=="35"){
            mine_code = 4;
        }
        System.out.print(dim_code);
    }

    private void resetPlaytime(){
        TimePlayed = 0;
        TextView timePlay = (TextView) findViewById(R.id.TimePlayed);
        timePlay.setText(""+TimePlayed);
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, UserMenu.class);
    }


}
