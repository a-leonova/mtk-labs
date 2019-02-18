package com.nsu.fit.leonova;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySimpleStateMachine implements StateMachine{

    private ArrayList<Integer> finishStates = new ArrayList<>();
    private Map<Integer, Map<String, Integer>> transitions = new HashMap<>();

    public MySimpleStateMachine(BufferedReader reader) throws IOException {

        String string = reader.readLine();
        if(string == null){
            //TODO: throw exception
        }

        for(String state : string.split(" ")){
            finishStates.add(Integer.parseInt(state));
        }

        String newTransitionStr;
        while((newTransitionStr = reader.readLine()) != null){
            String[] newTransition = newTransitionStr.split(" ");
            if(newTransition.length != 3){
                //Todo: throw exception
            }
            int beginState = Integer.parseInt(newTransition[0]);
            String input = newTransition[1];
            int nextState = Integer.parseInt(newTransition[2]);
            transitions.computeIfAbsent(beginState, k -> new HashMap<>());
            Map<String, Integer> allTransitionsForState =  transitions.get(beginState);
            allTransitionsForState.put(input, nextState);
        }
    }

    @Override
    public int getNextState(int currentState, char input) {

        Map<String, Integer> allTransitionForState = transitions.get(currentState);
        if(allTransitionForState == null){
            //TODO: throw exception
        }

        Integer nextState = allTransitionForState.get(String.valueOf(input));
        if(nextState == null){
            //TODO: throw exception
        }
        return nextState;
    }

    @Override
    public boolean isFinishState(int currentState) {
        return finishStates.contains(currentState);
    }
}
