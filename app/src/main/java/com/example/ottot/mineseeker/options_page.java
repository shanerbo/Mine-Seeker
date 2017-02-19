package com.example.ottot.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class options_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);
    }

    public static Intent makeIntent(UserMenu userMenu) {
        return new Intent(userMenu,options_page.class);
    }
}
