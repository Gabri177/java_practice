package com.yugao.avajlauncher.aircraft;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int p_longitude, int p_latitude, int p_height) {
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = p_height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String toString() {

        return "\t " + this.longitude + " " + this.latitude + " " + this.height;
    }
}
