package com.nsu.fit.leonova;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class NotDeterminateAutomat {

    private Map<Integer, Map<Character, List<Integer>>> transitions = new HashMap<>();
    private List<Integer> finishStates = new ArrayList<>();

    public NotDeterminateAutomat(BufferedReader reader) throws IOException, NumberFormatException {
        String string = reader.readLine();
        if(string == null){
            return;
        }

        for(String state : string.split(" ")){
            finishStates.add(Integer.parseInt(state));
        }

        String newTransitionStr;
        while((newTransitionStr = reader.readLine()) != null){
            String[] newTransition = newTransitionStr.split(" ");
            if(newTransition.length != 3){
                throw new IllegalArgumentException("Bad number of arguments for transition: " + newTransitionStr);
            }
            int beginState = Integer.parseInt(newTransition[0]);
            String input = newTransition[1];
            if(input.length() != 1 || !Character.isAlphabetic(input.codePointAt(0))){
                throw new IllegalArgumentException("Transition is a LETTER. And yours: " + input);
            }
            Character character = input.charAt(0);
            int nextState = Integer.parseInt(newTransition[2]);

            transitions.computeIfAbsent(beginState, k -> new HashMap<>());
            Map<Character, List<Integer>> allTransitionsForState =  transitions.get(beginState);
            allTransitionsForState.computeIfAbsent(character, k -> new ArrayList<>());
            List<Integer> smth = allTransitionsForState.get(character);
            smth.add(nextState);
        }
    }

    public int recognizeString(String expr) throws UnknownStateException {
        Configuration startConfiguration = new Configuration(0, 0);
        Stack<Configuration> stack = new Stack<>();
        stack.push(startConfiguration);

        while(!stack.empty()){
            Configuration currentConfiguration = stack.pop();

            if(expr.length() <= currentConfiguration.getIndex()){
                if(finishStates.contains(currentConfiguration.getState())){
                    return currentConfiguration.getState();
                }
                continue;
            }

            char currentSymb = expr.charAt(currentConfiguration.getIndex());
            Map<Character, List<Integer>> allTransitionsForState = transitions.get(currentConfiguration.getState());
            if(allTransitionsForState == null){
                throw new UnknownStateException("No transitions for: " + currentConfiguration.getState());
            }
            List<Integer> smth = allTransitionsForState.get(currentSymb);
            if(smth == null){
                throw new UnknownStateException("No transitions for: " + currentConfiguration.getState() + " " + currentSymb);
            }
            for(Integer i : smth){
                stack.push(new Configuration(i, currentConfiguration.getIndex() + 1));
            }
        }
        return -1;
    }


}
