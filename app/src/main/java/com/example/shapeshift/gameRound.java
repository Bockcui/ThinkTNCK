package com.example.shapeshift;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class gameRound extends Activity {
    private int round;
    private int difficulty;
    private int lives;
    private int score;
    private ArrayList<Integer> sequence;
    private ArrayList<Integer> inputs;
    private Button bSquare;
    private Button bTriangle;
    private Button bDiamond;
    private Button bCircle;
    private TextView tv;

    public gameRound(int difficulty)
    {
        round = 1;
        this.difficulty = difficulty;
        bSquare = (Button) findViewById(R.id.square);
        bTriangle = (Button) findViewById(R.id.triangle);
        bDiamond = (Button) findViewById(R.id.diamond);
        bCircle = (Button) findViewById(R.id.circle);
        tv = (TextView) findViewById(R.id.countdown);
        bSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputs.add(0);
                checkInput();
            }
        });
        bTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputs.add(1);
                checkInput();
            }
        });
        bDiamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputs.add(2);
                checkInput();
            }
        });
        bCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputs.add(3);
                checkInput();
            }
        });

        //Initialize sequence ArrayList
        sequence = new ArrayList<>();
        inputs = new ArrayList<>();
        round = 1;
        lives = 3;
        score = 0;
    }

    public void newSequence() //Will generate a sequence of integers from 0-3 of given length
    {
        sequence.clear();
        inputs.clear();
        for (int i = 0; i < round + 3; i++)
        {
            sequence.add((int) ((Math.random() * 100000) % 4));
        }
    }

    public void checkInput()
    {
        if(inputs.size() < sequence.size())
        {
            int index = inputs.size() - 1;
            if(inputs.get(index) != sequence.get(index))
            {
                loseLife();
            }
        }
        else if(!inputs.equals(sequence))
        {
            loseLife();
        }
        else
        {
            advanceRound();
        }
    }

    public void loseLife()
    {
        lives--;
    }

    public void advanceRound()
    {
        this.round++;
        this.newSequence();
    }

    public void startRound()
    {
        bSquare.setClickable(false);
        bTriangle.setClickable(false);
        bDiamond.setClickable(false);
        bCircle.setClickable(false);

        newSequence();

        tv.setText(String.format("Round %d", round));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv.setText("Ready?");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv.setText("Go!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv.setText("Remember the pattern!");

        for(int i = 0; i < sequence.size(); i++)
        {
            int temp = -1;
            switch(sequence.get(i))
            {
                case 0:
                    temp = 0;
                    bSquare.setBackground(Drawable.createFromPath("@drawable/ic_square_shape_light"));
                    break;
                case 1:
                    temp = 1;
                    bTriangle.setBackground(Drawable.createFromPath("@drawable/ic_square_shape_light"));
                    break;
                case 2:
                    temp = 2;
                    bDiamond.setBackground(Drawable.createFromPath("@drawable/ic_square_shape_light"));
                    break;
                case 3:
                    temp = 3;
                    bCircle.setBackground(Drawable.createFromPath("@drawable/ic_square_shape_light"));
                    break;
            }
            try {
                Thread.sleep(1000 - difficulty * 250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch(temp)
            {
                case 0:
                    bSquare.setBackground(Drawable.createFromPath("@drawable/ic_square_shape"));
                    break;
                case 1:
                    bTriangle.setBackground(Drawable.createFromPath("@drawable/ic_triangle_shape"));
                    break;
                case 2:
                    bDiamond.setBackground(Drawable.createFromPath("@drawable/ic_diamond_shape"));
                    break;
                case 3:
                    bCircle.setBackground(Drawable.createFromPath("@drawable/ic_circle_shape"));
                    break;
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tv.setText("Your turn!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv.setText("What was the pattern?");
        bSquare.setClickable(true);
        bTriangle.setClickable(true);
        bDiamond.setClickable(true);
        bCircle.setClickable(true);
    }
}
