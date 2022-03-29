package com.example.shapeshift;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class gameActivity extends AppCompatActivity {
    public static int difficulty = 0;
    // 0 -> easy, 1 -> medium, 2 -> hard

    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);




        // assign variable
        textView = findViewById(R.id.countdown);

        // timer duration

        long duration = TimeUnit.SECONDS.toMillis(30);

        // countdown timer






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




        }



}



