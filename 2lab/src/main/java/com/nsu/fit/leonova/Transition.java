package com.nsu.fit.leonova;

public class Transition {
    private String input;
    private int nextState;

    public Transition(String input, int nextState) {
        this.input = input;
        this.nextState = nextState;
    }

    public String getInput() {
        return input;
    }

    public int getNextState() {
        return nextState;
    }
}
