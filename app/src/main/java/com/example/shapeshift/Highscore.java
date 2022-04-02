package com.example.shapeshift;

public class Highscore {
    private String name;
    private String score;

    public Highscore(){}

    public Highscore(String name, String score)
    {
        name = name;
        score = score;
    }

    public int getScore()
    {
        return Integer.parseInt(score);
    }
}
