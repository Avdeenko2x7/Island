package com.javarush.island.avdeenko.plant;

import com.javarush.island.avdeenko.island.Location;

import java.util.List;

public class Grass extends Plant{

    @Override
    public void grow(Location location) {
        List<Plant> plants = location.getPlants();
        if(plants.size() < this.getMaxNumInLocation()){
            plants.add(new Grass());
        }
    }
}
