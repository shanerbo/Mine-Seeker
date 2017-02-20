package com.example.ottot.mineseeker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ottot.mineseeker.UserMenu;

public class options_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options_page);

        Intent passedIn = getIntent();

        final Spinner dimSpinner = (Spinner) findViewById(R.id.dimDD);
        final Spinner mineSpinner = (Spinner) findViewById(R.id.numMineDD);
        ArrayAdapter<String> mineAdapter = new ArrayAdapter<String>(options_page.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.numMineDD));
        ArrayAdapter<String> dimAdapter = new ArrayAdapter<String>(options_page.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.dimDD));
        dimAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mineAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dimSpinner.setAdapter(dimAdapter);
        mineSpinner.setAdapter(mineAdapter);

        dimSpinner.setSelection(passedIn.getIntExtra("table_dim",0));
        mineSpinner.setSelection(passedIn.getIntExtra("numberMine",0));

        final Intent result = new Intent();

        String dim = dimSpinner.getSelectedItem().toString();
        String mine = mineSpinner.getSelectedItem().toString();
        result.putExtra("dimension",dim);
        result.putExtra("mineNum",mine);

        setResult(Activity.RESULT_OK,result);
        Button done = (Button) findViewById(R.id.opt_ret_btn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dim = dimSpinner.getSelectedItemPosition();
                int mine = mineSpinner.getSelectedItemPosition();
                Toast.makeText(options_page.this,"dim is "+dim,Toast.LENGTH_SHORT).show();
                result.putExtra("dimension",dim);
                result.putExtra("mineNum",mine);
                setResult(Activity.RESULT_OK,result);
                finish();
            }
        });

        Button eraseData = (Button) findViewById(R.id.ResetTimePlay);
        eraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.putExtra("resetOrNot",1);
            }
        });
    }

    public static Intent makeIntent(Context userMenu) {
        return new Intent(userMenu,options_page.class);
    }
}
