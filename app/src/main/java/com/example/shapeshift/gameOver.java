package com.example.shapeshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class gameOver extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_page);

        TextView scoreText=(TextView)findViewById(R.id.scoreT);

        Button play_again = (Button) findViewById(R.id.play_again);
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gameOver.this, diffActivity.class);
                startActivity(intent);
            }
        });

Intent intentEnd= getIntent();
int score=intentEnd.getIntExtra("val",0);

scoreText.setText("Score: "+score);

    }


}
