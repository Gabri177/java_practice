package com.yugao.avajlauncher.aircraft;

import com.yugao.avajlauncher.exception.InputException;

import java.util.Objects;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) throws InputException {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions(){
        if (Objects.equals(this.currentWeather, "unSet"))
            this.currentWeather = this.weatherTower.getWeather(this.coordinates);
        switch (this.currentWeather){
            case "SUN":
                System.out.println(this + " It's sunny today!");
                this.updateCoordinates(10, 0, 2);
                break;
            case "RAIN":
                System.out.println(this + " It's raining!");
                this.updateCoordinates(5, 0, 0);
                break;
            case "FOG":
                System.out.println(this + " It's fog!");
                this.updateCoordinates(1, 0, 0);
                break;
            case "SNOW":
                System.out.println(this + " It's snow!");
                this.updateCoordinates(0, 0, -12);
                break;
            case "GROUND":
                if (!this.isLanding){
                    System.out.println(this + " It's ground!");
                    this.weatherTower.unregister(this);
                    this.isLanding = true;
                }
                break;
        }

    }
}
