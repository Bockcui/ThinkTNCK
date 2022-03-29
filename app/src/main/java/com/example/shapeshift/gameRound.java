package com.example.shapeshift;

import java.util.ArrayList;
import java.util.List;

public class gameRound {
    private int round;
    private int difficulty;
    private List<Integer> sequence;

    public gameRound(int round, int difficulty)
    {
        this.round = round;
        this.difficulty = difficulty;
        this.sequence = new ArrayList<>();
        this.newSequence(round + 3);
    }

    public gameRound(int difficulty)
    {
        this.round = 1;
        this.difficulty = difficulty;
        this.sequence = new ArrayList<>();
        this.newSequence(round + 3);
    }

    public int sequenceLength()
    {
        return this.sequence.size();
    }

    public List<Integer> getSequence()
    {
        return this.sequence;
    }

    public int getRound()
    {
        return this.round;
    }

    public void advanceRound()
    {
        this.round++;
        this.newSequence(round + 3);
    }

    public void newSequence(int length) //Will generate a sequence of integers from 0-3 of given length
    {
        this.sequence.clear();
        for (int i = 0; i < length; i++)
        {
            this.sequence.add((int) ((Math.random() * 100000) % 4));
        }
    }
}
