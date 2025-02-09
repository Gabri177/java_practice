package com.yugao.avajlauncher.weather;

import com.yugao.avajlauncher.aircraft.Coordinates;

import java.util.Objects;

public class WeatherProvider {

    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final WeatherProvider instance = new WeatherProvider();

    private WeatherProvider() {}
    public static WeatherProvider getInstance() {
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int x = p_coordinates.getLongitude();
        int y = p_coordinates.getLatitude();
        int z = p_coordinates.getHeight();
        int hashVal = Objects.hash(x, y, z);
        return weather[Math.abs(hashVal) % 4];
    }
}
