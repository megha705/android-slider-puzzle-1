package com.example.sliderpuzzle;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class hiscores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int easyscore, mediumscore, hardscore;
        setContentView(R.layout.activity_hiscores);

        //easy
        easyscore = prefs.getInt("easykey", 999999999);
        TextView textView = (TextView) findViewById(R.id.easyscore);
        if(easyscore != 999999999)
        {
            textView.setText(String.valueOf(easyscore));
        }
        else
        {
            textView.setText("No Scores Yet");
        }

        //medium
        mediumscore = prefs.getInt("mediumkey", 999999999);
        TextView textView2 = (TextView) findViewById(R.id.mediumscore);
        if(mediumscore != 999999999)
        {
            textView2.setText(String.valueOf(mediumscore));
        }
        else
        {
            textView2.setText("No Scores Yet");
        }

        //hard
        hardscore = prefs.getInt("hardkey", 999999999);
        TextView textView3 = (TextView) findViewById(R.id.hardscore);
        if(hardscore != 999999999)
        {
            textView3.setText(String.valueOf(hardscore));
        }
        else
        {
            textView3.setText("No Scores Yet");
        }

    }
}
