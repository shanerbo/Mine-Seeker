package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMenu extends AppCompatActivity {
    private table game_data;
    private int default_row = 6;
    private int default_col = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        //initialize table data with default values
        game_data = new table(default_row,default_col);

        //activation for the three buttons on main menu page
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
                startActivity(go_to_opt);
            }
        });

        final Button help = (Button)findViewById(R.id.return_btn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_help = help_page.makeIntent(UserMenu.this);
                startActivity(go_to_help);
            }
        });
        //------------------------------------
    }

    public table getData(){
        return game_data;
    }

    public void reSize(int newRow, int newCol){
        game_data = new table(newRow, newCol);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, UserMenu.class);
    }


}
