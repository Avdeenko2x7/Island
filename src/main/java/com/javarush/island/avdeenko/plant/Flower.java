package com.javarush.island.avdeenko.plant;

import com.javarush.island.avdeenko.island.Location;

import java.util.List;

public class Flower extends Plant{

    public Flower() {
        this.icon = "\uD83C\uDF3C";
    }

//    @Override
//    public void grow(Location location) {
//        List<Plant> plants = location.getPlants();
//        if(plants.size() < this.getMaxNumInLocation() && Math.random() < 0.9){
//            plants.add(new Flower());
//        }
//    }
}
