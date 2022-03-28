package com.example.shapeshift;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class gameActivity extends AppCompatActivity {
    public static int difficulty = 0;
    // 0 -> easy, 1 -> medium, 2 -> hard
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);

    }


}
