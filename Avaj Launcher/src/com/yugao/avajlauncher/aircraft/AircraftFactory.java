package com.yugao.avajlauncher.aircraft;

import com.yugao.avajlauncher.exception.InputException;
import com.yugao.avajlauncher.interfaces.Flyable;

public class AircraftFactory {

    private static int id;
    private static final AircraftFactory instance = new AircraftFactory();
    private AircraftFactory(){}

    public static AircraftFactory getInstance(){
        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws InputException {

        return switch (p_type) {
            case "Baloon" -> new Baloon(id++, p_name, p_coordinates);
            case "JetPlane" -> new JetPlane(id++, p_name, p_coordinates);
            case "Helicopter" -> new Helicopter(id++, p_name, p_coordinates);
            default -> throw new InputException("File Content Error: Invalid type: " + p_type + "!!!");
        };
    }
}
