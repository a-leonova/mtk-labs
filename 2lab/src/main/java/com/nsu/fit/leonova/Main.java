package com.nsu.fit.leonova;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("Wrong quantity of args.");
            return;
        }

        try(BufferedReader stateMachineReader = new BufferedReader(new FileReader(args[0]));
            BufferedReader expressionReader = new BufferedReader(new FileReader(args[1]))){
            StateMachine stateMachine = new MySimpleStateMachine(stateMachineReader);
            String expression = expressionReader.readLine();
            int currentState = 0;
            for(char symb : expression.toCharArray()){
                currentState = stateMachine.getNextState(currentState, symb);
            }
            if(stateMachine.isFinishState(currentState)){
                System.out.println("Line is valid. Result is: " + currentState);
            }
            else {
                System.out.println("Not valid");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
