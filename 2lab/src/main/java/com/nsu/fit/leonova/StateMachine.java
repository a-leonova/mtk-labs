package com.nsu.fit.leonova;

public interface StateMachine {
    int getNextState(int currentState, char input) throws UnknownStateException;
    boolean isFinishState(int currentState);
}
