package com.javarush.island.avdeenko.plant;

import com.javarush.island.avdeenko.island.Location;

import java.util.List;

public class Tree extends Plant{

    public Tree() {
        this.icon = "\uD83C\uDF33";
    }

//    @Override
//    public void grow(Location location) {
//        List<Plant> plants = location.getPlants();
//        if(plants.size() < this.getMaxNumInLocation() && Math.random() < 0.9){
//            plants.add(new Tree());
//        }
//    }
}
