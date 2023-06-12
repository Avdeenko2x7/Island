package com.javarush.island.avdeenko.plant;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.predators.Wolf;
import com.javarush.island.avdeenko.island.Location;

import java.util.List;
import java.util.Locale;

public abstract class Plant {
    private int weight;
    private int maxNumInLocation;

    public Plant() {
        this.weight = 1;
        this.maxNumInLocation = 200;
    }

    public abstract void grow(Location location);

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxNumInLocation() {
        return maxNumInLocation;
    }

    public void setMaxNumInLocation(int maxNumInLocation) {
        this.maxNumInLocation = maxNumInLocation;
    }
}
