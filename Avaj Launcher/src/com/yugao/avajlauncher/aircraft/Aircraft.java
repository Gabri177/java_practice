package com.yugao.avajlauncher.aircraft;

import com.yugao.avajlauncher.exception.InputException;
import com.yugao.avajlauncher.interfaces.Flyable;

public abstract class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected String type;
    protected String currentWeather = "unSet";
    protected boolean isLanding = false;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) throws InputException {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
        switch (this) {
            case Helicopter helicopter -> type = "Helicopter";
            case Baloon baloon -> type = "Baloon";
            case JetPlane jetPlane -> type = "JetPlane";
            default -> {
                type = "unknown";
                throw new InputException("File Content Error: Unrecognized Aircraft Name!!!");
            }
        }
//        this.weatherTower = new WeatherTower();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return this.type + "#" + this.name
            + "(" + this.id + ")"
            + ":";
    }

    public void updateCoordinates(int longitudeChange, int latitudeChange, int heightChange) {

        int newLongitude = Math.min(this.coordinates.getLongitude() + longitudeChange, 100);
        int newLatitude = Math.min(this.coordinates.getLatitude() + latitudeChange, 100);
        int newHeight = Math.min(this.coordinates.getHeight() + heightChange, 100);

        if (newLongitude < 0 || newLatitude < 0 || newHeight < 0) {
            this.currentWeather = "GROUND";
            return;
        }

        this.coordinates.setLongitude(newLongitude);
        this.coordinates.setLatitude(newLatitude);
        this.coordinates.setHeight(newHeight);

        this.currentWeather =  this.weatherTower.getWeather(this.coordinates);
    }

}
