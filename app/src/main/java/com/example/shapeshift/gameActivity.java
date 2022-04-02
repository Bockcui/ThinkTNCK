package com.example.shapeshift;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class gameActivity extends AppCompatActivity{
    public static int difficulty = 0;
    // 0 -> easy, 1 -> medium, 2 -> hard

    //creating animation objects for manipulation
    LottieAnimationView circ75;
    LottieAnimationView tri75;
    LottieAnimationView dia75;
    LottieAnimationView square75;

    LottieAnimationView circ05;
    LottieAnimationView tri05;
    LottieAnimationView dia05;
    LottieAnimationView square05;

    LottieAnimationView circ1;
    LottieAnimationView tri1;
    LottieAnimationView dia1;
    LottieAnimationView square1;
    private int round;
    private int lives;
    private int score;
    private ArrayList<Integer> sequence;
    private ArrayList<Integer> inputs;
    private Button bSquare;
    private Button bTriangle;
    private Button bDiamond;
    private Button bCircle;

    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);

        //binding animation objects to their layout counterpart
        circ05=findViewById(R.id.circ05);
        tri05=findViewById(R.id.tri05);
        dia05=findViewById(R.id.dia05);
        square05=findViewById(R.id.square05);

        circ75=findViewById(R.id.circ75);
        tri75=findViewById(R.id.tri75);
        dia75=findViewById(R.id.dia75);
        square75=findViewById(R.id.square75);

        circ1=findViewById(R.id.circ1);
        tri1=findViewById(R.id.tri1);
        dia1=findViewById(R.id.dia1);
        square1=findViewById(R.id.square1);
//making animation view invisible
        circ75.setVisibility(View.GONE);
        tri75.setVisibility(View.GONE);
        dia75.setVisibility(View.GONE);
        square75.setVisibility(View.GONE);

        circ05.setVisibility(View.GONE);
        tri05.setVisibility(View.GONE);
        dia05.setVisibility(View.GONE);
        square05.setVisibility(View.GONE);

        circ1.setVisibility(View.GONE);
        tri1.setVisibility(View.GONE);
        dia1.setVisibility(View.GONE);
        square1.setVisibility(View.GONE);




        // assign variable
        textView = findViewById(R.id.countdown);

        // timer duration

        long duration = TimeUnit.SECONDS.toMillis(30);

        // countdown timer



/*


        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                // convert milliseconds to seconds

                String sduration = String.format(Locale.CANADA, "%02d",TimeUnit.MILLISECONDS.toSeconds(l) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MICROSECONDS.toMinutes(l)));

                textView.setText(sduration);

            }

            @Override
            public void onFinish() {

                textView.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), "Time has ended", Toast.LENGTH_LONG).show();
            }
        }.start();

 */

        //Initialize buttons and functions associated
        bSquare = (Button) findViewById(R.id.square);
        bTriangle = (Button) findViewById(R.id.triangle);
        bDiamond = (Button) findViewById(R.id.diamond);
        bCircle = (Button) findViewById(R.id.circle);

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

    //Gameplay Back-end methods
    public void newSequence() //Will generate a sequence of integers from 0-3 of given length
    {
        sequence.clear();
        inputs.clear();
        for (int i = 0; i < round + 3; i++)
        {
            this.sequence.add((int) ((Math.random() * 100000) % 4));
        }
    }

    public void advanceRound()
    {
        this.round++;
        this.newSequence();
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

    public void startRound()
    {
        bSquare.setClickable(false);
        bTriangle.setClickable(false);
        bDiamond.setClickable(false);
        bCircle.setClickable(false);

        newSequence();

        new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {
                if(l <= 5000 && l > 3000)
                    textView.setText(String.format("Round %d", round));
                else if(l <= 3000 && l > 2000)
                    textView.setText("Ready?");
                else if(l <= 2000 && l > 1000)
                    textView.setText("Go!");
                else
                    textView.setText("Remember the pattern!");
            }

            @Override
            public void onFinish()
            {
                Timer t = new Timer();
                for(int i = 0; i < sequence.size(); i++)
                {
                    int index = i;
                    int button = sequence.get(index);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run()
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {





                                    switch(button)
                                    {
                                        case 0:
                                            //bSquare.setBackground(Drawable.createFromPath("@drawable/ic_square_shape"));
                                            bSquare.setVisibility(View.INVISIBLE);
                                            switch (difficulty){//interior switch cases to determine which animation speed to play based on difficulty

                                                case 0://1sec
                                                    square1.setVisibility(View.VISIBLE);
                                                    square1.playAnimation();
                                                    break;
                                                case 1://.75sec
                                                    square75.setVisibility(View.VISIBLE);
                                                    square75.playAnimation();

                                                    break;
                                                case 2://.5 sec
                                                    square05.setVisibility(View.VISIBLE);
                                                    square05.playAnimation();

                                                    break;

                                            }
//                                            square75.setVisibility(View.VISIBLE);
//                                            square75.playAnimation();
                                            break;
                                        case 1:
                                           // bTriangle.setBackground(Drawable.createFromPath("@drawable/ic_square_shape"));
                                            bTriangle.setVisibility(View.INVISIBLE);
                                            switch (difficulty){

                                                case 0://1sec
                                                    tri1.setVisibility(View.VISIBLE);
                                                    tri1.playAnimation();
                                                    break;
                                                case 1://.75sec
                                                    tri75.setVisibility(View.VISIBLE);
                                                    tri75.playAnimation();

                                                    break;
                                                case 2://.5 sec
                                                    tri05.setVisibility(View.VISIBLE);
                                                    tri05.playAnimation();
                                                    break;

                                            }

//                                            tri75.setVisibility(View.VISIBLE);
//                                            tri75.playAnimation();
                                            break;
                                        case 2:

                                            bDiamond.setVisibility(View.INVISIBLE);
                                            switch (difficulty){

                                                case 0://1sec
                                                    dia1.setVisibility(View.VISIBLE);
                                                    dia1.playAnimation();
                                                    break;
                                                case 1://.75sec
                                                    dia75.setVisibility(View.VISIBLE);
                                                    dia75.playAnimation();

                                                    break;
                                                case 2://.5 sec
                                                    dia05.setVisibility(View.VISIBLE);
                                                    dia05.playAnimation();
                                                    break;

                                            }

//                                            dia75.setVisibility(View.VISIBLE);
//                                            dia75.playAnimation();
                                           // bDiamond.setBackground(Drawable.createFromPath("@drawable/ic_square_shape"));
                                            break;
                                        case 3:

                                            bCircle.setVisibility(View.INVISIBLE);
                                            switch (difficulty){
                                                case 0://1sec
                                                    circ1.setVisibility(View.VISIBLE);
                                                    circ1.playAnimation();
                                                    break;
                                                case 1://.75sec
                                                    circ75.setVisibility(View.VISIBLE);
                                                    circ75.playAnimation();
                                                    break;
                                                case 2://.5 sec

                                                    circ05.setVisibility(View.VISIBLE);
                                                    circ05.playAnimation();
                                                    break;

                                            }
//                                            circ75.setVisibility(View.VISIBLE);
//                                            circ75.playAnimation();
                                           // bCircle.setBackground(Drawable.createFromPath("@drawable/ic_square_shape"));
                                            break;
                                    }
                                }
                            });
                        }
                    }, i * (250 + (1000 - difficulty * 250)));

                    t.schedule(new TimerTask() {
                        @Override
                        public void run()
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    switch(button)
                                    {
                                        case 0:
                                            //bSquare.setBackground(Drawable.createFromPath("@drawable/ic_square_shape"));
                                            break;
                                        case 1:
                                            //bTriangle.setBackground(Drawable.createFromPath("@drawable/ic_triangle_shape"));
                                            break;
                                        case 2:
                                           // bDiamond.setBackground(Drawable.createFromPath("@drawable/ic_diamond_shape"));
                                            break;
                                        case 3:
                                            //bCircle.setBackground(Drawable.createFromPath("@drawable/ic_circle_shape"));
                                            break;
                                    }
                                }
                            });
                        }
                    }, ((i + 1) * (250 + (1000 - difficulty * 250)) - 250));
                }

                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run(){

                                new CountDownTimer(2000,1000) {
                                    @Override
                                    public void onTick(long l) {
                                        if(l <= 2000 && l > 1000)
                                            textView.setText("Your turn!");
                                        else
                                            textView.setText("What was the pattern?");

                                    }

                                    @Override
                                    public void onFinish() {
                                        //making buttons visible again
                                        bSquare.setVisibility(View.VISIBLE);
                                        bCircle.setVisibility(View.VISIBLE);
                                        bDiamond.setVisibility(View.VISIBLE);
                                        bTriangle.setVisibility(View.VISIBLE);

                                        //making animations invisible again
                                        circ1.setVisibility(View.GONE);
                                        tri1.setVisibility(View.GONE);
                                        dia1.setVisibility(View.GONE);
                                        square1.setVisibility(View.GONE);

                                        circ75.setVisibility(View.GONE);
                                        dia75.setVisibility(View.GONE);
                                        square75.setVisibility(View.GONE);
                                        tri75.setVisibility(View.GONE);

                                        circ05.setVisibility(View.GONE);
                                        tri05.setVisibility(View.GONE);
                                        dia05.setVisibility(View.GONE);
                                        square05.setVisibility(View.GONE);

                                        //making buttons visible
                                        bSquare.setClickable(true);
                                        bTriangle.setClickable(true);
                                        bDiamond.setClickable(true);
                                        bCircle.setClickable(true);
                                    }
                                }.start();

                            }

                        });

//                        new CountDownTimer(2000,1000) {
//                            @Override
//                            public void onTick(long l) {
//                                if(l <= 2000 && l > 1000)
//                                    textView.setText("Your turn!");
//                                else
//                                    textView.setText("What was the pattern?");
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                bSquare.setClickable(true);
//                                bTriangle.setClickable(true);
//                                bDiamond.setClickable(true);
//                                bCircle.setClickable(true);
//                            }
//                        }.start();
                    }
                }, sequence.size() * (1000 - difficulty * 250));
            }
        }.start();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        startRound();
    }
}