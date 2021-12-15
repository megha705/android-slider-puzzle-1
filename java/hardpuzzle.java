package com.example.sliderpuzzle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Random;

public class hardpuzzle extends AppCompatActivity
{
    private int[] array;
    private TableLayout layout;
    private Random rng;
    private int size = 5;
    private int movecounter = 0;
    private int low = 999999999;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        rng = new Random();
        layout = new TableLayout(this);
        layout.setLayoutParams( new TableLayout.LayoutParams() );
        layout.setPadding(1,1,1,1);
        layout.setShrinkAllColumns(true);

        array = new int[size*size];

        for(int i = 0; i < size*size; i++)
        {
            array[i] = i+1;
        }
        for(int i = 0; i < 10000; i++)
        {
            int a = rng.nextInt(4)+1;
            randomize(a);
        }
        update();

        super.setContentView(layout);

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        low = prefs.getInt("hardkey", 999999999);
    }

    //randomizes the board
    public void randomize(int seed)
    {
        int n = -1;
        for(int i = 0; i < size*size; i++)
        {
            if(array[i] == size*size)
                n = i;
        }

        if(seed == 1)
        {
            if(n+1<size*size && n%size != size-1)
            {
                int t = array[n];
                array[n] = array[n+1];
                array[n+1] = t;
            }
        }
        else if(seed == 2 && n%size*size != 0)
        {
            if(n-1>-1)
            {
                int t = array[n];
                array[n] = array[n-1];
                array[n-1] = t;
            }
        }
        else if(seed == 3)
        {
            if(n+size<size*size)
            {
                int t = array[n];
                array[n] = array[n+size];
                array[n+size] = t;
            }
        }
        else if(seed == 4)
        {
            if(n-size>-1)
            {
                int t = array[n];
                array[n] = array[n-size];
                array[n-size] = t;
            }
        }
    }

    //updates the display
    public void update()
    {
        int count = 0;
        layout.removeAllViews();

        for (int i = 0; i < size; i++)
        {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < size; j++)
            {
                if(array[count] == size*size)
                {
                    final Button b = new Button(this);
                    b.setText("");
                    b.setHeight(210);
                    tr.addView(b);
                    count++;
                }
                else

                {
                    final Button b = new Button(this);
                    b.setText("" + array[count]);
                    b.setHeight(210);
                    final int a = count;
                    b.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            move(a);
                        }
                    });
                    count++;
                    tr.addView(b);
                }
            }
            layout.addView(tr);
        }
        TableRow tr = new TableRow(this);
        final Button b = new Button(this);
        b.setText("Reset");
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i = 0; i < 10000; i++)
                {
                    int a = rng.nextInt(4)+1;
                    randomize(a);
                    movecounter=0;
                }
                update();
            }
        });
        tr.addView(b);

        final Button b2 = new Button(this);
        b2.setText("Back");
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        tr.addView(b2);
        layout.addView(tr);

        super.setContentView(layout);
    }

    //moves a piece if allowed
    public void move(int x)
    {
        if(x+1<size*size) //empty space to the right
        {
            if(array[x+1] == size*size && x%size != size-1)
            {
                array[x+1] = array[x];
                array[x] = size*size;
                movecounter++;
            }
        }

        if(x-1>-1)//empty space to the left
        {
            if(array[x-1] == size*size && x%size != 0)
            {
                array[x-1] = array[x];
                array[x] = size*size;
                movecounter++;
            }
        }

        if(x+size<size*size)//empty space below
        {
            if(array[x+size] == size*size)
            {
                array[x+size] = array[x];
                array[x] = size*size;
                movecounter++;
            }
        }

        if(x-size>-1)//empty space above
        {
            if(array[x-size] == size*size)
            {
                array[x-size] = array[x];
                array[x] = size*size;
                movecounter++;
            }
        }
        update();

        if(win())
        {
            Toast toast = Toast.makeText(this, "You Won in "+movecounter+" moves.", Toast.LENGTH_SHORT);
            toast.show();

            if(movecounter < low) {
                SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("hardkey", movecounter);
                editor.commit();
            }
        }
    }

    //checks for winning board
    public boolean win()
    {
        for(int i = 0; i < size*size; i++)
        {
            if(array[i]-1 != i)
            {
                return false;
            }
        }
        return true;
    }
}