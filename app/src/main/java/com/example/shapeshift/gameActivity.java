package com.example.shapeshift;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class gameActivity extends AppCompatActivity{
    public static int difficulty = 0;
    // 0 -> easy, 1 -> medium, 2 -> hard

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

    public void demoSequence()
    {
        for(int i = 0; i < sequence.size(); i++)
        {
            int index = i;
            new CountDownTimer(1000 - difficulty * 250, 250)
            {
                @Override
                public void onTick(long l)
                {
                    switch(sequence.get(index))
                    {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                }

                @Override
                public void onFinish()
                {
                    switch(sequence.get(index))
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
                }
            }.start();

            try
            {
                gameActivity.this.wait(250);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void startRound()
    {
        bSquare.setEnabled(false);
        bTriangle.setEnabled(false);
        bDiamond.setEnabled(false);
        bCircle.setEnabled(false);

        newSequence();

        new CountDownTimer(5000, 1000)
        {
            @Override
            public void onTick(long l)
            {
                switch((int) l / 1000)
                {
                    case 4:
                        textView.setText(String.format("Round %d", round));
                        break;
                    case 2:
                        textView.setText("Ready?");
                        break;
                    case 1:
                        textView.setText("Go!");
                        break;
                    case 0:
                        textView.setText("Remember the pattern!");
                }
            }

            @Override
            public void onFinish()
            {
                textView.setText("Remember the pattern!");
            }
        }.start();

        demoSequence();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        startRound();
    }
}