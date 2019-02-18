package com.nsu.fit.leonova;

public interface StateMachine {
    int getNextState(int currentState, char input);
    boolean isFinishState(int currentState);
}
