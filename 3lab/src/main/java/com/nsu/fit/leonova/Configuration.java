package com.nsu.fit.leonova;

public class Configuration {
    private int state;
    private int index;

    public Configuration(int state, int index) {
        this.state = state;
        this.index = index;
    }

    public int getState() {
        return state;
    }

    public int getIndex() {
        return index;
    }
}
