package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public abstract class Omnivore extends Animal {

    public Omnivore(double weight, int maxNumInLocation, int speed, double currentFoodForSatiety) {
        super(weight, maxNumInLocation, speed, currentFoodForSatiety);
        this.maxFoodForSatiety = currentFoodForSatiety;
    }

}
