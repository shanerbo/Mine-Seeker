package com.example.ottot.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // enter game button activation
        Button enterGame = (Button)findViewById(R.id.Enter_game);
        enterGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipIntro = UserMenu.makeIntent(MainActivity.this);
                startActivity(skipIntro);
            }
        });
    }
}
