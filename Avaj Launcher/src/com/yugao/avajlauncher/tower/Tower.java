package com.yugao.avajlauncher.tower;

import com.yugao.avajlauncher.aircraft.Aircraft;
import com.yugao.avajlauncher.interfaces.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        Aircraft currentAircraft = ((Aircraft)p_flyable);
        System.out.println("Tower says: " + currentAircraft.getType() + "#" + currentAircraft.getName()
                + "(" + currentAircraft.getId() + ")"
                + " registered to weather tower");
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        Aircraft currentAircraft = ((Aircraft)p_flyable);
        System.out.println("Tower says: " + currentAircraft.getType() + "#" + currentAircraft.getName()
                + "(" + currentAircraft.getId() + ")"
                + " unregistered from weather tower");
    }

    protected void conditionChanged(){
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
