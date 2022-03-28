package com.example.shapeshift;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class diffActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diff_page);

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout2);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        Button diff_back = (Button) findViewById(R.id.diff_back);
        diff_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(diffActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button diff_easy = (Button) findViewById(R.id.easy_button);
        diff_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(diffActivity.this, gameActivity.class);
                startActivity(intent);
                gameActivity.difficulty = 0;
            }
        });

        Button diff_med = (Button) findViewById(R.id.med_button);
        diff_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(diffActivity.this, gameActivity.class);
                startActivity(intent);
                gameActivity.difficulty = 1;
            }
        });

        Button diff_hard = (Button) findViewById(R.id.hard_button);
        diff_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(diffActivity.this, gameActivity.class);
                startActivity(intent);
                gameActivity.difficulty = 2;
            }
        });

    }
}
