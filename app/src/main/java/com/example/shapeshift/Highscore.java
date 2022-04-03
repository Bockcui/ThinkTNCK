package com.example.shapeshift;

import android.text.Editable;

public class Highscore {
    private String name;
    private String score;

    public Highscore(){}

    public Highscore(String name, String score)
    {
        name = name;
        score = score;
    }

    public int getScoreInt()
    {
        return Integer.parseInt(score);
    }

    public String getScore()
    {
        return this.score;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
