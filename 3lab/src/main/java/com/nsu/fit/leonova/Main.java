package com.nsu.fit.leonova;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if(args.length != 2){
            System.err.println("Wrong quantity of args.");
            return;
        }

        try(BufferedReader stateMachineReader = new BufferedReader(new FileReader(args[0]));
            BufferedReader expressionReader = new BufferedReader(new FileReader(args[1]))){
            NotDeterminateAutomat stateMachine = new NotDeterminateAutomat(stateMachineReader);
            String expression = expressionReader.readLine();
            int i = stateMachine.recognizeString(expression);
            if(i == -1){
                System.out.println("Couldn't recognize string: " + expression);
            }
            else{
                System.out.println("Recognized " + expression + "as : " + i);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Something really broken: " + e.getMessage());
        } catch (IllegalArgumentException | UnknownStateException e) {
            System.err.println(e.getMessage());
        }
    }


}
