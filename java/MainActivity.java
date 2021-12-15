package com.example.sliderpuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Todo: timer

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void easyClicked(View view)
    {
        Intent intent = new Intent(this, easypuzzle.class);
        startActivity(intent);
    }

    public void mediumClicked(View view)
    {
        Intent intent = new Intent(this, mediumpuzzle.class);
        startActivity(intent);
    }

    public void hardClicked(View view)
    {
        Intent intent = new Intent(this, hardpuzzle.class);
        startActivity(intent);
    }

    public void hiscoresClicked(View view)
    {
        Intent intent = new Intent(this, hiscores.class);
        startActivity(intent);
    }
}
