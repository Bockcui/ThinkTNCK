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

import com.airbnb.lottie.LottieAnimationView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class gameOver extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<Highscore> top5;
    private Highscore player;
    private int score;
    private EditText textBox;
    private Button play_again;
    private Button save;
    private Button menu;
    private Button leaderboard;
//    LottieAnimationView shapeDraw;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_page);

//        shapeDraw.findViewById(R.id.shapeDraw);
//        shapeDraw.playAnimation();

        TextView scoreText=(TextView)findViewById(R.id.scoreT);
        TextView hsAlert=(TextView)findViewById(R.id.highScoreAlert);
        textBox=(EditText)findViewById(R.id.textBoxHighScore);

        textBox.setVisibility(View.INVISIBLE);
        hsAlert.setVisibility(View.INVISIBLE);
        hsAlert.setText("Better luck next time!");

        play_again = (Button) findViewById(R.id.play_again);
        save = (Button) findViewById(R.id.save);
        menu = (Button) findViewById(R.id.Menu);
        leaderboard = (Button) findViewById(R.id.leaderboard);

        play_again.setOnClickListener(this);
        save.setOnClickListener(this);
        menu.setOnClickListener(this);
        leaderboard.setOnClickListener(this);

        Button mainMenu = (Button) findViewById(R.id.Menu);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gameOver.this, MainActivity.class);
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
            }
        }
        textBox.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.play_again:
                Intent diff = new Intent(this, diffActivity.class);
                diff.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(diff);
                finish();
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
            case R.id.Menu:
                Intent menu = new Intent(this, MainActivity.class);
                menu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(menu);
                finish();
                break;
            case R.id.leaderboard:
                Intent leaders = new Intent(this, leaderboard.class);
                leaders.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(leaders);
                finish();
                break;
        }
    }
}
