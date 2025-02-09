package com.yugao.avajlauncher;

import com.yugao.avajlauncher.aircraft.Simulator;
import com.yugao.avajlauncher.exception.InputException;

public class Entry {
    public static void main(String[] args) throws InputException {
        try {
            if (args.length !=  1) {
                throw new InputException("Usage: java Simulator path");
            }
            Simulator sim = new Simulator(args[0]);
            sim.simulationStart();

        } catch (InputException e) {

            System.out.println(e.getMessage());
        }
    }
}
