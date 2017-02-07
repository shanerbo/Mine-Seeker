package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, UserMenu.class);
    }


}
