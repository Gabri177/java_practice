package com.yugao.avajlauncher.interfaces;

import com.yugao.avajlauncher.tower.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();
    public void registerTower(WeatherTower p_tower) {
        weatherTower = p_tower;
        p_tower.register(this);
    }
}
