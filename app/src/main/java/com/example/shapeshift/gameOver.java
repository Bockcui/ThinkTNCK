package com.example.shapeshift;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class gameOver extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<Highscore> top5;
    private Highscore player;
    private int score;
    private EditText textBox;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_page);

        TextView scoreText=(TextView)findViewById(R.id.scoreT);
        TextView hsAlert=(TextView)findViewById(R.id.highScoreAlert);
        textBox=(EditText)findViewById(R.id.textBoxHighScore);

        textBox.setVisibility(View.INVISIBLE);
        hsAlert.setVisibility(View.INVISIBLE);
        hsAlert.setText("Better luck next time!");

        Button play_again = (Button) findViewById(R.id.play_again);
        Button save = (Button) findViewById((R.id.save));
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gameOver.this, diffActivity.class);
                startActivity(intent);
            }
        });

        Intent intentEnd= getIntent();
        score=intentEnd.getIntExtra("val",0);
        top5 = new ArrayList<>();
        player = new Highscore("TEMP",String.valueOf(score));

        scoreText.setText("Score: "+score);

        try {
            BufferedReader b = new BufferedReader(new FileReader("highscores.csv"));
            String l = "";
            String schar = ",";
            String[] highscore;
            while ((l = b.readLine()) != null)
            {
                highscore = l.split(schar);
                top5.add(new Highscore(highscore[0], highscore[1]));
            }
            Collections.sort(top5, Comparator.comparing(Highscore::getScoreInt).reversed());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = top5.size() - 1; i >=0; i--)
        {
            if(player.getScoreInt() > top5.get(i).getScoreInt())
            {
                top5.add(i, player);
                hsAlert.setText("A new highscore!");
                textBox.setVisibility(View.VISIBLE);
            }
        }
        textBox.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.play_again:
                finish();
                super.finish();
                break;
            case R.id.save:
                player.setName(textBox.getText().toString());
                try {
                    FileWriter f = new FileWriter("highscores.csv", false);
                    for(int i = 0; i < 4 || i < top5.size(); i++)
                    {
                        f.write(String.format("%s,%s", top5.get(i).getName(), top5.get(i).getScore()));
                    }
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
